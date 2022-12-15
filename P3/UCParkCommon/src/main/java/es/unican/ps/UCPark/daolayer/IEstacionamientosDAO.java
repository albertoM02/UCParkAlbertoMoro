package es.unican.ps.UCPark.daolayer;

import java.util.Set;

import es.unican.ps.domain.Estacionamiento;

public interface IEstacionamientosDAO {

	Estacionamiento crearEstacionamiento(Estacionamiento e);
	Estacionamiento eliminarEstacionamiento(String ID);
	Estacionamiento actualizaEstacionamiento(Estacionamiento e);
	Estacionamiento estacionamiento(String ID);
	Set<Estacionamiento> estacionamientos();
	
}
