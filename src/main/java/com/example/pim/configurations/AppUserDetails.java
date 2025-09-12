package com.example.pim.configurations;

import com.example.pim.models.entities.ClientEntity;
import com.example.pim.models.entities.TecEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AppUserDetails implements UserDetails {

    private final String username;
    private final String password;
    private final List<GrantedAuthority> authorities;

    public AppUserDetails(TecEntity tec) {
        this.username = tec.getEmail();
        this.password = tec.getPassword();
        this.authorities = List.of(new SimpleGrantedAuthority("ROLE_" + tec.getPermission().name()));
    }

    public AppUserDetails(ClientEntity client) {
        this.username = client.getEmail();
        this.password = client.getPassword();
        this.authorities = List.of(new SimpleGrantedAuthority("ROLE_" + client.getSector().name()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
