package foro.alura.apiforo.domain.curso;

import jakarta.validation.constraints.NotBlank;

public record RegistroCurso(
        @NotBlank
        String nombre,
        @NotBlank
        String categoria) {
}
