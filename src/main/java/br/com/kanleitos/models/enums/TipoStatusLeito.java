package br.com.kanleitos.models.enums;

public enum TipoStatusLeito {
	DESOCUPADO("Desocupado"), OCUPADO_COMUM("Ocupado Comum"), OCUPADO_ISOLAMENTO_RESPIRATORIO(
			"Ocupado Isolamento Respiratório"), OCUPADO_PRECAUCAO_CONTATO(
					"Ocupado Precaução de Contato"), BLOQUEADO_REFORMA("Bloqueado Reforma"), BLOQUEADO_RH(
							"Bloqueado RH"), AGUARDANDO_LIMPEZA("Aguardando Limpeza"), OCUPADO_REVERSO_PROTETOR(
									"Ocupado Reverso Protetor"), INATIVO("Inativo");

	private String nome;

	private TipoStatusLeito(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
