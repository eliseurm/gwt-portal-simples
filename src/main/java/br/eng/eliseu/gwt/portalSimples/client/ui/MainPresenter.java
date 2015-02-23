package br.eng.eliseu.gwt.portalSimples.client.ui;

import br.eng.eliseu.gwt.portalSimples.client.event.MainEvent;
import br.eng.eliseu.gwt.portalSimples.client.mvp.PortalResource;
import br.eng.eliseu.gwt.portalSimples.client.mvp.presenter.Presenter;
import br.eng.eliseu.gwt.portalSimples.client.mvp.presenter.PresenterMenuEnum;
import br.eng.eliseu.gwt.portalSimples.client.mvp.view.BaseDisplay;

import com.google.gwt.user.client.History;
import com.google.inject.Inject;

public class MainPresenter implements Presenter {

	public interface Display extends BaseDisplay {
		public void showState(Presenter presenter, PresenterMenuEnum state);
	}

	
	private final PortalResource recursos;
	private final Display display;

	
	@Inject
	public MainPresenter(Display display, PortalResource recursos) {
		this.recursos = recursos;
		this.display = display;
		bind();
	}

	public void bind() {
	}

	public void go() {
		/**
		 * Chama o evento que cria a tela principal. 
		 * Ja carrego na tela principal e a tela do CENTRO apropriada.
		 * Faco isto assim porque as telas principais sao asyncronas e nao estava dando tempo de carregar
		 * a tela de CENTRO quando ela ja vinha da url
		 * 
		 */
		PresenterMenuEnum telaDoCentro = null;
		
		if (History.getToken() != null && !"".equals(History.getToken())) {
			// Se na url estiver especificando uma tela.
			
			for (final PresenterMenuEnum p : PresenterMenuEnum.valuesAcesso()) {
				if (p!=null && History.getToken().equals(p.getTagNome())){
					telaDoCentro = p;
					break;
				}
			}
		}
		
		/**
		 * Neste ponto telaDoMeio pode ser nulo ou conter a tela que esta na url
		 * Se for nulo no controlo do evento eu vou seta-lo como HOME
		 * Se nao for nulo é a propria tela que vai ser aberta
		 */
		MainEvent event = new MainEvent();
		event.setTelaDoCentro(telaDoCentro);
		getRecursos().getEventBus().fireEvent(new MainEvent());
		
	}

	
	public Display getDisplay() {
		return display;
	}
	public PortalResource getRecursos() {
		return recursos;
	}


}
