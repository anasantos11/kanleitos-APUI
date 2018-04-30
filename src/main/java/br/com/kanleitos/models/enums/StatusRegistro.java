package br.com.kanleitos.models.enums;

public enum StatusRegistro {
	EM_ANDAMENTO("Em Andamento"), ALTA("Alta"), OBITO("Óbito"), TRANSFERENCIA("Transferência"), PAD("PAD");

	private String nome;

	private StatusRegistro(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public static StatusRegistro fromName(String statusRegistro) {
		switch (statusRegistro) {
		case "Em Andamento":
			return StatusRegistro.EM_ANDAMENTO;
		case "Alta":
			return StatusRegistro.ALTA;
		case "Óbito":
			return StatusRegistro.OBITO;
		case "Transferência":
			return StatusRegistro.TRANSFERENCIA;
		case "PAD":
			return StatusRegistro.PAD;
		default:
			throw new IllegalArgumentException("Unknown" + statusRegistro);
		}
	}
}
