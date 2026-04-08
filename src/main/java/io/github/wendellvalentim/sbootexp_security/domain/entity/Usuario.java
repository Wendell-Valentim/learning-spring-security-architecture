package io.github.wendellvalentim.sbootexp_security.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String login;
    private String senha;
    private String nome;


    //nao é uma coluna do banco de dados, utilizada para ignorar mapeamento JPA.
    @Transient
    private List<String> permissoes;


}
