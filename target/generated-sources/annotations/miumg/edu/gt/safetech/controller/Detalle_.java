package miumg.edu.gt.safetech.controller;

import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import miumg.edu.gt.safetech.controller.Factura;
import miumg.edu.gt.safetech.controller.Servicio;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-09-22T22:57:47", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Detalle.class)
public class Detalle_ { 

    public static volatile SingularAttribute<Detalle, Integer> idDetalle;
    public static volatile SingularAttribute<Detalle, BigDecimal> subtotal;
    public static volatile SingularAttribute<Detalle, Factura> idFactura;
    public static volatile SingularAttribute<Detalle, Integer> cantidad;
    public static volatile SingularAttribute<Detalle, Servicio> idServicio;

}