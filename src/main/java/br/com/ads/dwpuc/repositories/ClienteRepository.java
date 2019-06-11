package br.com.ads.dwpuc.repositories;

import br.com.ads.dwpuc.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query(value = "SELECT c FROM Cliente c WHERE c.nome LIKE %:nome%")
    List<Cliente> queryClienteByNome(@Param("nome") String nome);
}
