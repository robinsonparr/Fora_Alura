package foro.alura.apiforo.domain.topico;

import foro.alura.apiforo.domain.curso.Curso;
import foro.alura.apiforo.domain.respuesta.Respuesta;
import foro.alura.apiforo.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.NavigableMap;
import java.util.Set;

@Entity(name = "Topico")
@Table(name = "topicos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idTopico")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTopico;
    private String titulo;
    private String mensjae;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private StatusTopico estado = StatusTopico.NO_RESPONDIDO;
    private Usuario autor;
    private Curso curso;
    private Set<Respuesta> respuesta = new HashSet<>();


}
