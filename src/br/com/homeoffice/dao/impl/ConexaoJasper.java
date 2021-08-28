package br.com.homeoffice.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ConexaoJasper {
	
	/**
	 * 
	 * @author Felipe Rodrigues
	 * @since HomeOffice 1.0 RELEASE
	 * @version 1.0.0
	 * @category Projeto Financeiro
	 * 
	 * Classe DAO Para  vizualizar os relatorios jasper  de acordo com o banco de dados.
	 * 
	 */
	
	final private String driver = "com.mysql.jdbc.Driver";
    final private String url = "jdbc:mysql://localhost/homeoffice";
    final private String usuario = "root";
    final private String senha = "admin123";
    private Connection conexao;
    public Statement statement;
    public ResultSet resultset;
    
   public boolean conecta()
   {
        boolean result = true;
        try 
        {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, usuario, senha);
        }
        catch(ClassNotFoundException Driver) 
        {
           JOptionPane.showMessageDialog(null,"Driver não localizado: "+Driver);
           result = false;
        }
        catch(SQLException Fonte) 
        {
            JOptionPane.showMessageDialog(null,"Deu erro na conexão "+
                    "com a fonte de dados: "+Fonte);
            result = false;
        }
        return result; 
   }
   
   public void desconecta()
   {
        @SuppressWarnings("unused")
		boolean result = true;
        try 
        {
            conexao.close();
            JOptionPane.showMessageDialog(null,"banco fechado");
        }
        catch(SQLException fecha) 
        {
            JOptionPane.showMessageDialog(null,"Não foi possivel "+
                    "fechar o banco de dados: "+fecha);
            result = false;
        }

   }
   
   public void executeSQL(String sql)
   {
        try 
        {
            statement = conexao.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            resultset = statement.executeQuery(sql);
        }
        catch(SQLException sqlex) 
        {
           JOptionPane.showMessageDialog(null,"Não foi possível "+
                   "executar o comando sql,"+sqlex+", o sql passado foi "+sql);
        }

   }
	
	

}
