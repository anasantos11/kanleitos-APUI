package br.com.kanleitos.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.kanleitos.models.Isolamento;

public class IsolamentoValidator implements Validator {

	private Isolamento isolamento;

	@Override
	public boolean supports(Class<?> clazz) {
		return Isolamento.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		isolamento = (Isolamento) target;

		String nome = isolamento.getNome();
		if (nome == null || nome.isEmpty())
			errors.rejectValue("nome", "O nome não pode ser nulo ou vazio");
	}

}
