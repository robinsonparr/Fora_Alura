package foro.alura.apiforo.controller;

import foro.alura.apiforo.domain.usuario.RegistroUsuario;
import foro.alura.apiforo.domain.usuario.RespuestaUsuario;
import foro.alura.apiforo.domain.usuario.Usuario;
import foro.alura.apiforo.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
