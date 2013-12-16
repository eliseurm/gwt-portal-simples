package br.eng.eliseu.gwt.portalSimples.client.event;

import br.eng.eliseu.gwt.portalSimples.model.UsuarioVO;

import com.google.gwt.event.shared.GwtEvent;

public class LoginEvent extends GwtEvent<LoginEventHandler>{

	public static Type<LoginEventHandler> TYPE = new Type<LoginEventHandler>();
	
	private UsuarioVO usuarioVO;
	
	@Override
	public Type<LoginEventHandler> getAssociatedType() {
		return TYPE;
	}
	@Override
	protected void dispatch(LoginEventHandler handler) {
		handler.onProcessa(this);
	}

	public void setUsuarioVO(UsuarioVO vo) {
		this.usuarioVO = vo;
	}
	public UsuarioVO getUsuarioVO() {
		return usuarioVO;
	}
	
}