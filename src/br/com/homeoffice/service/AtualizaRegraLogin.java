package br.com.homeoffice.service;

import br.com.homeoffice.core.Login;
import br.com.homeoffice.core.Role;

public interface AtualizaRegraLogin {
	
	void atualizaRegraLogin(String idRole, Role role, Login login);

}
