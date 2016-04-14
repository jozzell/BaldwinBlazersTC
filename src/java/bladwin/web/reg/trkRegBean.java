/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bladwin.web.reg;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author Lloyd
 */
@ManagedBean(name = "trkRegBean")
public class trkRegBean {
    private int trkId;
    private int custId;
    private int seasionId = 5;
    private int sizeShort;
    private int sizeShirt;
    private double cost;
    private String School;
    private String body;
    private String text;

    /**
     * @return the trkId
     */
    public int getTrkId() {
        return trkId;
    }

    /**
     * @param trkId the trkId to set
     */
    public void setTrkId(int trkId) {
        this.trkId = trkId;
    }

    /**
     * @return the custId
     */
    public int getCustId() {
        return custId;
    }

    /**
     * @param custId the custId to set
     */
    public void setCustId(int custId) {
        this.custId = custId;
    }

    /**
     * @return the seasionId
     */
    public int getSeasionId() {
        return seasionId;
    }

    /**
     * @param seasionId the seasionId to set
     */
    public void setSeasionId(int seasionId) {
        this.seasionId = seasionId;
    }

    /**
     * @return the sizeShort
     */
    public int getSizeShort() {
        return sizeShort;
    }

    /**
     * @param sizeShort the sizeShort to set
     */
    public void setSizeShort(int sizeShort) {
        this.sizeShort = sizeShort;
    }

    /**
     * @return the sizeShirt
     */
    public int getSizeShirt() {
        return sizeShirt;
    }

    /**
     * @param sizeShirt the sizeShirt to set
     */
    public void setSizeShirt(int sizeShirt) {
        this.sizeShirt = sizeShirt;
    }

    /**
     * @return the cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * @return the School
     */
    public String getSchool() {
        return School;
    }

    /**
     * @param School the School to set
     */
    public void setSchool(String School) {
        this.School = School;
    }

    /**
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }
}
