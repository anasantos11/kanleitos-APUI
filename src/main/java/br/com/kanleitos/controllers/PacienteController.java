package br.com.kanleitos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.kanleitos.models.Paciente;
import br.com.kanleitos.repository.PacienteRepository;
import br.com.kanleitos.util.Response;

@Controller

public class PacienteController {

	@Autowired
	private PacienteRepository repository;

	@PostMapping("paciente")
	public @ResponseBody ResponseEntity<Response<Long>> cadastrarPaciente(@RequestBody Paciente paciente) {

		boolean exists = repository.existsByNumProntuario(paciente.getNumProntuario());
		Response<Long> response = new Response<Long>();

		if (!exists) {
			repository.save(paciente);
			response.setData(paciente.getNumProntuario());
			return ResponseEntity.ok(response);
		} else {
			response.addError("Usuário já cadastrado");
			return ResponseEntity.badRequest().body(response);
		}
	}

	@GetMapping("pacientes")
	public @ResponseBody ResponseEntity<Response<List<Paciente>>> listarPacientes() {
		List<Paciente> pacientes = repository.findAll();

		Response<List<Paciente>> response = new Response<>();
		response.setData(pacientes);
		return ResponseEntity.ok(response);
	}

	@GetMapping("paciente")
	public @ResponseBody ResponseEntity<Response<Paciente>> getPaciente(@RequestParam Long numProntuario,
			String nomeMae) {
		Paciente paciente = null;
		if (numProntuario != null) {
			paciente = repository.findByNumProntuario(numProntuario);
		} else {
			paciente = repository.findByNomeMae(nomeMae);
		}

		Response<Paciente> response = new Response<>();
		response.setData(paciente);
		return ResponseEntity.ok(response);
	}

}
