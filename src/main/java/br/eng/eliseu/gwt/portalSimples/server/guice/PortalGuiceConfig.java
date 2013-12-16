package br.eng.eliseu.gwt.portalSimples.server.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;



public class PortalGuiceConfig extends GuiceServletContextListener{

	@Override
	protected final Injector getInjector() {
		return Guice.createInjector(
//				new PortalJPAModule(),
//				new PortalGuiceModule(),
//				new PortalServletModule(),
//				new CoreServletModule(),
//				new CoreGuiceModule()
				);
	}
}