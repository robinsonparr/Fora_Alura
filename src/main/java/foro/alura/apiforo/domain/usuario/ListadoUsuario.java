package foro.alura.apiforo.domain.usuario;

public record ListadoUsuario(Long idUsuario, String nombre, String email) {

    public ListadoUsuario(Usuario usuario){
        this(usuario.getIdUsuario(), usuario.getNombre(), usuario.getEmail());
    }
}
