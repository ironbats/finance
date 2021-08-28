package br.com.homeoffice.formregistation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.context.FacesContext;

import br.com.homeoffice.core.ContasAreceberCad;
import br.com.homeoffice.core.Favorecidos;
import br.com.homeoffice.forms.FormAccountReceiv;
import br.com.homeoffice.service.CadastroGenericaService;
import br.com.homeoffice.service.impl.DefaultCadastroGenericsServiceImpl;

public abstract class FormHomeOfficeContasAreceber {

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

	public String RegistrationHomeOfficeContasArebecer(FormAccountReceiv formacount)

	{
		receberCad.setDataRecebimento(formacount.getDataDeRecebimento());
		receberCad.setNomeEmpresa(formacount.getNomeEmpresa());
		receberCad.setValorAreceber(formacount.getValorAmountReceber());
		defaultServiceContasReceberImpl.SalvarDados(receberCad);
		zeraCampos();
		return "/pages/ContasAreceber.xhtml";

	}

	public void zeraCampos() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();

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

	public List<ContasAreceberCad> getListReceber() {
		return listReceber;
	}

	public void setListReceber(List<ContasAreceberCad> listReceber) {
		this.listReceber = listReceber;
	}

	public List<Favorecidos> getListFavorecidos() {
		return listFavorecidos;
	}

	public void setListFavorecidos(List<Favorecidos> listFavorecidos) {
		this.listFavorecidos = listFavorecidos;
	}

	public ContasAreceberCad getReceberCad() {
		return receberCad;
	}

	public void setReceberCad(ContasAreceberCad receberCad) {
		this.receberCad = receberCad;
	}

	public FormAccountReceiv getFormacount() {
		return formacount;
	}

	public void setFormacount(FormAccountReceiv formacount) {
		this.formacount = formacount;
	}

}
