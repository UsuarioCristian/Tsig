package persistencia;


import java.util.List;

import javax.ejb.Stateless;

import dominio.Casa;
import dominio.Usuario;




@Stateless
public class PropiedadDAO implements IPropiedadDAO {
	
	@javax.persistence.PersistenceContext(unitName = "TsigWeb")
	
	private javax.persistence.EntityManager em;
	
	public boolean guardarPropiedad(Casa casa) {
		
		try {
			
			em.persist(casa);
			return true;

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}

		

	}

	@Override
	public Casa getCasa(Integer id) {
		try{
			
			Casa c= em.find(Casa.class, id);
			return c;
			
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		return null;
	}
	
	public List<Integer> getFilteredCasa(String titulo,String barrio,String tipoProp, int cantbanios, int cantCuartos,boolean piscina, boolean garage){
		
		String 	tituloSTR,barrioSTR,tipoPropSTR,cantbaniosSTR,cantCuartosSTR,garageSTR,piscinaSTR,comb; 
		
		if("".compareTo(titulo)!=0)
		{
			tituloSTR="c.titulo="+titulo;
		}else{
			tituloSTR="";
		}		
		if("".compareTo(barrio)!=0)
		{
			barrioSTR="c.barrio="+barrio;
		}else{
			barrioSTR="";
		}		
		if("".compareTo(tipoProp)!=0)
		{
			tipoPropSTR="c.tipoprop="+tipoProp;
		}else{
			tipoPropSTR="";
		}
		if(cantbanios>-1){
			cantbaniosSTR="c.cantbanios >="+cantbanios;
		}
		else{
			cantbaniosSTR="";
		}		
		if(cantbanios>-1){
			cantCuartosSTR="c.cantcuartos >="+cantCuartos;
		}
		else{
			cantCuartosSTR="";
		}
		if(garage){
			garageSTR="c.garage= TRUE";
		}else{
			garageSTR= "";
		}
		if(piscina){
			piscinaSTR="c.piscina= TRUE";
		}else{
			piscinaSTR= "";
		}
		comb=tituloSTR+" and "+barrioSTR+" and "+tipoPropSTR+" and "+cantbaniosSTR+" and "+cantCuartosSTR+" and "+garageSTR+" and "+piscinaSTR;
		
		try{
		List<Integer>ids=em.createQuery("Select c.id from casa c where "+comb).getResultList();
		return ids;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
		
		
	}

	

}
