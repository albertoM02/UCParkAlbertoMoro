package es.unican.ps.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="Denuncia")
public class Denuncia implements Serializable {
	
	@Id
	@GeneratedValue
	private String ID;
	
	private Date fecha; 
	private double importe;
	private String causa;
	
	
	@ManyToOne
	private Vehiculo vehiculoDenunciado;
	
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public String getCausa() {
		return causa;
	}

	public void setCausa(String causa) {
		this.causa = causa;
	}

	public Vehiculo getVehiculoDenunciado() {
		return vehiculoDenunciado;
	}

	public void setVehiculoDenunciado(Vehiculo vehiculoDenunciado) {
		this.vehiculoDenunciado = vehiculoDenunciado;
	}

}
