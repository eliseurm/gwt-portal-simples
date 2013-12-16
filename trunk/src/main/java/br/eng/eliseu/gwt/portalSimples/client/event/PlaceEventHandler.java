package br.eng.eliseu.gwt.portalSimples.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface PlaceEventHandler extends EventHandler {
	 void onChangePlace(PlaceEvent event);
}