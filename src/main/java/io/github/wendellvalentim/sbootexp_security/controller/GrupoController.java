package io.github.wendellvalentim.sbootexp_security.controller;

import io.github.wendellvalentim.sbootexp_security.domain.entity.Grupo;
import io.github.wendellvalentim.sbootexp_security.repository.GrupoRepository;
import io.github.wendellvalentim.sbootexp_security.service.GrupoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos")
@RequiredArgsConstructor
public class GrupoController {

    private final GrupoService grupoService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Grupo> salvar(@RequestBody Grupo grupo) {
        grupoService.salvar(grupo);
        return ResponseEntity.ok(grupo);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Grupo>> listar() {
        return ResponseEntity.ok(grupoService.buscar());
    }
}
