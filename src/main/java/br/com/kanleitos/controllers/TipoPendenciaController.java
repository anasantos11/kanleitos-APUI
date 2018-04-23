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

import br.com.kanleitos.models.TipoPendencia;
import br.com.kanleitos.repository.TipoPendenciaRepository;
import br.com.kanleitos.util.Response;
import br.com.kanleitos.validators.TipoPendenciaValidator;

@Controller
public class TipoPendenciaController {

	@Autowired
	private TipoPendenciaRepository repo;

	@InitBinder("tipoPendencia")
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new TipoPendenciaValidator());
	}

	@PostMapping("/tipoPendencia")
	public @ResponseBody ResponseEntity<Response<Integer>> cadastrarTipo(
			@Valid @RequestBody TipoPendencia tipoPendencia, BindingResult result) {
		Response<Integer> response = new Response<Integer>();

		return persistTipoPendencia(tipoPendencia, result, response);
	}

	@GetMapping("/tipoPendencia")
	public @ResponseBody ResponseEntity<Response<List<TipoPendencia>>> getPendenciasByStatus(
			@RequestParam(value="somenteAtivos", required=false)  boolean somenteAtivos) {
		Response<List<TipoPendencia>> response = new Response<List<TipoPendencia>>();

		if(somenteAtivos) {
			response.setData(repo.findAllByInativo(false));
		}else {
			response.setData(repo.findAll());
		}

		return ResponseEntity.ok(response);
	}

	@PutMapping("/tipoPendencia")
	public @ResponseBody ResponseEntity<Response<Integer>> atualizarTipo(
			@RequestBody TipoPendencia tipoPendencia, BindingResult result) {
		Response<Integer> response = new Response<Integer>();

		return persistTipoPendencia(tipoPendencia, result, response);
	}

	@PutMapping("/tipoPendencia/alterarStatus")
	public @ResponseBody ResponseEntity<Response<Integer>> alteraStatus(@RequestParam Integer idTipoPendencia) {
		Response<Integer> response = new Response<Integer>();
		TipoPendencia tipoPendencia = repo.findOne(idTipoPendencia);

		if (tipoPendencia == null) {
			response.addError("Tipo de pendencia não encontrado");
			return ResponseEntity.badRequest().body(response);
		} else {
			tipoPendencia.setInativo(!tipoPendencia.isInativo());
			repo.save(tipoPendencia);
			response.setData(idTipoPendencia);
			return ResponseEntity.ok(response);
		}
	}

	private ResponseEntity<Response<Integer>> persistTipoPendencia(TipoPendencia tipoPendencia, BindingResult result,
			Response<Integer> response) {
		if (result.hasErrors()) {
			response.setData(null);
			result.getAllErrors().forEach(error -> response.addError(error.getCode()));

			return ResponseEntity.badRequest().body(response);
		} else {
			tipoPendencia = repo.save(tipoPendencia);
			response.setData(tipoPendencia.getIdPendencia());

			return ResponseEntity.ok(response);
		}
	}

}
