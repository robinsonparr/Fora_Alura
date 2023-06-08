package foro.alura.apiforo.controller;

import foro.alura.apiforo.config.security.JwtToken;
import foro.alura.apiforo.config.security.TokenService;
import foro.alura.apiforo.domain.usuario.AutenticacionUsuario;
import foro.alura.apiforo.domain.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;


    public AutenticacionController(AuthenticationManager authenticationManager, TokenService tokenService){
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }
     @PostMapping
    public ResponseEntity autenticarLogin(@RequestBody @Valid AutenticacionUsuario autenticacionUsuario){
        Authentication authToken = new UsernamePasswordAuthenticationToken(autenticacionUsuario.nombre(),
                autenticacionUsuario.contrasena());
        var authUsuario = authenticationManager.authenticate(authToken);
        var jwtToken = tokenService.generarToken((Usuario) authUsuario.getPrincipal());
        return ResponseEntity.ok(new JwtToken(jwtToken));

}
}
