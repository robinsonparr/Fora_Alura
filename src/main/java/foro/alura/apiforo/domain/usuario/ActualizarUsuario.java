package foro.alura.apiforo.domain.usuario;

import jakarta.validation.constraints.NotNull;

public record ActualizarUsuario(
        @NotNull
        Long idUsuario,
        String nombre,
        String email,
        String contrasena) {
}
