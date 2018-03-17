package com.principal.band.composicao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ComposicaoRepository extends JpaRepository<Composicao, Long>{
	
	ArrayList<Composicao> findByAutor(Long autor);

}
