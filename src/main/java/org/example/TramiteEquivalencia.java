package org.example;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class TramiteEquivalencia implements TramiteAcademico{
    @Override
    public SectorResponsable asignar_sector() {
        return SectorResponsable.DEPARTAMENTO_ALUMNOS;

    }
    public String toString(){
        return "Equivalencia";
    }
}
