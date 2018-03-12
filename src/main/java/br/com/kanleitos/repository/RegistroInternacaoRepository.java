package br.com.kanleitos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kanleitos.models.PedidoInternacao;
import br.com.kanleitos.models.RegistroInternacao;
import br.com.kanleitos.util.StatusRegistro;

@Repository
public interface RegistroInternacaoRepository extends JpaRepository<RegistroInternacao, Long> {

	List<RegistroInternacao> findAllByStatusRegistro(StatusRegistro statusRegistro);

	List<RegistroInternacao> findByPedidoInternacaoAndStatusRegistro(PedidoInternacao pedidoInternacao,
			StatusRegistro statusRegistro);

}
