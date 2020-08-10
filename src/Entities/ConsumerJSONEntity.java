/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Ulises
 */
public class ConsumerJSONEntity {
    
    private String titulo;
    private String dato;    
    private String noValidacion;

    /**
     * Get the value of noValidacion
     *
     * @return the value of noValidacion
     */
    public String getNoValidacion() {
        return noValidacion;
    }

    /**
     * Set the value of noValidacion
     *
     * @param noValidacion new value of noValidacion
     */
    public void setNoValidacion(String noValidacion) {
        this.noValidacion = noValidacion;
    }

    
    
    /**
     * Get the value of dato
     *
     * @return the value of dato
     */
    public String getDato() {
        return dato;
    }

    /**
     * Set the value of dato
     *
     * @param dato new value of dato
     */
    public void setDato(String dato) {
        this.dato = dato;
    }

    /**
     * Get the value of titulo
     *
     * @return the value of titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Set the value of titulo
     *
     * @param titulo new value of titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}
