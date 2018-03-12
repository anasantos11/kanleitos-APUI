package br.com.kanleitos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.kanleitos.models.RegistroInternacao;
import br.com.kanleitos.repository.EnfermariaRepository;
import br.com.kanleitos.repository.LeitoRepository;
import br.com.kanleitos.repository.PedidoInternacaoRepository;
import br.com.kanleitos.repository.RegistroInternacaoRepository;
import br.com.kanleitos.util.Response;
import br.com.kanleitos.util.StatusPedido;
import br.com.kanleitos.util.StatusRegistro;
import br.com.kanleitos.util.TipoStatusLeito;

@Controller
public class RegistroInternacaoController {

	@Autowired
	private RegistroInternacaoRepository registroRepository;

	@Autowired
	private PedidoInternacaoRepository pedidoRepository;

	@Autowired
	private LeitoRepository leitoRepository;

	@Autowired
	private EnfermariaRepository enfermariaRepository;

	@PostMapping("/registroInternacao")
	public @ResponseBody ResponseEntity<Response<Long>> registroInternacao(
			@RequestBody RegistroInternacao registroInternacao, Long idPedido, Long idEnfermaria, Long idLeito) {

		Response<Long> response = new Response<Long>();

		// Get PedidoInternacao Enfermaria e Leito
		registroInternacao.setPedidoInternacao(pedidoRepository.findOne(idPedido));
		registroInternacao.setEnfermaria(enfermariaRepository.findOne(idEnfermaria));
		registroInternacao.setLeito(leitoRepository.findOne(idLeito));
		registroInternacao.setStatusRegistro(StatusRegistro.EM_ANDAMENTO);

		List<RegistroInternacao> registros = registroRepository.findByPedidoInternacaoAndStatusRegistro(
				registroInternacao.getPedidoInternacao(), StatusRegistro.EM_ANDAMENTO);

		if (registros.size() == 0) {
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
			response.addError("Usuário já cadastrado");
			return ResponseEntity.badRequest().body(response);
		}

	}

	@GetMapping("/pacientesInternados")
	public @ResponseBody ResponseEntity<Response<List<RegistroInternacao>>> listarInternacoes() {
		List<RegistroInternacao> pacientesInternados = registroRepository
				.findAllByStatusRegistro(StatusRegistro.EM_ANDAMENTO);

		Response<List<RegistroInternacao>> response = new Response<>();
		response.setData(pacientesInternados);
		return ResponseEntity.ok(response);
	}
}
