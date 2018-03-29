package br.com.kanleitos.models;

import java.util.Date;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.com.kanleitos.util.StatusRegistro;

@Entity
@SqlResultSetMapping(name = "findAllPacientesbyEnfermaria", classes = @ConstructorResult(targetClass = Paciente.class, columns = {
		@ColumnResult(name = "num_prontuario"), @ColumnResult(name = "data_nascimento"), @ColumnResult(name = "genero"),
		@ColumnResult(name = "idade"), @ColumnResult(name = "nome_mae"), @ColumnResult(name = "nome_paciente")

}))
@NamedNativeQuery(name = "findAllPacientesbyEnfermaria", query = "SELECT paciente.* FROM registro_internacao "
		+ "JOIN pedido_internacao ON registro_internacao.id_pedido_internacao = pedido_internacao.id_pedido_internacao "
		+ "JOIN paciente ON pedido_internacao.id_paciente = paciente.num_prontuario "
		+ "WHERE registro_internacao.id_enfermaria  = :id_enfermaria AND registro_internacao.status_registro = 'EM_ANDAMENTO'")
public class RegistroInternacao {

	@Id
	@SequenceGenerator(name = "REGISTRO_ID", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "REGISTRO_ID")
	private long idRegistroInternacao;

	@OneToOne
	@JoinColumn(name = "idPedidoInternacao", nullable = false)
	private PedidoInternacao pedidoInternacao;

	@ManyToOne
	@JoinColumn(name = "idEnfermaria", nullable = false)
	private Enfermaria enfermaria;

	@ManyToOne
	@JoinColumn(name = "idLeito", nullable = false)
	private Leito leito;

	@NotNull
	private String dataInternacao;

	@NotNull
	private String previsaoAlta;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAlta;

	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusRegistro statusRegistro;

	public RegistroInternacao() {
		setPedidoInternacao(new PedidoInternacao());
		setEnfermaria(new Enfermaria());
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

	public Enfermaria getEnfermaria() {
		return enfermaria;
	}

	public void setEnfermaria(Enfermaria enfermaria) {
		this.enfermaria = enfermaria;
	}

	public Leito getLeito() {
		return leito;
	}

	public void setLeito(Leito leito) {
		this.leito = leito;
	}

	public String getDataInternacao() {
		return dataInternacao;
	}

	public void setDataInternacao(String dataInternacao) {
		this.dataInternacao = dataInternacao;
	}

	public String getPrevisaoAlta() {
		return previsaoAlta;
	}

	public void setPrevisaoAlta(String previsaoAlta) {
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

	@Override
	public String toString() {
		return "RegistroInternacao [idRegistroInternacao=" + idRegistroInternacao + ", pedidoInternacao=" + pedidoInternacao
				+ ", enfermaria=" + enfermaria + ", leito=" + leito + ", dataInternacao=" + dataInternacao
				+ ", previsaoAlta=" + previsaoAlta + ", dataAlta=" + dataAlta + ", statusRegistro=" + statusRegistro
				+ "]";
	}

}
