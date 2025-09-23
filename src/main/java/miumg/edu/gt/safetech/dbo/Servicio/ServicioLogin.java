
package miumg.edu.gt.safetech.dbo.Servicio;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import miumg.edu.gt.safetech.controller.Usuario;
import miumg.edu.gt.safetech.dbo.UsuarioJpaController;


public class ServicioLogin {
    
    private final UsuarioJpaController userControl;
    
    public ServicioLogin(EntityManagerFactory emf){
        this.userControl = new UsuarioJpaController(emf);
    }
    
    public Usuario login(String user, String password){
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
