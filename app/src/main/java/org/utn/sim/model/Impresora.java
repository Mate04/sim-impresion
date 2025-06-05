package org.utn.sim.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Impresora {
    private EstadoImpresora estado;
    private boolean mantenimientoSession;

    public Impresora() {
        this.estado = EstadoImpresora.LIBRE;
        this.mantenimientoSession = false;
    }

    public void imprimirTicket(){
        this.estado = EstadoImpresora.OCUPADO;
    }

    public void libre(){
        this.estado = EstadoImpresora.LIBRE;
    }

    public void realizarMantenimiento(){
        this.estado = EstadoImpresora.EN_MANTENIMIENTO;
    }

    public boolean estaLibre(){
        return this.estado == EstadoImpresora.LIBRE;
    }


}








