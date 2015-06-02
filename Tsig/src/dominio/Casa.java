package dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Table(name = "casa")
@Entity
public class Casa implements Serializable{

	private static final long serialVersionUID = 1L; // Mapping JPA
	
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "casa_idcasa_seq")
    @SequenceGenerator(name = "casa_idcasa_seq", sequenceName = "casa_idcasa_seq",allocationSize=1)
	@Column(name = "idcasa", nullable = false)
	private int idCasa;
		
	@Column(name = "titulo", nullable = false)
	private String titulo;
	
	@Column(name = "direccion", nullable = false)
	private int direccion;
	
	@Column(name = "idGeom", nullable = false)
	private int idGeom;
	
	@Column(name = "barrio", nullable = false)
	private String barrio;
	
	@Column(name = "tipoprop", nullable = false)
	private String tipoProp;
	
	@Column(name = "cantbanios", nullable = false)
	private int cantBanios;
	
	@Column(name = "cantcuartos", nullable = false)
	private int cantCuartos;
	
	@Column(name = "garage", nullable = false)
	private boolean garage;
	
	@Column(name = "piscina", nullable = false)
	private boolean piscina;
	
	@Column(name = "estado", nullable = false)
	private String estado;
	
    @ManyToOne  
    @JoinColumn(name = "user_id") 
	private Usuario encargado;
	
	public Casa(){
		
	}
	
	public Casa(int idGeom,int idCasa,String titulo, int direccion,String barrio,String tipoProp, int cantbanios, int cantCuartos,boolean piscina, boolean garage,String estado){
			
			this.idGeom=idGeom;
			this.idCasa = idCasa;
			this.titulo=titulo;
			this.direccion = direccion;
			this.barrio = barrio;
			this.tipoProp = tipoProp;
			this.cantBanios = cantbanios;
			this.cantCuartos = cantCuartos;
			this.garage = garage;
			this.piscina = piscina;
			this.estado=estado;
				
	}
	public Casa(int idGeom,String titulo, int direccion,String barrio,String tipoProp, int cantbanios, int cantCuartos,boolean piscina, boolean garage,String estado){
		
		this.idGeom=idGeom;
		this.titulo=titulo;
		this.direccion = direccion;
		this.barrio = barrio;
		this.tipoProp = tipoProp;
		this.cantBanios = cantbanios;
		this.cantCuartos = cantCuartos;
		this.garage = garage;
		this.piscina = piscina;
		this.estado=estado;
			
}
	
	public Casa(Casa p){
		
			this.idGeom=p.getIdGeom();
			this.titulo=p.getTitulo();
			this.idCasa = p.getIdCasa();
		    this.direccion = p.getDireccion();
		    this.barrio = p.getBarrio();
		    this.tipoProp = p.getTipoProp();
		    this.cantBanios = p.getCantBanios();
		    this.cantCuartos = p.getCantCuartos();
		    this.garage = p.isGarage();
		    this.piscina = p.isPiscina();
		    this.estado= p.getEstado();
		
	}

	public int getIdGeom() {
		return idGeom;
	}

	public void setIdGeom(int idGeom) {
		this.idGeom = idGeom;
	}

	public int getIdCasa() {
		return idCasa;
	}

	public void setIdCasa(int idCasa) {
		this.idCasa = idCasa;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Usuario getEncargado() {
		return encargado;
	}

	public void setEncargado(Usuario encargado) {
		this.encargado = encargado;
	}
	
	
	
			
}
