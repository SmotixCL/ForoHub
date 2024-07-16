package com.alura.forohub.security;

public class SecurityConstants {
    public static final String SECRET = "fernandosp"; //clave
    public static final long EXPIRATION_TIME = 864000000; // 10 días en milisegundos
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
