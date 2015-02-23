package br.eng.eliseu.gwt.portalSimples.client.mvp;

import br.eng.eliseu.gwt.portalSimples.client.event.PlaceEvent;
import br.eng.eliseu.gwt.portalSimples.client.mvp.presenter.PresenterMenuEnum;
import br.eng.eliseu.gwt.portalSimples.client.service.LoginService;
import br.eng.eliseu.gwt.portalSimples.client.service.LoginServiceAsync;
import br.eng.eliseu.gwt.portalSimples.model.Usuario;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

/**
 * PlaceManager, "gerencia o LUGAR" (historico) onde o usuario esta navegando.
 * 
 * @author Eliseu
 * 
 */
public class PlaceManager implements ValueChangeHandler<String> {

	// private static final int MAX_TOKEN_SIZE = 1900;
	// private static final String HT_DELIM = "_HT_";
	// public static final String HOME_PRESENTER =
	// "br.com.k.client.ui.home.HomePresenter";

	private final EventBus eventBus;

	@Inject
	public PlaceManager(final EventBus eventBus) {
		History.addValueChangeHandler(this);
		this.eventBus = eventBus;
	}

	/**
	 * Sempre que um History.newItem é alterado, este evento é chamado.
	 * Ou seja toda janela que for aberta, seja pelo menu seja usando uma
	 * url conhecida vai passar por aqui
	 */
	public void onValueChange(ValueChangeEvent<String> event) {
		String historyToken = event.getValue();
		
		// --- Se o token da url for igual ao de algum PresenterMenuEnum eu chamo a janela
		if (historyToken!=null && !"".equals(historyToken)){
			for (final PresenterMenuEnum p : PresenterMenuEnum.valuesAcesso()) {
				if (p!=null && historyToken.equals(p.getTagNome())){
					autenticaJanelaAsync(p);
					break;
				}
			}
		} else {
			// --- Quando nao tiver nada no History eu abro o Home
			PlaceEvent placeEvent = new PlaceEvent();
			placeEvent.setState(PresenterMenuEnum.HOME);
			eventBus.fireEvent(placeEvent);
		}
	}


	/**
	 * Se tiver alguma coisa na url ( #xxxx ) a ser considerada,
	 * mantenho o historico e chamo o evento onValueChange
	 */
	public void setaPrimeiraTelaHistorico() {
		if (History.getToken() != null && !"".equals(History.getToken())) {
			History.fireCurrentHistoryState();
		}
	}

	public void newPlace( PresenterMenuEnum state ){
		newPlace(state.getTagNome(), true);
	}
	
	private void newPlace(String historyToken, boolean refresh) {

		if (History.getToken().equals(historyToken)) {
			setaPrimeiraTelaHistorico();
		} else {
			History.newItem(historyToken, refresh);
		}
	}

	
	private void autenticaJanelaAsync(final PresenterMenuEnum presenterCode) {
		if (presenterCode.getRequerAutenticacao()){
			LoginServiceAsync service = GWT.create(LoginService.class);
			service.getUsuarioAutenticado(new AsyncCallback<Usuario>() {
				
				public void onSuccess(Usuario usr) {
					if (usr != null) {
						abreJanela(presenterCode);
					} else {
						// --- se nao tem usuario logado, abro a tela de login
						abreJanela(PresenterMenuEnum.LOGIN);
					}
					
				}

				public void onFailure(Throwable arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});
		} else {
			abreJanela(presenterCode);
		}
	}
	
	private void abreJanela(final PresenterMenuEnum presenterCode) {
		// --- Chama a janela
		PlaceEvent placeEvent = new PlaceEvent();
		placeEvent.setState(presenterCode);
		eventBus.fireEvent(placeEvent);
	}
	

}
