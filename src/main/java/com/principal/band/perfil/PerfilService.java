package com.principal.band.perfil;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfilService {
	
	@Autowired
	private PerfilRepository repository;
	
	public void salvar(Perfil p) {
		repository.save(p);
	}

	public Perfil getPerfil(Long id) {
		return repository.findOne(id);
	}

	public void atualizaPerfilUsuario(Perfil perfil) {
		repository.saveAndFlush(perfil);
		
	}

	public ArrayList<Perfil> getTodosPerfis() {
		return (ArrayList<Perfil>) repository.findAll();
	}

}
