package com.sm.customerservice.util;

import lombok.Data;

@Data
public class CustomResponse {
    private String message;
    private int statusCode;

    public CustomResponse(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

}
