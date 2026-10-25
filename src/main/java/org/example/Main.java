package org.example;

import java.util.InputMismatchException;
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

            opcion = leerEntero("Elegi una opcion: ",0,5,scanner);

            if (opcion == 1) {
                Long id = (long) leerEntero("Ingrese id de la solicitud: ",0,999,scanner);
                String nombre = leerTexto("Nombre del estudiante: ",scanner);
                String legajo = leerTexto("Legajo: ",scanner);
                String tipoTramite = leerTexto("Tipo (Beca, Equivalencia, Examen): ",scanner).toLowerCase();
                TramiteAcademico Tramite = TramiteFactory.CrearTramite(tipoTramite);
                String prioridad;
                do {
                     prioridad = leerTexto("Selecciona la prioridad (alta, baja)",scanner).toLowerCase();

                }while (!prioridad.equals("alta") && !prioridad.equals("baja") );


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

                Long id = (long) leerEntero("Ingrese id de la solicitud: ",0,999,scanner);
                SectorResponsable[] opciones = SectorResponsable.values();
                SectorResponsable sectorElegido = seleccionarOpcionEnum("Indique el Sector responsable",opciones,scanner); //Metodo seleccion de Enums.
                System.out.println("El sector elegido es: "+ sectorElegido);

                try{
                    gestor.cambiarResponsable(id,sectorElegido); //Metodo cambiarResponsable de las Solicitudes.
                }catch (RuntimeException e){
                    System.out.println("ERROR en la operacion " + e.getMessage());}


            } else if (opcion == 3) {

                Long id = (long) leerEntero("Ingrese id de la solicitud: ",0,999,scanner);
                Estado[] opciones = Estado.values();
                Estado estadoElegido = seleccionarOpcionEnum("Elija el Estado a seleccionar.",opciones,scanner); //Metodo seleccion de Enums.
                System.out.println("El estado elegido es: "+ estadoElegido);

                gestor.cambiarEstado(id,estadoElegido); //Metodo cambiarResponsable de las Solicitudes.
            } else if (opcion == 4) {
                gestor.generarReporte();
            }
        }
    }
    // El <T extends Enum<T>> le dice a Java: "Este metodo acepta cualquier clase que sea un Enum"
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
    // Validar y leer un número entero dentro de un rango.
    public static int leerEntero(String mensaje, int min, int max,Scanner scanner) {
        while (true) {
            try {
                System.out.print(mensaje);
                int numero = scanner.nextInt();
                scanner.nextLine();

                if (numero >= min && numero <= max) {
                    return numero;
                }
                System.out.println("Error: La opcion tiene que estar entre " + min + " y " + max + ".\n");

            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un numero válido (no letras ni simbolos).\n");
                scanner.nextLine();
            }
        }
    }

    // Validar y leer un texto que no estE vacío
    public static String leerTexto(String mensaje,Scanner scanner) {
        while (true) {
            System.out.print(mensaje);
            String texto = scanner.nextLine().trim();

            if (!texto.isBlank()) {
                return texto; // ¡Válido! Lo devuelve
            }
            System.out.println("Error: Este campo no puede estar vacío.\n");
        }
    }

}