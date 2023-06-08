package foro.alura.apiforo.domain.curso;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Curso")
@Table(name = "cursos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idCurso")

public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCurso;
    private String nombre;
    private String categoria;

    public Curso(Long cursoId) {
        this.idCurso = cursoId;
    }

    public Curso(RegistroCurso registroCurso) {
        this.nombre = registroCurso.nombre();
        this.categoria = registroCurso.categoria();
    }

    public void actualizarDatos(ActualizarCurso actualizarCurso) {
        if (actualizarCurso.nombre() != null) {
            this.nombre = actualizarCurso.nombre();
        }
        if (actualizarCurso.categoria() != null) {
            this.categoria = actualizarCurso.categoria();
        }
    }

}
