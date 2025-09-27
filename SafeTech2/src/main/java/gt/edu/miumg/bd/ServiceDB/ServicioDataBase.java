
package gt.edu.miumg.bd.ServiceDB;

import gt.edu.miumg.bd.Servicio;
import gt.edu.miumg.bd.ServicioJpaController;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ServicioDataBase {
    
    EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("gt.edu.miumg_SafeTech2_jar_1.0-SNAPSHOTPU");
    ServicioJpaController servicio = new ServicioJpaController(emf);
    
    
    public Servicio crearServicio(){
        
    }
    
}
