package com.escalation.minalba.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.escalation.minalba.entities.Unidade;
import com.escalation.minalba.exceptions.DatabaseException;
import com.escalation.minalba.exceptions.ResourceNotFoundException;
import com.escalation.minalba.repositories.UnidadeRepository;

import jakarta.persistence.EntityNotFoundException;

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
	
	public Unidade insert(Unidade obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(e);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Unidade update(Long id, Unidade obj) {
		try {
			Unidade unidadeOld = repository.getReferenceById(id);
			updateData(unidadeOld, obj);
			return repository.save(unidadeOld);
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(e);
		}
	}
	
	public void updateData(Unidade unidadeOld, Unidade unidadeNew) {
		unidadeOld.setNome(unidadeNew.getNome());
	}

}
