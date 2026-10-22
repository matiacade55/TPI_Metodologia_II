package org.example;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Solicitud {
    private TramiteAcademico tramiteAcademico;
    private String prioridad;
    private Estado estado;
    private SectorResponsable responsable;
    private Estudiante estudiante;
}
