package br.eng.eliseu.gwt.portalSimples.client.mvp.presenter;

import br.eng.eliseu.gwt.portalSimples.client.ui.sistema.CadastroBancoPresenter;
import br.eng.eliseu.gwt.portalSimples.client.ui.site.CabecalhoPresenter;
import br.eng.eliseu.gwt.portalSimples.client.ui.site.ContatoPresenter;
import br.eng.eliseu.gwt.portalSimples.client.ui.site.HomePresenter;
import br.eng.eliseu.gwt.portalSimples.client.ui.site.LoginPresenter;
import br.eng.eliseu.gwt.portalSimples.client.ui.site.MenuPresenter;
import br.eng.eliseu.gwt.portalSimples.client.ui.site.RodapePresenter;


public enum PresenterCodeEnum  {
	// --- Telas fora do Menu
	CABECALHO(null, null, null, LocalDestino.TOPO, CabecalhoPresenter.class, "Cabecalho", null, false),
	MENU(null, null, null, LocalDestino.MENU, MenuPresenter.class, "Menu", null, false),
	RODAPE(null, null, null, LocalDestino.RODAPE, RodapePresenter.class, "Rodape", null, false),
	LOGIN(null, null, null, LocalDestino.CENTRO, LoginPresenter.class, "Entrar", "login", false),

	// --- Telas do Menu
	HOME				(  00,   01, TipoMenu.ITEM, LocalDestino.CENTRO, HomePresenter.class, "Home", "home", false),
	
	CADASTRO			(  00,   02, TipoMenu.POPUP, null, null, "Cadastro", null, false),
		CAD_BANCOS		(  02, 0201, TipoMenu.ITEM, LocalDestino.CENTRO, CadastroBancoPresenter.class, "Bancos", "cadbancos", true),
		CAD_FORNECEDOR	(  02, 0202, TipoMenu.ITEM, null, null, "Fornecedores", "cadFornecedores", false),

	CONTATO				(  00,   03, TipoMenu.ITEM, LocalDestino.CENTRO, ContatoPresenter.class, "Contato", "contato", false),
	;
	
	public enum TipoMenu { POPUP, ITEM };
	public enum LocalDestino { TOPO, MENU, CENTRO, RODAPE };
	
	private Integer parentId; 
	private Integer id;
	private TipoMenu tipoMenu;
	private LocalDestino local;
	private Class<? extends Presenter> presenter;
	private String nome;
	private String tagNome;
	private Boolean requerAutenticacao;
	
	private PresenterCodeEnum(Integer parentId, Integer id, TipoMenu tipoMenu, LocalDestino local, 
			Class<? extends Presenter> presenter, String nome, String tagNome, Boolean requerAutenticacao) {
		this.parentId = parentId;
		this.id = id;
		this.tipoMenu = tipoMenu;
		this.local = local;
		this.presenter = presenter;
		this.nome = nome;
		this.tagNome = tagNome;
		this.requerAutenticacao = requerAutenticacao; 
	}
	

	public static PresenterCodeEnum[] valuesAcesso() {
		PresenterCodeEnum[] values = new PresenterCodeEnum[values().length];
		int i=0;
		for (PresenterCodeEnum item : PresenterCodeEnum.values()) {
			if(item!=null && item.getTipoMenu()!=null){
				values[i++] = item;
			}
		}
		return values;
	}

	
	
	
	public void setPresenter(Class<? extends Presenter> presenter) {
		this.presenter = presenter;
	}
	public Class<? extends Presenter> getPresenter() {
		return presenter;
	}
	public String getPresenterName(){
		String nome = null;
		if (presenter!=null){
			nome = presenter.toString().replaceAll("class ", "");
		}
		return nome;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCodigo() {
		return parentId * 1000 + id;
	}
	public Boolean getRequerAutenticacao() {
		return requerAutenticacao;
	}
	public void setRequerAutenticacao(Boolean requerAutenticacao) {
		this.requerAutenticacao = requerAutenticacao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTagNome() {
		return tagNome;
	}

	public void setTagNome(String tagNome) {
		this.tagNome = tagNome;
	}

	public TipoMenu getTipoMenu() {
		return tipoMenu;
	}
	public void setTipoMenu(TipoMenu tipoMenu) {
		this.tipoMenu = tipoMenu;
	}
	public LocalDestino getLocal() {
		return local;
	}
	public void setLocal(LocalDestino local) {
		this.local = local;
	}
}