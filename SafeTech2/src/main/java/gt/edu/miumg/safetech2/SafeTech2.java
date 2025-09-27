/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package gt.edu.miumg.safetech2;

import gt.edu.miumg.bd.ServiceDB.ServiceLog;
import gt.edu.miumg.bd.ServiceDB.ServicioDataBase;
import gt.edu.miumg.bd.Servicio;
import gt.edu.miumg.bd.Usuario;
import gt.edu.miumg.bd.exceptions.NonexistentEntityException;
import java.util.Scanner;


public class SafeTech2 {
    
    private static Scanner es = new Scanner(System.in);
    static ServiceLog login = new ServiceLog();
    static ServicioDataBase svDB = new ServicioDataBase();

    public static void main(String[] args) throws NonexistentEntityException {
        safeTechApp();
    }
    
    public static void safeTechApp() throws NonexistentEntityException{
        
        var ingreso = login();
        if (ingreso) {
            int op = menu();
            subMenu(op);
            
            
        }
        
        
    }
    
    public static boolean login(){
         System.out.println("\n**********Bienvenido a SAFETECH!! Su Empresa de Seguridad**********");
        System.out.println("\n\n");
        System.out.println("Para acceder ingrese sus credenciales:");
        System.out.print("Usuario: ");
        int user = Integer.parseInt(es.nextLine());
        System.out.print("Password: ");
        String pass = es.next();
        
        Usuario logueo = login.login(user, pass);
        
        if (logueo == null) {
            System.out.println("Credenciales Invalidas");
            return false;
        }else{
            System.out.println("\nUsuario Correcto");
        }
        return true;
    }
    
    public static int menu(){
        es.nextLine();
        System.out.println("\n***** MENU *****");
        System.out.println("1. Mantenimiento de Servicios");
        System.out.print("Seleccione una opcion: ");
        int op = es.nextInt();
        return op;
    }
    public static void subMenu(int op) throws NonexistentEntityException{
        
        switch(op){
            case 1:
                subMenuServicio();
                break;
        }
    }
    public static void subMenuServicio() throws NonexistentEntityException{
        es.nextLine();
        System.out.println("1. Crear Servicio");
        System.out.println("2. Eliminar Servicio");
        System.out.println("3. Modificar Servicio");
        System.out.println("4. Ver Servicios");
        System.out.print("Seleccione una Opcion: ");
        int op = es.nextInt();
        switch(op){
            case 1:
                System.out.println("\n***Crear Servicio***");
                Servicio ser = new Servicio();
                svDB.crearServicio(es, ser);
                break;
            case 2:
                System.out.println("\n***Eliminar Servicio***");
                svDB.eliminarServicio(es);
                break;
            case 3:
                System.out.println("\n***Modificar Servicio***");
                svDB.modificarServicio(es);
                break;
            case 4:
                System.out.println("\n***Ver Servicios***");
                svDB.mostrarServicios();
                break;
        }
    }
    
    
    
}
