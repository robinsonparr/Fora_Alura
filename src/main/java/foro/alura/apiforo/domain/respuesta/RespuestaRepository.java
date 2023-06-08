package foro.alura.apiforo.domain.respuesta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
    @Query("SELECT R FROM Respuesta R WHERE R.topico.idTopico=:topico")
    Page<Respuesta> buscarPorTopico(Long topico, Pageable paginacion);

    @Query("SELECT R FROM Respuesta R WHERE R.autor.idUsuario=:autor")
    Page<Respuesta> buscarPorAutor(Long autor, Pageable paginacion);

}
