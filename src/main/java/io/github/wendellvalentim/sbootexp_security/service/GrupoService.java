package io.github.wendellvalentim.sbootexp_security.service;

import io.github.wendellvalentim.sbootexp_security.domain.entity.Grupo;
import io.github.wendellvalentim.sbootexp_security.repository.GrupoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GrupoService {

    private final GrupoRepository grupoRepository;

    public Grupo salvar (Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    public List<Grupo> buscar() {
        return grupoRepository.findAll();
    }
}
