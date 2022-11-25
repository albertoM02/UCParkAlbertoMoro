package es.unican.ps.UCPark.bussineslayer;

import es.unican.ps.domain.Denuncia;

public interface IGestionDenuncias {

	public Denuncia añadirDenuncia(Denuncia d, String matricula);
	public Denuncia eliminaDenuncia(String ID);
	public Denuncia consultarDenuncia(String ID);
	
}
