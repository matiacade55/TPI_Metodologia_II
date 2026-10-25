package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RepositorioEnMemoria implements RepositorioSolicitudes{
    @Builder.Default
    private List<Solicitud> listaSolicitudes = new ArrayList<>() ;


    @Override
    public void guardar(Solicitud solicitud) {
        this.listaSolicitudes.add(solicitud);
        System.out.println("Solicitud guardada.");
        System.out.println("LAS SOLICITUDES ACTUALES SON: ");
        listar();
    }
    public Solicitud buscarPorId(Long id){
        Optional<Solicitud> solicitudEncontrada = listaSolicitudes.stream()
                .filter(solicitud -> solicitud.getId().equals(id))
                .findFirst();
        return solicitudEncontrada.orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));
    }


    @Override
    public void listar() {
        for (Solicitud solicitud: listaSolicitudes){
            System.out.println(solicitud.toString());
        }
    }
}
