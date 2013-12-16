package br.eng.eliseu.gwt.portalSimples.client.ui.site;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class RodapeView extends Composite implements RodapePresenter.Display{

	private static RodapeViewUiBinder uiBinder = GWT.create(RodapeViewUiBinder.class);

	interface RodapeViewUiBinder extends UiBinder<Widget, RodapeView> {}

	public RodapeView() {
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
