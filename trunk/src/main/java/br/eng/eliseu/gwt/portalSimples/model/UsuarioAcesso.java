package br.eng.eliseu.gwt.portalSimples.model;

import java.io.Serializable;

import br.eng.eliseu.gwt.portalSimples.client.mvp.presenter.PresenterCodeEnum;

public class UsuarioAcesso implements Serializable{

	private static final long serialVersionUID = 45735588539347328L;

	
	private Integer id;
	private Usuario usuario;
	private PresenterCodeEnum tela;
	
	private Boolean visualizacao = false;
	private Boolean alteracao = false;
	private Boolean exclusao = false;
	private Boolean impressao = false;
	
	

	public UsuarioAcesso() {
		super();
	}
	
	public UsuarioAcesso(Integer id, Usuario usuario, PresenterCodeEnum tela,
			Boolean visualizacao, Boolean alteracao, Boolean exclusao, Boolean impressao) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.tela = tela;
		this.visualizacao = visualizacao;
		this.alteracao = alteracao;
		this.exclusao = exclusao;
		this.impressao = impressao;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public PresenterCodeEnum getTela() {
		return tela;
	}
	public void setTela(PresenterCodeEnum tela) {
		this.tela = tela;
	}
	public Boolean getVisualizacao() {
		return visualizacao;
	}
	public void setVisualizacao(Boolean visualizacao) {
		this.visualizacao = visualizacao;
	}
	public Boolean getAlteracao() {
		return alteracao;
	}
	public void setAlteracao(Boolean alteracao) {
		this.alteracao = alteracao;
	}
	public Boolean getExclusao() {
		return exclusao;
	}
	public void setExclusao(Boolean exclusao) {
		this.exclusao = exclusao;
	}
	public Boolean getImpressao() {
		return impressao;
	}
	public void setImpressao(Boolean impressao) {
		this.impressao = impressao;
	}

	
	
}
