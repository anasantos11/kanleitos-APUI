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
public class ObservacaoInternacao {

	@Id
	@SequenceGenerator(name = "OBSERVACAO_INTERNACAO_ID", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "OBSERVACAO_INTERNACAO_ID")
	private Long idObservacaoInternacao;
	
	@ManyToOne
	@JoinColumn(name = "idRegistroInternacao", nullable = false)
	private RegistroInternacao registroInternacao;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@NotNull
	@Column(length = 500)
	private String descricao;

	public RegistroInternacao getRegistroInternacao() {
		return registroInternacao;
	}

	public void setRegistroInternacao(RegistroInternacao registroInternacao) {
		this.registroInternacao = registroInternacao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getIdObservacaoInternacao() {
		return idObservacaoInternacao;
	}
	
	
	
}
