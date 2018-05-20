package br.com.kanleitos.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class TransferenciaLeito {

	@Id
	@SequenceGenerator(name = "TRANSFERENCIA_LEITO_ID", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "TRANSFERENCIA_LEITO_ID")
	private Long idTransferenciaLeito;

	@ManyToOne
	@JoinColumn(name = "idRegistroInternacao", nullable = false)
	private RegistroInternacao registroInternacao;

	@ManyToOne
	@JoinColumn(name = "idLeitoAnterior", nullable = false)
	private Leito leitoAnterior;

	@ManyToOne
	@JoinColumn(name = "idProximoLeito", nullable = false)
	private Leito proximoLeito;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	public RegistroInternacao getRegistroInternacao() {
		return registroInternacao;
	}

	public void setRegistroInternacao(RegistroInternacao registroInternacao) {
		this.registroInternacao = registroInternacao;
	}

	public Leito getLeitoAnterior() {
		return leitoAnterior;
	}

	public void setLeitoAnterior(Leito leitoAnterior) {
		this.leitoAnterior = leitoAnterior;
	}

	public Leito getProximoLeito() {
		return proximoLeito;
	}

	public void setProximoLeito(Leito proximoLeito) {
		this.proximoLeito = proximoLeito;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Long getIdTransferenciaLeito() {
		return idTransferenciaLeito;
	}
}
