package es.unican.ps.UCPark.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Time;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.unican.ps.UCPark.bussineslayer.GestionUCPark;
import es.unican.ps.UCPark.daolayer.IEstacionamientosDAOLocal;
import es.unican.ps.domain.Estacionamiento;


public class GestionUCParkTest {
	
	private GestionUCPark sut;
	
	private Estacionamiento estacionamiento;
	
	@Mock
	private IEstacionamientosDAOLocal estacionamientosDAOLocal;
	
	private Estacionamiento estacionamientoMod;
	
	@BeforeEach
	public void inicializa() {
		MockitoAnnotations.openMocks(this);
		sut = new GestionUCPark(estacionamientosDAOLocal);
		estacionamiento = new Estacionamiento();
		estacionamiento.setID("1");
		Time ahora = Time.valueOf(LocalTime.now());
		estacionamiento.setHoraInicio(ahora);
		estacionamiento.setMinutos(10);
		estacionamientoMod = estacionamiento;
		estacionamientoMod.setMinutos(estacionamientoMod.getMinutos() + 10);
		estacionamientosDAOLocal.crearEstacionamiento(estacionamiento);
		when(estacionamientosDAOLocal.estacionamiento("1")).thenReturn(estacionamiento);
		when(estacionamientosDAOLocal.actualizaEstacionamiento(estacionamiento)).thenReturn(estacionamientoMod);

	}
	
	@Test
	public void testAmpliaTiempoEstacionamiento() {
		// PU6.1
		assertEquals(estacionamientoMod, sut.ampliarEstacionamiento("1", 10));
		
		// PU6.2
		assertEquals(null, sut.ampliarEstacionamiento("12", 10));
		
		// PU6.3
		assertEquals(null, sut.ampliarEstacionamiento("1", -23));
		
		// PU6.4
		assertEquals(null, sut.ampliarEstacionamiento("1", 130));
		
	}

}
