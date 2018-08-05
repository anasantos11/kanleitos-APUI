package br.com.kanleitos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.kanleitos.models.Ala;
import br.com.kanleitos.models.Enfermaria;

@Repository
public interface EnfermariaRepository extends JpaRepository<Enfermaria, Long> {

	List<Enfermaria> findAllByAlaAndInativa(Ala ala, boolean inativa);

	List<Enfermaria> findAllByInativa(boolean inativa);
	
	@Transactional
	@Modifying
	@Query("UPDATE Enfermaria e SET e.inativa = :inativa WHERE e.ala = :ala")
	void updateInativaByAla(@Param("inativa") boolean inativa, @Param("ala") Ala ala);
}
