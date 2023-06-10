package foro.alura.apiforo.domain.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idUsuario")

public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Long idUsuario;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "email")
    private String email;

    @Column(name = "contrasena")
    private String contrasena;

    public Usuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public void setContrasena(String contrasena){
        this.contrasena = contrasena;
    }
    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return nombre;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Usuario(RegistroUsuario registroUsuario){
        this.nombre = registroUsuario.nombre();
        this.email = registroUsuario.email();
        this.contrasena = registroUsuario.contrasena();
    }
    public void actualizar(ActualizarUsuario actualizarUsuario){
        if (actualizarUsuario.nombre() != null){
            this.nombre = actualizarUsuario.nombre();
        }
        if (actualizarUsuario.email() != null) {
            this.email = actualizarUsuario.email();

        }
        if (actualizarUsuario.contrasena() != null) {
            this.contrasena = actualizarUsuario.contrasena();

        }
    }

}
