package org.utn.sim.events;
import lombok.Getter;
import org.utn.sim.core.Simulador;
@Getter
public abstract class Event implements Comparable<Event>{
    protected double tiempoUsado;
    protected double tiempoLlegada;

    public Event(){};

    /**
     * Ejecuta la lógica del evento sobre el simulador.
     */
    public abstract void execute(Simulador simulador);

    @Override
    public int compareTo(Event other) {
        // Ordenamos por tiempo de llegada para priorizar el evento más próximo
        return Double.compare(this.tiempoLlegada, other.tiempoLlegada);
    }
}
