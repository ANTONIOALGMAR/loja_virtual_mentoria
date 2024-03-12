package jdev.mentoria.loja_virtual.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import jdev.mentoria.loja_virtual.model.Acesso;
import jdev.mentoria.loja_virtual.service.AcessoService;

@Controller
public class AcessoController {
	
	@Autowired
	private AcessoService acessoService;
	
	
	public Acesso salvarAcesso(Acesso acesso) {
		
		
		return acessoService.save(acesso);
		
	}

}
