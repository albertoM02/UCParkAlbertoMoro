package es.unican.ps.UCPark.daolayer;

import java.util.HashSet;
import java.util.Set;

import es.unican.ps.domain.Denuncia;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class DenunciasDAO implements IDenunciasDAOLocal, IDenunciasDAORemote {

	@PersistenceContext(unitName="UCPark")
	private EntityManager em;
	
	public Denuncia creaDenuncia(Denuncia d) {
		try {
			em.persist(d);
		} catch (EntityExistsException e) {
			return null;
		}
		return d;
	}

	public Denuncia eliminaDenuncia(String id) {
		Denuncia denuncia = em.find(Denuncia.class, id);
		if (denuncia != null) {
			em.remove(denuncia);
		} 
		return denuncia;
	}

	public Denuncia actualizaDenuncia(Denuncia d) {
		eliminaDenuncia(d.getID());		
		return creaDenuncia(d);
	}

	public Denuncia denuncia(String id) {
		Denuncia denuncia = em.find(Denuncia.class, id);
		return denuncia;
	}

	public Set<Denuncia> denuncias() {
		 Query query = em.createQuery("SELECT d FROM Denuncia d");
		 return (HashSet<Denuncia>) query.getResultList();
	}

}
