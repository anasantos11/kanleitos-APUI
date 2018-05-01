package br.com.kanleitos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.kanleitos.models.Enfermaria;
import br.com.kanleitos.models.enums.TipoStatusLeito;
import br.com.kanleitos.repository.AlaRepository;
import br.com.kanleitos.repository.EnfermariaRepository;
import br.com.kanleitos.repository.LeitoRepository;
import br.com.kanleitos.repository.RegistroInternacaoRepository;
import br.com.kanleitos.util.Response;

@Controller
public class EnfermariaController {

	@Autowired
	private EnfermariaRepository repository;

	@Autowired
	private AlaRepository alaRepository;

	@Autowired
	private RegistroInternacaoRepository registroInternacao;

	@Autowired
	private LeitoRepository leitoRepository;

	@GetMapping("enfermarias")
	public @ResponseBody ResponseEntity<Response<List<Enfermaria>>> listarEnfermarias(@RequestParam boolean ativo) {
		List<Enfermaria> enfermarias = ativo ? repository.findAllByInativa(false) : repository.findAll();

		Response<List<Enfermaria>> response = new Response<>();
		response.setData(enfermarias);
		return ResponseEntity.ok(response);
	}

	@GetMapping("enfermariasByAla")
	public @ResponseBody ResponseEntity<Response<List<Enfermaria>>> listarEnfermariasByAlas(@RequestParam Long idAla) {
		List<Enfermaria> enfermarias = null;
		Response<List<Enfermaria>> response = new Response<>();

		if (idAla != null) {
			enfermarias = repository.findAllByAla(alaRepository.findOne(idAla));
			response.setData(enfermarias);
			return ResponseEntity.ok(response);
		} else {
			response.addError("Nenhuma enfermaria encontrada");
			return ResponseEntity.badRequest().body(response);
		}

	}

	@PutMapping("/enfermaria/alterarStatus")
	public @ResponseBody ResponseEntity<Response<Long>> alteraStatus(@RequestParam Long idEnfermaria) {
		Response<Long> response = new Response<Long>();
		Enfermaria enfermaria = repository.findOne(idEnfermaria);

		if (enfermaria == null || registroInternacao.enfermariaHasLeitosRegistrados(enfermaria)) {
			response.addError("Enfermaria não encontrada ou com pacientes");
			return ResponseEntity.badRequest().body(response);
		} else {
			boolean isInativa = !enfermaria.isInativa();
			enfermaria.setInativa(isInativa);
			repository.save(enfermaria);
			response.setData(idEnfermaria);
			leitoRepository.updateStatusByEnfermaria(isInativa ? TipoStatusLeito.INATIVO : TipoStatusLeito.DESOCUPADO,
					enfermaria);
			return ResponseEntity.ok(response);
		}
	}

}
