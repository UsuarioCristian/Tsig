package controladores;

import javax.ejb.Local;

import dominio.Casa;

@Local
public interface IPropiedadController {

	public boolean guardarCasa(String usuario,int IdGeom,String titulo,int direccion,String barrio,String tipoProp, int cantBanios, int cantCuartos,boolean piscina, boolean garage);
	public Casa getCasa(Integer id);
}
