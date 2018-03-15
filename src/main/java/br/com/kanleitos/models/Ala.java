package br.com.kanleitos.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Entity
public class Ala {

	@Id
	@SequenceGenerator(name = "ALA_ID", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ALA_ID")
	private long idAla;

	@NotNull
	private String nomeAla;

	@NotNull
	private boolean inativa;

	public Ala() {
		setNomeAla(null);
		setInativa(false);
	}

	public long getIdAla() {
		return idAla;
	}

	public String getNomeAla() {
		return nomeAla;
	}

	public void setNomeAla(String nomeAla) {
		this.nomeAla = nomeAla;
	}

	public boolean isInativa() {
		return inativa;
	}

	public void setInativa(boolean inativa) {
		this.inativa = inativa;
	}

	@Override
	public String toString() {
		return "Ala [idAla=" + idAla + ", nomeAla=" + nomeAla + ", inativa=" + inativa + "]";
	}

}
