package br.com.homeoffice.core;

public enum TipoPagamentoVO {
	
	ESCOLHA,DEBITO,DINHEIRO,CREDITO,CHEQUE,DEBITO_EM_CONTA,TRANSFERENCIA_BANCO;
	
	public boolean isSelecionado() {
		return !ESCOLHA.equals(this);
	}

	public boolean isNotSelecionado() {
		return !isSelecionado();
	}


}
