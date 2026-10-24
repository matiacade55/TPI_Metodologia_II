package org.example;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class GestorDeSolicitudes {
    @Builder.Default
    RepositorioEnMemoria BaseDeDatos = RepositorioEnMemoria.builder().build();

    public TramiteAcademico registrarTramite(String tramite){
        if (tramite.equals("Beca")){
            return TramiteBeca.builder().build();
        } else if (tramite.equals("Equivalencia")) {
            return TramiteEquivalencia.builder().build();
        } else if (tramite.equals("Examen")) {
            return TramiteExamen.builder().build();
        }else{
            return TramiteGeneral.builder().build();
        }
    }

    public void registrarSolicitud(String nombreEstudiante, String legajoEstudiante, TramiteAcademico tipoTramite, String descripcion,Estado estado , SectorResponsable sectorResponsable){
      Estudiante estudiante = Estudiante.builder()
              .nombre(nombreEstudiante)
              .legajo(legajoEstudiante)
              .build();
      Solicitud solicitud = Solicitud.builder()
              .tramiteAcademico(tipoTramite)
              .descripcion(descripcion)
              .estado(estado)
              .responsable(sectorResponsable)
              .estudiante(estudiante)
              .build();

      BaseDeDatos.guardar(solicitud);
    }

}
