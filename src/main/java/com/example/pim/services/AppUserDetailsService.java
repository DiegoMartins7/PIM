package com.example.pim.services;

import com.example.pim.configurations.AppUserDetails;
import com.example.pim.models.entities.ClientEntity;
import com.example.pim.models.entities.TecEntity;
import com.example.pim.repositorys.ClientRepository;
import com.example.pim.repositorys.TecRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final TecRepository tecRepository;
    private final ClientRepository clientRepository;

    public AppUserDetailsService(TecRepository tecRepository, ClientRepository clientRepository) {
        this.tecRepository = tecRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Primeiro procura técnico
        TecEntity tec = tecRepository.findByEmail(username).orElse(null);
        if (tec != null) return new AppUserDetails(tec);

        // Depois procura cliente
        ClientEntity client = clientRepository.findByEmail(username).orElse(null);
        if (client != null) return new AppUserDetails(client);

        throw new UsernameNotFoundException("Usuário não encontrado: " + username);
    }
}
