package gt.edu.miumg.bd;

import gt.edu.miumg.bd.Detalle;
import gt.edu.miumg.bd.PlanServicio;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-10-04T16:19:12", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Servicio.class)
public class Servicio_ { 

    public static volatile SingularAttribute<Servicio, String> descripcion;
    public static volatile ListAttribute<Servicio, PlanServicio> planServicioList;
    public static volatile SingularAttribute<Servicio, BigDecimal> precio;
    public static volatile ListAttribute<Servicio, Detalle> detalleList;
    public static volatile SingularAttribute<Servicio, Integer> idServicio;

}