package com.principal.band.usuario;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.principal.band.evento.EventoService;
import com.principal.band.perfil.Perfil;
import com.principal.band.perfil.PerfilService;
import com.principal.band.rest.RespostaRest;
import com.principal.band.seguranca.CadastroVO;

@RestController
public class UsuarioController {
	
	@Autowired
	UsuarioService userService; 

	@Autowired
	PerfilService perfilService;
	
	@Autowired
	EventoService eventoService;

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
	
	@RequestMapping(method = RequestMethod.POST, value = "cadastrar")
	public RespostaRest cadastrar(@RequestBody CadastroVO cadastroForm) {
		
		cadastroForm.getEmail();
		
		if (!cadastroForm.getSenha1().equals(cadastroForm.getSenha2()) ) {
			return new RespostaRest(1,"As senhas não são iguais");
		} else if (cadastroForm.getSenha1().length() < 6) {
			return new RespostaRest(1,"A senha deve conter pelo menos 6 caracteres");
		} else if (userService.usuarioJaEsxiste(cadastroForm.getEmail())) {
			return new RespostaRest(1,"E-mail já cadastrado");
		} else {
			Usuario novoUsuario = new Usuario();
			Perfil novoPerfil = new Perfil();
			novoPerfil.setNome(cadastroForm.getNome());
			novoPerfil.setLocalidade(cadastroForm.getLocalidade());
			novoPerfil.setFoto("img/img_avatar1.png");
			novoPerfil.setInstrumento(cadastroForm.getInstrumento());
			perfilService.salvar(novoPerfil);
			
			novoUsuario.setEmail(cadastroForm.getEmail());
			novoUsuario.setSenha(cadastroForm.getSenha1());
			novoUsuario.setPerfil(novoPerfil);
			userService.salvar(novoUsuario);

			eventoService.SalvarEvento(novoPerfil, novoPerfil.getNome()+" se cadastrou no Home Band");
			
			return new RespostaRest(0,"Usuario cadastrado com sucesso");
		}

	}


	
}
