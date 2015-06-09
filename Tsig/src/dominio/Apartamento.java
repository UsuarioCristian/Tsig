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


@Table(name = "apartamento")
@Entity
public class Apartamento implements Serializable{

	private static final long serialVersionUID = 1L; // Mapping JPA
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departamento_iddep_seq")
    @SequenceGenerator(name = "departamento_iddep_seq", sequenceName = "departamento_iddep_seq",allocationSize=1)
	@Column(name = "iddep", nullable = false)
	private int idDep;
		
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
	
	@Column(name = "tipoNegocio", nullable = false)
	private String tipoNegocio;
	
	@Column(name = "cantbanios", nullable = false)
	private int cantBanios;
	
	@Column(name = "cantcuartos", nullable = false)
	private int cantCuartos;
	
	@Column(name = "garage", nullable = false)
	private boolean garage;
		
	@Column(name = "estado", nullable = false)
	private String estado;
	
	@Column(name = "precio", nullable = false)
	private int precio;
	
	@Column(name = "tamanio", nullable = false)
	private float tamanio;
	
	@Column(name = "numeroap", nullable = false)
	private int numeroAp;
	
	@Column(name = "visitas", nullable = false)
	private int visitas;
	
	
    @ManyToOne  
    @JoinColumn(name = "user_id") 
	private Usuario encargado;
	
    
    
	public Apartamento(){
		
	}
	
	public Apartamento(int idGeom,int idDep,String titulo, int direccion,String barrio,String tipoProp, String tipoNegocio, int cantbanios, int cantCuartos,boolean garage,String estado, int precio, float tamanio,int numeroAp){
			
			this.idGeom=idGeom;
			this.idDep = idDep;
			this.titulo=titulo;
			this.direccion = direccion;
			this.barrio = barrio;
			this.tipoProp = tipoProp;
			this.tipoNegocio = tipoNegocio;
			this.cantBanios = cantbanios;
			this.cantCuartos = cantCuartos;
			this.garage = garage;
			this.estado=estado;
			this.precio=precio;
			this.tamanio=tamanio;
			this.numeroAp=numeroAp;	
	}
	public Apartamento(int idGeom,String titulo, int direccion,String barrio,String tipoProp, String tipoNegocio, int cantbanios, int cantCuartos, boolean garage,String estado, int precio, float tamanio, int numeroAp){
		
		this.idGeom=idGeom;
		this.titulo=titulo;
		this.direccion = direccion;
		this.barrio = barrio;
		this.tipoProp = tipoProp;
		this.tipoNegocio = tipoNegocio;
		this.cantBanios = cantbanios;
		this.cantCuartos = cantCuartos;
		this.garage = garage;
		this.estado=estado;
		this.precio=precio;
		this.tamanio=tamanio;
		this.numeroAp=numeroAp;
	
}
	
	public Apartamento(Apartamento ap){
		
			this.idGeom=ap.getIdGeom();
			this.titulo=ap.getTitulo();
			this.idDep = ap.getIdApartamento();
		    this.direccion = ap.getDireccion();
		    this.barrio = ap.getBarrio();
		    this.tipoProp = ap.getTipoProp();
		    this.tipoNegocio = ap.getTipoNegocio();
		    this.cantBanios = ap.getCantBanios();
		    this.cantCuartos = ap.getCantCuartos();
		    this.garage = ap.isGarage();
		    this.estado= ap.getEstado();
		    this.precio=ap.getPrecio();
		    this.tamanio=ap.getTamanio();
		    this.numeroAp=ap.getNumeroAp();
		  
	}

	public int getIdGeom() {
		return idGeom;
	}

	public void setIdGeom(int idGeom) {
		this.idGeom = idGeom;
	}

	public int getIdApartamento() {
		return idDep;
	}

	public void setIdApartamento(int idDep) {
		this.idDep = idDep;
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
	
	public int getPrecio(){
		return precio;
	}
	
	public void setPrecio(int precio){
		this.precio = precio;	
	}
	
	public float getTamanio(){
		return tamanio;
	}
	
	public void setTamanio(float tamanio){
		this.tamanio = tamanio;
	}

	public String getTipoNegocio() {
		return tipoNegocio;
	}

	public void setTipoNegocio(String tipoNegocio) {
		this.tipoNegocio = tipoNegocio;
	}

	public int getNumeroAp() {
		return numeroAp;
	}

	public void setNumeroAp(int numeroAp) {
		this.numeroAp = numeroAp;
	}

	public int getVisitas() {
		return visitas;
	}

	public void setVisitas(int visitas) {
		this.visitas = visitas;
	}
	
	
			
}

