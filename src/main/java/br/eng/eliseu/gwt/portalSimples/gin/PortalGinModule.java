package br.eng.eliseu.gwt.portalSimples.gin;

import br.eng.eliseu.gwt.portalSimples.client.AppController;
import br.eng.eliseu.gwt.portalSimples.client.mvp.PlaceManager;
import br.eng.eliseu.gwt.portalSimples.client.mvp.PortalResource;
import br.eng.eliseu.gwt.portalSimples.client.ui.MainPresenter;
import br.eng.eliseu.gwt.portalSimples.client.ui.MainView;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.inject.Singleton;

public class PortalGinModule extends PortalBaseGinModuleAbstract {

	@Override
	protected void configure() {
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		bind(PlaceManager.class).in(Singleton.class);
		bind(PortalResource.class).in(Singleton.class);
		bindPresenter(MainPresenter.class, MainPresenter.Display.class, MainView.class);	
		bind(AppController.class).in(Singleton.class);
	}


}
