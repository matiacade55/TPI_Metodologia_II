package org.example;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class GestorDeSolicitudes {
    @Builder.Default
    RepositorioSolicitudes baseDeDatos = RepositorioEnMemoria.builder().build();

    public void registrarSolicitud (Solicitud solicitud){
        baseDeDatos.guardar(solicitud);
    }

    public void cambiarResponsable(Long id, SectorResponsable responsable){
        if(responsable != null){
            Solicitud solicitudEncontrada = baseDeDatos.buscarPorId(id);
            if(solicitudEncontrada.getEstado() == Estado.APROBADO || solicitudEncontrada.getEstado() == Estado.RECHAZADO){
                throw new RuntimeException("No se puede cambiar el Responsable de una solicitud APROBADA o RECHAZADA");
            }
            solicitudEncontrada.setResponsable(responsable);
        }else{
            throw new IllegalArgumentException("El responsable no puede ser nulo.");
        }
    }

    public void cambiarEstado(Long id, Estado estado){
        if(estado != null){
            Solicitud solicitudEncontrada = baseDeDatos.buscarPorId(id);
            solicitudEncontrada.setEstado(estado);
        }else{
            throw new IllegalArgumentException("El estado no puede ser nulo.");
        }
    }

    public void generarReporte() {
        System.out.println("\n--- Reporte de Solicitudes ---");
        baseDeDatos.listar();
    }

}
