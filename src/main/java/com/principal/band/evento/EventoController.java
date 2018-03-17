package com.principal.band.evento;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventoController {
	
	@Autowired
	EventoService eventoService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/geteventos")
	public ArrayList<Evento> obterEventos(){
		return eventoService.obterEventos();
	}

}
