package br.eng.eliseu.gwt.portalSimples.model;

import java.io.Serializable;

import br.eng.eliseu.gwt.portalSimples.model.enums.GrupoAcessoEnum;
import br.eng.eliseu.gwt.portalSimples.model.enums.SituacaoEnum;

public class Usuario implements Serializable{

	private static final long serialVersionUID = 750992139387920829L;

	
	private Integer id;
	private String usuario;
	private String senha;
	
	private SituacaoEnum situacao;
	private GrupoAcessoEnum grupo;
	
	public Usuario(){}
	
	public Usuario(Integer id, String usuario, String senha) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.senha = senha;
	}
	
	public Usuario(Integer id, String usuario, String senha, SituacaoEnum situacao, GrupoAcessoEnum grupo ) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.senha = senha;
		this.situacao = situacao;
		this.grupo = grupo;
	}
	
	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	public String getUsuario() { return usuario; }
	public void setUsuario(String usuario) { this.usuario = usuario; }
	public String getSenha() { return senha; }
	public void setSenha(String senha) { this.senha = senha; }
	public SituacaoEnum getSituacao() { return situacao; }
	public void setSituacao(SituacaoEnum situacao) { this.situacao = situacao; }
	public GrupoAcessoEnum getGrupo() { return grupo; }
	public void setGrupo(GrupoAcessoEnum grupo) { this.grupo = grupo; }
	
}
