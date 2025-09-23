package miumg.edu.gt.safetech.controller;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import miumg.edu.gt.safetech.controller.Cliente;
import miumg.edu.gt.safetech.controller.Detalle;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-09-22T22:57:47", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Factura.class)
public class Factura_ { 

    public static volatile SingularAttribute<Factura, Date> fecha;
    public static volatile SingularAttribute<Factura, BigDecimal> total;
    public static volatile SingularAttribute<Factura, Cliente> idCliente;
    public static volatile ListAttribute<Factura, Detalle> detalleList;
    public static volatile SingularAttribute<Factura, Integer> idFactura;

}