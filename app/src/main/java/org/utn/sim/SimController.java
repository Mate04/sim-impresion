package org.utn.sim;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.utn.sim.core.Simulador;
import org.utn.sim.dto.SimulacionDTO;
import org.utn.sim.dto.SimulacionRequestDTO;

import java.util.List;

@RestController
public class SimController {

    @PostMapping("/sim")
    public List<SimulacionDTO> simular(@RequestBody SimulacionRequestDTO request) {
        Simulador simulador = new Simulador();
        System.out.println("tiempo" + request.getTiempo() + " inicio: " + request.getInicio() + " iteraciones: " + request.getIteraciones());
        simulador.run(request.getTiempo(), request.getInicio(), request.getIteraciones());
        return simulador.getIteraciones();
    }

}
