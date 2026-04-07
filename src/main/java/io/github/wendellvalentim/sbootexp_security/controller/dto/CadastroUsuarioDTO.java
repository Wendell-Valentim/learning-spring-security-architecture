package io.github.wendellvalentim.sbootexp_security.controller.dto;

import io.github.wendellvalentim.sbootexp_security.domain.entity.Usuario;

import java.util.List;

public record CadastroUsuarioDTO (Usuario usuario,
                                  List<String> permissoes){
}
