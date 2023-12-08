package com.classes.DTO;
import java.time.LocalDateTime;
import java.util.List;


public class Atendente extends Pessoa {

	public Atendente(int id, String nome, String nomeUsuario, String senha, boolean estadoConta) {
		super(id, nome, nomeUsuario, senha, estadoConta);
	}
	


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Atendente: [Id = ");
		builder.append(getId());
		builder.append(" Nome = ");
		builder.append(getNome());
		builder.append(" Nome de Usuário = ");
		builder.append(getNomeUsuario());
		builder.append(" Senha = ");
		builder.append(getSenha());
		builder.append(" Ativo = ");
		builder.append(getEstadoConta());
		builder.append(", toString()=");
		builder.append(super.toString());
		//builder.append(", getClass()=");
		//builder.append(getClass());
		//builder.append(", hashCode()=");
		//builder.append(hashCode());
		builder.append("]");
		return builder.toString();
	}  
}
