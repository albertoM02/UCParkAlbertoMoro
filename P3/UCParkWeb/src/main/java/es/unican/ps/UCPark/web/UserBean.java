package es.unican.ps.UCPark.web;

import java.io.Serializable;

import es.unican.ps.UCPark.bussineslayer.IGestionCuentaRemote;
import es.unican.ps.UCPark.daolayer.IUsuariosDAORemote;
import es.unican.ps.domain.Usuario;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class UserBean implements Serializable {
	
	private String email;
	private String contrasenha;
	private Usuario user = null;
	


	@EJB
	private IGestionCuentaRemote userDAO;
	
	public String inicio() {
		user = userDAO.login(email, contrasenha);
		if(user == null) {
			return "usuarioIncorrecto.xhtml";
		} 
		return "principal.xhtml";
	}
	
	public String irEstacionamiento() {
		return "nuevoEstacionamiento.xhtml";
	}
	
	public void voidAction() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasenha() {
		return contrasenha;
	}

	public void setContrasenha(String contrasenha) {
		this.contrasenha = contrasenha;
	}
	
	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public IGestionCuentaRemote getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IGestionCuentaRemote userDAO) {
		this.userDAO = userDAO;
	}

	/**
	public IUsuariosDAORemote getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUsuariosDAORemote userDAO) {
		this.userDAO = userDAO;
	}
	**/
}
