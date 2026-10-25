package org.example;

import java.util.Scanner;

public class Main {

    static GestorDeSolicitudes gestor = GestorDeSolicitudes.builder().build();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 5) {
            System.out.println("\n--- Sistema de Solicitudes Académicas UTN ---");
            System.out.println("1. Registrar nueva solicitud");
            System.out.println("2. Cambiar Responsable");
            System.out.println("3. Cambiar estado de una solicitud");
            System.out.println("4. Listar reporte básico");
            System.out.println("5. Salir");
            System.out.print("Elija opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            if (opcion == 1) {
                System.out.println("Ingrese id de la solicitud: ");
                Long id = Long.parseLong(scanner.nextLine());
                System.out.print("Nombre del estudiante: ");
                String nombre = scanner.nextLine();
                System.out.print("Legajo: ");
                String legajo = scanner.nextLine();
                System.out.print("Tipo (Beca, Equivalencia, Examen): ");
                String tipoTramite = scanner.nextLine();
                TramiteAcademico Tramite = TramiteFactory.CrearTramite(tipoTramite);
                System.out.println("Prioridad (Alta, Baja)");
                String prioridad = scanner.nextLine();

                // instanciando los objetos Estudiante y solicitud en el menu.
                Estudiante estudiante = Estudiante.builder()
                        .nombre(nombre)
                        .legajo(legajo)
                        .build();
                Solicitud solicitud = Solicitud.builder()
                        .id(id)
                        .estudiante(estudiante)
                        .tramiteAcademico(Tramite)
                        .prioridad(prioridad)
                        .estado(Estado.PENDIENTE)
                        .responsable(Tramite.asignar_sector())
                        .build();
                // llamada al gestor para guardar la solicitud en la base de datos.
                gestor.registrarSolicitud(solicitud);

            } else if (opcion == 2) {
                System.out.println("Indique el id de la solicitud a modificar");
                Long id = scanner.nextLong();

                SectorResponsable[] opciones = SectorResponsable.values();
                System.out.println("Elija el sector responsable.");
                for(int i = 0; i< opciones.length; i++){
                    System.out.println((i + 1) + " . " + opciones[i]);
                }
                int eleccion = scanner.nextInt();
                SectorResponsable sectorElegido = opciones[eleccion-1];
                System.out.println("SE ELIGIO "+sectorElegido);


                gestor.cambiarResponsable(id,sectorElegido);

            } else if (opcion == 3) {
                System.out.println("Indique el id de la solicitud a modificar");
                Long id = scanner.nextLong();

                Estado[] opciones = Estado.values();
                System.out.println("Elija el Estado a seleccionar.");
                for(int i = 0; i< opciones.length; i++){
                    System.out.println((i + 1) + " . " + opciones[i]);
                }
                int eleccion = scanner.nextInt();
                Estado estadoElegido = opciones[eleccion-1];
                System.out.println("SE ELIGIO "+ estadoElegido);

                gestor.cambiarEstado(id,estadoElegido);
            } else if (opcion == 4) {
                gestor.generarReporte();
            }
        }
    }

}