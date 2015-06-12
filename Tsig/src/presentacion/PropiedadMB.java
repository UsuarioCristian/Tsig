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
	
	public String modificarApartamento(){
		

		try {
			
			String usuario =(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
								
			ipc.modificarApto(usuario,idDep,titulo, direccion, barrio, tipoProp, tipoNegocio, cantBanios, cantCuartos,  garage, precio, tamanio,numeroap);
			
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
		
	}
	
	public String modificarCasa(){
		

		try {
			
			String usuario =(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
								
			ipc.modificarCasa(usuario,idCasa,titulo, direccion, barrio, tipoProp, tipoNegocio, cantBanios, cantCuartos, piscina, garage, precio, tamanio);			
			
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
				    this.numeroap= a.getIdApartamento();
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
			String usuario =(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
			System.out.println("Entre getUser....."+ usuario);
			casas=ipc.getCasasUsuario(usuario);
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
	 
	    
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void eliminarFeature(String ddd){
		try{
			
			String data = (String)FacesContext.getCurrentInstance().getAttributes().get("id");
			System.out.println("id" +data);
		//	System.out.println("eliminarFeature.."+ idPunto);
		//	ipc.eliminarFeature(idPunto);
			
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			
		}
		catch(Exception e){
		e.printStackTrace();
		}
	
		
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
	
	
	
	

	
	
}
