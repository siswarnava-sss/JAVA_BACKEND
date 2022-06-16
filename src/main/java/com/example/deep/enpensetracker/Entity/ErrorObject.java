package com.example.deep.enpensetracker.Entity;

import lombok.Data;

@Data
public class ErrorObject {
    private Integer statusCode;
    private String message;
    private String timestamp;



}
