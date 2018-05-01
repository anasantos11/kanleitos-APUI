package br.com.kanleitos.models;

public class Taxa {

	private String grupo;
	private long quantidade;

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
