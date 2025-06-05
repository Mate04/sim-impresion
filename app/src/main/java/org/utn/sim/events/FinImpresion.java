package org.utn.sim.events;

import org.utn.sim.core.Simulador;
import org.utn.sim.model.Asistente;
import org.utn.sim.model.Impresora;
import org.utn.sim.utils.Utils;

public class FinImpresion extends Event{
    private String nombre = "Fin impresion";

    // Asistente cuya impresión finaliza en este evento
    private Asistente asistente;

    /**
     * Constructor que calcula el tiempo de finalización de la impresión.
     */
    public FinImpresion(double tiempoActual, Asistente asistente) {
        this.tiempoUsado = Utils.uniforme(5,8);
        this.tiempoLlegada = tiempoActual + tiempoUsado;
        this.asistente = asistente;

    }

    /**
     * Al ejecutarse libera la impresora y, si hay alguien en la cola, comienza
     * su impresión.
     */
    public void execute(Simulador simulador) {
        System.out.println("nombre: " + nombre + ", hora actual: " + tiempoLlegada+ " Asistente en cola " + simulador.getColaAsistentes().size());
        simulador.sumarAsistenteFinalizado();
        Impresora impresora = this.asistente.getImpresora();
        simulador.setTiempoActual(tiempoLlegada);
        this.asistente.destruir();
        Asistente asisteOcupar = simulador.obtenerPrimeroFilaAsistente();
        if (asisteOcupar != null) {
            asisteOcupar.imprimir(impresora);
            simulador.agregarEvento(new FinImpresion(simulador.getTiempoActual(), asisteOcupar));
            return;
        } else {
            impresora.libre();

        }


    }


}
