package br.com.kanleitos.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Isolamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idIsolamento;

	@NotNull
	private String nome;
	private boolean inativo;

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

	@Override
	public String toString() {
		return "Isolamento [id Isolamento=" + idIsolamento + ", nome=" + nome + ", inativo=" + inativo + "]";
	}

}
