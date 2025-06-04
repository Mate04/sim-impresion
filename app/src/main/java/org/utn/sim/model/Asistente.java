package org.utn.sim.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Asistente {
    private EstadoAsistente estado;
    private double horallegada;
    private double horavuelta;

    @Override
    public String toString() {
        return "Asistente{" +
                "estado=" + estado +
                ", horallegada=" + horallegada +
                ", horavuelta=" + horavuelta +
                '}';
    }

    public Asistente (double horallegada){
        this.horallegada = horallegada;
        this.estado = EstadoAsistente.CREADO;
    }

    public void destruir(){
        this.estado = EstadoAsistente.DESTRUIDO;
    }

    public void imprimir(){
        this.estado = EstadoAsistente.CREADO;
    }

    public void ingresarCola(){
        this.estado = EstadoAsistente.ESPERANDO;
    }

    public void postergar(double horavuelta){
        this.estado = EstadoAsistente.VUELTAEN30MIN;
    }
}
