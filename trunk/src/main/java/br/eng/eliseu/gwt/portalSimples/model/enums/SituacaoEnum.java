package br.eng.eliseu.gwt.portalSimples.model.enums;

public enum SituacaoEnum {

	ATIVO( "Ativo"),
	INATIVO( "Inativo"),
	;
	
	private String titulo;
	
	private SituacaoEnum( String titulo ){
		this.setTitulo(titulo);
	}

	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
