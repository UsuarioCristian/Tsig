package presentacion;


import java.io.IOException;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import javax.faces.context.FacesContext;

import controladores.IPropiedadController;
import dominio.Apartamento;
import dominio.Casa;



@ManagedBean
@SessionScoped
public class PropiedadMB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private int idborro=0;
	private int idPunto=0;
	private int idDep=0;
	private int idCasa=0;	
	private int direccion=0;	
	private String barrio="";	
	private String tipoProp="";	
	private String tipoNegocio="";
	private int cantBanios=0;		
	private int cantCuartos=0;	
	private boolean garage=false;	
	private boolean piscina=false;
	private String titulo="";
	private String propiedad="";
	private String propiedadClick="";
	private String estado="";

	/* For advanced filters*/
	private Integer distanciaInteres=0;
	private Integer distanciaBus=0;
	private Integer distanciaMar=0;
	private Integer distanciaParada=0;
	private String calle1="";
	private String calle2="";
	
	
	private int precio;
	private float tamanio;
	private int numeroap;

	private double x;
	private double y;
	
	private List<Integer> casas= new ArrayList();
	private List<Integer> aptos= new ArrayList();
	
	@EJB
	IPropiedadController ipc;

	public String guardarPropiedad(){
		

		try {
			
			String usuario =(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
			
			ipc.guardarCasa(usuario,idCasa,titulo, direccion, barrio, tipoProp, tipoNegocio, cantBanios, cantCuartos, piscina, garage, precio, tamanio);
			
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
		
	}
	
	

	public String guardarApartamento(){
		

		try {
			
			String usuario =(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
								
			ipc.guardarApartamento(usuario,idDep,titulo, direccion, barrio, tipoProp, tipoNegocio, cantBanios, cantCuartos,  garage, precio, tamanio,numeroap);
			
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
		
	}
	
	
	
	
	public void getInfo(){
		
		try{
			

			Casa c= ipc.getCasaFromGeom(idPunto);


			//Casa c= ipc.getCasa(idCasa);

			
			if (c!=null){
				this.titulo=c.getTitulo();
				this.direccion = c.getDireccion();
			    this.barrio = c.getBarrio();
			    this.tipoProp = c.getTipoProp();
			    this.cantBanios = c.getCantBanios();
			    this.cantCuartos = c.getCantCuartos();
			    this.garage = c.isGarage();
			    this.piscina = c.isPiscina();
			    this.precio=c.getPrecio();
			    this.tamanio=c.getTamanio();
			}else
			{
				System.out.println("LA CASA ES NULL");
			}
		}
		catch(Exception e){
		e.printStackTrace();
		}
		
	}
	public void getInfoApto(){
			
			try{
				
				Apartamento a= ipc.getAptoFromGeom(idPunto);
				
				if (a!=null){
					this.titulo=a.getTitulo();
					this.direccion = a.getDireccion();
				    this.barrio = a.getBarrio();
				    this.tipoProp = a.getTipoProp();
				    this.cantBanios = a.getCantBanios();
				    this.cantCuartos = a.getCantCuartos();
				    this.garage = a.isGarage();
				    this.numeroap= a.getNumeroAp();
				    this.precio=a.getPrecio();
				    this.tamanio=a.getTamanio();
				   
				}else
				{
					System.out.println("el apto ES NULL");
				}
			}
			catch(Exception e){
			e.printStackTrace();
			}
			
		}
	public void getUser(){
		
		try {
			
			casas.clear();
			aptos.clear();
			String usuario =(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
			System.out.println("Entre getUser....."+ usuario);
			casas=ipc.getCasasUsuario(usuario);
			aptos=ipc.getAptoUsuario(usuario);
			System.out.println("Entre getUser despues consulta"+ casas );
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		
		
	}
	
	/* Filters */
	public void consultaPropiedad(){
		 
		try{

		
			casas.clear();
			aptos.clear();
			
			
		    casas = ipc.getFilteredCasa(titulo,barrio,tipoProp,cantBanios,cantCuartos,piscina,garage,tamanio,precio,tipoNegocio);
		    aptos = ipc.getFilteredAptos(titulo,barrio,tipoProp,cantBanios,cantCuartos,garage,tamanio,precio,tipoNegocio,numeroap);
		    
		    for(Integer c: casas){
		    	System.out.println("casas filtro pichi "+c);
	    	}
		    
		    for(Integer d: aptos){
		    	System.out.println("aptos filtro pichi "+d);
	    	}
		    
		    if(distanciaInteres!=0){
		    	
		    	List<Integer> aux=ipc.getDistanciaInteres(distanciaInteres);
		    	List<Integer> auxa=ipc.getDistanciaInteresApto(distanciaInteres);
		    	    	
		    	if (aux != null){
		    		casas.retainAll(aux);
		    		aux.clear();
		    	}else{
		    		System.out.println("Casas Cleared dist int");
		    		casas.clear(); 
		    	}
		    	if (auxa != null){
		    		aptos.retainAll(auxa);
		    		auxa.clear();
		    	}else{
		    		System.out.println("Casas Cleared dist int");
		    		aptos.clear(); 
		    	}
		    }
		    	
			if(distanciaParada!=0){
				
			    	List<Integer> aux2=ipc.getDistanciaParadas(distanciaParada);
			    	List<Integer> aux2a=ipc.getDistanciaParadasApto(distanciaParada);
			    	
		    	
			    	if (aux2 != null){
			    		casas.retainAll(aux2);
			    		aux2.clear();
			    	}
			    	else{
			    		casas.clear();
			    	}
			    	
			    	if (aux2a != null){
			    		aptos.retainAll(aux2a);
			    		aux2a.clear();
			    	}else{
			    		System.out.println("Casas Cleared dist int");
			    		aptos.clear(); 
			    	}
			    	
			}
		    
			
			if(distanciaMar!=0){
				
		    	List<Integer> aux3=ipc.getDistanceRambla(distanciaMar);
		    	List<Integer> aux3a=ipc.getDistanceRambla(distanciaMar);

		    	
		    	if (aux3 != null){
		    		casas.retainAll(aux3);
		    		aux3.clear();
		    	}else{
		    		casas.clear();
		    	}
		    	
		    	if (aux3a != null){
		    		aptos.retainAll(aux3a);
		    		aux3a.clear();
		    	}else{
		    		System.out.println("Casas Cleared dist int");
		    		aptos.clear(); 
		    	}
		    
		    	
			}
			if(distanciaBus!=0){
	    		
	    		List<Integer> aux4=	ipc.getBusaDestino(distanciaBus,calle1,calle2);
	    		
	    		if (aux4 != null){
		    		casas.retainAll(aux4);
		    		aux4.clear();
		    	}else{
		    		casas.clear();
		    	}
	    	
	    	}
	 
	    
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void eliminarFeature(){
		try{
			if("Casa".compareTo(this.propiedadClick)==0){
				
				ipc.eliminarFeature(idPunto);
				
			}	else{
				
				ipc.eliminarFeatureApto(idPunto);
				
			}
			
			
		
		
			
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			
		}
		catch(Exception e){
		e.printStackTrace();
		}
	
		
	}
	
	public void redirect (){
		
		
		try {
			
			
			String countrry = (String)FacesContext.getCurrentInstance().getAttributes().get("maxi");
			
			System.out.println("ID DE LA CASA CARAJO "+ idPunto);
			
			System.out.println("ID DE LA CASA CARAJO country "+ countrry);
			 
			System.out.println("titulo"+ titulo);
				
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String detalleInmueble(){
		
		try{
			
			
		if("Casa".compareTo(this.propiedadClick)==0){
			
			Casa c=	ipc.getCasaFromGeom(idPunto);
			System.out.println("id punto pre redirect: "+idPunto);
			  
			  
			double[] coor= ipc.getCoor(idPunto);
			
			this.x=coor[0];
			this.y=coor[1];
			
			System.out.println("MB x :"+ x+" y: "+y);
					   
			this.titulo=c.getTitulo();
			this.direccion = c.getDireccion();
		    this.barrio = c.getBarrio();
		    this.tipoProp = c.getTipoProp();
		    this.cantBanios = c.getCantBanios();
		    this.cantCuartos = c.getCantCuartos();
		    this.garage = c.isGarage();
		    this.precio=c.getPrecio();
		    this.tamanio=c.getTamanio();
		    this.tipoNegocio=c.gettipoNegocio();
		    this.piscina=c.isPiscina();
		      
		    
		    System.out.println("id punto post redirect: "+idPunto);
		    
		    System.out.println("dsa"+this.titulo);
			
		}	else{
			
			Apartamento c=	ipc.getAptoFromGeom(idPunto);
			System.out.println("id punto pre redirect: "+idPunto);
			  
			  
			double[] coor= ipc.getCoorApto(idPunto);
			
			this.x=coor[0];
			this.y=coor[1];
			
			System.out.println("MB x :"+ x+" y: "+y);
					   
			this.titulo=c.getTitulo();
			this.direccion = c.getDireccion();
		    this.barrio = c.getBarrio();
		    this.tipoProp = c.getTipoProp();
		    this.cantBanios = c.getCantBanios();
		    this.cantCuartos = c.getCantCuartos();
		    this.garage = c.isGarage();
		    this.precio=c.getPrecio();
		    this.tamanio=c.getTamanio();
		    this.tipoNegocio=c.getTipoNegocio();
		    
		      
		    
		    System.out.println("id punto post redirect: "+idPunto);
		    
		    System.out.println("dsa"+this.titulo);
			
			
		}
		
		
	    
	    return "detalleInmueble.xhtml";
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return null;
	}
	
	public void actualizarZonas(){
		
		try{
			ipc.actualizarZonas();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	public String modificarInmueble(){
			
			try{
				
			System.out.println("Entre modificara...: "+idPunto);
			System.out.println("Entre modificar con propiedad...: "+ this.propiedad);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idPunto", idPunto);

			
			if("Casa".compareTo(this.propiedadClick)==0){
				System.out.println("Entre modificar casa...: "+idPunto);	

				Casa c=	ipc.getCasaFromGeom(idPunto);
				System.out.println("id punto pre redirect: "+idPunto);
				System.out.println("id idgeom pre redirect:.. "+ c.getIdGeom());

				this.idPunto=c.getIdGeom();
				this.titulo=c.getTitulo();
				this.direccion = c.getDireccion();
			    this.barrio = c.getBarrio();
			    this.tipoProp = c.getTipoProp();
			    this.cantBanios = c.getCantBanios();
			    this.cantCuartos = c.getCantCuartos();
			    this.garage = c.isGarage();
			    this.precio=c.getPrecio();
			    this.tamanio=c.getTamanio();
			    this.tipoNegocio=c.gettipoNegocio();
			    this.piscina=c.isPiscina();
			    this.estado=c.getEstado();
	
			    return "modificarCasa.xhtml";
			}	else{
				
				Apartamento c=	ipc.getAptoFromGeom(idPunto);
						   
				this.titulo=c.getTitulo();
				this.direccion = c.getDireccion();
			    this.barrio = c.getBarrio();
			    this.tipoProp = c.getTipoProp();
			    this.cantBanios = c.getCantBanios();
			    this.cantCuartos = c.getCantCuartos();
			    this.garage = c.isGarage();
			    this.precio=c.getPrecio();
			    this.tamanio=c.getTamanio();
			    this.tipoNegocio=c.getTipoNegocio();
			    this.estado=c.getEstado();
			    
			    return "modificarApto.xhtml";
				
			}
			
			
		    
		    
			}catch(Exception e){
				e.printStackTrace();
				
			}
			return null;
		}
	
	public String modificarCasa(){
		
		System.out.println("Entre modificaraCasa() este es el idPunto...: "+ idPunto);
		System.out.println("Entre modificaraCasa() este es el idCasa...: "+ idCasa);


		System.out.println("Entre modificaraCasa()...: "+idPunto+titulo+ direccion +barrio +tipoProp + tipoNegocio + cantBanios + cantCuartos + piscina + garage + precio + tamanio);

		try {
			
			String usuario =(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
			Integer idpunto =(Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idPunto");
			System.out.println("Entre modificaraCasa()...: "+idpunto+titulo+ direccion +barrio +tipoProp + tipoNegocio + cantBanios + cantCuartos + piscina + garage + precio + tamanio);

			
			ipc.modificarCasa(usuario,idpunto,titulo, direccion, barrio, tipoProp, tipoNegocio, cantBanios, cantCuartos, piscina, garage, precio, tamanio,estado);			
			
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
		
	}
	
	public String modificarApartamento(){
			
	
			try {
				
				String usuario =(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
				Integer idpunto =(Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idPunto");
				
				System.out.println("Entre modificarApartamento()...: "+idpunto+titulo+ direccion +barrio +tipoProp + tipoNegocio + cantBanios + cantCuartos + numeroap + garage + precio + tamanio);
				System.out.println("Entre modificarApartamento()...: "+idPunto+titulo+ direccion +barrio +tipoProp + tipoNegocio + cantBanios + cantCuartos + numeroap + garage + precio + tamanio);
	
				
				ipc.modificarApto(usuario,idpunto,titulo, direccion, barrio, tipoProp, tipoNegocio, cantBanios, cantCuartos,  garage, precio, tamanio,numeroap,estado);
				
				FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
				
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			return null;
			
	}
	public int getIdCasa() {
		return idCasa;
	}


	public void setIdCasa(int idCasa) {
		this.idCasa = idCasa;
	}
	
	
	public int getIdApto() {
		return idDep;
	}


	public void setIdApto(int idDep) {
		this.idDep = idDep;
	}


	public int getDireccion() {
		return direccion;
	}


	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}


	public String getBarrio() {
		return barrio;
	}


	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}


	public String getTipoProp() {
		return tipoProp;
	}


	public void setTipoProp(String tipoProp) {
		this.tipoProp = tipoProp;
	}
	
	
	public int getCantBanios() {
		return cantBanios;

	}


	public void setCantBanios(int cantBanios) {
		this.cantBanios = cantBanios;
	}


	public int getCantCuartos() {
		return cantCuartos;
	}


	public void setCantCuartos(int cantCuartos) {
		this.cantCuartos = cantCuartos;
	}


	public boolean isGarage() {
		return garage;
	}


	public void setGarage(boolean garage) {
		this.garage = garage;
	}


	public boolean isPiscina() {
		return piscina;
	}


	public void setPiscina(boolean piscina) {
		this.piscina = piscina;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public List<Integer> getCasas() {
		return casas;
	}
	public void setCasas(List<Integer> casas) {
		this.casas = casas;
	}

	public Integer getDistanciaInteres() {
		return distanciaInteres;
	}
	public void setDistanciaInteres(Integer distanciaInteres) {
		this.distanciaInteres = distanciaInteres;
	}
	public Integer getDistanciaBus() {
		return distanciaBus;
	}
	public void setDistanciaBus(Integer distanciaBus) {
		this.distanciaBus = distanciaBus;
	}
	public Integer getDistanciaMar() {
		return distanciaMar;
	}
	public void setDistanciaMar(Integer distanciaMar) {
		this.distanciaMar = distanciaMar;
	}
	public Integer getDistanciaParada() {
		return distanciaParada;
	}
	public void setDistanciaParada(Integer distanciaParada) {
		this.distanciaParada = distanciaParada;
	}
	public String getCalle1() {
		return calle1;
	}
	public void setCalle1(String calle1) {
		this.calle1 = calle1;
	}
	public String getCalle2() {
		return calle2;
	}
	public void setCalle2(String calle2) {
		this.calle2 = calle2;
	}

	public int getPrecio() {
		return precio;
	}
	
	public void setPrecio(int precio) {
		this.precio = precio;
	}


	public float getTamanio() {
		return tamanio;
	}
	
	public void setTamanio(float tamanio) {
		this.tamanio = tamanio;
	}



	public String getTipoNegocio() {
		return tipoNegocio;
	}



	public void setTipoNegocio(String tipoNegocio) {
		this.tipoNegocio = tipoNegocio;
	}



	public int getNumeroap() {
		return numeroap;
	}



	public void setNumeroap(int numeroap) {
		this.numeroap = numeroap;

	}



	public int getIdPunto() {
		return idPunto;
	}



	public void setIdPunto(int idPunto) {
		this.idPunto = idPunto;
	}



	public int getIdDep() {
		return idDep;
	}



	public void setIdDep(int idDep) {
		this.idDep = idDep;
	}



	public List<Integer> getAptos() {
		return aptos;
	}



	public void setAptos(List<Integer> aptos) {
		this.aptos = aptos;
	}



	public String getPropiedad() {
		return propiedad;
	}



	public void setPropiedad(String propiedad) {
		this.propiedad = propiedad;
	}




	public int getIdborro() {
		return idborro;
	}



	public void setIdborro(int idborro) {
		this.idborro = idborro;
	}

	public double getX() {
		return x;
	}



	public void setX(double x) {
		this.x = x;
	}



	public double getY() {
		return y;
	}



	public void setY(double y) {
		this.y = y;

	}



	public String getPropiedadClick() {
		return propiedadClick;
	}



	public void setPropiedadClick(String propiedadClick) {
		this.propiedadClick = propiedadClick;
	}



	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
	

	
	
}
