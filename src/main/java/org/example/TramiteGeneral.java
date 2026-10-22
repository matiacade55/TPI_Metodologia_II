package org.example;

public class TramiteGeneral implements TramiteAcademico{

    @Override
    public SectorResponsable asignar_sector() {
        return SectorResponsable.ADMINISTRACION_GENERAL;
    }
    public String toString(){
        return "General";
    }
}
