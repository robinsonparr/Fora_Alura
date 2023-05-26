package foro.alura.apiforo.domain.topico;

import jakarta.validation.constraints.NotNull;

public record ActualizarTopico(
        @NotNull
        Long idTopico,
        String titulo,
        String mensaje,
        StatusTopico estado,
        Long autor,
        Long curso) {

}
