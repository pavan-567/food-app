package com.ganga.food_app.entities.json;

public class FoodKaka {
    private int id;
    private String name;
    private String image;
    private int price;
    private String description;
    private String category;
    public FoodKaka(int id, String name, String image, int price, String description, String category) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public FoodKaka() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "FoodKaka [id=" + id + ", name=" + name + ", image=" + image + ", price=" + price + ", description="
                + description + ", category=" + category + "]";
    }

    
}
