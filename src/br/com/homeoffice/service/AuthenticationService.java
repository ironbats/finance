package br.com.homeoffice.service;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.homeoffice.core.Login;
import br.com.homeoffice.util.FacesContextUtil;
/**
 * 
 * @author Felipe Rodrigues
 * @since HomeOffice 1.0 RELEASE
 * @version 1.0.0
 * @category Projeto Financeiro
 * @serial Serial Version
 * 
 * Classe Service responsavel por realizar a autenticacao do sistema pelo Login e senha
 * sendo assim qualificamos esse service com a autoridade .
 * 
 */

@Component
public class AuthenticationService {

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;
	String username;

/**
 * Metodo boleano onde fazemos a verificacao de usuario e senha usando a bibliote do Spring de Seguranca\
 * caso ocorra algum erro teremos uma exeption lancada no Console
 * @param username
 * @param password
 * @return
 */
	public boolean login(String username, String password) {
		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					username, password);
			Authentication authenticate = authenticationManager
					.authenticate(token);
			if (authenticate.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(
						authenticate);
				return true;
			}
		} catch (AuthenticationException e) {
		}
		return false;
	}

	/**
	 * Metodo onde o usuario podera realizar o logoff do sistema
	 */
	public void logout() {
		SecurityContextHolder.getContext().setAuthentication(null);
		invalidateSession();
	}
   /**
    * Metodo onde podemos pegar qual Usuario esta logado no sistema
    * @return
    */
	public Login getUsuarioLogado() {
		return (Login) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
	}
   /**
    * Metodo responsavel por invalidar a sessao
    */
	private void invalidateSession() {
		FacesContext fc = FacesContext.getCurrentInstance();			
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(
				true);
		session.invalidate();
		
		FacesContextUtil.setNavegacao("Login.xhtml");
	
		
	}

}
