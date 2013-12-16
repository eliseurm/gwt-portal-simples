package br.eng.eliseu.gwt.portalSimples.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UsuarioVO implements Serializable{

	private static final long serialVersionUID = -4253009157238299019L;

	private Usuario usuario;
	private List<UsuarioAcesso> acesso = new ArrayList<UsuarioAcesso>();
	
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<UsuarioAcesso> getAcesso() {
		return acesso;
	}
	public void setAcesso(List<UsuarioAcesso> acesso) {
		this.acesso = acesso;
	}
	
}
