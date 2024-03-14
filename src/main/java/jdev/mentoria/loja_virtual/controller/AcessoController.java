package jdev.mentoria.loja_virtual.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jdev.mentoria.loja_virtual.model.Acesso;
import jdev.mentoria.loja_virtual.repository.AcessoRepository;
import jdev.mentoria.loja_virtual.service.AcessoService;

@Controller
@RestController
public class AcessoController {
	
	@Autowired
	private AcessoService acessoService;
	
	@Autowired
	private AcessoRepository acessoRepository;
	
	@ResponseBody /* PODER DAR UM RETORNO DA API */
	@PostMapping(value = "**/salvarAcesso")  /* MAPEANDO A URL PARA RECEBER UM JSON*/
	public ResponseEntity <Acesso> salvarAcesso(@RequestBody Acesso acesso) {   /* RECEBE O JSON E CONVERTE PARA OBJETO */
		
		
		Acesso acessoSalvo = acessoService.save(acesso);
		
		return new ResponseEntity<Acesso>(acessoSalvo, HttpStatus.OK);
		
	}

	
	@ResponseBody /* PODER DAR UM RETORNO DA API */
	@PostMapping(value = "**/deleteAcesso")  /* MAPEANDO A URL PARA RECEBER UM JSON*/
	public ResponseEntity <?> deleteAcesso(@RequestBody Acesso acesso) {   /* RECEBE O JSON E CONVERTE PARA OBJETO */
		
		
		acessoRepository.deleteById(acesso.getId());
		
		return new ResponseEntity("Acesso Removido", HttpStatus.OK);
		
	}
}
