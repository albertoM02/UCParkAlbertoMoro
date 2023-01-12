package es.unican.ps.UCPark.bussineslayer;


import java.util.Set;

import es.unican.ps.domain.Estacionamiento;
import es.unican.ps.domain.Usuario;

public interface IGestionEstacionamientos {

	/**
	 * Metodo que anhade un estacionamiento a un vehiculo
	 * @param e estacionamiento a anhadir
	 * @param matricula del vehiculo
	 * @return null si ya existe el estacionamiento o el vehiouclo no existe, el estacionamiento
	 * en otro caso o el tiempo es amyor de 120 min
	 */
	public Estacionamiento añadirEstacionamiento(Estacionamiento e, String matricula);

	/**
	 * Metodo para ampliar el estaconamiento de un vehiculo
	 * @param ID id del estacionamiento a aumentra
	 * @param tAumento tiempo a aumentar
	 * @return null si el tiempo es mayor de 120 min o menor de 0, o el estacionamiento 
	 * no existe.
	 */
	public Estacionamiento ampliarEstacionamiento(String ID, int tAumento);
	
	/**
	 * Metodo para eliminar un estacionamiento
	 * @param ID del estacionamiento
	 * @return null si no existe el estacionamiento en otro caso
	 */
	public Estacionamiento eliminarEstacionamiento(String ID);
	
	/**
	 * Metodo para terminar un estacionamiento
	 * @param ID del estacionamiento
	 * @return null si no existe el estacionamiento en otro caso
	 */
	public Estacionamiento terminarEstacionamiento(String ID);
	
	/**
	 * Metodo para consultar el estacionamiento de un coche
	 * @param ID del estacionamiento
	 * @return null si no tiene o el coche no existe y el estacionamiento en otro caso
	 */
	public Estacionamiento comprobarEstacionamiento(String matricula);
	
	/**
	 * Consulta los estacionamiento históricos de todos los vehiculos de un usuario
	 * @param u 
	 * @return la lista, estara vacía si no tiene o no existe.
	 */
	public Set<Estacionamiento> consultarEstacionamientos(Usuario u);

}
