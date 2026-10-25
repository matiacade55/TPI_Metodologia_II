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
        if(solicitud==null){
            throw new IllegalArgumentException("No se encontro una solicitud para guardar.");
        }
        this.listaSolicitudes.add(solicitud);
    }
    
    public Solicitud buscarPorId(Long id){
        Optional<Solicitud> solicitudEncontrada = listaSolicitudes.stream()
                .filter(solicitud -> solicitud.getId().equals(id))
                .findFirst();
        return solicitudEncontrada.orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));
    }


    @Override
    public List<Solicitud> listar() {return listaSolicitudes;}
}
