package org.utn.sim.events;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.utn.sim.core.Simulador;
import org.utn.sim.model.Impresora;
import org.utn.sim.utils.Utils;

/**
 * Evento que representa la llegada del técnico de mantenimiento al sistema.
 */
@ToString
@Getter
@Setter
public class LlegadaTecnico extends Event{

    private String nombre = "Llegada tecnico";
    private double random;

    /**
     * Genera la próxima llegada del técnico utilizando una distribución
     * uniforme.
     */
    public LlegadaTecnico(double horaActual){
        random = Math.random();
        this.tiempoUsado = Utils.uniforme(57, 63, random);
        this.tiempoLlegada = horaActual + this.tiempoUsado;
    }

    /**
     * Al ejecutarse verifica si hay alguna impresora lista para mantenimiento y
     * en caso afirmativo agenda el fin de mantenimiento.
     */
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
