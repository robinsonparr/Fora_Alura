package foro.alura.apiforo.domain.respuesta;

public record ListadoRespuesta(
        Long idRespuesta,
        String mensaje,
        String fechaCreacion,
        Boolean solucion) {

    public  ListadoRespuesta(Respuesta respuesta){
        this(respuesta.getIdRespuesta(),
                respuesta.getMensaje(),
                respuesta.getFechaCreacion().toString(),
                respuesta.getSolucion()

        );
    }
}
