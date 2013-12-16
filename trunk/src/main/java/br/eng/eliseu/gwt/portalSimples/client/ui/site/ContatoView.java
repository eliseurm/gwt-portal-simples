package br.eng.eliseu.gwt.portalSimples.client.ui.site;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ContatoView extends Composite implements ContatoPresenter.Display{

	private static ContatoViewUiBinder uiBinder = GWT.create(ContatoViewUiBinder.class);

	interface ContatoViewUiBinder extends UiBinder<Widget, ContatoView> {}

	public ContatoView() {
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
