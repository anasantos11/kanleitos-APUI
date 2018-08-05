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
import br.com.kanleitos.models.Taxa;
import br.com.kanleitos.models.enums.TipoStatusLeito;

@Repository
public interface LeitoRepository extends JpaRepository<Leito, Long> {

	List<Leito> findByEnfermariaAndStatusLeito(Enfermaria enfermaria, TipoStatusLeito desocupado);

	@Query("SELECT l FROM Leito l WHERE l.enfermaria.idEnfermaria = :idEnfermaria")
	List<Leito> findAllByEnfermaria(@Param("idEnfermaria") Long idEnfermaria);
	 
	@Query("SELECT l FROM Leito l WHERE l.enfermaria.idEnfermaria = :idEnfermaria AND l.statusLeito = 'DESOCUPADO'")
	List<Leito> findAllByEnfermariaAndDesocupados(@Param("idEnfermaria") Long idEnfermaria);
	
	@Query("SELECT l FROM Leito l WHERE l.statusLeito != 'INATIVO'")
	List<Leito> findAllByStatusLeitoNotInativo();

	@Query("SELECT l FROM Leito l WHERE l.enfermaria.ala.idAla = :idAla")
	List<Leito> findAllByAla(@Param("idAla") Long idAla);

	@Query("SELECT NEW br.com.kanleitos.models.Taxa(l.statusLeito, count(l.statusLeito)) FROM Leito l WHERE l.enfermaria.ala.idAla = :idAla group by l.statusLeito")
	List<Taxa<Long>> countAllStatusLeitoByAla(@Param("idAla") Long idAla);

	@Transactional
	@Modifying
	@Query("UPDATE Leito l SET l.statusLeito = :status WHERE l.enfermaria = :enfermaria")
	void updateStatusByEnfermaria(@Param("status") TipoStatusLeito status, @Param("enfermaria") Enfermaria enfermaria);
}
