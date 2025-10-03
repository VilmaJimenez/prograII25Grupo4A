package gt.edu.miumg.bd.AgentesDB;
import gt.edu.miumg.bd.Rol;
import gt.edu.miumg.bd.RolJpaController;
import gt.edu.miumg.bd.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Julissa
 */
public class RolDataBase {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("gt.edu.miumg_SafeTech2_jar_1.0-SNAPSHOTPU");
    RolJpaController rolJPA = new RolJpaController(emf);

    Scanner es = new Scanner(System.in);

    public void crearRol() {
    try {
        Rol r = new Rol();
        
        System.out.print("Ingrese nombre del rol: ");
        r.setNombre(es.nextLine());
        System.out.print("Ingrese acción del rol: ");
        r.setAccion(es.nextLine());

        rolJPA.create(r);
        System.out.println("Rol creado con éxito.");
    } catch (Exception e) {
        System.out.println("Error al crear rol: " + e.getMessage());
    }

    }

    public void mostrarRoles() {
        List<Rol> lista = rolJPA.findRolEntities();
        for (Rol r : lista) {
            System.out.println(r.getIdRol() + " | " + r.getNombre() + " | " + r.getAccion());
        }
    }

    public void modificarRol() {
    try {
        mostrarRoles();
        System.out.print("Ingrese ID del rol a editar: ");
        int id = Integer.parseInt(es.nextLine());
        
        Rol r = rolJPA.findRol(id);
        if (r != null) {
            System.out.print("Nuevo nombre: ");
            r.setNombre(es.nextLine());
            System.out.print("Nueva acción: ");
            r.setAccion(es.nextLine());

            rolJPA.edit(r);
            System.out.println("Rol actualizado con éxito.");
        } else {
            System.out.println("Rol no encontrado.");
        }
    } catch (Exception e) {
        System.out.println("Error al modificar rol: " + e.getMessage());
        e.printStackTrace();
    }
    }

    public void eliminarRol() {
        mostrarRoles();
        System.out.print("Ingrese ID del rol a eliminar: ");
        int id = Integer.parseInt(es.nextLine());
        try {
            rolJPA.destroy(id);
            System.out.println("Rol eliminado con éxito.");
        } catch (NonexistentEntityException e) {
            System.out.println("El rol no existe.");
        }
    }
}
