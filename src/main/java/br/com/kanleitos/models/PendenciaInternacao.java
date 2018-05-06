package br.com.kanleitos.models;

import java.util.Date;

import javax.persistence.Column;
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
public class PendenciaInternacao {

	@Id
	@SequenceGenerator(name = "PENDENCIA_INTERNACAO_ID", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PENDENCIA_INTERNACAO_ID")
	private Long idPendenciaInternacao;
	
	@ManyToOne
	@JoinColumn(name = "idRegistroInternacao", nullable = false)
	private RegistroInternacao registroInternacao;
	
	@ManyToOne
	@JoinColumn(name = "idTipoPendencia", nullable = false)
	private TipoPendencia tipoPendencia;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInicio;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date previsaoConclusao;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataConclusao;
	
	@Column(length = 500)
	private String observacao;


	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getPrevisaoConclusao() {
		return previsaoConclusao;
	}

	public void setPrevisaoConclusao(Date previsaoConclusao) {
		this.previsaoConclusao = previsaoConclusao;
	}

	public Date getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

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

	public Long getIdPendenciaInternacao() {
		return idPendenciaInternacao;
	}

	
	
}
