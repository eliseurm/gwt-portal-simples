package br.eng.eliseu.gwt.portalSimples.gin;

import br.eng.eliseu.gwt.portalSimples.client.AppController;
import br.eng.eliseu.gwt.portalSimples.client.mvp.PortalResource;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules({ 
	PortalGinModule.class
}) 

public interface PortalGinjector extends Ginjector {
	AppController getAppController();
	PortalResource getPortalResource();
}