package org.example;


public class Main {
    static void main() {
        Estudiante estudiante1 = Estudiante.builder()
                .nombre("pepe")
                .legajo("0001")
                .build();
        Solicitud solicitud1= Solicitud.builder()
                .estudiante(estudiante1)
                .estado(Estado.PENDIENTE)
                .prioridad("ALTA")
                .build();
        System.out.println(solicitud1.toString());

        TramiteBeca beca = TramiteBeca.builder().build();
        solicitud1.setTramiteAcademico(beca);
        System.out.println("==================================");
        System.out.println(solicitud1.getTramiteAcademico().toString());
        System.out.println("==================================");
        System.out.println(solicitud1.getTramiteAcademico().asignar_sector());

        }
}
