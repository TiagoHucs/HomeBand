package com.principal.band.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {
	
	@Autowired
	UsuarioService userService; 


	@RequestMapping(method = RequestMethod.GET, value = "/getloggeduser")
	public Usuario getUser(){
		return userService.getUserLogado();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/trocarSenha")
	public void trocarSenha(String senhaAnterior, String novaSenha) {
		userService.trocarSenha(senhaAnterior, novaSenha);
		
	}
	

	
}
