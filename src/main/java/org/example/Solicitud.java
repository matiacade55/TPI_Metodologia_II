package org.example;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Solicitud {
    private Long id;
    private Estudiante estudiante;
    private TramiteAcademico tramiteAcademico;
    private String prioridad;
    private Estado estado;
    private SectorResponsable responsable;



}
