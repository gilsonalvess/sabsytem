package br.com.ads.dwpuc.services;

import br.com.ads.dwpuc.models.Agendamento;
import br.com.ads.dwpuc.repositories.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public List<Agendamento> findAll() {
        List<Agendamento> agendamentos = agendamentoRepository.findAll(sortByDataAsc());
        calculaAtraso(agendamentos);
        return agendamentos;
    }

    public List<Agendamento> findAgendamentosByData(LocalDate data) {
        List<Agendamento> agendamentos = agendamentoRepository.findAgendamentosByData(data);
        calculaAtraso(agendamentos);
        return agendamentos;
    }

    public Optional<Agendamento> findOne(Long id) {
        return agendamentoRepository.findById(id);
    }

    public Agendamento acaoStatusAgendamento(Long id, String paramStatus) {
        Agendamento agendamento = agendamentoRepository.getOne(id);
        agendamento.setStatus(paramStatus);
        return agendamentoRepository.save(agendamento);
    }

    public Agendamento save(Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }

    private void calculaAtraso(List<Agendamento> agendamentos) {
        for (Agendamento agendto : agendamentos) {
            LocalTime horaAgora = LocalTime.now();
            LocalDate dataHoje = LocalDate.now();
            boolean statusCondicao = agendto.getStatus().equals("Confirmado");
            if (horaAgora.isAfter(agendto.getHoraAgendamento().plusSeconds(25)) && statusCondicao && agendto.getDataAgendamento().equals(dataHoje)) {
                agendto.setStatus("Atrasado");
            }
        }
    }

    public void remove(Long id) {
        agendamentoRepository.deleteById(id);
    }

    private Sort sortByDataAsc() {
        return new Sort(Sort.Direction.DESC, "dataAgendamento", "horaAgendamento");
    }
}
