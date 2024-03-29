package es.unican.ps.domain;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="Vehiculo")
public class Vehiculo implements Serializable {

	@OneToOne
	private Usuario propietario;
	
	@OneToOne
	private Estacionamiento estacionamientoEnVigor;
	
	@OneToMany(mappedBy="Vehiculo")
	private Set<Estacionamiento> estacionamientosHistoricos;
	
	@Id
	private String matricula;
	private String marca;
	private String modelo;
	
	@OneToMany(mappedBy="VehiculoDenunciado")
	private Set<Denuncia> denunciasEnVigor;
	
	public Usuario getPropietario() {
		return propietario;
	}
	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}
	public Estacionamiento getEstacionamientoEnVigor() {
		return estacionamientoEnVigor;
	}
	public void setEstacionamientoEnVigor(Estacionamiento estacionamientoEnVigor) {
		this.estacionamientoEnVigor = estacionamientoEnVigor;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public Set<Denuncia> getDenunciasEnVigor() {
		return denunciasEnVigor;
	}
	public void setDenunciasEnVigor(Set<Denuncia> denunciasEnVigor) {
		this.denunciasEnVigor = denunciasEnVigor;
	}
	public Set<Estacionamiento> getEstacionamientosHistoricos() {
		return estacionamientosHistoricos;
	}
	public void setEstacionamientosHistoricos(Set<Estacionamiento> estacionamientosHistoricos) {
		this.estacionamientosHistoricos = estacionamientosHistoricos;
	}
	
}
