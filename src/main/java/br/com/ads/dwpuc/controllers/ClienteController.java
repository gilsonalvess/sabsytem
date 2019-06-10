package br.com.ads.dwpuc.controllers;

import br.com.ads.dwpuc.models.Cliente;
import br.com.ads.dwpuc.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/listar")
    public String listar(Model model) {
        model.addAttribute("clientes", clienteService.findAll());
        return "html_pages/clientes";
    }

    @PostMapping("/adicionar")
    public String adicionar(Cliente cliente, Model model) {
        clienteService.save(cliente);
        model.addAttribute("clientes", clienteService.findAll());
        return "html_pages/clientes";
    }

    @RequestMapping(value = "/editar/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Optional<Cliente> editar(@PathVariable("id") Long id) {
        return clienteService.findOne(id);
    }

    @PostMapping("/editar")
    public String editar(Cliente cliente, Model model) {
        clienteService.save(cliente);
        model.addAttribute("clientes", clienteService.findAll());
        return "redirect:/clientes/listar";
    }

    @GetMapping("/remover/{id}")
    public String remover(@PathVariable("id") Long id) {
        clienteService.remove(id);
        return "redirect:/clientes/listar";
    }

}
