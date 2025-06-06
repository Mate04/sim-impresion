package org.utn.sim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de arranque de la aplicación Spring Boot.
 * <p>
 * Levanta el contexto de Spring y expone el controlador REST
 * definido en el paquete {@code org.utn.sim}.
 */
@SpringBootApplication
public class AppApplication {

    /**
     * Método {@code main} estándar utilizado para iniciar la aplicación.
     *
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }
}
