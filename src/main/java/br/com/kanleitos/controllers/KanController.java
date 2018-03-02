package br.com.kanleitos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.kanleitos.models.RegistroInternacao;
import br.com.kanleitos.repository.RegistroInternacaoRepository;
import br.com.kanleitos.util.Classificacao;
import br.com.kanleitos.util.Response;

@Controller
public class KanController {

	@Autowired
	private RegistroInternacaoRepository registroRepository;

	@GetMapping("kanbanInternacoes")
	public @ResponseBody ResponseEntity<Response<List<RegistroInternacao>>> getKanbanInternacoes(
			@RequestParam String classificacao) {

		List<RegistroInternacao> registros = registroRepository
				.findByClassificacao(Classificacao.fromName(classificacao));

		Response<List<RegistroInternacao>> response = new Response<>();
		response.setData(registros);
		return ResponseEntity.ok(response);
	}

}
