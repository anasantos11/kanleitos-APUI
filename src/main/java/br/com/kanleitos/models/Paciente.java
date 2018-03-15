package br.com.kanleitos.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Paciente {
	@Id
	@NotNull
	private long numProntuario;

	@NotNull
	private String nomePaciente;

	@NotNull
	private int idade;

	@NotNull
	private String genero;

	@NotNull
	private String nomeMae;

	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	public Paciente() {
		setDataNascimento(null);
		setGenero(null);
		setIdade(-1);
		setNomeMae(null);
		setNomePaciente(null);
		setNumProntuario(-1);
	}

	public long getNumProntuario() {
		return this.numProntuario;
	}

	public void setNumProntuario(long numProntuario) {
		this.numProntuario = numProntuario;
	}

	public String getNomePaciente() {
		return this.nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public int getIdade() {
		return this.idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getNomeMae() {
		return this.nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Override
	public String toString() {
		return "Paciente [numProntuario=" + numProntuario + ", nomePaciente=" + nomePaciente + ", idade=" + idade
				+ ", genero=" + genero + ", nomeMae=" + nomeMae + ", dataNascimento=" + dataNascimento + "]";
	}
}
