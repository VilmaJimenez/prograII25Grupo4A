package gt.edu.miumg.bd.ServiceDB;

import gt.edu.miumg.bd.Factura;
import gt.edu.miumg.bd.FacturaJpaController;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FacturaLog {

    EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("gt.edu.miumg_SafeTech2_jar_1.0-SNAPSHOTPU");
    FacturaJpaController facturaControl = new FacturaJpaController(emf);

    public Factura buscarFactura(int idFactura) {
        List<Factura> facturas = facturaControl.findFacturaEntities();
        for (Factura f : facturas) {
            if (f.getIdFactura().equals(idFactura)) {
                return f;
            }
        }
        return null;
    }

}
