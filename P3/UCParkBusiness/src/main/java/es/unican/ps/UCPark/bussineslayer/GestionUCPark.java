package es.unican.ps.UCPark.bussineslayer;

import java.util.HashSet;
import java.util.Set;

import es.unican.ps.UCPark.daolayer.IDenunciasDAOLocal;
import es.unican.ps.UCPark.daolayer.IEstacionamientosDAOLocal;
import es.unican.ps.UCPark.daolayer.IUsuariosDAOLocal;
import es.unican.ps.UCPark.daolayer.IVehiculosDAO;
import es.unican.ps.UCPark.daolayer.IVehiculosDAOLocal;
import es.unican.ps.domain.Denuncia;
import es.unican.ps.domain.Estacionamiento;
import es.unican.ps.domain.Usuario;
import es.unican.ps.domain.Vehiculo;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.ext.MessageBodyWriter;

@Stateless
public class GestionUCPark implements IGestionDenuncias, IGestionCuenta, 
										IGestionEstacionamientos, IGestionVehiculos {

	@EJB
	private IDenunciasDAOLocal denunciasDAO;
	
	@EJB
	private IEstacionamientosDAOLocal estacionamientosDAO;
	
	@EJB
	private IUsuariosDAOLocal usuariosDAO;
	
	@EJB
	private IVehiculosDAOLocal vehiculosDAO;
	
	
	public Vehiculo añadirVehiculo(Vehiculo v, Usuario u) {
		vehiculosDAO.creaVehiculo(v);
		
		return null;
	}

	public Vehiculo eliminarVehiculo(String matricula) {
		vehiculosDAO.eliminarVehiculo(matricula);
		return null;
	}

	public Vehiculo consultaVehiculo(String matricula) {
		return vehiculosDAO.vehiculo(matricula);
	}

	public Estacionamiento añadirEstacionamiento(Estacionamiento e, String matricula) {
		Vehiculo v = vehiculosDAO.vehiculo(matricula);
		v.setEstacionamientoEnVigor(e);
		
		return null;
	}

	public Estacionamiento ampliarEstacionamiento(String ID, int tiempoAumento) {
		Estacionamiento estacionamiento = estacionamientosDAO.estacionamiento(ID);
		estacionamiento.setMinutos(estacionamiento.getMinutos() + tiempoAumento);
		estacionamientosDAO.actualizaEstacionamiento(estacionamiento);
		return estacionamiento;
	}

	public Estacionamiento eliminarEstacionamiento(String ID) {
		Estacionamiento estacionamiento = estacionamientosDAO.eliminarEstacionamiento(ID);
		return estacionamiento;
	}

	public Estacionamiento comprobarEstacionamiento(String matricula) {
		Estacionamiento estacionamiento = vehiculosDAO.vehiculo(matricula).getEstacionamientoEnVigor();
		return estacionamiento;
	}

	public Set<Estacionamiento> consultarEstacionamientos(Usuario u) {
		Set<Vehiculo> vehiculos = u.getVehiculos();
		Set<Estacionamiento> estacionamientos = new HashSet<Estacionamiento>();
		for (Vehiculo vehiculo : vehiculos) {
			estacionamientos.addAll(vehiculo.getEstacionamientosHistoricos());
		}
		return estacionamientos;
	}

	public Usuario registrarse(Usuario u) {
		// TODO Auto-generated method stub
		return null;
	}

	public Denuncia añadirDenuncia(Denuncia d, String matricula) {
		// TODO Auto-generated method stub
		return null;
	}

	public Denuncia eliminaDenuncia(String ID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Denuncia consultarDenuncia(String ID) {
		// TODO Auto-generated method stub
		return null;
	}

}
