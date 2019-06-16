package br.com.ads.dwpuc.controllers;

import br.com.ads.dwpuc.models.Usuario;
import br.com.ads.dwpuc.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/listar")
    public String listar(Model model) {
        model.addAttribute("users", usuarioService.findAll());
        return "html_pages/usuarios";
    }

    @PostMapping("/adicionar")
    public String adicionar(Usuario usuario, Model model) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        usuarioService.save(usuario);
        return "redirect:/usuarios/listar";
    }

    @RequestMapping(value = "/editar/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Optional<Usuario> editar(@PathVariable("id") Long id) {
        return usuarioService.findOne(id);
    }

    @PostMapping("/editar")
    public String editar(Usuario usuario, Model model) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        usuarioService.save(usuario);
        return "redirect:/usuarios/listar";
    }
}

