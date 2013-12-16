package br.eng.eliseu.gwt.portalSimples.client.handle;

import br.eng.eliseu.gwt.portalSimples.client.mvp.presenter.Presenter;

public interface DownloadHandler {
	void finished(Presenter presenter);
	void failure(Throwable error);
}
