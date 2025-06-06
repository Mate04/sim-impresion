package org.utn.sim.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SimulacionConDatosAdicionalesDTO {
    private List<SimulacionDTO> simulacion;
    private double tiempoPromedioColaAsistente;
    private double porcentajeAsistentesQueSeVan;
}
