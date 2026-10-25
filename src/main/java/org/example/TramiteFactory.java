package org.example;

public class TramiteFactory {
    public static TramiteAcademico crearTramite(String tipo){
        if (tipo.equals("beca")){
            return TramiteBeca.builder().build();
        } else if (tipo.equals("equivalencia")) {
            return TramiteEquivalencia.builder().build();
        } else if (tipo.equals("examen")) {
            return TramiteExamen.builder().build();
        }else{
            return TramiteGeneral.builder().build();
        }
    }
}
