package br.com.kanleitos.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.kanleitos.models.Filtro;
import br.com.kanleitos.models.FinalizarInternacao;
import br.com.kanleitos.models.Leito;
import br.com.kanleitos.models.PedidoInternacao;
import br.com.kanleitos.models.RegistroInternacao;
import br.com.kanleitos.models.enums.StatusPedido;
import br.com.kanleitos.models.enums.StatusRegistro;
import br.com.kanleitos.models.enums.TipoStatusLeito;
import br.com.kanleitos.predicates.RegistroInternacaoPredicate;
import br.com.kanleitos.repository.LeitoRepository;
import br.com.kanleitos.repository.PedidoInternacaoRepository;
import br.com.kanleitos.repository.RegistroInternacaoRepository;
import br.com.kanleitos.util.Response;
import br.com.kanleitos.validators.FinalizarValidator;

@Controller
public class RegistroInternacaoController {

	@Autowired
	private RegistroInternacaoRepository registroRepository;

	@Autowired
	private PedidoInternacaoRepository pedidoRepository;

	@Autowired
	private LeitoRepository leitoRepository;

	@InitBinder("finalizar")
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new FinalizarValidator());
	}

	@PostMapping("/registroInternacao")
	public @ResponseBody ResponseEntity<Response<Long>> registroInternacao(
			@RequestBody RegistroInternacao registroInternacao) {

		Response<Long> response = new Response<Long>();

		List<PedidoInternacao> registros = registroRepository.findAllByPedidoInternacaoAndStatusRegistro(
				registroInternacao.getPedidoInternacao(), StatusRegistro.EM_ANDAMENTO);

		if (registros.size() == 0) {
			registroInternacao.setStatusRegistro(StatusRegistro.EM_ANDAMENTO);
			registroRepository.save(registroInternacao);
			// Alterar Status do Leito para Ocupado
			registroInternacao.getLeito().setStatusLeito(TipoStatusLeito.OCUPADO_COMUM);
			leitoRepository.save(registroInternacao.getLeito());

			// Atualizar Pedido para concluido
			registroInternacao.getPedidoInternacao().setStatusPedido(StatusPedido.CONCLUIDO);
			pedidoRepository.save(registroInternacao.getPedidoInternacao());

			response.setData(registroInternacao.getIdRegistroInternacao());
			return ResponseEntity.ok(response);
		} else {
			response.addError("Paciente já internado");
			return ResponseEntity.badRequest().body(response);
		}

	}

	@PostMapping("/pacientesInternados")
	public @ResponseBody ResponseEntity<Response<List<RegistroInternacao>>> listarInternacoes(@RequestBody Filtro filtros) {
		
		List<RegistroInternacao> pacientesInternados = (List<RegistroInternacao>) registroRepository
				.findAll(RegistroInternacaoPredicate.filtroPesquisa(filtros));

		Response<List<RegistroInternacao>> response = new Response<>();
		response.setData(pacientesInternados);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/encerrarInternacao")
	public @ResponseBody ResponseEntity<Response<Long>> encerrarInternacao(
			@Valid @RequestBody FinalizarInternacao finalizarInternacao, BindingResult result) {
		Response<Long> response = new Response<Long>();

		if (result.hasErrors()) {
			response.setData(null);
			result.getAllErrors().forEach(error -> response.addError(error.getCode()));

			return ResponseEntity.badRequest().body(response);
		} else {
			RegistroInternacao registroInternacao = registroRepository
					.getOne(finalizarInternacao.getIdRegistroInternacao());

			Leito leito = registroInternacao.getLeito();
			leito.setStatusLeito(TipoStatusLeito.DESOCUPADO);

			registroInternacao.setDataAlta(finalizarInternacao.getDataFinalizacao());
			registroInternacao.setStatusRegistro(finalizarInternacao.getStatus());

			registroRepository.save(registroInternacao);

			response.setData(registroInternacao.getIdRegistroInternacao());

			return ResponseEntity.ok(response);
		}
	}
}
