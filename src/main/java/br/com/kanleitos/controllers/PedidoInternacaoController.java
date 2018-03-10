package br.com.kanleitos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.kanleitos.models.Paciente;
import br.com.kanleitos.models.PedidoInternacao;
import br.com.kanleitos.repository.PacienteRepository;
import br.com.kanleitos.repository.PedidoInternacaoRepository;
import br.com.kanleitos.util.Response;
import br.com.kanleitos.util.StatusPedido;

@Controller
public class PedidoInternacaoController {

	@Autowired
	private PedidoInternacaoRepository repository;

	@Autowired
	private PacienteRepository pacienteRepository;

	@PostMapping("pedidoInternacao")
	public @ResponseBody ResponseEntity<Response<Long>> pedidoInternacao(
			@RequestBody PedidoInternacao pedidoInternacao) {

		Response<Long> response = new Response<Long>();

		List<PedidoInternacao> pedidos = repository.findAllByPacienteAndStatusPedido(pedidoInternacao.getPaciente(),
				StatusPedido.PENDENTE);

		if (pedidos.isEmpty()) {
			repository.save(pedidoInternacao);
			response.setData(pedidoInternacao.getIdPedidoInternacao());
			return ResponseEntity.ok(response);
		} else {
			response.addError("Pedido já cadastrado");
			return ResponseEntity.badRequest().body(response);
		}
	}

	@GetMapping("pedidoInternacao/{numProntuario}")
	public @ResponseBody ResponseEntity<Response<PedidoInternacao>> getPaciente(@PathVariable Long numProntuario) {

		Response<PedidoInternacao> response = new Response<PedidoInternacao>();
		PedidoInternacao pedido = null;
		Paciente paciente = null;
		if (numProntuario != null) {
			paciente = pacienteRepository.findByNumProntuario(numProntuario);
			if (paciente != null) {
				long pront = paciente.getNumProntuario();
				if (pront > 0) {
					pedido = repository.findByPaciente(paciente);
					response.setData(pedido);
				}
			}
		} else {
			// paciente = repository.findOne(Integer.parseInt(idPedidoInternacao));
		}
		return ResponseEntity.ok(response);
	}

	@GetMapping("pedidos")
	public @ResponseBody ResponseEntity<Response<List<PedidoInternacao>>> listarPedidos() {
		List<PedidoInternacao> pedidos = repository.findAll();

		Response<List<PedidoInternacao>> response = new Response<>();
		response.setData(pedidos);
		return ResponseEntity.ok(response);
	}

	@GetMapping("pedidosEmAberto")
	public @ResponseBody ResponseEntity<Response<List<PedidoInternacao>>> pedidosEmAndamento() {
		List<PedidoInternacao> pedidos = repository.findAllByStatusPedidoOrStatusPedido(StatusPedido.PENDENTE,
				StatusPedido.ATRASADO);

		Response<List<PedidoInternacao>> response = new Response<>();
		response.setData(pedidos);
		return ResponseEntity.ok(response);
	}

}
