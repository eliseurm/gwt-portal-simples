package br.eng.eliseu.gwt.portalSimples.gin;

import br.eng.eliseu.gwt.portalSimples.client.mvp.view.BaseDisplay;
import br.eng.eliseu.gwt.portalSimples.client.mvp.view.Display;

import com.google.gwt.inject.client.AbstractGinModule;

public abstract class PortalBaseGinModuleAbstract extends AbstractGinModule {

	protected <D extends Display> void bindDisplay(Class<D> display, Class<? extends D> displayImpl) {
		
		bind(display).to(displayImpl);
	}

	protected <D extends BaseDisplay> void bindPresenter(
			Class<?> presenter, 
			Class<D> display,
			Class<? extends D> displayImpl) {
		
		bind(presenter);
		bindDisplay(display, displayImpl);
	}


}
