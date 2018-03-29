package br.com.kanleitos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kanleitos.models.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

	Paciente findByNumProntuario(long numProntuario);

	boolean existsByNumProntuario(long numProntuario);

	Paciente findByNomeMae(String nomeMae);

}
