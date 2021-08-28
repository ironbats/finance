package br.com.homeoffice.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import br.com.homeoffice.core.Login;
import br.com.homeoffice.core.Role;
import br.com.homeoffice.dao.impl.ConexaoDefaultDAO;
import br.com.homeoffice.dao.impl.DefaultDaoGenericsImpl;
import br.com.homeoffice.service.impl.DefaultAtualizaRegraLogin;

@ManagedBean(name = "regrasBean")
@SessionScoped
public class RegrasBean {

	private Role role = new Role();
	private Login login = new Login();
	DefaultDaoGenericsImpl<Login> daologin = new DefaultDaoGenericsImpl<Login>(Login.class);
	DefaultDaoGenericsImpl<Role> daorole = new DefaultDaoGenericsImpl<Role>(Role.class);
	DefaultAtualizaRegraLogin  defaultatualizaRegralogin = new DefaultAtualizaRegraLogin();

	ConexaoDefaultDAO con;
	List<Role> listRole;
	List<Login> listLogin;

	private String idRole;
	
	
	public void SalvarLoginCriado(){
		zeraCampos();
		daologin.adicionarObjeto(this.login);
		zeraCampos();
	}

	public List<Login> getLisLogins() {
		listLogin = daologin.listarObjetos(Login.class);
		return listLogin;
	}
	
	public void ZeraCamposaoSelecionar(){
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();		
	}
	
	void zeraCampos(){		
	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();

	}

	public List<SelectItem> getAutoridade() {
		Connection con = null;
		PreparedStatement prepare = null;
		String sql = null;
		ResultSet rs = null;
		Role role = null;
		List<SelectItem> selectItem = new ArrayList<SelectItem>();
		try {

			con = ConexaoDefaultDAO.getconxao();
			sql = "SELECT  r.idRole,r.nomeRole  FROM role r";
			try {

				prepare = con.prepareStatement(sql);
				rs = prepare.executeQuery();

				while (rs.next()) {
					role = new Role();
					role.setIdrole(rs.getInt("idRole"));
					role.setNomeRole(rs.getString("nomeRole"));
					selectItem.add(new SelectItem(role.getIdrole().toString(), role.getNomeRole()));

					System.out.println("ID" + role.getIdrole() + "-" + "REGRA -  " + role.getNomeRole());
				}

			} catch (Exception causa) {
				causa.printStackTrace();
			}

		} catch (Exception causa) {
			causa.printStackTrace();

		}

		return selectItem;

	}

	public String confirmaSelecao() {

		this.role = new Role();
		this.setRole(daorole.buscarObjetoPorId(Integer.valueOf(idRole)));
		System.out.println("Regra Selecionada" + role.getNomeRole());

		return null;

	}

	public void alterSelecao(ActionEvent evt) {
		this.idRole = null;
		this.role = new Role();
	}

	public void atualizaRegra() {
		
		defaultatualizaRegralogin.atualizaRegraLogin(idRole,role,login);
		
		/*String sql = "UPDATE login_role SET roles_idrole=?" + "WHERE login_idLogin= ?";
		try {
			Connection con = ConexaoDefaultDAO.getconxao();
			PreparedStatement prepare = con.prepareStatement(sql);

			prepare.setString(1, role.getIdrole().toString());
			prepare.setString(2, login.getIdLogin().toString());
			prepare.executeUpdate();

			System.out.println(sql);

			this.idRole = null;
			this.role = new Role();

		} catch (Exception causa) {
			causa.printStackTrace();

		}*/

	}

	public void apagar() {
		daologin.removerObjeto(login);

	}

	public void alterar() {
		daologin.atualizarObjeto(login);

	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Role> getListRole() {
		return listRole;
	}

	public void setListRole(List<Role> listRole) {
		this.listRole = listRole;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public List<Login> getListLogin() {
		return listLogin;
	}

	public void setListLogin(List<Login> listLogin) {
		this.listLogin = listLogin;
	}

	/**
	 * @return the daologin
	 */
	public DefaultDaoGenericsImpl<Login> getDaologin() {
		return daologin;
	}

	/**
	 * @param daologin
	 *            the daologin to set
	 */
	public void setDaologin(DefaultDaoGenericsImpl<Login> daologin) {
		this.daologin = daologin;
	}

	/**
	 * @return the daorole
	 */
	public DefaultDaoGenericsImpl<Role> getDaorole() {
		return daorole;
	}

	/**
	 * @param daorole
	 *            the daorole to set
	 */
	public void setDaorole(DefaultDaoGenericsImpl<Role> daorole) {
		this.daorole = daorole;
	}

	/**
	 * @return the con
	 */
	public ConexaoDefaultDAO getCon() {
		return con;
	}

	/**
	 * @param con
	 *            the con to set
	 */
	public void setCon(ConexaoDefaultDAO con) {
		this.con = con;
	}

	/**
	 * @return the idRole
	 */
	public String getIdRole() {
		return idRole;
	}

	/**
	 * @param idRole
	 *            the idRole to set
	 */
	public void setIdRole(String idRole) {
		this.idRole = idRole;
	}
	
	

}
