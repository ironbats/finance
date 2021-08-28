package br.com.homeoffice.core;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "caixapessoal")
public class CaixaPessoal implements Serializable

{

	private static final long serialVersionUID = -6372753418854132901L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCaixapessoal;

	@Column
	private Double ValoremCaixa;
	
	

	public Integer getIdCaixapessoal() {
		return idCaixapessoal;
	}

	public void setIdCaixapessoal(Integer idCaixapessoal) {
		this.idCaixapessoal = idCaixapessoal;
	}

	public Double getValoremCaixa() {
		return ValoremCaixa;
	}

	public void setValoremCaixa(Double valoremCaixa) {
		ValoremCaixa = valoremCaixa;
	}
	
	

}
