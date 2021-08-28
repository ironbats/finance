package br.com.homeoffice.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.com.homeoffice.core.Login;
import br.com.homeoffice.service.AuthenticationService;
import br.com.homeoffice.util.FacesContextUtil;

/**
 * 
 * @author Felipe Rodrigues
 * @since HomeOffice 1.0 RELEASE
 * @version 1.0.0
 * @category Projeto Financeiro
 * @serial Serial Version
 */

@ManagedBean
@RequestScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 3607639633764758479L;
	/**
	 * Servico de autenticacao de servicos , Objeto serializado e que faz a
	 * autenticacao de Login e Senha do Usuario no sistema
	 */
	@ManagedProperty(value = "#{authenticationService}")
	private AuthenticationService authenticationService;

	private String userName;
	private String password;

	/**
	 * Objeto Login
	 */
	private Login login = new Login();

	/**
	 * metodo responsavel por fazer a autenticacao do Usuario no sistema se o
	 * metodo nao for bem sucedido na tela retorna um erro de login ou senha
	 * invalidos se nao o usuario e redirecionado para pagina de inicio
	 * 
	 * @return
	 */
	public String loginUsuario() {
		boolean success = authenticationService.login(userName, password);

		if (!success) {
			FacesMessage facesMessage = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "", "Login ou senha inválidos");
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			System.out.println("Passei aqui em if");

			return "/login.xhtml";
		}
		System.out.println("passei aqui em else");
		FacesContextUtil.setNavegacao("/pages/index.xhtml");
		return "";
	}

	/**
	 * Metodo responsavel por realizar logoff do sistema e redirecionalo para
	 * pagina de Login inicial
	 * 
	 * @return
	 */
	public void logout(ActionEvent event) {
		authenticationService.logout();
		setLogin(null);
		new Login();
		FacesContextUtil.setNavegacao("/Login.xhtml");
		//return "Login.xhtml";
	}

	/**
	 * Metodos get e Set
	 * 
	 * @return
	 */
	public AuthenticationService getAuthenticationService() {
		return authenticationService;
	}

	public void setAuthenticationService(
			AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

}
