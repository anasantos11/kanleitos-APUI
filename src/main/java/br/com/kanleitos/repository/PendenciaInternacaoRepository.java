package br.com.kanleitos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.kanleitos.models.PendenciaInternacao;

public interface PendenciaInternacaoRepository extends JpaRepository<PendenciaInternacao, Long>{

	@Query("SELECT pi FROM PendenciaInternacao pi "
			+ "WHERE pi.registroInternacao.idRegistroInternacao = :idRegistroInternacao")
	List<PendenciaInternacao> findAllByIdRegistroInternacao(@Param("idRegistroInternacao") long idRegistroInternacao);
}
