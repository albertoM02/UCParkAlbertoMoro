package es.unican.ps.UCPark.daolayer;

import java.util.Set;

import es.unican.ps.domain.Usuario;

public interface IUsuariosDAO {
	Usuario creaUsuarios(Usuario u);
	Usuario eliminaUsuario(String email);
	Usuario actualizaUsuario(Usuario u);
	Usuario usuario(String email);
	Set<Usuario> usuarios();
}
