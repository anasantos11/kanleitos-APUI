package br.com.kanleitos.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Paciente {
	@Id
	@Column(name = "numProntuario", nullable = false)
	private long numProntuario;

	@Column(name = "nomePaciente", nullable = false)
	private String nomePaciente;
	@Column(name = "idade", nullable = false)
	private int idade;
	@Column(name = "genero", nullable = false)
	private String genero;
	@Column(name = "nomeMae", nullable = false)
	private String nomeMae;
	@Column(name = "dataNascimento", nullable = false)
	private String dataNascimento;

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

	public String getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Override
	public String toString() {
		return "Paciente [numProntuario=" + numProntuario + ", nomePaciente=" + nomePaciente + ", idade=" + idade
				+ ", genero=" + genero + ", nomeMae=" + nomeMae + ", dataNascimento=" + dataNascimento + "]";
	}
}
