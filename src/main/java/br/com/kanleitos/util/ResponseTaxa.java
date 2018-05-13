package br.com.kanleitos.util;

import java.util.ArrayList;
import java.util.List;

import br.com.kanleitos.models.enums.RotuloTaxaOcupacao;

public class ResponseTaxa<T> {
	private final RotuloTaxaOcupacao rotulo;
	private List<T> data;

	public ResponseTaxa(RotuloTaxaOcupacao rotulo) {
		this.rotulo = rotulo;
		this.data = new ArrayList<>();
	}

	public RotuloTaxaOcupacao getRotulo() {
		return rotulo;
	}

	public List<T> getData() {
		return data;
	}

	public ResponseTaxa<T> addTaxa(T taxa) {
		data.add(taxa);
		return this;
	}

	public void addAllTaxas(List<T> taxas) {
		this.data.addAll(taxas);
	}

}
