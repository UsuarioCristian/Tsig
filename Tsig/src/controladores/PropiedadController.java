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
	
	public void modificarCasa(String usuario,int IdGeom,String titulo,int direccion,String barrio,String tipoProp, String tipoNegocio, int cantBanios, int cantCuartos,boolean piscina, boolean garage, int precio, float tamanio) {
		try{
			
			Casa c = new Casa(IdGeom, titulo, direccion, barrio, tipoProp, tipoNegocio, cantBanios, cantCuartos, piscina, garage,"privada", precio, tamanio);
			
			
			
			Usuario u=	UsuarioDAO.getUsuario(usuario);
			
			c.setEncargado(u);
			PropiedadDAO.modificarCasa(c);				

		}		
			
		catch(Exception e){
			e.printStackTrace();
			
		}		
	}
	
	
	public void modificarApto(String usuario,int IdGeom,String titulo,int direccion,String barrio,String tipoProp, String tipoNegocio, int cantBanios, int cantCuartos, boolean garage, int precio, float tamanio,int numeroap) {
		try{
			
			Apartamento ap = new Apartamento(IdGeom, titulo, direccion, barrio, tipoProp, tipoNegocio, cantBanios, cantCuartos, garage,"privada", precio, tamanio,numeroap);
			
			Usuario u=	UsuarioDAO.getUsuario(usuario);
			
			ap.setEncargado(u);
			PropiedadDAO.modificarApto(ap);				

		}		
			
		catch(Exception e){
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
	
    public List<Integer> getDistanceRambla(Integer distanciaRambla) {
		
			try{
			return   PropiedadDAO.getDistanceRambla(distanciaRambla);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return null;
	}


	
	public Casa getCasaFromGeom(int idCasa) {
		try{
			return   PropiedadDAO.getCasaFromGeom(idCasa);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return null;
	}


	public Apartamento getAptoFromGeom(int idPunto) {
	try{
			
			return  PropiedadDAO.AptoFromGeom(idPunto);
			
			}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}


	
	public List<Integer> getCasasUsuario(String usuario) {
		try{
			
			return  PropiedadDAO.getCasasUsuario(usuario);
			
			}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}


	
	public void eliminarFeature(int idPunto) {
	
		try{
			
			  PropiedadDAO.eliminarFeature(idPunto);
			
			}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
