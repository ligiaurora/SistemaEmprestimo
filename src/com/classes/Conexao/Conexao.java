package com.classes.Conexao;

import java.sql.DriverManager;
import java.sql.Connection;

public class Conexao {

	final static String NOME_DO_BANCO = "emprestimo_livros";
    public static Connection conectar() {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		String url = "jdbc:mysql://localhost/" + NOME_DO_BANCO ;

            return DriverManager.getConnection(url, "root", "123456789");
        } catch (Exception e) {
        	e.printStackTrace();
            return null;
        }
    }
}