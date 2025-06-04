package org.utn.sim.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Tecnico {
    private EstadoTecnico estado;
    private Impresora impresoraManteniendola;

    public void descansar(){
        this.estado = EstadoTecnico.DESCANSANDO;
        this.impresoraManteniendola = null;
    };

    public void esperar(){
        this.estado = EstadoTecnico.ESPERANDO_FIN_DE_IMPRESION;
        this.impresoraManteniendola = null;
    };

    public void mantener(Impresora impresora){
        this.estado = EstadoTecnico.REALIZANDO_MANTENIMIENTO;
        this.impresoraManteniendola = impresora;
    };

}
