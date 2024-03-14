package jdev.mentoria.loja_virtual;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

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
		
		assertEquals(true, acesso.getId() == null);
		
		acesso.setDescricao("ROLE_ADMIN");
		
		/* GRAVOU NO BANCO DE DADOS */
		acesso = acessoController.salvarAcesso(acesso).getBody();
		
		assertEquals(true, acesso.getId() > 0);
		
		
		
		/* VALIDAR DADOS SALVOS DA FORMA CORRETA */
		assertEquals("ROLE_ALUNO", acesso.getDescricao());
		
		
		
		/* TESTE DE CARREGAMENTO */
		Acesso acesso2 = acessoRepository.findById(acesso.getId()).get();
		
		assertEquals(acesso.getId(), acesso2.getId());
		
		
		
		/* TESTE DE DELETE */
		acessoRepository.deleteById(acesso2.getId());
		
		acessoRepository.flush(); /* RODA ESSE SQL DE DELETE NO BANCO DE DADOS */
		
		Acesso acesso3 = acessoRepository.findById(acesso2.getId()).orElse(null);
		
		assertEquals(true, acesso3 == null);
		
		
		/* TESTE DE QUERY */
		
		acesso = new Acesso();
		
		acesso.setDescricao("ROLE_ALUNO");
		
		acesso = acessoController.salvarAcesso(acesso).getBody();
		
		List<Acesso> acessos = acessoRepository.buscarAcessoDesc("ALUNO_".trim().toUpperCase());
		
		assertEquals(1, acessos.size());
		
		acessoRepository.deleteById(acesso.getId());
	}

}
