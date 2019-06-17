package br.com.ads.dwpuc.controllers;


import br.com.ads.dwpuc.models.Agendamento;
import br.com.ads.dwpuc.services.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/")
public class PainelController {

    @Autowired
    AgendamentoService agendamentoService;

    @GetMapping(value = "/")
    public String painel(Model model) {

        LocalDate data = LocalDate.now();
        List<Agendamento> agendamentos = agendamentoService.findAgendamentosByData(data);
        model.addAttribute("qtdAgendamentos", agendamentos.size());
        model.addAttribute("qtdAtrasos", agendamentos.stream().filter(i -> i.getStatus().equals("Atrasado")).count());
        model.addAttribute("qtdFinalizados", agendamentos.stream().filter(i -> i.getStatus().equals("Finalizado")).count());
        model.addAttribute("qtdCancelados", agendamentos.stream().filter(i -> i.getStatus().equals("Cancelado")).count());
        return "/index";
    }

}
