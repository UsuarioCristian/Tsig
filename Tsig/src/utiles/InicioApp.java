//package utiles;
//
//import java.util.List;
//
//import javax.ejb.EJB;
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//
//import controladores.IMensajeController;
//import controladores.IUsuarioController;
//import dominio.Mensaje;
//
//@WebListener
//public class InicioApp implements ServletContextListener {
//	
//	@EJB
//	IUsuarioController usuarioCtrl;
//	
//	@EJB
//	IMensajeController mensajeCtrl;
//	
//	@Override
//	public void contextDestroyed(ServletContextEvent arg0) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void contextInitialized(ServletContextEvent arg0) {
//	/*	System.out.println("");
//		System.out.println("Iniciando aplicacion--------Ver: utiles.InicioApp");
//		System.out.println("*****************************");
//		System.out.println("******* Test mensajes *******");
//		System.out.println("*****************************");
//		System.out.println("Creando usuario userTest (si no existe)...");
//		if(!usuarioCtrl.existeUsuario("userTest"))
//			usuarioCtrl.guardarUsuario("userTest", "userTest", "userTest@mail.com");
//		
//		System.out.println("Creando mensaje para el usuario userTest..");
//		mensajeCtrl.altaMensaje("Prueba", "Este es el contenido", null, "userTest");
//		
//		System.out.println("Obtener todos los mensajes del usuario userTest: ");
//		List<Mensaje> mensajes = mensajeCtrl.getMensajes("userTest");
//		for (Mensaje mensaje : mensajes) {
//			System.out.println(mensaje.toString());
//		}
//		
//		System.out.println("Borrado de mensajes.......");
//		
//		for (Mensaje mensaje : mensajes) {
//			mensajeCtrl.borrarMensaje(mensaje.getId());
//		}
//		
//		System.out.println("Crear nuevo mensaje y modificar");
//		mensajeCtrl.altaMensaje("ProbandoActualizar", "Probando actualizar mensaje (marcar como leido)", null, "userTest");
//		mensajes = mensajeCtrl.getMensajes("userTest");
//		for (Mensaje mensaje : mensajes) {
//			mensajeCtrl.marcarComoLeido(mensaje.getId());
//		}
//		
//		
//*/
//	}
//
//}
