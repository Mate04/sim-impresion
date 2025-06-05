package org.utn.sim.core;

import lombok.Data;
import org.utn.sim.events.Event;
import org.utn.sim.events.LlegadaCliente;
import org.utn.sim.model.Asistente;
import org.utn.sim.model.Impresora;
import org.utn.sim.utils.Utils;

import java.util.*;

@Data
public class Simulador {
    protected double tiempoActual;
    protected double X; // Tiempo de inicio de simulación
    protected double j; // Hora de inicio
    protected int i; // Número de iteraciones
    private int iteracionActual = 0;
    //Lista de asistente que se estan procesando su tickets, postergados
    private Queue<Asistente> asistentes = new LinkedList<>();
    private List<Impresora> impresoras = new ArrayList<Impresora>();
    private PriorityQueue<Event> eventosAProcesar = new PriorityQueue<>();
    // Cola de asistentes que llegan al sistema y deben ir a la cola
    private Queue<Asistente> colaAsistentes = new LinkedList<>();

    public Simulador() {
        this.tiempoActual = 0.0;
        //Creamos las impresoras
        this.crearListaImpresoras(6);
        this.eventosAProcesar.add(new LlegadaCliente(tiempoActual));
    }

    public void run(double X, double j, int i) {
        this.X = X; // Tiempo de simulación
        this.j = j; // Hora de inicio de muestra
        this.i = i; // Número de iteraciones a mostrar
        //TODO: implementar ciclo actual

        while (tiempoActual < X && iteracionActual < i) {
            // Obtener el próximo evento a procesar
            Event proximoEvento = obtenerProximoEvento();
            proximoEvento.execute(this);
            this.tiempoActual = proximoEvento.getTiempoLlegada();
            this.iteracionActual++;
        }
    }

    public void agregarEvento(Event evento) {
        eventosAProcesar.offer(evento);
    }

    public void agregarImpresora(Impresora impresora) {
        this.impresoras.add(impresora);
    }

    public Event obtenerProximoEvento() {
        return eventosAProcesar.poll(); //todo: Agarra el primer elemento actual, y lo saca
    }
    public void crearListaImpresoras(int cantidadDeImpresoras) {
        for (int i = 0; i < cantidadDeImpresoras; i++) {
            this.impresoras.add(new Impresora());
        }
    }

    public Impresora obtenerImpresoraLibre() {
        for (Impresora impresora : impresoras) {
            if (impresora.estaLibre()) {
                return impresora;
            }
        }
        return null; // Si no hay impresoras libres
    }
    public void agregarAsistenteCola(Asistente asistente) {
        colaAsistentes.offer(asistente);
    }
    public boolean hayMasDeNEnCola(Integer n) {
        return colaAsistentes.size() >= n;
    }
}



