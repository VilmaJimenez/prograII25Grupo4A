package gt.edu.miumg.bd;

import gt.edu.miumg.bd.Cliente;
import gt.edu.miumg.bd.Detalle;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-09-26T20:47:24", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Factura.class)
public class Factura_ { 

    public static volatile SingularAttribute<Factura, Date> fecha;
    public static volatile SingularAttribute<Factura, BigDecimal> total;
    public static volatile SingularAttribute<Factura, Cliente> idCliente;
    public static volatile ListAttribute<Factura, Detalle> detalleList;
    public static volatile SingularAttribute<Factura, Integer> idFactura;

}