package foro.alura.apiforo.controller;

import foro.alura.apiforo.config.errores.TratadorDeErrores;
import foro.alura.apiforo.domain.respuesta.*;
import foro.alura.apiforo.domain.topico.Topico;
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
@RequestMapping("/respuesta")
public class RespuestaController {

    private final RespuestaRepository respuestaRepository;

    public RespuestaController(RespuestaRepository respuestaRepository) {
        this.respuestaRepository = respuestaRepository;
}
    @PostMapping
    public ResponseEntity<DatosRespuesta> registrarRespuesta(@RequestBody @Valid RegistroRespuesta registroRespuesta,
                                                             UriComponentsBuilder uriComponentsBuilder) {
        Respuesta respuesta = respuestaRepository.save(new Respuesta(registroRespuesta));
        DatosRespuesta datosRespuesta  = new DatosRespuesta(
                respuesta.getIdRespuesta(),
                respuesta.getMensaje(),
                respuesta.getFechaCreacion(),
                respuesta.getSolucion(),
                new Topico(respuesta.getTopico().getIdTopico()).getIdTopico(),
                new Usuario(respuesta.getAutor().getIdUsuario()).getIdUsuario()
        );
        URI url = uriComponentsBuilder.path("/respuesta/{idRespuesta}").buildAndExpand(respuesta.getIdRespuesta()).toUri();
        return ResponseEntity.created(url).body(datosRespuesta);
    }
    @GetMapping
    public ResponseEntity<Page<ListadoRespuesta>> listadoRespuesta(@PageableDefault(size = 10, sort = "idRespuesta") Pageable paginacion) {
        return ResponseEntity.ok(respuestaRepository.findAll(paginacion).map(ListadoRespuesta::new));
    }
    @GetMapping("/topico/{topico}")
    public ResponseEntity<Page<ListadoRespuesta>> listadoRespuestaPorTopico(@PathVariable Long topico,
                                                                                 @PageableDefault(size = 10, sort = "idRespuesta") Pageable paginacion) {
        return ResponseEntity.ok(respuestaRepository
                .buscarPorTopico(topico, paginacion).map(ListadoRespuesta::new));
    }

    @GetMapping("/autor/{autor}")
    public ResponseEntity<Page<ListadoRespuesta>> listadoRespuestaPorAutor(@PathVariable Long autor,
                                                                                @PageableDefault(size = 10, sort = "idRespeusta") Pageable paginacion) {
        return ResponseEntity.ok(respuestaRepository
                .buscarPorAutor(autor, paginacion).map(ListadoRespuesta::new));
    }
    @GetMapping("/{idRespuesta}")
    public ResponseEntity<?> RetornarDatosRespuesta(@PathVariable Long idRespuesta) {
        Respuesta respuesta = respuestaRepository.getReferenceById(idRespuesta);
        var datosRespuesta = new ListadoRespuesta(
                respuesta.getIdRespuesta(),
                respuesta.getMensaje(),
                respuesta.getFechaCreacion().toString(),
                respuesta.getSolucion()
        );
        return ResponseEntity.ok(datosRespuesta);
    }
    @PutMapping
    @Transactional
    public ResponseEntity actualizarDatos(@RequestBody @Valid ActualizarRespuesta actualizarRespuesta) {
        Respuesta respuesta = respuestaRepository.getReferenceById(actualizarRespuesta.idRespuesta());
        respuesta.actualizarDatos(actualizarRespuesta);
        return ResponseEntity.ok(new DatosRespuesta(
                respuesta.getIdRespuesta(),
                respuesta.getMensaje(),
                respuesta.getFechaCreacion(),
                respuesta.getSolucion(),
                new Topico(respuesta.getTopico().getIdTopico()).getIdTopico(),
                new Usuario(respuesta.getAutor().getIdUsuario()).getIdUsuario())
        );
    }
    @DeleteMapping("/{idRespuesta}")
    @Transactional
    public ResponseEntity eliminarRespuesta(@PathVariable Long idRespuesta) {
        if (respuestaRepository.existsById(idRespuesta)) {
            Respuesta respuesta = respuestaRepository.getReferenceById(idRespuesta);
            respuestaRepository.delete(respuesta);
            return ResponseEntity.noContent().build();
        }
        return new TratadorDeErrores().tratarError404();
    }

}
