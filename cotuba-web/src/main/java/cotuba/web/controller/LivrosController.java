package cotuba.web.controller;

import cotuba.web.application.CadastroDeLivros;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.swing.*;

@Controller
public class LivrosController {

	private final CadastroDeLivros livros;

	public LivrosController(CadastroDeLivros livros) {
		this.livros = livros;
	}

	@GetMapping("/")
	public String lista(Model model) {
		model.addAttribute("livros", livros.listagem());
		return "livros/index";
	}

	@GetMapping("/livros/{id}")
	public String lista(@PathVariable("id") Long id, Model model) {
		model.addAttribute("livro", livros.detalha(id));
		return "livros/detalhe";
	}

}
