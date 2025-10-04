package gt.edu.miumg.bd.ServiceDB;

import gt.edu.miumg.bd.Cliente;
import gt.edu.miumg.bd.ClienteJpaController;
import gt.edu.miumg.bd.PlanServicio;
import gt.edu.miumg.bd.PlanServicioJpaController;
import gt.edu.miumg.bd.Servicio;
import gt.edu.miumg.bd.ServicioJpaController;
import gt.edu.miumg.bd.exceptions.NonexistentEntityException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class ServicioDataBase2 {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory(
            "gt.edu.miumg_SafeTech2_jar_1.0-SNAPSHOTPU");

    private final PlanServicioJpaController planJPA = new PlanServicioJpaController(emf);
    private final ClienteJpaController clientesJPA = new ClienteJpaController(emf);
    private final ServicioJpaController servicioJPA = new ServicioJpaController(emf);

    public PlanServicio crearPlanServicio(Scanner sc) {
        Cliente cliente = seleccionarCliente(sc);
        if (cliente == null) {
            return null;
        }

        System.out.print("Nombre del Plan: ");
        String nombrePlan = sc.nextLine();
        System.out.print("Descripcion: ");
        String descripcionPlan = sc.nextLine();

        System.out.println("\nServicios disponibles: ");
        mostrarServicios();
        System.out.print("Ingrese el ID del servicio a incluir en el plan: ");
        String idServicioInput = sc.nextLine().trim();
        int idServicio = Integer.parseInt(idServicioInput);

        Servicio servicio = servicioJPA.findServicio(idServicio);
        if (servicio == null) {
            System.out.println("Servicio no encontrado. Se canceló la creación del plan.");
            return null;
        }

        PlanServicio plan = new PlanServicio();
        plan.setNombrePlan(nombrePlan);
        plan.setDescripcion(descripcionPlan);
        plan.setPrecio(servicio.getPrecio());
        plan.setIdCliente(cliente);
        plan.setServicio(servicio);

        try {
            planJPA.create(plan);
            System.out.println("Plan creado correctamente con ID: " + plan.getIdPlan());
            return plan;
        } catch (Exception e) {
            System.out.println("Error al crear el plan.");
            e.printStackTrace();
            return null;
        }
    }

    public void mostrarPlanes() {
        List<PlanServicio> planes = planJPA.findPlanServicioEntities();
        if (planes.isEmpty()) {
            System.out.println("No hay planes registrados.");
        } else {
            for (PlanServicio plan : planes) {
                System.out.println("ID Plan: " + plan.getIdPlan());
                System.out.println("Nombre: " + plan.getNombrePlan());
                System.out.println("Descripcion: " + plan.getDescripcion());
                System.out.println("Precio: " + plan.getPrecio());
                System.out.println("Cliente: " + (plan.getIdCliente() != null ? plan.getIdCliente().getNombre() : "N/A"));
                System.out.println("Servicio: " + (plan.getServicio() != null ? plan.getServicio().getDescripcion() : "N/A"));
                System.out.println("**************************");
            }
        }
    }

    public void modificarPlanServicio(Scanner sc) {
        System.out.print("Ingrese el ID del plan a modificar: ");
        String idInput = sc.nextLine().trim();
        int idPlan = Integer.parseInt(idInput);

        PlanServicio plan = planJPA.findPlanServicio(idPlan);
        if (plan == null) {
            System.out.println("Plan no encontrado.");
            return;
        }

        System.out.print("Nuevo nombre (presione enter para mantener actual): ");
        String nombre = sc.nextLine();
        if (!nombre.isEmpty()) {
            plan.setNombrePlan(nombre);
        }

        System.out.print("Nueva descripcion (presione enter para mantener actual): ");
        String desc = sc.nextLine();
        if (!desc.isEmpty()) {
            plan.setDescripcion(desc);
        }

        System.out.print("Nuevo precio (0 para mantener actual): ");
        String precioInput = sc.nextLine().trim();
        if (!precioInput.isEmpty()) {
            BigDecimal precio = new BigDecimal(precioInput);
            if (precio.compareTo(BigDecimal.ZERO) > 0) {
                plan.setPrecio(precio);
            }
        }

        try {
            planJPA.edit(plan);
            System.out.println("Plan modificado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al modificar el plan.");
            e.printStackTrace();
        }
    }

    public void eliminarPlanServicio(Scanner sc) {
        System.out.print("Ingrese el ID del plan a eliminar: ");
        String idInput = sc.nextLine().trim();
        int idPlan = Integer.parseInt(idInput);
        try {
            planJPA.destroy(idPlan);
            System.out.println("Plan eliminado correctamente.");
        } catch (NonexistentEntityException e) {
            System.out.println("Plan no encontrado.");
        } catch (Exception e) {
            System.out.println("Error al eliminar el plan.");
            e.printStackTrace();
        }
    }

    private Cliente seleccionarCliente(Scanner sc) {
        List<Cliente> clientes = clientesJPA.findClienteEntities();
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return null;
        }

        for (Cliente c : clientes) {
            System.out.println("ID: " + c.getIdCliente() + " - Nombre: " + c.getNombre());
        }
        System.out.print("Ingrese el ID del cliente: ");
        String idInput = sc.nextLine().trim();
        int idCliente = Integer.parseInt(idInput);

        Cliente cliente = clientesJPA.findCliente(idCliente);
        if (cliente == null) {
            System.out.println("Cliente no existe.");
        }
        return cliente;
    }

    private void mostrarServicios() {
        List<Servicio> servicios = servicioJPA.findServicioEntities();
        for (Servicio s : servicios) {
            System.out.println("ID: " + s.getIdServicio() + " - " + s.getDescripcion() + " - Precio: " + s.getPrecio());
        }
    }
}
