package com.DualPlay.dtos.request;

public record RegisterRequest (
        String username,
        String email,
        String password,
        String role
){
}
