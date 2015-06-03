package persistencia;

import java.util.List;

import javax.ejb.Local;

import dominio.Casa;


@Local
public interface IPropiedadDAO {
	
	public boolean guardarPropiedad(Casa casa);

	public Casa getCasa(Integer id);

	public List<Casa> consultaPropiedad(int cantCuartos);

	public List<Integer> getFilteredCasa(String titulo, String barrio,String tipoProp, int cantbanios, int cantCuartos, boolean piscina,boolean garage);
	
	public List<Integer> getDistance(Integer distance);
}
