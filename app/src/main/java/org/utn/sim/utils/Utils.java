package org.utn.sim.utils;

import java.util.Random;

public class Utils {

    private static final Random random = new Random();

    /**
     * Calcula una variable aleatoria con distribución exponencial negativa.
     *
     * @param media valor medio de la distribución
     * @return número aleatorio generado
     */
    public static double exponencialNegativa(double media) {
        return -media * Math.log(1 - random.nextDouble());
    }

    /**
     * Genera una variable aleatoria con distribución uniforme entre dos límites.
     *
     * @param LimiteInferior valor mínimo
     * @param LimiteSuperior valor máximo
     * @return número aleatorio generado
     */
    public static double uniforme(double LimiteInferior, double LimiteSuperior) {
        return LimiteInferior + (LimiteSuperior - LimiteInferior) * random.nextDouble();
    }
}
