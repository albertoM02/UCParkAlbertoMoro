package es.unican.ps.UCPark.bussineslayer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import es.unican.ps.UCPark.daolayer.IDenunciasDAOLocal;
import es.unican.ps.UCPark.daolayer.IEstacionamientosDAOLocal;
import es.unican.ps.UCPark.daolayer.IUsuariosDAOLocal;
import es.unican.ps.UCPark.daolayer.IVehiculosDAO;
import es.unican.ps.UCPark.daolayer.IVehiculosDAOLocal;
import es.unican.ps.UCPark.daolayer.IVehiculosDAORemote;
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
	
	public GestionUCPark() {
		
	}
	
	public GestionUCPark(IEstacionamientosDAOLocal estacionamiento) {
		this.estacionamientosDAO = estacionamiento;
	}
	
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
		estacionamientosDAO.crearEstacionamiento(e);
		return e;
	}

	public Estacionamiento ampliarEstacionamiento(String ID, int tiempoAumento) {
		if (tiempoAumento < 0 || tiempoAumento > 120) {
			return null;
		}
		Estacionamiento estacionamiento = estacionamientosDAO.estacionamiento(ID);
		if (estacionamiento == null) {
			return null;
		}
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
		List<Vehiculo> vehiculos = u.getVehiculos();
		Set<Estacionamiento> estacionamientos = new HashSet<Estacionamiento>();
		for (Vehiculo vehiculo : vehiculos) {
			estacionamientos.addAll(vehiculo.getEstacionamientosHistoricos());
		}
		return estacionamientos;
	}

	public Usuario registrarse(Usuario u) {
		Usuario user = usuariosDAO.creaUsuarios(u);
		return user;
	}

	public Denuncia añadirDenuncia(Denuncia d, String matricula) {
		Vehiculo v = vehiculosDAO.vehiculo(matricula);
		Set<Denuncia> des = v.getDenunciasEnVigor();
		des.add(d);
		v.setDenunciasEnVigor(des);
		return d;
	}

	public Denuncia eliminaDenuncia(Denuncia d, String matricula) {
		Vehiculo v = vehiculosDAO.vehiculo(matricula);
		Set<Denuncia> des = v.getDenunciasEnVigor();
		des.remove(d);
		v.setDenunciasEnVigor(des);
		return d;
	}

	public Set<Denuncia> consultarDenuncia(String matricula) {
		Vehiculo v = vehiculosDAO.vehiculo(matricula);
		Set<Denuncia> des = v.getDenunciasEnVigor();
		return des;
	}



}
