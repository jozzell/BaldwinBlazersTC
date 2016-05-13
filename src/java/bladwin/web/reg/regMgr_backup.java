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
import mgn.obj._beans.customerRegBean;
import mgn.obj._beans.mgnLookupBean;
import mgn.obj.cust.custObj;
import mgn.obj.cust.custObjCheck;
import mgn.obj.cust.custRegObj;
import mgn.obj.lookup.mgnLookupObj;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Lloyd
 */
@ManagedBean
@ViewScoped
public class regMgr_backup extends mgrVideoProduction_EL  implements Serializable{
    //private customerBean customerBean;
    private customerLinkBean customerLinkBean ;
    private customerBean customerBean;
    private trkRegBean trkRegBean;
    private String msg;
    private List<mgnLookupBean> sizeSuite;
    private List<mgnLookupBean> sizeShirt;
    private List<mgnLookupBean> sizeShorts;
    private List<mgnLookupBean> type;
    private List<mgnLookupBean> mgnLookupList = null;
    private mgnLookupBean mgnLookupBean;
    private customerRegBean customerRegBean;
    private boolean isNewCustomer=true;
    // ------------------------------------------------------------------
    
    private List<customerRegBean> regList;
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
        this.mgrVideoProduction.setAthletId(this.customerBean.getCustId());
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

    /**
     * @return the sizeSuite
     */
    public List<mgnLookupBean> getSizeSuite() {
        return sizeSuite;
    }

    /**
     * @param sizeSuite the sizeSuite to set
     */
    public void setSizeSuite(List<mgnLookupBean> sizeSuite) {
        this.sizeSuite = sizeSuite;
    }
    // ----------------------------------------------------------
    public void regAthlete(){
        msg = null;
        if (isCusteromBeanSelected()){
            mgrVideoProduction.setAthletId(this.customerBean.getCustId());
            genRegList();
            customerLinkBean = new customerLinkBean();
            if (this.regList != null && this.regList.size() > 0){
                customerLinkBean.setDob(regList.get(0).getDob());
                customerLinkBean.setType(regList.get(0).getType());
                if (regList.size() == 1 && regList.get(0).getRegId() == 0){
                    regList.remove(0);
                }
            }
            mgrVideoProduction.setUrl("reg/regAthlete01.xhtml");
            
        } else {
            RequestContext.getCurrentInstance().execute("PF('dialogWidgetbasicDialogBlockMsg').show()");
        }
        
        
    }
    public void regAthleteReset(){
        regList = new ArrayList<>();
        
    }
    
    private void genRegList(){
         int id = mgrVideoProduction.getAthletId();
         //this.regList = new custRegObj().getcustomerRegBeanList(id, mgrVideoProduction.getDbBlazers());
    }
   
     public List<customerRegBean> getRegList() {
        if (regList == null){
            regList = new ArrayList<customerRegBean>();
        }
        return regList;
    }

    /**
     * @param regList the regList to set
     */
    public void setRegList(List<customerRegBean> regList) {
        this.regList = regList;
    }
    public void childCancel(){
        mgrVideoProduction.setUrl("reg/userLogin01.xhtml");
        //genList();
    }
     public void regAthleteAdd(){
        regAthleteReset();
        genRegType();
        customerRegBean = new customerRegBean();
        mgnLookupBean = new mgnLookupBean();
        mgrVideoProduction.setUrl("reg/regAthlete02.xhtml");
    }
    public void regAthleteEdit(){
        
    }
    public void regAthleteBack(){
        regAthleteReset();
    }
    public void childAdd(){
        
        cleanup();
        mgrVideoProduction.setAthletId(0);
        
        child();
    }
    private void cleanup(){
        customerBean = new customerBean();
        customerLinkBean = new customerLinkBean();
        
    }
    public void childEdit(){
        if (!isCusteromBeanSelected()){
             msg = "Nothing was selectred";
             RequestContext.getCurrentInstance().execute("PF('dialogWidgetbasicDialogBlockMsg').show()");
             //mgrVideoProduction.forward();
        } else {
            mgrVideoProduction.setAthletId(customerBean.getCustId());
            child();
        }
    }
    private void child(){
        genAthleteType();
        mgrVideoProduction.setUrl("reg/userLogin02.xhtml");
    }
    public void childSave(){
        
        custObj custObj = new custObj();
        int i = custObj.createUser(customerBean, mgrVideoProduction.getDbBlazers());
        if (i != 0){
            new custRegObj().insertIntoLink(i,mgrVideoProduction.getParentId(), customerLinkBean, mgrVideoProduction.getDbBlazers());
            childCancel();
        }
        
        
    }
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
    public void chkAddr(){
        int i;
         msg =  new custObjCheck().chkNewUserAddr(getCustomerBean());
         if (getMsg() !=  null){
            RequestContext.getCurrentInstance().execute("PF('dialogWidgetbasicDialogBlockMsg_2').show()");
        } else {
             custObj custObj = new custObj();
             i =  custObj.createUser(getCustomerBean(), this.getDbBlazers());
             if (isNewCustomer){
                 new custRegObj().insertIntoLink(i,i, getCustomerLinkBean(),mgrVideoProduction.getDbBlazers());
             }
             isNewCustomer = false;
             //this.getCustomerBean().setCustId(i);
             this.mgrVideoProduction.setParentId(i);
             mgrVideoProduction.setUrl("reg/userLogin01.xhtml");
            
             
         }
    }
    
     private void genRegType(){
        mgnLookupList = new mgnLookupObj().getLookupList(4, mgrVideoProduction.getDbBlazers());
        if (mgnLookupList == null){
            mgnLookupList = new ArrayList<mgnLookupBean>();
        }
    }
     private void genAthleteType(){
        mgnLookupList = new mgnLookupObj().getLookupList(1, mgrVideoProduction.getDbBlazers());
        if (mgnLookupList == null){
            mgnLookupList = new ArrayList<mgnLookupBean>();
        }
    }
    public List<mgnLookupBean> getList(){
        if (mgnLookupList == null){
            genAthleteType();
            
        }
        return mgnLookupList;
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
}
