package miumg.edu.gt.safetech.controller;

import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import miumg.edu.gt.safetech.controller.Detalle;
import miumg.edu.gt.safetech.controller.PlanServicio;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-09-19T20:02:09", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Servicio.class)
public class Servicio_ { 

    public static volatile SingularAttribute<Servicio, String> descripcion;
    public static volatile SingularAttribute<Servicio, PlanServicio> idPlan;
    public static volatile SingularAttribute<Servicio, BigDecimal> precio;
    public static volatile ListAttribute<Servicio, Detalle> detalleList;
    public static volatile SingularAttribute<Servicio, Integer> idServicio;

}