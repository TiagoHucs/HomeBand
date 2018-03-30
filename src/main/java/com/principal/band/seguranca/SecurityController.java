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
	
}
