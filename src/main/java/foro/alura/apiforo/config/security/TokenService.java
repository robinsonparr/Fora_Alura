package foro.alura.apiforo.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import foro.alura.apiforo.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("foro")
                    .withSubject(usuario.getNombre())
                    .withClaim("idUsuario", usuario.getIdUsuario())
                    .withExpiresAt(generarFechaDeExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    private Instant generarFechaDeExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }


    public String getSubject (String token){
        if (token == null){
            throw new RuntimeException("Token invalid!");

        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            verifier = JWT.require(algorithm)
                    .withIssuer("foro")
                    .build()
                    .verify(token);
            verifier.getSubject();

        }catch (JWTVerificationException exception){
            throw  new RuntimeException("Invalid signature/claims!");
        }
        if (verifier.getSubject()== null){
            throw new RuntimeException("verifier invalido!");

        }
        return verifier.getSubject();

    }
}


