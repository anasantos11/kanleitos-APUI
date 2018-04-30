package br.com.kanleitos.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.kanleitos.models.PendenciaInternacao;
import br.com.kanleitos.repository.PendenciaInternacaoRepository;
import br.com.kanleitos.util.Response;
import br.com.kanleitos.validators.PendenciaInternacaoValidator;

@Controller
public class PendenciaInternacaoController {

	@Autowired
	private PendenciaInternacaoRepository repository;

	@InitBinder("pendenciaInternacao")
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new PendenciaInternacaoValidator());
	}

	@PostMapping("/pendenciaInternacao")
	public @ResponseBody ResponseEntity<Response<Integer>> cadastrarPendenciaInternacao(
			@Valid @RequestBody PendenciaInternacao pendenciaInternacao, BindingResult result) {
		Response<Integer> response = new Response<Integer>();

		return persistPendenciaInternacao(pendenciaInternacao, result, response);
	}

	private ResponseEntity<Response<Integer>> persistPendenciaInternacao(PendenciaInternacao pendenciaInternacao,
			BindingResult result, Response<Integer> response) {

		if (result.hasErrors()) {
			response.setData(null);
			result.getAllErrors().forEach(error -> response.addError(error.getCode()));

			return ResponseEntity.badRequest().body(response);
		} else {
			pendenciaInternacao = repository.save(pendenciaInternacao);
			response.setData(0);

			return ResponseEntity.ok(response);
		}
	}

}
