package br.eng.eliseu.gwt.portalSimples.client.ui.site;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class LoginView extends Composite implements LoginPresenter.Display{

	private static LoginViewUiBinder uiBinder = GWT.create(LoginViewUiBinder.class);
	interface LoginViewUiBinder extends UiBinder<Widget, LoginView> {}
	
	@UiField TextBox usuarioTBox;
	@UiField PasswordTextBox senhaTBox;
	@UiField Button entrarButton;


	public LoginView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	
	public Widget asWidget() {
		return this;
	}


	
	// --- Aqui usamos HasValue<String> para o view ser bem generico 
	public HasValue<String> getUsuarioTBox() {
		return usuarioTBox;
	}
	public HasValue<String> getSenhaTBox() {
		return senhaTBox;
	}

	public HasClickHandlers getEntrarButton() {
		return entrarButton;
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
