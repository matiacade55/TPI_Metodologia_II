package org.example;


import java.util.List;

public interface RepositorioSolicitudes {
    public void guardar(Solicitud solicitud);
    public List<Solicitud> listar();
    public Solicitud buscarPorId(Long id);
}
