package com.principal.band.composicao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.principal.band.usuario.UsuarioService;

@RestController
public class ComposicaoController {
	
	@Autowired
	ComposicaoService composicaoService;
	
	@Autowired
	UsuarioService userService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/obterComposicoes")
	public ArrayList<Composicao> obterComposicoes(){
		return composicaoService.getComposicoes();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/obterMinhasComposicoes")
	public ArrayList<Composicao> obterMinhasComposicoes(){
		return composicaoService.getMinhasComposicoes(userService.getUserLogado().getId());
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/salvarMinhaComposicao")
	public void salvarMinhaComposicao(@RequestBody Composicao c) {
		System.out.println("recebi: "+c.getNome()+" - "+c.getEstilo());
		composicaoService.salvarMinhaComposicao(userService.getUserLogado().getId(),c);
	}	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/composicao/{id}")
	public Composicao obterComposicao(@PathVariable Long id) {
		System.out.println(composicaoService.getComposicao(id).getNome());
		return composicaoService.getComposicao(id);
	}

}
