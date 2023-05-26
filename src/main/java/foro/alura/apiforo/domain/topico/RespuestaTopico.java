package foro.alura.apiforo.domain.topico;

import java.time.LocalDateTime;

public record RespuestaTopico(
        Long idTopico,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        StatusTopico estado,
        Long autor,
        Long curso) {
}
