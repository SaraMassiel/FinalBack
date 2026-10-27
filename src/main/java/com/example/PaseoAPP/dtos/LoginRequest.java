package com.example.PaseoAPP.dtos;

public record LoginRequest(
        String correo,
        String contraseña
) {}