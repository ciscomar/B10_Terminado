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
public class SendJsonEntity {
    
    private String estacion;
    private String impresoraBartender;
    private String nSAP;
    private String pckd;
    private String empresa;
    private boolean bartender;    
    private String dataBase;
    private String subline;
    private String empaque;

    public String getEmpaque() {
        return empaque;
    }

    public void setEmpaque(String empaque) {
        this.empaque = empaque;
    }

    /**
     * Get the value of subline
     *
     * @return the value of subline
     */
    public String getSubline() {
        return subline;
    }

    /**
     * Set the value of subline
     *
     * @param subline new value of subline
     */
    public void setSubline(String subline) {
        this.subline = subline;
    }

    

    /**
     * Get the value of dataBase
     *
     * @return the value of dataBase
     */
    public String getDataBase() {
        return dataBase;
    }

    /**
     * Set the value of dataBase
     *
     * @param dataBase new value of dataBase
     */
    public void setDataBase(String dataBase) {
        this.dataBase = dataBase;
    }

    
    
    

    /**
     * Get the value of bartender
     *
     * @return the value of bartender
     */
    public boolean isBartender() {
        return bartender;
    }

    /**
     * Set the value of bartender
     *
     * @param bartender new value of bartender
     */
    public void setBartender(boolean bartender) {
        this.bartender = bartender;
    }

    

    /**
     * Get the value of empresa
     *
     * @return the value of empresa
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * Set the value of empresa
     *
     * @param empresa new value of empresa
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    

    /**
     * Get the value of pckd
     *
     * @return the value of pckd
     */
    public String getPckd() {
        return pckd;
    }

    /**
     * Set the value of pckd
     *
     * @param pckd new value of pckd
     */
    public void setPckd(String pckd) {
        this.pckd = pckd;
    }

    

    /**
     * Get the value of nSAP
     *
     * @return the value of nSAP
     */
    public String getnSAP() {
        return nSAP;
    }

    /**
     * Set the value of nSAP
     *
     * @param nSAP new value of nSAP
     */
    public void setnSAP(String nSAP) {
        this.nSAP = nSAP;
    }
    
    

    /**
     * Get the value of impresoraBartender
     *
     * @return the value of impresoraBartender
     */
    public String getImpresoraBartender() {
        return impresoraBartender;
    }

    /**
     * Set the value of impresoraBartender
     *
     * @param impresoraBartender new value of impresoraBartender
     */
    public void setImpresoraBartender(String impresoraBartender) {
        this.impresoraBartender = impresoraBartender;
    }

    /**
     * Get the value of estacion
     *
     * @return the value of estacion
     */
    public String getEstacion() {
        return estacion;
    }

    /**
     * Set the value of estacion
     *
     * @param estacion new value of estacion
     */
    public void setEstacion(String estacion) {
        this.estacion = estacion;
    }

    
}
