package br.com.kanleitos.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.kanleitos.models.TipoExame;

public class TipoExameValidator implements Validator {

	private TipoExame tipoExame;

	@Override
	public boolean supports(Class<?> clazz) {
		return TipoExame.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		this.tipoExame = (TipoExame) target;

		String nome = this.tipoExame.getNome();

		if (nome == null || nome.isEmpty())
			errors.rejectValue("nome", "O nome não pode ser nulo ou vazio");
	}

}
