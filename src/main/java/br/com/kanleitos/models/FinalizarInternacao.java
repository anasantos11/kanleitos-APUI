package br.com.kanleitos.models;

import java.util.Date;

import br.com.kanleitos.util.StatusRegistro;

public class FinalizarInternacao {

	private Date dataFinalizacao;
	private StatusRegistro status;
	private Long idRegistroInternacao;
	private Long idLeito;

	public Date getDataFinalizacao() {
		return dataFinalizacao;
	}

	public StatusRegistro getStatus() {
		return status;
	}

	public long getIdRegistroInternacao() {
		return idRegistroInternacao;
	}

	public Long getIdLeito() {
		return idLeito;
	}

	@Override
	public String toString() {
		return "FinalizarInternacao [dataFinalizacao=" + dataFinalizacao + ", status=" + status
				+ ", idRegistroInternacao=" + idRegistroInternacao + ", idLeito=" + idLeito + "]";
	}

}
