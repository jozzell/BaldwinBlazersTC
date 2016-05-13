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
@SessionScoped
public class regMgr extends mgrVideoProduction_EL  implements Serializable{
    private customerBean customerBean;
    //private List<customerBean> custList;
    //private List<customerRegBean> regList;
    //private customerRegBean customerRegBean;
    //private customerLinkBean customerLinkBean ;
    private String msg,passwd;
    private int pgNum=0,athletId=0;
    private boolean isNewCustomer=true;
    private customerLinkBean customerLinkBean;
    private List<mgnLookupBean> mgnLookupList = null;
    private mgnLookupBean mgnLookupBean;
    private customerRegBean customerRegBean;
    
    // ---------------------------------------------------------------
    @ManagedProperty("#{mgrVideoProduction}")
    private mgrVideoProduction mgrVideoProduction = null;
    public void setMgrVideoProduction(mgrVideoProduction mgrVideoProduction) {
        this.mgrVideoProduction = mgrVideoProduction;
    }
    
    
    // ------------------------------------------------------------------
    public int getParentId(){
        return this.getCustomerBean().getCustId();
    }
    public void logout(){
        this.customerBean = new customerBean();
        setCustomerLinkBean(new customerLinkBean());
        isNewCustomer = true;
        this.customerBean.setCustId(0);
    }
   
    // ------------------------------------------------------------------
    public void cancelParentEditing(){
        
        if (this.customerBean.getCustId() == 0){
            mgrVideoProduction.regNewUsers();
            
        } else {
            customerBean = new custObj().getcustomerBean(this.getCustomerBean().getCustId(), this.getDbBlazers());
            mgrVideoProduction.setUrl("reg/userLogin01.xhtml");
        }
    }
    public void editParent(){
        customerBean = new custObj().getcustomerBean(this.getCustomerBean().getCustId(), this.getDbBlazers());
        parent();
    }
    private void parent(){
        pgNum = 0;
       mgrVideoProduction.setUrl("reg/regNewPg00.xhtml");
    }
    public void newParent(){
       customerBean = new customerBean();
       parent();
       
    }
    
    
    public void login() {
        msg = null;
        String password = this.getCustomerBean().getUserPass();
        String email = this.getCustomerBean().getEMail();
        if (password == null || password.trim().length() == 0) {
            msg = "Missing Password";
        } else if (email == null || email.trim().length() == 0) {
            msg = "Missing eMail";
        } else {
            custObj custObj = new custObj();
            customerBean = custObj.getcustomerBeanEMail(email,getCustomerBean().getCustId(), mgrVideoProduction.getDbBlazers());
            if (customerBean == null) {
                msg = "Invaled EMail";
                this.getCustomerBean().setEMail(email);
            } else {
                if (!customerBean.getUserPass().toLowerCase().trim().contains(password.trim().toLowerCase())) {
                    customerBean.setCustId(0);
                    //customerBean.setUserPass(null);
                    msg = "Invalid Password";
                } else {
                    mgrVideoProduction.setParentId(customerBean.getCustId());
                }
            }
        }
        if (msg != null) {
            RequestContext.getCurrentInstance().execute("PF('dialogWidgetbasicDialogBlockMsg').show()");
            //mgrVideoProduction.forward();
        } else {
            mgrVideoProduction.setUrl("reg/userLogin01.xhtml");
           
        }

    }
   
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
            int i = mgrVideoProduction.getParentId();
            if (i == 0){
                customerBean = new customerBean();
            }
            //customerBean = this.mgrVideoProduction.getCustomerBean();
        }
        return customerBean;
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

    /**
     * @param customerBean the customerBean to set
     */
    public void setCustomerBean(customerBean customerBean) {
        this.customerBean = customerBean;
    }
    // -------------------------------------------------------------------------
    public void chkAddrBack(){
        this.pgNum = 0;
        this.mgrVideoProduction.forward();
    }
    
    public  void chkNewUser(){
        msg =  new custObjCheck().chkNewUser(getCustomerBean());
        if (getMsg() == null){
            custObj custObj = new custObj();
            int i = custObj.getCustID(this.getCustomerBean().getEMail(),this.getCustomerBean().getCustId(), this.getDbBlazers());
            if (i > 0){
                msg = "E-Mail All Ready Exist";
                
            }
        }
        if (msg != null){
            RequestContext.getCurrentInstance().execute("PF('dialogWidgetbasicDialogBlockMsg').show()");
        } else {
            this.passwd = this.getCustomerBean().getUserPass();
            this.pgNum=1;
            mgrVideoProduction.forward();
        }
    }
 public void chkAddr(){
        int i;
         msg =  new custObjCheck().chkNewUserAddr(getCustomerBean());
         if (getMsg() !=  null){
            RequestContext.getCurrentInstance().execute("PF('dialogWidgetbasicDialogBlockMsg_2').show()");
        } else {
             custObj custObj = new custObj();
             String str = this.getCustomerBean().getUserPass();
             if (str == null || str.trim().length() == 0){
                 this.getCustomerBean().setUserPass(this.passwd);
             }
             i =  custObj.createUser(getCustomerBean(), this.getDbBlazers());
             this.getCustomerBean().setCustId(i);
             if (isNewCustomer){
                 new custRegObj().insertIntoLink(i,i, getCustomerLinkBean(),mgrVideoProduction.getDbBlazers());
             }
             isNewCustomer = false;
             //this.getCustomerBean().setCustId(i);
             this.mgrVideoProduction.setParentId(i);
             mgrVideoProduction.setUrl("reg/userLogin01.xhtml");
            
             
         }
    }
    // ---------------------------------------------------------------------------------------
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
   
    /**
     * @return the custList
     */
    

    /**
     * @return the customerBeanChild
     */
    public customerBean getCustomerBeanChild() {
        if (customerBean == null){
            int i = mgrVideoProduction.getAthletId();
            if (i != 0){
                customerBean = new custObj().getcustomerBean(i, mgrVideoProduction.getDbBlazers());
            }
            if (customerBean == null) customerBean = new customerBean();
        }
        return customerBean;
    }

    /**
     * @param customerBeanChild the customerBeanChild to set
     */
    public void setCustomerBeanChild(customerBean customerBeanChild) {
        this.customerBean = customerBeanChild;
    }

    /**
     * @return the regList
     */
   

   

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
     * @return the athletId
     */
    public int getAthletId() {
        return athletId;
    }

    /**
     * @param athletId the athletId to set
     */
    public void setAthletId(int athletId) {
        this.athletId = athletId;
    }

    /**
     * @return the customerLinkBean
     */
    public customerLinkBean getCustomerLinkBean() {
        if (customerLinkBean == null){
            customerLinkBean = new customerLinkBean();
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
     * @return the customerRegBean
     */
    public void resetRegBean(){
        customerRegBean = new customerRegBean();
        customerRegBean.setCust_id(this.athletId);
    }
    public customerRegBean getCustomerRegBean() {
        if (customerRegBean == null) resetRegBean();
        return customerRegBean;
    }

    
}
