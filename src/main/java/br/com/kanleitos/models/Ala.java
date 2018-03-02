package br.com.kanleitos.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Ala {

	@Id
	@SequenceGenerator(name = "ALA_ID", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ALA_ID")
	private long idAla;

	@Column(name = "nomeAla", nullable = false)
	private String nomeAla;

	@Column(name = "inativa", nullable = false)
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

}
