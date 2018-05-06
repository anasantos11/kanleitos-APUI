package br.com.kanleitos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kanleitos.models.TipoExame;

@Repository
public interface TipoExameRepository extends JpaRepository<TipoExame, Integer> {

	List<TipoExame> findAllByInativo(boolean inativo);

}
