package org.utn.sim;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.utn.sim.core.Simulador;
import org.utn.sim.dto.SimulacionConDatosAdicionalesDTO;
import org.utn.sim.dto.SimulacionDTO;
import org.utn.sim.dto.SimulacionRequestDTO;

import java.util.List;

@RestController
public class SimController {

    @PostMapping("/sim")
    public SimulacionConDatosAdicionalesDTO simular(@RequestBody SimulacionRequestDTO request) {

        Simulador simulador = new Simulador();

        double tiempoPromedioEnCola = 0;
        double porcentajeQueFueronYVolvieron = 0;
        System.out.println("tiempo" + request.getTiempo() + " inicio: " + request.getInicio() + " iteraciones: " + request.getIteraciones());
        simulador.run(request.getTiempo(), request.getInicio(), request.getIteraciones());

        if(simulador.getAcumAsistentesEstuvieronCola() != 0) {
            tiempoPromedioEnCola = (simulador.getAcumuladorTiempoCola() / simulador.getAcumAsistentesEstuvieronCola());
        }
        if (simulador.getAcumAsistentesFinalizados() != 0) {
            porcentajeQueFueronYVolvieron = ((double) simulador.getAcumAsistentesPostergados() / simulador.getAcumAsistentesFinalizados()) * 100;
        }

        SimulacionConDatosAdicionalesDTO simuladorDto = new SimulacionConDatosAdicionalesDTO(simulador.getIteraciones(), tiempoPromedioEnCola, porcentajeQueFueronYVolvieron);
        return simuladorDto;
    }

}
