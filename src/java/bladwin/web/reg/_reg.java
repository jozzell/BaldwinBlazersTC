/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bladwin.web.reg;

import bladwin.web.mgrVideoProduction;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import mgn.obj._beans.customerBean;
import mgn.obj._beans.customerRegBean;
import mgn.obj._beans.mgnLookupBean;
import mgn.obj.cust.custObj;
import mgn.obj.cust.custRegObj;
import mgn.obj.lookup.mgnLookupObj;

/**
 *
 * @author Lloyd
 */
@ManagedBean
@RequestScoped
public class _reg   implements Serializable{
     private List<customerRegBean> regList;
     private List<customerBean> custList;
     private List<mgnLookupBean> 
            listShirt=null,
            listSuit=null,
            listShorts=null,
            listPayment=null,
            listReg=null;
    private String path=null;
    private List<mgnLookupBean> type;
    private List<mgnLookupBean> athleteType;
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
    public List<mgnLookupBean> getType() {
        if (type == null){
            type = new mgnLookupObj().getLookupList(1, mgrVideoProduction.getDbBlazers());
        }
        return type;
    }
    // ------------------------------------------------------------------
    public List<mgnLookupBean> getAthleteType(){
        if (athleteType == null){
            athleteType = new mgnLookupObj().getLookupList(1, mgrVideoProduction.getDbBlazers());
        if (athleteType == null){
            athleteType = new ArrayList<mgnLookupBean>();
        }
            
        }
        return athleteType;
    }
    public synchronized List<mgnLookupBean> genList(int i){
         
         List<mgnLookupBean> l =  new mgnLookupObj().getLookupList(i, mgrVideoProduction.getDbBlazers());
         
         return l;
    }
    /**
     * @return the listShirt
     */
    public List<mgnLookupBean> getListShirt() {
        if (this.listShirt == null) listShirt = genList(7);
        return listShirt;
    }

    /**
     * @return the listSuit
     */
    public List<mgnLookupBean> getListSuit() {
        if (listSuit == null) listSuit = genList(6);
        return listSuit;
    }

    /**
     * @return the listReg
     */
    public List<mgnLookupBean> getListReg() {
        if (listReg == null)listReg  = genList(9);
        return listReg;
    }

    /**
     * @return the listShorts
     */
    public List<mgnLookupBean> getListShorts() {
        if ( listShorts== null)listShorts  = genList(5);
        return listShorts;
    }

    

    /**
     * @return the listPayment
     */
    public List<mgnLookupBean> getListPayment() {
        if ( listPayment== null) listPayment = genList(8);
        return listPayment;
    }
public List<customerRegBean> getRegList() {
        if (regList == null){
            int id = regMgr.getParentId();
            regList = new custRegObj().getCustomerRegBeanList_byRollup(id, mgrVideoProduction.getDbBlazers());
        }
        return regList;
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
        //if(this.getCustomerBean() != null) this.getCustomerBean().setCustId(0);
    }
    public void setCustList(List<customerBean> custList) {
        this.custList = custList;
    }

    /**
     * @return the path
     */
    public String getPath() {
        if (path == null){
            mgnLookupBean b = new mgnLookupObj().getLookupBean(-947, mgrVideoProduction.getDbBlazers());
            if (b != null){
                path = b.getSubjectText();
            }
        }
        return path;
    }
}
