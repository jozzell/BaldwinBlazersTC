/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bladwin.web.reg;

import bladwin.web.mgrVideoProduction;
import bladwin.web.mgrVideoProduction_EL;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import mgn.obj._beans.customerBean;
import mgn.obj._beans.mgnLookupBean;
import mgn.obj.cust.custObj;
import mgn.obj.lookup.mgnLookupObj;

/**
 *
 * @author Lloyd
 */
@ManagedBean
@ViewScoped
public class regMgrAhtlete extends mgrVideoProduction_EL  implements Serializable{
    private customerBean customerBean;
    private trkRegBean trkRegBean;
    private String msg;
    private List<mgnLookupBean> sizeShirt;
    private List<mgnLookupBean> sizeShorts;
    private List<mgnLookupBean> type;
     // ---------------------------------------------------------------
    @ManagedProperty("#{mgrVideoProduction}")
    private mgrVideoProduction mgrVideoProduction = null;
    public void setMgrVideoProduction(mgrVideoProduction mgrVideoProduction) {
        this.mgrVideoProduction = mgrVideoProduction;
    }

    /**
     * @return the customerBean
     */
    public void chkName(){
        
    }
    public void chkNameCancel(){
        
    }
    public customerBean getCustomerBean() {
        if (customerBean == null){
            int i = this.mgrVideoProduction.getAthletId();
            customerBean = new custObj().getcustomerBean(i, this.getDbBlazers());
            if (this.customerBean ==  null){
                customerBean = new customerBean();
                customerBean.setRollup_id(this.mgrVideoProduction.getParentId());
            }
        }
        return customerBean;
    }

    /**
     * @param customerBean the customerBean to set
     */
    public void setCustomerBean(customerBean customerBean) {
        
        this.customerBean = customerBean;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @return the sizeShirt
     */
    public List<mgnLookupBean> getSizeShirt() {
        if (sizeShirt == null){
            sizeShirt = new mgnLookupObj().getLookupList(2, this.getDbBlazers());
        }
        return sizeShirt;
    }

    /**
     * @return the sizeShorts
     */
    public List<mgnLookupBean> getSizeShorts() {
        if (sizeShorts == null){
            sizeShorts = new mgnLookupObj().getLookupList(3, this.getDbBlazers());
        }
        return sizeShorts;
    }

    /**
     * @return the type
     */
    public List<mgnLookupBean> getType() {
        if (type == null){
            type = new mgnLookupObj().getLookupList(1, this.getDbBlazers());
        }
        return type;
    }

    /**
     * @return the trkRegBean
     */
    public trkRegBean getTrkRegBean() {
        if (trkRegBean == null){
            trkRegBean = new trkRegBean();
        }
        return trkRegBean;
    }

    /**
     * @param trkRegBean the trkRegBean to set
     */
    public void setTrkRegBean(trkRegBean trkRegBean) {
        this.trkRegBean = trkRegBean;
    }
}
