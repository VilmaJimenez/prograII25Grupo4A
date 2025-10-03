
package gt.edu.miumg.safetech2;

import gt.edu.miumg.bd.Agente;
import gt.edu.miumg.bd.AgentesDB.AgentesDataBase;
import gt.edu.miumg.bd.AgentesDB.RolDataBase;
import gt.edu.miumg.bd.AgentesDB.UsuarioDataBase;
import gt.edu.miumg.bd.Cliente;
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
        boolean salir = false;
        var ingreso = login();
        if (ingreso) {
            while (!salir) {
                int op = menu();
                salir = subMenu(op); 
            }
        }
    }

    public static boolean login() {
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
        } else {
            System.out.println("\nUsuario Correcto");
        }
        return true;
    }

    public static int menu() {
        System.out.println("\n***** MENU *****");
        System.out.println("1. Mantenimiento de Servicios");
        System.out.println("2. Mantenimiento de Clientes");
        System.out.println("3. Manejo de Planes");
        System.out.println("4.  Agentes");
        System.out.println("5. Usuario");
        System.out.println("6. Rol");

        System.out.println("7. Salir");
        System.out.print("Seleccione una opcion: ");
        return es.nextInt();
    }

    public static boolean subMenu(int op) throws NonexistentEntityException {
        switch (op) {
            case 1:
                return subMenuServicio();
            case 2:
                return subMenuClientes();
            case 3:
                return subMenuPlanes();
            case 4:
                return subMenuAgentes();
            case 5:
                return subMenuUsuario();
            case 6:
                return subMenuRol();
            case 7:
                System.out.println("\nSaliendo del programa...");
                return true;
            default:
                System.out.println("Número incorrecto");
        }
        return false;
    }

    public static boolean subMenuServicio() throws NonexistentEntityException {
        boolean regresar = false;

        while (!regresar) {
            es.nextLine();
            System.out.println("\n***** SUBMENÚ SERVICIOS *****");
            System.out.println("1. Crear Servicio");
            System.out.println("2. Eliminar Servicio");
            System.out.println("3. Modificar Servicio");
            System.out.println("4. Ver Servicios");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una Opcion: ");
            int op = es.nextInt();

            switch (op) {
                case 1:
                    System.out.println("\n*** Crear Servicio ***");
                    Servicio ser = new Servicio();
                    svDB.crearServicio(es, ser);
                    break;
                case 2:
                    System.out.println("\n*** Eliminar Servicio ***");
                    svDB.eliminarServicio(es);
                    break;
                case 3:
                    System.out.println("\n*** Modificar Servicio ***");
                    svDB.modificarServicio(es);
                    break;
                case 4:
                    System.out.println("\n*** Ver Servicios ***");
                    svDB.mostrarServicios();
                    break;
                case 5:
                    System.out.println("Volviendo al menú principal...");
                    regresar = true;
                    break;
                default:
                    System.out.println("Número incorrecto");
            }
        }
        return false;
    }

    public static boolean subMenuClientes() {
        boolean regresar = false;

        while (!regresar) {
            es.nextLine();
            System.out.println("\n***** SUBMENÚ CLIENTES *****");
            System.out.println("1. Crear Cliente");
            System.out.println("2. Eliminar Cliente");
            System.out.println("3. Modificar Cliente");
            System.out.println("4. Ver Clientes");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una Opcion: ");
            int op = es.nextInt();

            switch (op) {
                case 1:
                    System.out.println("***** Crear Cliente *****");
                    Cliente cliente = new Cliente();
                    svDB.crearCliente(es, cliente);
                    break;
                case 2:
                    System.out.println("***** Eliminar Cliente *****");
                    svDB.eliminarCliente(es);
                    break;
                case 3:
                    System.out.println("***** Modificar Cliente *****");
                    svDB.modificarCliente(es);
                    break;
                case 4:
                    System.out.println("***** Ver Todos los Clientes *****");
                    svDB.mostrarClientes();
                    break;
                case 5:
                    System.out.println("Volviendo al menú principal...");
                    regresar = true;
                    break;
                default:
                    System.out.println("Número no válido");
            }
        }
        return false;
    }

    public static boolean subMenuPlanes() {
        boolean regresar = false;

        while (!regresar) {
            es.nextLine();
            System.out.println("\n***** SUBMENÚ PLANES *****");
            System.out.println("1. Crear Plan");
            System.out.println("2. Eliminar Plan");
            System.out.println("3. Modificar Plan");
            System.out.println("4. Ver Planes");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una Opcion: ");
            int op = es.nextInt();

            switch (op) {
                case 1:
                    System.out.println("\n***** Crear un Plan *****");
                    svDB.crearPlanServicio(es);
                    break;
                case 2:
                    System.out.println("\n***** Eliminar un Plan *****");

                    break;
                case 3:
                    System.out.println("\n***** Modificar un Plan *****");

                    break;
                case 4:
                    System.out.println("\n***** Ver todos los Planes *****");

                    break;
                case 5:
                    System.out.println("Volviendo al menú principal...");
                    regresar = true;
                    break;
                default:
                    System.out.println("Número incorrecto");
            }
        }
        return false;
    }

    public static boolean subMenuAgentes() throws NonexistentEntityException {
        boolean regresar = false;

        while (!regresar) {
            System.out.println("1. Crear Agente");
            System.out.println("2. Mostrar Agentes");
            System.out.println("3. Eliminar Agente");
            System.out.println("4. Modificar Agente");
            System.out.println("5. Ver Agente Específico");
            System.out.println("6. Regresar");
            System.out.print("Seleccione una Opcion: ");
            int op = es.nextInt();
            es.nextLine();

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
                case 6:
                    System.out.println("Volviendo al menú principal...");
                    regresar = true;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
        return false;

    }

    public static boolean subMenuUsuario() throws NonexistentEntityException {
        boolean regresar = false;

        while (!regresar) {
            System.out.println("1. Crear Usuario");
            System.out.println("2. Mostrar Usuarios");
            System.out.println("3. Modificar Usuario");
            System.out.println("4. Eliminar Usuario");
            System.out.println("5. Regresar");
            System.out.print("Seleccione una Opcion: ");
            int op = es.nextInt();
            es.nextLine();

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
                case 5:
                    System.out.println("Volviendo al menú principal...");
                    regresar = true;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
        return false;

    }

    public static boolean subMenuRol() throws NonexistentEntityException {
        boolean regresar = false;

        while (!regresar) {
            es.nextLine();
            System.out.println("1. Crear Rol");
            System.out.println("2. Mostrar Roles");
            System.out.println("3. Modificar Rol");
            System.out.println("4. Eliminar Rol");
            System.out.println("5. Regresar");
            System.out.print("Seleccione una Opcion: ");
            int op = es.nextInt();
            es.nextLine();

            switch (op) {
                case 1:
                    System.out.println("\nCrear Rol");
                    rolDB.crearRol();
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
                case 5:
                    System.out.println("Volviendo al menú principal...");
                    regresar = true;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
        return false;
    }

}
