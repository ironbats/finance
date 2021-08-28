package br.com.homeoffice.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import br.com.homeoffice.core.ContasApagarCad;
import br.com.homeoffice.core.Favorecidos;
import br.com.homeoffice.core.TipoPagamentoVO;
import br.com.homeoffice.core.UFVO;
import br.com.homeoffice.service.CadastroGenericaService;
import br.com.homeoffice.service.impl.DefaultCadastroGenericsServiceImpl;

@ManagedBean(name = "contasapagarbean")
@SessionScoped
public class ContasApagarBean implements Serializable {

	private static final long serialVersionUID = -4412036921298733816L;

	private String[] idFavorecidos;
	private List<ContasApagarCad> listContaspagar = new ArrayList<ContasApagarCad>();
	private List<Favorecidos> listFavorecidos = new ArrayList<Favorecidos>();

	private Favorecidos favorecidos = new Favorecidos();
	private ContasApagarCad contasApagarCad = new ContasApagarCad();

	@Resource(name = "cadastroGenericaService")
	private CadastroGenericaService<ContasApagarCad> defaultServiceApagarImpl = new DefaultCadastroGenericsServiceImpl<ContasApagarCad>(
			ContasApagarCad.class);

	@Resource(name = "cadastroGenericaService")
	private CadastroGenericaService<Favorecidos> defaultServiceImple = new DefaultCadastroGenericsServiceImpl<Favorecidos>(
			Favorecidos.class);

	public void getFavorecidoSelecionados(AjaxBehaviorEvent event) {
		List<Favorecidos> listfav = new ArrayList<Favorecidos>();
		int tamanho = listfav.size();
		for (int i = 0; i < tamanho; i++) {
			FacesMessage message = new FacesMessage("ID Selecionado");
			FacesContext.getCurrentInstance().addMessage(idFavorecidos[tamanho], message);
			FacesContext.getCurrentInstance().addMessage(listfav.get(i).getIdfavorecidos().toString(), message);
		}
	}

	public List<ContasApagarCad> getListContasapagar() {
		return defaultServiceApagarImpl.listarObjetos(ContasApagarCad.class);
	}

	public void excluirDados() {
		defaultServiceApagarImpl.removerObjeto(contasApagarCad);
		zeraCampos();
	}

	public void salvarDados() {
		defaultServiceApagarImpl.atualizarObjeto(contasApagarCad);
		zeraCampos();

	}

	// metodo Ira listar as contas Pagas no datatable
	public List<ContasApagarCad> getlistContasapagarCad() {
		List<ContasApagarCad> listcontas = defaultServiceApagarImpl.listarObjetos(ContasApagarCad.class);
		return listcontas;
	}

	// Metodo para alterar Dados
	public void alterarDados() {
		defaultServiceApagarImpl.atualizarObjeto(contasApagarCad);
		zeraCampos();
	}

	/**
	 *
	 * @return Metodo Responsavel Por Listar os Favorecidos A pagar no Mes.
	 */
	public List<SelectItem> getListaFavorecidos() {
		this.listFavorecidos = defaultServiceImple.listarObjetos(Favorecidos.class);
		List<SelectItem> selectItem = new ArrayList<SelectItem>();
		for (Favorecidos favorecidosBean : listFavorecidos) {
			selectItem.add(new SelectItem(favorecidosBean.getNomeFavorecidos()));
		}
		return selectItem;
	}

	/**
	 *
	 * @param event
	 *
	 *            Metodo Evento Responsavel Por Identificar qual Favorecido Foi
	 *            Selecionado
	 */

	public void getIdentificador2(AjaxBehaviorEvent event) {

		this.favorecidos = new Favorecidos();
		List<Favorecidos> listfav = new ArrayList<Favorecidos>();
		for (int j = 0; j < listfav.size(); j++) {

			this.favorecidos.setIdfavorecidos(listfav.get(j).getIdfavorecidos());

			this.setFavorecidos(defaultServiceImple.buscarObjetoPorId(listfav.get(j).getIdfavorecidos()));
			System.out.println(idFavorecidos[j] + favorecidos.getNomeFavorecidos());

		}

	}

	public void getIdentificador(AjaxBehaviorEvent event) {
		this.favorecidos = new Favorecidos();
		List<Favorecidos> listfav = new ArrayList<Favorecidos>();
		for (int j = 0; j < listfav.size(); j++) {
			this.favorecidos.setIdfavorecidos(listfav.get(j).getIdfavorecidos());
			this.favorecidos.setIdfavorecidos(Integer.valueOf(idFavorecidos[j]));
			this.setFavorecidos(defaultServiceImple.buscarObjetoPorId(Integer.valueOf(idFavorecidos[j])));
			System.out.println(idFavorecidos[j] + favorecidos.getNomeFavorecidos());
		}

	}

	public String getConfirmaItensSelecionados() {
		this.favorecidos = new Favorecidos();
		for (int i = 0; i < idFavorecidos.length; i++) {
			this.setFavorecidos(defaultServiceImple.buscarObjetoPorId(Integer.valueOf(idFavorecidos[i])));
			System.out.println(
					"Favorecido Selecionado: " + idFavorecidos[i] + ": -  : " + favorecidos.getNomeFavorecidos());
		}

		return null;

	}

	public void zeraCampos() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();

	}

	public void zeraCampos2(ActionEvent event) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();

	}

	public TipoPagamentoVO[] getTipoPag() {
		return TipoPagamentoVO.values();
	}

	// Metodo que ira trazer as UF Selecionadas
	public UFVO[] getUFVO() {
		return UFVO.values();
	}

	/**
	 * @return the idFavorecidos
	 */
	public String[] getIdFavorecidos() {
		return idFavorecidos;
	}

	/**
	 * @param idFavorecidos
	 *            the idFavorecidos to set
	 */
	public void setIdFavorecidos(String[] idFavorecidos) {
		this.idFavorecidos = idFavorecidos;
	}

	/**
	 * @return the listContaspagar
	 */
	public List<ContasApagarCad> getListContaspagar() {
		return listContaspagar;
	}

	/**
	 * @param listContaspagar
	 *            the listContaspagar to set
	 */
	public void setListContaspagar(List<ContasApagarCad> listContaspagar) {
		this.listContaspagar = listContaspagar;
	}

	/**
	 * @return the listFavorecidos
	 */
	public List<Favorecidos> getListFavorecidos() {
		return listFavorecidos;
	}

	/**
	 * @param listFavorecidos
	 *            the listFavorecidos to set
	 */
	public void setListFavorecidos(List<Favorecidos> listFavorecidos) {
		this.listFavorecidos = listFavorecidos;
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

	/**
	 * @return the contasApagarCad
	 */
	public ContasApagarCad getContasApagarCad() {
		return contasApagarCad;
	}

	/**
	 * @param contasApagarCad
	 *            the contasApagarCad to set
	 */
	public void setContasApagarCad(ContasApagarCad contasApagarCad) {
		this.contasApagarCad = contasApagarCad;
	}

	public CadastroGenericaService<ContasApagarCad> getDefaultServiceApagarImpl() {
		return defaultServiceApagarImpl;
	}

	public void setDefaultServiceApagarImpl(CadastroGenericaService<ContasApagarCad> defaultServiceApagarImpl) {
		this.defaultServiceApagarImpl = defaultServiceApagarImpl;
	}

	public CadastroGenericaService<Favorecidos> getDefaultServiceImple() {
		return defaultServiceImple;
	}

	public void setDefaultServiceImple(CadastroGenericaService<Favorecidos> defaultServiceImple) {
		this.defaultServiceImple = defaultServiceImple;
	}

}
