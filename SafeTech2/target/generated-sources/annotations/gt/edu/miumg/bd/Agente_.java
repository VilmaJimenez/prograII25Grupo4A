package gt.edu.miumg.bd;

import gt.edu.miumg.bd.PlanAgente;
import gt.edu.miumg.bd.Usuario;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-10-04T16:44:23", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Agente.class)
public class Agente_ { 

    public static volatile SingularAttribute<Agente, Boolean> estado;
    public static volatile SingularAttribute<Agente, String> residencia;
    public static volatile SingularAttribute<Agente, String> apellido;
    public static volatile SingularAttribute<Agente, Usuario> idUsuario;
    public static volatile SingularAttribute<Agente, Integer> idAgente;
    public static volatile SingularAttribute<Agente, Integer> telefono;
    public static volatile ListAttribute<Agente, PlanAgente> planAgenteList;
    public static volatile SingularAttribute<Agente, String> nombre;
    public static volatile SingularAttribute<Agente, Integer> edad;

}