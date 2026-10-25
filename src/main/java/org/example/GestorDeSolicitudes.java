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
        Solicitud solicitudEncontrada = baseDeDatos.buscarPorId(id);
        solicitudEncontrada.setResponsable(responsable);
    }

    public void cambiarEstado(Long id, Estado estado){
        Solicitud solicitudEncontrada = baseDeDatos.buscarPorId(id);
        solicitudEncontrada.setEstado(estado);
    }

    public void generarReporte() {
        System.out.println("\n--- Reporte de Solicitudes ---");
        baseDeDatos.listar();
    }

}
