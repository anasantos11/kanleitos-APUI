package br.com.kanleitos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kanleitos.models.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	List<Funcionario> findAllByInativo(boolean inativo);
	List<Funcionario> findAllByCrmNotNull();
	List<Funcionario> findAllByCrmNull();

}
