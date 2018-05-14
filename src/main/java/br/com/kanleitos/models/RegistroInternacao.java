package br.com.kanleitos.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.com.kanleitos.models.enums.StatusRegistro;

@Entity
public class RegistroInternacao {

	@Id
	@SequenceGenerator(name = "REGISTRO_ID", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "REGISTRO_ID")
	private long idRegistroInternacao;

	@OneToOne
	@JoinColumn(name = "idPedidoInternacao", nullable = false)
	private PedidoInternacao pedidoInternacao;

	@ManyToOne
	@JoinColumn(name = "id_hospital", nullable = true)
	private Hospital hospital;

	@ManyToOne
	@JoinColumn(name = "idLeito", nullable = false)
	private Leito leito;

	@NotNull
	private Date dataInternacao;

	@NotNull
	private Date previsaoAlta;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAlta;

	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusRegistro statusRegistro;

	public RegistroInternacao() {
		setPedidoInternacao(new PedidoInternacao());
		setLeito(new Leito());
		setDataInternacao(null);
		setPrevisaoAlta(null);
		setDataAlta(null);
		setStatusRegistro(StatusRegistro.EM_ANDAMENTO);

	}

	public PedidoInternacao getPedidoInternacao() {
		return pedidoInternacao;
	}

	public void setPedidoInternacao(PedidoInternacao pedidoInternacao) {
		this.pedidoInternacao = pedidoInternacao;
	}

	public Leito getLeito() {
		return leito;
	}

	public void setLeito(Leito leito) {
		this.leito = leito;
	}

	public Date getDataInternacao() {
		return dataInternacao;
	}

	public void setDataInternacao(Date dataInternacao) {
		this.dataInternacao = dataInternacao;
	}

	public Date getPrevisaoAlta() {
		return previsaoAlta;
	}

	public void setPrevisaoAlta(Date previsaoAlta) {
		this.previsaoAlta = previsaoAlta;
	}

	public Date getDataAlta() {
		return dataAlta;
	}

	public void setDataAlta(Date dataAlta) {
		this.dataAlta = dataAlta;
	}

	public long getIdRegistroInternacao() {
		return idRegistroInternacao;
	}

	public StatusRegistro getStatusRegistro() {
		return statusRegistro;
	}

	public void setStatusRegistro(StatusRegistro statusRegistro) {
		this.statusRegistro = statusRegistro;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	@Override
	public String toString() {
		return "RegistroInternacao [idRegistroInternacao=" + idRegistroInternacao + ", pedidoInternacao="
				+ pedidoInternacao + ", hospital=" + hospital + ", leito=" + leito + ", dataInternacao="
				+ dataInternacao + ", previsaoAlta=" + previsaoAlta + ", dataAlta=" + dataAlta + ", statusRegistro="
				+ statusRegistro + "]";
	}

}
