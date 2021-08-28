package br.com.homeoffice.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Felipe Rodrigues
 * @since HomeOffice 1.0 RELEASE
 * @version 1.0.0
 * @category Projeto Financeiro
 */
public class ConexaoDefaultDAO
{
	public static final Connection getconxao()
	{
		Connection con = null;
		try
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("passou");
			}
			catch (ClassNotFoundException causa)
			{

				causa.printStackTrace();
				System.out.println("Deu erro");
			}
			con = DriverManager.getConnection("jdbc:mysql://localhost/homeoffice", "root", "admin");
			System.out.println("Conectado com Sucesso");
		}
		catch (SQLException causa)
		{
			causa.printStackTrace();
			System.out.println("Deu erro");
		}
		return con;
	}



}
