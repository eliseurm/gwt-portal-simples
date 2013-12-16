package br.eng.eliseu.gwt.portalSimples.client.service;

import br.eng.eliseu.gwt.portalSimples.model.Usuario;
import br.eng.eliseu.gwt.portalSimples.model.UsuarioVO;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface LoginServiceAsync {

	void autenticaUsuario(String usuario, String senha, AsyncCallback<UsuarioVO> asyncCallback);

	public void desconecta(AsyncCallback<Void> asyncCallback);

	public void getUsuarioAutenticado(AsyncCallback<Usuario> asyncCallback);


}
