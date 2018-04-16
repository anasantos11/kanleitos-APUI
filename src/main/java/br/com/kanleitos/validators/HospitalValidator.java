package br.com.kanleitos.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.kanleitos.models.Hospital;

public class HospitalValidator implements Validator {

	private Hospital hospital;

	@Override
	public boolean supports(Class<?> clazz) {
		return Hospital.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		hospital = (Hospital) target;

		String nome = hospital.getNome();
		if (nome == null || nome.isEmpty())
			errors.rejectValue("nome", "O nome não pode ser nulo ou vazio");
	}

}
