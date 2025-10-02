/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package gt.edu.miumg.safetech2;

import gt.edu.miumg.bd.Agente;
import gt.edu.miumg.bd.AgentesDB.AgentesDataBase;
import gt.edu.miumg.bd.AgentesDB.RolDataBase;
import gt.edu.miumg.bd.AgentesDB.UsuarioDataBase;
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
    static AgentesDataBase agDB = new AgentesDataBase();
    static UsuarioDataBase usDB = new UsuarioDataBase();
    static RolDataBase rolDB = new RolDataBase();

    public static void main(String[] args) throws NonexistentEntityException {
        safeTechApp();
    }

    public static void safeTechApp() throws NonexistentEntityException {
        var ingreso = login();
        if (ingreso) {
            int op = menu();
            subMenu(op);
        }
    }

    public static boolean login() {
        System.out.println("\n**********Bienvenido a SAFETECH!! Su Empresa de Seguridad**********");
        System.out.println("\n\n");
        System.out.println("Para acceder ingrese sus credenciales:");
        System.out.print("Usuario: ");
        int user = Integer.parseInt(es.nextLine());
        System.out.print("Password: ");
        String pass = es.nextLine();

        Usuario logueo = login.login(user, pass);

        if (logueo == null) {
            System.out.println("Credenciales Invalidas");
            return false;
        } else {
            System.out.println("\nUsuario Correcto");
            return true;
        }
    }

    public static int menu() {
        System.out.println("\n***** MENU *****");
        System.out.println("1. Mantenimiento de Servicios");
        System.out.println("2. Agentes");
        System.out.println("3. Usuario");
        System.out.println("4. Rol");
        System.out.print("Seleccione una opcion: ");
        int op = es.nextInt();
        es.nextLine(); // Limpiar buffer
        return op;
    }

    public static void subMenu(int op) throws NonexistentEntityException {
        switch (op) {
            case 1:
                subMenuServicio();
                break;
            case 2:
                subMenuAgentes();
                break;
            case 3:
                subMenuUsuario();
                break;
            case 4:
                subMenuRol();
                break;
            default:
                System.out.println("Opción inválida");
                break;
        }
    }

    public static void subMenuServicio() throws NonexistentEntityException {
        System.out.println("1. Crear Servicio");
        System.out.println("2. Eliminar Servicio");
        System.out.println("3. Modificar Servicio");
        System.out.println("4. Ver Servicios");
        System.out.print("Seleccione una Opcion: ");
        int op = es.nextInt();
        es.nextLine(); // Limpiar buffer
        
        switch (op) {
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
            default:
                System.out.println("Opción inválida");
                break;
        }
    }

    public static void subMenuAgentes() throws NonexistentEntityException {
        System.out.println("1. Crear Agente");
        System.out.println("2. Mostrar Agentes");
        System.out.println("3. Eliminar Agente");
        System.out.println("4. Modificar Agente");
        System.out.println("5. Ver Agente Específico");
        System.out.print("Seleccione una Opcion: ");
        int op = es.nextInt();
        es.nextLine(); // Limpiar buffer
        
        switch (op) {
            case 1:
                System.out.println("\nCrear Agente");
                Agente a = new Agente();
                agDB.crearAgente(es, a);
                break;
            case 2:
                System.out.println("\nMostrar Agentes");
                agDB.mostrarAgentes();
                break;
            case 3:
                System.out.println("\nEliminar Agente");
                agDB.eliminarAgente(es);
                break;
            case 4:
                System.out.println("\nModificar Agente");
                agDB.modificarAgente(es);
                break;
            case 5:
                System.out.println("\nMostrar un Agente");
                System.out.print("Ingrese el ID del agente: ");
                int id = es.nextInt();
                agDB.mostrarUno(id);
                break;
            default:
                System.out.println("Opción inválida");
                break;
        }
    }

    public static void subMenuUsuario() throws NonexistentEntityException {
        System.out.println("1. Crear Usuario");
        System.out.println("2. Mostrar Usuarios");
        System.out.println("3. Modificar Usuario");
        System.out.println("4. Eliminar Usuario");
        System.out.print("Seleccione una Opcion: ");
        int op = es.nextInt();
        es.nextLine(); // Limpiar buffer
        
        switch (op) {
            case 1:
                System.out.println("\nCrear Usuario");
                Usuario u = new Usuario();
                usDB.crearUsuario(es, u);
                break;
            case 2:
                System.out.println("\nMostrar Usuarios");
                usDB.mostrarUsuarios();
                break;
            case 3:
                System.out.println("\nModificar Usuario");
                usDB.modificarUsuario(es);
                break;
            case 4:
                System.out.println("\nEliminar Usuario");
                usDB.eliminarUsuario(es);
                break;
            default:
                System.out.println("Opción inválida");
                break;
        }
    }

   public static void subMenuRol() throws NonexistentEntityException {
    es.nextLine();
    System.out.println("1. Crear Rol");
    System.out.println("2. Mostrar Roles");
    System.out.println("3. Modificar Rol");
    System.out.println("4. Eliminar Rol");
    System.out.print("Seleccione una Opcion: ");
    int op = es.nextInt();
    es.nextLine();
    
    switch (op) {
        case 1:
            System.out.println("\nCrear Rol");
            rolDB.crearRol(); // Sin parámetros
            break;
        case 2:
            System.out.println("\nMostrar Roles");
            rolDB.mostrarRoles();
            break;
        case 3:
            System.out.println("\nModificar Rol");
            rolDB.modificarRol();
            break;
        case 4:
            System.out.println("\nEliminar Rol");
            rolDB.eliminarRol(); 
            break;
        default:
            System.out.println("Opción inválida");
            break;
    }
    }
}
