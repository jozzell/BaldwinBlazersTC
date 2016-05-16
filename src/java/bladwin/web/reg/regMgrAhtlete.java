/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bladwin.web.reg;

import bladwin.web.mgrVideoProduction;
import java.io.Serializable;
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
public class regMgrAhtlete   implements Serializable{
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
        mgnLookupBean b = new mgnLookupObj().getLookupBean(-947, mgrVideoProduction.getDbBlazers());
        String str = regMgr.getPdfFileName();
        regMgr.getCustomerRegBean().setPdf(b.getLookupDesc()+"/"+str);
        new custRegObj().customerRegUpdate(regMgr.getCustomerRegBean(), mgrVideoProduction.getDbBlazers());
        childCancel();
        
        //new regPDF().genPed(b.getSubjectText()+"/"+b.getLookupDesc()+"/"+str,this.regMgr,mgrVideoProduction.getDbBlazers());
        //regMgr.setNav(eNumReg.reg_pg03);
       //mgrVideoProduction.setUrl("reg/regAthlete03.xhtml");
        //childCancel();
    }
    public void regPg2Back(){
        regMgr.setNav(eNumReg.reg_pg01);
        mgrVideoProduction.setUrl("reg/regAthlete01.xhtml");
    }
    
    public void regPg1Next(){
        if (this.mgnLookupBean == null){
            msg = "Nothing was Selected";
            RequestContext.getCurrentInstance().execute("PF('dialogWidgetbasicDialogBlockMsg').show()");
        } else {
            regMgr.getCustomerRegBean().setRegLookupId(mgnLookupBean.getLookupId());
            regMgr.getCustomerRegBean().setLookupDesc(mgnLookupBean.getLookupDesc());
            regMgr.setNav(eNumReg.reg_pg02);
            mgrVideoProduction.setUrl("reg/regAthlete02.xhtml");
        }
    }
    public void cancelReg(){
        childCancel();
    }
    public void childCancel(){
        regMgr.setNav(eNumReg.regBrw);
        
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
                 customerBean = new custObj().getcustomerBean(i, mgrVideoProduction.getDbBlazers());
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
         List<mgnLookupBean> l =  mgnLookupObj.getLookupList(i, mgrVideoProduction.getDbBlazers());
         if (i != 4){
             
            l.add(0,new mgnLookupBean());
         }
         return l;
    }
    public customerLinkBean getCustomerLinkBean() {
        if (customerLinkBean == null) {
            int i = regMgr.getAthletId();
            if (i > 0) {
                customerLinkBean = new custRegObj().getCustomerLinkBean(i, mgrVideoProduction.getDbBlazers());
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
        if (mgnLookupBean == null) {
            int i = regMgr.getCustomerRegBean().getRegLookupId();
            if (i > 0) {
                mgnLookupBean = new mgnLookupObj().getLookupBean(i, mgrVideoProduction.getDbBlazers());
            }
            if (mgnLookupBean == null) {
                mgnLookupBean = new mgnLookupBean();
            }
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
