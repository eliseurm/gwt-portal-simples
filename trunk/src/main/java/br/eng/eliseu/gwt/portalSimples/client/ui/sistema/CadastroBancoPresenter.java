package br.eng.eliseu.gwt.portalSimples.client.ui.sistema;

import br.eng.eliseu.gwt.portalSimples.client.mvp.PortalResource;
import br.eng.eliseu.gwt.portalSimples.client.mvp.presenter.BasePresenter;
import br.eng.eliseu.gwt.portalSimples.client.mvp.view.BaseDisplay;

public class CadastroBancoPresenter extends BasePresenter{

	public interface Display extends BaseDisplay {
	}

	private final PortalResource recursos;
	private final Display display;

	public CadastroBancoPresenter(Display display, PortalResource recursos ) {
		this.recursos = recursos;
		this.display = display;
		bind();
	}

	
	public void bind() {
		// TODO Auto-generated method stub
	}
	public void go() {
		// TODO Auto-generated method stub
	}
	public BaseDisplay getDisplay() {
		return display;
	}
	public PortalResource getRecursos() {
		return recursos;
	}

}
