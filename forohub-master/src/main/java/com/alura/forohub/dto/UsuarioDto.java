package com.alura.forohub.dto;

public class UsuarioDto {
    private String username;
    private String password;
    private String correo_electronico;

    public UsuarioDto() {
        // Constructor vacío necesario para deserialización por frameworks como Jackson
    }

    public UsuarioDto(String username, String password) {
        this.username = username;
        this.password = password;
        this.correo_electronico = correo_electronico;
    }

    // Getters y setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return correo_electronico;
    }
    public void setEmail(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }
}
