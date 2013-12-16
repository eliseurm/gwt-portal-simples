package br.eng.eliseu.gwt.portalSimples.client.ui;

import br.eng.eliseu.gwt.portalSimples.client.mvp.presenter.Presenter;
import br.eng.eliseu.gwt.portalSimples.client.mvp.presenter.PresenterCodeEnum;
import br.eng.eliseu.gwt.portalSimples.client.mvp.presenter.PresenterCodeEnum.LocalDestino;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MainView extends Composite implements MainPresenter.Display {

	private static MainViewUiBinder uiBinder = GWT.create(MainViewUiBinder.class);
	interface MainViewUiBinder extends UiBinder<Widget, MainView> {	}
	
	@UiField DockPanel mainDPanel;
	
	@UiField VerticalPanel mainTopo;
	@UiField VerticalPanel mainMenu;
	@UiField ScrollPanel mainCentro;
	@UiField VerticalPanel mainRodape;

//	private List<Widget> listJanelas = new LinkedList<Widget>();
	
	public MainView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	
	public void showState(Presenter presenter, PresenterCodeEnum s) {
		
		if (s.getLocal().equals(LocalDestino.TOPO)){
        	mainTopo.clear();
        	mainTopo.add(presenter.getDisplay().asWidget() );
		} else if (s.getLocal().equals(LocalDestino.MENU)){
        	mainMenu.clear();
        	mainMenu.add(presenter.getDisplay().asWidget() );
		} else if (s.getLocal().equals(LocalDestino.CENTRO)){
        	mainCentro.clear();
        	mainCentro.add(presenter.getDisplay().asWidget() );
		} else if (s.getLocal().equals(LocalDestino.RODAPE)){
        	mainRodape.clear();
        	mainRodape.add(presenter.getDisplay().asWidget() );
		}
		/*
		if (state != null) {
			BasePresenter basePresenter = (BasePresenter) state;
			bodyContent.clear();
			bodyContent.add(basePresenter.getDisplay().asWidget());
			History.newItem(null);
			for (PresenterCodeEnum p : PresenterCodeEnum.values()) {
				if(p.getPresenter().getName().equals(presenter.getClassName())){
					if(p.equals(PresenterCodeEnum.HOME)){
						History.newItem(null);
					} else {
						History.newItem(p.getTagName());
					}
					break;
				}
			}
		}
		 */
	}

	public Widget asMainMenu() {
		return mainMenu;
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
