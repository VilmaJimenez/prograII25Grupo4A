package gt.edu.miumg.bd;

import gt.edu.miumg.bd.Cliente;
import gt.edu.miumg.bd.PlanAgente;
import gt.edu.miumg.bd.Servicio;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-09-26T20:47:24", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(PlanServicio.class)
public class PlanServicio_ { 

    public static volatile SingularAttribute<PlanServicio, Integer> idPlan;
    public static volatile SingularAttribute<PlanServicio, String> descripcion;
    public static volatile SingularAttribute<PlanServicio, BigDecimal> precio;
    public static volatile SingularAttribute<PlanServicio, Cliente> idCliente;
    public static volatile SingularAttribute<PlanServicio, String> nombrePlan;
    public static volatile SingularAttribute<PlanServicio, Servicio> idServicio;
    public static volatile ListAttribute<PlanServicio, PlanAgente> planAgenteList;

}