package org.utn.sim.core;

import lombok.Data;
import lombok.ToString;
import org.utn.sim.events.Event;
import org.utn.sim.events.LlegadaCliente;
import org.utn.sim.model.Asistente;
import org.utn.sim.model.Impresora;

import java.util.*;

@Data
@ToString
public class Simulador {
    protected double tiempoActual;
    protected double X; // Tiempo de inicio de simulación
    protected double j; // Hora de inicio
    protected int i; // Número de iteraciones
    private int iteracionActual = 0;
    //Listado  de impresoras que son permanentes
    private List<Impresora> impresoras = new ArrayList<>();
    //Cola
    private PriorityQueue<Event> eventosAProcesar = new PriorityQueue<>();
    private Queue<Asistente> asistentesConsumiendoServicio = new LinkedList<>();
    // Cola de asistentes que llegan al sistema y deben ir a la cola
    private Queue<Asistente> colaAsistentes = new LinkedList<>();
    //Estadistico
    private int acumAsistentesPostergados = 0;
    private int acumAsistentesFinalizados = 0;
    private int acumAsistentesEstuvieronCola = 0;
    private double acumuladorTiempoCola = 0;

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
        System.out.println(this);
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
    public void sumarAsistenteFinalizado(){
        this.acumAsistentesFinalizados++;
    }
    public void sumarAsistentePostergado() {
        this.acumAsistentesPostergados++;
    }

    public Asistente obtenerPrimeroFilaAsistente() {
        Asistente asistente =  this.colaAsistentes.poll();
        if(asistente == null){
            return null;
        }
        this.acumuladorTiempoCola = (this.tiempoActual - asistente.getHorallegadaCola())+ this.acumuladorTiempoCola;
        return asistente;
    }
    public void sumarAsistentesEstuvieronCola() {
        this.acumAsistentesEstuvieronCola++;
    }
}



