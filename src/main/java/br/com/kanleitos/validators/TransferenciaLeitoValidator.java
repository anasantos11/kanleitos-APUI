package br.com.kanleitos.validators;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.kanleitos.models.Leito;
import br.com.kanleitos.models.RegistroInternacao;
import br.com.kanleitos.models.TransferenciaLeito;
import br.com.kanleitos.models.enums.StatusRegistro;
import br.com.kanleitos.models.enums.TipoStatusLeito;

public class TransferenciaLeitoValidator implements Validator {

	private TransferenciaLeito transferencia;

	@Override
	public boolean supports(Class<?> clazz) {
		return TransferenciaLeito.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		transferencia = (TransferenciaLeito) target;

		RegistroInternacao registroInternacao = transferencia.getRegistroInternacao();
		Date data = transferencia.getData();
		Leito proximoLeito = transferencia.getProximoLeito();

		if (registroInternacao == null || registroInternacao.getIdRegistroInternacao() <= 0)
			errors.rejectValue("registroInternacao", "O registro de internação precisa ser informado.");
		if (registroInternacao != null && registroInternacao.getStatusRegistro() != StatusRegistro.EM_ANDAMENTO)
			errors.rejectValue("registroInternacao",
					"Paciente não está mais internado, transferência não pode ser realizada.");
		if (data == null)
			errors.rejectValue("data", "A data de transferência precisa ser informada.");
		if (proximoLeito == null)
			errors.rejectValue("proximoLeito", "O leito que receberá a transferência precisa ser informado.");
		if (proximoLeito != null && proximoLeito.getStatusLeito() != TipoStatusLeito.DESOCUPADO)
			errors.rejectValue("proximoLeito", "O leito que receberá a transferência não está desocupado.");

	}

}
