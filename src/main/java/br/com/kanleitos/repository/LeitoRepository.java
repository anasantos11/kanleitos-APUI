package br.com.kanleitos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.kanleitos.models.Enfermaria;
import br.com.kanleitos.models.Leito;
import br.com.kanleitos.models.enums.TipoStatusLeito;

@Repository
public interface LeitoRepository extends JpaRepository<Leito, Long> {

	List<Leito> findByEnfermariaAndStatusLeito(Enfermaria enfermaria, TipoStatusLeito desocupado);

	@Query("SELECT l FROM Leito l WHERE l.statusLeito != 'INATIVO'")
	List<Leito> findAllByStatusLeitoNotInativo();

	@Transactional
	@Modifying
	@Query("UPDATE Leito l SET l.statusLeito = :status WHERE l.enfermaria = :enfermaria")
	void updateStatusByEnfermaria(@Param("status") TipoStatusLeito status, @Param("enfermaria") Enfermaria enfermaria);
}
