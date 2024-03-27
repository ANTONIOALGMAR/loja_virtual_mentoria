package jdev.mentoria.loja_virtual.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jdev.mentoria.loja_virtual.model.PessoaJuridica;

@Repository
public interface PessoaRepository extends CrudRepository<PessoaJuridica, Long> {
	

}
