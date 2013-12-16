package br.eng.eliseu.gwt.portalSimples.client.ui.sistema;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class CadastroBancoView extends Composite implements CadastroBancoPresenter.Display {

	private static CadastroBancoUiBinder uiBinder = GWT.create(CadastroBancoUiBinder.class);
	interface CadastroBancoUiBinder extends UiBinder<Widget, CadastroBancoView> {}


	public CadastroBancoView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public Widget asWidget() {
		return this;
	}

	
	public void showMessage(String message) {
		// TODO Auto-generated method stub
	}
	public void showError(String message) {
		// TODO Auto-generated method stub
	}
	public void showWaitMessage(boolean b) {
		// TODO Auto-generated method stub
	}

}
