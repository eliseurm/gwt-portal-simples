package br.eng.eliseu.gwt.portalSimples.client;

import br.eng.eliseu.gwt.portalSimples.client.event.LoginEvent;
import br.eng.eliseu.gwt.portalSimples.client.event.LoginEventHandler;
import br.eng.eliseu.gwt.portalSimples.client.event.LogoutEvent;
import br.eng.eliseu.gwt.portalSimples.client.event.LogoutEventHandler;
import br.eng.eliseu.gwt.portalSimples.client.event.PlaceEvent;
import br.eng.eliseu.gwt.portalSimples.client.event.PlaceEventHandler;
import br.eng.eliseu.gwt.portalSimples.client.handle.DownloadHandler;
import br.eng.eliseu.gwt.portalSimples.client.mvp.PortalResource;
import br.eng.eliseu.gwt.portalSimples.client.mvp.presenter.Presenter;
import br.eng.eliseu.gwt.portalSimples.client.mvp.presenter.PresenterCodeEnum;
import br.eng.eliseu.gwt.portalSimples.client.mvp.presenter.PresenterService;
import br.eng.eliseu.gwt.portalSimples.client.mvp.presenter.PresenterServiceImpl;
import br.eng.eliseu.gwt.portalSimples.client.mvp.view.BaseDisplay;
import br.eng.eliseu.gwt.portalSimples.client.ui.MainPresenter;
import br.eng.eliseu.gwt.portalSimples.client.ui.MainView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;

public class AppController implements Presenter {

	private final PresenterService presenterViewService;
	private final PortalResource recursos;

	private MainPresenter mainPresenter;
	private HasWidgets container;
	
	@Inject
	public AppController( MainPresenter mainPresenter,  PortalResource recurso) {
		presenterViewService = GWT.create(PresenterServiceImpl.class);

		this.mainPresenter = mainPresenter;
		this.recursos = recurso;
		
		bind();
	}

	public void bind() {
		// --- Implementaar os eventos Globais Aqui
		
		// Sempre que um evento EventBus.fireEvent( new PlaceEvent.. ) for invocado este evento sera executado
		getRecursos().getEventBus().addHandler(PlaceEvent.TYPE, new PlaceEventHandler() {

			public void onChangePlace(PlaceEvent event) {
				PresenterCodeEnum states = event.getState();
				showPresenterCode(states);
			}
		});	

		// --- sempre que um evento LogOUT for acionado 
		getRecursos().getEventBus().addHandler(LogoutEvent.TYPE, new LogoutEventHandler() {
			public void onLogout(LogoutEvent logoutEvent) {
				// --- Seto null nas variaveis de recursos do sistema
				getRecursos().setUsrAutentidado(null);

				reconstroiTelaPrincipal();
				
			}
		});	

		// --- sempre que um evento LogIN for acionado 
		getRecursos().getEventBus().addHandler(LoginEvent.TYPE, new LoginEventHandler() {

			public void onProcessa(LoginEvent event) {
				// --- Seto o usuario nas variaveis de recursos do sistema
				getRecursos().setUsrAutentidado(event.getUsuarioVO());
				
				reconstroiTelaPrincipal();

			}
		});	

		
		
	}

	// --- Comessa aqui
	public void init(HasWidgets container) {
		this.container = container;
		
		// --- Inicia o Presenter principal "main"
		if ( this.mainPresenter==null){
			// --- So entra aqui se o Injetor nao consegui instanciar o MainPresenter
			this.mainPresenter = new MainPresenter(new MainView(), getRecursos());
		}
		
		if (this.container!=null){
			this.container.clear();
			mainPresenter.go();
			this.container.add( mainPresenter.getDisplay().asWidget() );

		}
		
		getRecursos().getPlaceManager().currentPlace();
		
		
	}
	
	
	private void showPresenterCode(final PresenterCodeEnum s) {
		
		presenterViewService.downloadPresenter(s, new DownloadHandler() {
			
			public void finished(final Presenter presenter) {
				mainPresenter.getDisplay().showState(presenter, s);
			}

			public void failure(Throwable error) {
				System.out.println("Download error: "+error.getMessage()+" ("+s.getNome()+")");
				error.printStackTrace();
			}
		});
	}

	private void reconstroiTelaPrincipal() {
		// --- Reconstruo o Menu e chamo o HOME
		PlaceEvent placeEvent = new PlaceEvent();
		placeEvent.setState(PresenterCodeEnum.MENU);
		getRecursos().getEventBus().fireEvent(placeEvent);
		
		getRecursos().getPlaceManager().newPlace(PresenterCodeEnum.HOME);
	}
	
	
	
	public void go() {
		// TODO Auto-generated method stub
	}

	public BaseDisplay getDisplay() {
		return null;
	}
	public PortalResource getRecursos() {
		return recursos;
	}


}
