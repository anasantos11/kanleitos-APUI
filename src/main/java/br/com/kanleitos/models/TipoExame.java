package br.com.kanleitos.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class TipoExame {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tipoExameId;
	@NotNull
	@Column(length = 70)
	private String nome;
	private boolean inativo;

	public int getTipoExameId() {
		return tipoExameId;
	}

	public void setTipoExameId(int tipoExameId) {
		this.tipoExameId = tipoExameId;
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
		return "TipoExame [tipoExameId=" + tipoExameId + ", nome=" + nome + ", inativo=" + inativo + "]";
	}

}
