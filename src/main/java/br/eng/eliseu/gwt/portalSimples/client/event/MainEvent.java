package br.eng.eliseu.gwt.portalSimples.client.event;

import br.eng.eliseu.gwt.portalSimples.client.mvp.presenter.PresenterMenuEnum;

import com.google.gwt.event.shared.GwtEvent;

public class MainEvent extends GwtEvent<MainEventHandler>{

	private PresenterMenuEnum telaDoCentro;
	
	public static Type<MainEventHandler> TYPE = new Type<MainEventHandler>();
	
	@Override
	protected void dispatch(MainEventHandler handler) {
		handler.onMainPlace(this);
		
	}

	@Override
	public Type<MainEventHandler> getAssociatedType() {
		return TYPE;
	}

	public PresenterMenuEnum getTelaDoCentro() {
		return telaDoCentro;
	}

	public void setTelaDoCentro(PresenterMenuEnum telaDoCentro) {
		this.telaDoCentro = telaDoCentro;
	}

}
