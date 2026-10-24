package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CodigoBase {

    // Simulación de base de datos usando una lista de Strings (Obsesión por Primitivos)
    // Formato guardado: "Nombre,Legajo,Tipo,Descripcion,Estado,Responsable"
    static List<String> baseDeDatosSolicitudes = new ArrayList<>();
    static GestorDeSolicitudes gestor = GestorDeSolicitudes.builder().build();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 5) {
            System.out.println("\n--- Sistema de Solicitudes Académicas UTN ---");
            System.out.println("1. Registrar nueva solicitud");
            System.out.println("2. Asignar responsable y procesar pendientes");
            System.out.println("3. Cambiar estado de una solicitud");
            System.out.println("4. Listar reporte básico");
            System.out.println("5. Salir");
            System.out.print("Elija opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            if (opcion == 1) {
                System.out.print("Nombre del estudiante: ");
                String nom = scanner.nextLine();
                System.out.print("Legajo: ");
                String leg = scanner.nextLine();
                System.out.print("Tipo (Beca, Equivalencia, Examen): ");
                String tip = scanner.nextLine();
                TramiteAcademico Tramite = gestor.registrarTramite(tip);
                System.out.print("Descripcion: ");
                String desc = scanner.nextLine();

                // Llamada con demasiados parámetros
                //registrarSolicitud(nom, leg, tip, desc);
                gestor.registrarSolicitud(nom,leg,Tramite,desc,Estado.PENDIENTE,SectorResponsable.ASUNTOS_ESTUDIANTILES);

            } else if (opcion == 2) {
                procesarYAsignarResponsable();
            } else if (opcion == 3) {
                System.out.print("Ingrese legajo del estudiante: ");
                String leg = scanner.nextLine();
                System.out.print("Nuevo estado (Aprobada/Rechazada): ");
                String est = scanner.nextLine();
                cambiarEstado(leg, est);
            } else if (opcion == 4) {
                generarReporte();
            }
        }
    }

    // CODE SMELL: Demasiados parámetros (Long Parameter List)
    public static void registrarSolicitud(String nombre, String legajo, String tipo, String descripcion, String estado, String responsable) {
        String nuevaSolicitud = nombre + "," + legajo + "," + tipo + "," + descripcion + "," + estado + "," + responsable;
        baseDeDatosSolicitudes.add(nuevaSolicitud);
        System.out.println("Solicitud registrada con éxito.");
    }

    // CODE SMELL: Método Largo y Complejidad Ciclomática (Uso excesivo de IF-ELSE)
    public static void procesarYAsignarResponsable() {
        for (int i = 0; i < baseDeDatosSolicitudes.size(); i++) {
            String[] datos = baseDeDatosSolicitudes.get(i).split(",");
            String tipo = datos[2];
            String estado = datos[4];

            if (estado.equals("Pendiente")) {
                String nuevoResponsable = "";

                // Violación del principio OCP (Open/Closed Principle)
                if (tipo.equals("Beca")) {
                    nuevoResponsable = "Secretaria Asuntos Estudiantiles";
                    System.out.println("Procesando beca para el alumno: " + datos[0]);
                    System.out.println("Legajo asociado: " + datos[1] + " | Tramite: " + datos[2]); // Código duplicado
                } else if (tipo.equals("Equivalencia")) {
                    nuevoResponsable = "Departamento de Alumnos";
                    System.out.println("Procesando equivalencia para el alumno: " + datos[0]);
                    System.out.println("Legajo asociado: " + datos[1] + " | Tramite: " + datos[2]); // Código duplicado
                } else if (tipo.equals("Examen")) {
                    nuevoResponsable = "Secretaria Academica";
                    System.out.println("Procesando fecha de examen para el alumno: " + datos[0]);
                    System.out.println("Legajo asociado: " + datos[1] + " | Tramite: " + datos[2]); // Código duplicado
                } else {
                    nuevoResponsable = "Administración General";
                }

                // Se actualiza el string en la "base de datos"
                baseDeDatosSolicitudes.set(i, datos[0] + "," + datos[1] + "," + datos[2] + "," + datos[3] + ",En Proceso," + nuevoResponsable);
            }
        }
        System.out.println("Todas las solicitudes pendientes han sido asignadas.");
    }

    public static void cambiarEstado(String legajo, String nuevoEstado) {
        for (int i = 0; i < baseDeDatosSolicitudes.size(); i++) {
            String[] datos = baseDeDatosSolicitudes.get(i).split(",");
            if (datos[1].equals(legajo)) {
                baseDeDatosSolicitudes.set(i, datos[0] + "," + datos[1] + "," + datos[2] + "," + datos[3] + "," + nuevoEstado + "," + datos[5]);
                System.out.println("Estado actualizado exitosamente.");
                return;
            }
        }
        System.out.println("No se encontró ninguna solicitud para ese legajo.");
    }

    public static void generarReporte() {
        System.out.println("\n--- Reporte de Solicitudes ---");
        for (String registro : baseDeDatosSolicitudes) {
            String[] datos = registro.split(",");
            System.out.println("Alumno: " + datos[0] + " | Legajo: " + datos[1] + " | Tipo: " + datos[2] + " | Estado: " + datos[4] + " | Responsable: " + datos[5]);
        }
    }
}