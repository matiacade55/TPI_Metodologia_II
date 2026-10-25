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
                //Seleccion de id de la solicitud a modificar.
                System.out.println("Indique el id de la solicitud a modificar");
                Long id = scanner.nextLong();
                //Seleccion del Enum de sector responsable.
                String mensaje ="Indique el Sector responsable";
                SectorResponsable[] opciones = SectorResponsable.values();
                SectorResponsable sectorElegido = seleccionarOpcionEnum(mensaje,opciones,scanner); //Metodo seleccion de Enums.
                System.out.println("El sector elegido es: "+ sectorElegido);

                gestor.cambiarResponsable(id,sectorElegido); //Metodo cambiarResponsable de las Solicitudes.

            } else if (opcion == 3) {
                //Seleccion de id de la solicitud a modificar.
                System.out.println("Indique el id de la solicitud a modificar");
                Long id = scanner.nextLong();
                //Seleccion del Enum de Estado.
                String mensaje = "Elija el Estado a seleccionar.";
                Estado[] opciones = Estado.values();
                Estado estadoElegido = seleccionarOpcionEnum(mensaje,opciones,scanner); //Metodo seleccion de Enums.
                System.out.println("El estado elegido es: "+ estadoElegido);

                gestor.cambiarEstado(id,estadoElegido); //Metodo cambiarResponsable de las Solicitudes.
            } else if (opcion == 4) {
                gestor.generarReporte();
            }
        }
    }
    // El <T extends Enum<T>> le dice a Java: "Este método acepta cualquier clase que sea un Enum"
    public static <T extends Enum<T>> T seleccionarOpcionEnum(String mensaje, T[] opciones, Scanner scanner) {
        System.out.println(mensaje);
        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i + 1) + ". " + opciones[i]);
        }
        System.out.print("Elección: ");
        int eleccion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer para evitar saltos de línea inesperados

        return opciones[eleccion - 1];
    }

}