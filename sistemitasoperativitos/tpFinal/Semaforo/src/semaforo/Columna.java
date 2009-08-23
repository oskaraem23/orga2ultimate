/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package semaforo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tomas
 */
public class Columna implements Serializable {

    private List<Integer>   colaInicial=new ArrayList<Integer>();
    private List<Semaforo>  semaforosSuperiores= new ArrayList<Semaforo>();
    private List<Integer>   procesosEnZonaCritica=new ArrayList<Integer>();
    private List<Integer>   colaInferior=new ArrayList<Integer>();
    private List<Semaforo>  semaforosInferiores= new ArrayList<Semaforo>();

    public Columna() {
    }

    public void agregarNuevoProcesoAColaInicial(Integer proceso){
        colaInicial.add(proceso);
    }

    public List<Integer> getProcesosEnZonaCritica() {
        return procesosEnZonaCritica;
    }

    void agregarSemaforoSuperior(Semaforo semaforo) {
        semaforosSuperiores.add(semaforo);
    }

    void agregarSemaforoInferior(Semaforo semaforo) {
        semaforosInferiores.add(semaforo);
    }

    public List<Integer> getColaInferior() {
        return colaInferior;
    }

    public List<Integer> getColaInicial() {
        return colaInicial;
    }

    public Integer cantSemaforosSup(){
        return this.semaforosSuperiores.size();
    }

    public Integer cantSemaforosInf(){
        return this.semaforosInferiores.size();
    }

    public String mostrarSemaforoSup(Integer i){
        return this.semaforosSuperiores.get(i).mostrar();
    }
    
    public String mostrarSemaforoInf(Integer i){
        return this.semaforosInferiores.get(i).mostrar();
    }

}
