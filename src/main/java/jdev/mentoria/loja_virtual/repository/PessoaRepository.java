package jdev.mentoria.loja_virtual.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jdev.mentoria.loja_virtual.model.PessoaJuridica;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaJuridica, Long> {
	
	@Query(value = "SELECT pj FROM PessoaJuridica pj WHERE pj.cnpj = ?1")
    PessoaJuridica existeCnpjCadastrado(String cnpj);

}
