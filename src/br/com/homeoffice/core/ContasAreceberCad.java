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

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "CONTAS_RECEBER")
public class ContasAreceberCad extends CountsPagAndRec implements Serializable

{

	private static final long serialVersionUID = -1679811611562425378L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idempresasareceber;

	@Column
	@NotNull
	private Double valorAreceber;

	@Column
	@NotNull
	@NotEmpty
	private String nomeEmpresa;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataRecebimento;

	public ContasAreceberCad()
	{
		super();

	}

	public Integer getIdempresasareceber()
	{
		return idempresasareceber;
	}

	public void setIdempresasareceber(Integer idempresasareceber)
	{
		this.idempresasareceber = idempresasareceber;
	}

	public Double getValorAreceber()
	{
		return valorAreceber;
	}

	public void setValorAreceber(Double valorAreceber)
	{
		this.valorAreceber = valorAreceber;
	}

	public Date getDataRecebimento()
	{
		return dataRecebimento;
	}

	public void setDataRecebimento(Date dataRecebimento)
	{
		this.dataRecebimento = dataRecebimento;
	}

	public String getNomeEmpresa()
	{
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa)
	{
		this.nomeEmpresa = nomeEmpresa;
	}



}
