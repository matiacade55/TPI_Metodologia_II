package org.example;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class Estudiante {
    private String nombre;
    private String legajo;
}
