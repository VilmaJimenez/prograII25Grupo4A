
package miumg.edu.gt.safetech.main;

import java.util.Scanner;

public class SafeTech {
    
    private static Scanner es = new Scanner(System.in);
    
    public static void main(String[] args) {
       safeTechApp();
    }
    
    
    public static void safeTechApp(){
        
        System.out.println("\n**********Bienvenido a SAFETECH!! Su Empresa de Seguridad**********");
        System.out.println("\n\n");
        System.out.println("Para acceder ingrese sus credenciales:");
        System.out.print("Usuario: ");
        String user = es.next();
        System.out.print("Password: ");
        
    }
    
}
