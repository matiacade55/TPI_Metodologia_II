package org.example;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TramiteExamen implements TramiteAcademico{
    @Override
    public SectorResponsable asignar_sector() {
        return SectorResponsable.SECRETARIA_ACADEMICA;
    }

    public String toString(){
        return "Examen";
    }
}
