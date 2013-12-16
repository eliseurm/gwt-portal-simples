package br.eng.eliseu.gwt.portalSimples.client.service;

import br.eng.eliseu.gwt.portalSimples.model.Usuario;
import br.eng.eliseu.gwt.portalSimples.model.UsuarioVO;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService{

	public UsuarioVO autenticaUsuario(String usuario, String senha) throws Exception;

	public void desconecta() throws Exception;

	public Usuario getUsuarioAutenticado() throws Exception;


}
