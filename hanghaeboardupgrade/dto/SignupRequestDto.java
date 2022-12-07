package com.example.hanghaeboardupgrade.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
public class SignupRequestDto {

    @Size(min = 4, max = 10)
    @Pattern(regexp ="[a-z0-9]")
    private String username;


    @Pattern(regexp = "[a-zA-Z0-9]{8,15}")
    private String password;

}