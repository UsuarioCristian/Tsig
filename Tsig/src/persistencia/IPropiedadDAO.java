package persistencia;

import dominio.Apartamento;

import java.util.List;

import javax.ejb.Local;

import dominio.Casa;


@Local
public interface IPropiedadDAO {
	
	public boolean guardarPropiedad(Casa casa);
	public boolean guardarApartamento(Apartamento apart);
	public Casa getCasa(Integer id);
	public List<Casa> consultaPropiedad(int cantCuartos);
	public List<Integer> getFilteredCasa(String titulo, String barrio,String tipoProp, int cantbanios, int cantCuartos, boolean piscina,boolean garage, float tamanio, int precio, String tipoNegocio);
	public List<Integer> getFilteredApto(String titulo, String barrio,	String tipoProp, int cantBanios, int cantCuartos,boolean garage, float tamanio, int precio, String tipoNegocio,	int numeroap);
	
	
	public List<Integer> getDistancePuntoInteres(Integer distance);

	List<Integer> getDistanceParadas(Integer distance);

	public void modificarCasa(Casa c);
	public void modificarApto(Apartamento apart);
	public Casa getCasaFromGeom(int idCasa);
	public Apartamento AptoFromGeom(int idPunto);
	public List<Integer> getDistanceRambla(Integer distance);

	public List<Integer> getCasasUsuario(String usuario);
	public void eliminarFeature(int idPunto);

	
	public List<Integer> getDistancePuntoInteresApto(Integer distanciaInteres);
	public List<Integer> getDistanceParadasApto(Integer distanciaParadas);
	public List<Integer> getDistanceRamblaApto(Integer distanciaRambla);
	



	
	

}
