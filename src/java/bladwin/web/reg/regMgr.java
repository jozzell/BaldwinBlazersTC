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
    private List<customerBean> custList;
    private List<customerRegBean> regList;
    private customerRegBean customerRegBean;
    private customerLinkBean customerLinkBean ;
    private String msg;
    private int pgNum=0;
    private boolean isNewCustomer=true;
    private List<mgnLookupBean> mgnLookupList = null;
    private mgnLookupBean mgnLookupBean;
    
    
    // ---------------------------------------------------------------
    @ManagedProperty("#{mgrVideoProduction}")
    private mgrVideoProduction mgrVideoProduction = null;
    public void setMgrVideoProduction(mgrVideoProduction mgrVideoProduction) {
        this.mgrVideoProduction = mgrVideoProduction;
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
    // ------------------------------------------------------------------
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
    private void genRegType(){
        mgnLookupList = new mgnLookupObj().getLookupList(4, mgrVideoProduction.getDbBlazers());
        if (mgnLookupList == null){
            mgnLookupList = new ArrayList<mgnLookupBean>();
        }
    }
    // ------------------------------------------------------------------
    public void newParent(){
       
        mgrVideoProduction.setUrl("reg/regNewPg00.xhtml");
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
            custObj.insertIntoLink(i,mgrVideoProduction.getParentId(), customerLinkBean, mgrVideoProduction.getDbBlazers());
            childCancel();
        }
        
        
    }
    public void childCancel(){
        mgrVideoProduction.setUrl("reg/userLogin01.xhtml");
        genList();
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
            customerBean = custObj.getcustomerBeanEMail(email, mgrVideoProduction.getDbBlazers());
            if (customerBean == null) {
                msg = "Invaled EMail";
                this.getCustomerBean().setEMail(email);
            } else {
                if (!customerBean.getUserPass().toLowerCase().trim().contains(password.trim().toLowerCase())) {
                    customerBean.setCustId(0);
                    customerBean.setUserPass(null);
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
             //this.getCustomerBean().setCustId(i);
             this.mgrVideoProduction.setParentId(i);
             mgrVideoProduction.setUrl("reg/userLogin01.xhtml");
            
             
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

    /**
     * @return the custList
     */
    public List<customerBean> getCustList() {
        if (custList == null){
             genList();
        }
        return custList;
    }
    private void genRegList(){
         int id = mgrVideoProduction.getAthletId();
         this.regList = new custRegObj().getcustomerRegBean(id, mgrVideoProduction.getDbBlazers());
    }
    private void genList(){
        int id = mgrVideoProduction.getParentId();
        custList = new custObj().getcustomerList_link(id, mgrVideoProduction.getDbBlazers());
        if (custList == null) custList = new ArrayList<customerBean>();
        if(this.customerBean != null) this.customerBean.setCustId(0);
    }

    /**
     * @param custList the custList to set
     */
    public void setCustList(List<customerBean> custList) {
        this.custList = custList;
    }

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
}
