
package gt.edu.miumg.bd.ServiceDB;

import gt.edu.miumg.bd.Servicio;
import gt.edu.miumg.bd.ServicioJpaController;
import gt.edu.miumg.bd.exceptions.NonexistentEntityException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ServicioDataBase {
    
    EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("gt.edu.miumg_SafeTech2_jar_1.0-SNAPSHOTPU");
    ServicioJpaController serviceJPA = new ServicioJpaController(emf);
    
    
   public void crearServicio(Scanner es, Servicio s) {
    System.out.print("Ingrese una descripcion para el nuevo servicio: ");
    es.nextLine();
    s.setDescripcion(es.nextLine());
    
    boolean valido = false;
    BigDecimal precio = null;

    while (!valido) {
        System.out.print("Ingrese un precio: ");
        String precioStr = es.nextLine().trim();
        try {
            precio = new BigDecimal(precioStr);
            valido = true;
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número válido (ejemplo: 250.75).");
        }
    }

    s.setPrecio(precio);
    serviceJPA.create(s);
    System.out.println("Servicio creado con éxito.");
}
   
   public void mostrarServicios(){
       List<Servicio> listaServicios = serviceJPA.findServicioEntities();
       if (listaServicios.isEmpty()) {
           System.out.println("No hay servicios creados");
           
       }else {
           for (Servicio ser: listaServicios) {
               System.out.println("ID Servicio: "+ ser.getIdServicio());
               System.out.println("Descripcion: "+ ser.getDescripcion());
               System.out.println("Precio: "+ ser.getPrecio());
               System.out.println("**************************");
           }
       }
   }
   
   public void eliminarServicio(Scanner es) throws NonexistentEntityException{
       System.out.println("Servicios Disponibles: ");
       mostrarServicios();
       System.out.print("\nIngrese el ID del Servicio a Eliminar: ");
       es.nextLine();
       int id = Integer.parseInt(es.nextLine());
       try {
           serviceJPA.destroy(id);
           System.out.println("El Servicio "+ id+ " ha sido elimiado");
       } catch (Exception e) {
           System.out.println("Error al ingresar el ID");
       }
       
   }
   
   public void modificarServicio(Scanner es){
       es.nextLine();
       System.out.println("Ingrese el ID del Servicio a Modificar: ");
       int id = Integer.parseInt(es.nextLine());
       Servicio ser = serviceJPA.findServicio(id);
       if (ser == null) {
           System.out.println("El Servicio no existe en la base de datos");
       }else{
           mostrarUno(ser);
           System.out.println("\nIngrese los nuevos valores para el Servicio");
           System.out.print("Descripcion : ");
            String desc = es.nextLine();
            ser.setDescripcion(desc);
            //es.nextLine();
    
            boolean valido = false;
            BigDecimal precio = null;

    while (!valido) {
        System.out.print("Ingrese un precio: ");
        String precioStr = es.nextLine().trim();
        try {
            precio = new BigDecimal(precioStr);
            valido = true;
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número válido (ejemplo: 250.75).");
        }
    }

            ser.setPrecio(precio);
           try {
               serviceJPA.edit(ser);
               System.out.println("Servicio Modificado Exitosamente");
           } catch (Exception e) {
               System.out.println("A ocurrido un error");
           }
       }
       
   }
   
   public void mostrarUno(Servicio ser){
       System.out.println("ID Servicio: "+ ser.getIdServicio());
       System.out.println("Descripcion: "+ ser.getDescripcion());
       System.out.println("Precio: "+ ser.getPrecio());
       System.out.println("**************************");
       
   }

   
}
