package br.com.homeoffice.core;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "bancos")
public class Bancos implements Serializable

{

	private static final long serialVersionUID = -1607923238387876909L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idBancos;

	@Column
	private Double limiteBanco;
	@Column
	private Double jurosCredito;
	@Column(length = 1)
	private char Cheques;

	@Column
	@NotNull
	private String nomeBanco;

	@Column
	@NotNull
	private String agencia;

	@Column
	@NotNull
	private String contaCorrente;

	public Bancos() {
	}

	public Bancos(Integer idBancos, Double limiteBanco, Double jurosCredito, char cheques, String nomeBanco,
			String agencia, String contaCorrente) {
		super();
		this.idBancos = idBancos;
		this.limiteBanco = limiteBanco;
		this.jurosCredito = jurosCredito;
		Cheques = cheques;
		this.nomeBanco = nomeBanco;
		this.agencia = agencia;
		this.contaCorrente = contaCorrente;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Bancos [idBancos=" + idBancos + ", limiteBanco=" + limiteBanco + ", jurosCredito=" + jurosCredito
				+ ", Cheques=" + Cheques + ", nomeBanco=" + nomeBanco + ", agencia=" + agencia + ", contaCorrente="
				+ contaCorrente + "]";
	}

	/**
	 * @return the idBancos
	 */
	public Integer getIdBancos() {
		return idBancos;
	}

	/**
	 * @param idBancos
	 *            the idBancos to set
	 */
	public void setIdBancos(Integer idBancos) {
		this.idBancos = idBancos;
	}

	/**
	 * @return the limiteBanco
	 */
	public Double getLimiteBanco() {
		return limiteBanco;
	}

	/**
	 * @param limiteBanco
	 *            the limiteBanco to set
	 */
	public void setLimiteBanco(Double limiteBanco) {
		this.limiteBanco = limiteBanco;
	}

	/**
	 * @return the jurosCredito
	 */
	public Double getJurosCredito() {
		return jurosCredito;
	}

	/**
	 * @param jurosCredito
	 *            the jurosCredito to set
	 */
	public void setJurosCredito(Double jurosCredito) {
		this.jurosCredito = jurosCredito;
	}

	/**
	 * @return the cheques
	 */
	public char getCheques() {
		return Cheques;
	}

	/**
	 * @param cheques
	 *            the cheques to set
	 */
	public void setCheques(char cheques) {
		Cheques = cheques;
	}

	/**
	 * @return the nomeBanco
	 */
	public String getNomeBanco() {
		return nomeBanco;
	}

	/**
	 * @param nomeBanco
	 *            the nomeBanco to set
	 */
	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

	/**
	 * @return the agencia
	 */
	public String getAgencia() {
		return agencia;
	}

	/**
	 * @param agencia
	 *            the agencia to set
	 */
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	/**
	 * @return the contaCorrente
	 */
	public String getContaCorrente() {
		return contaCorrente;
	}

	/**
	 * @param contaCorrente
	 *            the contaCorrente to set
	 */
	public void setContaCorrente(String contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Cheques;
		result = prime * result + ((agencia == null) ? 0 : agencia.hashCode());
		result = prime * result + ((contaCorrente == null) ? 0 : contaCorrente.hashCode());
		result = prime * result + ((idBancos == null) ? 0 : idBancos.hashCode());
		result = prime * result + ((jurosCredito == null) ? 0 : jurosCredito.hashCode());
		result = prime * result + ((limiteBanco == null) ? 0 : limiteBanco.hashCode());
		result = prime * result + ((nomeBanco == null) ? 0 : nomeBanco.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bancos other = (Bancos) obj;
		if (Cheques != other.Cheques)
			return false;
		if (agencia == null) {
			if (other.agencia != null)
				return false;
		} else if (!agencia.equals(other.agencia))
			return false;
		if (contaCorrente == null) {
			if (other.contaCorrente != null)
				return false;
		} else if (!contaCorrente.equals(other.contaCorrente))
			return false;
		if (idBancos == null) {
			if (other.idBancos != null)
				return false;
		} else if (!idBancos.equals(other.idBancos))
			return false;
		if (jurosCredito == null) {
			if (other.jurosCredito != null)
				return false;
		} else if (!jurosCredito.equals(other.jurosCredito))
			return false;
		if (limiteBanco == null) {
			if (other.limiteBanco != null)
				return false;
		} else if (!limiteBanco.equals(other.limiteBanco))
			return false;
		if (nomeBanco == null) {
			if (other.nomeBanco != null)
				return false;
		} else if (!nomeBanco.equals(other.nomeBanco))
			return false;
		return true;
	}

}
