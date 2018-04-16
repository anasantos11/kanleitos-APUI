package br.com.kanleitos.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.kanleitos.models.FinalizarInternacao;
import br.com.kanleitos.repository.LeitoRepository;
import br.com.kanleitos.repository.RegistroInternacaoRepository;
import br.com.kanleitos.util.StatusRegistro;

public class FinalizarValidator implements Validator {

	private FinalizarInternacao pedidoFinalizacao;

	@Autowired
	private LeitoRepository leitoRepo;

	@Autowired
	private RegistroInternacaoRepository registroRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return FinalizarInternacao.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		pedidoFinalizacao = (FinalizarInternacao) target;

		if (registroRepository.exists(pedidoFinalizacao.getIdRegistroInternacao()))
			errors.rejectValue("idRegistroInternacao", "O registro de internacao não existe!");

		StatusRegistro status = pedidoFinalizacao.getStatus();
		if (status == null || status.toString().isEmpty())
			errors.rejectValue("nome", "O nome não pode ser nulo ou vazio");

		Long idLeito = pedidoFinalizacao.getIdLeito();
		if (idLeito <= 0 || leitoRepo.exists(idLeito))
			errors.rejectValue("idLeito", "Leito não pode ser nulo");

		if (pedidoFinalizacao.getDataFinalizacao() == null)
			errors.rejectValue("dataFinalizacao", "A data de finalização não pode ser nula");

	}

}
