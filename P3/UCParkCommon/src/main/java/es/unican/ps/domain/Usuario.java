package es.unican.ps.domain;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="Usuario")
public class Usuario implements Serializable {
	
	
	private Set<MedioPago> mediosPago;
	private Set<Vehiculo> vehiculos;
	
	@Id
	private String email;
	private String contraseña;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<MedioPago> getMediosPago() {
		return mediosPago;
	}
	public void setMediosPago(Set<MedioPago> mediosPago) {
		this.mediosPago = mediosPago;
	}
	public Set<Vehiculo> getVehiculos() {
		return vehiculos;
	}
	public void setVehiculos(Set<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
}
