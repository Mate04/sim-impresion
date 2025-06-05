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

    /**
     * Constructor por defecto. Inicializa el tiempo en cero, crea la lista de
     * impresoras inicial y agenda el primer evento de llegada.
     */
    public Simulador() {
        this.tiempoActual = 0.0;
        this.crearListaImpresoras(6);
        this.eventosAProcesar.add(new LlegadaCliente(tiempoActual));
    }

    /**
     * Ejecuta la simulación hasta cumplir el tiempo X o la cantidad de
     * iteraciones i.
     *
     * @param X tiempo total de simulación en minutos
     * @param j hora de inicio de la muestra (no se utiliza aun)
     * @param i número de iteraciones a mostrar en consola
     */
    public void run(double X, double j, int i) {
        this.X = X;
        this.j = j;
        this.i = i;

        while (tiempoActual < X && iteracionActual < i) {
            Event proximoEvento = obtenerProximoEvento();
            proximoEvento.execute(this);
            this.tiempoActual = proximoEvento.getTiempoLlegada();
            this.iteracionActual++;
        }
        System.out.println(this);
    }


    /**
     * Encola un nuevo evento para ser procesado en el futuro.
     */
    public void agregarEvento(Event evento) {
        eventosAProcesar.offer(evento);
    }

    /**
     * Agrega una impresora permanente al sistema.
     */
    public void agregarImpresora(Impresora impresora) {
        this.impresoras.add(impresora);
    }

    /**
     * Devuelve y remueve el próximo evento según el orden de la cola.
     *
     * @return siguiente evento a ejecutar o {@code null} si no hay eventos.
     */
    public Event obtenerProximoEvento() {
        return eventosAProcesar.poll();
    }
    /**
     * Crea una cantidad fija de impresoras y las agrega a la lista del sistema.
     */
    public void crearListaImpresoras(int cantidadDeImpresoras) {
        for (int i = 0; i < cantidadDeImpresoras; i++) {
            this.impresoras.add(new Impresora());
        }
    }

    /**
     * Busca en la lista una impresora que esté libre.
     *
     * @return impresora disponible o {@code null} si todas están ocupadas.
     */
    public Impresora obtenerImpresoraLibre() {
        for (Impresora impresora : impresoras) {
            if (impresora.estaLibre()) {
                return impresora;
            }
        }
        return null;
    }
    /**
     * Ingresa un asistente a la cola de espera.
     */
    public void agregarAsistenteCola(Asistente asistente) {
        colaAsistentes.offer(asistente);
    }
    /**
     * Verifica si la cola de asistentes posee al menos n elementos.
     *
     * @param n cantidad a comparar
     * @return {@code true} si la cola tiene n o más asistentes
     */
    public boolean hayMasDeNEnCola(Integer n) {
        return colaAsistentes.size() >= n;
    }
    /** Incrementa el contador de asistentes que terminaron de imprimir. */
    public void sumarAsistenteFinalizado(){
        this.acumAsistentesFinalizados++;
    }
    /** Incrementa el contador de asistentes postergados. */
    public void sumarAsistentePostergado() {
        this.acumAsistentesPostergados++;
    }

    /**
     * Obtiene el primer asistente de la cola y actualiza el tiempo total de espera.
     *
     * @return asistente listo para ocupar una impresora o {@code null} si la cola está vacía
     */
    public Asistente obtenerPrimeroFilaAsistente() {
        Asistente asistente =  this.colaAsistentes.poll();
        if(asistente == null){
            return null;
        }
        this.acumuladorTiempoCola = (this.tiempoActual - asistente.getHorallegadaCola()) + this.acumuladorTiempoCola;
        return asistente;
    }
    /** Aumenta el contador de asistentes que pasaron por la cola. */
    public void sumarAsistentesEstuvieronCola() {
        this.acumAsistentesEstuvieronCola++;
    }
}



