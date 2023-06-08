package foro.alura.apiforo.domain.respuesta;

import foro.alura.apiforo.domain.topico.Topico;
import foro.alura.apiforo.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Respuesta")
@Table(name = "respuestas")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idRespuesta")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRespuesta;
    private String mensaje;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private Boolean solucion = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTopico")
    private Topico topico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAutor")
    private Usuario autor;


    public Respuesta(RegistroRespuesta registroRespuesta) {
        this.mensaje = registroRespuesta.mensaje();
        this.topico = new Topico(registroRespuesta.topico());
        this.autor = new Usuario(registroRespuesta.autor());
    }

    public void actualizarDatos(ActualizarRespuesta actualizarRespuesta) {
        if (actualizarRespuesta.mensaje() != null) {
            this.mensaje = actualizarRespuesta.mensaje();
        }
        if (actualizarRespuesta.solucion() != null) {
            this.solucion = actualizarRespuesta.solucion();
        }
        if (actualizarRespuesta.topico() != null) {
            this.topico = new Topico(actualizarRespuesta.topico());
        }
        if (actualizarRespuesta.autor() != null) {
            this.autor = new Usuario(actualizarRespuesta.autor());
        }
    }

}
