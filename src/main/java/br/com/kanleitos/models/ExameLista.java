package br.com.kanleitos.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class ExameLista {

	@Id
	@SequenceGenerator(name = "EXAME_LISTA_ID", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "EXAME_LISTA_ID")
	private long idExameLista;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "registroInternacao", referencedColumnName = "idRegistroInternacao")
	private RegistroInternacao registroInternacao;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "exameLista")
	private List<TipoExame> tiposExames;

	public ExameLista() {
		setRegistroInternacao(new RegistroInternacao());
	}

	public long getIdExameLista() {
		return idExameLista;
	}

	public RegistroInternacao getRegistroInternacao() {
		return registroInternacao;
	}

	public void setRegistroInternacao(RegistroInternacao registroInternacao) {
		this.registroInternacao = registroInternacao;
	}

	@Override
	public String toString() {
		return "ExameLista [idExameLista=" + idExameLista + ", registroInternacao=" + registroInternacao
				+ ", tiposExames=" + tiposExames + "]";
	}

}
