package br.com.kanleitos.models;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class PendenciaInternacaoId implements Serializable{

	private static final long serialVersionUID = 4515320804787340978L;

	@ManyToOne
	@JoinColumn(name = "idRegistroInternacao", nullable = false)
	private RegistroInternacao registroInternacao;
	
	@ManyToOne
	@JoinColumn(name = "idTipoPendencia", nullable = false)
	private TipoPendencia tipoPendencia;

	public RegistroInternacao getRegistroInternacao() {
		return registroInternacao;
	}

	public void setRegistroInternacao(RegistroInternacao registroInternacao) {
		this.registroInternacao = registroInternacao;
	}

	public TipoPendencia getTipoPendencia() {
		return tipoPendencia;
	}

	public void setTipoPendencia(TipoPendencia tipoPendencia) {
		this.tipoPendencia = tipoPendencia;
	}
	
	
}
