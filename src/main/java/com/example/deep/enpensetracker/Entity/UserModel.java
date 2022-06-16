package com.example.deep.enpensetracker.Entity;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserModel {

    @NotBlank(message = "Please enter your name")
    private String name;
    @NotNull(message = "Email should not be empty")
    @Email
    private String email;
    @NotNull
    @Size(min = 5 , message = "Password should be at least 5 character long")
    private String password;
    private Long age=0L;

}
