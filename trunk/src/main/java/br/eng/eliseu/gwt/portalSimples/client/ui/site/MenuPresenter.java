package br.eng.eliseu.gwt.portalSimples.client.ui.site;

import br.eng.eliseu.gwt.portalSimples.client.mvp.PortalResource;
import br.eng.eliseu.gwt.portalSimples.client.mvp.presenter.BasePresenter;
import br.eng.eliseu.gwt.portalSimples.client.mvp.presenter.PresenterCodeEnum;
import br.eng.eliseu.gwt.portalSimples.client.mvp.presenter.PresenterCodeEnum.TipoMenu;
import br.eng.eliseu.gwt.portalSimples.client.mvp.view.BaseDisplay;
import br.eng.eliseu.gwt.portalSimples.model.UsuarioAcesso;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;

public class MenuPresenter extends BasePresenter {

	public interface Display extends BaseDisplay {
		public MenuBar getMenuBar();
	}

	private final PortalResource recursos;
	private final Display display;

	public MenuPresenter(Display display, PortalResource recursos) {
		this.recursos = recursos;
		this.display = display;
		bind();
	}

	public void bind() {
	}

	public void go() {
		// --- Cria Menu
		getDisplay().getMenuBar().clearItems();
		addItemMenu(null, getDisplay().getMenuBar());
	}
	

	private Integer addItemMenu(PresenterCodeEnum presenterCode, MenuBar menuParent){
		Integer adicionados = 0;
		for (final PresenterCodeEnum p : PresenterCodeEnum.valuesAcesso()) {
			if(p!=null && p.getTipoMenu()!=null && usuarioVisualiza(p) ){
				// && !item.getRequerAutenticacao()
				if (presenterCode == null) {
					// --- Menus da Barra principal
					if (p.getParentId().equals(0)) {
						MenuItem menuItem = null ;
						Integer add = new Integer(0);
						if (p.getTipoMenu().equals(TipoMenu.ITEM)) {
							// --- Item
							menuItem = new MenuItem(p.getNome(), new Command() {
								public void execute() {
									executeMenu(p);
								}
							});
							add++;
						} else {
							// --- Popup
							MenuBar subMenu = new MenuBar(true);
							menuItem = new MenuItem(p.getNome(), subMenu);
							add = addItemMenu(p, subMenu);
						}
						
						if (add > 0) {
							// --- Adiciona menu na barra principal
							getDisplay().getMenuBar().addItem(menuItem);
						}
					}
				} else {
					if(p.getParentId()!=null && p.getParentId().equals(presenterCode.getId())){
						if(p.getTipoMenu().equals(PresenterCodeEnum.TipoMenu.POPUP)){
							// --- Popup
							MenuBar subMenu = new MenuBar(true);
							Integer add = addItemMenu(p, subMenu);
							if(add>0){
								MenuItem menuItem = new MenuItem(p.getNome(), subMenu);
								menuParent.addItem(menuItem);
								adicionados++;
							}
						} else {
							// --- Itenm
							MenuItem menuItem = new MenuItem(p.getNome(), new Command() {
								public void execute() { executeMenu(p);}
							});
							menuParent.addItem(menuItem);
							adicionados++;
						}
					}
				}
			}
		}
		return adicionados;
	}

	private Boolean usuarioVisualiza(PresenterCodeEnum presenterCode){
		
		if (presenterCode.getRequerAutenticacao()){
			// --- Tela requer autenticacao
			if (getRecursos().getUsrAutentidado()!=null){
				for (UsuarioAcesso a : getRecursos().getUsrAutentidado().getAcesso()) {
					if (a.getTela().equals(presenterCode)){
						if (a.getVisualizacao()){
							return true;
						}
					}
				}
			} 
			// --- Usuario nao autenticado
			return false;
		} 
		// --- Tela nao requer autenticacao
		return true;
	}
	
	private void executeMenu(PresenterCodeEnum p) {
		// --- Aciono o evento onValueChange
		getRecursos().getPlaceManager().newPlace(p);
	}

	
	
	public Display getDisplay() {
		return display;
	}
	public PortalResource getRecursos() {
		return recursos;
	}

}
