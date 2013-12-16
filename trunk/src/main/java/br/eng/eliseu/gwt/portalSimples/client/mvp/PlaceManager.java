package br.eng.eliseu.gwt.portalSimples.client.mvp;

import br.eng.eliseu.gwt.portalSimples.client.event.PlaceEvent;
import br.eng.eliseu.gwt.portalSimples.client.mvp.presenter.PresenterCodeEnum;
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
	 * Se tiver alguma coisa na url ( #xxxx ) a ser considerada,
	 * mantenho o historico e chamo o evento onValueChange
	 */
	public void currentPlace() {
		if (History.getToken() != null && !"".equals(History.getToken())) {
			History.fireCurrentHistoryState();
		}
	}

//	public void newPlace(Class<? extends BasePresenter> presenter) {
//		if (presenter != null) {
//			for (PresenterCodeEnum p : PresenterCodeEnum.values()) {
//				String nomeClass = presenter.getClass().getName();
//				if (p.getPresenter().getName().equals(nomeClass)) {
//					newPlace(p.getTagNome(), true);
//					break;
//				}
//			}
//		}
//	}

	/**
	 * Aciono o evento onValueChange
	 * @param state
	 */
	public void newPlace( PresenterCodeEnum state ){
		newPlace(state.getTagNome(), true);
	}
	
	// public void newPlace(StateHistory newStateHistory) {
	// newPlace(newStateHistory, true, true);
	// }
	//
	// public void newPlaceClean(@SuppressWarnings("rawtypes") Class<? extends
	// Presenter> presenter) {
	// newPlace(new StateHistory(presenter), true, false);
	// }
	//
	// public void newPlaceClean(StateHistory state) {
	// newPlace(state, true, false);
	// }

	private void newPlace(String historyToken, boolean refresh) {

		if (History.getToken().equals(historyToken)) {
			currentPlace();
		} else {
			History.newItem(historyToken, refresh);
		}
	}

	//
	// private void newPlace(StateHistory newStateHistory, boolean refresh,
	// boolean addToken) {
	// String decodeHistoryToken = "";
	//
	// if(addToken) {
	// if(History.getToken()!=null && !"".equals(History.getToken())) {
	// decodeHistoryToken = CipherUtil.decodeToken(History.getToken());
	// }
	// }
	//
	// List<String> lstTokens = new ArrayList<String>();
	// StringTokenizer st = new StringTokenizer(decodeHistoryToken, HT_DELIM);
	//
	// String newJsonHistoryToken =
	// newStateHistory.getHistoryTokenAsJson(false);
	// if(newJsonHistoryToken.length() > 350) {
	// GWT.log("ATENÇÃO: O StateHistory para o Presenter: "+newStateHistory.getPresenterName()+" possui mais de 350 letras. "
	// +
	// "Você pode estar comprometendo o número de abas aberta do sistema. Token: "+newJsonHistoryToken);
	// }
	// for(String jhToken : st.getTokens()) {
	// StateHistory sh = new StateHistory();
	// sh.setHistoryTokenAsJson(jhToken);
	// sh.setShow(false);
	//
	// if(sh.getHistoryTokenAsJson(false).equals(newJsonHistoryToken)) {
	// sh.setShow(true);
	// newStateHistory = null;
	// }
	//
	// lstTokens.add( sh.getHistoryTokenAsJson() );
	// }
	// if(newStateHistory != null) {
	// newStateHistory.setShow(true);
	// lstTokens.add(newStateHistory.getHistoryTokenAsJson());
	// }
	//
	// String newDecodeHistoryToken = joinTokens(lstTokens);
	// if(! decodeHistoryToken.equals(newDecodeHistoryToken)) {
	// newPlace(CipherUtil.encodeToken(newDecodeHistoryToken), refresh);
	// }
	// }

	// public void removePlace(StateHistory... tokensToRemove) {
	// removePlace(false, tokensToRemove);
	// }

	// public void showPlace( String jsonHistoryTokenSelect ) {
	// StateHistory stateHistory = new StateHistory();
	// stateHistory.setHistoryTokenAsJson(jsonHistoryTokenSelect);
	// newPlace(stateHistory, false, true);
	// }

	// public void updatePlace(StateHistory oldStateHistory, StateHistory
	// newStateHistory) {
	// if(oldStateHistory != null && newStateHistory != null) {
	// newStateHistory.setShow( oldStateHistory.getShow() );
	//
	// String currentToken = oldStateHistory.getHistoryTokenAsJson(true);
	// String newToken = newStateHistory.getHistoryTokenAsJson(true);
	//
	// List<String> tokens = new ArrayList<String>();
	// String decodeHistoryToken = CipherUtil.decodeToken(History.getToken());
	// StringTokenizer st = new StringTokenizer(decodeHistoryToken, HT_DELIM);
	// for (String jsToken : st.getTokens()) {
	// if(jsToken.equals(newToken)) {
	// // token novo e igual a outro token ja existente, nao faz nada
	// GWT.log("Não pude executar o updatePlace(...). O novo place já encontra-se aberto. token: "
	// + newToken);
	// return;
	// }
	//
	// if(jsToken.equals(currentToken)) {
	// tokens.add(newToken);
	// }
	// else {
	// tokens.add(jsToken);
	// }
	// }
	//
	// // PlaceUpdateEvent event = new PlaceUpdateEvent();
	// // event.setCurrentStateHistory(oldStateHistory);
	// // event.setNewStateHistory(newStateHistory);
	// // eventBus.fireEvent(event);
	// newPlace( CipherUtil.encodeToken(joinTokens(tokens)), false );
	// }
	// }
	//
	// public void updatePlace(List<StateHistory> states) {
	// List<String> tokens = new ArrayList<String>();
	// boolean show = false;
	// for (StateHistory state : states) {
	// if(state.getShow()) {
	// show = true;
	// break;
	// }
	// }
	// for (StateHistory state : states) {
	// if(show == false && state.getPresenterName().equals(HOME_PRESENTER)) {
	// state.setShow(true); // nao tem ninguem marcado para ser exibido, seta o
	// home
	// }
	// tokens.add(state.getHistoryTokenAsJson());
	// }
	// newPlace( CipherUtil.encodeToken(joinTokens(tokens)), false );
	// }

	// private boolean possuiToken(String historyToken, StateHistory...
	// tokensToRemove) {
	// for(StateHistory state : tokensToRemove) {
	// if(state.getHistoryTokenAsJson(false).equals(historyToken)) {
	// return true;
	// }
	// }
	// return false;
	// }

	// private void removePlace(boolean refresh, StateHistory... tokensToRemove)
	// {
	// if(tokensToRemove != null) {
	// List<StateHistory> states = new ArrayList<StateHistory>();
	// String decodeHistoryToken = CipherUtil.decodeToken(History.getToken());
	// StringTokenizer st = new StringTokenizer(decodeHistoryToken, HT_DELIM);
	//
	// int indexNextTokenToShow = -1;
	// for (int i=0; i < st.getTokens().length; i++) {
	// String jsToken = st.getTokens()[i];
	// StateHistory state = new StateHistory();
	// state.setHistoryTokenAsJson(jsToken);
	// state.setShow(false);
	// if( possuiToken(state.getHistoryTokenAsJson(), tokensToRemove) ) {
	// indexNextTokenToShow = i;
	// }
	// else {
	// states.add(state);
	// }
	// }
	// if (states.size() <= indexNextTokenToShow) {
	// indexNextTokenToShow = states.size()-1;
	// }
	// states.get(indexNextTokenToShow).setShow(true);
	//
	// List<String> lstTokens = new ArrayList<String>();
	// for (StateHistory state : states) {
	// lstTokens.add( state.getHistoryTokenAsJson() );
	// }
	//
	// newPlace( CipherUtil.encodeToken(joinTokens(lstTokens)), refresh );
	// }
	// }

	// public List<StateHistory> getStates() {
	// List<StateHistory> states = new ArrayList<StateHistory>();
	// String decodeHistoryToken = CipherUtil.decodeToken(History.getToken());
	// StringTokenizer st = new StringTokenizer(decodeHistoryToken, HT_DELIM);
	// for( String jsToken : st.getTokens() ) {
	// StateHistory sh = new StateHistory();
	// sh.setHistoryTokenAsJson(jsToken);
	// states.add(sh);
	// }
	// return states;
	// }

	// private String joinTokens(List<String> tokens) {
	// StringBuffer sb = new StringBuffer();
	// sb.append(tokens.get(0));
	// for(int i=1; i<tokens.size(); i++) {
	// sb.append(HT_DELIM);
	// sb.append(tokens.get(i));
	// }
	// return sb.toString();
	// }

	// public String getPresenterName(String jsonHistoryToken) {
	// if(jsonHistoryToken!=null && !"".equals(jsonHistoryToken.trim())) {
	// try {
	// StateHistory stateHistory = new StateHistory();
	// stateHistory.setHistoryTokenAsJson(jsonHistoryToken);
	//
	// return stateHistory.getPresenterName();
	// } catch(Exception e) {
	// e.printStackTrace();
	// }
	// }
	// return null;
	// }

//	public void onValueChange(ValueChangeEvent<String> event) {
		// if (event.getValue() != null) {
		// if("".equals(event.getValue()) ) {
		// newPlace(new StateHistory( HOME_PRESENTER )); // fica no HOME de
		// qualquer jeito!
		// }
		// else {
		// List<StateHistory> states = new ArrayList<StateHistory>();
		//
		// String decodeHistoryToken = CipherUtil.decodeToken(event.getValue());
		// StringTokenizer sTokenizer = new StringTokenizer(decodeHistoryToken,
		// HT_DELIM);
		// for (String jsonHistoryToken : sTokenizer.getTokens()) {
		// StateHistory stateHistory = new StateHistory();
		// stateHistory.setHistoryTokenAsJson(jsonHistoryToken);
		//
		// states.add(stateHistory);
		// }
		//
		// PlaceEvent placeEvent = new PlaceEvent();
		// placeEvent.setStates(states);
		//
		// eventBus.fireEvent(placeEvent);
		// }
		// }
//	}
	
	/**
	 * Sempre que um History.newItem é alterado, este evento é chamado.
	 * Ou seja toda janela que for aberta, seja pelo menu seja usando uma
	 * url conhecida vai passar por aqui
	 */
	public void onValueChange(ValueChangeEvent<String> event) {
		String historyToken = event.getValue();
		
		// --- Se o token da url for igual ao de algum PresenterCodeEnum eu chamo a janela
		if (historyToken!=null && !"".equals(historyToken)){
			for (final PresenterCodeEnum p : PresenterCodeEnum.valuesAcesso()) {
				if (p!=null && historyToken.equals(p.getTagNome())){
					autenticaJanelaAsync(p);
					break;
				}
			}
		} else {
			// --- Quando nao tiver nada no History eu abro o Home
			PlaceEvent placeEvent = new PlaceEvent();
			placeEvent.setState(PresenterCodeEnum.HOME);
			eventBus.fireEvent(placeEvent);
		}
	}

	private void autenticaJanelaAsync(final PresenterCodeEnum presenterCode) {
		if (presenterCode.getRequerAutenticacao()){
			LoginServiceAsync service = GWT.create(LoginService.class);
			service.getUsuarioAutenticado(new AsyncCallback<Usuario>() {
				
				public void onSuccess(Usuario usr) {
					if (usr != null) {
						abreJanela(presenterCode);
					} else {
						// --- se nao tem usuario logado, abro a tela de login
						abreJanela(PresenterCodeEnum.LOGIN);
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
	
	private void abreJanela(final PresenterCodeEnum presenterCode) {
		// --- Chama a janela
		PlaceEvent placeEvent = new PlaceEvent();
		placeEvent.setState(presenterCode);
		eventBus.fireEvent(placeEvent);
	}
	

}
