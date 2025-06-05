package org.utn.sim.events;
import lombok.Getter;
import org.utn.sim.core.Simulador;
@Getter
public abstract class Event implements Comparable<Event>{
    protected double tiempoUsado;
    protected double tiempoLlegada;

    public Event(){};

    public abstract void execute(Simulador simulador);

    @Override
    public int compareTo(Event other) {
        return Double.compare(this.tiempoLlegada, other.tiempoLlegada);
    }
}
