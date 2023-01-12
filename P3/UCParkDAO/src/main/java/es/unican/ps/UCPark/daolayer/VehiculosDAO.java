package es.unican.ps.UCPark.daolayer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import es.unican.ps.domain.Denuncia;
import es.unican.ps.domain.Vehiculo;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class VehiculosDAO implements IVehiculosDAOLocal, IVehiculosDAORemote {
	
	@PersistenceContext(unitName="UCPark")
	private EntityManager em;

	public Vehiculo creaVehiculo(Vehiculo v) {
		try {
			em.persist(v);
		} catch (EntityExistsException e) {
			return null;
		}
		return v;
	}

	public Vehiculo eliminarVehiculo(String matricula) {
		Vehiculo vehiculo = em.find(Vehiculo.class, matricula);
		if (vehiculo != null) {
			em.remove(vehiculo);
		} 
		return vehiculo;
	}

	public Vehiculo actualizaVehiculo(Vehiculo v) {
		eliminarVehiculo(v.getMatricula());
		return creaVehiculo(v);
	}

	public Vehiculo vehiculo(String matricula) {
		Vehiculo vehiculo = em.find(Vehiculo.class, matricula);
		return vehiculo;
	}

	public List<Vehiculo> vehiculos() {
		 Query query = em.createQuery("SELECT v FROM Vehiculo v");
		 return query.getResultList();
	}

}
