package br.com.kanleitos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.kanleitos.models.PendenciaInternacao;
import br.com.kanleitos.models.Taxa;

public interface PendenciaInternacaoRepository extends JpaRepository<PendenciaInternacao, Long> {

	@Query("SELECT pi FROM PendenciaInternacao pi "
			+ "WHERE pi.registroInternacao.idRegistroInternacao = :idRegistroInternacao")
	List<PendenciaInternacao> findAllByIdRegistroInternacao(@Param("idRegistroInternacao") long idRegistroInternacao);

	@Query("SELECT NEW br.com.kanleitos.models.Taxa(p.tipoPendencia.nome, count(p.tipoPendencia.nome)) FROM PendenciaInternacao p "
			+ "WHERE p.dataConclusao is null and p.registroInternacao.statusRegistro = 'EM_ANDAMENTO' and "
			+ "p.registroInternacao.leito.enfermaria.ala.idAla = :idAla group by p.tipoPendencia.nome")
	List<Taxa<Long>> countAllPendenciasInternacaoAndamento(@Param("idAla") Long idAla);
}
