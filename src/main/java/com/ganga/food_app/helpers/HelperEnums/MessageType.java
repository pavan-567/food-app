package com.ganga.food_app.helpers.HelperEnums;

public enum MessageType {
    SUCCESS("success"),
    PRIMARY("primary"),
    DANGER("danger"),
    WARNING("warning");

    String color;

    private MessageType(String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }
}
