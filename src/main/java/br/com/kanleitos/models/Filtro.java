package br.com.kanleitos.models;

import java.util.Date;

public class Filtro {
	private long idAla;
	private long idEnfermaria;
	private long idLeito;
	private long idMedico;
	private long idResidente;
	private long idIsolamento;
	private String nomePaciente;
	private long numProntuario;
	private Date dataAdmissao;
	private Date previsaoAlta;
	private String status;
	
	public long getIdEnfermaria() {
		return idEnfermaria;
	}

	public void setIdEnfermaria(long idEnfermaria) {
		this.idEnfermaria = idEnfermaria;
	}

	public long getIdLeito() {
		return idLeito;
	}

	public void setIdLeito(long idLeito) {
		this.idLeito = idLeito;
	}

	public Date getPrevisaoAlta() {
		return previsaoAlta;
	}

	public void setPrevisaoAlta(Date previsaoAlta) {
		this.previsaoAlta = previsaoAlta;
	}

	public long getIdAla() {
		return idAla;
	}

	public void setIdAla(long idAla) {
		this.idAla = idAla;
	}

	public long getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(long idMedico) {
		this.idMedico = idMedico;
	}

	public long getIdResidente() {
		return idResidente;
	}

	public void setIdResidente(long idResidente) {
		this.idResidente = idResidente;
	}

	public long getIdIsolamento() {
		return idIsolamento;
	}

	public void setIdIsolamento(long idIsolamento) {
		this.idIsolamento = idIsolamento;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public long getNumProntuario() {
		return numProntuario;
	}

	public void setNumProntuario(long numProntuario) {
		this.numProntuario = numProntuario;
	}

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
