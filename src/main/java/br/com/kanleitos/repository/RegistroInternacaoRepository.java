package br.com.kanleitos.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.kanleitos.models.Paciente;
import br.com.kanleitos.models.PedidoInternacao;
import br.com.kanleitos.models.RegistroInternacao;
import br.com.kanleitos.models.enums.StatusRegistro;

@Repository
public interface RegistroInternacaoRepository
		extends JpaRepository<RegistroInternacao, Long>, QueryDslPredicateExecutor<RegistroInternacao> {

	List<RegistroInternacao> findAllByStatusRegistro(StatusRegistro statusRegistro);

	@Query("SELECT ri.pedidoInternacao.paciente FROM RegistroInternacao ri "
			+ "WHERE ri.statusRegistro = 'EM_ANDAMENTO' AND ri.leito.enfermaria.idEnfermaria = :idEnfermaria")
	List<Paciente> findAllPacientesbyEnfermaria(@Param("idEnfermaria") long idEnfermaria);

	List<PedidoInternacao> findAllByPedidoInternacaoAndStatusRegistro(PedidoInternacao pedidoInternacao,
			StatusRegistro statusRegistro);

	@Query("SELECT ri.pedidoInternacao.isolamento.nome FROM RegistroInternacao ri "
			+ "WHERE ri.statusRegistro = 'EM_ANDAMENTO' AND ri.leito.enfermaria.idEnfermaria = :idEnfermaria")
	Set<String> findAllIsolamentoNomebyEnfermaria(@Param("idEnfermaria") Long idEnfermaria);

}
