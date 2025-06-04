package org.utn.sim;

import org.utn.sim.model.Impresora;

public class Main {
    public static void main(String[] args) {
        Impresora impresora = new Impresora();
        System.out.println("Estado inicial de la impresora: " + impresora.toString());
        impresora.imprimirTicket(10.0);
        System.out.println("Estado de la impresora después de imprimir: " + impresora.toString());
    }
}