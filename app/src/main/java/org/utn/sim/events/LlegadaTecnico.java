package org.utn.sim.events;

import lombok.Getter;
import lombok.ToString;
import org.utn.sim.core.Simulador;
import org.utn.sim.model.Impresora;
import org.utn.sim.utils.Utils;

@ToString
@Getter
public class LlegadaTecnico extends Event{

    private String nombre = "Llegada tecnico";

    public LlegadaTecnico(double horaActual){
        this.tiempoUsado = Utils.uniforme(57, 63);
        this.tiempoLlegada = horaActual + this.tiempoUsado;
    }

    public void execute(Simulador simulador) {
        //Verificamos que haya una maquina libre
        Impresora impresoraMantener = simulador.obtenerImpresoraAMantener();
        if (impresoraMantener != null) {
            simulador.agregarEvento(new FinMantenimiento(this.tiempoLlegada,impresoraMantener));
            simulador.getTecnico().mantener(impresoraMantener); //El tecnico pasa a estar ocupado y la maquina en mantenimiento
            return;
        }

        simulador.getTecnico().esperar();







    };
}
