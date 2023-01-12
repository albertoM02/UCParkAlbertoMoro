package es.unican.ps.UCPark.bussineslayer;

import java.util.Set;

import es.unican.ps.domain.Denuncia;

public interface IGestionDenuncias {

	/**
	 * Metodo para anhadir una nueva denuncia a un vehículo.
	 * @param d denuncia a anhadir
	 * @param matricula del vehiculo al que se le anahde la denuncian
	 * @return si el vehiculo no existe o la denuncia ya esta registrada null,
	 * en caso contrario la denuncia
	 */
	public Denuncia añadirDenuncia(Denuncia d, String matricula);
	
	/**
	 * Metodo para eliminar una denuncia a un vehículo.
	 * @param d denuncia a eliminar
	 * @param matricula del vehiculo al que se le elimina la denuncian
	 * @return si el vehiculo no existe o la denuncia no existe null,
	 * en caso contrario la denuncia
	 */
	public Denuncia eliminaDenuncia(Denuncia d, String matricula);
	
	/**
	 * Metodo que retorna las denuncias de un vehiculo.
	 * @param ID
	 * @return null si no se encuentra el vehiculo, la denuncia en otro caso
	 */
	public Set<Denuncia> consultarDenuncia(String ID);
	
}
