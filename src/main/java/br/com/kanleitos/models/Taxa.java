package br.com.kanleitos.models;

import br.com.kanleitos.models.enums.TipoStatusLeito;

public class Taxa {
	private String nomeAla;
	private String grupo;
	private long quantidade;

	public Taxa() {
		this.grupo = "";
		this.quantidade = 0;
	}
	
	public Taxa(String grupo, Long quantidade) {
		this.grupo = grupo;
		this.quantidade = quantidade;
	}
	public Taxa(String nomeAla, TipoStatusLeito status, Long quantidade) {
		this.nomeAla = nomeAla;
		this.grupo = status.getNome();
		this.quantidade = quantidade;
	}

	public String getNomeAla() {
		return nomeAla;
	}

	public void setNomeAla(String nomeAla) {
		this.nomeAla = nomeAla;
	}

	public String getGrupo() {
		return grupo;
	}

	public Taxa setGrupo(String grupo) {
		this.grupo = grupo;
		return this;
	}

	public long getQuantidade() {
		return quantidade;
	}

	public Taxa setQuantidade(long quantidade) {
		this.quantidade = quantidade;
		return this;
	}

}
