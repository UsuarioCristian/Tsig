package persistencia;


import dominio.Apartamento;

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
		
		String comb; 
		
		comb="(c.estado= 'publica' or c.estado='reservada')";
		if("".compareTo(titulo)!=0)
		{
			comb= comb+" and c.titulo like '"+titulo+"'";
		}		
		if("".compareTo(barrio)!=0)
		{
			comb=comb+" and c.barrio= '"+barrio+"'";
		}		
		if("Cualquiera".compareTo(tipoProp)!=0)
		{
			comb=comb+" and c.tipoProp= '"+tipoProp+"'";
		}
		if(cantbanios>0){
			comb= comb + " and c.cantBanios ="+cantbanios;
		}				
		if(cantCuartos>0){
			comb = comb+" and c.cantCuartos ="+cantCuartos;
		}		
		if(garage){
			comb= comb+" and c.garage= TRUE";
		}
		if(piscina){
			comb = comb+" and c.piscina= TRUE";
		}
		
		
		try{
			System.out.println("La query: "+comb);
			
		List<Integer>ids=em.createQuery("Select c.id from Casa c where "+comb).getResultList();
		
		return ids;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
		
		
	}

	@Override
	public List<Casa> consultaPropiedad(int cantCuartos) {
		try {
			
			List<Casa> casas = em.createQuery("Select c FROM Casa c WHERE c.cantCuartos = '"+cantCuartos+"'").getResultList();

			return casas;

		} catch (Exception e) {

			e.printStackTrace();
			return null;
			
		}
	}

	@Override
	public List<Integer> getDistancePuntoInteres(Integer distance) {
		
		try{
		List<Integer> result = em.createNativeQuery("select distinct c.idcasa from casa c, casageom g, serv_comerciales s where c.idgeom=g.idpunto and ST_Intersects(ST_Buffer(g.punto,"+(distance+50)+"),ST_Transform(s.geom,32721) )").getResultList();
		System.out.println("LA DISTANCIA Intrest" + distance);
		return result;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
	@Override
	public List<Integer> getDistanceParadas(Integer distance){
		try{
		List<Integer> result = em.createNativeQuery("select distinct c.idcasa from casa c, casageom g, paradas p where c.idgeom=g.idpunto and ST_Intersects(ST_Buffer(g.punto,"+(distance+50)+"),ST_Transform(p.geom,32721) )").getResultList();
		System.out.println("LA DISTANCIA BUS" + distance);
		return result;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	
public boolean guardarApartamento(Apartamento apart) {
		
		try {
			
			em.persist(apart);
			return true;

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}

		

	}


public void modificarCasa(Casa c) {
	
	try {
		em.merge(c);			

	} catch (Exception e) {

		e.printStackTrace();

	}
	
}	


public void modificarApto(Apartamento apart) {
	
	try {
		em.merge(apart);			

	} catch (Exception e) {

		e.printStackTrace();

	}
	
}

@Override
public Casa getCasaFromGeom(int idPunto) {

	try{
		Casa casa = (Casa)em.createQuery("Select c FROM Casa c WHERE c.idGeom = '"+idPunto+"'").getSingleResult();
		return casa;
		
	}catch(Exception e){
		
		e.printStackTrace();
	}
	return null;
}

@Override
public Apartamento AptoFromGeom(int idPunto) {
	try{
		Apartamento ap = (Apartamento)em.createQuery("Select a FROM Apartamento a WHERE a.idGeom = '"+idPunto+"'").getSingleResult();
		return ap;
		
	}catch(Exception e){
		
		e.printStackTrace();
	}
	return null;
}

	

}
