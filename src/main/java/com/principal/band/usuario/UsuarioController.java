package com.principal.band.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.principal.band.rest.RespostaRest;
import com.principal.band.seguranca.CadastroVO;

@RestController
public class UsuarioController {
	
	@Autowired
	UsuarioService userService; 


	@RequestMapping(method = RequestMethod.GET, value = "/getloggeduser")
	public Usuario getUser(){
		return userService.getUserLogado();
	}

	private boolean validaFormTrocaSenha(String s1, String s2, String s3){
		if(s1==null || s2==null || s3==null){
			return false;
		}else if(!s2.equalsIgnoreCase(s3)){
			return false;
		}else if(s2.length()<6){
			return false;
		}else{
			return true;
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/trocarSenha")
	public RespostaRest trocarSenha(@RequestBody CadastroVO form) {
		
		boolean formularioIsValido = validaFormTrocaSenha(form.getSenha(),form.getSenha1(), form.getSenha2());
		
		if(formularioIsValido){
			return userService.trocarSenha(form.getSenha(), form.getSenha1());
		}else{
			return new RespostaRest(1,"Formulario incorreto ou não respeita as regras de senha");			
		}

	}

	
}
