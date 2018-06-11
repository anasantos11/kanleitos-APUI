package br.com.kanleitos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.kanleitos.models.Enfermaria;
import br.com.kanleitos.models.Leito;
import br.com.kanleitos.repository.EnfermariaRepository;
import br.com.kanleitos.repository.LeitoRepository;
import br.com.kanleitos.util.Response;

@Controller
public class LeitoController {

	@Autowired
	private LeitoRepository repository;

	@Autowired
	private EnfermariaRepository repositoryEnfermaria;

	@GetMapping("leitos")
	public @ResponseBody ResponseEntity<Response<List<Leito>>> listarLeitos(@RequestParam boolean ativo) {
		List<Leito> leitos = ativo ? repository.findAllByStatusLeitoNotInativo() : repository.findAll();

		Response<List<Leito>> response = new Response<>();
		response.setData(leitos);
		return ResponseEntity.ok(response);
	}

	@GetMapping("leitosEnfermarias")
	public @ResponseBody ResponseEntity<Response<List<Leito>>> getLeitosByEnfermaria(@RequestParam Long idEnfermaria,
			@RequestParam boolean apenasDesocupados) {
		List<Leito> leitos = null;
		Enfermaria enfermaria = null;
		if (idEnfermaria != null) {
			enfermaria = repositoryEnfermaria.findOne(idEnfermaria);

			if (enfermaria != null) {
				leitos = apenasDesocupados ? repository.findAllByEnfermariaAndDesocupados(idEnfermaria)
						: repository.findAllByEnfermaria(idEnfermaria);
			}
		}

		Response<List<Leito>> response = new Response<>();
		response.setData(leitos);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/leito")
	public @ResponseBody ResponseEntity<Response<Long>> updateLeito(@RequestBody Leito leito) {
		Response<Long> response = new Response<Long>();
		Leito leitoAtual = repository.getOne(leito.getIdLeito());
		if(leitoAtual.getStatusLeito().name().startsWith("OCUPADO")) {
			response.setData(null);
			response.addError("O status não pode ser alterado o leito está ocupado.");
			return ResponseEntity.badRequest().body(response);
		}
			
		repository.save(leito);		
		response.setData(leito.getIdLeito());
		return ResponseEntity.ok(response);
	}
}
