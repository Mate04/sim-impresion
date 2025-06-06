package org.utn.sim.dto;

import lombok.Data;
import org.utn.sim.model.Impresora;

/**
 * DTO que describe el estado de una impresora en un instante determinado.
 */
@Data
public class PuntoImpresionDTO {
    private String estado;
    private boolean acFlag;

    /**
     * Construye el DTO en base a la impresora recibida.
     */
    public PuntoImpresionDTO(Impresora impresora) {
        this.estado = impresora.getEstado().toString();
        this.acFlag = impresora.isMantenimientoSession();
    }

}
