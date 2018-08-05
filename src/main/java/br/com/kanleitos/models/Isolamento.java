package br.com.kanleitos.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import br.com.kanleitos.models.enums.TipoStatusLeito;

@Entity
public class Isolamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idIsolamento;

	@NotNull
	private String nome;
	private boolean inativo;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoStatusLeito statusLeito;

	public long getIdIsolamento() {
		return idIsolamento;
	}

	public void setIdIsolamento(long idIsolamento) {
		this.idIsolamento = idIsolamento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isInativo() {
		return inativo;
	}

	public void setInativo(boolean inativo) {
		this.inativo = inativo;
	}

	public TipoStatusLeito getStatusLeito() {
		return statusLeito;
	}

	public void setStatusLeito(TipoStatusLeito statusLeito) {
		this.statusLeito = statusLeito;
	}

	@Override
	public String toString() {
		return "Isolamento [id Isolamento=" + idIsolamento + ", nome=" + nome + ", inativo=" + inativo + "]";
	}

}
