package io.github.wendellvalentim.sbootexp_security.service;

import io.github.wendellvalentim.sbootexp_security.domain.entity.Grupo;
import io.github.wendellvalentim.sbootexp_security.domain.entity.Usuario;
import io.github.wendellvalentim.sbootexp_security.domain.entity.UsuarioGrupo;
import io.github.wendellvalentim.sbootexp_security.repository.GrupoRepository;
import io.github.wendellvalentim.sbootexp_security.repository.UsuarioGrupoRepository;
import io.github.wendellvalentim.sbootexp_security.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final GrupoRepository grupoRepository;
    private final UsuarioGrupoRepository usuarioGrupoRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario salvar(Usuario usuario, List<String> grupos) {
        String senhaCripto = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCripto);
        repository.save(usuario);

        List<UsuarioGrupo> listaUsuarioGrupo = grupos.stream().map(nomeGrupo -> {
            Optional<Grupo> possivelGrupo = grupoRepository.findByNome(nomeGrupo);
            if (possivelGrupo.isPresent()) {
                Grupo grupo = possivelGrupo.get();
                return new UsuarioGrupo(usuario, grupo);
            }
            return null;

        }).filter(grupo -> grupo != null).collect(Collectors.toList());
        usuarioGrupoRepository.saveAll(listaUsuarioGrupo);
        return usuario;
    }
    public Usuario obterUsuarioComPermissoes(String login){
        Optional<Usuario> usuarioOptional = repository.findByLogin(login);
        if(usuarioOptional.isEmpty()){
            return null;
        }

        Usuario usuario = usuarioOptional.get();
        List<String> permissoes = usuarioGrupoRepository.findPermissoesByUsuario(usuario);
        usuario.setPermissoes(permissoes);

        return usuario;
    }
}
