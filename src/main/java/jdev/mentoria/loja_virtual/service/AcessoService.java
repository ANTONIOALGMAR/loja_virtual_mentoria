package jdev.mentoria.loja_virtual.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdev.mentoria.loja_virtual.model.Acesso;
import jdev.mentoria.loja_virtual.repository.AcessoRepository;

@Service
public class AcessoService {
	
	@Autowired
	private AcessoRepository acessoRepository; 
	
	public Acesso save(Acesso acesso) {
		
		
		// QUALQUER TIPO DE VALIDACAO //
		
		
		return acessoRepository.save(acesso);
	}

}
