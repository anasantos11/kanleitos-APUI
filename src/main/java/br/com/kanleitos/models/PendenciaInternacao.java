package br.com.kanleitos.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class PendenciaInternacao implements Serializable {

	private static final long serialVersionUID = 3282951803951126808L;

	@EmbeddedId
	private PendenciaInternacaoId id;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInicio;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date previsaoConclusao;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataConclusao;
	
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PendenciaInternacaoId getId() {
		return id;
	}

	
	
}
