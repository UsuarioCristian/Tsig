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
	public List<Integer> getFilteredCasa(String titulo, String barrio,String tipoProp, int cantbanios, int cantCuartos, boolean piscina,boolean garage);

	public List<Integer> getDistancePuntoInteres(Integer distance);

	List<Integer> getDistanceParadas(Integer distance);

	public void modificarCasa(Casa c);
	public void modificarApto(Apartamento apart);
	public Casa getCasaFromGeom(int idPunto);
	public Apartamento AptoFromGeom(int idPunto);

}
