package br.com.homeoffice.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.homeoffice.core.Login;

/**
 * 
 * @author Felipe Rodrigues
 * @since HomeOffice 1.0 RELEASE
 * @version 1.0.0
 * @category Projeto Financeiro
 * @serial Serial Version
 * 
 *         Classe service Responsavel por realizar a persistencia dos dados de
 *         Login e senha onde diretamente daqui criamos os parametros de Select
 *         da tabela no banco de dados sendo assim temos a persistencia do JPA
 *         para detalhar os atributos da Classe e realizar um check nas tabelas
 *         do banco
 * 
 */
@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
// Persistencia de dados
	@PersistenceContext
	private EntityManager entityManager;

	public UserDetails loadUserByUsername(String LoginUsuario)
			throws UsernameNotFoundException {
		return findByLoginUsuario(LoginUsuario);

	}

	private Login findByLoginUsuario(String LoginUsuario) {
		try {
			return entityManager
					.createNamedQuery("Login.findByLoginUsuario", Login.class)
					.setParameter("LoginUsuario", LoginUsuario)
					.getSingleResult();

		} catch (NoResultException e) {
			throw new UsernameNotFoundException("Usuario nao encontrado");
		}
	}

}
