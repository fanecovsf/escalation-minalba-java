package com.escalation.minalba.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.escalation.minalba.entities.Contato;
import com.escalation.minalba.exceptions.DatabaseException;
import com.escalation.minalba.exceptions.ResourceNotFoundException;
import com.escalation.minalba.repositories.ContatoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ContatoService {
	
	@Autowired
	private ContatoRepository repository;
	
	public List<Contato> findAll() {
		return repository.findAll();
	}
	
	public Contato findById(Long id) {
		Optional<Contato> obj = repository.findById(id);
		return obj.get();
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
	
	public Contato insert(Contato obj) {
		return repository.save(obj);
	}
	
	public Contato update(Long id, Contato obj) {
		try {
			Contato contatoOld = repository.getReferenceById(id);
			updateData(contatoOld, obj);
			return repository.save(contatoOld);
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(e);
		}
	}
	
	public void updateData(Contato old, Contato update) {
		old.setCargo(update.getCargo());
		old.setEmail(update.getEmail());
		old.setNivel(update.getNivel());
		old.setNome(update.getNome());
		old.setTelefone(update.getTelefone());
		old.setTurno(update.getTurno());
		old.setUnidade(update.getUnidade());
	}

}
