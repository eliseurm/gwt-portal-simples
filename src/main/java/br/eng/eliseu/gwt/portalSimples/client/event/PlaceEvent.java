package br.eng.eliseu.gwt.portalSimples.client.event;


import br.eng.eliseu.gwt.portalSimples.client.mvp.presenter.PresenterCodeEnum;

import com.google.gwt.event.shared.GwtEvent;

public class PlaceEvent extends GwtEvent<PlaceEventHandler> {

	private PresenterCodeEnum state;
	
	public static Type<PlaceEventHandler> TYPE = new Type<PlaceEventHandler>();

	@Override
	public Type<PlaceEventHandler> getAssociatedType() {
		return TYPE;
	}
	@Override
	protected void dispatch(PlaceEventHandler handler) {
		handler.onChangePlace(this);
	}
	public PresenterCodeEnum getState() {
		return state;
	}
	public void setState(PresenterCodeEnum state) {
		this.state = state;
	}
}