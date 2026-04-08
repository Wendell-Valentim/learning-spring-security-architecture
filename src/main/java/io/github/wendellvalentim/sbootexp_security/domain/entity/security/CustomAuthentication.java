package io.github.wendellvalentim.sbootexp_security.domain.entity.security;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomAuthentication implements Authentication {
    private final IdentificacaoUsuario identificacaoUsuario;

    public CustomAuthentication(IdentificacaoUsuario identificacaoUsuario) {
        if(identificacaoUsuario == null){
            throw new ExceptionInInitializerError("Não é possível criar um customauthentication sem a identificacao do usuario");
        }
        this.identificacaoUsuario = identificacaoUsuario;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.identificacaoUsuario.getPermissoes()
                .stream()
                .map(permissao -> new SimpleGrantedAuthority(permissao))
                .collect(Collectors.toList());
    }

    @Override
    public @Nullable Object getCredentials() {
        return null;
    }

    @Override
    //meta dados se eu quiser retornar
    public @Nullable Object getDetails() {
        return null;
    }

    @Override
    //identificação do usuario
    public @Nullable Object getPrincipal() {
        return this.identificacaoUsuario;
    }

    @Override
    //sempre true, para logar a authentication
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new IllegalArgumentException("Nao precisa chamar, ja estamos autenticados");
    }

    @Override
    public String getName() {
        return this.identificacaoUsuario.getNome();
    }
}
