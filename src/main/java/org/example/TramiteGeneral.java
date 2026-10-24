package org.example;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TramiteGeneral implements TramiteAcademico{

    @Override
    public SectorResponsable asignar_sector() {
        return SectorResponsable.ADMINISTRACION_GENERAL;
    }
    public String toString(){
        return "General";
    }
}
