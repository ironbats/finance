package br.com.homeoffice.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 
 * @author Felipe Rodrigues
 * @since HomeOffice 1.0 RELEASE
 * @version 1.0.0
 * @category Projeto Financeiro
 * 
 *           Class Serializada e implementada o UserDetails que possuem metodos
 *           do Spring de Seguranca e verificam se o Usuario possui a Permissao
 *           e se ele existe no sistema Usando NamedQueries do JPA
 *
 */

@Entity
@NamedQueries({ @NamedQuery(name = "Login.findByLoginUsuario", query = "SELECT usu FROM Login usu WHERE usu.LoginUsuario = :LoginUsuario") })
@Table(name = "Login")
public class Login implements Serializable, UserDetails {

	/**
	 * Serial Version na Classe Core
	 * 
	 * @serial
	 */
	private static final long serialVersionUID = 8513365123301881921L;


	/**
	 * Atributos onde alguns campos nao podem ser nulos e ja estao filtrados por
	 * anotacoes JPA.
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idLogin;

	@Column(name = "LoginUsuario", length = 8)
	private String LoginUsuario;

	@Column(name = "SenhaUsuario", length = 6)
	private String SenhaUsuario;

	@Column(name = "NomeCompleto", length = 30)
	@NotNull
	private String NomeCompleto;

	@Column(name = "emailUsuario")
	private String emailUsuario;

	@Column(name = "cargo")
	private String cargo;
	@Column(name = "DepartamentoUsuario")
	private String DepartamentoUsuario;

	@Column(name = "ativo")
	private boolean ativo;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOfBirthday;

	/**
	 * Funcao Muitos pra Muitos em Anotations ou muitas permissoes para muitos
	 * logins Criados
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Role> roles;

	/**
	 * Metodo que ira definir a autoridade do sistemas o metodo retorna uma
	 * regra e aplica ao usuario definido pelo sistema
	 */
	@Transient
	public Collection<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> result = new ArrayList<GrantedAuthority>();
		for (Role role : roles) {
			result.add(new GrantedAuthorityImpl(role.getNomeRole()));
		}
		return result;

	}
	
	public Login() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Metodos get e Set
	 */
	
	/**
	 * Construtor Super
	 */
	public Login(String LoginUsuario, String NomeCompleto) {
		super();
		this.LoginUsuario  = LoginUsuario;
		this.NomeCompleto = NomeCompleto;
	}


	@Override
	public String getPassword() {

		return SenhaUsuario;
	}

	@Override
	public String getUsername() {
		return LoginUsuario;
	}

	@Transient
	public boolean isEnabled() {
		return true;
	}

	@Transient
	public boolean isAccountNonExpired() {
		return true;
	}

	@Transient
	public boolean isAccountNonLocked() {
		return true;
	}

	@Transient
	public boolean isCredentialsNonExpired() {
		return true;
	}

	public Integer getIdLogin() {
		return idLogin;
	}

	public void setIdLogin(Integer idLogin) {
		this.idLogin = idLogin;
	}

	public String getLoginUsuario() {
		return LoginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		LoginUsuario = loginUsuario;
	}

	public String getSenhaUsuario() {
		return SenhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		SenhaUsuario = senhaUsuario;
	}

	public String getNomeCompleto() {
		return NomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		NomeCompleto = nomeCompleto;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getDepartamentoUsuario() {
		return DepartamentoUsuario;
	}

	public void setDepartamentoUsuario(String departamentoUsuario) {
		DepartamentoUsuario = departamentoUsuario;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Date getDateOfBirthday() {
		return dateOfBirthday;
	}

	public void setDateOfBirthday(Date dateOfBirthday) {
		this.dateOfBirthday = dateOfBirthday;
	}
	
	

}
