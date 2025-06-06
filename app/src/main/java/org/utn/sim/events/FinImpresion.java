package org.utn.sim.events;

import lombok.Data;
import org.utn.sim.core.Simulador;
import org.utn.sim.model.Asistente;
import org.utn.sim.model.EstadoImpresora;
import org.utn.sim.model.EstadoTecnico;
import org.utn.sim.model.Impresora;
import org.utn.sim.utils.Utils;

@Data
public class FinImpresion extends Event{
    private String nombre = "Fin impresion";
    private double random;

    // Asistente cuya impresión finaliza en este evento
    private Asistente asistente;

    /**
     * Constructor que calcula el tiempo de finalización de la impresión.
     */
    public FinImpresion(Simulador simulador, double tiempoActual, Asistente asistente) {
        this.random = Math.random();
        this.tiempoUsado = Utils.uniforme(simulador.getSimulacionRequestDTO().getLimInfTiempoImpresion(),simulador.getSimulacionRequestDTO().getLimSupTiempoImpresion(), random);
        this.tiempoLlegada = tiempoActual + tiempoUsado;
        this.asistente = asistente;
    }

    /**
     * Al ejecutarse libera la impresora y, si hay alguien en la cola, comienza
     * su impresión.
     */
    public void execute(Simulador simulador) {
        Impresora impresora = this.asistente.getImpresora();
        simulador.setTiempoActual(tiempoLlegada);
        simulador.removerAsistentePorId(asistente);
        this.asistente.destruir();
        //todo: logica si el tecnico esta esperando a q se desocupe una maquina y se desocupa
        if (simulador.getTecnico().getEstado() == EstadoTecnico.ESPERANDO_FIN_DE_IMPRESION && !impresora.isMantenimientoSession()){
            simulador.agregarEvento(new FinMantenimiento(simulador,this.tiempoLlegada,impresora));
            simulador.getTecnico().mantener(impresora);
            return;
        }

        Asistente asisteOcupar = simulador.obtenerPrimeroFilaAsistente();
        if (asisteOcupar != null) {
            asisteOcupar.imprimir(impresora);
            simulador.getAsistentesConsumiendoServicio().offer(asisteOcupar);
            simulador.agregarEvento(new FinImpresion(simulador,simulador.getTiempoActual(), asisteOcupar));
            return;
        } else {
            impresora.libre();

        }
        simulador.sumarAsistenteFinalizado();


    }


}
