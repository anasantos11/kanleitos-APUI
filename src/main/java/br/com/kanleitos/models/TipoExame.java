package br.com.kanleitos.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Entity
public class TipoExame {

	@Id
	@SequenceGenerator(name = "TIPO_EXAME_ID", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "TIPO_EXAME_ID")
	private long idExame;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "exameLista", referencedColumnName = "idExameLista")
	private ExameLista exameLista;

	@NotNull
	private String descExame;

	@NotNull
	private boolean inativo;

	public TipoExame() {
		setDescExame(null);
		setInativo(false);
	}

	public String getDescExame() {
		return descExame;
	}

	public void setDescExame(String descExame) {
		this.descExame = descExame;
	}

	public boolean getInativo() {
		return inativo;
	}

	public void setInativo(boolean inativo) {
		this.inativo = inativo;
	}

	public long getIdExame() {
		return idExame;
	}

	@Override
	public String toString() {
		return "TipoExame [idExame=" + idExame + ", exameLista=" + exameLista + ", descExame=" + descExame
				+ ", inativo=" + inativo + "]";
	}

}
