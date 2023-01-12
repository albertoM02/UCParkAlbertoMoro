package es.unican.ps.UCPark.daolayer;

import java.util.Set;

import es.unican.ps.domain.Denuncia;

public interface IDenunicasDAO {
	/**
	 * Metodo que anhade una denuncia a la base de datos
	 * @param d denuncia a anhadir
	 * @return la denuncia creada
	 */
	Denuncia creaDenuncia(Denuncia d);
	
	/**
	 * Metodo que elimina una denuncia a la base de datos
	 * @param id de la denuncia a eliminar
	 * @return denuncia eliminada
	 */
	Denuncia eliminaDenuncia(String id);
	
	/**
	 * Metodo que actualiza una denuncia a la base de datos
	 * @param d denuncia a actualziar
	 * @return la denuncia actualziada
	 */
	Denuncia actualizaDenuncia(Denuncia d);
	
	/**
	 * Metodo que busca una denuncia 
	 * @param id de denuncia a buscar
	 * @return la denuncia enonrtada o null si no lo encuentra
	 */
	Denuncia denuncia(String id);
	
	/**
	 * Metodo que retorna las denuncias
	 * @return denuncias del sisetma
	 */
	Set<Denuncia> denuncias();

}
