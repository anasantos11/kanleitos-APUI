package br.com.kanleitos.validators;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.kanleitos.models.PendenciaInternacao;
import br.com.kanleitos.models.RegistroInternacao;
import br.com.kanleitos.models.TipoPendencia;

public class PendenciaInternacaoValidator implements Validator {

	private PendenciaInternacao pendenciaInternacao;

	@Override
	public boolean supports(Class<?> clazz) {
		return PendenciaInternacao.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		pendenciaInternacao = (PendenciaInternacao) target;

		Date dataInicio = pendenciaInternacao.getDataInicio();
		Date previsaoConclusao = pendenciaInternacao.getPrevisaoConclusao();
		RegistroInternacao registroInternacao = pendenciaInternacao.getId().getRegistroInternacao();
		TipoPendencia tipoPendencia = pendenciaInternacao.getId().getTipoPendencia();

		if (dataInicio == null)
			errors.rejectValue("dataInicio", "A data de início precisa ser informada!");
		
		if (previsaoConclusao == null)
			errors.rejectValue("previsaoConclusao", "A previsão de conclusão precisa ser informada!");
		
		if(registroInternacao == null ||registroInternacao.getIdRegistroInternacao() == 0)
			errors.rejectValue("registroInternacao", "O registro de internação não foi informado!");
		
		if(tipoPendencia == null || tipoPendencia.getIdPendencia() == 0)
			errors.rejectValue("tipoPendencia", "O tipo de pendência não foi informado!");
		
	}

}
