package foro.alura.apiforo.domain.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ActualizarCurso(
        @NotNull
        Long idCurso,
        String nombre,
        String categoria) {
}
