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
        System.out.println(solicitud1);
        System.out.println("==================================");
        System.out.println(solicitud1.getTramiteAcademico().asignar_sector());

        // PARA LA PROXIMA
        // Interfaz para guardar las cosas y la clase que la implemente.
        // registrarSolicitud -> Metodo para instanciar las solicitudes y usar el objeto de guardado.
        // procesarYAsignarResponsable me parece que no va a existir en la version final, no tiene uso.
        // cambiarEstado cambiar a solo buscar por legajo.
        // generarReporte reacer con nuestra logica.
        }
}
