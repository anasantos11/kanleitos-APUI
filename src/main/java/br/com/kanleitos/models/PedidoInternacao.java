package br.com.kanleitos.models;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.com.kanleitos.util.StatusPedido;
import br.com.kanleitos.util.StatusPedidoConverter;

@Entity
public class PedidoInternacao {

	@Id
	@SequenceGenerator(name = "PEDIDO_ID", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PEDIDO_ID")
	private long idPedidoInternacao;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "numProntuario", nullable = false)
	private Paciente paciente;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idAla", nullable = false)
	private Ala ala;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idDiagnostico")
	private Diagnostico diagnostico;

	@Column(name = "medicoResponsavel")
	private String medicoResponsavel;

	@Column(name = "residenteResponsavel")
	private String residenteResponsavel;

	@Temporal(TemporalType.DATE)
	private Date dataAdmissao;

	@NotNull
	private String aih;

	@Temporal(TemporalType.DATE)
	private Date dataPedido;

	@Column(name = "statusPedido", nullable = false)
	@Convert(converter = StatusPedidoConverter.class)
	private StatusPedido statusPedido;

	public PedidoInternacao() {
		setAih(null);
		setAla(new Ala());
		setDataAdmissao(null);
		setDiagnostico(new Diagnostico());
		setMedicoResponsavel(null);
		setPaciente(new Paciente());
		setResidenteResponsavel(null);
		setDataPedido(new Date());
		setStatusPedido(StatusPedido.PENDENTE);

	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public void setAla(Ala ala) {
		this.ala = ala;
	}

	public void setDiagnostico(Diagnostico diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getMedicoResponsavel() {
		return medicoResponsavel;
	}

	public void setMedicoResponsavel(String medicoResponsavel) {
		this.medicoResponsavel = medicoResponsavel;
	}

	public String getResidenteResponsavel() {
		return residenteResponsavel;
	}

	public void setResidenteResponsavel(String residenteResponsavel) {
		this.residenteResponsavel = residenteResponsavel;
	}

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public String getAih() {
		return aih;
	}

	public void setAih(String aih) {
		this.aih = aih;
	}

	public long getIdPedidoInternacao() {
		return idPedidoInternacao;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public Ala getAla() {
		return ala;
	}

	public Diagnostico getDiagnostico() {
		return diagnostico;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public StatusPedido getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
	}

	@Override
	public String toString() {
		return "PedidoInternacao [idPedidoInternacao=" + idPedidoInternacao + ", paciente=" + paciente + ", ala=" + ala
				+ ", diagnostico=" + diagnostico + ", medicoResponsavel=" + medicoResponsavel
				+ ", residenteResponsavel=" + residenteResponsavel + ", dataAdmissao=" + dataAdmissao + ", aih=" + aih
				+ ", dataPedido=" + dataPedido + ", statusPedido=" + statusPedido + "]";
	}

}
