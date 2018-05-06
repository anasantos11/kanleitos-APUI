package br.com.kanleitos.models.enums;

public enum StatusRegistro {
	EM_ANDAMENTO, ALTA, OBITO, TRANSFERENCIA, PAD;

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
