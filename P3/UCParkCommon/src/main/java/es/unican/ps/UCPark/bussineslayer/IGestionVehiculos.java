package es.unican.ps.UCPark.bussineslayer;

import es.unican.ps.domain.Usuario;
import es.unican.ps.domain.Vehiculo;

public interface IGestionVehiculos {

	public Vehiculo añadirVehiculo(Vehiculo v, Usuario u);
	public Vehiculo eliminarVehiculo(String matricula);
	public Vehiculo consultaVehiculo(String matricula);
}
