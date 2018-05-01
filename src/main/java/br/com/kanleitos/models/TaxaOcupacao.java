package br.com.kanleitos.models;

import java.util.ArrayList;
import java.util.List;

import br.com.kanleitos.models.enums.RotuloTaxaOcupacao;

public class TaxaOcupacao {

	private final RotuloTaxaOcupacao rotulo;
	private List<Taxa> taxas;

	public TaxaOcupacao(RotuloTaxaOcupacao rotulo) {
		this.rotulo = rotulo;
		this.taxas = new ArrayList<>();
	}

	public RotuloTaxaOcupacao getRotulo() {
		return rotulo;
	}

	public List<Taxa> getTaxas() {
		return taxas;
	}

	public TaxaOcupacao addTaxa(Taxa taxa) {
		this.taxas.add(taxa);
		return this;
	}

}
