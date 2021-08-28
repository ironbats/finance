/*package br.com.homeoffice.bean;

import java.io.IOException;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;

import br.com.homeoffice.core.Login;
import br.com.homeoffice.dao.DAO;
import br.com.homeoffice.service.ServicoUsuario;
import br.com.homeoffice.util.Constantes;
import br.com.homeoffice.util.FacesUtils;
import br.com.homeoffice.util.Funcoes;


@SessionScoped
@ManagedBean(name = "autenticacaoController")
 @Controller 
@Scope("view")
public class  implements Serializable
{

	private static final long serialVersionUID = -5521443886024182165L;

	private FacebookConnectionFactory connectionFactory;
	private Login login;
	private boolean exibeMensagem;
	private boolean exibeCriaContaFacebook;


	@Autowired
	private ServicoUsuario servicoUsuario;
	private Facebook facebook;
	private List<FacebookProfile> amigos = new ArrayList<FacebookProfile>();

	@Autowired
	private DAO<Login> daoLgin;

	@PostConstruct
	public void init()
	{

	}

	@PreDestroy
	public void destroy()
	{

	}

	
	 * @Autowired public AutenticacaoController(ServicoUsuario servicoUsuario) { this.servicoUsuario = servicoUsuario;
	 * 
	 * }
	 

	public void autenticar()
	{
		try
		{
			FacesContext ctx = FacesContext.getCurrentInstance();
			ctx.getExternalContext().dispatch("/j_spring_security_check");
			ctx.responseComplete();
		}
		catch (Exception ex)
		{
			FacesUtils.addErrorMessageComponent("j_password",
					ResourceBundle.getBundle(Constantes.MESSAGE_PROPERTIES_PATH).getString("login.erroAutenticacao"));
		}
	}

	public void autenticarSpringComFacebook()
	{

		try
		{

			connectionFactory = new FacebookConnectionFactory(Constantes.APP_ID, Constantes.APP_SECRET);
			OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
			OAuth2Parameters oAuth2Parameters = new OAuth2Parameters();
			oAuth2Parameters.setScope("user_about_me,user_birthday,user_likes,publish_pages,email");
			oAuth2Parameters.add("display", "popup");
			String serverPath = FacesUtils.getApplicationURI() + Constantes.PAGINA_AUTENTICACAO_LOGIN;
			oAuth2Parameters.setRedirectUri(serverPath);
			String authorizeUrl = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, oAuth2Parameters);
			HttpServletResponse response = FacesUtils.getServletResponse();
			response.sendRedirect(authorizeUrl);

		}
		catch (IOException causa)
		{

			causa.printStackTrace();

		}
	}

	public void processLoginFacebook() throws IOException, ServletException
	{

		try
		{

			Map<String, String> paramMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			String code = paramMap.get("code");

			if (code != null && !code.isEmpty())
			{

				FacebookConnectionFactory connFactory = new FacebookConnectionFactory(Constantes.APP_ID, Constantes.APP_SECRET);
				String serverPath = FacesUtils.getApplicationURI() + Constantes.PAGINA_AUTENTICACAO_LOGIN;
				AccessGrant accessGrant = connFactory.getOAuthOperations().exchangeForAccess(code, serverPath, null);
				Connection<Facebook> connection = connFactory.createConnection(accessGrant);
				this.facebook = connection.getApi();

				if (this.facebook.isAuthorized())
				{

					FacebookProfile fp = facebook.userOperations().getUserProfile();
					amigos.addAll(facebook.friendOperations().getFriendProfiles());

					if (fp.getUsername() != null && !fp.getUsername().isEmpty())
					{
						this.login = servicoUsuario.recuperaUsuario(fp.getUsername());

						// this.login =
						// daoLgin.buscarPorLogin(fp.getUsername());

						if (this.login == null)
						{
							criaNovoUsuario(fp, fp.getUsername());
						}
						else
						{
							servicoUsuario.realizaAutenticacaoAutomatica(FacesUtils.getServletRequest(), this.login);
							FacesUtils.getServletResponse().sendRedirect("/HomeOffice/pages/home.xhtml");
						}
					}
					else
					{
						String login = Funcoes.recuperaNomeUsuario(fp.getEmail());
						this.login = servicoUsuario.recuperaUsuario(login);
						if (login == null)
						{
							criaNovoUsuario(fp, login);
						}
					}
				}
			}
		}
		catch (IllegalArgumentException iae)
		{
			FacesContext context = FacesContext.getCurrentInstance();
			FacesUtils
					.addErrorMessage(ResourceBundle.getBundle(Constantes.MESSAGE_PROPERTIES_PATH).getString("usuario.loginExistente"));
			NavigationHandler nh = context.getApplication().getNavigationHandler();
			nh.handleNavigation(context, null, Constantes.PAGINA_LOGIN);

		}
		catch (Exception ex)
		{
			FacesUtils.addErrorMessageComponent("msgAviso",
					ResourceBundle.getBundle(Constantes.MESSAGE_PROPERTIES_PATH).getString("usuario.login.erroAutenticacao"));
		}
	}

	private void criaNovoUsuario(FacebookProfile fp, String login)
	{
		if (this.login == null)
		{
			this.login = new Login();
			this.login.setLoginUsuario(login);
			this.login.setNomeCompleto(fp.getName());
			this.login.setEmailUsuario(fp.getEmail());
			// this.login.setCidade(fp.getLocation() != null ?
			// fp.getLocation().getName() : null);
		}
	}

	public void salvarUsuarioFacebook()
	{

		try
		{
			servicoUsuario.salva(login, true);
			//daoLgin.adicionarObjeto(login);
			autenticar();
			FacesUtils.addSuccessMessage(MessageFormat.format(
					ResourceBundle.getBundle(Constantes.MESSAGE_PROPERTIES_PATH).getString("usuario.criadoSucessoFacebook"),
					login.getNomeCompleto()));

		}
		catch (Exception ex)
		{
			setExibeCriaContaFacebook(true);
			System.out.println(ex.getMessage());
			FacesUtils.addErrorMessageComponent("msgAviso",
					ResourceBundle.getBundle(Constantes.MESSAGE_PROPERTIES_PATH).getString("usuario.erroIncluir"));
		}
	}

	public void encerraSessao()
	{
		try
		{
			FacesContext ctx = FacesContext.getCurrentInstance();
			ctx.getExternalContext().redirect(FacesUtils.getServletRequest().getContextPath() + "/j_spring_security_logout");
		}
		catch (Exception e)
		{
		}
	}

	public FacebookConnectionFactory getConnectionFactory()
	{
		return connectionFactory;
	}

	public void setConnectionFactory(FacebookConnectionFactory connectionFactory)
	{
		this.connectionFactory = connectionFactory;
	}

	public Login getLogin()
	{
		return login;
	}

	public void setLogin(Login login)
	{
		this.login = login;
	}

	public boolean isExibeMensagem()
	{
		return exibeMensagem;
	}

	public void setExibeMensagem(boolean exibeMensagem)
	{
		this.exibeMensagem = exibeMensagem;
	}

	public DAO<Login> getDaoLgin()
	{
		return daoLgin;
	}

	public void setDaoLgin(DAO<Login> daoLgin)
	{
		this.daoLgin = daoLgin;
	}

	public boolean isExibeCriaContaFacebook()
	{
		return exibeCriaContaFacebook;
	}

	public void setExibeCriaContaFacebook(boolean exibeCriaContaFacebook)
	{
		this.exibeCriaContaFacebook = exibeCriaContaFacebook;
	}

	public ServicoUsuario getServicoUsuario()
	{
		return servicoUsuario;
	}

	public void setServicoUsuario(ServicoUsuario servicoUsuario)
	{
		this.servicoUsuario = servicoUsuario;
	}

	public Facebook getFacebook()
	{
		return facebook;
	}

	public void setFacebook(Facebook facebook)
	{
		this.facebook = facebook;
	}

	public List<FacebookProfile> getAmigos()
	{
		return amigos;
	}

	public void setAmigos(List<FacebookProfile> amigos)
	{
		this.amigos = amigos;
	}




}
*/