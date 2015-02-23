package br.eng.eliseu.gwt.portalSimples.client.mvp.presenter;

import br.eng.eliseu.gwt.portalSimples.client.handle.DownloadHandler;

public interface PresenterService {
	public void downloadPresenter(PresenterMenuEnum presenter, final DownloadHandler handler);
	
}