package br.com.kanleitos.validators;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.kanleitos.models.ObservacaoInternacao;
import br.com.kanleitos.models.RegistroInternacao;

public class ObservacaoInternacaoValidator implements Validator {

	private ObservacaoInternacao observacaoInternacao;

	@Override
	public boolean supports(Class<?> clazz) {
		return ObservacaoInternacao.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		observacaoInternacao = (ObservacaoInternacao) target;

		Date data = observacaoInternacao.getData();
		String descricao = observacaoInternacao.getDescricao();
		RegistroInternacao registroInternacao = observacaoInternacao.getRegistroInternacao();


		if (data == null)
			errors.rejectValue("data", "A data da observação precisa ser informada!");

		if (descricao == null)
			errors.rejectValue("descricao", "A descrição da observação precisa ser informada!");

		if (registroInternacao == null || registroInternacao.getIdRegistroInternacao() == 0)
			errors.rejectValue("registroInternacao", "O registro de internação precisa ser informado!");
	}

}
