package org.utn.sim.events;

import org.utn.sim.core.Simulador;
import org.utn.sim.utils.Utils;

public class FinImpresion extends Event{
    public FinImpresion(double tiempoActual) {
        this.tiempoUsado = Utils.exponencialNegativa(2);
        this.tiempoLlegada = tiempoActual + tiempoUsado;
    }public void execute(Simulador simulador) {}}
