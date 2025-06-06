package org.utn.sim.dto;

import lombok.Data;

@Data
public class SimulacionRequestDTO {
    private double tiempo;
    private double inicio;
    private int iteraciones;
    private int limInfTiempoImpresion;
    private int limSupTiempoImpresion;
    private int expAsistente;
    private int limInfTiempoFinDescanso;
    private int limSupTiempoFinDescanso;
    private int limInfTiempoMantenimiento;
    private int limSupTiempoMantenimiento;

    public SimulacionRequestDTO() {}

}