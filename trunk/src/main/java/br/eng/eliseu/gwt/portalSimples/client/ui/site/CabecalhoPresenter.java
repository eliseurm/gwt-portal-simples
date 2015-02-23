package br.eng.eliseu.gwt.portalSimples.client.ui.site;

import br.eng.eliseu.gwt.portalSimples.client.event.LoginEvent;
import br.eng.eliseu.gwt.portalSimples.client.event.LoginEventHandler;
import br.eng.eliseu.gwt.portalSimples.client.event.LogoutEvent;
import br.eng.eliseu.gwt.portalSimples.client.event.LogoutEventHandler;
import br.eng.eliseu.gwt.portalSimples.client.event.PlaceEvent;
import br.eng.eliseu.gwt.portalSimples.client.mvp.PortalResource;
import br.eng.eliseu.gwt.portalSimples.client.mvp.presenter.BasePresenter;
import br.eng.eliseu.gwt.portalSimples.client.mvp.presenter.PresenterMenuEnum;
import br.eng.eliseu.gwt.portalSimples.client.mvp.view.BaseDisplay;
import br.eng.eliseu.gwt.portalSimples.client.service.LoginService;
import br.eng.eliseu.gwt.portalSimples.client.service.LoginServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;

public class CabecalhoPresenter extends BasePresenter {

	public interface Display extends BaseDisplay {
		public Panel getEntrarPanel();
		public HasClickHandlers getEntrarButton();
		public Panel getSairPanel();
		public Label getNomeUsuarioLabel();
		public HasClickHandlers getSairButton();

	}

	private final PortalResource recursos;
	private final Display display;

	LoginServiceAsync service = GWT.create(LoginService.class);

	public CabecalhoPresenter(Display display, PortalResource recursos ) {
		this.recursos = recursos;
		this.display = display;
		bind();
	}

	public void bind() {
		getDisplay().getSairPanel().setVisible(false);
		
		// --- Evendo do click do botao Entrar
	    getDisplay().getEntrarButton().addClickHandler(new ClickHandler() {   
	        public void onClick(ClickEvent event) {
	        	// --- Abre a tela de Login
	    		PlaceEvent eventLogin = new PlaceEvent();
	    		eventLogin.setState(PresenterMenuEnum.LOGIN);
	    		getRecursos().getEventBus().fireEvent(eventLogin);

	        }
	      });

	    // --- Evento de click do botao Sair
	    getDisplay().getSairButton().addClickHandler(new ClickHandler() {   
	        public void onClick(ClickEvent event) {
	        	// --- Faz o Logout, se der certo aciona o evento Logout
	    		service.desconecta(new AsyncCallback<Void>(){

	    			public void onSuccess(Void arg0) {
	    				LogoutEvent event = new LogoutEvent();
	    				getRecursos().getEventBus().fireEvent(event);
	    			}

	    			public void onFailure(Throwable arg0) {
	    			}
	    		});

	        }
	      });
	    
	    // --- Evento de quando estiver Logado
		getRecursos().getEventBus().addHandler(LoginEvent.TYPE, new LoginEventHandler() {
			public void onProcessa(LoginEvent event) {
				// --- Se esta logado
				getDisplay().getEntrarPanel().setVisible(false);
				getDisplay().getSairPanel().setVisible(true);
				getDisplay().getNomeUsuarioLabel().setText("Ola, "+event.getUsuarioVO().getUsuario().getUsuario());
			}
		});	

		// --- Evento de quando estiver Logof
		getRecursos().getEventBus().addHandler(LogoutEvent.TYPE, new LogoutEventHandler() {
			public void onLogout(LogoutEvent logoutEvent) {
				// --- Se esta Logout some o botao Sair e exibe o botao Entrar
				getDisplay().getEntrarPanel().setVisible(true);
				getDisplay().getSairPanel().setVisible(false);
			}
		});	

	}

	public void go() {
		// TODO Auto-generated method stub
		
	}
	public Display getDisplay() {
		return display;
	}
	public PortalResource getRecursos() {
		return recursos;
	}


}
