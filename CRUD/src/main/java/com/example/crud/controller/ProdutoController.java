package com.example.crud.controller;

import com.example.crud.model.Produto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
@Controller
@RequestMapping("/produto")
public class ProdutoController {
    @GetMapping("/cadastrar")
    public String cadastrarProduto(Model model) {
        model.addAttribute("produto", new Produto());
        return "cadastrarProduto";
    }
}
