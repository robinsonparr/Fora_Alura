package foro.alura.apiforo.domain.respuesta;

import java.time.LocalDateTime;

public record DatosRespuesta(
        Long idRespuesta,
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean solucion,
        Long topico,
        Long autor) {
}
