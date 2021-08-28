package br.com.homeoffice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.homeoffice.core.Login;
import br.com.homeoffice.dao.CadGenericoDaoService;
import br.com.homeoffice.dao.impl.DefaultDaoGenericsImpl;
import br.com.homeoffice.service.CadastroGenericaService;

@Service("cadastroGenericaService")
@Transactional
public class DefaultCadastroGenericsServiceImpl<T> implements CadastroGenericaService<T> {

	private Class<T> classe;

	public DefaultCadastroGenericsServiceImpl(Class<T> classe) {

		this.classe = classe;

	}

	public DefaultCadastroGenericsServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private CadGenericoDaoService<T> cadGenericsDaoService = new DefaultDaoGenericsImpl<T>(classe);

	@Override
	public void SalvarDados(T t) {

		getCadGenericsDaoService().adicionarObjeto(t);

	}

	@Override
	public void atualizarObjeto(T t) {
		getCadGenericsDaoService().atualizarObjeto(t);

	}

	@Override
	public void removerObjeto(T t) {
		getCadGenericsDaoService().removerObjeto(t);

	}

	public Class<T> getClasse() {
		return classe;
	}

	public CadGenericoDaoService<T> getCadGenericsDaoService() {
		return cadGenericsDaoService;
	}

	public void setCadGenericsDaoService(CadGenericoDaoService<T> cadGenericsDaoService) {
		this.cadGenericsDaoService = cadGenericsDaoService;
	}

	@Override
	public List<T> listarObjetos(Class<T> classe) {
		// TODO Auto-generated method stub
		return getCadGenericsDaoService().listarObjetos(classe);
	}

	@Override
	public T buscarObjetoPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Login> buscarPorLogin(Login bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buscarPorLoginString(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

}
