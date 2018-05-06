package br.com.kanleitos.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.kanleitos.models.ObservacaoInternacao;
import br.com.kanleitos.repository.ObservacaoInternacaoRepository;
import br.com.kanleitos.util.Response;
import br.com.kanleitos.validators.ObservacaoInternacaoValidator;

@Controller
public class ObservacaoInternacaoController {

	@Autowired
	private ObservacaoInternacaoRepository repository;

	@InitBinder("observacaoInternacao")
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new ObservacaoInternacaoValidator());
	}

	@PostMapping("/observacaoInternacao")
	public @ResponseBody ResponseEntity<Response<Long>> cadastrarObservacaoInternacao(
			@Valid @RequestBody ObservacaoInternacao observacaoInternacao, BindingResult result) {
		Response<Long> response = new Response<Long>();

		return persistObservacaoInternacao(observacaoInternacao, result, response);
	}

	@GetMapping("/observacaoInternacao")
	public @ResponseBody ResponseEntity<Response<List<ObservacaoInternacao>>> getObservacaoInternacaoByRegistroInternacao(
			@RequestParam long idRegistroInternacao) {

		Response<List<ObservacaoInternacao>> response = new Response<List<ObservacaoInternacao>>();
		response.setData(repository.findAllByIdRegistroInternacao(idRegistroInternacao));
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/observacaoInternacao")
	public @ResponseBody ResponseEntity<Response<Long>> updateObservacaoInternacao(
			@RequestBody ObservacaoInternacao observacaoInternacao, BindingResult result) {
		Response<Long> response = new Response<Long>();

		return persistObservacaoInternacao(observacaoInternacao, result, response);
	}
	
	@DeleteMapping("/observacaoInternacao")
	public @ResponseBody ResponseEntity<Response<Long>> deletarObservacaoInternacao(@RequestParam Long idObservacaoInternacao) {
		Response<Long> response = new Response<Long>();
		ObservacaoInternacao observacaoInternacao = repository.findOne(idObservacaoInternacao);

		if (observacaoInternacao == null) {
			response.addError("Observação de Internação não encontrada");
			return ResponseEntity.badRequest().body(response);
		} else {
			repository.delete(idObservacaoInternacao);
			response.setData(observacaoInternacao.getIdObservacaoInternacao());
			return ResponseEntity.ok(response);
		}
	}
	
	private ResponseEntity<Response<Long>> persistObservacaoInternacao(ObservacaoInternacao observacaoInternacao,
			BindingResult result, Response<Long> response) {

		if (result.hasErrors()) {
			response.setData(null);
			result.getAllErrors().forEach(error -> response.addError(error.getCode()));

			return ResponseEntity.badRequest().body(response);
		} else {
			observacaoInternacao = repository.save(observacaoInternacao);
			response.setData(observacaoInternacao.getIdObservacaoInternacao());

			return ResponseEntity.ok(response);
		}
	}

}
