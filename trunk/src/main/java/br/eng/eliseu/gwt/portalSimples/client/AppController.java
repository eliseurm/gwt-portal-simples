package br.eng.eliseu.gwt.portalSimples.client;

import br.eng.eliseu.gwt.portalSimples.client.event.LoginEvent;
import br.eng.eliseu.gwt.portalSimples.client.event.LoginEventHandler;
import br.eng.eliseu.gwt.portalSimples.client.event.LogoutEvent;
import br.eng.eliseu.gwt.portalSimples.client.event.LogoutEventHandler;
import br.eng.eliseu.gwt.portalSimples.client.event.MainEvent;
import br.eng.eliseu.gwt.portalSimples.client.event.MainEventHandler;
import br.eng.eliseu.gwt.portalSimples.client.event.PlaceEvent;
import br.eng.eliseu.gwt.portalSimples.client.event.PlaceEventHandler;
import br.eng.eliseu.gwt.portalSimples.client.handle.DownloadHandler;
import br.eng.eliseu.gwt.portalSimples.client.mvp.PortalResource;
import br.eng.eliseu.gwt.portalSimples.client.mvp.presenter.Presenter;
import br.eng.eliseu.gwt.portalSimples.client.mvp.presenter.PresenterMenuEnum;
import br.eng.eliseu.gwt.portalSimples.client.mvp.presenter.PresenterService;
import br.eng.eliseu.gwt.portalSimples.client.mvp.presenter.PresenterServiceImpl;
import br.eng.eliseu.gwt.portalSimples.client.mvp.view.BaseDisplay;
import br.eng.eliseu.gwt.portalSimples.client.ui.MainPresenter;
import br.eng.eliseu.gwt.portalSimples.client.ui.MainView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;

public class AppController implements Presenter {

	private final PresenterService presenterViewService;
	private final PortalResource recursos;

	private MainPresenter mainPresenter;
	private HasWidgets container;
	
	@Inject
	public AppController( MainPresenter mainPresenter,  PortalResource recurso) {
		presenterViewService = GWT.create(PresenterServiceImpl.class);

		this.mainPresenter = mainPresenter;
		this.recursos = recurso;
		
		bind();
	}

	public void bind() {
		// --- Implementaar os eventos Globais Aqui
		
		// Sempre que um evento EventBus.fireEvent( new PlaceEvent.. ) for invocado este evento sera executado
		getRecursos().getEventBus().addHandler(PlaceEvent.TYPE, new PlaceEventHandler() {

			public void onChangePlace(PlaceEvent event) {
				PresenterMenuEnum states = event.getState();
				showPresenterCode(states);
			}
		});	

		// --- Evento que chama e monta todas as telas CABECALHO, MENU, CENTRO, RODAPE
		getRecursos().getEventBus().addHandler(MainEvent.TYPE, new MainEventHandler() {

			public void onMainPlace(MainEvent event) {
				PresenterMenuEnum telaDoCentro = event.getTelaDoCentro();
				if (telaDoCentro == null) {
					showPresenterMainAsync(PresenterMenuEnum.HOME);
				} else {
					showPresenterMainAsync(telaDoCentro);
				}

			}
		});	

		// --- sempre que um evento LogOUT for acionado 
		getRecursos().getEventBus().addHandler(LogoutEvent.TYPE, new LogoutEventHandler() {
			public void onLogout(LogoutEvent logoutEvent) {
				// --- Seto null nas variaveis de recursos do sistema
				getRecursos().setUsrAutentidado(null);

				reconstroiMenuAsync(PresenterMenuEnum.HOME);
				
			}
		});	

		// --- sempre que um evento LogIN for acionado 
		getRecursos().getEventBus().addHandler(LoginEvent.TYPE, new LoginEventHandler() {

			public void onProcessa(LoginEvent event) {
				// --- Seto o usuario nas variaveis de recursos do sistema
				getRecursos().setUsrAutentidado(event.getUsuarioVO());
				
				reconstroiMenuAsync(PresenterMenuEnum.HOME);

			}
		});	

		
		
	}

	// --- Comessa aqui
	public void init(HasWidgets container) {
		this.container = container;
		
		// --- Inicia o Presenter principal "main"
		if ( this.mainPresenter==null){
			// --- So entra aqui se o Injetor nao consegui instanciar o MainPresenter
			this.mainPresenter = new MainPresenter(new MainView(), getRecursos());
		}
		
		if (this.container!=null){
			this.container.clear();
			mainPresenter.go();
			this.container.add( mainPresenter.getDisplay().asWidget() );

		}
		
	}
	
	
	private void showPresenterCode(final PresenterMenuEnum s) {
		
		presenterViewService.downloadPresenter(s, new DownloadHandler() {
			
			public void finished(final Presenter presenter) {
				mainPresenter.getDisplay().showState(presenter, s);
			}

			public void failure(Throwable error) {
				System.out.println("Download error: "+error.getMessage()+" ("+s.getNome()+")");
				error.printStackTrace();
			}
		});
	}

	/**
	 * Criei este metodo assim por se tratar de chamadas asincronas e o que estava acontecendo 
	 * é que a primeira chamada nao tinha erminado entes da proxima comessar, assim 
	 * a a ultima tela ficava repetida para todas 
	 */
	private void showPresenterMainAsync(final PresenterMenuEnum telaDoCentro) {
		
		presenterViewService.downloadPresenter(PresenterMenuEnum.CABECALHO, new DownloadHandler() {

			public void finished(final Presenter presenter) {
				mainPresenter.getDisplay().showState(presenter, PresenterMenuEnum.CABECALHO);
				
				presenterViewService.downloadPresenter(PresenterMenuEnum.MENU, new DownloadHandler() {
					
					public void finished(final Presenter presenter) {
						mainPresenter.getDisplay().showState(presenter, PresenterMenuEnum.MENU);
						
						presenterViewService.downloadPresenter(PresenterMenuEnum.RODAPE, new DownloadHandler() {
					
							public void finished(final Presenter presenter) {
								mainPresenter.getDisplay().showState(presenter, PresenterMenuEnum.RODAPE);
								
								presenterViewService.downloadPresenter(telaDoCentro, new DownloadHandler() {
							
									public void finished(final Presenter presenter) {
										mainPresenter.getDisplay().showState(presenter, telaDoCentro);
										getRecursos().getPlaceManager().setaPrimeiraTelaHistorico();
										
									}

									public void failure(Throwable error) {
										System.out.println("Download error: "+error.getMessage()+" ("+telaDoCentro.getNome()+")");
										error.printStackTrace();
									}
								});
							}

							public void failure(Throwable error) {
								System.out.println("Download error: "+error.getMessage()+" ("+PresenterMenuEnum.RODAPE.getNome()+")");
								error.printStackTrace();
							}
						});
					}

					public void failure(Throwable error) {
						System.out.println("Download error: "+error.getMessage()+" ("+PresenterMenuEnum.MENU.getNome()+")");
						error.printStackTrace();
					}
				});
			}

			public void failure(Throwable error) {
				System.out.println("Download error: "+error.getMessage()+" ("+PresenterMenuEnum.CABECALHO.getNome()+")");
				error.printStackTrace();
			}
		});
	}

	private void reconstroiMenuAsync(final PresenterMenuEnum telaDoCentro) {
		presenterViewService.downloadPresenter(PresenterMenuEnum.MENU, new DownloadHandler() {
			
			public void finished(final Presenter presenter) {
				mainPresenter.getDisplay().showState(presenter, PresenterMenuEnum.MENU);
				
				presenterViewService.downloadPresenter(telaDoCentro, new DownloadHandler() {
			
					public void finished(final Presenter presenter) {
						mainPresenter.getDisplay().showState(presenter, telaDoCentro);
						getRecursos().getPlaceManager().setaPrimeiraTelaHistorico();
						
					}

					public void failure(Throwable error) {
						System.out.println("Download error: "+error.getMessage()+" ("+telaDoCentro.getNome()+")");
						error.printStackTrace();
					}
				});
			}

			public void failure(Throwable error) {
				System.out.println("Download error: "+error.getMessage()+" ("+PresenterMenuEnum.MENU.getNome()+")");
				error.printStackTrace();
			}
		});

	
	}
	
	
	
	public void go() {
		// TODO Auto-generated method stub
	}

	public BaseDisplay getDisplay() {
		return null;
	}
	public PortalResource getRecursos() {
		return recursos;
	}


}
