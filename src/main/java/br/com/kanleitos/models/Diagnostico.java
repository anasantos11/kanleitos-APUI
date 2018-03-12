package br.com.kanleitos.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Entity
public class Diagnostico {

	@Id
	@SequenceGenerator(name = "DIAG_ID", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "DIAG_ID")
	private long idDiagnostico;

	@Column(name = "descricaoDiagnostico", nullable = false)
	private String descricaoDiagnostico;

	@NotNull
	private String cid;

	@Column(name = "tempoPermanencia", nullable = false)
	private int tempoPermanencia;

	public Diagnostico() {
		setCid("0000");
		setDescricaoDiagnostico(null);
		setTempoPermanencia(-1);
	}

	public String getDescricaoDiagnostico() {
		return descricaoDiagnostico;
	}

	public void setDescricaoDiagnostico(String descricaoDiagnostico) {
		this.descricaoDiagnostico = descricaoDiagnostico;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cID) {
		this.cid = cID;
	}

	public int getTempoPermanencia() {
		return tempoPermanencia;
	}

	public void setTempoPermanencia(int tempoPermanencia) {
		this.tempoPermanencia = tempoPermanencia;
	}

	public long getIdDiagnostico() {
		return idDiagnostico;
	}

}
