package br.com.kanleitos.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Entity
public class TipoPendencia {

	@Id
	@SequenceGenerator(name = "TIPO_PENDENCIA_ID", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "TIPO_PENDENCIA_ID")
	private long idPendencia;

	@NotNull
	private String descPendencia;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "registroInternacao", referencedColumnName = "idRegistroInternacao")
	private RegistroInternacao registroInternacao;

	public TipoPendencia() {
		setDescPendencia(null);
	}

	public String getDescPendencia() {
		return descPendencia;
	}

	public void setDescPendencia(String descPendencia) {
		this.descPendencia = descPendencia;
	}

	public long getIdPendencia() {
		return idPendencia;
	}

}
