package org.utn.sim.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Tecnico {
    private EstadoTecnico estado;
    private Impresora impresoraManteniendola;

    public void descansar(){

    };

    public void esperar(){

    };

    public void mantener(){

    };

}
