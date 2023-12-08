package com.classes.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.classes.Conexao.Conexao;
import com.classes.DTO.*;
import com.classes.DAO.*;
import java.time.LocalDateTime;
import java.sql.Timestamp;


public class EmprestimoDAO {
	
	final String NOMEDATABELA = "emprestimo";
	
	
	 public boolean inserir(Emprestimo emprestimo) {
	        try {
	            Connection conn = Conexao.conectar();
	            String sql = "INSERT INTO " + NOMEDATABELA + " (dataEmprestimo,dataEntrega,valorMulta,exemplar_id,usuario_id) VALUES (?,?,?,?,?);";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            
	            // Convertendo LocalDateTime para Timestamp
	            Timestamp dataEmprestimo = Timestamp.valueOf(emprestimo.getDataEmprestimo());
	            Timestamp dataEntrega = Timestamp.valueOf(emprestimo.getDataEntrega());
	            ps.setTimestamp(1, dataEmprestimo);
	            ps.setTimestamp(2, dataEntrega);
	            ps.setDouble(3, emprestimo.getValorMulta());
	            ps.setInt(4, emprestimo.getExemplar_id().getId());
	            ps.setInt(5, emprestimo.getUsuario_id().getId());
	            ps.executeUpdate();
	            ps.close();
	            conn.close();
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	 
	 
	 public boolean alterar(Emprestimo emprestimo) {
	        try {
	            Connection conn = Conexao.conectar();
	            String sql = "UPDATE " + NOMEDATABELA + " SET dataEmprestimo = ?, dataEntrega= ?, valorMulta = ?, exemplar_id = ?, usuario_id = ? WHERE id = ?";
	            PreparedStatement ps = conn.prepareStatement(sql);
	           
	            // Convertendo LocalDateTime para Timestamp
	            Timestamp dataEmprestimo = Timestamp.valueOf(emprestimo.getDataEmprestimo());
	            Timestamp dataEntrega = Timestamp.valueOf(emprestimo.getDataEntrega());
	            ps.setTimestamp(1, dataEmprestimo);
	            ps.setTimestamp(2, dataEntrega);
	            ps.setDouble(3, emprestimo.getValorMulta());
	            ps.setInt(4, emprestimo.getExemplar_id().getId());
	            ps.setInt(5, emprestimo.getUsuario_id().getId());
	            ps.setInt(6, emprestimo.getId());
	            ps.executeUpdate();
	            ps.close();
	            conn.close();
	            return true;
	        } catch (Exception e) {
	        	 e.printStackTrace();
	             return false;
	        }
	    }
	 
	 public boolean excluir(Emprestimo emprestimo) {
	        try {
	            Connection conn = Conexao.conectar();
	            String sql = "DELETE FROM " + NOMEDATABELA + " WHERE id = ?;";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setInt(1, emprestimo.getId());
	            ps.executeUpdate();
	            ps.close();
	            conn.close();
	            return true;
	        } catch (Exception e) {
	        	 e.printStackTrace();
	             return false;
	        }
	    }
	 
	 
	 public List<Emprestimo> pesquisarTodos() {
	        try {
	            Connection conn = Conexao.conectar();
	            String sql = "SELECT * FROM " + NOMEDATABELA + ";";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ResultSet rs = ps.executeQuery();
	            List<Emprestimo> listObj = montarLista(rs);
	            return listObj;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	
	    private List<Emprestimo> montarLista(ResultSet rs) {
	        List<Emprestimo> listObj = new ArrayList<Emprestimo>();
	        
	       
	        try {
	            while (rs.next()) {
	            	ExemplarLivroDAO a = new ExemplarLivroDAO();
	            	ExemplarLivro exemplar = a.procurarPorCodigo(rs.getInt(5));
	            	
	            	UsuarioDAO b = new UsuarioDAO();
	            	Usuario usuario = b.procurarPorCodigo(rs.getInt(6));
	            
	            	Emprestimo obj = new Emprestimo(rs.getInt(1),  rs.getTimestamp(2).toLocalDateTime(), rs.getTimestamp(3).toLocalDateTime(), rs.getDouble(4), usuario, exemplar);                
	            	
	                listObj.add(obj);
	            }
	            return listObj;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	    
	    public List<Emprestimo> procurarPorCodigo(int cod) {
		        try {
		            Connection conn = Conexao.conectar();
		            String sql = "SELECT * FROM " + NOMEDATABELA + " where usuario_id = ?;";
		            PreparedStatement ps = conn.prepareStatement(sql);
		            ps.setInt(1, cod);
		            ResultSet rs = ps.executeQuery();
		            List<Emprestimo> listObj = montarLista(rs);
		            return listObj;
		        } catch (Exception e) {
		            e.printStackTrace();
		            return null;
		        }
		    }
	    
	    public List<Emprestimo> procurarPorCodigoID(int cod) {
	        try {
	            Connection conn = Conexao.conectar();
	            String sql = "SELECT * FROM " + NOMEDATABELA + " where id = ?;";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setInt(1, cod);
	            ResultSet rs = ps.executeQuery();
	            List<Emprestimo> listObj = montarLista(rs);
	            return listObj;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	    
	    /*
	     * Metodo para realizar emprestimo de livro
	     * 
	     */
	    
	    public boolean realizarEmprestimo(int idExemplar, int idUsuario) {
	        try {
	            Connection conn = Conexao.conectar();

	            // Verificar se o exemplar está disponível
	            ExemplarLivroDAO exemplarLivroDAO = new ExemplarLivroDAO();
	            ExemplarLivro exemplar = exemplarLivroDAO.procurarPorCodigo(idExemplar);

	            UsuarioDAO usuarioDAO = new UsuarioDAO();
	            Usuario user = usuarioDAO.procurarPorCodigo(idUsuario);

	            
	            if (exemplar != null && exemplar.isDisponivel()) {
	                // Criar um novo empréstimo
	                Emprestimo emprestimo = new Emprestimo(0, LocalDateTime.now(), LocalDateTime.now().plusDays(7), 0, user, exemplar);
	                // Chamar o método de inserção
	                if (inserir(emprestimo)) {
	                    System.out.println("Empréstimo realizado com sucesso!");
	                    return true;
	                }
	            } else {
	                System.out.println("Exemplar não disponível para empréstimo.");
	            }

	            conn.close();
	            return false;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    
	    
	    public void renovarEmprestimo() {
	    	Scanner scanner = new Scanner(System.in);
            
       		System.out.print("Informe o ID do Emprestimo: ");
  	        int idEmprestimo = scanner.nextInt();
  	        
  	        List<Emprestimo> a = procurarPorCodigoID(idEmprestimo);
  	        for (Emprestimo e : a) {
	            e.setDataEntrega(LocalDateTime.now().plusDays(7));
	            alterar(e);
  	        }
  	        
	    }
	    
	    public void AdicionarMulta() {
	    	Scanner scanner = new Scanner(System.in);
            
       		System.out.print("Informe o ID do Emprestimo: ");
  	        int idEmprestimo = scanner.nextInt();
  	        
  	        System.out.print("Informe o valor da multa: ");
	        double valor = scanner.nextDouble();
  	        
  	        List<Emprestimo> a = procurarPorCodigoID(idEmprestimo);
  	        for (Emprestimo e : a) {
	            e.setValorMulta(valor);
	            System.out.println(e);
	            alterar(e);
  	        }
  	        
	    }
	    
}
