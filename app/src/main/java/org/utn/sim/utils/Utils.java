package org.utn.sim.utils;

import java.util.Random;

/**
 * Métodos utilitarios para el cálculo de tiempos aleatorios utilizados en la
 * simulación.
 */
public class Utils {

    /**
     * Calcula una variable aleatoria con distribución exponencial negativa.
     *
     * @param media valor medio de la distribución
     * @return número aleatorio generado
     */
    public static double exponencialNegativa(double media, double random) {
        return -media * Math.log(1 - random);
    }

    /**
     * Genera una variable aleatoria con distribución uniforme entre dos límites.
     *
     * @param LimiteInferior valor mínimo
     * @param LimiteSuperior valor máximo
     * @return número aleatorio generado
     */
    public static double uniforme(double LimiteInferior, double LimiteSuperior, double random) {
        return LimiteInferior + (LimiteSuperior - LimiteInferior) * random;
    }
}
