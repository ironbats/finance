package br.com.homeoffice.core;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Favorecidos")
public class Favorecidos implements Serializable {
	private static final long serialVersionUID = -6936168979687973189L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idfavorecidos;
	@Column
	@NotNull
	private String nomeFavorecidos;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	@Column
	private String descricaoFavorecidos;

	/**
	 * @return the idfavorecidos
	 */
	public Integer getIdfavorecidos() {
		return idfavorecidos;
	}

	/**
	 * @param idfavorecidos
	 *            the idfavorecidos to set
	 */
	public void setIdfavorecidos(Integer idfavorecidos) {
		this.idfavorecidos = idfavorecidos;
	}

	/**
	 * @return the nomeFavorecidos
	 */
	public String getNomeFavorecidos() {
		return nomeFavorecidos;
	}

	/**
	 * @param nomeFavorecidos
	 *            the nomeFavorecidos to set
	 */
	public void setNomeFavorecidos(String nomeFavorecidos) {
		this.nomeFavorecidos = nomeFavorecidos;
	}

	/**
	 * @return the dataCadastro
	 */
	public Date getDataCadastro() {
		return dataCadastro;
	}

	/**
	 * @param dataCadastro
	 *            the dataCadastro to set
	 */
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	/**
	 * @return the descricaoFavorecidos
	 */
	public String getDescricaoFavorecidos() {
		return descricaoFavorecidos;
	}

	/**
	 * @param descricaoFavorecidos
	 *            the descricaoFavorecidos to set
	 */
	public void setDescricaoFavorecidos(String descricaoFavorecidos) {
		this.descricaoFavorecidos = descricaoFavorecidos;
	}
	
	
	


	
}
