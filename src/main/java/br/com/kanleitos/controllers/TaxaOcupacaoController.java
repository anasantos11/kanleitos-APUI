package br.com.kanleitos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.kanleitos.models.RegistroInternacao;
import br.com.kanleitos.models.Taxa;
import br.com.kanleitos.models.TaxaOcupacao;
import br.com.kanleitos.models.enums.RotuloTaxaOcupacao;
import br.com.kanleitos.models.enums.StatusRegistro;
import br.com.kanleitos.repository.RegistroInternacaoRepository;
import br.com.kanleitos.util.Response;

@Controller
public class TaxaOcupacaoController {

	@Autowired
	private RegistroInternacaoRepository registroInternacaoRepository;

	@GetMapping("taxaOcupacao/genero")
	public @ResponseBody ResponseEntity<Response<TaxaOcupacao>> taxaPorGenero() {

		Response<TaxaOcupacao> response = new Response<TaxaOcupacao>();
		TaxaOcupacao taxaOcupacaoPorGernero = new TaxaOcupacao(RotuloTaxaOcupacao.GENERO);
		List<RegistroInternacao> registrosEmAndamento = registroInternacaoRepository
				.findAllByStatusRegistro(StatusRegistro.EM_ANDAMENTO);

		long mulheres = registrosEmAndamento.stream()
				.filter(ri -> ri.getPedidoInternacao().getPaciente().getGenero().toUpperCase().equals("FEMININO"))
				.count();
		long homens = registrosEmAndamento.size() - mulheres;

		taxaOcupacaoPorGernero.addTaxa(new Taxa().setGrupo("Feminino").setQuantidade(mulheres))
				.addTaxa(new Taxa().setGrupo("Masculino").setQuantidade(homens));

		response.setData(taxaOcupacaoPorGernero);
		return ResponseEntity.ok(response);
	}

}
