package br.com.kanleitos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kanleitos.models.Ala;
import br.com.kanleitos.models.Enfermaria;

@Repository
public interface EnfermariaRepository extends JpaRepository<Enfermaria, Long> {

	List<Enfermaria> findAllByAla(Ala ala);

	List<Enfermaria> findAllByInativa(boolean inativa);
}
