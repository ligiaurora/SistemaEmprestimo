package com.classes.DTO;
import java.time.LocalDateTime;

public class Emprestimo {
	private int id;
	private LocalDateTime dataEmprestimo;
	private LocalDateTime dataEntrega;
	private double valorMulta;
	private ExemplarLivro exemplar_id;
	private Pessoa usuario_id;
	
	
	//criar um construtor só com o que eu preciso do emprestimo
	public Emprestimo(int id,LocalDateTime dataEmprestimo,LocalDateTime dataEntrega,double valorMulta,Pessoa usuario_id, ExemplarLivro exemplar_id ) {
		this.id= id;
		this.dataEmprestimo = dataEmprestimo;
		this.dataEntrega = dataEntrega;
		this.valorMulta = valorMulta;
		this.exemplar_id = exemplar_id;
		this.usuario_id = usuario_id;
	}

	public Emprestimo(int id, String nome, String nomeUsuario, String senha, boolean estadoConta) {
		
	}

	//Get e Setters
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public LocalDateTime getDataEmprestimo() {
		return dataEmprestimo;
	}


	public void setDataEmprestimo(LocalDateTime dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}


	public LocalDateTime getDataEntrega() {
		return dataEntrega;
	}


	public void setDataEntrega(LocalDateTime dataEntrega) {
		this.dataEntrega = dataEntrega;
	}


	public double getValorMulta() {
		return valorMulta;
	}


	public void setValorMulta(double valorMulta) {
		this.valorMulta = valorMulta;
	}


	public ExemplarLivro getExemplar_id() {
		return exemplar_id;
	}


	public void setExemplar_id(ExemplarLivro exemplar_id) {
		this.exemplar_id = exemplar_id;
	}


	public Pessoa getUsuario_id() {
		return usuario_id;
	}


	public void setUsuario_id(Pessoa usuario_id) {
		this.usuario_id = usuario_id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Emprestimo [id=");
		builder.append(id);
		builder.append(", dataEmprestimo=");
		builder.append(dataEmprestimo);
		builder.append(", dataEntrega=");
		builder.append(dataEntrega);
		builder.append(", valorMulta=");
		builder.append(valorMulta);
		builder.append(", exemplar");
		builder.append(exemplar_id);
		builder.append(", usuario=");
		builder.append(usuario_id);
		builder.append("]");
		return builder.toString();
	}
}
