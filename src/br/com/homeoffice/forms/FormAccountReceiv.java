package br.com.homeoffice.forms;

import java.util.Date;

public class FormAccountReceiv {
	
	private String nomeEmpresa;
	private Double ValorAmountReceber;
	private Date  dataDeRecebimento;
	
	public String getNomeEmpresa() {
		return nomeEmpresa;
	}
	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}
	public Double getValorAmountReceber() {
		return ValorAmountReceber;
	}
	public void setValorAmountReceber(Double valorAmountReceber) {
		ValorAmountReceber = valorAmountReceber;
	}
	public Date getDataDeRecebimento() {
		return dataDeRecebimento;
	}
	public void setDataDeRecebimento(Date dataDeRecebimento) {
		this.dataDeRecebimento = dataDeRecebimento;
	}

}
