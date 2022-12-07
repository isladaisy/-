package com.example.hanghaeboardupgrade.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto {

    protected int statusCode;
    private String message;

    public ResponseDto(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }


}
