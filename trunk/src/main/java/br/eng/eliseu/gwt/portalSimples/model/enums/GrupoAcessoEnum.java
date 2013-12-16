package br.eng.eliseu.gwt.portalSimples.model.enums;


public enum GrupoAcessoEnum {

	ADMINISTRADOR( 0, "Administrador"),
	USUARIO( 10, "Usuario"),
	CONVIDADO( 99, "Convidado"),
	;
	
	private Integer nivel;
	private String titulo;

	
	private GrupoAcessoEnum( Integer nivel, String nome ){
		this.setTitulo(nome);
	}


	public Integer getNivel() {
		return nivel;
	}
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
