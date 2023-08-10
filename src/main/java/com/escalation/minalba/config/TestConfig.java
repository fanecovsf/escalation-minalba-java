package com.escalation.minalba.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.escalation.minalba.entities.Contato;
import com.escalation.minalba.entities.Unidade;
import com.escalation.minalba.repositories.ContatoRepository;
import com.escalation.minalba.repositories.UnidadeRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private UnidadeRepository unidadeRepository;
	
	@Autowired
	private ContatoRepository contatoRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Unidade unidade1 = new Unidade(null, "Barra Funda");
		Unidade unidade2 = new Unidade(null, "Campos do Jordão");
		
		unidadeRepository.saveAll(Arrays.asList(unidade1, unidade2));
		
		Contato c1 = new Contato(null, "Henrique", "Analista", 2, "ADM", "henrique@gmail.com", "996754376", unidade1);
		Contato c2 = new Contato(null, "Gustavo", "Supervisor", 3, "B", "gustavo@gmail.com", "993561207", unidade1);
		Contato c3 = new Contato(null, "Luciano", "Assistente", 1, "A", "luciano@gmail.com", "998756326", unidade2);
		
		contatoRepository.saveAll(Arrays.asList(c1, c2, c3));
			
	}
	
	

}
