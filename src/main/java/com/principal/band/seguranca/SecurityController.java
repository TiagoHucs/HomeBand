package com.principal.band.seguranca;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.principal.band.evento.EventoService;
import com.principal.band.perfil.Perfil;
import com.principal.band.perfil.PerfilService;
import com.principal.band.usuario.Usuario;
import com.principal.band.usuario.UsuarioService;

@Controller
public class SecurityController {

	@Autowired
	UsuarioService userService;

	@Autowired
	PerfilService perfilService;
	
	@Autowired
	EventoService eventoService;

	@RequestMapping("efetuaLogin")
	public String efetuaLogin(Usuario usuario, HttpSession session) {
		Usuario usuarioCadastrado = userService.validaUsuario(usuario);
		if (usuarioCadastrado != null) {
			usuario = usuarioCadastrado;
			usuario.setSenha(null);
			session.setAttribute("usuarioLogado", usuario);
			session.setAttribute("mensagemLogin", "");
			return "redirect:/";
		} else {
			session.setAttribute("mensagemLogin", "Usuario ou senha inválidos");
			return "redirect:login";
		}

	}

	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login";
	}
	
	@RequestMapping("cadastrar")
	public String cadastrar(CadastroVO cadastroForm, HttpSession session) {
		if (!cadastroForm.getSenha1().equals(cadastroForm.getSenha2()) ) {
			session.setAttribute("mensagem", "As senhas não são iguais");
			return "cadastro";
		} else if (cadastroForm.getSenha1().length() < 6) {
			session.setAttribute("mensagem", "A senha deve conter pelo menos 6 caracteres");
			return "cadastro";
		} else if (userService.usuarioJaEsxiste(cadastroForm.getEmail())) {
			session.setAttribute("mensagem", "E-mail já cadastrado");
			return "cadastro";
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
			
			session.setAttribute("mensagem", "Usuario cadastrado com sucesso");
			return "cadastro";
		}

	}


}
