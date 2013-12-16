package br.eng.eliseu.gwt.portalSimples.client.mvp;

import java.io.Serializable;

import br.eng.eliseu.gwt.portalSimples.model.UsuarioVO;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

public class PortalResource implements Serializable {
	private static final long serialVersionUID = -1025292010727087548L;
	
	private final EventBus eventBus;
	private final PlaceManager placeManager;
	private UsuarioVO usrAutentidado = null;
	
	private static PortalResource instance ;
	
	@Inject
	public PortalResource(EventBus eventBus, PlaceManager placeManager) {
		this.eventBus = eventBus;
		this.placeManager = placeManager;
		instance = this;
	}

	/** retorna null se o construtor padrao nao tiver sido usado nem uma vez */
	public static PortalResource getInstanceCreated() {
//		if(instance == null) {
//			throw new Exception("Esse obj deve ser criado pelo GIN pelo menos uma vez antes de usar esse metodo!");
//		}
		return instance;
	}
	
	
	
	public PlaceManager getPlaceManager() {
		return placeManager;
	}
	public EventBus getEventBus() {
		return eventBus;
	}
	public UsuarioVO getUsrAutentidado() {
		return usrAutentidado;
	}
	public void setUsrAutentidado(UsuarioVO usrAutentidado) {
		this.usrAutentidado = usrAutentidado;
	}

}
