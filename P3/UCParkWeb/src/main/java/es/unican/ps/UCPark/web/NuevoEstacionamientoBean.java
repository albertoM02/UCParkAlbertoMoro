package es.unican.ps.UCPark.web;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import es.unican.ps.UCPark.bussineslayer.IGestionEstacionamientosRemote;
import es.unican.ps.UCPark.daolayer.IEstacionamientosDAORemote;
import es.unican.ps.UCPark.daolayer.IUsuariosDAORemote;
import es.unican.ps.UCPark.daolayer.IVehiculosDAORemote;
import es.unican.ps.domain.Estacionamiento;
import es.unican.ps.domain.Usuario;
import es.unican.ps.domain.Vehiculo;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class NuevoEstacionamientoBean implements Serializable {
	
	@Inject
	private UserBean ub;
	
	@EJB
	private IVehiculosDAORemote vehiculosDAO;

	@EJB
	private IGestionEstacionamientosRemote estacionamientosDAO;

	
	public IGestionEstacionamientosRemote getEstacionamientosDAO() {
		return estacionamientosDAO;
	}


	public void setEstacionamientosDAO(IGestionEstacionamientosRemote estacionamientosDAO) {
		this.estacionamientosDAO = estacionamientosDAO;
	}


	private int minutos;
	private List<String> matriculas;

	private String matricula;
	
	private Usuario user;
	
	private LocalDateTime finaliza;

	@PostConstruct
	public void cargaInfo() {
		user = ub.getUser();
		
		matriculas = new ArrayList<String>();

		
		for (Vehiculo v : obtenVehiculosUsuario(user)) {
			matriculas.add(v.getMatricula());
		}
		
		
		
	}
	

	public List<Vehiculo> obtenVehiculosUsuario(Usuario u) {
		List<Vehiculo> vehiculosUsuario = new ArrayList<Vehiculo>();
		List<Vehiculo> vehiculos;
		vehiculos = vehiculosDAO.vehiculos();
		for (Vehiculo vehiculo : vehiculos) {
			if (vehiculo.getPropietario().getEmail().equals(u.getEmail())) {
				vehiculosUsuario.add(vehiculo);
			}
		} 
		return vehiculosUsuario;
	}
	
	
	public String crearEstacionamiento() {
		Estacionamiento estacionamiento = new Estacionamiento();
		Time ahora = Time.valueOf(LocalTime.now());
		estacionamiento.setHoraInicio(ahora);
		estacionamiento.setMinutos(minutos);
		estacionamiento.setVehiculo(vehiculosDAO.vehiculo(matricula));
		
		if(estacionamientosDAO.añadirEstacionamiento(estacionamiento,matricula) == null) {
			return "minExcedidos";
		}
		finaliza = LocalDateTime.now().plusMinutes(minutos);
		return "estacionamientoCreado.xhtml";
	}
	
	public UserBean getUb() {
		return ub;
	}

	public void setUb(UserBean ub) {
		this.ub = ub;
	}

	public int getMinutos() {
		return minutos;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}

	public List<String> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<String> matriculas) {
		this.matriculas = matriculas;
	}
	
	public String getMatricula() {
		return matricula;
	}


	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public IVehiculosDAORemote getVehiculosDAO() {
		return vehiculosDAO;
	}


	public void setVehiculosDAO(IVehiculosDAORemote vehiculosDAO) {
		this.vehiculosDAO = vehiculosDAO;
	}


	public Usuario getUser() {
		return user;
	}


	public void setUser(Usuario user) {
		this.user = user;
	}


	public LocalDateTime getfinaliza() {
		return finaliza;
	}


	public void setfinaliza(LocalDateTime finaliza) {
		this.finaliza = finaliza;
	}




}
