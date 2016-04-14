/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bladwin.web;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import mgn.obj._beans.mgnLookupBean;
import mgn.obj.images.imgObjV2;
import mgn.obj.lookup.mgnLookupObj;

import mgn.web.mgr.eNum.calObjMgrEnum;

@ManagedBean
@ViewScoped
public class imageDisplay_Maint extends mgrVideoProduction_EL implements Serializable {

    private List<mgnLookupBean> who,batch,list;
    private mgnLookupBean mgnLookupBean,mgnBatchLookupBean;
    
    @ManagedProperty("#{mgrVideoProduction}")
    private mgrVideoProduction mgrVideoProduction = null;
    public void setMgrVideoProduction(mgrVideoProduction mgrVideoProduction) {
        this.mgrVideoProduction = mgrVideoProduction;
    }

    public imageDisplay_Maint() {

    }
   
    public void saveWho(){
        (new imgObjV2()).updateLookupDesc(
                mgrVideoProduction.mgnMgrObj().getWho(), 
                mgrVideoProduction.mgnMgrObj().getRollupId(), 
                mgrVideoProduction.getDbBlazers());
         mgrVideoProduction.setVideoType(calObjMgrEnum.brwPg2_batch,1);
        
    }
    public void saveWho2(){
        (new imgObjV2()).updateLookupDesc(
                mgrVideoProduction.mgnMgrObj().getWho2(), 
                mgrVideoProduction.mgnMgrObj().getLookupId(), 
                mgrVideoProduction.getDbBlazers());
         mgrVideoProduction.setVideoType(calObjMgrEnum.brwPg3_view,2);
        
    }
    public List<mgnLookupBean> getList() {
        if (list == null) {
            list = (new imgObjV2()).getImageFilesLookup(
                    mgrVideoProduction.mgnMgrObj().getLookupId(),  
                    mgrVideoProduction.getDbBlazers());
            //batch = (new mgnLookupObj()).getLookupListTypeRollup(-2, mgnMgrObj.getRollupId(), mgnMgrObj.getObj());
        }
        return list;
    }

    public List<mgnLookupBean> getBatchlist() {
        if (batch == null) {
            batch = (new imgObjV2()).getImageFilesRollup(
                    mgrVideoProduction.mgnMgrObj().getRollupId(),  
                    mgrVideoProduction.getDbBlazers());
            //batch = (new mgnLookupObj()).getLookupListTypeRollup(-2, mgnMgrObj.getRollupId(), mgnMgrObj.getObj());
        }
        return batch;
    }

    public void addBatchList() {

    }

    // ==============================================================

    public List<mgnLookupBean> getWhoList() {
        if (who == null) {
            who = (new mgnLookupObj()).getLookupList(-1, mgrVideoProduction.getDbBlazers());
        }
        return who;
    }

    public void whoAdd() {
        mgrVideoProduction.mgnMgrObj().setLookupType(-1);
        mgrVideoProduction.mgnMgrObj().setLookupId(-1);
        mgrVideoProduction.setVideoType(calObjMgrEnum.brwPg1_whoEdit,0);
    }

    public void whoNext() {
        if (getMgnLookupBean() == null) {
            return;
        }
        System.out.println(getMgnLookupBean().getLookupId());

    }
    public void batchAdd() {
        mgrVideoProduction.batchAdd(false);
        mgrVideoProduction.setVideoType(calObjMgrEnum.brwPg2_batchEdit,1);
    }
   

    /**
     * @return the mgnLookupBean
     */
    public mgnLookupBean getMgnLookupBean() {
        return mgnLookupBean;
    }

    /**
     * @param mgnLookupBean the mgnLookupBean to set
     */
    public void setMgnLookupBean(mgnLookupBean mgnLookupBean) {
        if (mgnLookupBean != null) {
            mgrVideoProduction.mgnMgrObj().setRollupId(mgnLookupBean.getLookupId());
            mgrVideoProduction.mgnMgrObj().setLookupType(-2);
            mgrVideoProduction.mgnMgrObj().setLookupId(0);
            mgrVideoProduction.mgnMgrObj().setWho(mgnLookupBean.getLookupDesc());
            mgrVideoProduction.setVideoType(calObjMgrEnum.brwPg2_batch,1);
        }
        this.mgnLookupBean = mgnLookupBean;
    }
    public void back(){
         mgrVideoProduction.setVideoType(calObjMgrEnum.brwPg2_batch,1);
    }

   
    public mgnLookupBean getMgnBatchLookupBean() {
        return mgnBatchLookupBean;
    }

    public void setMgnBatchLookupBean(mgnLookupBean mgnBatchLookupBean) {
        if (mgnBatchLookupBean != null) {
            mgrVideoProduction.mgnMgrObj().setLookupId(mgnBatchLookupBean.getLookupId());
            mgrVideoProduction.mgnMgrObj().setWho2(mgnBatchLookupBean.getLookupDesc());
            mgrVideoProduction.setVideoType(calObjMgrEnum.brwPg3_view,2);
        }
        this.mgnBatchLookupBean = mgnBatchLookupBean;
    }
   

}
