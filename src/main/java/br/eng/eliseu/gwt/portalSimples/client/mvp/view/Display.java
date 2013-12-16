package br.eng.eliseu.gwt.portalSimples.client.mvp.view;


public interface Display {
	void showMessage(String message);
	void showError(String message);
	void showWaitMessage(boolean b);
}
