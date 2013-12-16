package br.eng.eliseu.gwt.portalSimples.client.mvp.event;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.event.shared.HandlerRegistration;

public class PortalEventBus extends EventBus {

	private static PortalEventBus instance;

	public static PortalEventBus getInstance() {
		if (instance == null) {
			instance = new PortalEventBus();
		}
		return instance;
	}

	private PortalEventBus() {
	}

	@Override
	public <H extends EventHandler> HandlerRegistration addHandler(
			Type<H> arg0, H arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <H extends EventHandler> HandlerRegistration addHandlerToSource(
			Type<H> arg0, Object arg1, H arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void fireEvent(GwtEvent<?> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fireEventFromSource(GwtEvent<?> arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
