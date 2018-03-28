package br.com.kanleitos.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.kanleitos.models.Isolamento;
import br.com.kanleitos.repository.IsolamentoRepository;
import br.com.kanleitos.util.Response;
import br.com.kanleitos.validators.IsolamentoValidator;

@Controller
public class IsolamentoController {

	@Autowired
	private IsolamentoRepository isolamentoRepository;

	@InitBinder("isolamento")
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new IsolamentoValidator());
	}

	@GetMapping("/isolamentos")
	public @ResponseBody ResponseEntity<Response<List<Isolamento>>> getIsolamentos() {
		List<Isolamento> isolamentos = isolamentoRepository.findAll();
		Response<List<Isolamento>> response = new Response<>();
		response.setData(isolamentos);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/isolamento")
	public @ResponseBody ResponseEntity<Response<Long>> cadastrarIsolamento(@RequestBody @Valid Isolamento isolamento,
			BindingResult result) {

		Response<Long> response = new Response<Long>();

		if (result.hasErrors()) {
			response.setData(null);
			result.getAllErrors().forEach(error -> response.addError(error.getCode()));

			return ResponseEntity.badRequest().body(response);
		} else {
			isolamento = isolamentoRepository.save(isolamento);
			response.setData(isolamento.getIdIsolamento());

			return ResponseEntity.ok(response);
		}

	}

	@PutMapping("/isolamento")
	public @ResponseBody ResponseEntity<Response<Long>> updateIsolamento(@RequestParam Long idIsolamento,
			@RequestBody @Valid Isolamento isolamento, BindingResult result) {

		Response<Long> response = new Response<Long>();

		if (result.hasErrors()) {
			response.setData(null);
			result.getAllErrors().forEach(error -> response.addError(error.getCode()));

			return ResponseEntity.badRequest().body(response);
		} else if (!isolamentoRepository.exists(idIsolamento)) {
			response.setData(null);
			response.addError("Não conseguimos encontrar este isolamento");

			return ResponseEntity.badRequest().body(response);
		} else {

			isolamento.setIdIsolamento(idIsolamento);
			isolamentoRepository.save(isolamento);
			response.setData(idIsolamento);

			return ResponseEntity.ok(response);
		}
	}

	@PostMapping("/inativarIsolamento")
	public @ResponseBody ResponseEntity<Response<Long>> inativarIsolamento(@RequestBody Long idIsolamento) {
		Response<Long> response = new Response<Long>();
		if (!isolamentoRepository.exists(idIsolamento)) {
			response.setData(null);
			response.addError("Não conseguimos encontrar este isolamento");

			return ResponseEntity.badRequest().body(response);
		} else {
			Isolamento isolamento = isolamentoRepository.findOne(idIsolamento);
			isolamento.setInativo(true);
			isolamentoRepository.save(isolamento);

			response.setData(idIsolamento);
			return ResponseEntity.ok(response);
		}
	}
}