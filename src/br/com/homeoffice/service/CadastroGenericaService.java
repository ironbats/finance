package br.com.homeoffice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.homeoffice.core.Login;
@Service
public interface CadastroGenericaService <T> {
	
	
	public void  SalvarDados (T t);	
	
	public void atualizarObjeto(T t);
	
	public void removerObjeto(T t);
	
	public List<T> listarObjetos(Class<T> classe);
	
	public T buscarObjetoPorId(Integer id);
	
	public List<Login> buscarPorLogin(Login bean);
	
	public String buscarPorLoginString(String userName);


}
