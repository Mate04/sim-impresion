package org.utn.sim.events;
import lombok.Data;
import lombok.ToString;
import org.utn.sim.core.Simulador;
import org.utn.sim.model.Asistente;
import org.utn.sim.model.EstadoAsistente;
import org.utn.sim.model.Impresora;
import org.utn.sim.utils.Utils;

/**
 * Clase que representa el evento de llegada de un asistente al sistema de
 * impresión.
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
    public LlegadaCliente(double tiempoActual) {
        this.nombre = "Llegada cliente normal";
        this.random = Math.random();
        this.tiempoUsado = Utils.exponencialNegativa(2, random);
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
            simulador.agregarEvento(new LlegadaCliente(tiempoLlegada));
        }
        Impresora impresoraAsignar = simulador.obtenerImpresoraLibre();

        if (simulador.hayMasDeNEnCola(5)){
            asistente.postergar();
            //todo: Hacer logica de crear evento postergado
            simulador.agregarEvento(new LlegadaCliente(tiempoLlegada,asistente));
            simulador.sumarAsistentePostergado();
            return;
        }
        if (impresoraAsignar != null ) {
            asistente.imprimir(impresoraAsignar);
            simulador.agregarEvento(new FinImpresion(tiempoLlegada, asistente));
            return;
        }
        asistente.ingresarCola(tiempoLlegada);
        simulador.sumarAsistentesEstuvieronCola();
        simulador.agregarAsistenteCola(asistente);
    }

}
