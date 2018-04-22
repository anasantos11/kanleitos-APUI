package br.com.kanleitos.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.kanleitos.models.TipoPendencia;
import br.com.kanleitos.repository.TipoPendenciaRepository;

public class TipoPendenciaValidator implements Validator {

	private TipoPendencia tipo;
	@Autowired
	private TipoPendenciaRepository repo;

	@Override
	public boolean supports(Class<?> clazz) {
		return TipoPendencia.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		tipo = (TipoPendencia) target;

		String nome = tipo.getNome();
		int idPendencia = tipo.getIdPendencia();
		if (nome == null || nome.isEmpty())
			errors.rejectValue("nome", "O nome não pode ser vazio!");
		if (idPendencia > 0 && repo.exists(idPendencia))
			errors.rejectValue("idPendencia", "Este Tipo de Pedencia já está cadastrado");
	}

}
