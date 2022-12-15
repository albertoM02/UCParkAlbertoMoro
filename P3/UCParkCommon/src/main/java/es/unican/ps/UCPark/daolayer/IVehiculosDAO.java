package es.unican.ps.UCPark.daolayer;

import java.util.Set;

import es.unican.ps.domain.Vehiculo;

public interface IVehiculosDAO {
	public Vehiculo creaVehiculo(Vehiculo v);
	public Vehiculo eliminarVehiculo(String matricula);
	public Vehiculo actualizaVehiculo(Vehiculo v);
	public Vehiculo vehiculo(String matricula);
	public Set<Vehiculo> vehiculos();
}
