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
import mgn.obj._beans.customerLinkBean;
import mgn.obj._beans.mgnLookupBean;
import mgn.obj.cust.custObj;
import mgn.obj.cust.custRegObj;
import mgn.obj.lookup.mgnLookupObj;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Lloyd
 */
@ManagedBean
@ViewScoped
public class regMgrAhtlete  extends mgrVideoProduction_EL  implements Serializable{
    private customerBean customerBean;
    private mgnLookupBean mgnLookupBean;
    private customerLinkBean customerLinkBean; // = new customerLinkBean();
    private List<mgnLookupBean>  list= null;
    mgnLookupObj mgnLookupObj ;
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
    public void childSave(){
        
        custObj custObj = new custObj();
        int i = custObj.createUser(customerBean, mgrVideoProduction.getDbBlazers());
        if (i != 0){
            new custRegObj().insertIntoLink(i,regMgr.getParentId(), customerLinkBean, mgrVideoProduction.getDbBlazers());
            childCancel();
        }
        
        
    }
    public void regPg2Save(){
        //regMgr.getCustomerRegBean().setCust_id(regMgr.getAthletId());
        new custRegObj().customerRegUpdate(regMgr.getCustomerRegBean(), this.getDbBlazers());
        childCancel();
    }
    public void regPg2Back(){
        mgrVideoProduction.setUrl("reg/regAthlete01.xhtml");
    }
    
    public void regPg1Next(){
        if (this.mgnLookupBean == null){
            msg = "Nothing was Selected";
            RequestContext.getCurrentInstance().execute("PF('dialogWidgetbasicDialogBlockMsg').show()");
        } else {
            regMgr.getCustomerRegBean().setRegLookupId(mgnLookupBean.getLookupId());
            regMgr.getCustomerRegBean().setLookupDesc(mgnLookupBean.getLookupDesc());
            mgrVideoProduction.setUrl("reg/regAthlete02.xhtml");
        }
    }
    public void cancelReg(){
        childCancel();
    }
    public void childCancel(){
        mgrVideoProduction.setUrl("reg/userLogin01.xhtml");
        //genList();
    }
    /**
     * @return the customerBean
     */
    public customerBean getCustomerBean() {
        if (customerBean == null){
             int i = regMgr.getAthletId();
             if (i != 0){
                 customerBean = new custObj().getcustomerBean(i, this.getDbBlazers());
             }
             if (customerBean == null) {
                customerBean = new customerBean();
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
     * @return the customerLinkBean
     */
    
    public synchronized List<mgnLookupBean> genList(int i){
         if (mgnLookupObj == null){
            mgnLookupObj = new mgnLookupObj();
        }
         List<mgnLookupBean> l =  mgnLookupObj.getLookupList(i, this.getDbBlazers());
         if (i != 4){
             
            l.add(0,new mgnLookupBean());
         }
         return l;
    }
    public customerLinkBean getCustomerLinkBean() {
        if (customerLinkBean == null) {
            int i = regMgr.getAthletId();
            if (i > 0) {
                customerLinkBean = new custRegObj().getCustomerLinkBean(i, this.getDbBlazers());
            }
            if (customerLinkBean == null) customerLinkBean = new customerLinkBean();
        }
        return customerLinkBean;
    }

    /**
     * @param customerLinkBean the customerLinkBean to set
     */
    public void setCustomerLinkBean(customerLinkBean customerLinkBean) {
        this.customerLinkBean = customerLinkBean;
    }
   

    /**
     * @return the lList
     */
    public List<mgnLookupBean> getList() {
        if (list == null){
            list = genList(4);
        }
        return list;
    }

    /**
     * @return the mgnLookupBean
     */
    public mgnLookupBean getMgnLookupBean() {
        if (mgnLookupBean == null){
            mgnLookupBean = new mgnLookupBean();
        }
        return mgnLookupBean;
    }

    /**
     * @param mgnLookupBean the mgnLookupBean to set
     */
    public void setMgnLookupBean(mgnLookupBean mgnLookupBean) {
        this.mgnLookupBean = mgnLookupBean;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    
}
