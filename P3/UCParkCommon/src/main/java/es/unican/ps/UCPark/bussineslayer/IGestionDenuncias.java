package es.unican.ps.UCPark.bussineslayer;

import java.util.Set;

import es.unican.ps.domain.Denuncia;

public interface IGestionDenuncias {

	public Denuncia añadirDenuncia(Denuncia d, String matricula);
	public Denuncia eliminaDenuncia(Denuncia d, String matricula);
	public Set<Denuncia> consultarDenuncia(String ID);
	
}
