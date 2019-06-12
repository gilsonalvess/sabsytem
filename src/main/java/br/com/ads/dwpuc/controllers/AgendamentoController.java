package br.com.ads.dwpuc.controllers;

import br.com.ads.dwpuc.repositories.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    AgendamentoRepository agendamentoRepository;

    @GetMapping(value = "/listar")
    public String listar(Model model) {
        model.addAttribute("agendamentos", agendamentoRepository.findAll());
        return "html_pages/agendamentos";
    }

}
