package es.unican.ps.UCPark.daolayer;

import java.util.List;
import java.util.Set;

import es.unican.ps.domain.Vehiculo;

public interface IVehiculosDAO {
	
	/**
	 * Metodo que anhade un vehiculo a la base de datos
	 * @param v vehiculo a anhadir
	 * @return el vehiculo creado
	 */	
	public Vehiculo creaVehiculo(Vehiculo v);
	
	/**
	 * Metodo que elimina un vehiculo a la base de datos
	 * @param matricula del vehiculo a eliminar
	 * @return vehiculo eliminada
	 */
	public Vehiculo eliminarVehiculo(String matricula);
	
	/**
	 * Metodo que actualiza un vehiculo a la base de datos
	 * @param v vehiculo a actualziar
	 * @return el vehiculo actualziada
	 */
	public Vehiculo actualizaVehiculo(Vehiculo v);
	
	/**
	 * Metodo que busca un vehiculo 
	 * @param matricula del vehiculo
	 * @return el vehiculo enonrtado o null si no lo encuentra
	 */
	public Vehiculo vehiculo(String matricula);
	
	/**
	 * Metodo que retorna todos los vehiculo del sistema.
	 * @return listado de vehiculo.
	 */
	public List<Vehiculo> vehiculos();
}
