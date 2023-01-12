package es.unican.ps.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@NamedQueries({
	@NamedQuery(name="usuarios", query="SELECT u FROM Usuario u")
})
@Table(name="Usuario")
public class Usuario implements Serializable {
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="fkUsuario")
	private List<Vehiculo> vehiculos;
	
	@Id
	private String email;
	private String contrasenha;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public List<Vehiculo> getVehiculos() {
		return vehiculos;
	}
	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}
	public String getContraseña() {
		return contrasenha;
	}
	public void setContraseña(String contraseña) {
		this.contrasenha = contraseña;
	}
}
