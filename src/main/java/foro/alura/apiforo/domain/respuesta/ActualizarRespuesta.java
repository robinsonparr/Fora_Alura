package foro.alura.apiforo.domain.respuesta;

import jakarta.validation.constraints.NotNull;

public record ActualizarRespuesta(
        @NotNull
        Long idRespuesta,
        String mensaje,
        Boolean solucion,
        Long topico,
        Long autor) {
}
