package br.com.kanleitos.models;

import br.com.kanleitos.models.enums.TipoStatusLeito;

public class Taxa<T> {
	private String nomeAla;
	private String grupo;
	private T quantidade;

	public Taxa() {
		this.grupo = "";
		this.quantidade = null;
	}

	public Taxa(String grupo, T quantidade) {
		this.grupo = grupo;
		this.quantidade = quantidade;
	}

	public Taxa(String nomeAla, TipoStatusLeito status, T quantidade) {
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

	public Taxa<T> setGrupo(String grupo) {
		this.grupo = grupo;
		return this;
	}

	public T getQuantidade() {
		return quantidade;
	}

	public Taxa<T> setQuantidade(T quantidade) {
		this.quantidade = quantidade;
		return this;
	}

}
