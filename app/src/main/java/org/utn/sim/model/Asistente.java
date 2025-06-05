package org.utn.sim.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
public class Asistente {
    private EstadoAsistente estado;
    private double horallegadaCola = 0;
    private Impresora impresora;


    /**
     * Crea un asistente en estado inicial.
     */
    public Asistente (){
        this.estado = EstadoAsistente.CREADO;
    }

    /**
     * Libera la impresora asociada y marca al asistente como destruido.
     */
    public void destruir(){
        this.impresora.libre();
        this.estado = EstadoAsistente.DESTRUIDO;
    }

    /**
     * Asigna una impresora y cambia el estado a imprimiendo.
     */
    public void imprimir(Impresora impresora){
        this.impresora = impresora;
        this.impresora.imprimirTicket();
        this.estado = EstadoAsistente.IMPRIMIENDO;
    }

    /**
     * Coloca al asistente en la cola registrando la hora de ingreso.
     */
    public void ingresarCola(double horaLlegada){
        this.estado = EstadoAsistente.ESPERANDO;
        this.horallegadaCola = horaLlegada;
    }

    /**
     * Marca al asistente para que vuelva en 30 minutos.
     */
    public void postergar(){
        this.estado = EstadoAsistente.VUELTAEN30MIN;
    }
}
