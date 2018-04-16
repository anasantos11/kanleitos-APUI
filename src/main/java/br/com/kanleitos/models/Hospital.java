package br.com.kanleitos.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Hospital {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_hospital;

	@NotNull
	private String nome;

	private String endereco;
	@Column(length = 20)
	private String telefone;
	private String especialidade;
	private boolean inativo;

	public int getId_hospital() {
		return id_hospital;
	}

	public void setId_hospital(int id_hospital) {
		this.id_hospital = id_hospital;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public boolean isInativo() {
		return inativo;
	}

	public void setInativo(boolean inativo) {
		this.inativo = inativo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "Hospital [id_hospital=" + id_hospital + ", nome=" + nome + ", endereco=" + endereco + ", telefone="
				+ telefone + ", especialidade=" + especialidade + ", inativo=" + inativo + "]";
	}

}
