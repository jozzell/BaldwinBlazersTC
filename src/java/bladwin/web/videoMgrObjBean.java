/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bladwin.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jvp.obj.video.videoBean;
import jvp.obj.video.videoObj;

/**
 *
 * @author lmeans
 */
public class videoMgrObjBean extends mgrVideoProduction_EL implements Serializable{
    private int videoType = 100,picked;
    private List<videoBean> list;
    private videoBean videoBean;
    mgrVideoProductionInterface iface;
    
    public void selectChng2(){
        for (videoBean x: list){
            if (x.getVideoId() == picked){
                 videoBean = x;
                 break;
            }
        }
      
    }
    public videoMgrObjBean(mgrVideoProductionInterface iface,int type){
        this.iface = iface;
        videoType = type;
    }
    public List<videoBean> getList() {
        if (list == null) genList();
        return list;
    }
    public videoBean getVideoBean() {
        if (videoBean == null) genList();
        return videoBean;
    }
    private void genList(){
        list = (new videoObj()).getVideoBeanList(videoType, getObj());
        if (list != null && list.size() > 0){
            videoBean = list.get(0);
        } else {
            if (list == null) list = new ArrayList<videoBean>();
            videoBean = new videoBean();
            videoBean.setImageName("default.png");
        }
        
    }

    /**
     * @return the picked
     */
    public int getPicked() {
        return picked;
    }

    /**
     * @param picked the picked to set
     */
    public void setPicked(int picked) {
        this.picked = picked;
    }
}
