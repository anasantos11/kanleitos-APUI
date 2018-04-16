package br.com.kanleitos.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class TipoPendencia {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idPendencia;
	@NotNull
	private String nome;
	private boolean inativo;

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

	public int getIdPendencia() {
		return idPendencia;
	}

	@Override
	public String toString() {
		return "TipoPendencia [idPendencia=" + idPendencia + ", nome=" + nome + ", inativo=" + inativo + "]";
	}

}
