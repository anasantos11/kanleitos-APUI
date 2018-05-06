package br.com.kanleitos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.kanleitos.models.ObservacaoInternacao;

public interface ObservacaoInternacaoRepository extends JpaRepository<ObservacaoInternacao, Long>{

	@Query("SELECT oi FROM ObservacaoInternacao oi "
			+ "WHERE oi.registroInternacao.idRegistroInternacao = :idRegistroInternacao")
	List<ObservacaoInternacao> findAllByIdRegistroInternacao(@Param("idRegistroInternacao") long idRegistroInternacao);
}
