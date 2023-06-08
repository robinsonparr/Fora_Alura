package foro.alura.apiforo.controller;

import foro.alura.apiforo.config.errores.TratadorDeErrores;
import foro.alura.apiforo.domain.usuario.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {


    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;


    public UsuarioController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<RespuestaUsuario> registrarUsuario(
        @RequestBody @Valid RegistroUsuario registroUsuario,
        UriComponentsBuilder uriComponentsBuilder){
        Usuario usuario = new Usuario(registroUsuario);
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        usuarioRepository.save(usuario);
        RespuestaUsuario respuestaUsuario = new RespuestaUsuario(
                usuario.getIdUsuario(),
                usuario.getNombre(),
                usuario.getEmail()
        );
        URI url = uriComponentsBuilder.path("/usuario/{idUsuario}").buildAndExpand(usuario.getIdUsuario()).toUri();
        return ResponseEntity.created(url).body(respuestaUsuario);
    }
    @GetMapping
    public ResponseEntity<Page<ListadoUsuario>>listadoUsuario(@PageableDefault(size = 10, sort = "idUsuario") Pageable paginacion) {
        return ResponseEntity.ok(usuarioRepository.findAll(paginacion).map(ListadoUsuario::new));
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<?> RetornarUsuario(@PathVariable Long idUsuario) {
        Usuario usuario = usuarioRepository.getReferenceById(idUsuario);
        var datosUsuario = new RespuestaUsuario(
                usuario.getIdUsuario(),
                usuario.getNombre(),
                usuario.getEmail()
        );
        return ResponseEntity.ok(datosUsuario);
    }
    @PutMapping
  @Transactional
    public ResponseEntity actualizarUsuario(@RequestBody @Valid ActualizarUsuario actualizarUsuario) {
        Usuario usuario = usuarioRepository.getReferenceById(actualizarUsuario.idUsuario());
        usuario.actualizar(actualizarUsuario);
        return ResponseEntity.ok(new RespuestaUsuario(
                usuario.getIdUsuario(),
                usuario.getNombre(),
                usuario.getEmail())
        );
    }
    @DeleteMapping("/{idUsuario}")
    @Transactional
    public ResponseEntity eliminarUsuario(@PathVariable Long idUsuario) {
        if (usuarioRepository.existsById(idUsuario)) {
            Usuario usuario = usuarioRepository.getReferenceById(idUsuario);
            usuarioRepository.delete(usuario);
            return ResponseEntity.noContent().build();
        }
        return new TratadorDeErrores().tratarError404();
}
}
