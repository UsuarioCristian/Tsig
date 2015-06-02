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
import dominio.Casa;



@ManagedBean
@SessionScoped
public class PropiedadMB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private int idCasa=0;	
	private int direccion=0;	
	private String barrio="";	
	private String tipoProp="";	
	private int cantBanios=0;		
	private int cantCuartos=0;	
	private boolean garage=false;	
	private boolean piscina=false;
	private String titulo="";
	private List<Integer> casas= new ArrayList();
	@EJB
	IPropiedadController ipc;

	public String guardarPropiedad(){
		

		try {
			
			String usuario =(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
			
			
			
			ipc.guardarCasa(usuario,idCasa,titulo, direccion, barrio, tipoProp, cantBanios, cantCuartos, piscina, garage);
			
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
		
	}
	public void getInfo(){
		
		try{
			
			Casa c= ipc.getCasa(idCasa);
			
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
	public void consultaPropiedad(){
		 
		try{
	    casas = ipc.getFilteredCasa(titulo,barrio,tipoProp,cantBanios,cantCuartos,piscina,garage);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public int getIdCasa() {
		return idCasa;
	}


	public void setIdCasa(int idCasa) {
		this.idCasa = idCasa;
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
	
	
	
	

	
	
}
