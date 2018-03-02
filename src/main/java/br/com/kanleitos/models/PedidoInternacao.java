package br.com.kanleitos.models;

import java.time.LocalDate;

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

	@Column(name = "dataAdmissao", nullable = false)
	private String dataAdmissao;

	@Column(name = "AIH", nullable = false)
	private String AIH;

	@Column(name = "dataPedido", nullable = false)
	private String dataPedido;

	@Column(name = "statusPedido", nullable = false)
	@Convert(converter = StatusPedidoConverter.class)
	private StatusPedido statusPedido;

	public PedidoInternacao() {
		setAIH(null);
		setAla(new Ala());
		setDataAdmissao(null);
		setDiagnostico(new Diagnostico());
		setMedicoResponsavel(null);
		setPaciente(new Paciente());
		setResidenteResponsavel(null);
		setDataPedido(LocalDate.now().toString());
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

	public String getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(String dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public String getAIH() {
		return AIH;
	}

	public void setAIH(String aIH) {
		AIH = aIH;
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

	public String getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(String dataPedido) {
		this.dataPedido = dataPedido;
	}

	public StatusPedido getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
	}

}