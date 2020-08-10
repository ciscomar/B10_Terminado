/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author ulisesrdz
 */
public class bartenderEntity {
    
    private int id;
    private String no_sap;    
    private String cust_part;    
    private String std_pack;    
    private String platform;

    /**
     * Get the value of platform
     *
     * @return the value of platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * Set the value of platform
     *
     * @param platform new value of platform
     */
    public void setPlatform(String platform) {
        this.platform = platform;
    }


    /**
     * Get the value of std_pack
     *
     * @return the value of std_pack
     */
    public String getStd_pack() {
        return std_pack;
    }

    /**
     * Set the value of std_pack
     *
     * @param std_pack new value of std_pack
     */
    public void setStd_pack(String std_pack) {
        this.std_pack = std_pack;
    }


    /**
     * Get the value of cust_part
     *
     * @return the value of cust_part
     */
    public String getCust_part() {
        return cust_part;
    }

    /**
     * Set the value of cust_part
     *
     * @param cust_part new value of cust_part
     */
    public void setCust_part(String cust_part) {
        this.cust_part = cust_part;
    }

    

    /**
     * Get the value of no_sap
     *
     * @return the value of no_sap
     */
    public String getNo_sap() {
        return no_sap;
    }

    /**
     * Set the value of no_sap
     *
     * @param no_sap new value of no_sap
     */
    public void setNo_sap(String no_sap) {
        this.no_sap = no_sap;
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

}
