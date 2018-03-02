package br.com.kanleitos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.kanleitos.models.Diagnostico;
import br.com.kanleitos.repository.DiagnosticoRepository;
import br.com.kanleitos.util.Response;

@Controller
public class DiagnosticoController {

	@Autowired
	private DiagnosticoRepository repository;

	@GetMapping("diagnosticos")
	public @ResponseBody ResponseEntity<Response<List<Diagnostico>>> listarDiagnosticos() {
		List<Diagnostico> diagnosticos = repository.findAll();

		Response<List<Diagnostico>> response = new Response<>();
		response.setData(diagnosticos);
		return ResponseEntity.ok(response);
	}

}
