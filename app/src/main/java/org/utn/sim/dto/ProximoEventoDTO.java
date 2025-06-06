package org.utn.sim.dto;

import lombok.Data;
import org.utn.sim.events.Event;

@Data
public class ProximoEventoDTO {
    private double hora;
    private double RND;

    public ProximoEventoDTO(Event evento) {
        if (evento != null) {
            this.hora = evento.getTiempoLlegada();
            this.RND = evento.getRandom();
        } else {
            this.hora = 0;
            this.RND = 0;
        }
    }

}