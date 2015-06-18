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
	public List<Integer> getFilteredCasa(String titulo,String barrio,String tipoProp, int cantbanios, int cantCuartos,boolean piscina, boolean garage, float tamanio, int precio, String tipoNegocio) {
		

		return PropiedadDAO.getFilteredCasa(titulo,barrio,tipoProp,cantbanios,cantCuartos,piscina,garage,tamanio,precio,tipoNegocio);

	}
	@Override
	public List<Integer> getFilteredAptos(String titulo, String barrio,	String tipoProp, int cantBanios, int cantCuartos,boolean garage, float tamanio, int precio, String tipoNegocio,	int numeroap)
	{
		return PropiedadDAO.getFilteredApto( titulo, barrio, tipoProp, cantBanios, cantCuartos, garage, tamanio, precio, tipoNegocio, numeroap);
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
	
	public void modificarCasa(String usuario,int IdGeom,String titulo,int direccion,String barrio,String tipoProp, String tipoNegocio, int cantBanios, int cantCuartos,boolean piscina, boolean garage, int precio, float tamanio,String estado) {
		try{
			
			Casa c =PropiedadDAO.getCasaFromGeom(IdGeom);			
			Usuario u=	UsuarioDAO.getUsuario(usuario);
			c.setEncargado(u);
			c.setBarrio(barrio);
			c.setCantBanios(cantBanios);
			c.setCantCuartos(cantCuartos);
			c.setDireccion(direccion);
			c.setEstado(estado);
			c.setGarage(garage);
			c.setPiscina(piscina);
			c.setPrecio(precio);
			c.setTamanio(tamanio);
			c.setTitulo(titulo);
			
			
			
			PropiedadDAO.modificarCasa(c);				

		}		
			
		catch(Exception e){
			e.printStackTrace();
			
		}		
	}
	
	
	public void modificarApto(String usuario,int IdGeom,String titulo,int direccion,String barrio,String tipoProp, String tipoNegocio, int cantBanios, int cantCuartos, boolean garage, int precio, float tamanio,int numeroap,String estado) {
		try{
			
			Apartamento ap = PropiedadDAO.AptoFromGeom(IdGeom);
			Usuario u=	UsuarioDAO.getUsuario(usuario);
			ap.setEncargado(u);
			ap.setBarrio(barrio);
			ap.setCantBanios(cantBanios);
			ap.setCantCuartos(cantCuartos);
			ap.setDireccion(direccion);
			ap.setEstado(estado);
			ap.setGarage(garage);
			ap.setTipoProp(tipoProp);
			ap.setTipoNegocio(tipoNegocio);
			ap.setNumeroAp(numeroap);
			ap.setPrecio(precio);
			ap.setTamanio(tamanio);
			ap.setTitulo(titulo);
			
			
			
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
    ////////////////////////// Aptos
	@Override
	public List<Integer> getDistanciaInteresApto(Integer distanciaInteres) {
		
		try{
		return   PropiedadDAO.getDistancePuntoInteresApto(distanciaInteres);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Integer> getDistanciaParadasApto(Integer distanciaParadas) {
		
		try{
		return   PropiedadDAO.getDistanceParadasApto(distanciaParadas);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
    public List<Integer> getDistanceRamblaApto(Integer distanciaRambla) {
		
			try{
			return   PropiedadDAO.getDistanceRamblaApto(distanciaRambla);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return null;
	}
    
    
    /////////////////////////


	
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
	public void eliminarFeatureApto(int idPunto) {
		
		try{
			
			  PropiedadDAO.eliminarFeatureApto(idPunto);
			
			}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}


	@Override
	public double[] getCoor(int idPunto) {
		try {
			
			return PropiedadDAO.getCoor(idPunto);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public double[] getCoorApto(int idPunto) {
		try {
			
			return PropiedadDAO.getCoorApto(idPunto);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}


	@Override

	public List<Integer> getAptoUsuario(String usuario) {
		try{
			
			return  PropiedadDAO.getAptoUsuario(usuario);
			
			}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Integer> getBusaDestino(Integer distanciaBus, String calle1,String calle2) {
		try {
			
			return PropiedadDAO.getBusaDestino(distanciaBus,calle1,calle2);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
		
	}
	@Override
	public List<Integer> getBusaDestinoApto(Integer distanciaBus, String calle1,String calle2) {
		try {
			
			return PropiedadDAO.getBusaDestinoApto(distanciaBus,calle1,calle2);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
		
	}


	@Override
	public void actualizarZonas() {
		try{
			
			PropiedadDAO.actualizarZonas();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		

	}


	@Override
	public void borrarZona(Integer idzona) {

		PropiedadDAO.borrarZona(idzona);
		
	}





}
