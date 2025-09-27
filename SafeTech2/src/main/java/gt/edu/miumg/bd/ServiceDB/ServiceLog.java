
package gt.edu.miumg.bd.ServiceDB;

import gt.edu.miumg.bd.Usuario;
import gt.edu.miumg.bd.UsuarioJpaController;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ServiceLog {
    
    EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("gt.edu.miumg_SafeTech2_jar_1.0-SNAPSHOTPU");
    UsuarioJpaController userControl = new UsuarioJpaController(emf);
    
    
    public Usuario login(int user, String password){
        List<Usuario> usuarios = userControl.findUsuarioEntities();
        for(Usuario us : usuarios){
            if (us.getIdUsuario().equals(user) && us.getContraseña().equals(password)) {
                return us;
            }
            
        }
        
        return null;
        
    }
    
}
