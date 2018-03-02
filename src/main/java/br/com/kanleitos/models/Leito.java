package br.com.kanleitos.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import br.com.kanleitos.util.StatusLeitoConverter;
import br.com.kanleitos.util.TipoStatusLeito;

@Entity

public class Leito {

	@Id
	@SequenceGenerator(name = "LEITO_ID", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "LEITO_ID")
	private long idLeito;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idAla", nullable = false)
	private Ala ala;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idEnfermaria", nullable = false)
	private Enfermaria enfermaria;

	@Column(name = "nomeLeito", nullable = false)
	private String nomeLeito;

	@Column(name = "generoLeito", nullable = false)
	private String generoLeito;

	@Column(name = "tipoLeito", nullable = false)
	private String tipoLeito;

	@Column(name = "statusLeito", nullable = false)
	@Convert(converter = StatusLeitoConverter.class)
	private TipoStatusLeito statusLeito;

	public Leito() {
		setNomeLeito(null);
		setGeneroLeito(null);
		setTipoLeito(null);
		setStatusLeito(TipoStatusLeito.DESOCUPADO);
		setAla(new Ala());
		setEnfermaria(new Enfermaria());
	}

	public Ala getAla() {
		return ala;
	}

	public void setAla(Ala ala) {
		this.ala = ala;
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
