package br.com.homeoffice.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

/**
 * 
 * @author Felipe Rodrigues
 * @since HomeOffice 1.0 RELEASE
 * @version 1.0.0
 * @category Projeto Financeiro
 * @serial Serial Version
 * 
 *         Classe responsavel pela navegacao e redirecionamento de paginas do
 *         sistema onde pegamos as requisicoes por sessoes.
 * 
 */
public class FacesContextUtil {

	private static final String HIBERNATE_SESSION = "hibernate_session";

	public static void setRequestSession(Session session) {
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put(HIBERNATE_SESSION, session);
	}

	public static Session getRequestSession() {
		return (Session) FacesContext.getCurrentInstance().getExternalContext()
				.getRequestMap().get(HIBERNATE_SESSION);
	}

	public static Object getSessionAtributte(String nomeAtributo) {

		return ((HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true))
				.getAttribute(nomeAtributo);

	}

	public static void setNavegacao(String saida) {
		FacesContext
				.getCurrentInstance()
				.getApplication()
				.getNavigationHandler()
				.handleNavigation(FacesContext.getCurrentInstance(), null,
						saida);

	}

}
