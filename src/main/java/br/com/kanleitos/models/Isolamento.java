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
	private long id_Isolamento;

	@NotNull
	private String nome;
	private boolean inativo;

	public long getId_Isolamento() {
		return id_Isolamento;
	}

	public void setId_Isolamento(long id_Isolamento) {
		this.id_Isolamento = id_Isolamento;
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
		return "Isolamento [id_Isolamento=" + id_Isolamento + ", nome=" + nome + ", inativo=" + inativo + "]";
	}

}
