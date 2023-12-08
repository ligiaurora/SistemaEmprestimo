package com.classes.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.classes.Conexao.Conexao;
import com.classes.DTO.*;

public class UsuarioDAO {
	
	
	final String NOMEDATABELA = "usuario";
	 public boolean inserir(Usuario usuario) {
	        try {
	            Connection conn = Conexao.conectar();
	            String sql = "INSERT INTO " + NOMEDATABELA + " (nome,nomeUsuario,senha,estadoConta,atendente) VALUES (?,?,?,?,?);";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setString(1, usuario.getNome());
	            ps.setString(2, usuario.getNomeUsuario());
	            ps.setString(3, usuario.getSenha());
	            ps.setBoolean(4, usuario.getEstadoConta());
	            ps.setBoolean(5,usuario.isAtendente());
	            ps.executeUpdate();
	            ps.close();
	            conn.close();
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	 
	 
	 public boolean alterar(Usuario usuario) {
	        try {
	            Connection conn = Conexao.conectar();
	            String sql = "UPDATE " + NOMEDATABELA + " SET nome = ?, nomeUsuario = ?, senha = ?, estadoConta = ?, atendente = ? WHERE id = ?";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setString(1, usuario.getNome());
	            ps.setString(2, usuario.getNomeUsuario());
	            ps.setString(3, usuario.getSenha());
	            ps.setBoolean(4, usuario.getEstadoConta());
	            ps.setBoolean(5,usuario.isAtendente());
	            ps.setInt(6,usuario.getId());
	            ps.executeUpdate();
	            ps.close();
	            conn.close();
	            return true;
	        } catch (Exception e) {
	        	 e.printStackTrace();
	             return false;
	        }
	    }
	 
	 public boolean excluir(Usuario usuario) {
	        try {
	            Connection conn = Conexao.conectar();
	            String sql = "DELETE FROM " + NOMEDATABELA + " WHERE id = ?;";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setInt(1, usuario.getId());
	            ps.executeUpdate();
	            ps.close();
	            conn.close();
	            return true;
	        } catch (Exception e) {
	        	 e.printStackTrace();
	             return false;
	        }
	    }
	 
	 
	 
	 /*
	  * METODO PARA EXCLUIR ATENDENTE
	  */
	 
	 public boolean excluirAtendente(int id) {
	     try {
	         Connection conn = Conexao.conectar();
	         String sql = "DELETE FROM " + NOMEDATABELA + " WHERE id = ? AND atendente = true;";
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ps.setInt(1, id);
	         int rowsAffected = ps.executeUpdate();
	         ps.close();
	         conn.close();
	         return rowsAffected > 0;
	     } catch (Exception e) {
	         e.printStackTrace();
	         return false;
	     }
	 }


	 /*
	  * METODO PARA EXCLUIR USUARIO
	  */
	 
	 public boolean excluirUsuario(int id) {
	     try {
	         Connection conn = Conexao.conectar();
	         String sql = "DELETE FROM " + NOMEDATABELA + " WHERE id = ? AND atendente = false;";
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ps.setInt(1, id);
	         int rowsAffected = ps.executeUpdate();
	         ps.close();
	         conn.close();
	         return rowsAffected > 0;
	     } catch (Exception e) {
	         e.printStackTrace();
	         return false;
	     }
	 }
	 
	 /*
	  * ALTERAR ATENDENTE
	  * */
	 
	 public boolean alterarAtendente(int id, String novoNome, String novoNomeUsuario, String novaSenha) {
		    try {
		        Connection conn = Conexao.conectar();
		        String sql = "UPDATE " + NOMEDATABELA + " SET nome = ?, nomeUsuario = ?, senha = ? WHERE id = ? AND atendente = true";
		        PreparedStatement ps = conn.prepareStatement(sql);
		        ps.setString(1, novoNome);
		        ps.setString(2, novoNomeUsuario);
		        ps.setString(3, novaSenha);
		        ps.setInt(4, id);

		        int rowsAffected = ps.executeUpdate();

		        ps.close();
		        conn.close();

		        return rowsAffected > 0;
		    } catch (Exception e) {
		        e.printStackTrace();
		        return false;
		    }
		}
	 
	 /*
	  * METODO PARA ALTERAR USUARIO
	  * */
	 
	 public boolean alterarUsuario(int id, String novoNome, String novoNomeUsuario, String novaSenha) {
		    try {
		        Connection conn = Conexao.conectar();
		        String sql = "UPDATE " + NOMEDATABELA + " SET nome = ?, nomeUsuario = ?, senha = ? WHERE id = ? AND atendente = false";
		        PreparedStatement ps = conn.prepareStatement(sql);
		        ps.setString(1, novoNome);
		        ps.setString(2, novoNomeUsuario);
		        ps.setString(3, novaSenha);
		        ps.setInt(4, id);

		        int rowsAffected = ps.executeUpdate();

		        ps.close();
		        conn.close();

		        return rowsAffected > 0;
		    } catch (Exception e) {
		        e.printStackTrace();
		        return false;
		    }
		}


	 
	 
	 
	 public List<Usuario> pesquisarTodos() {
	        try {
	            Connection conn = Conexao.conectar();
	            String sql = "SELECT * FROM " + NOMEDATABELA + ";";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ResultSet rs = ps.executeQuery();
	            List<Usuario> listObj = montarLista(rs);
	            //System.out.println(ps);
	            return listObj;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	 
	    private List<Usuario> montarLista(ResultSet rs) {
	        List<Usuario> listObj = new ArrayList<Usuario>();
	        try {
	            while (rs.next()) {
	            	Usuario obj = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getBoolean(5),rs.getBoolean(6));                
	                listObj.add(obj);
	            }
	            return listObj;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	    
	    public Usuario procurarPorCodigo(int cod) {
	        try {
	            Connection conn = Conexao.conectar();
	            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE id = ?;";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setInt(1, cod);
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	            	Usuario obj = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getBoolean(5),rs.getBoolean(6));              
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
	    
	    
	   /*VERIFICAÇÃO USUARIO - ATENDENTE*/
	    
	    public Atendente loginAtendente(String nomeUsuario, String senha) {
	        try {
	            Connection conn = Conexao.conectar();
	            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE nomeUsuario = ? AND senha = ? AND atendente = true AND estadoConta=true;";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setString(1, nomeUsuario);
	            ps.setString(2, senha);
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	                Atendente atendente = new Atendente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5));
	                ps.close();
	                rs.close();
	                conn.close();
	                return atendente;
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
	    
	    
	    /*VERIFICAÇÃO USUARIO - USUARIO*/
	    
	    
	    public Usuario loginUsuario(String nomeUsuario, String senha) {
	        try {
	            Connection conn = Conexao.conectar();
	            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE nomeUsuario = ? AND senha = ? AND atendente = false AND estadoConta=true;";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setString(1, nomeUsuario);
	            ps.setString(2, senha);
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	               Usuario user = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5), false);
	                ps.close();
	                rs.close();
	                conn.close();
	                return user;
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
	    
	    
	   /*
		* Metodo para adicionar Atendente
		 * */
	    
	  public void addAtendente() {
		  	Scanner scanner = new Scanner(System.in);

		  	System.out.print("Digite o nome: ");
	        String nome = scanner.nextLine();
		  	
	        System.out.print("Digite o nome de Usuário: ");
	        String nomeUsuario = scanner.nextLine();

	        System.out.print("Digite a sua senha de Usuario: ");
	        String senha = scanner.nextLine();
	        
	        Usuario a = new Usuario(0, nome, nomeUsuario, senha, true, true);
	        System.out.println("");
	        System.out.println("Novo Atendente: " + nomeUsuario + " inserido com sucesso!");
	        inserir(a);
	  }  
	  
	  
	  /*
	   * Metodo para adicionar Usuario
	   * */
	  public void addUsuario() {
		  	Scanner scanner = new Scanner(System.in);

		  	System.out.print("Digite o nome: ");
	        String nome = scanner.nextLine();
		  	
	        System.out.print("Digite o nome de Usuário: ");
	        String nomeUsuario = scanner.nextLine();

	        System.out.print("Digite a sua senha de Usuario: ");
	        String senha = scanner.nextLine();
	        
	        Usuario a = new Usuario(0, nome, nomeUsuario, senha, true, false);
	        System.out.println("");
	        System.out.println("Novo Usuario: "+ nomeUsuario + " inserido com sucesso!");
	        inserir(a);
	  }
	  
	  /*
	   * Metodo para desativarAtendente
	   */
	  
	// Dentro da classe UsuarioDAO
	  public boolean desativarAtendente(int id) {
		    try {
		        Connection conn = Conexao.conectar();
		        String sql = "UPDATE " + NOMEDATABELA + " SET estadoConta = false WHERE id = ?";
		        PreparedStatement ps = conn.prepareStatement(sql);
		        ps.setInt(1, id);
		        int rowsAffected = ps.executeUpdate();
		        ps.close();
		        conn.close();
		        return rowsAffected > 0;
		    } catch (Exception e) {
		        e.printStackTrace();
		        return false;
		    }
		}

	  
	  /*
	   * Metodo para desativarUsuario
	   */
	  
	
	  public boolean desativarUsuario(int id) {
		    try {
		        Connection conn = Conexao.conectar();
		        String sql = "UPDATE " + NOMEDATABELA + " SET estadoConta = false WHERE id = ?";
		        PreparedStatement ps = conn.prepareStatement(sql);
		        ps.setInt(1, id);
		        int rowsAffected = ps.executeUpdate();
		        ps.close();
		        conn.close();
		        return rowsAffected > 0;
		    } catch (Exception e) {
		        e.printStackTrace();
		        return false;
		    }
		}
	  
	  
	/*
	 * Metodo para ativar Atendente
	 */
	  public boolean reativarAtendente(int id) {
		    try {
		        Connection conn = Conexao.conectar();
		        String sql = "UPDATE " + NOMEDATABELA + " SET estadoConta = true WHERE id = ?";
		        PreparedStatement ps = conn.prepareStatement(sql);
		        ps.setInt(1, id);
		        int rowsAffected = ps.executeUpdate();
		        ps.close();
		        conn.close();
		        return rowsAffected > 0;
		    } catch (Exception e) {
		        e.printStackTrace();
		        return false;
		    }
		}


	  /*
	   * Metodo para reativar Atendente
	   */
	  
	  
	  public boolean reativarUsuario(int id) {
		    try {
		        Connection conn = Conexao.conectar();
		        String sql = "UPDATE " + NOMEDATABELA + " SET estadoConta = true WHERE id = ?";
		        PreparedStatement ps = conn.prepareStatement(sql);
		        ps.setInt(1, id);
		        int rowsAffected = ps.executeUpdate();
		        ps.close();
		        conn.close();
		        return rowsAffected > 0;
		    } catch (Exception e) {
		        e.printStackTrace();
		        return false;
		    }
		}
	  
	  
	  /*
	   * 
	   * METODO PARA LISTAR TODOS OS ATENDENTES
	   */
	  
	  public List<Atendente> listarAtendentes() {
		    try {
		        Connection conn = Conexao.conectar();
		        String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE atendente = true;";
		        PreparedStatement ps = conn.prepareStatement(sql);
		        ResultSet rs = ps.executeQuery();
		        List<Atendente> atendentes = new ArrayList<>();

		        while (rs.next()) {
		            Atendente atendente = new Atendente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5));
		            atendentes.add(atendente);
		        }

		        ps.close();
		        rs.close();
		        conn.close();

		        return atendentes;
		    } catch (Exception e) {
		        e.printStackTrace();
		        return null;
		    }
		}
	  
	  /*
	   * 
	   * METODO PARA LISTAR TODOS OS USUARIOS
	   */
	  
	  public List<Usuario> listarUsuarios() {
		    try {
		        Connection conn = Conexao.conectar();
		        String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE atendente = false;";
		        PreparedStatement ps = conn.prepareStatement(sql);
		        ResultSet rs = ps.executeQuery();
		        List<Usuario> users = new ArrayList<>();

		        while (rs.next()) {
		            Usuario usuario = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5), false);
		            users.add(usuario);
		        }

		        ps.close();
		        rs.close();
		        conn.close();

		        return users;
		    } catch (Exception e) {
		        e.printStackTrace();
		        return null;
		    }
		}



}
