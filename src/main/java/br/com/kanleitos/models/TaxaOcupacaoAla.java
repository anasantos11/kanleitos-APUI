package br.com.kanleitos.models;

import java.util.ArrayList;
import java.util.List;

import br.com.kanleitos.models.enums.RotuloTaxaOcupacao;

public class TaxaOcupacaoAla {
	private final RotuloTaxaOcupacao rotulo;
	private List<TaxaEnfermaria> enfermarias;

	public TaxaOcupacaoAla(RotuloTaxaOcupacao rotulo) {
		this.rotulo = rotulo;
		this.enfermarias = new ArrayList<>();
	}

	public RotuloTaxaOcupacao getRotulo() {
		return rotulo;
	}

	public List<TaxaEnfermaria> getEnfermarias() {
		return enfermarias;
	}

	public TaxaOcupacaoAla addTaxa(TaxaEnfermaria taxa) {
		enfermarias.add(taxa);
		return this;
	}

}
