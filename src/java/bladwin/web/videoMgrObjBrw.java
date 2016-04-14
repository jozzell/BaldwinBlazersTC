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
import jvp.obj.video.jvpLookupBean;
import jvp.obj.video.jvpLookupObj;

/**
 *
 * @author lmeans
 */
@ManagedBean
@ViewScoped
public class videoMgrObjBrw extends mgrVideoProduction_EL implements Serializable{
    private  List<jvpLookupBean> list;
    private final jvpLookupObj jvpLookupObj;
    private int videoSysId;
    
    public videoMgrObjBrw(){
        jvpLookupObj = new jvpLookupObj();
    }
    // ---------------------------------------------------------------
    @ManagedProperty("#{jvpLookupBean}")
    private jvpLookupBean jvpLookupBean;
    public void setJvpLookupBean(jvpLookupBean pick) {
        this.jvpLookupBean = pick;
    }
    
    @ManagedProperty("#{mgrVideoProduction}")
    private mgrVideoProduction mgrVideoProduction = null;
    public void setMgrVideoProduction(mgrVideoProduction mgrVideoProduction) {
        this.mgrVideoProduction = mgrVideoProduction;
    }
    // ---------------------------------------------------------------
    public List<jvpLookupBean> getBrowseList(){
        int x = mgrVideoProduction.getVideoSysId();
        if (list == null || x != videoSysId){
           this.videoSysId = x;
           list = jvpLookupObj.getLookupListBySysId(videoSysId, getObj());
        }
        return list;
    }
    public void setSelected(){
        //mgrVideoProduction.setSelectId(videoSysId);
        mgrVideoProduction.setSelectId(this.jvpLookupBean.getLookupType());
    }
    public void setSelectedBean(jvpLookupBean jvpLookupBean){
        if (jvpLookupBean != null){
        System.err.println(jvpLookupBean.getLookupDesc());
        } else {
            System.err.println("public void selected(jvpLookupBean picked)");
        }
    }
}
