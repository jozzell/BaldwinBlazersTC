/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bladwin.web.reg;

import bladwin.web.mgrVideoProduction;
import bladwin.web.mgrVideoProduction_EL;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import mgn.obj._beans.customerBean;
import mgn.obj.cust.custObj;
import mgn.obj.cust.custObjCheck;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Lloyd
 */
@ManagedBean
@SessionScoped
public class regMgr extends mgrVideoProduction_EL  implements Serializable{
    private customerBean customerBean;
    private String msg;
    // ---------------------------------------------------------------
    @ManagedProperty("#{mgrVideoProduction}")
    private mgrVideoProduction mgrVideoProduction = null;
    public void setMgrVideoProduction(mgrVideoProduction mgrVideoProduction) {
        this.mgrVideoProduction = mgrVideoProduction;
    }

    /**
     * @return the customerBean
     */
    public customerBean getCustomerBean() {
        if (customerBean == null){
            customerBean = this.mgrVideoProduction.getCustomerBean();
        }
        return customerBean;
    }

    /**
     * @param customerBean the customerBean to set
     */
    public void setCustomerBean(customerBean customerBean) {
        this.customerBean = customerBean;
    }
    public void chkAddr(){
         msg =  new custObjCheck().chkNewUserAddr(customerBean);
         if (getMsg() !=  null){
             this.mgrVideoProduction.setCustomerBean(customerBean);
         }
    }
    public  void chkNewUser(){
        msg =  new custObjCheck().chkNewUser(customerBean);
        if (getMsg() == null){
            custObj custObj = new custObj();
            int i = custObj.getCustID(this.customerBean.getEMail(), this.getDbBlazers());
            if (i > 0){
                msg = "E-Mail All Ready Exist";
                
            } else {
                i = custObj.createUser(customerBean, this.getDbBlazers());
                if (i > 0){
                    this.customerBean.setCustId(i);
                    this.mgrVideoProduction.setCustomerBean(customerBean);
                }
            }
        }
        if (msg != null){
            RequestContext.getCurrentInstance().execute("PF('dialogWidgetbasicDialogBlockMsg').show()");
        }
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }
}
