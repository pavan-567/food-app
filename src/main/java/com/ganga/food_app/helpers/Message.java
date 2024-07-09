package com.ganga.food_app.helpers;

import com.ganga.food_app.helpers.HelperEnums.MessageType;

public class Message {
    public String content;
    public MessageType type = MessageType.PRIMARY;

    public Message(String content, MessageType type) {
        this.content = content;
        this.type = type;
    }

    public Message() {

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    
}
