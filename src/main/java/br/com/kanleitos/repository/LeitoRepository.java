package br.com.kanleitos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kanleitos.models.Enfermaria;
import br.com.kanleitos.models.Leito;
import br.com.kanleitos.util.TipoStatusLeito;

@Repository
public interface LeitoRepository extends JpaRepository<Leito, Long> {
	List<Leito> findByEnfermariaAndStatusLeito(Enfermaria enfermaria, TipoStatusLeito desocupado);
}
