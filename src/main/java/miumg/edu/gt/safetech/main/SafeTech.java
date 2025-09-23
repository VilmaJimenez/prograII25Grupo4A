
package miumg.edu.gt.safetech.main;

import java.util.Scanner;
import miumg.edu.gt.safetech.controller.Usuario;
import miumg.edu.gt.safetech.dbo.Servicio.ServicioLogin;

public class SafeTech {
    
    private static Scanner es = new Scanner(System.in);
    static ServicioLogin login = new ServicioLogin();
    
    public static void main(String[] args) {
       safeTechApp();
    }
    
    
    public static void safeTechApp(){
        Usuario logueo = null;
        
        System.out.println("\n**********Bienvenido a SAFETECH!! Su Empresa de Seguridad**********");
        System.out.println("\n\n");
        System.out.println("Para acceder ingrese sus credenciales:");
        System.out.print("Usuario: ");
        int user = Integer.parseInt(es.nextLine());
        System.out.print("Password: ");
        String pass = es.next();
        
        logueo = login.login(user, pass);
        
        if (logueo == null) {
            System.out.println("Credenciales Invalidas");
        }
        System.out.println("\nUsuario Correcto");
        
    }
    
}
