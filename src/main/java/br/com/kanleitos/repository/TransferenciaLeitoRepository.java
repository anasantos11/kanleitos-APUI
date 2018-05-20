package br.com.kanleitos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kanleitos.models.RegistroInternacao;
import br.com.kanleitos.models.TransferenciaLeito;

@Repository
public interface TransferenciaLeitoRepository extends JpaRepository<TransferenciaLeito, Long> {
	
	List<TransferenciaLeito> findAllByRegistroInternacao(RegistroInternacao registro);
}
