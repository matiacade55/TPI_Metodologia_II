package org.example;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public void listar() {
        for (Solicitud solicitud: listaSolicitudes){
            System.out.println(solicitud.toString());
        }
    }
}
