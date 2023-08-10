package com.escalation.minalba.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.escalation.minalba.entities.Unidade;
import com.escalation.minalba.services.UnidadeService;

@RestController
@RequestMapping(value="/minalba/unidades")
public class UnidadeResource {
	
	@Autowired
	private UnidadeService service;
	
	@GetMapping
	public ResponseEntity<List<Unidade>> findAll() {
		List<Unidade> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Unidade> findById(@PathVariable Long id) {
		Unidade obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Unidade> insert(@RequestBody Unidade obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Unidade> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "{id}")
	public ResponseEntity<Unidade> update(@PathVariable Long id, @RequestBody Unidade obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}

}
