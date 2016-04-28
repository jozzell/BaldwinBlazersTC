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
import javax.faces.bean.SessionScoped;
import mgn.obj._beans.customerBean;
import mgn.obj._beans.customerLinkBean;
import mgn.obj._beans.mgnLookupBean;
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
    private customerLinkBean customerLinkBean ;
    private String msg;
    private int pgNum=0;
    private boolean isNewCustomer=true;
    private List<mgnLookupBean> mgnLookupBean = null;
    
    // ---------------------------------------------------------------
    @ManagedProperty("#{mgrVideoProduction}")
    private mgrVideoProduction mgrVideoProduction = null;
    public void setMgrVideoProduction(mgrVideoProduction mgrVideoProduction) {
        this.mgrVideoProduction = mgrVideoProduction;
    }

    /**
     * @return the customerBean
     */
    public String pgOneDisable(){
        return this.pgNum != 0 ? "true":"false";
    }
    public String pgTwoDisable(){
        return this.pgNum != 1 ? "true":"false";
    }
    public String pgThreeDisable(){
        return this.pgNum != 2 ? "true":"false";
    }
    public customerBean getCustomerBean() {
        if (customerBean == null){
            customerBean = this.mgrVideoProduction.getCustomerBean();
        }
        return customerBean;
    }
    public List<mgnLookupBean> getList(){
        if (mgnLookupBean == null){
            mgnLookupBean = new ArrayList<mgnLookupBean>();
            mgnLookupBean.add(new mgnLookupBean(-101,"Parent"));
            mgnLookupBean.add(new mgnLookupBean(-102,"Athlete"));
        }
        return mgnLookupBean;
    }

    /**
     * @param customerBean the customerBean to set
     */
    public void setCustomerBean(customerBean customerBean) {
        this.customerBean = customerBean;
    }
    public void chkAddrBack(){
        this.pgNum = 0;
        this.mgrVideoProduction.forward();
    }
    public void chkAddr(){
        int i;
         msg =  new custObjCheck().chkNewUserAddr(getCustomerBean());
         if (getMsg() !=  null){
            RequestContext.getCurrentInstance().execute("PF('dialogWidgetbasicDialogBlockMsg_2').show()");
        } else {
             custObj custObj = new custObj();
             i =  custObj.createUser(getCustomerBean(), this.getDbBlazers());
             if (isNewCustomer){
                 custObj.insertIntoLink(i,i, getCustomerLinkBean(),mgrVideoProduction.getDbBlazers());
             }
             isNewCustomer = false;
             this.getCustomerBean().setCustId(i);
             this.mgrVideoProduction.setCustomerBean(getCustomerBean());
             this.pgNum = 2;
             this.mgrVideoProduction.forward();
         }
    }
    public  void chkNewUser(){
        msg =  new custObjCheck().chkNewUser(getCustomerBean());
        if (getMsg() == null){
            custObj custObj = new custObj();
            int i = custObj.getCustID(this.getCustomerBean().getEMail(), this.getDbBlazers());
            if (i > 0){
                msg = "E-Mail All Ready Exist";
                
            } //else {
                //i = custObj.createUser(customerBean, this.getDbBlazers());
                //if (i > 0){
                    
                    //this.mgrVideoProduction.setCustomerBean(customerBean);
                //}
            //}
        }
        if (msg != null){
            RequestContext.getCurrentInstance().execute("PF('dialogWidgetbasicDialogBlockMsg').show()");
        } else {
            this.pgNum=1;
            mgrVideoProduction.forward();
        }
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @return the pgNum
     */
    public int getPgNum() {
        return pgNum;
    }

    /**
     * @param pgNum the pgNum to set
     */
    public void setPgNum(int pgNum) {
        this.pgNum = pgNum;
    }

    /**
     * @return the customerLinkBean
     */
    public customerLinkBean getCustomerLinkBean() {
        if (this.customerLinkBean == null) customerLinkBean = new customerLinkBean();
        return customerLinkBean;
    }

    /**
     * @param customerLinkBean the customerLinkBean to set
     */
    public void setCustomerLinkBean(customerLinkBean customerLinkBean) {
        this.customerLinkBean = customerLinkBean;
    }
}
