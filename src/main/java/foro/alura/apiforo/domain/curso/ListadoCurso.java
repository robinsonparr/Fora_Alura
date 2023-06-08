package foro.alura.apiforo.domain.curso;

public record ListadoCurso(Long idCurso, String nombre, String categoria) {
    public  ListadoCurso(Curso curso){
        this(curso.getIdCurso(), curso.getNombre(), curso.getCategoria());
    }
}
