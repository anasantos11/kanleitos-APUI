package br.com.kanleitos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.kanleitos.models.Ala;
import br.com.kanleitos.repository.AlaRepository;
import br.com.kanleitos.util.Response;

@Controller
public class AlaController {

	@Autowired
	private AlaRepository repository;

	@GetMapping("alas")
	public @ResponseBody ResponseEntity<Response<List<Ala>>> listarAlas() {
		List<Ala> alas = repository.findAll();

		Response<List<Ala>> response = new Response<>();
		response.setData(alas);
		return ResponseEntity.ok(response);
	}

}
