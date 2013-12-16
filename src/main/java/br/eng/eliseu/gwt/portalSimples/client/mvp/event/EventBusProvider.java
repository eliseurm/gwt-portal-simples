package br.eng.eliseu.gwt.portalSimples.client.mvp.event;


import com.google.gwt.event.shared.EventBus;
import com.google.inject.Provider;

public class EventBusProvider implements Provider<EventBus> {

	public EventBus get() {
		return PortalEventBus.getInstance();
	}

}
