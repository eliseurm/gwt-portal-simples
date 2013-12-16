package br.eng.eliseu.gwt.portalSimples.client.mvp.presenter;

import br.eng.eliseu.gwt.portalSimples.client.handle.DownloadHandler;
import br.eng.eliseu.gwt.portalSimples.client.mvp.PortalResource;
import br.eng.eliseu.gwt.portalSimples.gin.InjectorFactory;
import br.eng.eliseu.gwt.portalSimples.gin.PortalGinjector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;

public class PresenterServiceImpl_Manual extends PresenterServiceImpl implements br.eng.eliseu.gwt.portalSimples.client.mvp.presenter.PresenterService {
  	private String presenterName = new String();
  	private final PortalResource recurso;
  
  	public PresenterServiceImpl_Manual() {
  		PortalGinjector injector = InjectorFactory.getInjector();
  		this.recurso = injector.getPortalResource();
  	}
  
  	public void downloadPresenter(final PresenterCodeEnum presenterCode, final DownloadHandler handler) {
  		setPresenterName( presenterCode.getPresenterName() );
  		if("br.eng.eliseu.gwt.portalSimples.client.ui.site.CabecalhoPresenter".equals(presenterName) 
  		   || "br.eng.eliseu.gwt.portalSimples.client.ui.site.ContatoPresenter".equals(presenterName) 
  		   || "br.eng.eliseu.gwt.portalSimples.client.ui.site.HomePresenter".equals(presenterName) 
  		   || "br.eng.eliseu.gwt.portalSimples.client.ui.site.LoginPresenter".equals(presenterName) 
  		   || "br.eng.eliseu.gwt.portalSimples.client.ui.site.MenuPresenter".equals(presenterName) 
  		   || "br.eng.eliseu.gwt.portalSimples.client.ui.site.RodapePresenter".equals(presenterName) ) { 
  			GWT.runAsync(new RunAsyncCallback() { 
  				public void onSuccess() { 
  					if( "br.eng.eliseu.gwt.portalSimples.client.ui.site.CabecalhoPresenter".equals(presenterName) ) { 
  						br.eng.eliseu.gwt.portalSimples.client.ui.site.CabecalhoView display = new br.eng.eliseu.gwt.portalSimples.client.ui.site.CabecalhoView(); 
  						br.eng.eliseu.gwt.portalSimples.client.ui.site.CabecalhoPresenter presenter = new br.eng.eliseu.gwt.portalSimples.client.ui.site.CabecalhoPresenter(display, recurso);
  						presenter.go();
  						handler.finished(presenter);
  					}else if( "br.eng.eliseu.gwt.portalSimples.client.ui.site.ContatoPresenter".equals(presenterName) ) { 
  						br.eng.eliseu.gwt.portalSimples.client.ui.site.ContatoView display = new br.eng.eliseu.gwt.portalSimples.client.ui.site.ContatoView(); 
  						br.eng.eliseu.gwt.portalSimples.client.ui.site.ContatoPresenter presenter = new br.eng.eliseu.gwt.portalSimples.client.ui.site.ContatoPresenter(display, recurso);
  						presenter.go();
  						handler.finished(presenter);
  					}else if( "br.eng.eliseu.gwt.portalSimples.client.ui.site.HomePresenter".equals(presenterName) ) { 
  						br.eng.eliseu.gwt.portalSimples.client.ui.site.HomeView display = new br.eng.eliseu.gwt.portalSimples.client.ui.site.HomeView(); 
  						br.eng.eliseu.gwt.portalSimples.client.ui.site.HomePresenter presenter = new br.eng.eliseu.gwt.portalSimples.client.ui.site.HomePresenter(display, recurso);
  						presenter.go();
  						handler.finished(presenter);
  					}else if( "br.eng.eliseu.gwt.portalSimples.client.ui.site.LoginPresenter".equals(presenterName) ) { 
  						br.eng.eliseu.gwt.portalSimples.client.ui.site.LoginView display = new br.eng.eliseu.gwt.portalSimples.client.ui.site.LoginView(); 
  						br.eng.eliseu.gwt.portalSimples.client.ui.site.LoginPresenter presenter = new br.eng.eliseu.gwt.portalSimples.client.ui.site.LoginPresenter(display, recurso);
  						presenter.go();
  						handler.finished(presenter);
  					}else if( "br.eng.eliseu.gwt.portalSimples.client.ui.site.MenuPresenter".equals(presenterName) ) { 
  						br.eng.eliseu.gwt.portalSimples.client.ui.site.MenuView display = new br.eng.eliseu.gwt.portalSimples.client.ui.site.MenuView(); 
  						br.eng.eliseu.gwt.portalSimples.client.ui.site.MenuPresenter presenter = new br.eng.eliseu.gwt.portalSimples.client.ui.site.MenuPresenter(display, recurso);
  						presenter.go();
  						handler.finished(presenter);
  					}else if( "br.eng.eliseu.gwt.portalSimples.client.ui.site.RodapePresenter".equals(presenterName) ) { 
  						br.eng.eliseu.gwt.portalSimples.client.ui.site.RodapeView display = new br.eng.eliseu.gwt.portalSimples.client.ui.site.RodapeView(); 
  						br.eng.eliseu.gwt.portalSimples.client.ui.site.RodapePresenter presenter = new br.eng.eliseu.gwt.portalSimples.client.ui.site.RodapePresenter(display, recurso);
  						presenter.go();
  						handler.finished(presenter);
  					}
  				} 
      			public void onFailure(Throwable error) { 
  	 				handler.failure(error); 
  	 			} 
  			}); 
  		}
  		else { 
  			handler.failure(new RuntimeException("(Nao e este) -- Não consegui achar o presenter "+getPresenterName()+", verifique o nome em PresenterCodeEnum")); 
  		}
  	}
  
  	public String getPresenterName() {
  		return presenterName;
  	}
  
  	public void setPresenterName(String presenterName) {
  		this.presenterName = presenterName;
  	}
  
}
