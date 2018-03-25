package com.principal.band.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.principal.band.rest.RespostaRest;

@RestController
public class UsuarioController {
	
	@Autowired
	UsuarioService userService; 


	@RequestMapping(method = RequestMethod.GET, value = "/getloggeduser")
	public Usuario getUser(){
		return userService.getUserLogado();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/trocarSenha")
	public RespostaRest trocarSenha(@RequestBody String senhaAntiga, String senhaNova1,String senhaNova2) {
		try {
			System.out.println("recebi para trocar: "+senhaAntiga);
			return new RespostaRest(0,"perfil alterado com sucesso!");
		} catch (Exception e) {
			return new RespostaRest(1,"erro ao alterar perfil. "+e);
		}
	}

	
}
