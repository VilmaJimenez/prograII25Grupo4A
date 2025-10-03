package gt.edu.miumg.bd.AgentesDB;

import gt.edu.miumg.bd.Agente;
import gt.edu.miumg.bd.AgenteJpaController;
import gt.edu.miumg.bd.Usuario;
import gt.edu.miumg.bd.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Julissa
 *
 */
public class AgentesDataBase {

    EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("gt.edu.miumg_SafeTech2_jar_1.0-SNAPSHOTPU");
    AgenteJpaController agenteJPA = new AgenteJpaController(emf);

    public void crearAgente(Scanner es, Agente a) {
        System.out.print("Ingrese nombre: ");
        es.nextLine();
        a.setNombre(es.nextLine());

        System.out.print("Ingrese apellido: ");
        a.setApellido(es.nextLine());

        System.out.print("Ingrese edad: ");
        a.setEdad(Integer.parseInt(es.nextLine()));

        System.out.print("Ingrese residencia: ");
        a.setResidencia(es.nextLine());

        System.out.print("Ingrese estado (true=Activo, false=Inactivo): ");
        a.setEstado(Boolean.parseBoolean(es.nextLine()));

        System.out.print("Ingrese teléfono: ");
        a.setTelefono(Integer.parseInt(es.nextLine()));

        System.out.print("Ingrese idUsuario existente: ");
        int uId = es.nextInt();
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(uId);

        a.setIdUsuario(usuario);
        agenteJPA.create(a);
        System.out.println("Agente creado con éxito.");
    }

    public void mostrarAgentes() {
        List<Agente> lista = agenteJPA.findAgenteEntities();
        if (lista.isEmpty()) {
            System.out.println(" No hay agentes registrados.");
        } else {
            for (Agente a : lista) {
                mostrarDatosAgente(a); // Método que imprime los datos
            }
        }
    }

    public void mostrarUno(int id) {
        Agente a = agenteJPA.findAgente(id); // Busca al agente por su ID
        if (a == null) {
            System.out.println("El agente con ID " + id + " no existe.");
        } else {
            mostrarDatosAgente(a);
        }
    }

    private void mostrarDatosAgente(Agente a) {
        System.out.println("ID Agente: " + a.getIdAgente());
        System.out.println("Nombre: " + a.getNombre());
        System.out.println("Apellido: " + a.getApellido());
        System.out.println("Edad: " + a.getEdad());
        System.out.println("Residencia: " + a.getResidencia());
        System.out.println("Estado: " + (a.getEstado() ? "Activo" : "Inactivo"));
        System.out.println("Teléfono: " + a.getTelefono());
        System.out.println("ID Usuario: " + a.getIdUsuario().getIdUsuario());
        System.out.println("******************************");
    }

    public void eliminarAgente(Scanner es) throws NonexistentEntityException {
        mostrarAgentes();
        System.out.print("\nIngrese el ID del Agente a Eliminar: ");
        es.nextLine();
        int id = Integer.parseInt(es.nextLine());
        try {
            agenteJPA.destroy(id);
            System.out.println("Agente eliminado.");
        } catch (NonexistentEntityException e) {
            System.out.println("Error: ID inválido.");
        }
    }

    // Modificar
    public void modificarAgente(Scanner es) {
        es.nextLine();
        System.out.print("Ingrese el ID del Agente a Modificar: ");
        int id = Integer.parseInt(es.nextLine());
        Agente a = agenteJPA.findAgente(id);

        if (a == null) {
            System.out.println("El agente no existe.");
        } else {
            mostrarUno(id);
            System.out.println("\nIngrese los nuevos datos:");

            System.out.print("Nuevo nombre: ");
            a.setNombre(es.nextLine());

            System.out.print("Nuevo apellido: ");
            a.setApellido(es.nextLine());

            System.out.print("Nueva edad: ");
            a.setEdad(Integer.parseInt(es.nextLine()));

            System.out.print("Nueva residencia: ");
            a.setResidencia(es.nextLine());

            System.out.print("Nuevo estado (true/false): ");
            a.setEstado(Boolean.parseBoolean(es.nextLine()));

            System.out.print("Nuevo teléfono: ");
            a.setTelefono(Integer.parseInt(es.nextLine()));

            System.out.print("Nuevo idUsuario: ");
            int u = es.nextInt();
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(u);

            try {
                agenteJPA.edit(a);
                System.out.println("Agente modificado con éxito.");
            } catch (Exception e) {
                System.out.println("Error al modificar.");
            }
        }
    }
}
