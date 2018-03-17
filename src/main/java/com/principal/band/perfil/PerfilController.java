package com.principal.band.perfil;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.principal.band.usuario.Usuario;
import com.principal.band.usuario.UsuarioService;

@RestController
public class PerfilController {
	
	@Autowired
	PerfilService perfilService;
	
	@Autowired
	UsuarioService userService; 
	
	@RequestMapping(method = RequestMethod.GET, value = "/getloggedperfil")
	public Perfil obterPerfilUsuario(){
		Usuario usuario = userService.getUserLogado();
		return perfilService.getPerfil(usuario.getPerfil().getId());
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/obterTodosPerfis")
	public ArrayList<Perfil> obterTodosPerfis(){
		return perfilService.getTodosPerfis();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/atualizaPerfilUsuario")
	public void atualizaPerfilUsuario(@RequestBody Perfil perfil) {
		System.out.println("recebi: "+perfil.getId()+" - "+perfil.getNome());
		Usuario usuario = userService.getUserLogado();
		perfil.setId(usuario.getId());
	
		perfilService.atualizaPerfilUsuario(perfil);
	}

}
