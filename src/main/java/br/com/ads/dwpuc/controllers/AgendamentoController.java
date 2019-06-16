package br.com.ads.dwpuc.controllers;

import br.com.ads.dwpuc.models.Agendamento;
import br.com.ads.dwpuc.services.AgendamentoService;
import br.com.ads.dwpuc.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    AgendamentoService agendamentoService;

    @Autowired
    ClienteService clienteService;

    @GetMapping(value = "/listar")
    public String listar(Model model) {
        model.addAttribute("agendamentos", agendamentoService.findAll());
        model.addAttribute("clientes", clienteService.findAll());
        return "html_pages/agendamentos";
    }

    @PostMapping(value = "/adicionar")
    public String adicionar(Agendamento agendamento){
        agendamento.setStatus("A confirmado");
        agendamentoService.save(agendamento);
        return "redirect:/agendamentos/listar";
    }

    @RequestMapping(value = "/status/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Agendamento acaoStatus(@PathVariable("id") Long id, @RequestParam(name = "paramStatus") String paramStatus) {
        return agendamentoService.acaoStatusAgendamento(id, paramStatus);
    }

}
