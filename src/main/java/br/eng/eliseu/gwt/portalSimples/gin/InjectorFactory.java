package br.eng.eliseu.gwt.portalSimples.gin;

import com.google.gwt.core.client.GWT;

public class InjectorFactory {
	private final PortalGinjector injector;
	private static InjectorFactory instance;

	private InjectorFactory() {
		injector = GWT.create(PortalGinjector.class);
	}
		
	public static PortalGinjector getInjector() {
		if(instance == null) {
			instance = new InjectorFactory();
		}

		return instance.injector;
	}
}
