package br.com.homeoffice.core;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Felipe Rodrigues
 * @since HomeOffice 1.0 RELEASE
 * @version 1.0.0
 * @category Projeto Financeiro Classe Role onde criaremos as regras de acesso
 *           ao sistema sendo assim essa classe Ira definir as regras de acesso
 *           para cada Usuario
 * 
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {

	private static final long serialVersionUID = -6120861541027751228L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idrole;

	@Column(name = "nomeRole")
	private String nomeRole;

	public Integer getIdrole() {
		return idrole;
	}

	public void setIdrole(Integer idrole) {
		this.idrole = idrole;
	}

	public String getNomeRole() {
		return nomeRole;
	}

	public void setNomeRole(String nomeRole) {
		this.nomeRole = nomeRole;
	}

}
