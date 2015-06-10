package controladores;


import dominio.Apartamento;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import persistencia.IPropiedadDAO;
import persistencia.IUsuarioDAO;
import controladores.IPropiedadController;
import dominio.Casa;
import dominio.Usuario;

@Stateless
public class PropiedadController implements IPropiedadController{

	@EJB
	private IPropiedadDAO PropiedadDAO;
	@EJB
	private IUsuarioDAO UsuarioDAO;
	

	public boolean guardarCasa(String usuario,int IdGeom,String titulo, int direccion,String barrio,String tipoProp, String tipoNegocio, int cantBanios, int cantCuartos,boolean piscina, boolean garage, int precio, float tamanio) {
		try{
					
			Casa c = new Casa(IdGeom, titulo, direccion, barrio, tipoProp, tipoNegocio, cantBanios, cantCuartos, piscina, garage,"privada", precio, tamanio);
			
			
			
			Usuario u=	UsuarioDAO.getUsuario(usuario);
			
			c.setEncargado(u);
			return PropiedadDAO.guardarPropiedad(c);				

		}		
			
		catch(Exception e){
			e.printStackTrace();
			return false;
		}		
		
	}


	@Override
	public Casa getCasa(Integer id) {
		try{
			return PropiedadDAO.getCasa(id);
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		return null;
		
	}


	@Override
	public List<Integer> getFilteredCasa(String titulo,String barrio,String tipoProp, int cantbanios, int cantCuartos,boolean piscina, boolean garage) {
		

		return PropiedadDAO.getFilteredCasa(titulo,barrio,tipoProp,cantbanios,cantCuartos,piscina,garage);

	}
	
	
	public boolean guardarApartamento(String usuario,int IdGeom,String titulo, int direccion,String barrio,String tipoProp, String tipoNegocio, int cantBanios, int cantCuartos, boolean garage, int precio, float tamanio,int numeroap)  {
		try{
					
			Apartamento ap = new Apartamento(IdGeom, titulo, direccion, barrio, tipoProp, tipoNegocio, cantBanios, cantCuartos, garage,"privada", precio, tamanio,numeroap);
			
			Usuario u=	UsuarioDAO.getUsuario(usuario);
			
			ap.setEncargado(u);
			return PropiedadDAO.guardarApartamento(ap);				

		}		
			
		catch(Exception e){
			e.printStackTrace();
			return false;
		}		
		
	}
	
	public void modificarCasa(Casa c) {
		try{
	
			PropiedadDAO.modificarCasa(c);
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
	}
	
	
	public void modificarApto(Apartamento apart) {
		try{
	
			PropiedadDAO.modificarApto(apart);
		}catch(Exception e){
			e.printStackTrace();
			
		}
		

	}


	@Override
	public List<Integer> getDistanciaInteres(Integer distanciaInteres) {
		
		try{
		return   PropiedadDAO.getDistancePuntoInteres(distanciaInteres);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Integer> getDistanciaParadas(Integer distanciaParadas) {
		
		try{
		return   PropiedadDAO.getDistanceParadas(distanciaParadas);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public Casa getCasaFromGeom(int idPunto) {
		try{
			
			return  PropiedadDAO.getCasaFromGeom(idPunto);
			
			}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public Apartamento getAptoFromGeom(int idPunto) {
	try{
			
			return  PropiedadDAO.AptoFromGeom(idPunto);
			
			}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
