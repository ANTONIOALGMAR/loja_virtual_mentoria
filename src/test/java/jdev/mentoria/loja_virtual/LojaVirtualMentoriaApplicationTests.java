package jdev.mentoria.loja_virtual;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jdev.mentoria.loja_virtual.controller.AcessoController;
import jdev.mentoria.loja_virtual.model.Acesso;
import jdev.mentoria.loja_virtual.repository.AcessoRepository;
import jdev.mentoria.loja_virtual.service.AcessoService;

@SpringBootTest(classes = LojaVirtualMentoriaApplication.class)
public class LojaVirtualMentoriaApplicationTests {

	@Autowired
	private AcessoService acessoservice;
	
	@Autowired
	private AcessoRepository acessoRepository;
	
	@Autowired
	private AcessoController acessoController;
	
	@Test
	public void testCadastraAcesso() {
		
		Acesso acesso = new Acesso();
		
		acesso.setDescricao("ROLE_ADMIN");
		acessoController.salvarAcesso(acesso);
		
	}

}
