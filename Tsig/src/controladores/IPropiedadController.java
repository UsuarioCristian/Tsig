package controladores;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;

import dominio.Casa;

@Local
public interface IPropiedadController {

	public boolean guardarCasa(String usuario,int IdGeom,String titulo,int direccion,String barrio,String tipoProp, int cantBanios, int cantCuartos,boolean piscina, boolean garage);
	public Casa getCasa(Integer id);
	public List<Integer> getFilteredCasa(String titulo,String barrio,String tipoProp, int cantbanios, int cantCuartos,boolean piscina, boolean garage);
	public List<Integer> getDistanciaInteres(Integer distanciaInteres);
	public List<Integer> getDistanciaParadas(Integer distanciaParadas);
	public List<Integer> getDistanceRambla(Integer distanciaRambla);
	public Casa getCasaFromGeom(int idCasa);
}
