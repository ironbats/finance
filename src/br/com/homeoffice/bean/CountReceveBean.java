package br.com.homeoffice.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.com.homeoffice.core.ContasAreceberCad;
import br.com.homeoffice.core.Favorecidos;
import br.com.homeoffice.formregistation.FormHomeOfficeContasAreceber;
import br.com.homeoffice.forms.FormAccountReceiv;
import br.com.homeoffice.service.CadastroGenericaService;
import br.com.homeoffice.service.impl.DefaultCadastroGenericsServiceImpl;

@ManagedBean(name = "countReceveBean")
@SessionScoped
public class CountReceveBean extends FormHomeOfficeContasAreceber implements Serializable {

	private static final long serialVersionUID = -5785496001277462289L;

	@Resource(name = "cadastroGenericaService")
	private CadastroGenericaService<Favorecidos> defaultServiceImplFavorecidos = new DefaultCadastroGenericsServiceImpl<Favorecidos>(
			Favorecidos.class);

	@Resource(name = "cadastroGenericaService")
	private CadastroGenericaService<ContasAreceberCad> defaultServiceContasReceberImpl = new DefaultCadastroGenericsServiceImpl<ContasAreceberCad>(
			ContasAreceberCad.class);

	private List<ContasAreceberCad> listReceber = new ArrayList<ContasAreceberCad>();
	private List<Favorecidos> listFavorecidos = new ArrayList<Favorecidos>();
	private ContasAreceberCad receberCad = new ContasAreceberCad();
	private FormAccountReceiv formacount = new FormAccountReceiv();

	private Favorecidos favorecidos = new Favorecidos();

	public String salvarDados() {

		return RegistrationHomeOfficeContasArebecer(formacount);
	}

	public List<ContasAreceberCad> getListContasRecev() {

		return defaultServiceContasReceberImpl.listarObjetos(ContasAreceberCad.class);
	}

	public List<SelectItem> getListFav() {
		this.listFavorecidos = defaultServiceImplFavorecidos.listarObjetos(Favorecidos.class);
		List<SelectItem> selectItem = new ArrayList<SelectItem>();
		for (Favorecidos favorecidosBean : listFavorecidos) {
			selectItem.add(new SelectItem(favorecidosBean.getNomeFavorecidos()));
		}
		return selectItem;
	}

	public List<ContasAreceberCad> getListReceber() {
		return listReceber;
	}

	public void setListReceber(List<ContasAreceberCad> listReceber) {
		this.listReceber = listReceber;
	}

	public ContasAreceberCad getReceberCad() {
		return receberCad;
	}

	public void setReceberCad(ContasAreceberCad receberCad) {
		this.receberCad = receberCad;
	}

	public List<Favorecidos> getListFavorecidos() {
		return listFavorecidos;
	}

	public void setListFavorecidos(List<Favorecidos> listFavorecidos) {
		this.listFavorecidos = listFavorecidos;
	}

	public Favorecidos getFavorecidos() {
		return favorecidos;
	}

	public void setFavorecidos(Favorecidos favorecidos) {
		this.favorecidos = favorecidos;
	}

	public CadastroGenericaService<Favorecidos> getDefaultServiceImplFavorecidos() {
		return defaultServiceImplFavorecidos;
	}

	public void setDefaultServiceImplFavorecidos(CadastroGenericaService<Favorecidos> defaultServiceImplFavorecidos) {
		this.defaultServiceImplFavorecidos = defaultServiceImplFavorecidos;
	}

	public CadastroGenericaService<ContasAreceberCad> getDefaultServiceContasReceberImpl() {
		return defaultServiceContasReceberImpl;
	}

	public void setDefaultServiceContasReceberImpl(
			CadastroGenericaService<ContasAreceberCad> defaultServiceContasReceberImpl) {
		this.defaultServiceContasReceberImpl = defaultServiceContasReceberImpl;
	}

	public FormAccountReceiv getFormacount() {
		return formacount;
	}

	public void setFormacount(FormAccountReceiv formacount) {
		this.formacount = formacount;
	}

}
