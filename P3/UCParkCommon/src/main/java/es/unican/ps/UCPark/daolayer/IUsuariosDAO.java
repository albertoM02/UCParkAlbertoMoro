package es.unican.ps.UCPark.daolayer;

import java.util.Set;

import es.unican.ps.domain.Usuario;

public interface IUsuariosDAO {
	
	/**
	 * Metodo que anhade un usuario a la base de datos
	 * @param u usuario a anhadir
	 * @return el usuario creado
	 */
	Usuario creaUsuarios(Usuario u);
	
	/**
	 * Metodo que elimina un usuario a la base de datos
	 * @param email del usuario a eliminar
	 * @return usuario eliminada
	 */
	Usuario eliminaUsuario(String email);
	
	/**
	 * Metodo que actualiza un usuario a la base de datos
	 * @param u usuario a actualziar
	 * @return el usuario actualziada
	 */
	Usuario actualizaUsuario(Usuario u);
	
	/**
	 * Metodo que busca un usuario 
	 * @param email del usuario
	 * @return el usuario enonrtado o null si no lo encuentra
	 */
	Usuario usuario(String email);
	
	/**
	 * Metodo que retorna todos los usuarios del sistema.
	 * @return listado de usuario.
	 */
	Set<Usuario> usuarios();
}
