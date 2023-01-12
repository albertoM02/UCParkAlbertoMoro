package es.unican.ps.UCPark.daolayer;

import java.util.HashSet;
import java.util.Set;

import es.unican.ps.domain.Denuncia;
import es.unican.ps.domain.Usuario;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class UsuariosDAO implements IUsuariosDAOLocal, IUsuariosDAORemote {
	
	@PersistenceContext(unitName="UCPark")
	private EntityManager em;

	public UsuariosDAO() {
		
	}
	
	public UsuariosDAO(EntityManager entityManager) {
		this.em = entityManager;
	}
	
	public Usuario creaUsuarios(Usuario u) {
		try {
			em.persist(u);
		} catch (EntityExistsException e) {
			return null;
		}
		return u;
	}

	public Usuario eliminaUsuario(String email) {
		Usuario usuario = em.find(Usuario.class, email);
		if (usuario != null) {
			em.remove(usuario);
			return usuario;
		} else {
			return null;
		}
	}

	public Usuario actualizaUsuario(Usuario u) {
		eliminaUsuario(u.getEmail());
		return creaUsuarios(u);
	}

	public Usuario usuario(String email) {
		Usuario usuario = em.find(Usuario.class, email);
		return usuario;
	}

	public Set<Usuario> usuarios() {
		 Query query = em.createQuery("SELECT u FROM Usuario u");
		 return (HashSet<Usuario>) query.getResultList();
	}
	

}
