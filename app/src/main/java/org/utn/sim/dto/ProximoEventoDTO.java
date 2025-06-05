package org.utn.sim.dto;

import lombok.Data;
import org.utn.sim.events.Event;

@Data
public class ProximoEventoDTO {
    private double hora;
    private double RND;

    public ProximoEventoDTO(Event evento) {
        this.hora = evento.getTiempoLlegada() ;
        this.RND =evento.getRandom();
    }

}