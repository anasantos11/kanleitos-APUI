package br.com.kanleitos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kanleitos.models.TipoPendencia;

@Repository
public interface TipoPendenciaRepository extends JpaRepository<TipoPendencia, Integer> {

	List<TipoPendencia> findAllByInativo(boolean inativo);

}
