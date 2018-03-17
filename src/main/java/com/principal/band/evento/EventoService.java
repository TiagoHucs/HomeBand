package com.principal.band.evento;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.principal.band.perfil.Perfil;

@Service
public class EventoService {
	
	@Autowired
	EventoRepository eventoRepository;
	
	public void SalvarEvento(Perfil perfil,String acao){
		Evento evento = new Evento();
		evento.setPerfil(perfil);
		Date novaData = new Date(System.currentTimeMillis());
		evento.setData(novaData);
		evento.setAcao(acao);
		eventoRepository.save(evento);
	}

	public ArrayList<Evento> obterEventos() {
		return (ArrayList<Evento>) eventoRepository.findAll();
	}

}
