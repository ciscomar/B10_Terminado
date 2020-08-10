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
public class StationEntity {
    
    private int id;
    private String mq_server;
    private String mq_user;
    private String mq_pass;
    private String no_estacion;
    private String impre;
    private String cancelacion;
    private boolean enableStation;
    private boolean enable;
    private String mysql_db;

    private String confimar;

    /**
     * Get the value of confimar
     *
     * @return the value of confimar
     */
    public String getConfimar() {
        return confimar;
    }

    /**
     * Set the value of confimar
     *
     * @param confimar new value of confimar
     */
    public void setConfimar(String confimar) {
        this.confimar = confimar;
    }

    /**
     * Get the value of enable
     *
     * @return the value of enable
     */
    public boolean isEnable() {
        return enable;
    }

    /**
     * Set the value of enable
     *
     * @param enable new value of enable
     */
    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    
    
    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(int id) {
        this.id = id;
    }

       

    /**
     * Get the value of mq_server
     *
     * @return the value of mq_server
     */
    public String getMq_server() {
        return mq_server;
    }

    /**
     * Set the value of mq_server
     *
     * @param mq_server new value of mq_server
     */
    public void setMq_server(String mq_server) {
        this.mq_server = mq_server;
    }

    

    /**
     * Get the value of mq_user
     *
     * @return the value of mq_user
     */
    public String getMq_user() {
        return mq_user;
    }

    /**
     * Set the value of mq_user
     *
     * @param mq_user new value of mq_user
     */
    public void setMq_user(String mq_user) {
        this.mq_user = mq_user;
    }

    

    /**
     * Get the value of mq_pass
     *
     * @return the value of mq_pass
     */
    public String getMq_pass() {
        return mq_pass;
    }

    /**
     * Set the value of mq_pass
     *
     * @param mq_pass new value of mq_pass
     */
    public void setMq_pass(String mq_pass) {
        this.mq_pass = mq_pass;
    }

   

    /**
     * Get the value of no_estacion
     *
     * @return the value of no_estacion
     */
    public String getNo_estacion() {
        return no_estacion;
    }

    /**
     * Set the value of no_estacion
     *
     * @param no_estacion new value of no_estacion
     */
    public void setNo_estacion(String no_estacion) {
        this.no_estacion = no_estacion;
    }

    

    /**
     * Get the value of impre
     *
     * @return the value of impre
     */
    public String getImpre() {
        return impre;
    }

    /**
     * Set the value of impre
     *
     * @param impre new value of impre
     */
    public void setImpre(String impre) {
        this.impre = impre;
    }

    

    /**
     * Get the value of cancelacion
     *
     * @return the value of cancelacion
     */
    public String getCancelacion() {
        return cancelacion;
    }

    /**
     * Set the value of cancelacion
     *
     * @param cancelacion new value of cancelacion
     */
    public void setCancelacion(String cancelacion) {
        this.cancelacion = cancelacion;
    }

   

    /**
     * Get the value of enableStation
     *
     * @return the value of enableStation
     */
    public boolean isEnableStation() {
        return enableStation;
    }

    /**
     * Set the value of enableStation
     *
     * @param enableStation new value of enableStation
     */
    public void setEnableStation(boolean enableStation) {
        this.enableStation = enableStation;
    }

    

    /**
     * Get the value of mysql_db
     *
     * @return the value of mysql_db
     */
    public String getMysql_db() {
        return mysql_db;
    }

    /**
     * Set the value of mysql_db
     *
     * @param mysql_db new value of mysql_db
     */
    public void setMysql_db(String mysql_db) {
        this.mysql_db = mysql_db;
    }

}
