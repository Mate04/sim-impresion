package org.utn.sim.events;

import lombok.Data;
import org.utn.sim.core.Simulador;
import org.utn.sim.model.Impresora;
import org.utn.sim.utils.Utils;


@Data
public class FinMantenimiento extends Event{
    private String nombre = "Fin mantenimiento";
    private double random;
    private Impresora impresora;

    public FinMantenimiento(double horaActual, Impresora impresora) {
        this.random = Math.random();
        this.tiempoUsado = Utils.uniforme(3, 10, random);
        this.tiempoLlegada = horaActual + tiempoUsado;
        this.impresora = impresora;
    }

    @Override
    public void execute(Simulador simulador) {
        simulador.contarFinImpresion();
        this.impresora.libre();
        //Verifica que no haya maquinas para mantener
        if (simulador.hayMaquinasParaMantener()){
            simulador.agregarEvento(new LlegadaTecnico(this.tiempoLlegada));
            simulador.getTecnico().descansar();
            //reseteo la sesion, todas las maquinas pasan a estar en falso
            simulador.setMantenimientoMaquina();
            return;
        }

        //Todo: Verificamos que haya una proxima maquina libre y tenga que mantenerse
        Impresora impresoraMantener = simulador.obtenerImpresoraAMantener();
        if(impresoraMantener != null){
            simulador.agregarEvento(new FinMantenimiento(this.tiempoLlegada,impresoraMantener));
            simulador.getTecnico().mantener(impresoraMantener);
            return;
        }
        simulador.getTecnico().esperar();

    }
}
