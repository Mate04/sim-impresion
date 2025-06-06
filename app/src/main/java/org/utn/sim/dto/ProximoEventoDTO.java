package org.utn.sim.dto;

import lombok.Data;
import org.utn.sim.events.Event;

/**
 * DTO que expone la información básica del próximo evento en la cola.
 */
@Data
public class ProximoEventoDTO {
    private double hora;
    private double RND;

    /**
     * Construye el DTO tomando los datos del evento recibido. Si el evento es
     * {@code null}, se cargan valores por defecto.
     */
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