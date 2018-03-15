package br.com.kanleitos.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Entity

public class Enfermaria {

	@Id
	@SequenceGenerator(name = "ENF_ID", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ENF_ID")
	private long idEnfermaria;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idAla", nullable = false)
	private Ala ala;

	@NotNull
	private String nomeEnfermaria;

	@Column(name = "generoEnfermaria")
	private String generoEnfermaria;

	@NotNull
	private boolean inativa;

	public Enfermaria() {
		setNomeEnfermaria(null);
		setGenero(null);
		setAla(new Ala());
		setInativa(false);
	}

	public Ala getAla() {
		return ala;
	}

	public void setAla(Ala ala) {
		this.ala = ala;
	}

	public String getNomeEnfermaria() {
		return nomeEnfermaria;
	}

	public void setNomeEnfermaria(String nomeEnfermaria) {
		this.nomeEnfermaria = nomeEnfermaria;
	}

	public String getGenero() {
		return generoEnfermaria;
	}

	public void setGenero(String generoEnfermaria) {
		this.generoEnfermaria = generoEnfermaria;
	}

	public boolean isInativa() {
		return inativa;
	}

	public void setInativa(boolean inativa) {
		this.inativa = inativa;
	}

	public long getIdEnfermaria() {
		return idEnfermaria;
	}

	@Override
	public String toString() {
		return "Enfermaria [idEnfermaria=" + idEnfermaria + ", ala=" + ala + ", nomeEnfermaria=" + nomeEnfermaria
				+ ", generoEnfermaria=" + generoEnfermaria + ", inativa=" + inativa + "]";
	}

}
