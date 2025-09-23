package miumg.edu.gt.safetech.controller;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import miumg.edu.gt.safetech.controller.PlanAgente;
import miumg.edu.gt.safetech.controller.Usuario;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-09-22T22:57:47", comments="EclipseLink-2.7.10.v20211216-rNA")
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