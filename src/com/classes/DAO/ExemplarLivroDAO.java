package com.classes.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.classes.Conexao.Conexao;
import com.classes.DTO.*;

public class ExemplarLivroDAO {
	
	final String NOMEDATABELA = "exemplarlivro";
	 public boolean inserir(ExemplarLivro exemplarLivro) {
	        try {
	            Connection conn = Conexao.conectar();
	            String sql = "INSERT INTO " + NOMEDATABELA + " (disponivel,numExemplar,livro_id) VALUES (?,?,?);";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setBoolean(1, exemplarLivro.isDisponivel());
	            ps.setInt(2, exemplarLivro.getNumExemplar());
	            ps.setInt(3, exemplarLivro.getLivro_id());
	            ps.executeUpdate();
	            ps.close();
	            conn.close();
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	 
	 
	 public boolean alterar(ExemplarLivro exemplarLivro) {
	        try {
	            Connection conn = Conexao.conectar();
	            String sql = "UPDATE " + NOMEDATABELA + " SET disponivel = ?, numExemplar = ?, livro_id = ? WHERE id = ?";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setBoolean(1, exemplarLivro.isDisponivel());
	            ps.setInt(2, exemplarLivro.getNumExemplar());
	            ps.setInt(3, exemplarLivro.getLivro_id());
	            ps.setInt(4, exemplarLivro.getId());
	            ps.executeUpdate();
	            ps.close();
	            conn.close();
	            return true;
	        } catch (Exception e) {
	        	 e.printStackTrace();
	             return false;
	        }
	    }
	 
	 public boolean excluir(ExemplarLivro exemplarLivro) {
	        try {
	            Connection conn = Conexao.conectar();
	            String sql = "DELETE FROM " + NOMEDATABELA + " WHERE id = ?;";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setInt(1, exemplarLivro.getId());
	            ps.executeUpdate();
	            ps.close();
	            conn.close();
	            return true;
	        } catch (Exception e) {
	        	 e.printStackTrace();
	             return false;
	        }
	    }
	 
	 
	 public List<ExemplarLivro> pesquisarTodos() {
	        try {
	            Connection conn = Conexao.conectar();
	            String sql = "SELECT "
	            		+ "el.id, l.titulo, l.autor, l.editora,"
	            		+ "l.genero, l.ano, l.id, el.disponivel,"
	            		+ "el.numExemplar, el.livro_id "
	            		+ "FROM " + NOMEDATABELA + " el "
	            		+ "left join livro l"
	            		+ "	on el.livro_id = l.id;";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ResultSet rs = ps.executeQuery();
	            List<ExemplarLivro> listObj = montarLista(rs);
	            return listObj;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	 
	    private List<ExemplarLivro> montarLista(ResultSet rs) {
	        List<ExemplarLivro> listObj = new ArrayList<ExemplarLivro>();
	        try {
	            while (rs.next()) {
	            	ExemplarLivro obj = new ExemplarLivro(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
	            			rs.getInt(6), rs.getInt(7), rs.getBoolean(8), rs.getInt(9), rs.getInt(10));                
	                listObj.add(obj);
	            }
	            return listObj;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	    
	    public ExemplarLivro procurarPorCodigo(int cod) {
	        try {
	            Connection conn = Conexao.conectar();
	            String sql = "SELECT "
	            		+ "el.id, l.titulo, l.autor, l.editora,"
	            		+ "l.genero, l.ano, el.id, el.disponivel,"
	            		+ "el.numExemplar, el.livro_id "
	            		+ "FROM " + NOMEDATABELA + " el "
	            		+ "left join livro l"
	            		+ "	on el.livro_id = l.id "
	            		+ "WHERE el.id = ?;";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setInt(1, cod);
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	            	ExemplarLivro obj = new ExemplarLivro(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
	            			rs.getInt(6), rs.getInt(1), rs.getBoolean(8), rs.getInt(9), rs.getInt(10));                
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
	    
	    
		  public void editarExemplar() {
			  int opcao;  
			  do {
		            System.out.println("");
		            System.out.println("------------------------------------");
		            System.out.println("Adicionar Exemplar");
		            System.out.println("------------------------------------");
		            System.out.println("Opções de Exemplar:");
		            System.out.println("1 - Adicionar ou Remover Quantidade");
		            System.out.println("2 - Cadastrar novo Livro");
		            System.out.println("3 - Cadastrar novo Exemplar");
		            System.out.println("4 - Alterar Informações de Exemplar(Que já está cadastrado no sistema)");
		            System.out.println("5 - Voltar");

		            opcao = Integer.parseInt(new Scanner(System.in).nextLine());
		            Scanner scanner = new Scanner(System.in);
		            
		            switch (opcao) {
	            	case 1:
	            		System.out.print("Informe o ID do Exemplar: ");
	    		        int idExemplar = scanner.nextInt();
	    		        
	    		        System.out.print("Informe a nova quantidade disponivel: ");
	    		        int quantidade = scanner.nextInt();
	    		        
	    		        ExemplarLivro elivro = procurarPorCodigo(idExemplar);
	    		        elivro.setNumExemplar(quantidade);
	    		        
	                	alterar(elivro);
	    		        break;
	            	case 2:
	            		LivroDAO a = new LivroDAO();
	            		a.addLivro();
	            		break;
	            	case 3:
	            		addExemplar();
	            		break;
	            	case 4:
	            		LivroDAO alterar = new LivroDAO();
	            		alterar.alterarLivro();
	            		break;
	            }
	                       
	        } while (opcao != 5);	        
	  	        
		  }

		  public void addExemplar() {
			  	Scanner scanner = new Scanner(System.in);

		        System.out.print("Informe o Numero de Exemplares Disponiveis: ");
		        int numExemplar = scanner.nextInt();

		        System.out.print("Informe o ID do Livro: ");
		        int livro_id = scanner.nextInt();
		        
		        ExemplarLivro a = new ExemplarLivro(0, true, numExemplar, livro_id);
		        System.out.println("");
		        System.out.println("Novo Exemplar Cadastrado com sucesso!");
		        inserir(a);     
		        
		  }
		  
		  public void RemoverExemplar() {
			  Scanner scanner = new Scanner(System.in);
			  System.out.print("Informe o ID do Exemplar: ");
		      int idExemplar = scanner.nextInt();
		      
		      	ExemplarLivro elivro = procurarPorCodigo(idExemplar);
		        elivro.setDisponivel(false);
		        
		        alterar(elivro);
		  }
		  
		

}
