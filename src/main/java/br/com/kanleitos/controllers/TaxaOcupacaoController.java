package br.com.kanleitos.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.kanleitos.models.Enfermaria;
import br.com.kanleitos.models.Leito;
import br.com.kanleitos.models.RegistroInternacao;
import br.com.kanleitos.models.Taxa;
import br.com.kanleitos.models.TaxaEnfermaria;
import br.com.kanleitos.models.enums.FaixaEtaria;
import br.com.kanleitos.models.enums.RotuloTaxaOcupacao;
import br.com.kanleitos.models.enums.StatusRegistro;
import br.com.kanleitos.models.enums.TipoStatusLeito;
import br.com.kanleitos.repository.LeitoRepository;
import br.com.kanleitos.repository.PendenciaInternacaoRepository;
import br.com.kanleitos.repository.RegistroInternacaoRepository;
import br.com.kanleitos.util.ResponseTaxa;

@Controller
public class TaxaOcupacaoController {

	@Autowired
	private RegistroInternacaoRepository registroInternacaoRepository;

	@Autowired
	private LeitoRepository leitoRepository;

	@Autowired
	private PendenciaInternacaoRepository pendenciaInternacaoRepository;

	@GetMapping("taxaOcupacao/genero")
	public @ResponseBody ResponseEntity<ResponseTaxa<Taxa>> taxaPorGenero() {

		ResponseTaxa<Taxa> taxaOcupacaoPorGenero = new ResponseTaxa<Taxa>(RotuloTaxaOcupacao.GENERO);
		List<RegistroInternacao> registrosEmAndamento = registroInternacaoRepository
				.findAllByStatusRegistro(StatusRegistro.EM_ANDAMENTO);

		long mulheres = registrosEmAndamento.stream()
				.filter(ri -> ri.getPedidoInternacao().getPaciente().getGenero().toUpperCase().equals("FEMININO"))
				.count();
		long homens = registrosEmAndamento.size() - mulheres;

		taxaOcupacaoPorGenero.addTaxa(new Taxa().setGrupo("Feminino").setQuantidade(mulheres))
				.addTaxa(new Taxa().setGrupo("Masculino").setQuantidade(homens));

		return ResponseEntity.ok(taxaOcupacaoPorGenero);
	}

	@GetMapping("taxaOcupacao/idade")
	public @ResponseBody ResponseEntity<ResponseTaxa<Taxa>> taxaPorIdade() {

		ResponseTaxa<Taxa> taxaOcupacaoPorIdade = new ResponseTaxa<Taxa>(RotuloTaxaOcupacao.IDADE);
		List<RegistroInternacao> registrosEmAndamento = registroInternacaoRepository
				.findAllByStatusRegistro(StatusRegistro.EM_ANDAMENTO);
		List<Taxa> taxas = filtraTaxasPorIdade(registrosEmAndamento);
		taxaOcupacaoPorIdade.addAllTaxas(taxas);

		return ResponseEntity.ok(taxaOcupacaoPorIdade);
	}

	private List<Taxa> filtraTaxasPorIdade(List<RegistroInternacao> stream) {
		List<Taxa> taxas = new ArrayList<Taxa>();
		List<FaixaEtaria> faixasEtarias = Arrays.asList(FaixaEtaria.values());

		for (int i = 0; i < faixasEtarias.size(); i++) {
			FaixaEtaria faixaEtaria = faixasEtarias.get(i);
			int faixaEtariaBase = faixaEtaria.idade;
			int faixaEtariaTopo = (i == faixasEtarias.size() - 1) ? Integer.MAX_VALUE : faixasEtarias.get(i + 1).idade;

			long quantidade = stream.stream()
					.filter(ri -> ri.getPedidoInternacao().getPaciente().getIdade() > faixaEtariaBase
							&& ri.getPedidoInternacao().getPaciente().getIdade() <= faixaEtariaTopo)
					.count();
			taxas.add(new Taxa().setGrupo(faixaEtaria.nome).setQuantidade(quantidade));
		}

		return taxas;
	}

	@GetMapping("taxaOcupacao/alas")
	public @ResponseBody ResponseEntity<ResponseTaxa<TaxaEnfermaria>> taxaPorAla(
		@RequestParam Long idAla) {

		List<Leito> leitos = leitoRepository.findAllByAla(idAla);
		Set<Enfermaria> enfermarias = new HashSet<>();
		leitos.forEach(leito -> enfermarias.add(leito.getEnfermaria()));
		ResponseTaxa<TaxaEnfermaria> ocupacaoAla = new ResponseTaxa<TaxaEnfermaria>(RotuloTaxaOcupacao.ALA);

		enfermarias.forEach(enf -> {
			TaxaEnfermaria taxa = new TaxaEnfermaria().setNomeAla(enf.getAla().getNomeAla())
					.setNomeEnf(enf.getNomeEnfermaria());
			setDadosLeitos(enf, leitos, taxa);
			ocupacaoAla.addTaxa(taxa);
		});

		return ResponseEntity.ok(ocupacaoAla);
	}

	private void setDadosLeitos(Enfermaria enf, List<Leito> leitos, TaxaEnfermaria taxa) {
		taxa.setQuantidadeLeitosLivres(leitos.stream()
				.filter(leito -> leito.getEnfermaria() == enf && leito.getStatusLeito() == TipoStatusLeito.DESOCUPADO)
				.count())
				.setQuantidadeLeitosOcupados(leitos.stream()
						.filter(leito -> leito.getEnfermaria() == enf
								&& leito.getStatusLeito().name().startsWith("OCUPADO"))
						.count())
				.setQuantidadeLeitosIndisponiveis(leitos.stream().filter(
						leito -> leito.getEnfermaria() == enf && (leito.getStatusLeito().name().startsWith("BLOQUEADO")
								|| leito.getStatusLeito() == TipoStatusLeito.AGUARDANDO_LIMPEZA))
						.count());

	}

	@GetMapping("taxaOcupacao/statusLeitos")
	public @ResponseBody ResponseEntity<ResponseTaxa<Taxa>> taxaPorStatusLeito(@RequestParam Long idAla) {
		ResponseTaxa<Taxa> taxaStatusLeito = new ResponseTaxa<Taxa>(RotuloTaxaOcupacao.STATUS_LEITO);
		taxaStatusLeito.addAllTaxas(leitoRepository.countAllStatusLeitoByAla(idAla));
		return ResponseEntity.ok(taxaStatusLeito);
	}

	@GetMapping("taxaOcupacao/tipoPendenciaInternacao")
	public @ResponseBody ResponseEntity<ResponseTaxa<Taxa>> taxaPorTipoPendenciaInternacaoEmAndamento(
			@RequestParam Long idAla) {
		ResponseTaxa<Taxa> taxaStatusLeito = new ResponseTaxa<Taxa>(RotuloTaxaOcupacao.TIPO_PENDENCIA_INTERNACAO);
		taxaStatusLeito.addAllTaxas(pendenciaInternacaoRepository.countAllPendenciasInternacaoAndamento(idAla));
		return ResponseEntity.ok(taxaStatusLeito);
	}

}
