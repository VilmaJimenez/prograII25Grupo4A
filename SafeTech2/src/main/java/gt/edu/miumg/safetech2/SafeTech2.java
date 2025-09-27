/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package gt.edu.miumg.safetech2;

import gt.edu.miumg.bd.ServiceDB.ServiceLog;
import gt.edu.miumg.bd.Usuario;
import java.util.Scanner;


public class SafeTech2 {
    
    private static Scanner es = new Scanner(System.in);
    static ServiceLog login = new ServiceLog();

    public static void main(String[] args) {
        safeTechApp();
    }
    
    public static void safeTechApp(){
        
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
        System.out.println("\n***** MENU *****");
        System.out.println("1. Mantenimiento de Servicios");
        System.out.print("Seleccione una opcion: ");
        int op = es.nextInt();
        return op;
    }
    public static void subMenu(int op){
        
        switch(op){
            case 1:
                subMenuServicio();
                break;
        }
    }
    public static void subMenuServicio(){
        System.out.println("1. Crear Servicio");
        System.out.print("Seleccione una Opcion: ");
        int op = es.nextInt();
        switch(op){
            case 1:
                System.out.println("\n***Crear Servicio***");
        }
    }
    
    
    
}
