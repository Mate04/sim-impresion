package org.utn.sim.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Impresora {
    private EstadoImpresora estado;
    private double horaInicioImpresion;
    private boolean mantenimientoSession;

    public Impresora() {
        this.estado = EstadoImpresora.LIBRE;
        this.horaInicioImpresion = 0.0;
        this.mantenimientoSession = false;
    }

    public void imprimirTicket(double hora){
        this.estado = EstadoImpresora.OCUPADO;
        this.horaInicioImpresion = hora;
    }

    public void libre(){
        this.estado = EstadoImpresora.LIBRE;
    }

    public void realizarMantenimiento(){
        this.estado = EstadoImpresora.EN_MANTENIMIENTO;
    }
}
