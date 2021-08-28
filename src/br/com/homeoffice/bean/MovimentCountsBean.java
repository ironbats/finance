package br.com.homeoffice.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.homeoffice.core.ContasAreceberCad;
import br.com.homeoffice.dao.impl.DefaultDaoGenericsImpl;


@ManagedBean(name = "movimentCounts")
@SessionScoped
public class MovimentCountsBean
{
	DefaultDaoGenericsImpl<ContasAreceberCad> daoReceber = new DefaultDaoGenericsImpl<ContasAreceberCad>(ContasAreceberCad.class);
	List<ContasAreceberCad> listReceber = new ArrayList<ContasAreceberCad>();

	/**
	 *
	 * @return List
	 */
	public List<ContasAreceberCad> getListReceber()
	{
		return daoReceber.listarObjetos(ContasAreceberCad.class);
	}





}
