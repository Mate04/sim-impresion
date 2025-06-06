package org.utn.sim.events;
import lombok.Data;
import lombok.ToString;
import org.utn.sim.core.Simulador;
import org.utn.sim.model.Asistente;
import org.utn.sim.model.EstadoAsistente;
import org.utn.sim.model.Impresora;
import org.utn.sim.utils.Utils;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Clase que representa el evento de llegada de un cliente al sistema.
 */

@ToString
@Data
public class LlegadaCliente extends Event {
    private String nombre = "LlegadaCliente";
    // Asistente que ingresa al sistema (puede venir postergado)
    private Asistente asistente;
    private double random;


    /**
     * Constructor para un arribo normal.
     */
    public LlegadaCliente(Simulador simulador, double tiempoActual) {
        this.nombre = "Llegada cliente normal";
        this.random = Math.random();
        this.tiempoUsado = Utils.exponencialNegativa(simulador.getSimulacionRequestDTO().getExpAsistente(), random);
        this.tiempoLlegada = tiempoActual + tiempoUsado;

        this.asistente = new Asistente();
    }

    /**
     * Constructor para un asistente previamente postergado.
     */
    public LlegadaCliente(double tiempoActual, Asistente asistente) {
        this.nombre = "Llegada cliente postergado";
        this.asistente = asistente;
        this.tiempoUsado = 30;
        this.tiempoLlegada = tiempoActual + tiempoUsado;
    }

    /**
     * Maneja la lógica cuando un asistente llega al sistema. Puede ser atendido,
     * pasar a la cola o ser postergado.
     */
    public void execute(Simulador simulador) {

        simulador.setTiempoActual(tiempoLlegada);
        //TODO: si es un asistente postergado no se calcula proximo evento
        if (asistente.getEstado() != EstadoAsistente.VUELTAEN30MIN) {
            simulador.agregarEvento(new LlegadaCliente(simulador, tiempoLlegada));
        }

        Impresora impresoraAsignar = simulador.obtenerImpresoraLibre();

        if (simulador.hayMasDeNEnCola(5)){
            asistente.postergar();
            simulador.getAsistentespostergados().offer(asistente);
            //todo: Hacer logica de crear evento postergado
            simulador.agregarEvento(new LlegadaCliente(tiempoLlegada,asistente));
            simulador.sumarAsistentePostergado();
            return;
        }
        if (impresoraAsignar != null ) {
            if (asistente.getEstado() == EstadoAsistente.VUELTAEN30MIN) {
                simulador.removerAsistentePostergadoPorId(asistente);
            }
            asistente.imprimir(impresoraAsignar);
            simulador.getAsistentesConsumiendoServicio().offer(asistente);
            simulador.agregarEvento(new FinImpresion(simulador, tiempoLlegada, asistente));
            return;
        }
        // no hay mas de 5 en cola y estan todas las puntos de impresion ocupados
        if (asistente.getEstado() == EstadoAsistente.VUELTAEN30MIN){
            simulador.removerAsistentePostergadoPorId(asistente);
        }
        asistente.ingresarCola(tiempoLlegada);
        simulador.sumarAsistentesEstuvieronCola();
        simulador.agregarAsistenteCola(asistente);
    }

}
