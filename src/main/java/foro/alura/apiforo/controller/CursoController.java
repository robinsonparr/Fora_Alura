package foro.alura.apiforo.controller;

import foro.alura.apiforo.config.errores.TratadorDeErrores;
import foro.alura.apiforo.domain.curso.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/curso")
public class CursoController {

    private final CursoRepository cursoRepository;

    public CursoController(CursoRepository cursoRepository){
        this.cursoRepository = cursoRepository;
    }
    @PostMapping
    public ResponseEntity<RespuestaCurso> registrarUsuario(@RequestBody @Valid RegistroCurso registroCurso,
                                                           UriComponentsBuilder uriComponentsBuilder){
        Curso curso = cursoRepository.save(new Curso(registroCurso));
        RespuestaCurso respuestaCurso = new RespuestaCurso(
                curso.getIdCurso(),
                curso.getNombre(),
                curso.getCategoria()
        );
        URI url = uriComponentsBuilder.path("/curso/{idCurso}").buildAndExpand(curso.getIdCurso()).toUri();
        return ResponseEntity.created(url).body(respuestaCurso);
    }
    @GetMapping
    public ResponseEntity<Page<ListadoCurso>> listadoCurso(@PageableDefault(size = 10, sort = "idCurso") Pageable paginacion) {
        return ResponseEntity.ok(cursoRepository.findAll(paginacion).map(ListadoCurso::new));
    }

    @GetMapping("/{idCurso}")
    public ResponseEntity<RespuestaCurso> RetornaDatosCurso(@PathVariable Long idCurso) {
        Curso curso = cursoRepository.getReferenceById(idCurso);
        var datosCurso = new RespuestaCurso(
                curso.getIdCurso(),
                curso.getNombre(),
                curso.getCategoria()
        );
        return ResponseEntity.ok(datosCurso);
    }

    @PutMapping
   @Transactional
    public ResponseEntity actualizarCurso(@RequestBody @Valid ActualizarCurso actualizarCurso) {
        Curso curso = cursoRepository.getReferenceById(actualizarCurso.idCurso());
        curso.actualizarDatos(actualizarCurso);
        return ResponseEntity.ok(new RespuestaCurso(
                curso.getIdCurso(),
                curso.getNombre(),
                curso.getCategoria()
        ));
    }
    @DeleteMapping("/{idCurso}")
    @Transactional
    public ResponseEntity eliminarCurso(@PathVariable Long idCurso) {
        if (cursoRepository.existsById(idCurso)) {
            Curso curso = cursoRepository.getReferenceById(idCurso);
            cursoRepository.delete(curso);
            return ResponseEntity.noContent().build();
        }
        return new TratadorDeErrores().tratarError404();
    }

}


