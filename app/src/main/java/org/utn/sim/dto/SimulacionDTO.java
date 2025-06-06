package org.utn.sim.dto;
import lombok.Data;
import org.utn.sim.core.Simulador;

import java.util.List;

@Data
public class SimulacionDTO {
    private String evento;
    private Double reloj;
    private ProximoEventoDTO proxAsist;
    private ProximoEventoDTO proxTecnico;
    private String estadoTecnico;
    private int colaAsistentes;
    private List<PuntoImpresionDTO> puntosImpresion;
    private int acAsistenteCola;
    private double acTiempoCola;
    private int acAsistPostergados;
    private List<AsistenteDTO> asistente;

    public SimulacionDTO(Simulador simulador) {
        this.evento = simulador.getEventoActual().getNombre();
        this.reloj = (simulador.getTiempoActual());
        this.proxAsist = new ProximoEventoDTO(simulador.buscarProximaLLegadaCliente());
        this.proxTecnico = new ProximoEventoDTO(simulador.buscarProximaLlegadaTecnico());
        this.estadoTecnico = simulador.getTecnico().getEstado().toString();
        this.colaAsistentes = simulador.getColaAsistentes().size();
        this.puntosImpresion = simulador.generarDTOsPuntosImpresion();
        this.acAsistenteCola = simulador.getAcumAsistentesEstuvieronCola();
        this.acTiempoCola = simulador.getAcumuladorTiempoCola();
        this.acAsistPostergados = simulador.getAcumAsistentesPostergados();
        this.asistente = simulador.generarDTOsAsistentes();
    }

}
