package br.com.kanleitos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.kanleitos.models.Ala;
import br.com.kanleitos.repository.AlaRepository;
import br.com.kanleitos.util.Response;

@Controller
public class AlaController {

	@Autowired
	private AlaRepository repository;

	@GetMapping("alas")
	public @ResponseBody ResponseEntity<Response<List<Ala>>> listarAlas(@RequestParam boolean ativo) {
		List<Ala> alas = ativo ? repository.findAllByInativa(false) : repository.findAll();

		Response<List<Ala>> response = new Response<>();
		response.setData(alas);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/ala/alterarStatus")
	public @ResponseBody ResponseEntity<Response<Long>> alteraStatus(@RequestParam Long idAla) {
		Response<Long> response = new Response<Long>();
		Ala ala = repository.findOne(idAla);

		if (ala == null) {
			response.addError("Ala não encontrada");
			return ResponseEntity.badRequest().body(response);
		} else {
			ala.setInativa(!ala.isInativa());
			repository.save(ala);
			response.setData(idAla);
			return ResponseEntity.ok(response);
		}
	}

}
