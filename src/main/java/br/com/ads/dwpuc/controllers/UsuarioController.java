package br.com.ads.dwpuc.controllers;

import br.com.ads.dwpuc.models.Usuario;
import br.com.ads.dwpuc.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuarios-json")
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("webpages/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/usuarios")
    public String index(Model model) {
        model.addAttribute("users", usuarioService.findAll());
        return "html_pages/usuarios";
    }

    @PostMapping("/usuarios/adicionar")
    public String adicionar(Usuario usuario, Model model) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        usuarioService.save(usuario);
        model.addAttribute("users", usuarioService.findAll());
        return "html_pages/usuarios";
    }

//    @GetMapping("/usuarios/editar")
//    public String editar(@PathVariable("id") Long id, Model model){
//        model.addAttribute("users", usuarioService.findAll());
//        model.addAttribute("usuario", usuarioService.findOne(id));
//        return "html_pages/usuarios";
//    }

    @RequestMapping(value = "/usuarios/editar/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Optional<Usuario> editar(@PathVariable("id") Long id) {
        return usuarioService.findOne(id);
    }


    @PostMapping("/usuarios/editar")
    public String editar(Usuario usuario, Model model) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        usuarioService.save(usuario);
        model.addAttribute("users", usuarioService.findAll());
        return "redirect:/usuarios";
    }
}

