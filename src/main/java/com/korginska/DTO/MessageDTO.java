package com.korginska.DTO;

/**
 * Created by Sofia on 19.12.2017.
 */
public class MessageDTO {
    private String message;

    public MessageDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
