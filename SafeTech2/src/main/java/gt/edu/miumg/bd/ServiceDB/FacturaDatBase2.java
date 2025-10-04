package gt.edu.miumg.bd.ServiceDB;

import java.text.SimpleDateFormat;
import gt.edu.miumg.bd.Cliente;
import gt.edu.miumg.bd.ClienteJpaController;
import gt.edu.miumg.bd.Factura;
import gt.edu.miumg.bd.FacturaJpaController;
import gt.edu.miumg.bd.Detalle;
import gt.edu.miumg.bd.DetalleJpaController;
import gt.edu.miumg.bd.Servicio;
import gt.edu.miumg.bd.ServicioJpaController;
import gt.edu.miumg.bd.exceptions.NonexistentEntityException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FacturaDatBase2 {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory(
            "gt.edu.miumg_SafeTech2_jar_1.0-SNAPSHOTPU");

    private final FacturaJpaController facturaJPA = new FacturaJpaController(emf);
    private final DetalleJpaController detalleJPA = new DetalleJpaController(emf);
    private final ClienteJpaController clientesJPA = new ClienteJpaController(emf);
    private final ServicioJpaController servicioJPA = new ServicioJpaController(emf);

    public void crearFactura(Scanner sc) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();

            Cliente cliente = seleccionarCliente(sc);
            if (cliente == null) {
                return;
            }

            Factura factura = new Factura();
            factura.setIdCliente(cliente);
            factura.setFecha(new Date());
            factura.setTotal(BigDecimal.ZERO);
            em.persist(factura);

            BigDecimal totalFactura = BigDecimal.ZERO;
            boolean agregarMas = true;

            while (agregarMas) {
                Servicio servicio = seleccionarServicio(sc);
                if (servicio == null) {
                    continue;
                }

                System.out.print("Ingrese la cantidad: ");
                String cantidadInput = sc.nextLine().trim();
                int cantidad = Integer.parseInt(cantidadInput);

                BigDecimal subtotal = servicio.getPrecio().multiply(BigDecimal.valueOf(cantidad));

                Detalle detalle = new Detalle();
                detalle.setIdFactura(factura);
                detalle.setIdServicio(servicio);
                detalle.setCantidad(cantidad);
                detalle.setSubtotal(subtotal);
                em.persist(detalle);

                if (factura.getDetalleList() == null) {
                    factura.setDetalleList(new ArrayList<>());
                }
                factura.getDetalleList().add(detalle);

                totalFactura = totalFactura.add(subtotal);

                System.out.print("¿Desea agregar otro servicio? (s/n): ");
                String respuesta = sc.nextLine().trim();
                agregarMas = respuesta.equalsIgnoreCase("s");
            }

            factura.setTotal(totalFactura);
            em.merge(factura);

            em.getTransaction().commit();

            mostrarFactura(factura);

        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Ocurrió un error creando la factura: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void crearFacturaConServicio(Scanner sc, Servicio servicio) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();

            Cliente cliente = seleccionarCliente(sc);
            if (cliente == null) {
                System.out.println("No se creó la factura: no existe un cliente válido.");
                return;
            }

            System.out.print("Ingrese la cantidad para el servicio (por defecto 1): ");
            String cantidadInput = sc.nextLine().trim();
            int cantidad = cantidadInput.isEmpty() ? 1 : Integer.parseInt(cantidadInput);
            if (cantidad <= 0) {
                cantidad = 1;
            }

            BigDecimal subtotal = servicio.getPrecio().multiply(BigDecimal.valueOf(cantidad));

            Factura factura = new Factura();
            factura.setIdCliente(cliente);
            factura.setFecha(new Date());
            factura.setTotal(subtotal);
            em.persist(factura);

            Detalle detalle = new Detalle();
            detalle.setIdFactura(factura);
            detalle.setIdServicio(servicio);
            detalle.setCantidad(cantidad);
            detalle.setSubtotal(subtotal);
            em.persist(detalle);

            if (factura.getDetalleList() == null) {
                factura.setDetalleList(new ArrayList<>());
            }
            factura.getDetalleList().add(detalle);

            em.getTransaction().commit();

            System.out.println("\n*** FACTURA GENERADA ***");
            System.out.println("Factura ID: " + factura.getIdFactura());
            System.out.println("Cliente: " + (factura.getIdCliente() != null ? factura.getIdCliente().getNombre() : "N/A"));
            System.out.println("Fecha: " + factura.getFecha());
            System.out.println("Total: " + factura.getTotal());
            System.out.println("Detalle de servicios:");
            System.out.println(" - Servicio: " + servicio.getDescripcion()
                    + ", Cantidad: " + cantidad
                    + ", Subtotal: " + subtotal);
            System.out.println("**************************");

        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Ocurrió un error creando la factura automática: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void mostrarFactura(Factura f) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        System.out.println("\n*** FACTURA GENERADA ***");
        System.out.println("Factura ID: " + f.getIdFactura());
        System.out.println("Cliente: " + (f.getIdCliente() != null ? f.getIdCliente().getNombre() : "N/A"));
        System.out.println("Fecha: " + formato.format(f.getFecha()));
        System.out.println("Total: " + f.getTotal());
        System.out.println("Detalle de servicios:");

        List<Detalle> listaDetalle = detalleJPA.findDetalleByFactura(f);
        boolean tieneDetalle = false;

        for (Detalle d : listaDetalle) {
            if (d.getIdFactura() != null && d.getIdFactura().getIdFactura().equals(f.getIdFactura())) {
                String descripcion = "";

                if (d.getIdPlan() != null) {
                    descripcion = d.getIdPlan().getNombrePlan() + " (Plan) - Servicios: ";
                    if (d.getIdPlan().getServicio() != null) {
                        descripcion += d.getIdPlan().getServicio().getDescripcion();
                    } else {
                        descripcion += "N/A";
                    }
                } else if (d.getIdServicio() != null) {
                    descripcion = d.getIdServicio().getDescripcion();
                } else {
                    descripcion = "N/A";
                }

                BigDecimal sub = d.getSubtotal() != null ? d.getSubtotal() : BigDecimal.ZERO;
                System.out.println(" - " + descripcion
                        + ", Cantidad: " + d.getCantidad()
                        + ", Subtotal: " + sub);
                tieneDetalle = true;
            }
        }

        if (!tieneDetalle) {
            System.out.println("   (No hay servicios agregados a esta factura)");
        }
        System.out.println("**************************\n");
    }

    public void mostrarFacturas() {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            List<Factura> facturas = em.createQuery(
                    "SELECT DISTINCT f FROM Factura f LEFT JOIN FETCH f.detalleList d LEFT JOIN FETCH d.idServicio LEFT JOIN FETCH d.idPlan",
                    Factura.class
            ).getResultList();

            if (facturas.isEmpty()) {
                System.out.println("No hay facturas registradas.");
                return;
            }

            for (Factura f : facturas) {
                System.out.println("\nFactura ID: " + f.getIdFactura());
                System.out.println("Cliente: " + (f.getIdCliente() != null ? f.getIdCliente().getNombre() : "N/A"));
                System.out.println("Fecha: " + formato.format(f.getFecha()));
                System.out.println("Total: " + f.getTotal());
                System.out.println("Detalle de servicios:");

                List<Detalle> listaDetalle = f.getDetalleList();

                if (listaDetalle == null || listaDetalle.isEmpty()) {
                    System.out.println("   (No hay servicios agregados a esta factura)");
                } else {
                    for (Detalle d : listaDetalle) {
                        String descripcion = "";

                        if (d.getIdPlan() != null) {
                            descripcion = d.getIdPlan().getNombrePlan() + " (Plan)";
                        } else if (d.getIdServicio() != null) {
                            descripcion = d.getIdServicio().getDescripcion();
                        } else {
                            descripcion = "Servicio no especificado";
                        }

                        BigDecimal sub = d.getSubtotal() != null ? d.getSubtotal() : BigDecimal.ZERO;
                        System.out.println(" - " + descripcion
                                + ", Cantidad: " + d.getCantidad()
                                + ", Subtotal: " + sub);
                    }
                }

                System.out.println("**************************");
            }
        } catch (Exception e) {
            System.out.println("Error mostrando facturas: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    private Cliente seleccionarCliente(Scanner sc) {
        List<Cliente> listaClientes = clientesJPA.findClienteEntities();
        if (listaClientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return null;
        }

        System.out.println("Clientes disponibles:");
        for (Cliente cli : listaClientes) {
            System.out.println("ID: " + cli.getIdCliente() + " - Nombre: " + cli.getNombre());
        }

        System.out.print("Ingrese el ID del cliente: ");
        String idInput = sc.nextLine().trim();
        int idCliente = Integer.parseInt(idInput);
        Cliente cli = clientesJPA.findCliente(idCliente);
        if (cli == null) {
            System.out.println("Cliente no existe.");
        }
        return cli;
    }

    private Servicio seleccionarServicio(Scanner sc) {
        List<Servicio> listaServicios = servicioJPA.findServicioEntities();
        if (listaServicios.isEmpty()) {
            System.out.println("No hay servicios registrados.");
            return null;
        }

        System.out.println("Servicios disponibles:");
        for (Servicio s : listaServicios) {
            System.out.println("ID: " + s.getIdServicio()
                    + " - Descripcion: " + s.getDescripcion()
                    + " - Precio: " + s.getPrecio());
        }

        System.out.print("Ingrese el ID del servicio: ");
        String idInput = sc.nextLine().trim();
        int idServicio = Integer.parseInt(idInput);
        Servicio ser = servicioJPA.findServicio(idServicio);
        if (ser == null) {
            System.out.println("Servicio no existe.");
        }
        return ser;
    }
}
