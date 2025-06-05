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


    //El creador tiene estos datos, si cola colalibre es true ingresa a la cola, si no postega
    public Asistente (){
        this.estado = EstadoAsistente.CREADO;
    }

    public void destruir(){
        this.impresora.libre();
        this.estado = EstadoAsistente.DESTRUIDO;
    }

    public void imprimir(Impresora impresora){
        this.impresora = impresora;
        this.impresora.imprimirTicket();
        this.estado = EstadoAsistente.IMPRIMIENDO;
    }

    public void ingresarCola(double horaLlegada){
        this.estado = EstadoAsistente.ESPERANDO;
        this.horallegadaCola = horaLlegada;
    }

    public void postergar(){
        this.estado = EstadoAsistente.VUELTAEN30MIN;
    }
}
