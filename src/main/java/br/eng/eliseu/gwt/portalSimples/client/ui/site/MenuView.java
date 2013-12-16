package br.eng.eliseu.gwt.portalSimples.client.ui.site;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.Widget;

public class MenuView extends Composite implements MenuPresenter.Display {

	private static MenuViewUiBinder uiBinder = GWT.create(MenuViewUiBinder.class);
	interface MenuViewUiBinder extends UiBinder<Widget, MenuView> {	}

	@UiField MenuBar menuBar;
	
	public MenuView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public Widget asWidget() {
		return this;
	}

	public MenuBar getMenuBar() {
		return menuBar;
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
