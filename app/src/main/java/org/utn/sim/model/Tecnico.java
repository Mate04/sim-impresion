package org.utn.sim.model;


import lombok.Data;

@Data
public class Tecnico {
    private EstadoTecnico estado;
    private Impresora impresoraManteniendola;

    /**
     * Crea al técnico inicialmente en descanso.
     */
    public Tecnico() {
        this.estado = EstadoTecnico.DESCANSANDO;
        this.impresoraManteniendola = null;
    }

    /** Pone al técnico en estado de descanso. */
    public void descansar(){
        this.estado = EstadoTecnico.DESCANSANDO;
        this.impresoraManteniendola = null;
    };

    /** Indica que el técnico espera a que termine una impresión. */
    public void esperar(){
        this.estado = EstadoTecnico.ESPERANDO_FIN_DE_IMPRESION;
        this.impresoraManteniendola = null;
    };

    /**
     * Asigna una impresora al técnico y cambia a estado de mantenimiento.
     */
    public void mantener(Impresora impresora){
        this.estado = EstadoTecnico.REALIZANDO_MANTENIMIENTO;
        this.impresoraManteniendola = impresora;
        this.impresoraManteniendola.realizarMantenimiento();
    };

}
