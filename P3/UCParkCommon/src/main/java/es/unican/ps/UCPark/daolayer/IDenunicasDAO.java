package es.unican.ps.UCPark.daolayer;

import java.util.Set;

import es.unican.ps.domain.Denuncia;

public interface IDenunicasDAO {
	Denuncia creaDenuncia(Denuncia d);
	Denuncia eliminaDenuncia(String id);
	Denuncia actualizaDenuncia(Denuncia d);
	Denuncia denuncia(String id);
	Set<Denuncia> denuncias();

}
