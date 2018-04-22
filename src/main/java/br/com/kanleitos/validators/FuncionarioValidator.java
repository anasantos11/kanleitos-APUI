package br.com.kanleitos.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.kanleitos.models.Funcionario;
import br.com.kanleitos.repository.FuncionarioRepository;

public class FuncionarioValidator implements Validator {

	@Autowired
	private FuncionarioRepository repository;

	private Funcionario funcionario;

	@Override
	public boolean supports(Class<?> clazz) {
		return Funcionario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		funcionario = (Funcionario) target;

		String especialidade = funcionario.getEspecialidade();
		int idFuncionario = funcionario.getIdFuncionario();
		if (especialidade == null || especialidade.isEmpty())
			errors.rejectValue("especialidade", "A especialidade não pode ser vazio!");
		if (idFuncionario > 0 && repository.exists(idFuncionario))
			errors.rejectValue("idPendencia", "Este Funcionário já está cadastrado");
	}

}
