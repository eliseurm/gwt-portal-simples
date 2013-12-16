package br.eng.eliseu.gwt.portalSimples.client;

import br.eng.eliseu.gwt.portalSimples.gin.InjectorFactory;
import br.eng.eliseu.gwt.portalSimples.gin.PortalGinjector;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class PortalSimples implements EntryPoint {
 
  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
		PortalGinjector injector = InjectorFactory.getInjector();
		AppController appController = injector.getAppController();
		appController.init(RootPanel.get());

	  
	  
//	    MainServiceAsync mainService = GWT.create(MainService.class);    
//	    HandlerManager eventBus = new HandlerManager(null);
//	    AppController appViewer = new AppController(mainService, eventBus);
//	    appViewer.init(RootPanel.get());
  }
}
