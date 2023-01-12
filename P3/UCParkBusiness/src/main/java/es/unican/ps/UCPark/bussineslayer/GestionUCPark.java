package es.unican.ps.UCPark.bussineslayer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import es.unican.ps.UCPark.daolayer.IDenunciasDAOLocal;
import es.unican.ps.UCPark.daolayer.IEstacionamientosDAO;
import es.unican.ps.UCPark.daolayer.IEstacionamientosDAOLocal;
import es.unican.ps.UCPark.daolayer.IUsuariosDAO;
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
public class GestionUCPark implements IGestionDenunciasLocal, IGestionDenunciasRemote, IGestionCuentaLocal, IGestionCuentaRemote,
										IGestionEstacionamientosLocal, IGestionEstacionamientosRemote, IGestionVehiculosLocal, 
										IGestionVehiculosRemote {

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
	
	public GestionUCPark(IEstacionamientosDAO estacionamiento) {
		this.estacionamientosDAO = (IEstacionamientosDAOLocal) estacionamiento;
	}
	
	
	/**
	 * Null si el vehiculo existe o el propietario no existe
	 * En otro caso crea el vehículo y lo retorna.
	 */
	public Vehiculo añadirVehiculo(Vehiculo v, Usuario u) {
		Vehiculo anhadido = null;
		if (vehiculosDAO.vehiculo(v.getMatricula()) == null && usuariosDAO.usuario(u.getEmail()) != null) {
			anhadido = vehiculosDAO.creaVehiculo(v);
			List<Vehiculo> nuevaLista = u.getVehiculos();
			nuevaLista.add(anhadido);
			u.setVehiculos(nuevaLista);
			usuariosDAO.actualizaUsuario(u);
		}

		return anhadido;
	}

	/**
	 * Null si el vehiculo no existe.
	 * En otro caso lo borra y se lo elimina al propietario.
	 */
	public Vehiculo eliminarVehiculo(String matricula) {
		Vehiculo vehiculo = vehiculosDAO.vehiculo(matricula);
		if (vehiculo == null) {
			return null;
		} 
		Usuario propietario = usuariosDAO.usuario(vehiculo.getPropietario().getEmail());
		List<Vehiculo> ves = propietario.getVehiculos();
		ves.remove(vehiculo);
		propietario.setVehiculos(ves);
		usuariosDAO.actualizaUsuario(propietario);
		vehiculosDAO.eliminarVehiculo(matricula);
		return vehiculosDAO.eliminarVehiculo(matricula);
	}

	/**
	 * Devuelve el vehiculo o null si no existe.
	 */
	public Vehiculo consultaVehiculo(String matricula) {
		return vehiculosDAO.vehiculo(matricula);
	}

	/**
	 * Si excede más de 120 minutos o el vehiculo no existe, devuelve null, si no
	 * el estacionamiento anhadido.
	 */
	public Estacionamiento añadirEstacionamiento(Estacionamiento e, String matricula) {
		Vehiculo v = vehiculosDAO.vehiculo(matricula);
		if (v == null || e.getMinutos() > 120 || e.getMinutos() < 0) {
			return null;
		} 
		v.setEstacionamientoEnVigor(e);
		v.getEstacionamientosHistoricos().add(e);
		estacionamientosDAO.crearEstacionamiento(e);
		vehiculosDAO.actualizaVehiculo(v);
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
	
	//No he logrado que funcione con un Timer
	public Estacionamiento terminarEstacionamiento(String ID) {
		Estacionamiento estacionamiento = estacionamientosDAO.estacionamiento(ID);
		if(estacionamiento != null) {
			Vehiculo vehiculo = estacionamiento.getVehiculo();
			vehiculo.setEstacionamientoEnVigor(null);
			vehiculosDAO.actualizaVehiculo(vehiculo);
		}
		return estacionamiento;
	}

	public Usuario registrarse(Usuario u) {
		if (usuariosDAO.usuario(u.getEmail()) == null) {
			usuariosDAO.creaUsuarios(u);
			return u;
		}
		return null;
	}

	public Denuncia añadirDenuncia(Denuncia denuncia, String matricula) {
		Vehiculo v = vehiculosDAO.vehiculo(matricula);
		Denuncia den = denunciasDAO.denuncia(denuncia.getID());
		if(den != null) {
			return null; //Ya existe
		}
		if(v == null) {
			return null;
		}
		Set<Denuncia> des = v.getDenunciasEnVigor();
		des.add(denuncia);
		v.setDenunciasEnVigor(des);
		denunciasDAO.creaDenuncia(denuncia);
		vehiculosDAO.actualizaVehiculo(v);
		return denuncia;
	}

	public Denuncia eliminaDenuncia(Denuncia denuncia, String matricula) {
		Vehiculo v = vehiculosDAO.vehiculo(matricula);
		Denuncia den = denunciasDAO.denuncia(denuncia.getID());
		if(den == null) {
			return null; //No existe
		}
		if(v == null) {
			return null;
		}
		Set<Denuncia> des = v.getDenunciasEnVigor();
		des.remove(denuncia);
		v.setDenunciasEnVigor(des);
		vehiculosDAO.actualizaVehiculo(v);
		return denuncia;
	}

	public Set<Denuncia> consultarDenuncia(String matricula) {
		Vehiculo v = vehiculosDAO.vehiculo(matricula);
		if (v == null) {
			return null;
		}
		Set<Denuncia> des = v.getDenunciasEnVigor();
		return des;
	}

	@Override
	public Usuario consultarUsuario(String email) {
		return usuariosDAO.usuario(email);
	}

	@Override
	public Usuario login(String email, String contrasenha) {
		Usuario user = usuariosDAO.usuario(email);
		//Si la contrasenha es incorrecta o no existe.
		if(user == null || !user.getContraseña().equals(contrasenha)) {
			return null;
		}
		return user;
	}




}
