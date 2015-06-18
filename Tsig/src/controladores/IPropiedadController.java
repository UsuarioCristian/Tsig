package controladores;
import dominio.Apartamento;

import java.util.List;

import javax.ejb.Local;

import dominio.Casa;

@Local
public interface IPropiedadController {

	public boolean guardarCasa(String usuario,int IdGeom,String titulo,int direccion,String barrio,String tipoProp, String tipoNegocio, int cantBanios, int cantCuartos,boolean piscina, boolean garage, int precio, float tamanio);
	public Casa getCasa(Integer id);
	public boolean guardarApartamento(String usuario,int IdGeom,String titulo,int direccion,String barrio,String tipoProp, String tipoNegocio, int cantBanios, int cantCuartos, boolean garage, int precio, float tamanio,int numeroap);

	public void modificarCasa(String usuario,int IdGeom,String titulo,int direccion,String barrio,String tipoProp, String tipoNegocio, int cantBanios, int cantCuartos,boolean piscina, boolean garage, int precio, float tamanio, String estado);
	public void modificarApto(String usuario,int IdGeom,String titulo,int direccion,String barrio,String tipoProp, String tipoNegocio, int cantBanios, int cantCuartos, boolean garage, int precio, float tamanio,int numeroap,String estado);

	public List<Integer> getFilteredCasa(String titulo,String barrio,String tipoProp, int cantbanios, int cantCuartos,boolean piscina, boolean garage, float tamanio, int precio, String tipoNegocio);
	public List<Integer> getFilteredAptos(String titulo, String barrio,	String tipoProp, int cantBanios, int cantCuartos, boolean garage, float tamanio, int precio, String tipoNegocio,int numeroap);

	public List<Integer> getDistanciaInteres(Integer distanciaInteres);
	List<Integer> getDistanciaParadas(Integer distanciaParadas);
	public List<Integer> getDistanceRambla(Integer distanciaRambla);
	public List<Integer> getCasasUsuario(String usuario);
	
	public void eliminarFeature(int idPunto);
	public void eliminarFeatureApto(int idPunto);
	
	public List<Integer> getDistanciaInteresApto(Integer distanciaInteres);
	List<Integer> getDistanciaParadasApto(Integer distanciaParadas);
	public List<Integer> getDistanceRamblaApto(Integer distanciaRambla);
	public List<Integer> getBusaDestino(Integer distanciaBus, String calle1,String calle2);
	
	public Casa getCasaFromGeom(int idCasa);	
	public Apartamento getAptoFromGeom(int idPunto);
	public double[] getCoor(int idPunto);
	public double[] getCoorApto(int idPunto);

	public List<Integer> getAptoUsuario(String usuario);

	public void actualizarZonas();
	public void borrarZona(Integer idzona);

	

	
	





}
