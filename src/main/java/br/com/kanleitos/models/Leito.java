package br.com.kanleitos.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import br.com.kanleitos.util.TipoStatusLeito;

@Entity

public class Leito {

	@Id
	@SequenceGenerator(name = "LEITO_ID", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "LEITO_ID")
	private long idLeito;

	@ManyToOne
	@JoinColumn(name = "idEnfermaria", nullable = false)
	private Enfermaria enfermaria;

	@NotNull
	private String nomeLeito;

	@NotNull
	private String generoLeito;

	@NotNull
	private String tipoLeito;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoStatusLeito statusLeito;
	
	public Leito() {
		setNomeLeito(null);
		setGeneroLeito(null);
		setTipoLeito(null);
		setStatusLeito(TipoStatusLeito.DESOCUPADO);
		setEnfermaria(new Enfermaria());
	}


	public Enfermaria getEnfermaria() {
		return enfermaria;
	}

	public void setEnfermaria(Enfermaria enfermaria) {
		this.enfermaria = enfermaria;
	}

	public String getNomeLeito() {
		return nomeLeito;
	}

	public void setNomeLeito(String nomeLeito) {
		this.nomeLeito = nomeLeito;
	}

	public String getGeneroLeito() {
		return generoLeito;
	}

	public void setGeneroLeito(String generoLeito) {
		this.generoLeito = generoLeito;
	}

	public String getTipoLeito() {
		return tipoLeito;
	}

	public void setTipoLeito(String tipoLeito) {
		this.tipoLeito = tipoLeito;
	}

	public TipoStatusLeito getStatusLeito() {
		return statusLeito;
	}

	public void setStatusLeito(TipoStatusLeito statusLeito) {
		this.statusLeito = statusLeito;
	}

	public long getIdLeito() {
		return idLeito;
	}
	

}
