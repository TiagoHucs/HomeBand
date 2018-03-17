package com.principal.band;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
	
	
    @RequestMapping("/")
    public String home(){
    		return "index";

    }
    
    @RequestMapping("/login")
    public String login(HttpSession session){
    	session.setAttribute("mensagem", "");
    	return "login";
    }
    
    @RequestMapping("/cadastro")
    public String cadastro(HttpSession session){
    	session.setAttribute("mensagemLogin", "");
    	return "cadastro";
    }

}
