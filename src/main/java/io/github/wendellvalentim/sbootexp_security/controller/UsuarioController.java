package io.github.wendellvalentim.sbootexp_security.controller;

import io.github.wendellvalentim.sbootexp_security.controller.dto.CadastroUsuarioDTO;
import io.github.wendellvalentim.sbootexp_security.domain.entity.Usuario;
import io.github.wendellvalentim.sbootexp_security.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;
    @PostMapping
    public ResponseEntity<Usuario> salvar(@RequestBody CadastroUsuarioDTO body) {
        Usuario UsuarioSalvo = usuarioService.salvar(body.usuario(), body.permissoes());
        return ResponseEntity.ok(UsuarioSalvo);
    }
}
