package com.classes.DTO;

public class Livro {
	private int id;
	private String titulo;
	private String autor;
	private String editora;
	private String genero;
	private int ano;
	
	
	public Livro(int id, String titulo, String autor, String editora, String genero, int ano) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.editora = editora;
		this.genero = genero;
		this.ano = ano;
	}
	
	public Livro() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getEditora() {
		return editora;
	}
	public void setEditora(String editora) {
		this.editora = editora;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Livro [id=");
		builder.append(id);
		builder.append(", titulo=");
		builder.append(titulo);
		builder.append(", autor=");
		builder.append(autor);
		builder.append(", editora=");
		builder.append(editora);
		builder.append(", genero=");
		builder.append(genero);
		builder.append(", ano=");
		builder.append(ano);
		builder.append("]");
		return builder.toString();
	}
}
