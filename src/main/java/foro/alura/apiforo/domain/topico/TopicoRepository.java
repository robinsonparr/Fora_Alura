package foro.alura.apiforo.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicoRepository  extends JpaRepository<Topico, Long> {
    @Query("SELECT T FROM Topico T WHERE T.autor.idUsuario=:autor")
    Page<Topico> buscarPorAutor(Long autor, Pageable paginacion);

    @Query("SELECT T FROM Topico T WHERE T.curso.idCurso=:curso")
    Page<Topico> buscarPorCurso(Long curso, Pageable paginacion);
}
