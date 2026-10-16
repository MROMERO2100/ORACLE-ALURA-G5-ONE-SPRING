package com.clinic.api.controller;

import com.clinic.api.domain.usuario.DatosAutenticacionUsuario;
import com.clinic.api.domain.usuario.Usuario;
import com.clinic.api.infra.security.DatosJWTToken;
import com.clinic.api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AutenticationController(
            AuthenticationManager authenticationManager,
            TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<DatosJWTToken> autenticarUsuario(
            @RequestBody @Valid DatosAutenticacionUsuario datos) {

        var authenticationToken =
                new UsernamePasswordAuthenticationToken(datos.login(), datos.clave());

        var authenticatedUser = authenticationManager.authenticate(authenticationToken);
        var jwtToken = tokenService.generarToken((Usuario) authenticatedUser.getPrincipal());

        return ResponseEntity.ok(new DatosJWTToken(jwtToken));
    }
}
