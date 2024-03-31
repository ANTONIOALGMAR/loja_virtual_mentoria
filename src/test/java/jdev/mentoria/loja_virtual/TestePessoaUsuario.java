package jdev.mentoria.loja_virtual;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import jdev.mentoria.loja_virtual.controller.PessoaController;
import jdev.mentoria.loja_virtual.model.PessoaFisica;
import jdev.mentoria.loja_virtual.model.PessoaJuridica;
import jdev.mentoria.loja_virtual.repository.PessoaRepository;
import jdev.mentoria.loja_virtual.service.PessoaUserService;
import junit.framework.TestCase;

@Profile("test")
@SpringBootTest(classes = LojaVirtualMentoriaApplication.class)
public class TestePessoaUsuario extends TestCase {
	
	
	@Autowired
	private PessoaController pessoaController;
	
	@Test
	public void testCadPessoaFisica() throws ExceptionMentoriaJava {
		
		PessoaJuridica pessoaJuridica = new PessoaJuridica();
		
		pessoaJuridica.setCnpj("" + Calendar.getInstance().getTimeInMillis());
		pessoaJuridica.setNome("alex fernando");
		pessoaJuridica.setEmail("alex@gmail.com");
		pessoaJuridica.setTelefone("45959595959");
		pessoaJuridica.setInscEstadual("65656556565655656565");
		pessoaJuridica.setInscMunicipal("55555555555555555555");
		pessoaJuridica.setNomeFantasia("7777777777777777777");
		pessoaJuridica.setRazaoSocial("zxzxzxzxzxzxzxzzxxzx");
		
		pessoaController.salvarPj(pessoaJuridica);
	
		
		/*
		
		PessoaFisica pessoaFisica = new PessoaFisica();
		
		pessoaFisica.setCpf("18118118100");
		pessoaFisica.setNome("Antonio");
		pessoaFisica.setEmail("antonio@gmail.com");
		pessoaFisica.setTelefone("999777555");
		
		pessoaFisica.setEmpresa(pessoaFisica);*/
		
	}

}
