package org.utn.sim.utils;

public class Utils {

    // aca llevamos a cabo el calculo de la variable exponencial negativa, tomando
    // como parametros la media y el random, que se pueden modificar por entrada del
    // usuario
    public static double exponencialNegativa(double media, double random) {
        return -media * Math.log(1 - random);
    }

    // lo mismo que en el anterior pero con la variable uniforme a,b donde se pueden
    // cargar ambos limites y el random
    // en este caso la vamos a usar varias veces por lo tanto es mejor generalizarla
    public static double uniforme(double LimiteInferior, double LimiteSuperior, double random) {
        return LimiteInferior + (LimiteSuperior - LimiteInferior) * random;
    }
}
