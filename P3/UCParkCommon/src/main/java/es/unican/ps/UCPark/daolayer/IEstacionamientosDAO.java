package es.unican.ps.UCPark.daolayer;

import java.util.Set;

import es.unican.ps.domain.Estacionamiento;

public interface IEstacionamientosDAO {

	/**
	 * Metodo que anhade un estacionamiento a la base de datos
	 * @param e estacionamiento a anhadir
	 * @return el estacionamiento creado
	 */
	Estacionamiento crearEstacionamiento(Estacionamiento e);
	
	/**
	 * Metodo que elimina un estacionamiento a la base de datos
	 * @param id del estacionamiento a eliminar
	 * @return estacionamiento eliminada
	 */
	Estacionamiento eliminarEstacionamiento(String ID);
	
	/**
	 * Metodo que actualiza un estacionamiento a la base de datos
	 * @param e estacionamiento a actualziar
	 * @return el estacionamiento actualziada
	 */
	Estacionamiento actualizaEstacionamiento(Estacionamiento e);
	
	/**
	 * Metodo que busca un estacionamiento 
	 * @param id del estacionamiento
	 * @return el estacionamiento enonrtado o null si no lo encuentra
	 */
	Estacionamiento estacionamiento(String ID);
	
	/**
	 * Metodo que retorna todos los estacionameintos del sistema.
	 * @return listado de estacionameinto.
	 */
	Set<Estacionamiento> estacionamientos();
	
}
