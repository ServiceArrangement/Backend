package com.fixnow.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String usuario;
    private String password;
}