package br.eng.eliseu.gwt.portalSimples.server.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.eng.eliseu.gwt.portalSimples.client.service.LoginService;
import br.eng.eliseu.gwt.portalSimples.model.Usuario;
import br.eng.eliseu.gwt.portalSimples.model.UsuarioAcesso;
import br.eng.eliseu.gwt.portalSimples.model.UsuarioVO;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class LoginServiceImpl extends RemoteServiceServlet implements LoginService{

	private static final long serialVersionUID = -2164339367091650949L;

	public UsuarioVO autenticaUsuario(String usuario, String senha)throws Exception {
		@SuppressWarnings("unchecked")
		List<Usuario> listUsuario = (List<Usuario>) getServletContext().getAttribute("listUsuarios");
		@SuppressWarnings("unchecked")
		List<UsuarioAcesso> listAcesso = (List<UsuarioAcesso>) getServletContext().getAttribute("listUsuariosAcesso");
		
		UsuarioVO vo = null;
		
		for (Usuario u : listUsuario) {
			if (usuario.equals(u.getUsuario()) && senha.equals(u.getSenha())){
				vo = new UsuarioVO();
				vo.setUsuario(u);
				
				for (UsuarioAcesso a : listAcesso) {
					if (a.getUsuario().getId().equals(a.getId())){
						vo.getAcesso().add(a);
					}
				}
				break;
			}
		}
		
		if(vo!=null){
			HttpServletRequest req = getThreadLocalRequest();
			HttpSession session = req.getSession(true);
			session.setAttribute("usuario", vo.getUsuario());
		} else {
			throw new Exception("Usuário não encontrado.");
		}
		
		return vo;
	}

	public void desconecta() throws Exception {
		HttpServletRequest req = getThreadLocalRequest();
		HttpSession session = req.getSession();
		if(session != null) {
			session.setAttribute("usuario", null);
		}
		getThreadLocalRequest().getSession(true).invalidate();
		
	}

	public Usuario getUsuarioAutenticado() throws Exception {
		HttpServletRequest req = getThreadLocalRequest();
		HttpSession session = req.getSession();
		Usuario user = null;
		if(session != null) {
			user = (Usuario) session.getAttribute("usuario");
		}
		return user;
	}

}
