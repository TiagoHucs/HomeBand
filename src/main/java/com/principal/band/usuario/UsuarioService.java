package com.principal.band.usuario;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.principal.band.rest.RespostaRest;

@Service
public class UsuarioService {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	UsuarioRepository repository;
	
	public Usuario getUserLogado(){
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		usuario.setSenha(null);
		return usuario;
	}

	public Usuario validaUsuario(Usuario usuarioEmValidacao) {
		List<Usuario> usuariosCadastrados = repository.findAll();

		for (Usuario usuarioCadastrado : usuariosCadastrados) {
			if(usuarioCadastrado.getEmail().equals(usuarioEmValidacao.getEmail())){
				if(usuarioCadastrado.getSenha().equals(usuarioEmValidacao.getSenha())){
					return usuarioCadastrado;
				}
			}
		}
		return null;
	}
	
	public Boolean usuarioJaEsxiste(String email){
		List<Usuario> usuariosCadastrados = repository.findAll();
		for (Usuario usuario : usuariosCadastrados) {
			if(email.equals(usuario.getEmail())){
				return true;
			}
		}
		return false;
	}

	public Usuario salvar(Usuario usuario) {
		repository.save(usuario);
		System.out.println("novo user id: "+usuario.getId());
		return usuario;
	}

	public RespostaRest trocarSenha(String senhaAnterior, String novaSenha) {
		Usuario usuario = this.getUserLogado();
		usuario = repository.getOne(usuario.getId());
		if(usuario.getSenha().equals(senhaAnterior)){
			usuario.setSenha(novaSenha);
			return new RespostaRest(0,"Senha alterada com sucesso!"); 
			}else{
			return new RespostaRest(1,"Senha atual incorreta!");
		}
		
	}

}
