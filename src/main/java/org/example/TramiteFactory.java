package org.example;

public class TramiteFactory {
    public static TramiteAcademico CrearTramite(String tipo){
        if (tipo.equals("Beca")){
            return TramiteBeca.builder().build();
        } else if (tipo.equals("Equivalencia")) {
            return TramiteEquivalencia.builder().build();
        } else if (tipo.equals("Examen")) {
            return TramiteExamen.builder().build();
        }else{
            return TramiteGeneral.builder().build();
        }
    }
}
