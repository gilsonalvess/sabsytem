package br.com.ads.dwpuc.repositories;

import br.com.ads.dwpuc.models.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    @Query(value = "SELECT a FROM Agendamento a WHERE a.dataAgendamento = :data")
    List<Agendamento> findAgendamentosByData(@Param("data") LocalDate data);
}
