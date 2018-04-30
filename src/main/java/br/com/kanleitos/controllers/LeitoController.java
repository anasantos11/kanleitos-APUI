package br.com.kanleitos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.kanleitos.models.Enfermaria;
import br.com.kanleitos.models.Leito;
import br.com.kanleitos.models.enums.TipoStatusLeito;
import br.com.kanleitos.repository.LeitoRepository;
import br.com.kanleitos.repository.EnfermariaRepository;
import br.com.kanleitos.util.Response;

@Controller
public class LeitoController {

	@Autowired
	private LeitoRepository repository;

	@Autowired
	private EnfermariaRepository repositoryEnfermaria;

	@GetMapping("leitos")
	public @ResponseBody ResponseEntity<Response<List<Leito>>> listarLeitos() {
		List<Leito> leitos = repository.findAll();

		Response<List<Leito>> response = new Response<>();
		response.setData(leitos);
		return ResponseEntity.ok(response);
	}

	@GetMapping("leitosEnfermarias")
	public @ResponseBody ResponseEntity<Response<List<Leito>>> getLeitosByEnfermaria(@RequestParam Long idEnfermaria) {
		List<Leito> leitos = null;
		Enfermaria enfermaria = null;
		if (idEnfermaria != null) {
			enfermaria = repositoryEnfermaria.findOne(idEnfermaria);

			if (enfermaria != null) {
				leitos = repository.findByEnfermariaAndStatusLeito(enfermaria, TipoStatusLeito.DESOCUPADO);
			}
		}

		Response<List<Leito>> response = new Response<>();
		response.setData(leitos);
		return ResponseEntity.ok(response);
	}

}
