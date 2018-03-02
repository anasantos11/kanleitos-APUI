package br.com.kanleitos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kanleitos.models.Diagnostico;


@Repository
public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Long> {

	
}
