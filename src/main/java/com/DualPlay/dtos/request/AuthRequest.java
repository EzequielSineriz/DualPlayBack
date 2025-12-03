package com.DualPlay.dtos.request;

public record AuthRequest(
        String email,
        String password
) {
}
