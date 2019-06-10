package br.com.ads.dwpuc.models;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "agendamento")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @JoinColumn(name = "cliente_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Cliente cliente;

    @NotBlank
    @Column(name = "data_agendamento", nullable = false)
    private LocalDate dataAgendamento;

    @NotBlank
    @Column(name = "hora_agendamento", nullable = false)
    private LocalDateTime horaAgendamento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDate dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public LocalDateTime getHoraAgendamento() {
        return horaAgendamento;
    }

    public void setHoraAgendamento(LocalDateTime horaAgendamento) {
        this.horaAgendamento = horaAgendamento;
    }
}
