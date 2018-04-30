package br.com.kanleitos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kanleitos.models.Ala;

@Repository
public interface AlaRepository extends JpaRepository<Ala, Long> {

	List<Ala> findAllByInativa(boolean inativa);

}
