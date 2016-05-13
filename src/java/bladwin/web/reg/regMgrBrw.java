/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bladwin.web.reg;

import bladwin.web.mgrVideoProduction;
import bladwin.web.mgrVideoProduction_EL;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import mgn.obj._beans.customerBean;
import mgn.obj._beans.customerRegBean;
import mgn.obj.cust.custObj;
import mgn.obj.cust.custRegObj;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Lloyd
 */
@ManagedBean
@ViewScoped
public class regMgrBrw  extends mgrVideoProduction_EL  implements Serializable{
    private List<customerBean> custList;
    private List<customerRegBean> regList;
    private customerRegBean customerRegBean;
    private customerBean customerBean;
    
    // ---------------------------------------------------------------
    private String msg;
    @ManagedProperty("#{mgrVideoProduction}")
    private mgrVideoProduction mgrVideoProduction = null;
    public void setMgrVideoProduction(mgrVideoProduction mgrVideoProduction) {
        this.mgrVideoProduction = mgrVideoProduction;
    }
    @ManagedProperty("#{regMgr}")
    private regMgr regMgr = null;
    public void setRegMgr(regMgr regMgr) {
        this.regMgr = regMgr;
    }
    
    
    
    
    public List<customerBean> getCustList() {
        if (custList == null){
             genList();
        }
        return custList;
    }
     private void genList(){
        int id = regMgr.getParentId();
        custList = new custObj().getcustomerList_link(id, mgrVideoProduction.getDbBlazers());
        if (custList == null) custList = new ArrayList<customerBean>();
        if(this.getCustomerBean() != null) this.getCustomerBean().setCustId(0);
    }
    public void setCustList(List<customerBean> custList) {
        this.custList = custList;
    }

    /**
     * @return the customerBean
     */
    public customerBean getCustomerBean() {
        if (customerBean == null){
            customerBean = new customerBean();
        }
        return customerBean;
    }
    public void setCustomerBean(customerBean customerBean) {
        this.customerBean = customerBean;
    }
    // ---------------------------------------------------------
    public void childAdd(){
        regMgr.setAthletId(0);
         child();
    }
    public void childEdit(){
        if (!isCusteromBeanSelected()){
             msg = "Nothing was selectred";
             RequestContext.getCurrentInstance().execute("PF('dialogWidgetbasicDialogBlockMsg').show()");
             //mgrVideoProduction.forward();
        } else {
            if (customerBean.getCustId() == regMgr.getParentId()){
                regMgr.editParent();
            } else {
                regMgr.setAthletId(customerBean.getCustId());
                child();
            }
        }
    }
    private void child(){
        mgrVideoProduction.setUrl("reg/userLogin02.xhtml");
    }
     private boolean isCusteromBeanSelected(){
        if (this.customerBean == null || this.customerBean.getCustId() == 0) {
            msg = "Nothing was selecterd";
            return false;
        }else {
            msg = null;
            return true;
        }
            
    }
      public void regAthlete(){
        msg = null;
        if (isCusteromBeanSelected()){
            regMgr.setAthletId(this.customerBean.getCustId());
            regMgr.resetRegBean();
            mgrVideoProduction.setUrl("reg/regAthlete01.xhtml");
            
            
        } else {
            RequestContext.getCurrentInstance().execute("PF('dialogWidgetbasicDialogBlockMsg').show()");
        }
        
        
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @return the regList
     */
    public List<customerRegBean> getRegList() {
        if (regList == null){
            int id = regMgr.getParentId();
            regList = new custRegObj().getCustomerRegBeanList_byRollup(id, this.getDbBlazers());
        }
        return regList;
    }

    /**
     * @return the customerRegBean
     */
    public customerRegBean getCustomerRegBean() {
        return customerRegBean;
    }

    /**
     * @param customerRegBean the customerRegBean to set
     */
    public void setCustomerRegBean(customerRegBean customerRegBean) {
        this.customerRegBean = customerRegBean;
    }
}
