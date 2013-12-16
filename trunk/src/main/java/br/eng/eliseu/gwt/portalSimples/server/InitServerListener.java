package br.eng.eliseu.gwt.portalSimples.server;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.eng.eliseu.gwt.portalSimples.client.mvp.presenter.PresenterCodeEnum;
import br.eng.eliseu.gwt.portalSimples.model.Usuario;
import br.eng.eliseu.gwt.portalSimples.model.UsuarioAcesso;

public class InitServerListener implements ServletContextListener {

	private List<Usuario> listUsuario = null;
	private List<UsuarioAcesso> listAcesso = null;

	private static final String[] usuarios = new String[] { "a", "root", "Joao" };

	private static final String[] senhas = new String[] { "a", "root", "Joao" };

	public void contextDestroyed(ServletContextEvent arg0) {
	}

	public void contextInitialized(ServletContextEvent contEvent) {
		/**
		 * Fiz esta parte so para simular um Banco de dados com alguns registros de usuarios
		 * que poderao ser utilizados mais tarde como teste para o sistema
		 */
		
		// --- Crio alguns usuarios
		listUsuario = new ArrayList<Usuario>();
		listAcesso = new ArrayList<UsuarioAcesso>();
		for (int i = 0; i < usuarios.length; ++i) {
			Usuario u = new Usuario(i, usuarios[i], senhas[i]);
			listUsuario.add(u);
			
			// --- Carrego algumas telas para os usuarios
			int j = 0;
			for (final PresenterCodeEnum p : PresenterCodeEnum.valuesAcesso()) {
				if ( p!=null && p.getRequerAutenticacao()){
					UsuarioAcesso a = new UsuarioAcesso(i*100+j, u, p, true, true, false, false);
					listAcesso.add(a);
					
				}
			}
			
		}
		
		contEvent.getServletContext().setAttribute("listUsuarios", listUsuario);
		contEvent.getServletContext().setAttribute("listUsuariosAcesso", listAcesso);
		
		// --- Pegar variavel
//		List<Usuario> lista = (List<Usuario>) arg0.getServletContext().getAttribute("listUsuarios");

		
	}


}
