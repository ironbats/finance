package br.com.homeoffice.core;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "CONTAS_PAGAR")
public class ContasApagarCad extends CountsPagAndRec implements Serializable
{

	private static final long serialVersionUID = -6936168979687973189L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idempresa;
	@Column
	private String nomeEmpresa;
	@Column
	@NotNull
	private String CNPJ;
	@Column
	@NotNull
	private String enderecoEmpresa;
	@Column
	@NotNull
	private String telefoneContato;

	@Column
	@Enumerated(EnumType.STRING)
	private UFVO ufvo;

	@Column
	private Double valorApagar;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastroConta;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataVencimento;

	@NotNull
	@Column
	@Enumerated(EnumType.STRING)
	private TipoPagamentoVO descricaoOuTipoPagamento;

	@OneToOne
	@JoinColumn(name = "idfavorecidos")
	private Favorecidos empapagar;



	public Integer getIdempresa()
	{
		return idempresa;
	}

	public void setIdempresa(Integer idempresa)
	{
		this.idempresa = idempresa;
	}

	public String getNomeEmpresa()
	{
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa)
	{
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getCNPJ()
	{
		return CNPJ;
	}

	public void setCNPJ(String cNPJ)
	{
		CNPJ = cNPJ;
	}

	public String getEnderecoEmpresa()
	{
		return enderecoEmpresa;
	}

	public void setEnderecoEmpresa(String enderecoEmpresa)
	{
		this.enderecoEmpresa = enderecoEmpresa;
	}

	public String getTelefoneContato()
	{
		return telefoneContato;
	}

	public void setTelefoneContato(String telefoneContato)
	{
		this.telefoneContato = telefoneContato;
	}

	public UFVO getUfvo()
	{
		return ufvo;
	}

	public void setUfvo(UFVO ufvo)
	{
		this.ufvo = ufvo;
	}

	public Double getValorApagar()
	{
		return valorApagar;
	}

	public void setValorApagar(Double valorApagar)
	{
		this.valorApagar = valorApagar;
	}

	public Date getDataCadastroConta()
	{
		return dataCadastroConta;
	}

	public void setDataCadastroConta(Date dataCadastroConta)
	{
		this.dataCadastroConta = dataCadastroConta;
	}

	public Date getDataVencimento()
	{
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento)
	{
		this.dataVencimento = dataVencimento;
	}




	public TipoPagamentoVO getDescricaoOuTipoPagamento()
	{
		return descricaoOuTipoPagamento;
	}

	public void setDescricaoOuTipoPagamento(TipoPagamentoVO descricaoOuTipoPagamento)
	{
		this.descricaoOuTipoPagamento = descricaoOuTipoPagamento;
	}

	public Favorecidos getEmpapagar()
	{
		return empapagar;
	}

	public void setEmpapagar(Favorecidos empapagar)
	{
		this.empapagar = empapagar;
	}

}
