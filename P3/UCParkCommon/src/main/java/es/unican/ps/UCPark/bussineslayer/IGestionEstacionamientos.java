package es.unican.ps.UCPark.bussineslayer;


import java.util.Set;

import es.unican.ps.domain.Estacionamiento;
import es.unican.ps.domain.Usuario;

public interface IGestionEstacionamientos {

	public Estacionamiento añadrEstacionamiento(Estacionamiento e, String matricula);
	public Estacionamiento ampliarEstacionamiento(String ID);
	public Estacionamiento eliminarEstacionamiento(String ID);
	public Estacionamiento comprobarEstacionamiento(String matricula);
	public Set<Estacionamiento> consultarEstacionamientos(Usuario u);

}
