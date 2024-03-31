package jdev.mentoria.loja_virtual.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jdev.mentoria.loja_virtual.ExceptionMentoriaJava;
import jdev.mentoria.loja_virtual.model.PessoaJuridica;
import jdev.mentoria.loja_virtual.repository.PessoaRepository;
import jdev.mentoria.loja_virtual.service.PessoaUserService;

import static org.springframework.util.ObjectUtils.isEmpty;

@RestController
public class PessoaController{
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private PessoaUserService pessoaUserService;
	

	@ResponseBody
	@PostMapping(value = "**/SalvarPj")
	public ResponseEntity<PessoaJuridica>salvarPj(@RequestBody @Valid PessoaJuridica pessoaJuridica) throws ExceptionMentoriaJava{
		
		
		if (pessoaJuridica == null) {
			throw new ExceptionMentoriaJava("Pessoa Juridica não pode ser NULL");
		}
		
		if (pessoaJuridica.getId() == null && pessoaRepository.existeCnpjCadastrado (pessoaJuridica.getCnpj()) != null) {
			throw new ExceptionMentoriaJava("Já existe CNPJ cadastrado com o número: " + pessoaJuridica.getCnpj());
		}
		
		pessoaJuridica = pessoaUserService.salvarPessoaJuridica(pessoaJuridica);
		
				
		
		return new ResponseEntity<PessoaJuridica>(pessoaJuridica, HttpStatus.OK);
	}
		

}