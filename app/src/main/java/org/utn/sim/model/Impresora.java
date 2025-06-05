package org.utn.sim.model;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@ToString
@Getter
public class Impresora {
    private EstadoImpresora estado;
    private boolean mantenimientoSession;

    /**
     * Crea una impresora en estado libre sin mantenimiento pendiente.
     */
    public Impresora() {
        this.estado = EstadoImpresora.LIBRE;
        this.mantenimientoSession = false;
    }

    /** Cambia el estado a ocupado indicando que está imprimiendo. */
    public void imprimirTicket(){
        this.estado = EstadoImpresora.OCUPADO;
    }

    /** Marca la impresora como libre. */
    public void libre(){
        this.estado = EstadoImpresora.LIBRE;
    }

    /** Pone la impresora en mantenimiento. */
    public void realizarMantenimiento(){
        this.mantenimientoSession = true;
        this.estado = EstadoImpresora.EN_MANTENIMIENTO;
    }

    /**
     * Indica si la impresora está disponible para usar.
     *
     * @return {@code true} si su estado es LIBRE
     */
    public boolean estaLibre(){
        return this.estado == EstadoImpresora.LIBRE;
    }


}








