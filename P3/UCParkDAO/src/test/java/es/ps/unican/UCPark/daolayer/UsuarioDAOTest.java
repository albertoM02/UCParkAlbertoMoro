package es.ps.unican.UCPark.daolayer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import es.unican.ps.UCPark.daolayer.UsuariosDAO;
import es.unican.ps.domain.Usuario;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;




public class UsuarioDAOTest {
	
	@Mock
	private EntityManager entityManager;
		

	
	private UsuariosDAO sut;
	
	private Usuario usuario = new Usuario();
	
	@BeforeEach
	public void inicializa() {
		MockitoAnnotations.openMocks(this);
		sut = new UsuariosDAO(entityManager);
		usuario.setEmail("aa@hotmail.com");
		usuario.setContraseña("aa");
		when(entityManager.find(Usuario.class, "aa@hotmail.com")).thenReturn(usuario);
		
	}
	
	@Test
	public void testCreaUsuario() {
		//PU1.1
		sut.creaUsuarios(usuario);
		Usuario obtenido = sut.usuario(usuario.getEmail());
		assertEquals(usuario.getEmail(), obtenido.getEmail());
		
		//PU1.2
		//Ahora que ya estaría anhadido debe lanzar la excpeción.
		Mockito.doThrow(new EntityExistsException()).when(entityManager).persist(usuario);
		assertEquals(null, sut.creaUsuarios(usuario));
		
	}
	
	@Test
	public void testEliminaUsuario() {
		//PU1.1
		when(entityManager.find(Usuario.class, "aa@hotmail.com")).thenReturn(usuario);
		Usuario obtenido = sut.eliminaUsuario(usuario.getEmail());
		assertEquals(usuario.getEmail(), obtenido.getEmail());
		verify(entityManager).remove(usuario);

		
		//PU1.2
		//Ahora que ya estaría anhadido debe lanzar la excpeción.
		when(entityManager.find(Usuario.class, "aa@hotmail.com")).thenReturn(null);
		assertEquals(null, sut.eliminaUsuario(usuario.getEmail()));
		
	}
	

}
