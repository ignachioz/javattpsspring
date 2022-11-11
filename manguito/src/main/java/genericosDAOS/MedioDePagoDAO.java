package genericosDAOS;

import ttps.spring.manguitoClases.MedioDePago;

public interface MedioDePagoDAO extends GenericDAO<MedioDePago>{
	public MedioDePago obtenerMedioDePagoPorNombre(String nombre);
}
