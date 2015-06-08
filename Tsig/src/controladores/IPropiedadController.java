package controladores;

import dominio.Apartamento;

import java.util.List;

import javax.ejb.Local;

import dominio.Casa;

@Local
public interface IPropiedadController {

	public boolean guardarCasa(String usuario,int IdGeom,String titulo,int direccion,String barrio,String tipoProp, String tipoNegocio, int cantBanios, int cantCuartos,boolean piscina, boolean garage, int precio, float tamanio);
	public Casa getCasa(Integer id);
	public List<Integer> getFilteredCasa(String titulo,String barrio,String tipoProp, int cantbanios, int cantCuartos,boolean piscina, boolean garage);
	public boolean guardarApartamento(String usuario,int IdGeom,String titulo,int direccion,String barrio,String tipoProp, String tipoNegocio, int cantBanios, int cantCuartos, boolean garage, int precio, float tamanio);
	public void modificarCasa(Casa c);
	public void modificarApto(Apartamento apart);
			
}
