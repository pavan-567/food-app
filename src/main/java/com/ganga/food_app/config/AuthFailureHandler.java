package com.ganga.food_app.config;

import java.io.IOException;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.ganga.food_app.helpers.Message;
import com.ganga.food_app.helpers.HelperEnums.MessageType;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AuthFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        if (exception instanceof DisabledException) {
            HttpSession session = request.getSession();
            session.setAttribute("message", new Message(
                    "User Is Disabled, Email With Verification Link Is Sent On To Your Email Id!", MessageType.DANGER));
            response.sendRedirect("/auth/login");
        } else {
            response.sendRedirect("auth/login?error");
        }
    }

}
