package io.github.wendellvalentim.sbootexp_security.repository;

import io.github.wendellvalentim.sbootexp_security.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,String> {
}
