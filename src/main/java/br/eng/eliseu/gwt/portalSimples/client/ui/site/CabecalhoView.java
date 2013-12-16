package br.eng.eliseu.gwt.portalSimples.client.ui.site;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class CabecalhoView extends Composite implements CabecalhoPresenter.Display {

	private static CabecalhoViewUiBinder uiBinder = GWT.create(CabecalhoViewUiBinder.class);
	interface CabecalhoViewUiBinder extends UiBinder<Widget, CabecalhoView> {}
	
	@UiField HorizontalPanel entrarPanel;
	@UiField Button entrarButton;

	@UiField HorizontalPanel sairPanel;
	@UiField Label nomeUsuarioLabel;
	@UiField Button sairButton;


	public CabecalhoView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public Widget asWidget() {
		return this;
	}


	public Label getNomeUsuarioLabel() {
		return nomeUsuarioLabel;
	}

	public Panel getEntrarPanel() {
		return entrarPanel;
	}
	public HasClickHandlers getEntrarButton() {
		return entrarButton;
	}

	public Panel getSairPanel() {
		return sairPanel;
	}
	public HasClickHandlers getSairButton() {
		return sairButton;
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
