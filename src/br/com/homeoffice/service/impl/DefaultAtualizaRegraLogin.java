package br.com.homeoffice.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.com.homeoffice.core.Login;
import br.com.homeoffice.core.Role;
import br.com.homeoffice.dao.impl.ConexaoDefaultDAO;
import br.com.homeoffice.service.AtualizaRegraLogin;

public class DefaultAtualizaRegraLogin implements  AtualizaRegraLogin {

	@Override
	public void atualizaRegraLogin(String idRole, Role role, Login login) {
		
		String sql = "UPDATE login_role SET roles_idrole=?" + "WHERE login_idLogin= ?";
		try {
			Connection con = ConexaoDefaultDAO.getconxao();
			PreparedStatement prepare = con.prepareStatement(sql);

			prepare.setString(1, role.getIdrole().toString());
			prepare.setString(2, login.getIdLogin().toString());
			prepare.executeUpdate();

			System.out.println(sql);

			idRole = null;
			role = new Role();

		} catch (Exception causa) {
			causa.printStackTrace();

		}
		
	}

}
