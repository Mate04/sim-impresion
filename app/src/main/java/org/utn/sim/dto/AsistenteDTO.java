package org.utn.sim.dto;

import lombok.Data;
import org.utn.sim.model.Asistente;


@Data
public class AsistenteDTO {
    private long id;
    private String estado;
    private String horaLlegadaCola;

    public AsistenteDTO(Asistente asistente) {
        this.id = asistente.getId();
        this.estado = asistente.getEstado().toString();
        this.horaLlegadaCola = asistente.getHorallegadaCola() > 0 ? String.valueOf( (int)(asistente.getHorallegadaCola()*100)/100) : "0.00";
    }

}