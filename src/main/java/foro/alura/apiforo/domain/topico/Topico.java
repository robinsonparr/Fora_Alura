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

    private String mensaje;

    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatusTopico estado = StatusTopico.NO_RESPONDIDO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAutor")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCurso")
    private Curso curso;

   @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL)
    private Set<Respuesta> respuesta = new HashSet<>();

    public Topico(Long idTopico) {
        this.idTopico = idTopico;
    }

    public Topico(RegistroTopico registroTopico) {
        this.titulo = registroTopico.titulo();
        this.mensaje = registroTopico.mensaje();
        this.estado = registroTopico.estado();
        this.autor = new Usuario(registroTopico.autor());
        this.curso = new Curso(registroTopico.curso());
    }

    public void actualizarDatos(ActualizarTopico actualizarTopico) {
        if (actualizarTopico.titulo() != null) {
            this.titulo = actualizarTopico.titulo();
        }
        if (actualizarTopico.mensaje() != null) {
            this.mensaje = actualizarTopico.mensaje();
        }
        if (actualizarTopico.estado() != null) {
            this.estado = actualizarTopico.estado();
        }
        if (actualizarTopico.autor() != null) {
            this.autor = new Usuario(actualizarTopico.autor());
        }
        if (actualizarTopico.curso() != null) {
            this.curso = new Curso(actualizarTopico.curso());
        }
    }

}

