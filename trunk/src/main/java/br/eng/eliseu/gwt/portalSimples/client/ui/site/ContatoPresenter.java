package br.eng.eliseu.gwt.portalSimples.client.ui.site;

import br.eng.eliseu.gwt.portalSimples.client.mvp.PortalResource;
import br.eng.eliseu.gwt.portalSimples.client.mvp.presenter.BasePresenter;
import br.eng.eliseu.gwt.portalSimples.client.mvp.view.BaseDisplay;

import com.google.gwt.event.shared.EventBus;

public class ContatoPresenter extends BasePresenter{

	public interface Display extends BaseDisplay {
	}

	private final EventBus eventBus;
	private final Display display;

	public ContatoPresenter(Display display, PortalResource recursos) {
		this.eventBus = recursos.getEventBus();
		this.display = display;
		bind();
	}

	public EventBus getEventBus() {
		return eventBus;
	}
	
	public BaseDisplay getDisplay() {
		return display;
	}
	
	public void bind() {
		// TODO Auto-generated method stub
		
	}

	public void go() {
		// TODO Auto-generated method stub
		
	}

}
