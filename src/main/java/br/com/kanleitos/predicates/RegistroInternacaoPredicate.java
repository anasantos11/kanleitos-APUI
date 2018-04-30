package br.com.kanleitos.predicates;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;

import br.com.kanleitos.models.Filtro;
import br.com.kanleitos.models.QRegistroInternacao;
import br.com.kanleitos.models.enums.StatusRegistro;

public class RegistroInternacaoPredicate {

	@SuppressWarnings("deprecation")
	public static Predicate filtroPesquisa(Filtro filtros) {
		List<Predicate> listPredicates = new ArrayList<>();

		QRegistroInternacao registro = QRegistroInternacao.registroInternacao;
		
		if(filtros.getStatus() != null && !filtros.getStatus().isEmpty()) {
			listPredicates.add(registro.statusRegistro.eq(StatusRegistro.fromName(filtros.getStatus())));		
		}

		if (filtros.getIdAla() > 0) {
			listPredicates.add(registro.pedidoInternacao.ala.idAla.eq(filtros.getIdAla()));
		}
		
		if (filtros.getIdEnfermaria() > 0) {
			listPredicates.add(registro.leito.enfermaria.idEnfermaria.eq(filtros.getIdEnfermaria()));
		}
		
		if (filtros.getIdEnfermaria() > 0) {
			listPredicates.add(registro.leito.idLeito.eq(filtros.getIdEnfermaria()));
		}

		if (!filtros.getMedicoResponsavel().isEmpty() && filtros.getMedicoResponsavel() != null) {
			listPredicates.add(registro.pedidoInternacao.medicoResponsavel
					.like(Expressions.asString("%").concat(filtros.getMedicoResponsavel()).concat("%")));
		}

		if (!filtros.getResidenteResponsavel().isEmpty() && filtros.getResidenteResponsavel() != null) {
			listPredicates.add(registro.pedidoInternacao.residenteResponsavel
					.like(Expressions.asString("%").concat(filtros.getResidenteResponsavel()).concat("%")));
		}

		if (filtros.getIdIsolamento() > 0) {
			listPredicates.add(registro.pedidoInternacao.isolamento.idIsolamento.eq(filtros.getIdIsolamento()));
		}

		if (!filtros.getNomePaciente().isEmpty() && filtros.getNomePaciente() != null) {
			listPredicates.add(registro.pedidoInternacao.paciente.nomePaciente
					.like(Expressions.asString("%").concat(filtros.getNomePaciente()).concat("%")));
		}

		if (filtros.getNumProntuario() > 0) {
			listPredicates.add(registro.pedidoInternacao.paciente.numProntuario.eq(filtros.getNumProntuario()));
		}

		if (filtros.getDataAdmissao() != null) {
			Date startDate = new Date(filtros.getDataAdmissao().getTime());
			startDate.setHours(0);
			startDate.setMinutes(0);
			startDate.setSeconds(0);
			
			Date endDate = new Date(filtros.getDataAdmissao().getTime());
			endDate.setHours(23);
			endDate.setMinutes(59);
			endDate.setSeconds(59);
			listPredicates.add(registro.pedidoInternacao.dataAdmissao.between(startDate, endDate));
		}

		BooleanBuilder bb = new BooleanBuilder();
		return bb.orAllOf(listPredicates.toArray(new Predicate[listPredicates.size()]));

	}
}
