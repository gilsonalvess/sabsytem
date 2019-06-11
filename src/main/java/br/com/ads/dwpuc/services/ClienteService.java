package br.com.ads.dwpuc.services;

import br.com.ads.dwpuc.models.Cliente;
import br.com.ads.dwpuc.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findOne(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.saveAndFlush(cliente);
    }

    public void remove(Long id){
        clienteRepository.deleteById(id);
    }

    public List<Cliente> queryClienteByNome (String nome){
        return clienteRepository.queryClienteByNome(nome);
    }
}
