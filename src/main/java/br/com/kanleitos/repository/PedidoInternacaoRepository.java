package br.com.kanleitos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.com.kanleitos.models.Paciente;
import br.com.kanleitos.models.PedidoInternacao;
import br.com.kanleitos.util.StatusPedido;

@Repository
public interface PedidoInternacaoRepository extends JpaRepository<PedidoInternacao, Long>, QueryDslPredicateExecutor<PedidoInternacao> {
	PedidoInternacao findByPaciente(Paciente paciente);
	
	List<PedidoInternacao> findAllByStatusPedidoOrStatusPedido(StatusPedido pendente, StatusPedido atrasado);
	
	List<PedidoInternacao> findAllByPacienteAndStatusPedido(Paciente paciente, StatusPedido pendente);	
}
