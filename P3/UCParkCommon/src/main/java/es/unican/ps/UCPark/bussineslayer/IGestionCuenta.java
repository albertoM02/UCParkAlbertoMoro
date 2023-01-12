package es.unican.ps.UCPark.bussineslayer;


import es.unican.ps.domain.Usuario;


public interface IGestionCuenta {
	
	/**
	 * Metodo que crea un nuevo usuario si no existe.
	 * @param u usuario a registrar.
	 * @return null si el usuario ya existe, el usuario en caso contrario.
	 */
	public Usuario registrarse(Usuario u);
	
	/**
	 * Metodo que consulta si un usuario existe en la base de datos.
	 * @param email que identifica al usuario
	 * @return Usuario si está en la base de datos, null en caso opuesto.
	 */
	public Usuario consultarUsuario(String email);
	
	/**
	 * Metodo para iniciar sesion
	 * @param email email del usuario
	 * @param contrasenha contrasenha
	 * @return el usuario en caso de iniciar sesion, null si el usuario
	 * no existe o la contrasenha es incorrecta.
	 */
	public Usuario login(String email, String contrasenha);

}
