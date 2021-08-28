package br.com.homeoffice.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import br.com.homeoffice.core.Login;
import br.com.homeoffice.dao.impl.DefaultDaoGenericsImpl;

@WebService(name = "homeOfficeWS")
public class HomeOfficeWS {

	@WebMethod
	public List<Login> listLogins(
			@WebParam(name = "LoginUsuario") String loginUsuario,
			@WebParam(name = "NomeCompleto") String NomeCompleto) {
		Login log = new Login(loginUsuario, loginUsuario);
		DefaultDaoGenericsImpl<Login> daologins = new DefaultDaoGenericsImpl<Login>(Login.class);

		List<Login> listlogins = new ArrayList<Login>();

		listlogins = daologins.listarObjetos(Login.class);
		for (Login login2 : listlogins) {
			log.setLoginUsuario(login2.getLoginUsuario());
			log.setNomeCompleto(login2.getNomeCompleto());
		}
		return listlogins;
	}
}
