package br.com.homeoffice.core;

public enum UFVO {

	ESCOLHA, RS, SC, PR, SP, MG, ES, RJ, MS, MT, RO, AC, AM, RR, TO, GO, PA, MA, PB, BA, AL, CE, SE, DF, RN, PE, PI, AP;

	public boolean isSelecionado() {
		return !ESCOLHA.equals(this);
	}

	public boolean isNotSelecionado() {
		return !isSelecionado();
	}

}
