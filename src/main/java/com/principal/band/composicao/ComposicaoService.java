package com.principal.band.composicao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.principal.band.evento.EventoService;
import com.principal.band.perfil.Perfil;
import com.principal.band.perfil.PerfilService;

@Service
public class ComposicaoService {
	
	@Autowired
	ComposicaoRepository repository;
	
	@Autowired
	EventoService eventoService;
	
	@Autowired
	PerfilService perfilservice;
	
	public ArrayList<Composicao> getComposicoes() {
		return (ArrayList<Composicao>) repository.findAll();
	}
	
	public ArrayList<Composicao> getMinhasComposicoes(Long id) {
        return (ArrayList<Composicao>) repository.findByAutor(id);
	}

	public void salvarMinhaComposicao(Long id, Composicao c) {
		c.setAutor(id);
		Perfil perfil = perfilservice.getPerfil(id);
		String acao = perfil.getNome()+" criou uma nova composição ("+c.getNome()+")";
		eventoService.SalvarEvento(perfil, acao);
		repository.save(c);
	}

	public Composicao getComposicao(Long id) {
		return repository.findOne(id);
	}

}
