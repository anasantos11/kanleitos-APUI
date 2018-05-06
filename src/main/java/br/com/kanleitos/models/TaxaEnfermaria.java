package br.com.kanleitos.models;

public class TaxaEnfermaria {
	private String nomeAla;
	private String nomeEnf;
	private long quantidadeLeitosLivres;
	private long quantidadeLeitosOcupados;
	private long quantidadeLeitosIndisponiveis;

	public String getNomeAla() {
		return nomeAla;
	}

	public TaxaEnfermaria setNomeAla(String nomeAla) {
		this.nomeAla = nomeAla;
		return this;
	}

	public String getNomeEnf() {
		return nomeEnf;
	}

	public TaxaEnfermaria setNomeEnf(String nomeEnf) {
		this.nomeEnf = nomeEnf;
		return this;
	}

	public long getQuantidadeLeitosLivres() {
		return quantidadeLeitosLivres;
	}

	public TaxaEnfermaria setQuantidadeLeitosLivres(long quantidadeLeitosLivres) {
		this.quantidadeLeitosLivres = quantidadeLeitosLivres;
		return this;
	}

	public long getQuantidadeLeitosOcupados() {
		return quantidadeLeitosOcupados;
	}

	public TaxaEnfermaria setQuantidadeLeitosOcupados(long quantidadeLeitosOcupados) {
		this.quantidadeLeitosOcupados = quantidadeLeitosOcupados;
		return this;
	}

	public long getQuantidadeLeitosIndisponiveis() {
		return quantidadeLeitosIndisponiveis;
	}

	public TaxaEnfermaria setQuantidadeLeitosIndisponiveis(long quantidadeLeitosIndisponiveis) {
		this.quantidadeLeitosIndisponiveis = quantidadeLeitosIndisponiveis;
		return this;
	}

}
