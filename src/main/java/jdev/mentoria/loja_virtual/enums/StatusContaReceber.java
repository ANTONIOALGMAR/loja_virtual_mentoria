package jdev.mentoria.loja_virtual.enums;

public enum StatusContaReceber {
	
	COBRANCA("Pagar"),
	VENCIDA("VENCIDA"),
	ABERTA("Aberta"),
	QUITADA("Quitada");
	
	
	private String descricao;
	
	private StatusContaReceber(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	@Override
	public String toString() {
		return this.descricao;
	}
	
	
	
}
