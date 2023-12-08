package com.classes.DTO;

import java.util.ArrayList;
import java.util.List;

public class Usuario extends Pessoa {
	
	private boolean atendente;
	
	 //private List<Emprestimo> emprestimos;

	 public Usuario(int id, String nome, String nomeUsuario, String senha, boolean estadoConta, boolean atendente) {
		    super(id, nome, nomeUsuario, senha, estadoConta);
		    //this.emprestimos = new ArrayList<>(); 
		    this.atendente = atendente; 
	}
	 
	 //Get e Setters
	 public boolean isAtendente() {
		   return atendente;
	   }

	   public void setAtendente(boolean atendente) {
		this.atendente = atendente;
	   }

   public boolean loginUsuario(String nomeUsuario, String senha) {
       return this.getNomeUsuario().equals(nomeUsuario) && this.getSenha().equals(senha);
   }

@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Usuario: ");
	builder.append(" [Usuario_id = ");
	builder.append(id);
	builder.append(" Nome = ");
	builder.append(nome);
	builder.append(" Nome de Usuário = ");
	builder.append(nomeUsuario);
	builder.append(" Estado da Conta = ");
	builder.append(estadoConta);
	//builder.append(", emprestimos=");
	//builder.append(emprestimos);
	builder.append("]");
	return builder.toString();
}


}
