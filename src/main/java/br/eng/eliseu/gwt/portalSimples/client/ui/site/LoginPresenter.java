package br.eng.eliseu.gwt.portalSimples.client.ui.site;

import br.eng.eliseu.gwt.portalSimples.client.event.LoginEvent;
import br.eng.eliseu.gwt.portalSimples.client.mvp.PortalResource;
import br.eng.eliseu.gwt.portalSimples.client.mvp.presenter.BasePresenter;
import br.eng.eliseu.gwt.portalSimples.client.mvp.view.BaseDisplay;
import br.eng.eliseu.gwt.portalSimples.client.service.LoginService;
import br.eng.eliseu.gwt.portalSimples.client.service.LoginServiceAsync;
import br.eng.eliseu.gwt.portalSimples.model.UsuarioVO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;

public class LoginPresenter extends BasePresenter{

	
	public interface Display extends BaseDisplay {
		public HasValue<String> getUsuarioTBox();
		public HasValue<String> getSenhaTBox();
		public HasClickHandlers getEntrarButton();
	}
	
	private final PortalResource recursos;
	private final Display display;

	
	LoginServiceAsync service = GWT.create(LoginService.class);
	
	public LoginPresenter(Display display, PortalResource recursos ) {
		this.recursos = recursos;
		this.display = display;
		bind();
	}

	public void bind() {
		// --- Evendo do click do botao Entrar
	    getDisplay().getEntrarButton().addClickHandler(new ClickHandler() {   
	        public void onClick(ClickEvent event) {
	        	if ("".equals(getDisplay().getUsuarioTBox().getValue()) || 
	        		"".equals(getDisplay().getSenhaTBox().getValue())){
	        		Window.alert("Informe usuario e senha");
	        	} else {
	        		loginAsync(getDisplay().getUsuarioTBox().getValue(), getDisplay().getSenhaTBox().getValue());
	        		
	        	}
	        }
	      });

	}

	public void go() {
	}

	public void loginAsync(String usuario, String senha) {
		
		service.autenticaUsuario(usuario, senha, new AsyncCallback<UsuarioVO>(){

			public void onSuccess(UsuarioVO vo) {
				// --- Envio um evento LoginEvent para quem interecar
				LoginEvent event = new LoginEvent();
				event.setUsuarioVO(vo);
				getRecursos().getEventBus().fireEvent(event);

			}
			
			public void onFailure(Throwable arg0) {
				Window.alert("Nao foi possivel autenticar este usuario");
			}
			
		});
	}

	public Display getDisplay() {
		return this.display;
	}
	public PortalResource getRecursos() {
		return recursos;
	}
	
	

}
