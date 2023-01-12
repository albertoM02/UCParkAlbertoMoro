package es.unican.ps.UCPark.daolayer;

import java.util.HashSet;
import java.util.Set;

import es.unican.ps.domain.Denuncia;
import es.unican.ps.domain.Estacionamiento;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class EstacionamientosDAO implements IEstacionamientosDAOLocal, IEstacionamientosDAORemote {

	@PersistenceContext(unitName="UCPark")
	private EntityManager em;
	
	public Estacionamiento crearEstacionamiento(Estacionamiento estacionamiento) {
		try {
			em.persist(estacionamiento);
		} catch (EntityExistsException f) {
			return null;
		}
		return estacionamiento;
	}

	public Estacionamiento eliminarEstacionamiento(String id) {
		Estacionamiento estacionamiento = em.find(Estacionamiento.class, id);
		if (estacionamiento != null) {
			em.remove(estacionamiento);
		} 
		return estacionamiento;
	}

	public Estacionamiento actualizaEstacionamiento(Estacionamiento e) {
		eliminarEstacionamiento(e.getID());
		return crearEstacionamiento(e);
	}

	public Estacionamiento estacionamiento(String id) {
		Estacionamiento estacionamiento = em.find(Estacionamiento.class, id);
		return estacionamiento;
	}

	public Set<Estacionamiento> estacionamientos() {
		Query query = em.createQuery("SELECT e FROM Estacionamiento e");
		return (HashSet<Estacionamiento>) query.getResultList();
	}

}
