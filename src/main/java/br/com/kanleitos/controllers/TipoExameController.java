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

import br.com.kanleitos.models.TipoExame;
import br.com.kanleitos.repository.TipoExamaRepository;
import br.com.kanleitos.util.Response;
import br.com.kanleitos.validators.TipoExameValidator;

@Controller
public class TipoExameController {

	@Autowired
	private TipoExamaRepository repository;

	@InitBinder("tipoExame")
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new TipoExameValidator());
	}

	@GetMapping("/tiposExames")
	public @ResponseBody ResponseEntity<Response<List<TipoExame>>> getTiposExames(@RequestParam boolean ativo) {
		List<TipoExame> tiposExames = ativo ? repository.findAllByInativo(false) : repository.findAll();

		Response<List<TipoExame>> response = new Response<>();
		response.setData(tiposExames);

		return ResponseEntity.ok(response);
	}

	@PostMapping("/tipoExame")
	public @ResponseBody ResponseEntity<Response<Integer>> cadastrarTipoExame(@Valid @RequestBody TipoExame tipoExame,
			BindingResult result) {
		Response<Integer> response = new Response<Integer>();
		if (result.hasErrors()) {
			response.setData(null);
			result.getAllErrors().forEach(error -> response.addError(error.getCode()));

			return ResponseEntity.badRequest().body(response);
		} else {
			tipoExame = repository.save(tipoExame);
			response.setData(tipoExame.getTipoExameId());

			return ResponseEntity.ok(response);
		}
	}

	@PutMapping("/tipoExame/alterarStatus")
	public @ResponseBody ResponseEntity<Response<Integer>> alteraStatus(@RequestParam Integer tipoExameId) {
		Response<Integer> response = new Response<Integer>();
		TipoExame tipoExame = repository.findOne(tipoExameId);

		if (tipoExame == null) {
			response.addError("Tipo de exame não encontrado");
			return ResponseEntity.badRequest().body(response);
		} else {
			tipoExame.setInativo(!tipoExame.isInativo());
			repository.save(tipoExame);
			response.setData(tipoExameId);
			return ResponseEntity.ok(response);
		}
	}

	@PutMapping("/tipoExame")
	public @ResponseBody ResponseEntity<Response<Integer>> atualizarTipo(@RequestBody @Valid TipoExame tipoExame,
			BindingResult result) {
		Response<Integer> response = new Response<Integer>();
		if (result.hasErrors()) {
			response.setData(null);
			result.getAllErrors().forEach(error -> response.addError(error.getCode()));

			return ResponseEntity.badRequest().body(response);
		} else {
			tipoExame = repository.save(tipoExame);
			response.setData(tipoExame.getTipoExameId());

			return ResponseEntity.ok(response);
		}

	}

}
