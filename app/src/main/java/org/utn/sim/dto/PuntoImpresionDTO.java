package org.utn.sim.dto;

import lombok.Data;
import org.utn.sim.model.Impresora;

@Data
public class PuntoImpresionDTO {
    private String estado;
    private boolean acFlag;

    public PuntoImpresionDTO(Impresora impresora) {
        this.estado = impresora.getEstado().toString();
        this.acFlag = impresora.isMantenimientoSession();
    }

}
