package com.classes.DAO;

import com.classes.DTO.ExemplarLivro;
import com.classes.DTO.Livro;
import com.classes.DTO.Usuario;
import com.classes.Conexao.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class LivroDAO {
	
	final String NOMEDATABELA = "livro";
	 public boolean inserir(Livro livro) {
	        try {
	            Connection conn = Conexao.conectar();
	            String sql = "INSERT INTO " + NOMEDATABELA + " (titulo,autor,editora,genero,ano) VALUES (?,?,?,?,?);";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setString(1, livro.getTitulo());
	            ps.setString(2, livro.getAutor());
	            ps.setString(3, livro.getEditora());
	            ps.setString(4, livro.getGenero());
	            ps.setInt(5,livro.getAno());
	            ps.executeUpdate();
	            ps.close();
	            conn.close();
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	 
	 
	 public boolean alterar(Livro livro) {
	        try {
	            Connection conn = Conexao.conectar();
	            String sql = "UPDATE " + NOMEDATABELA + " SET titulo = ?, autor = ?, editora = ?, genero = ?, ano = ? WHERE id = ?";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setString(1, livro.getTitulo());
	            ps.setString(2, livro.getAutor());
	            ps.setString(3, livro.getEditora());
	            ps.setString(4, livro.getGenero());
	            ps.setInt(5, livro.getAno());
	            ps.setInt(6,livro.getId());
	            //System.out.println(ps);
	            ps.executeUpdate();
	            ps.close();
	            conn.close();
	            return true;
	        } catch (Exception e) {
	        	 e.printStackTrace();
	             return false;
	        }
	    }
	 
	 public boolean excluir(Livro livro) {
	        try {
	            Connection conn = Conexao.conectar();
	            String sql = "DELETE FROM " + NOMEDATABELA + " WHERE id = ?;";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setInt(1, livro.getId());
	            ps.executeUpdate();
	            ps.close();
	            conn.close();
	            return true;
	        } catch (Exception e) {
	        	 e.printStackTrace();
	             return false;
	        }
	    }
	 
	 
	 public List<Livro> pesquisarTodos() {
	        try {
	            Connection conn = Conexao.conectar();
	            String sql = "SELECT * FROM " + NOMEDATABELA + ";";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ResultSet rs = ps.executeQuery();
	            List<Livro> listObj = montarLista(rs);
	            //System.out.println(ps);
	            return listObj;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	 
	    private List<Livro> montarLista(ResultSet rs) {
	        List<Livro> listObj = new ArrayList<Livro>();
	        try {
	            while (rs.next()) {
	            	Livro obj = new Livro(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6));                
	                listObj.add(obj);
	            }
	            return listObj;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	    
		  public void addLivro() {
			  	Scanner scanner = new Scanner(System.in);

			  	System.out.print("Digite o Título do Livro: ");
		        String nome = scanner.nextLine();
			  	
		        System.out.print("Digite o nome do Autor: ");
		        String autor = scanner.nextLine();

		        System.out.print("Digite a Editora: ");
		        String editora = scanner.nextLine();
		        
		        System.out.print("Digite a Genero: ");
		        String genero = scanner.nextLine();
		        
		        System.out.print("Digite o Ano de publicação: ");
		        int ano = scanner.nextInt();
		        
		        
		        Livro a = new Livro(0, nome, autor, editora, genero, ano);
		        System.out.println("");
		        System.out.println("Novo Usuario: "+ nome + " inserido com sucesso!");
		        inserir(a);    
		  }
		  
		  public void alterarLivro() {
			    Scanner scanner = new Scanner(System.in);
			    
			    System.out.print("Informe o ID do livro que você deseja editar: ");
			    int idLivro = Integer.parseInt(scanner.nextLine());
			    
			    Livro livro = procurarId(idLivro);
			    
			    if (livro != null) {
			        System.out.println("Informações atuais do Exemplar:");
			        System.out.println(livro.toString());
			        
			        System.out.println("Informe as novas informações do Exemplar:");
			        
			        System.out.print("Título: ");
			        livro.setTitulo(scanner.nextLine());
			        
			        System.out.print("Autor: ");
			        livro.setAutor(scanner.nextLine());
			        
			        System.out.print("Ano de Publicação: ");
			        livro.setAno(scanner.nextInt());
			        
			        System.out.print("Editora: ");
			        livro.setEditora(scanner.nextLine());
			  
			        System.out.println(livro);
			        if (alterar(livro)) {
			            System.out.println("Exemplar editado com sucesso!");
			        } else {
			            System.out.println("Não foi possível editar o exemplar. Verifique o ID informado.");
			        }
			    } else {
			        System.out.println("Exemplar não encontrado. Verifique o ID informado.");
			    }
			}
		  
		  public Livro procurarId(int id) {
		        try {
		            Connection conn = Conexao.conectar();
		            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE id = ?;";
		            PreparedStatement ps = conn.prepareStatement(sql);
		            ps.setInt(1, id);
		            ResultSet rs = ps.executeQuery();
		            if (rs.next()) {
		                Livro obj = new Livro(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6));
		                
		                //obj.setDescricao(rs.getString(2));
		                ps.close();
		                rs.close();
		                conn.close();
		                return obj;
		            } else {
		                ps.close();
		                rs.close();
		                conn.close();
		                return null;
		            }
		        } catch (Exception e) {
		        	 e.printStackTrace();
		             return null;
		        }
		    }
}
