package presentacion;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;



import controladores.IPropiedadController;


@ManagedBean
@SessionScoped
public class ZonaMB implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer idzona=0;

	@EJB
	IPropiedadController ipc;

	
	public void borrarZona(){
	System.out.println("LLEGUE CARAHE "+idzona);
		ipc.borrarZona(idzona);
	}




	public Integer getIdzona() {
		return idzona;
	}

	public void setIdzona(Integer idzona) {
		this.idzona = idzona;
	}

	
	
	
}
