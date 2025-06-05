package org.utn.sim.events;
import lombok.Data;
import lombok.ToString;
import org.utn.sim.core.Simulador;
import org.utn.sim.model.Asistente;
import org.utn.sim.model.EstadoAsistente;
import org.utn.sim.model.Impresora;
import org.utn.sim.utils.Utils;

/**
 * Clase que representa el evento de llegada de un cliente al sistema.
 */

@ToString()
@Data
public class LlegadaCliente extends Event {
    private String nombre = "LlegadaCliente";
    //Si no es postergado el asistente esta null
    private Asistente asistente;
    //Constructor para asistente normal
    public LlegadaCliente(double tiempoActual) {
        this.tiempoUsado = Utils.exponencialNegativa(2);
        this.tiempoLlegada = tiempoActual + tiempoUsado;
        this.asistente = new Asistente();
    }
    //Contructor para el evento postergador
    public LlegadaCliente(double tiempoActual, Asistente asistente) {
        this.asistente = asistente;
        this.tiempoUsado = 30;
        this.tiempoLlegada = tiempoActual + tiempoUsado;
    }

    //todo: Aca se implementa la logica de lo que sucede cuando llega un cliente
    public void execute(Simulador simulador) {
        System.out.println("Llegada Cliente, tiempo: " + tiempoLlegada + " Asistente en cola " + simulador.getColaAsistentes().size()) ;

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
