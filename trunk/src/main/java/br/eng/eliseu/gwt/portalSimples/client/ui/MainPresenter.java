package br.eng.eliseu.gwt.portalSimples.client.ui;

import br.eng.eliseu.gwt.portalSimples.client.event.PlaceEvent;
import br.eng.eliseu.gwt.portalSimples.client.mvp.PortalResource;
import br.eng.eliseu.gwt.portalSimples.client.mvp.presenter.Presenter;
import br.eng.eliseu.gwt.portalSimples.client.mvp.presenter.PresenterCodeEnum;
import br.eng.eliseu.gwt.portalSimples.client.mvp.view.BaseDisplay;

import com.google.inject.Inject;

public class MainPresenter implements Presenter {

	public interface Display extends BaseDisplay {
		public void showState(Presenter presenter, PresenterCodeEnum state);
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
		PlaceEvent event;
		
		// Estas telas sao as primeiras telas a serem iniciadas
		event = new PlaceEvent();
		event.setState(PresenterCodeEnum.CABECALHO);
		getRecursos().getEventBus().fireEvent(event);

		event = new PlaceEvent();
		event.setState(PresenterCodeEnum.MENU);
		getRecursos().getEventBus().fireEvent(event);

		event = new PlaceEvent();
		event.setState(PresenterCodeEnum.RODAPE);
		getRecursos().getEventBus().fireEvent(event);

		event = new PlaceEvent();
		event.setState(PresenterCodeEnum.HOME);
		getRecursos().getEventBus().fireEvent(event);
		
	}

	
	public Display getDisplay() {
		return display;
	}
	public PortalResource getRecursos() {
		return recursos;
	}


}
