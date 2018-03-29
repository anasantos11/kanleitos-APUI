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
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.com.kanleitos.util.StatusPedido;

@Entity
public class PedidoInternacao {

	@Id
	@SequenceGenerator(name = "PEDIDO_ID", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PEDIDO_ID")
	private long idPedidoInternacao;

	@ManyToOne
	@JoinColumn(name = "idPaciente", nullable = false)
	private Paciente paciente;

	@ManyToOne
	@JoinColumn(name = "idAla", nullable = false)
	private Ala ala;

	@ManyToOne
	@JoinColumn(name = "idDiagnostico", nullable = false)
	private Diagnostico diagnostico;

	private String medicoResponsavel;

	private String residenteResponsavel;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date dataAdmissao;

	@NotNull
	private String aih;

	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusPedido statusPedido;

	@ManyToOne
	@JoinColumn(name = "idIsolamento", nullable = true)
	private Isolamento isolamento;

	public PedidoInternacao() {
		setAih(null);
		setAla(new Ala());
		setDataAdmissao(null);
		setDiagnostico(new Diagnostico());
		setMedicoResponsavel(null);
		setPaciente(new Paciente());
		setResidenteResponsavel(null);
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

	public StatusPedido getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
	}

	public Isolamento getIsolamento() {
		return isolamento;
	}

	public void setIsolamento(Isolamento isolamento) {
		this.isolamento = isolamento;
	}

	public void setIdPedidoInternacao(long idPedidoInternacao) {
		this.idPedidoInternacao = idPedidoInternacao;
	}

	@Override
	public String toString() {
		return "PedidoInternacao [idPedidoInternacao=" + idPedidoInternacao + ", paciente=" + paciente + ", ala=" + ala
				+ ", diagnostico=" + diagnostico + ", medicoResponsavel=" + medicoResponsavel
				+ ", residenteResponsavel=" + residenteResponsavel + ", dataAdmissao=" + dataAdmissao + ", aih=" + aih
				+ ", statusPedido=" + statusPedido + ", isolamento=" + isolamento + "]";
	}

}
