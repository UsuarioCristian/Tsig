package persistencia;


import dominio.Apartamento;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.ejb.Stateless;

import dominio.Casa;





@Stateless
public class PropiedadDAO implements IPropiedadDAO {
	
	@javax.persistence.PersistenceContext(unitName = "TsigWeb")
	
	private javax.persistence.EntityManager em;
	
	
	
	public boolean guardarPropiedad(Casa casa) {
		
		try {
		
			em.persist(casa);
			System.out.print("guarda casa"+casa.getIdCasa()+casa.getEncargado());
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
	
	public List<Integer> getFilteredCasa(String titulo, String barrio,String tipoProp, int cantbanios, int cantCuartos, boolean piscina,boolean garage, float tamanio, int precio, String tipoNegocio){
		
		String comb; 
		
		comb="(c.estado= 'publica' or c.estado='reservada')";
		if("".compareTo(titulo)!=0)
		{
			comb= comb+" and c.titulo like '"+titulo+"'";
		}
		if("Cualquiera".compareTo(tipoNegocio)!=0)
		{
			comb= comb+" and c.tipoNegocio like '"+tipoNegocio+"'";
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
		if(tamanio>0){
			comb= comb + " and c.tamanio >="+tamanio;
		}	
		if(precio>0){
			comb= comb + " and c.precio <="+precio;
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
			
		List<Integer>ids=em.createQuery("Select c.idGeom from Casa c where "+comb).getResultList();
		
		return ids;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
		
		
	}
	
	@Override
	public List<Integer> getFilteredApto(String titulo, String barrio,	String tipoProp, int cantBanios, int cantCuartos,	boolean garage, float tamanio, int precio, String tipoNegocio,	int numeroap) 
	{
String comb; 
		
		comb="(c.estado= 'publica' or c.estado='reservada')";
		if("".compareTo(titulo)!=0)
		{
			comb= comb+" and c.titulo like '"+titulo+"'";
		}
		
		if("Cualquiera".compareTo(tipoNegocio)!=0)
		{
			comb= comb+" and c.tipoNegocio like '"+tipoNegocio+"'";
		}
		if("".compareTo(barrio)!=0)
		{
			comb=comb+" and c.barrio= '"+barrio+"'";
		}		
		if("Cualquiera".compareTo(tipoProp)!=0)
		{
			comb=comb+" and c.tipoProp= '"+tipoProp+"'";
		}
		if(cantBanios>0){
			comb= comb + " and c.cantBanios ="+cantBanios;
		}	
		if(tamanio>0){
			comb= comb + " and c.tamanio >="+tamanio;
		}	
		if(precio>0){
			comb= comb + " and c.precio <="+precio;
		}	
		if(cantCuartos>0){
			comb = comb+" and c.cantCuartos ="+cantCuartos;
		}		
		if(garage){
			comb= comb+" and c.garage= TRUE";
		}
		if(numeroap>0){
			comb= comb + " and c.numeroap >="+(numeroap*100);
		}	
		
		
		try{
			System.out.println("La query: "+comb);
			
		List<Integer>ids=em.createQuery("Select c.idGeom from Apartamento c where "+comb).getResultList();
		
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
		List<Integer> result = em.createNativeQuery("select distinct c.idGeom from casa c, casageom g, serv_comerciales s where c.idgeom=g.id and ST_Intersects(ST_Buffer(g.punto,"+(distance+50)+"),ST_Transform(s.geom,32721) )").getResultList();
		
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
		List<Integer> result = em.createNativeQuery("select distinct c.idGeom from casa c, casageom g, paradas p where c.idgeom=g.id and ST_Intersects(ST_Buffer(g.punto,"+distance+"),ST_Transform(p.geom,32721) )").getResultList();
		System.out.println("LA DISTANCIA BUS" + distance);
		return result;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public List<Integer> getDistanceRambla(Integer distance){
		try{
		List<Integer> result = em.createNativeQuery("select distinct c.idGeom from casa c, casageom g, borderambla b where c.idgeom=g.id and ST_Intersects(ST_Buffer(g.punto,"+(distance+50)+"),ST_Transform(b.the_geom,32721) )").getResultList();
		
		return result;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	
	///////Aptos
	@Override
	public List<Integer> getDistancePuntoInteresApto(Integer distance) {
		
		try{
		List<Integer> result = em.createNativeQuery("select distinct c.idGeom from apartamento c, aptogeom g, serv_comerciales s where c.idgeom=g.id and ST_Intersects(ST_Buffer(g.punto,"+(distance+50)+"),ST_Transform(s.geom,32721) )").getResultList();
		
		return result;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
	@Override
	public List<Integer> getDistanceParadasApto(Integer distance){
		try{
		List<Integer> result = em.createNativeQuery("select distinct c.idGeom from apartamento c, aptogeom g, paradas p where c.idgeom=g.id and ST_Intersects(ST_Buffer(g.punto,"+distance+"),ST_Transform(p.geom,32721) )").getResultList();
		System.out.println("LA DISTANCIA BUS" + distance);
		return result;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public List<Integer> getDistanceRamblaApto(Integer distance){
		try{
		List<Integer> result = em.createNativeQuery("select distinct c.idGeom from apartamento c, aptogeom g, borderambla b where c.idgeom=g.id and ST_Intersects(ST_Buffer(g.punto,"+(distance+50)+"),ST_Transform(b.the_geom,32721) )").getResultList();
		
		return result;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Integer> getBusaDestino(Integer distance,String calle1,String calle2){
		try{
		
		String query=	"select distinct c.idgeom from casa c, casageom g, paradas p where c.idgeom=g.id and ST_Intersects(ST_Buffer(g.punto,"+distance+"),ST_Transform(p.geom,32721) ) and p.desc_linea in" +
			
			" (select distinct linea from (select ST_Intersection(buff.geom,bus.geom) as result ,bus.desc_linea as linea from (select distinct ST_Buffer(e,200) as geom"+
					" from (select (ST_Intersection(ca.geom,ca2.geom)) as e "+
						" from (select *"+ 
							" from calles c where c.nom_calle='"+calle1+"') ca,"+ 
							" (select * from calles c where c.nom_calle='"+calle2+"') ca2  )inter  "+
							" where  not ST_IsEmpty(inter.e)) buff	, lineasbus bus )  intersections where not ST_IsEmpty(intersections.result))";

			
			
			System.out.println(query);
			
		List<Integer> result = em.createNativeQuery(query).getResultList();
		
		for(Integer i:result){
			
			System.out.println("GET DESTINO DE BUS"+i);
		}
		return result;
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	///////
	
	public Casa getCasaFromGeom(int idCasa) {
		try{
			System.out.println("getinfocasa"+ idCasa);

			Casa casa = (Casa)em.createQuery("Select c FROM Casa c WHERE c.idGeom = '"+idCasa+"'").getSingleResult();
			System.out.println("getinfocasa"+ casa  );
			return casa;
			
		}catch(Exception e){
			
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
public Apartamento AptoFromGeom(int idPunto) {
	try{
		Apartamento ap = (Apartamento)em.createQuery("Select a FROM Apartamento a WHERE a.idGeom = '"+idPunto+"'").getSingleResult();
		return ap;
		
	}catch(Exception e){
		
		e.printStackTrace();
	}
	return null;
}

public List<Integer> getCasasUsuario(String usuario) {
	try{
		List<Integer> result = em.createNativeQuery("select distinct c.idGeom from casa c, casageom g where c.idgeom=g.id and c.user_id='"+usuario+"'").getResultList();
		return result;
		
	}catch(Exception e){
		
		e.printStackTrace();
	}
	return null;
}

@Override
public double[] getCoor(int idPunto) {

	 Double x =(Double) em.createNativeQuery("SELECT ST_X(punto) FROM casageom g,casa c WHERE c.idgeom=g.id and g.id="+idPunto).getSingleResult();
	 Double y =(Double) em.createNativeQuery("SELECT ST_Y(punto) FROM casageom g,casa c WHERE c.idgeom=g.id and g.id="+idPunto).getSingleResult(); 
	 
	 System.out.println("DAO COORDENADAAS: x:"+x+" y: "+y);
	
	 
	 double[] result={x,y};
	 
	 return result;
}

@Override
public double[] getCoorApto(int idPunto) {

	 Double x =(Double) em.createNativeQuery("SELECT ST_X(punto) FROM aptogeom g,apartamento c WHERE c.idgeom=g.id and g.id="+idPunto).getSingleResult();
	 Double y =(Double) em.createNativeQuery("SELECT ST_Y(punto) FROM aptogeom g,apartamento c WHERE c.idgeom=g.id and g.id="+idPunto).getSingleResult(); 
	 
	 System.out.println("DAO COORDENADAAS: x:"+x+" y: "+y);
	
	 
	 double[] result={x,y};
	 
	 return result;
}


public void eliminarFeature(int idPunto) {
	
	try{
		em.createNativeQuery("delete  from casa c where c.idgeom='"+idPunto+"'").executeUpdate();
		em.createNativeQuery("delete  from casageom g where g.id='"+idPunto+"'").executeUpdate();

		
	}catch(Exception e){
		
		e.printStackTrace();
	}
	
	
}


@Override
public List<Integer> getAptoUsuario(String usuario) {
	try{
		List<Integer> result = em.createNativeQuery("select distinct a.idGeom from apartamento a, aptogeom g where a.idgeom=g.id and a.user_id='"+usuario+"'").getResultList();
		return result;
		
	}catch(Exception e){
		
		e.printStackTrace();
	}
	return null;
}
public void eliminarFeatureApto(int idPunto) {
	
	try{
		em.createNativeQuery("delete  from apartamento c where c.idgeom='"+idPunto+"'").executeUpdate();
		em.createNativeQuery("delete  from aptogeom g where g.id='"+idPunto+"'").executeUpdate();

		
	}catch(Exception e){
		
		e.printStackTrace();
	}
	
	
}

@Override
public void actualizarZonas() {

	try{
		
		List<Integer> zonas=em.createNativeQuery("select zg.id from zonageom zg").getResultList();
		
		for(Integer i:zonas){
			
		BigInteger val =(BigInteger) em.createNativeQuery("select count(*)  from ZonaGeom zg ,aptogeom c  where ST_intersects(c.punto,zg.geom) and zg.id="+i).getSingleResult();	
		System.out.println("ACTUALIZAR ZONAS Id: "+i+"Val: "+val);
		em.createNativeQuery("update zonageom set visitas ="+val+" where zonageom.id ="+i).executeUpdate();
		}
		
	
		
	}catch(Exception e ){
		
		
		e.printStackTrace();
	}

}

	

}
