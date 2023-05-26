package foro.alura.apiforo.domain.usuario;

import jakarta.validation.constraints.NotNull;

public record ActualizarUusario(
        @NotNull
        Long id,
        String nombre,
        String email,
        String contrasena) {
}
