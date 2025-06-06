package org.utn.sim.dto;

/**
 * DTO utilizado para recibir los parámetros de la simulación desde el
 * frontend.
 */
public class SimulacionRequestDTO {
    private double tiempo;
    private double inicio;
    private int iteraciones;

    public SimulacionRequestDTO() {}

    public double getTiempo() {
        return tiempo;
    }

    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

    public double getInicio() {
        return inicio;
    }

    public void setInicio(double inicio) {
        this.inicio = inicio;
    }

    public int getIteraciones() {
        return iteraciones;
    }

    public void setIteraciones(int iteraciones) {
        this.iteraciones = iteraciones;
    }
}