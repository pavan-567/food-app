package com.ganga.food_app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.ganga.food_app.entities.Address;
import com.ganga.food_app.entities.Cart;
import com.ganga.food_app.entities.Orders;
import com.ganga.food_app.entities.User;
import com.ganga.food_app.helpers.CartInput;
import com.ganga.food_app.helpers.Message;
import com.ganga.food_app.helpers.HelperEnums.MessageType;
import com.ganga.food_app.services.AddressService;
import com.ganga.food_app.services.OrdersService;
import com.ganga.food_app.services.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/payment")
public class PaypalController {
    @Autowired
    private PaypalService paypalService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private AddressService addressService;

    @PostMapping("/create")
    public RedirectView createPayment(
            @RequestParam("method") String method,
            @RequestParam("amount") String amount,
            @RequestParam("currency") String currency,
            @RequestParam("description") String description, 
            @RequestParam("address") UUID addr,
            HttpSession session) {
        try {
            String cancelUrl = "http://localhost:8080/payment/cancel";
            String successUrl = "http://localhost:8080/payment/success";
            Payment payment = paypalService.createPayment(Double.valueOf(amount), currency, method, "sale", description,
                    cancelUrl, successUrl);

            session.setAttribute("address", addr);
            for (Links links : payment.getLinks()) {
                if (links.getRel().equals("approval_url")) {
                    return new RedirectView(links.getHref());
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return new RedirectView("/payments/error");
    }

    @GetMapping("/success")
    public String paymentSuccess(@RequestParam("paymentId") String paymentId,
            @RequestParam("PayerID") String payerId, HttpSession session, Model model) {

        UUID addressId = (UUID) session.getAttribute("address");
        List<CartInput> cartInputs = (List<CartInput>) session.getAttribute("cart");

        User user = (User) model.getAttribute("loggedUser");
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                Address addr = addressService.getAddress(addressId);
                Orders order = new Orders();

                for(var inp: cartInputs) {
                    Cart c = new Cart();
                    c.setQuantity(inp.getQuantity());
                    c.setFood(inp.getFood());
                    order.addToCart(c);
                }

                order.setPaymentStatus(true);
                order.setAddress(addr);
                order.setUser(user);
                order.setPaymentID(paymentId);


                List<Transaction> t = payment.getTransactions();
                double total = 0;
                for(var transaction: t) {
                    total = Double.parseDouble(transaction.getAmount().getTotal());
                }
                order.setAmount((int) Math.round(total));
                order.setOrderStatus("processing");

                ordersService.save(order);
                
                session.removeAttribute("cart");
                session.removeAttribute("address");
                session.setAttribute("message", new Message("Order Placed Successfully!", MessageType.SUCCESS));
                return "redirect:/orders";
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "payment/paymentSuccess";
    }

    @GetMapping("/cancel")
    public String paymentCancel(HttpSession session) {
        session.removeAttribute("address");
        session.setAttribute("message", new Message("Payment Cancelled! Please Try Again!", MessageType.DANGER));
        return "redirect:/orders";
    }

    @GetMapping("/error")
    public String paymentError(HttpSession session) {
        session.removeAttribute("address");
        return "payment/paymentError";
    }

}
