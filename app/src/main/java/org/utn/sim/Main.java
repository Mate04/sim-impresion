package org.utn.sim;

import org.utn.sim.core.Simulador;
import org.utn.sim.model.Impresora;

import java.util.Scanner;

public class Main {
    /**
     * Punto de entrada de la aplicación. Solicita los parámetros de la
     * simulación y lanza el simulador.
     */
    public static void main(String[] args) {
        // Pedir los datos de X tiempo, i iteraciones y hora j
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el tiempo X (en minutos) a simular:");
        double X = scanner.nextDouble();

        System.out.println("Ingrese la hora j (en minutos) para iniciar la simulación:");
        double j = scanner.nextDouble();

        System.out.println("Ingrese el número de iteraciones i:");
        int i = scanner.nextInt();

        Simulador simulador = new Simulador();
        simulador.run(X, j, i);

    }
}