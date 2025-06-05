package org.utn.sim.events;
import lombok.Data;
import lombok.ToString;
import org.utn.sim.core.Simulador;
import org.utn.sim.model.Asistente;
import org.utn.sim.model.Impresora;
import org.utn.sim.utils.Utils;

/**
 * Clase que representa el evento de llegada de un cliente al sistema.
 */

@ToString()
@Data
public class LlegadaCliente extends Event {
    private String nombre = "LlegadaCliente";

    public LlegadaCliente(double tiempoActual) {
        this.tiempoUsado = Utils.exponencialNegativa(2);
        this.tiempoLlegada = tiempoActual + tiempoUsado;
    }

    //todo: Aca se implementa la logica de lo que sucede cuando llega un cliente
    public void execute(Simulador simulador) {
        System.out.println("Ejecutando evento: " + nombre + " a las " + tiempoLlegada);
        simulador.setTiempoActual(tiempoLlegada);
        simulador.agregarEvento(new LlegadaCliente(tiempoLlegada));
        Impresora impresoraAsignar = simulador.obtenerImpresoraLibre();
        Asistente asistente = new Asistente(tiempoLlegada);
        if (simulador.hayMasDeNEnCola(100000000)){
            asistente.postergar();
            //todo: Hacer logica de crear evento postergado
            return;
        }
        if (impresoraAsignar != null) {
            impresoraAsignar.imprimirTicket();
            asistente.imprimir();
        } else {
            asistente.ingresarCola();
            simulador.agregarAsistenteCola(asistente);
        }
    }

}
