package foro.alura.apiforo.domain.topico;

public record ListadoTopico(Long idTopico, String titulo, String mensaje, String fechaCreacion) {

    public ListadoTopico(Topico topico){

        this(topico.getIdTopico(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion().toString());
    }
}
