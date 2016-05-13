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
import javax.faces.bean.RequestScoped;
import mgn.obj._beans.mgnLookupBean;
import mgn.obj.lookup.mgnLookupObj;

/**
 *
 * @author Lloyd
 */
@ManagedBean
@RequestScoped
public class _reg  extends mgrVideoProduction_EL  implements Serializable{
     private List<mgnLookupBean> 
            listShirt=null,
            listSuit=null,
            listShorts=null,
            listPayment=null,
            listReg=null;
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
            type = new mgnLookupObj().getLookupList(1, this.getDbBlazers());
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
         
         List<mgnLookupBean> l =  new mgnLookupObj().getLookupList(i, this.getDbBlazers());
         
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

}
