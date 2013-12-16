package br.eng.eliseu.gwt.portalSimples.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface LogoutEventHandler extends EventHandler{

	public void onLogout(LogoutEvent logoutEvent);
}