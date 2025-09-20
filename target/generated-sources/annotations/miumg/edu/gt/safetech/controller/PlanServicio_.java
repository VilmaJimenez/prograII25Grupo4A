package miumg.edu.gt.safetech.controller;

import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import miumg.edu.gt.safetech.controller.Cliente;
import miumg.edu.gt.safetech.controller.PlanAgente;
import miumg.edu.gt.safetech.controller.Servicio;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-09-19T20:02:09", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(PlanServicio.class)
public class PlanServicio_ { 

    public static volatile SingularAttribute<PlanServicio, Integer> idPlan;
    public static volatile SingularAttribute<PlanServicio, String> descripcion;
    public static volatile SingularAttribute<PlanServicio, BigDecimal> precio;
    public static volatile SingularAttribute<PlanServicio, Cliente> idCliente;
    public static volatile SingularAttribute<PlanServicio, String> nombrePlan;
    public static volatile ListAttribute<PlanServicio, Servicio> servicioList;
    public static volatile ListAttribute<PlanServicio, PlanAgente> planAgenteList;

}