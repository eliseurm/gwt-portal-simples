package br.eng.eliseu.gwt.portalSimples.client.mvp.presenter;

import br.eng.eliseu.gwt.portalSimples.client.mvp.view.BaseDisplay;


public interface Presenter {

	public BaseDisplay getDisplay();
	public void bind();
	public void go();
}
