package com.principal.band.rest;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class RespostaRest {
	
	private int codigo;
	private String mensagem;
	
	public RespostaRest(int codigo, String mensagem) {
		super();
		this.codigo = codigo;
		this.mensagem = mensagem;
	}
	
	

}
