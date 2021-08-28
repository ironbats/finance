package br.com.homeoffice.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.homeoffice.core.Favorecidos;
import br.com.homeoffice.dao.impl.DefaultDaoGenericsImpl;
import br.com.homeoffice.service.CadastroGenericaService;
import br.com.homeoffice.service.impl.DefaultCadastroGenericsServiceImpl;


//@Controller("favorecidosbean")
//@Scope("request")
@ManagedBean(name = "favorecidosbean")
@SessionScoped
public class FavorecidosBean<T> {

	private Favorecidos favorecidos = new Favorecidos();

	private Logger LOG = Logger.getLogger(FavorecidosBean.class);

	private Class<T> classe;

	public FavorecidosBean(Class<T> classe) {

		this.classe = classe;

	}

	@Resource(name = "cadastroGenericaService")
	private CadastroGenericaService<Favorecidos> cadGenericsDados = new DefaultCadastroGenericsServiceImpl<Favorecidos>(
			Favorecidos.class);

	public FavorecidosBean() {
		// TODO Auto-generated constructor stub
	}

	public Class<T> getClasse() {
		return classe;
	}

	// Salva favorecido
	public void salvarFavorecidos() {
		getCadGenericsDados().SalvarDados(favorecidos);
		zerarCampos();
	}

	public void excluirFavorecidos() {
		getCadGenericsDados().removerObjeto(favorecidos);
		zerarCampos();
	}

	public void editaFavorecidos() {
		getCadGenericsDados().atualizarObjeto(favorecidos);
		zerarCampos();
	}

	void zerarCampos() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();

	}

	public List<Favorecidos> getListFavorecidos() {

		List<Favorecidos> resultListFavorecidos = getCadGenericsDados().listarObjetos(Favorecidos.class);

		return resultListFavorecidos;
	}

	/**
	 * @return the favorecidos
	 */
	public Favorecidos getFavorecidos() {
		return favorecidos;
	}

	/**
	 * @param favorecidos
	 *            the favorecidos to set
	 */
	public void setFavorecidos(Favorecidos favorecidos) {
		this.favorecidos = favorecidos;
	}

	public CadastroGenericaService<Favorecidos> getCadGenericsDados() {
		return cadGenericsDados;
	}

	public void setCadGenericsDados(CadastroGenericaService<Favorecidos> cadGenericsDados) {
		this.cadGenericsDados = cadGenericsDados;
	}

}
