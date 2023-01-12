package es.unican.ps.UCPark.bussineslayer;

import es.unican.ps.domain.Usuario;
import es.unican.ps.domain.Vehiculo;

public interface IGestionVehiculos {

	/**
	 * Metodo que anhade un vehiculo a un usuario
	 * @param v vehiculo a anhadir
	 * @param u propietartio
	 * @return null si el vehiculo ya existe o el usuario no exite,
	 * el vehiculo en otro caso
	 */
	public Vehiculo añadirVehiculo(Vehiculo v, Usuario u);
	
	/**
	 * Metodo que eliminar un vehiculo
	 * @param matricula vehiculo a eliminar
	 * @return null si el vehiculo no existe y
	 * el vehiculo en otro caso
	 */
	public Vehiculo eliminarVehiculo(String matricula);
	
	/**
	 * Metodo que consultar un vehiculo
	 * @param matricula vehiculo a consultar
	 * @return null si el vehiculo no existe y
	 * el vehiculo en otro caso
	 */
	public Vehiculo consultaVehiculo(String matricula);
}
