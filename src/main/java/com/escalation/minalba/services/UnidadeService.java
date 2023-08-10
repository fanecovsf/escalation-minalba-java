package com.escalation.minalba.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escalation.minalba.entities.Unidade;
import com.escalation.minalba.repositories.UnidadeRepository;

@Service
public class UnidadeService {
	
	@Autowired
	private UnidadeRepository repository;
	
	public List<Unidade> findAll() {
		return repository.findAll();
	}
	
	public Unidade findById(Long id) {
		Optional<Unidade> obj = repository.findById(id);
		return obj.get();
	}

}
