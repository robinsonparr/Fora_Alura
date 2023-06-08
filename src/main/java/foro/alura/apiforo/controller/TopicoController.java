package foro.alura.apiforo.controller;

import foro.alura.apiforo.config.errores.TratadorDeErrores;
import foro.alura.apiforo.domain.curso.Curso;
import foro.alura.apiforo.domain.topico.*;
import foro.alura.apiforo.domain.usuario.Usuario;
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
@RequestMapping("/topico")

public class TopicoController {

    private final TopicoRepository topicoRepository;

    public TopicoController(TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }
    @PostMapping
    public ResponseEntity<RespuestaTopico> registrarTopico(@RequestBody @Valid RegistroTopico registroTopico,
                                                           UriComponentsBuilder uriComponentsBuilder) {
        Topico topico = topicoRepository.save(new Topico(registroTopico));
        RespuestaTopico respuestaTopico = new RespuestaTopico(
                topico.getIdTopico(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getEstado(),
                new Usuario(topico.getAutor().getIdUsuario()).getIdUsuario(),
                new Curso(topico.getCurso().getIdCurso()).getIdCurso()
        );
        URI url = uriComponentsBuilder.path("/topico/{idTopico}").buildAndExpand(topico.getIdTopico()).toUri();
        return ResponseEntity.created(url).body(respuestaTopico);
    }
   @GetMapping
    public ResponseEntity<Page<ListadoTopico>> listadoTopico(@PageableDefault(size = 10, sort = "idTopico") Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(ListadoTopico::new));
    }

    @GetMapping("/autor/{autor}")
    public ResponseEntity<Page<ListadoTopico>> listadoTopicoPorAutor(@PathVariable Long autor,
                                                                          @PageableDefault(size = 10, sort = "idTopico") Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository
                .buscarPorAutor(autor, paginacion).map(ListadoTopico::new));
    }

    @GetMapping("/curso/{curso}")
    public ResponseEntity<Page<ListadoTopico>> listadoTopicoPorCurso(@PathVariable Long curso,
                                                                          @PageableDefault(size = 10, sort = "idTopico") Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository
                .buscarPorCurso(curso, paginacion).map(ListadoTopico::new));
    }
    @GetMapping("/{idTopico}")
    public ResponseEntity<?> retornaDatosTopico(@PathVariable Long idTopico) {
        Topico topico = topicoRepository.getReferenceById(idTopico);

        var datosTopico = new ListadoTopico(
                topico.getIdTopico(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion().toString()
        );
        return ResponseEntity.ok(datosTopico);
    }

    @PutMapping()
    @Transactional
    public ResponseEntity ActualizarTopico(@RequestBody @Valid ActualizarTopico actualizarTopico) {
        Topico topico = topicoRepository.getReferenceById(actualizarTopico.idTopico());
        topico.actualizarDatos(actualizarTopico);
        return ResponseEntity.ok(new RespuestaTopico(
                topico.getIdTopico(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getEstado(),
                new Usuario(topico.getAutor().getIdUsuario()).getIdUsuario(),
                new Curso(topico.getCurso().getIdCurso()).getIdCurso())
        );
    }

    @DeleteMapping("/{idTopico}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long idTopico) {
        if (topicoRepository.existsById(idTopico)) {
            Topico topico = topicoRepository.getReferenceById(idTopico);
            topicoRepository.delete(topico);
            return ResponseEntity.noContent().build();
        }
        return new TratadorDeErrores().tratarError404();

    }

}
