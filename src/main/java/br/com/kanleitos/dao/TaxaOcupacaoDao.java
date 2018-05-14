package br.com.kanleitos.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import br.com.kanleitos.models.Taxa;
import br.com.kanleitos.models.enums.MesAno;

@Component
public class TaxaOcupacaoDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Taxa<Double>> getTaxaTempoMedioAno(int ano, Long idLeito) {
		ArrayList<Taxa<Double>> taxas = new ArrayList<Taxa<Double>>();
		String query = "SELECT MONTH(data_alta), AVG(data_alta - data_internacao) FROM registro_internacao WHERE "
				+ "id_leito = ? AND YEAR(data_internacao) = ? AND status_registro = 'ALTA' GROUP BY MONTH(data_alta)";

		jdbcTemplate.query(query, new Object[] { idLeito, ano }, resultSet -> {
			int mesInt = resultSet.getInt(1);
			MesAno mes = Arrays.asList(MesAno.values()).stream().filter(m -> m.mes == mesInt).findFirst().get();
			double quantidade = resultSet.getDouble(2) / 3600;
			taxas.add(new Taxa<Double>(mes.toString(), quantidade));
		});

		return taxas;
	}

}
