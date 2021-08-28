package br.com.homeoffice.core;

import java.io.Serializable;

public class LicenHomeOffice implements Serializable {

private static final long serialVersionUID = -3101085302326927523L;

private Integer idempresaSoliciante;

private String RazaoSocial;

private String CNPJ;

private String enderecoEmpresa;

private String telefoneContato;

private String bairroEmpresa;

private String  cepEmpresa;

private  UFVO ufvo [];

private Integer numero;

/**
 * @return the idempresaSoliciante
 */
public Integer getIdempresaSoliciante() {
	return idempresaSoliciante;
}

/**
 * @param idempresaSoliciante the idempresaSoliciante to set
 */
public void setIdempresaSoliciante(Integer idempresaSoliciante) {
	this.idempresaSoliciante = idempresaSoliciante;
}

/**
 * @return the razaoSocial
 */
public String getRazaoSocial() {
	return RazaoSocial;
}

/**
 * @param razaoSocial the razaoSocial to set
 */
public void setRazaoSocial(String razaoSocial) {
	RazaoSocial = razaoSocial;
}

/**
 * @return the cNPJ
 */
public String getCNPJ() {
	return CNPJ;
}

/**
 * @param cNPJ the cNPJ to set
 */
public void setCNPJ(String cNPJ) {
	CNPJ = cNPJ;
}

/**
 * @return the enderecoEmpresa
 */
public String getEnderecoEmpresa() {
	return enderecoEmpresa;
}

/**
 * @param enderecoEmpresa the enderecoEmpresa to set
 */
public void setEnderecoEmpresa(String enderecoEmpresa) {
	this.enderecoEmpresa = enderecoEmpresa;
}

/**
 * @return the telefoneContato
 */
public String getTelefoneContato() {
	return telefoneContato;
}

/**
 * @param telefoneContato the telefoneContato to set
 */
public void setTelefoneContato(String telefoneContato) {
	this.telefoneContato = telefoneContato;
}

/**
 * @return the bairroEmpresa
 */
public String getBairroEmpresa() {
	return bairroEmpresa;
}

/**
 * @param bairroEmpresa the bairroEmpresa to set
 */
public void setBairroEmpresa(String bairroEmpresa) {
	this.bairroEmpresa = bairroEmpresa;
}

/**
 * @return the cepEmpresa
 */
public String getCepEmpresa() {
	return cepEmpresa;
}

/**
 * @param cepEmpresa the cepEmpresa to set
 */
public void setCepEmpresa(String cepEmpresa) {
	this.cepEmpresa = cepEmpresa;
}

/**
 * @return the ufvo
 */
public UFVO[] getUfvo() {
	return ufvo;
}

/**
 * @param ufvo the ufvo to set
 */
public void setUfvo(UFVO[] ufvo) {
	this.ufvo = ufvo;
}

/**
 * @return the numero
 */
public Integer getNumero() {
	return numero;
}

/**
 * @param numero the numero to set
 */
public void setNumero(Integer numero) {
	this.numero = numero;
}







}
