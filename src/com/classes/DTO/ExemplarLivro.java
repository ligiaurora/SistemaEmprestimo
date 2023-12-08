package com.classes.DTO;



import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ExemplarLivro extends Livro {
	
	private int id;
	private boolean disponivel;
	private int numExemplar;
	private int livro_id;
	
	/*public ExemplarLivro(int id, String titulo, String autor, String editora, String genero, int ano, int numExemplar, boolean disponivel) {
		super(id, titulo, autor, editora, genero, ano);
		setDisponivel(disponivel);
		setNumExemplar(numExemplar);
	}*/
	
	
	public ExemplarLivro(int id, String titulo, String autor, String editora, String genero, int ano, int id2,
			boolean disponivel, int numExemplar, int livro_id) {
		super(id, titulo, autor, editora, genero, ano);
		this.id = id2;
		this.disponivel = disponivel;
		this.numExemplar = numExemplar;
		this.livro_id = livro_id;
	}
	
	

	//criar um construtor só com o que eu preciso do exemplar
	public ExemplarLivro(int id, boolean disponivel, int numExemplar, int livro_id) {	
		this.id = id;
		this.disponivel = disponivel;
		this.numExemplar = numExemplar;
		this.livro_id = livro_id;
	}

	//Get and Setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public int getNumExemplar() {
		return numExemplar;
	}


	public void setNumExemplar(int numExemplar) {
		if (numExemplar > 0) {
            this.numExemplar = numExemplar;
        }
	}

	public int getLivro_id() {
		return livro_id;
	}


	public void setLivro_id(int livro_id) {
		this.livro_id = livro_id;
	}
	
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExemplarLivro [Disponivel=");
		builder.append(disponivel);
		builder.append(", Id Exemplar = ");
		builder.append(getId());
		//builder.append(", Quantidade Exemplares = ");
		//builder.append(numExemplar);
		//builder.append(", Disponivel = ");
		//builder.append(isDisponivel());
		builder.append(", getNumExemplar = ");
		builder.append(getNumExemplar());
		builder.append(", Titulo = ");
		builder.append(getTitulo());
		builder.append(", Autor = ");
		builder.append(getAutor());
		builder.append(", Editora = ");
		builder.append(getEditora());
		builder.append(", Genero = ");
		builder.append(getGenero());
		builder.append(", Ano de Lançamento = ");
		builder.append(getAno());
		builder.append(", toString()= ");
		builder.append(super.toString());
		//builder.append(", getClass()=");
		//builder.append(getClass());
		//builder.append(", hashCode()=");
		builder.append(hashCode());
		builder.append("]");
		return builder.toString();
	}	
}
