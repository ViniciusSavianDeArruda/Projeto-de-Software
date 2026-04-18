package com.example.crud.controller;

import com.example.crud.model.Categoria;
import com.example.crud.repository.CategoriaRepository;
import com.example.crud.service.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaRepository categoriaRepository, CategoriaService categoriaService) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaService = categoriaService;

    }

    @GetMapping("/formulario")
    public String form(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categoria-form";
    }

    @PostMapping("/salvar")
    public String salvar(Categoria categoria) {
        categoriaService.salvar(categoria);
        return "redirect:/categoria/listar";
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "categoria-lista";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Categoria categoria = categoriaRepository.findById(id).orElseThrow();
        model.addAttribute("categoria", categoria);
        return "categoria-form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        categoriaRepository.deleteById(id);
        return "redirect:/categoria/listar";
    }
}