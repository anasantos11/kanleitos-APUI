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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.kanleitos.models.TransferenciaLeito;
import br.com.kanleitos.models.enums.TipoStatusLeito;
import br.com.kanleitos.repository.LeitoRepository;
import br.com.kanleitos.repository.RegistroInternacaoRepository;
import br.com.kanleitos.repository.TransferenciaLeitoRepository;
import br.com.kanleitos.util.Response;
import br.com.kanleitos.validators.TransferenciaLeitoValidator;

@Controller
public class TransferenciaLeitoController {

	@Autowired
	private TransferenciaLeitoRepository repo;
	@Autowired
	private RegistroInternacaoRepository repoRegistro;
	@Autowired
	private LeitoRepository repoLeito;

	@InitBinder("transferenciaLeito")
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new TransferenciaLeitoValidator());
	}

	@PostMapping("/transferenciaLeito")
	public @ResponseBody ResponseEntity<Response<Long>> cadastrarTransferencia(
			@Valid @RequestBody TransferenciaLeito transfLeito, BindingResult result) {
		Response<Long> response = new Response<Long>();
		transfLeito.setLeitoAnterior(transfLeito.getRegistroInternacao().getLeito());

		return persistTransferenciaLeito(transfLeito, result, response);
	}

	@GetMapping("/transferenciaLeito")
	public @ResponseBody ResponseEntity<Response<List<TransferenciaLeito>>> getTransferenciasByRegistroInternacao(
			@RequestParam long idRegistroInternacao) {
		Response<List<TransferenciaLeito>> response = new Response<List<TransferenciaLeito>>();
		response.setData(repo.findAllByRegistroInternacao(repoRegistro.findOne(idRegistroInternacao)));
		
		return ResponseEntity.ok(response);
	}

	private ResponseEntity<Response<Long>> persistTransferenciaLeito(TransferenciaLeito transfLeito,
			BindingResult result, Response<Long> response) {
		if (result.hasErrors()) {
			response.setData(null);
			result.getAllErrors().forEach(error -> response.addError(error.getCode()));

			return ResponseEntity.badRequest().body(response);
		} else {
			transfLeito = repo.save(transfLeito);
			transfLeito.getRegistroInternacao().setLeito(transfLeito.getProximoLeito());
			transfLeito.getLeitoAnterior().setStatusLeito(TipoStatusLeito.DESOCUPADO);
			transfLeito.getProximoLeito().setStatusLeito(TipoStatusLeito.OCUPADO_COMUM);
			repoLeito.save(transfLeito.getLeitoAnterior());
			repoLeito.save(transfLeito.getProximoLeito());
			repoRegistro.save(transfLeito.getRegistroInternacao());
			response.setData(transfLeito.getIdTransferenciaLeito());

			return ResponseEntity.ok(response);
		}
	}

}
