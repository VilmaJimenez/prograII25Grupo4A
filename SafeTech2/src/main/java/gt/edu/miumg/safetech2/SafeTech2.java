package gt.edu.miumg.safetech2;

import gt.edu.miumg.bd.Agente;
import gt.edu.miumg.bd.AgentesDB.AgentesDataBase;
import gt.edu.miumg.bd.AgentesDB.RolDataBase;
import gt.edu.miumg.bd.AgentesDB.UsuarioDataBase;
import gt.edu.miumg.bd.Cliente;
import gt.edu.miumg.bd.PlanServicio;
import gt.edu.miumg.bd.Rol;
import gt.edu.miumg.bd.ServiceDB.FacturaDatBase2;
import gt.edu.miumg.bd.ServiceDB.ServiceLog;
import gt.edu.miumg.bd.ServiceDB.ServicioDataBase;
import gt.edu.miumg.bd.ServiceDB.ServicioDataBase2;
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
    static Rol logu = new Rol();
    static ServicioDataBase2 svDB2 = new ServicioDataBase2();
    static FacturaDatBase2 facturaDB = new FacturaDatBase2();

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
        boolean val = false;
        System.out.println("\n**********Bienvenido a SAFETECH!! Su Empresa de Seguridad**********");
        System.out.println("\n\n");
        System.out.println("Para acceder ingrese sus credenciales:");
        while (!val) {
            System.out.print("Usuario: ");
            String user = es.next();
            System.out.print("Password: ");
            String pass = es.next();

            var logueo = login.login(user, pass);

            if (logueo == null) {
                System.out.println("\nCredenciales Invalidas, intente nuevamente");
                //val = false;
                //return false;
            } else {
                System.out.println("\nUsuario Correcto");
                logu = logueo.getIdRol();
                //log.getIdRol();
                return true;

            }

        }

        return false;
    }

    public static int menu() {

        if (logu.getIdRol().equals(2)) {

            System.out.println("\n***** MENU *****");
            System.out.println("\nPermisos de Ventas:");
            System.out.println("3. Manejo de Planes");
            System.out.print("Seleccione una opcion: ");
            return es.nextInt();
        } else {

            System.out.println("\n***** MENU *****");
            System.out.println("\nPermisos de Administrador:");
            System.out.println("1. Mantenimiento de Servicios");
            System.out.println("2. Mantenimiento de Clientes");
            System.out.println("3. Manejo de Planes");
            System.out.println("4. Manejo de Facturas");
            System.out.println("5. Agentes");
            System.out.println("6. Usuario");
            System.out.println("7. Rol");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opcion: ");
            return es.nextInt();
        }

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
                return subMenuFacturas();
            case 5:
                return subMenuAgentes();
            case 6:
                return subMenuUsuario();
            case 7:
                return subMenuRol();
            case 8:
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

    public static boolean subMenuPlanesAnt() {
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
                    // svDB.crearPlanServicio(es);
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

    public static boolean subMenuPlanes() {
        boolean regresar = false;

        while (!regresar) {
            System.out.println("\n*** MANEJO DE PLANES ***");
            System.out.println("1. Crear Plan");
            System.out.println("2. Eliminar Plan");
            System.out.println("3. Modificar Plan");
            System.out.println("4. Ver Planes");
            System.out.println("5. Volver al Menú Principal");

            int op = leerEntero("Seleccione una Opcion: ");

            switch (op) {
                case 1:
                    PlanServicio plan = null;
                    try {
                        plan = svDB2.crearPlanServicio(es);
                    } catch (Exception e) {
                        System.out.println("Error creando plan: " + e.getMessage());
                    }

                    if (plan != null) {
                        System.out.print("¿Desea generar una factura para este plan? (s/n): ");
                        String respuesta = es.nextLine().trim();

                        if (respuesta.equalsIgnoreCase("s")) {
                            try {
                                Servicio servicioDelPlan = plan.getServicio();
                                facturaDB.crearFacturaConServicio(es, servicioDelPlan);
                            } catch (Exception e) {
                                System.out.println("Error generando factura para el plan: " + e.getMessage());
                            }
                        }
                    }
                    break;

                case 2:
                        try {
                    svDB2.eliminarPlanServicio(es);
                } catch (Exception e) {
                    System.out.println("Error eliminando plan: " + e.getMessage());
                }
                break;
                case 3:
                        try {
                    svDB2.modificarPlanServicio(es);
                } catch (Exception e) {
                    System.out.println("Error modificando plan: " + e.getMessage());
                }
                break;
                case 4:
                        try {
                    svDB2.mostrarPlanes();
                } catch (Exception e) {
                    System.out.println("Error mostrando planes: " + e.getMessage());
                }
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

    public static int leerEntero(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = es.nextLine().trim();
                int val = Integer.parseInt(input);
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido por favor.");
            } catch (Exception e) {
                System.out.println("Entrada no válida: " + e.getMessage());
            }
        }
    }

    public static boolean subMenuFacturas() {
        boolean regresar = false;

        while (!regresar) {
            System.out.println("\n*** MANEJO DE FACTURAS ***");
            System.out.println("1. Crear Factura");
            System.out.println("2. Ver Facturas");  // ← Se convierte en opción 2
            System.out.println("3. Volver al Menú Principal"); // ← Se convierte en opción 3

            int op = leerEntero("Seleccione una Opcion: ");

            switch (op) {
                case 1:
                try {
                    facturaDB.crearFactura(es);
                } catch (Exception e) {
                    System.out.println("Error creando factura: " + e.getMessage());
                }
                break;
                case 2:
                try {
                    facturaDB.mostrarFacturas();
                } catch (Exception e) {
                    System.out.println("Error mostrando facturas: " + e.getMessage());
                }
                break;
                case 3:
                    System.out.println("Volviendo al menú principal...");
                    regresar = true;
                    break;
                default:
                    System.out.println("Número incorrecto");
            }
        }
        return false;
    }

}
