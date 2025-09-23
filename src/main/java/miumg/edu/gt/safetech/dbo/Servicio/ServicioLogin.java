
package miumg.edu.gt.safetech.dbo.Servicio;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import miumg.edu.gt.safetech.controller.Usuario;
import miumg.edu.gt.safetech.dbo.UsuarioJpaController;


public class ServicioLogin {
    
    //private final UsuarioJpaController userControl;
    
    /*public ServicioLogin(EntityManagerFactory emf){
        this.userControl = new UsuarioJpaController(emf);
    }*/
    EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("miumg.edu.gt_SafeTech_jar_1.0-SNAPSHOTPU");
    UsuarioJpaController userControl = new UsuarioJpaController(emf);
    
    
    public Usuario login(int user, String password){
        List<Usuario> usuarios = userControl.findUsuarioEntities();
        for(Usuario us : usuarios){
            if (us.getIdUsuario().equals(user) && us.getContraseña().equals(password)) {
                return us;
            }
            System.out.println("Incorrecto, revise sus credenciales. ");
            
        }
        
        return null;
        
    }
}
