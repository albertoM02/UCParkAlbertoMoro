package es.unican.ps.UCPark.bussineslayer;


import es.unican.ps.domain.Usuario;


public interface IGestionCuenta {
	public Usuario registrarse(Usuario u);
	
	public Usuario consultarUsuario(String email);
	
	public Usuario login(String email, String contrasenha);

}
