/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bladwin.web.reg;

import bladwin.web.mgrVideoProduction;
import eMail.sendEMail;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.mail.MessagingException;
import mgn.obj._beans.customerBean;
import mgn.obj._beans.customerRegBean;
import mgn.obj._beans.mgnLookupBean;
import mgn.obj.cust.custRegObj;
import mgn.obj.lookup.mgnLookupObj;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Lloyd
 */
@ManagedBean
@ViewScoped
public class regMgrBrw   implements Serializable{
    
    //private List<customerRegBean> regList;
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
     public void sendEMail(){
        if (this.customerRegBean == null){
             msg = "No Registration was selected";
             RequestContext.getCurrentInstance().execute("PF('dialogWidgetbasicDialogBlockMsg').show()");
        } else {
            try {
                String[] mail = new String[2];
        mail[0] = "lmeans@optonline.com";
        mail[1] = "BaldwinBlazers@optonline.net";
                mgnLookupBean b = new mgnLookupObj().getLookupBean(-947,  mgrVideoProduction.getDbBlazers());
                new sendEMail().send(b.getSubjectText()+"/"+ customerRegBean.getPdf(),mail,"BaldwinBlazers@optonline.net","Registration Form from "+customerRegBean.getFullname());
                msg = "E-Mail Sent";
                
            } catch (MessagingException ex) {
                 msg = "Sorry, But your E-Mail was not sent, Please Download PDF and e-mail it to BaldwinBlazers@optonline.net";
                Logger.getLogger(regMgrBrw.class.getName()).log(Level.SEVERE, null, ex);
            }
            RequestContext.getCurrentInstance().execute("PF('dialogWidgetbasicDialogBlockMsg').show()");
             
        
        }
     }
    // ---------------------------------------------------------
    public void viewPdf(){
        if (this.customerRegBean == null){
             msg = "No Registration was selected";
             RequestContext.getCurrentInstance().execute("PF('dialogWidgetbasicDialogBlockMsg').show()");
        } else {
            
            this.regMgr.setCustomerRegBean(customerRegBean);
            int i = customerRegBean.getRegId();
            mgnLookupBean b = new mgnLookupObj().getLookupBean(-947, mgrVideoProduction.getDbBlazers());
            String str = regMgr.getPdfFileName();
            
            
            this.regMgr.getCustomerRegBean().setPdf(b.getLookupDesc()+"/"+str);
            new custRegObj().updateRegPdf(i,  this.regMgr.getCustomerRegBean().getPdf(), mgrVideoProduction.getDbBlazers());
            new regPDF().genPed(b.getSubjectText()+"/"+b.getLookupDesc()+"/"+str,this.regMgr,mgrVideoProduction.getDbBlazers());
            
            this.regMgr.setNav(eNumReg.regPdfView);
            mgrVideoProduction.setUrl("reg/regAthlete03.xhtml");
        }
    }
    public void editReg(){
        if (this.customerRegBean == null){
             msg = "No Registration was selected";
             RequestContext.getCurrentInstance().execute("PF('dialogWidgetbasicDialogBlockMsg').show()");
        } else {
            regMgr.setAthletId(customerRegBean.getCust_id());
            regMgr.setCustomerRegBean(customerRegBean);
            regMgr.setNav(eNumReg.reg_pg01);
            mgrVideoProduction.setUrl("reg/regAthlete01.xhtml");
        }
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
        this.regMgr.setNav(eNumReg.regAthlete);
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
            
            regMgr.setNav(eNumReg.reg_pg01);
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
