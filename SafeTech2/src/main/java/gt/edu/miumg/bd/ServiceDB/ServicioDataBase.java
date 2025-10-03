package gt.edu.miumg.bd.ServiceDB;

import gt.edu.miumg.bd.Cliente;
import gt.edu.miumg.bd.ClienteJpaController;
import gt.edu.miumg.bd.PlanServicio;
import gt.edu.miumg.bd.PlanServicioJpaController;
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
    ClienteJpaController clientesJPA = new ClienteJpaController(emf);
    PlanServicioJpaController planJPA = new PlanServicioJpaController(emf);

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

    public void mostrarServicios() {
        List<Servicio> listaServicios = serviceJPA.findServicioEntities();
        if (listaServicios.isEmpty()) {
            System.out.println("No hay servicios creados");

        } else {
            for (Servicio ser : listaServicios) {
                System.out.println("ID Servicio: " + ser.getIdServicio());
                System.out.println("Descripcion: " + ser.getDescripcion());
                System.out.println("Precio: " + ser.getPrecio());
                System.out.println("**************************");
            }
        }
    }

    public void eliminarServicio(Scanner es) throws NonexistentEntityException {
        System.out.println("Servicios Disponibles: ");
        mostrarServicios();
        System.out.print("\nIngrese el ID del Servicio a Eliminar: ");
        es.nextLine();
        int id = Integer.parseInt(es.nextLine());
        try {
            serviceJPA.destroy(id);
            System.out.println("El Servicio " + id + " ha sido elimiado");
        } catch (Exception e) {
            System.out.println("Error al intentar eliminar el servicio.");
        }

    }

    public void modificarServicio(Scanner es) {
        es.nextLine();
        System.out.println("Ingrese el ID del Servicio a Modificar: ");
        int id = Integer.parseInt(es.nextLine());
        Servicio ser = serviceJPA.findServicio(id);
        if (ser == null) {
            System.out.println("El Servicio no existe en la base de datos");
        } else {
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

    public void mostrarUno(Servicio ser) {
        System.out.println("ID Servicio: " + ser.getIdServicio());
        System.out.println("Descripcion: " + ser.getDescripcion());
        System.out.println("Precio: " + ser.getPrecio());
        System.out.println("**************************");

    }

    //Clientes
    public void crearCliente(Scanner s, Cliente cliente) {
        s.nextLine();
        try {
            System.out.println("Ingrese los datos del nuevo cliente:");
            System.out.print("Nit: ");
            String nit = s.nextLine();
            System.out.print("Nombre: ");
            String nombre = s.nextLine();
            System.out.print("Direccion: ");
            String direccion = s.nextLine();
            System.out.print("Telefono (Sin guiones): ");
            int telefono = s.nextInt();
            s.nextLine();
            System.out.print("Email: ");
            String correo = s.nextLine();
            System.out.print("Observacion: ");
            String obs = s.nextLine();

            cliente.setNitCliente(nit);
            cliente.setNombre(nombre);
            cliente.setDireccion(direccion);
            cliente.setTelefono(telefono);
            cliente.setEmail(correo);
            cliente.setObservacion(obs);

        } catch (Exception e) {
            System.out.println("Ocurrio un problema con el ingreso de los datos");
        }

        try {
            clientesJPA.create(cliente);
            System.out.println("\nCliente creado con exito! ");
        } catch (Exception e) {
            System.out.println("\nA ocurrido un problema creando el cliente");
        }

    }

    public void mostrarClientes() {
        List<Cliente> listaClientes = clientesJPA.findClienteEntities();
        if (listaClientes.isEmpty()) {
            System.out.println("No hay clientes creados");
        } else {
            for (Cliente cli : listaClientes) {
                System.out.println("");
                System.out.println("ID Cliente: " + cli.getIdCliente());
                System.out.println("Nit: " + cli.getNitCliente());
                System.out.println("Nombre: " + cli.getNombre());
                System.out.println("Direccion: " + cli.getDireccion());
                System.out.println("Telefono: " + cli.getTelefono());
                System.out.println("Email: " + cli.getEmail());
                System.out.println("Observaciones: " + cli.getObservacion());
                System.out.println("**************************");
            }
        }
    }

    public void eliminarCliente(Scanner s) {
        System.out.println("\nClientes disponibles: ");
        mostrarClientes();
        System.out.println("\nIngrese el ID del Cliente a eliminar: ");
        int id = s.nextInt();
        s.nextLine();
        try {
            System.out.println("Eliminando cliente...");
            clientesJPA.destroy(id);
            System.out.println("\nCliente con ID " + id + " eliminado con exito!");
        } catch (Exception e) {
            System.out.println("A ocurrido un problema.");
        }
    }

    public void mostrarUnCliente(Cliente cli) {
        System.out.println("ID Cliente: " + cli.getIdCliente());
        System.out.println("Nit: " + cli.getNitCliente());
        System.out.println("Nombre: " + cli.getNombre());
        System.out.println("Direccion: " + cli.getDireccion());
        System.out.println("Telefono: " + cli.getTelefono());
        System.out.println("Email: " + cli.getEmail());
        System.out.println("Observaciones: " + cli.getObservacion());
        System.out.println("**************************");
    }

    public void modificarCliente(Scanner s) {
        System.out.println("Ingrese el ID del cliente a modificar: ");
        int id = s.nextInt();
        s.nextLine();
        Cliente cli = clientesJPA.findCliente(id);
        if (cli == null) {
            System.out.println("El cliente no existe.");
        } else {
            try {
                mostrarUnCliente(cli);
                System.out.println("Ingrese los nuevos datos del cliente: ");
                System.out.print("Nit: ");
                String nit = s.nextLine();
                System.out.print("Nombre: ");
                String nombre = s.nextLine();
                System.out.print("Direccion: ");
                String direccion = s.nextLine();
                System.out.print("Telefono (Sin guiones): ");
                int telefono = s.nextInt();
                s.nextLine();
                System.out.print("Email: ");
                String correo = s.nextLine();
                System.out.print("Observacion: ");
                String obs = s.nextLine();

                cli.setNitCliente(nit);
                cli.setNombre(nombre);
                cli.setDireccion(direccion);
                cli.setTelefono(telefono);
                cli.setEmail(correo);
                cli.setObservacion(obs);

            } catch (Exception e) {
                System.out.println("Ocurrio un problema con el ingreso de los datos");
            }

            try {
                clientesJPA.edit(cli);
                System.out.println("\nCliente modificado con exito!");
            } catch (Exception e) {
                System.out.println("A ocurrido un problema con la modificacion del clinete.");
            }
        }
    }

    //Planes 
    public void crearPlanServicio(Scanner s) {
        boolean val = false;
        int id;
        Cliente cli = null;

        s.nextLine();
        System.out.println("Para crear un plan seleccione el ID del cliente: ");
        System.out.println("Desea ver el listado de clientes? (true/false): ");
        boolean valida = s.nextBoolean();
        if (valida) {
            mostrarClientes();
        }
        while (!val) {
            System.out.print("Ingrese el ID del cliente para crear el plan: ");
            id = s.nextInt();
            cli = clientesJPA.findCliente(id);
            if (cli == null) {
                System.out.println("El cliente no existe. Seleccione uno que este creado en la base de datos");
            } else {
                val = true;
            }
        }
        s.nextLine();
        System.out.print("Nombre del Plan: ");
        String nombrePlan = s.nextLine();
        System.out.print("Descripcion: ");
        String descripcionPlan = s.nextLine();
        System.out.println("");
        System.out.println("Servicios disponibles: ");
        mostrarServicios();
        System.out.println("Agrege el ID del servicio al plan: ");
        int idServicio = s.nextInt();
        s.nextLine();
        Servicio ser = serviceJPA.findServicio(idServicio);
        System.out.println("Precio del Servicio: " + ser.getPrecio());

        PlanServicio plan = new PlanServicio();
        plan.setNombrePlan(nombrePlan);
        plan.setDescripcion(descripcionPlan);
        plan.setPrecio(ser.getPrecio());
        plan.setIdCliente(cli);
        plan.setIdServicio(ser);

        try {
            planJPA.create(plan);
            System.out.println("Creando plan...");
            System.out.println("Plan creado con exito!");
        } catch (Exception e) {
            System.out.println("Ah ocurrido un error");
        }

    }

}
