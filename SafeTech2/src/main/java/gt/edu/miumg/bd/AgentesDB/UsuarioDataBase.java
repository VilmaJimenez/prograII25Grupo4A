package gt.edu.miumg.bd.AgentesDB;

import gt.edu.miumg.bd.Rol;
import gt.edu.miumg.bd.Usuario;
import gt.edu.miumg.bd.UsuarioJpaController;
import gt.edu.miumg.bd.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Julissa
 */
public class UsuarioDataBase {

    EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("gt.edu.miumg_SafeTech2_jar_1.0-SNAPSHOTPU");
    UsuarioJpaController usuarioJPA = new UsuarioJpaController(emf);

    public void crearUsuario(Scanner es, Usuario u) {
        System.out.println("Ingrese Nombre");
        es.nextLine();
        u.setNombre(es.nextLine());

        System.out.println("Ingrese correo");
        u.setCorreo(es.nextLine());

        System.out.println("Ingrese contraseña");
        u.setContraseña(es.nextLine());

        System.out.println("Ingrese estado (Activo/Inactivo");
        u.setEstado(es.nextLine());

        System.out.println("Ingrese Id Rol");
        int rolId = Integer.parseInt(es.nextLine());
        Rol rol = new Rol();
        rol.setIdRol(rolId);
        u.setIdRol(rol);

        usuarioJPA.create(u);
        System.out.println("Usuario creado con ID: " + u.getIdUsuario());

    }

    public void mostrarUsuarios() {
        List<Usuario> lista = usuarioJPA.findUsuarioEntities();
        if (lista.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            for (Usuario u : lista) {
                System.out.println("ID Usuario: " + u.getIdUsuario());
                System.out.println("Nombre: " + u.getNombre());
                System.out.println("Correo: " + u.getCorreo());
                System.out.println("Estado: " + u.getEstado());
                System.out.println("Rol: " + (u.getIdRol() != null ? u.getIdRol().getIdRol() : "N/A"));

                System.out.println("---------------------------");
            }
        }
    }

    public void modificarUsuario(Scanner es) {
        es.nextLine();
        System.out.print("Ingrese el ID del Usuario a modificar: ");
        int id = Integer.parseInt(es.nextLine());

        Usuario u = usuarioJPA.findUsuario(id);
        if (u == null) {
            System.out.println("El usuario no existe.");
            return;
        }

        System.out.println("Usuario actual:");
        System.out.println("Nombre: " + u.getNombre());
        System.out.println("Correo: " + u.getCorreo());
        System.out.println("Estado: " + u.getEstado());
        System.out.println("Rol: " + (u.getIdRol() != null ? u.getIdRol().getIdRol() : "N/A"));

        System.out.println("\nIngrese los nuevos datos:");

        System.out.print("Nuevo nombre: ");
        u.setNombre(es.nextLine());

        System.out.print("Nuevo correo: ");
        u.setCorreo(es.nextLine());

        System.out.print("Nueva contraseña: ");
        u.setContraseña(es.nextLine());

        System.out.print("Nuevo estado (Activo/Inactivo): ");
        u.setEstado(es.nextLine());
        RolDataBase rolDB = new RolDataBase();
        rolDB.mostrarRoles();

        System.out.print("Seleccione idRol para el usuario: ");
        int rolId = Integer.parseInt(es.nextLine());

        Rol rol = new Rol();
        rol.setIdRol(rolId);
        u.setIdRol(rol);

        try {
            usuarioJPA.edit(u);
            System.out.println("Usuario modificado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al modificar usuario: " + e.getMessage());
        }
    }

    public void eliminarUsuario(Scanner es) {
        mostrarUsuarios();
        es.nextLine();
        System.out.print("Ingrese el ID del Usuario a eliminar: ");
        int id = Integer.parseInt(es.nextLine());

        try {
            usuarioJPA.destroy(id);
            System.out.println("Usuario eliminado con éxito.");
        } catch (NonexistentEntityException e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
        }
    }
}
