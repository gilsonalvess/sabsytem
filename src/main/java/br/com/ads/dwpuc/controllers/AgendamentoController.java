package br.com.ads.dwpuc.controllers;

import br.com.ads.dwpuc.models.Agendamento;
import br.com.ads.dwpuc.services.AgendamentoService;
import br.com.ads.dwpuc.services.ClienteService;
import br.com.ads.dwpuc.services.GeradorDePdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    AgendamentoService agendamentoService;

    @Autowired
    ClienteService clienteService;

    @GetMapping(value = "/listar")
    public String listar(Model model) {
        model.addAttribute("infoData", "Todos agendamentos");
        model.addAttribute("agendamentos", agendamentoService.findAll());
        model.addAttribute("clientes", clienteService.findAll());
        return "html_pages/agendamentos";
    }

    @GetMapping(value = "/hoje")
    public String agendamentosDoDia(Model model) {
        LocalDate dataHoje = LocalDate.now();
        model.addAttribute("infoData", "Agendamentos de hoje");
        model.addAttribute("agendamentos", agendamentoService.findAgendamentosByData(dataHoje));
        model.addAttribute("clientes", clienteService.findAll());
        return "html_pages/agendamentos";
    }

    @PostMapping(value = "/adicionar")
    public String adicionar(Agendamento agendamento) {
        agendamento.setStatus("A confirmar");
        agendamentoService.save(agendamento);
        return "redirect:/agendamentos/listar";
    }

    @RequestMapping(value = "/reagendar/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Optional<Agendamento> reagendar(@PathVariable("id") Long id) {
        return agendamentoService.findOne(id);
    }

    @GetMapping("/filtrar")
    public String pesquisar(@RequestParam(name = "data") LocalDate data, Model model) {
        if (data == null) {
            return "redirect:/agendamentos/listar";
        }

        List<Agendamento> agendamentos = agendamentoService.findAgendamentosByData(data);

        if (agendamentos.size() > 0) {
            String infoData = "Agendamentos do dia: " + agendamentos.get(0).getDataFormatada();
            model.addAttribute("agendamentos", agendamentos);
            model.addAttribute("infoData", infoData);
            model.addAttribute("clientes", clienteService.findAll());
            return "html_pages/agendamentos";
        }
        model.addAttribute("alertConsulta", true);
        return "html_pages/agendamentos";
    }

    @PostMapping("/reagendar")
    public String reagendar(Agendamento agendamento) {
        agendamento.setStatus("A confirmar");
        agendamentoService.save(agendamento);
        return "redirect:/agendamentos/listar";
    }

    @RequestMapping(value = "/status/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Agendamento acaoStatus(@PathVariable("id") Long id, @RequestParam(name = "paramStatus") String paramStatus) {
        return agendamentoService.acaoStatusAgendamento(id, paramStatus);
    }

    @RequestMapping(value = "/pdfagendamentos", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> agendamentosReport() {
        LocalDate data = LocalDate.now();
        List<Agendamento> agendamentos = agendamentoService.findAgendamentosByData(data);

        ByteArrayInputStream byteArrayReport = GeradorDePdfService.agendamentoReport(agendamentos);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=Relatorio_agendamentos.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(byteArrayReport));
    }

}
