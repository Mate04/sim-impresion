package org.utn.sim.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
public class Asistente {
    private EstadoAsistente estado;
    private double horallegadaCola;


    //El creador tiene estos datos, si cola colalibre es true ingresa a la cola, si no postega
    public Asistente (double horallegadaCola){
        this.horallegadaCola = horallegadaCola;
        this.estado = EstadoAsistente.CREADO;
    }

    public void destruir(){
        this.estado = EstadoAsistente.DESTRUIDO;
    }

    public void imprimir(){
        this.estado = EstadoAsistente.IMPRIMIENDO;
    }

    public void ingresarCola(){
        this.estado = EstadoAsistente.ESPERANDO;
    }

    public void postergar(){
        this.estado = EstadoAsistente.VUELTAEN30MIN;
    }
}
