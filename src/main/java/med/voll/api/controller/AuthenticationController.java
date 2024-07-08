package med.voll.api.controller;

import med.voll.api.domain.dto.DatosAuthenticationUsuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    private AuthenticationManager authManager;

    @PostMapping
    public ResponseEntity autenticarUsuario(DatosAuthenticationUsuario datosUsuario){
        Authentication token = new UsernamePasswordAuthenticationToken(datosUsuario.username(), datosUsuario.password());
        authManager.authenticate(token);
        return ResponseEntity.ok().build();
    }

}
