package org.example;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class TramiteBeca implements TramiteAcademico{
    @Override
    public SectorResponsable asignar_sector() {
        return SectorResponsable.ASUNTOS_ESTUDIANTILES;

    }
    public String toString(){
        return "Beca";
    }
}
