package persistencia;

import java.util.List;

import javax.ejb.Stateless;

import dominio.Mensaje;

@Stateless
public class MensajeDAO implements IMensajeDAO {
	
	@javax.persistence.PersistenceContext(unitName = "TsigWeb")	
	private javax.persistence.EntityManager em;

	@Override
	public boolean altaMensaje(Mensaje mensaje) {
		try {
			em.persist(mensaje);			
			return true;
		} catch (Exception e) {
			System.out.println("Error en alta mensaje (MensajeDAO)");
			return false;
		}
		
	}

	@Override
	public boolean borrarMensaje(Mensaje mensaje) {
		try {
			em.remove(mensaje);
			return true;
		} catch (Exception e) {
			System.out.println("Error en borrar mensaje (MensajeDAO)");
			return false;
		}
	}

	@Override
	public List<Mensaje> getMensajes(String nombreUsuario) {
		try {
			return em.createNamedQuery("Mensaje.getMensajes", Mensaje.class)
					.setParameter("nombre", nombreUsuario)
					.getResultList();
		} catch (Exception e) {
			System.out.println("Error en getMensajes (MensajeDAO)");
			return null;
		}
		
	}

	@Override
	public Mensaje findById(int idMensaje) {
		try {
			return em.createNamedQuery("Mensaje.findById", Mensaje.class)
					.setParameter("idMensaje", idMensaje)
					.getSingleResult();
		} catch (Exception e) {
			System.out.println("Error en findById (MensajeDAO)");
			return null;
		}
	}

	@Override
	public boolean actualizarMensaje(Mensaje mensaje) {
		try {			
			em.merge(mensaje);			
			return true;
		} catch (Exception e) {
			System.out.println("Error al actualizar mensaje en (MensajeDAO)");
			return false;
		}
	}

}
