package br.com.kanleitos.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import br.com.kanleitos.util.Classificacao;
import br.com.kanleitos.util.ClassificacaoConverter;
import br.com.kanleitos.util.StatusRegistro;
import br.com.kanleitos.util.StatusRegistroConverter;

@Entity
public class RegistroInternacao {

	@Id
	@SequenceGenerator(name = "REGISTRO_ID", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "REGISTRO_ID")
	private long idRegistroInternacao;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idPedidoInternacao", nullable = false)
	private PedidoInternacao pedidoInternacao;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "registroInternacao")
	private List<ExameLista> exames;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "registroInternacao")
	private List<TipoPendencia> pendencias;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idEnfermaria", nullable = false)
	private Enfermaria enfermaria;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idLeito", nullable = false)
	private Leito leito;

	@Column(name = "dataInternacao", nullable = false)
	private String dataInternacao;

	@Column(name = "previsaoAlta", nullable = false)
	private String previsaoAlta;

	@Column(name = "dataAlta")
	private String dataAlta;

	@Column(name = "diasInternacao", nullable = false)
	private int diasInternacao;

	@Column(name = "classificacao", nullable = false)
	@Convert(converter = ClassificacaoConverter.class)
	private Classificacao classificacao;

	@Column(name = "statusRegistro", nullable = false)
	@Convert(converter = StatusRegistroConverter.class)
	private StatusRegistro statusRegistro;

	public RegistroInternacao() {
		setPedidoInternacao(new PedidoInternacao());
		setEnfermaria(new Enfermaria());
		setLeito(new Leito());
		setDataInternacao(null);
		setPrevisaoAlta(null);
		setDataAlta(null);
		setDiasInternacao(0);
		setClassificacao(Classificacao.VERDE);
		setStatusRegistro(StatusRegistro.EM_ANDAMENTO);

	}

	public PedidoInternacao getPedidoInternacao() {
		return pedidoInternacao;
	}

	public void setPedidoInternacao(PedidoInternacao pedidoInternacao) {
		this.pedidoInternacao = pedidoInternacao;
	}

	public List<TipoPendencia> getTipoPendencia() {
		return pendencias;
	}

	public void setTipoPendencia(TipoPendencia pendencia) {
		pendencias.add(pendencia);
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

	public String getDataAlta() {
		return dataAlta;
	}

	public void setDataAlta(String dataAlta) {
		this.dataAlta = dataAlta;
	}

	public int getDiasInternacao() {
		return diasInternacao;
	}

	public void setDiasInternacao(int diasInternacao) {
		this.diasInternacao = diasInternacao;
	}

	public Classificacao getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(Classificacao classificacao) {
		this.classificacao = classificacao;
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

}
