package com.classes.DTO;

import java.util.ArrayList;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Pessoa {
	
    private static List<Pessoa> pessoas = new ArrayList<>();

	protected int id;
	protected String nome;
	protected String nomeUsuario;
	protected String senha;
	protected boolean estadoConta;
	
	
	//construtor
	public Pessoa (int id,String nome, String nomeUsuario, String senha, boolean estadoConta){
			setId(id);
			setNome(nome);
			setNomeUsuario(nomeUsuario);
			setSenha(senha); 
			setEstadoConta(estadoConta);
	}
	
	//Get e Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
	    // Criptografa a senha antes de armazená-la
	    String senhaCriptografada = criptografarSenha(senha);
	    this.senha = senhaCriptografada;
	}

	public boolean getEstadoConta() {
		return estadoConta;
	}
	public void setEstadoConta(boolean estadoConta) {
		this.estadoConta = estadoConta;
	}
	
    // método para adicionar Atendente 
    public static void adicionarAtendente(Atendente bibliotecario) {
        pessoas.add(bibliotecario);
    }
    
    //metodo para adicionar Usuario
    public static void adicionarUsuario(Usuario aluno) {
        pessoas.add(aluno);
    }
    
    
    //Metodo para remoção
    
    public static void removerAtendente(Atendente bibliotecario) {
        pessoas.remove(bibliotecario);
    }
    
    public static void removerUsuario(Usuario aluno) {
        pessoas.remove(aluno);
    }
    
 // método para buscar  ID
    public static Atendente buscarAtendentePorId(int id) {
        for (Pessoa pessoa : pessoas) {
            if (pessoa instanceof Atendente && pessoa.getId() == id) {
                return (Atendente) pessoa;
            }
        }
        return null;
    }

    
    public static Usuario buscarUsuarioPorId(int id) {
        for (Pessoa pessoa : pessoas) {
            if (pessoa instanceof Usuario && pessoa.getId() == id) {
                return (Usuario) pessoa;
            }
        }
        return null;
    }
    
    
    public static String criptografarSenha(String senha) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(senha.getBytes(StandardCharsets.UTF_8));

            // Converte o array de bytes para uma representação hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // Lida com a exceção de algoritmo não encontrado
            return null;
        }
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pessoa [id=");
		builder.append(id);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", nomeUsuario=");
		builder.append(nomeUsuario);
		builder.append(", senha=");
		builder.append(senha);
		builder.append(", estadoConta=");
		builder.append(estadoConta);
		builder.append("]");
		return builder.toString();
	}
}
