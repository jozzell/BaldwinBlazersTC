/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bladwin.web;

import jvp.obj.video.uploadImages;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import jvp.obj.bean.lookupBean;
import jvp.obj.video.videoBean;
import jvp.obj.video.videoObj;
import org.primefaces.event.FileUploadEvent;

/**
 * server.xml
 * getvideoObj()getvideoObj()
 * @author i
 */
@ManagedBean
@ViewScoped
public class videoMgrObj  extends mgrVideoProduction_EL  implements Serializable {

    /**
     * @return the spinneyhill
     */
    
    private int videoType = 101,picked;
    private List<videoBean> list;
    private List<lookupBean> lookup;

    private videoObj videoObj;
    private uploadImages uploadImages;
    private String path = "";
    public String 
            videoCntrEditing = "pgVideo/videoViewPgEditing.xhtml",
            videoCntr    = "pgVideo/videoViewPgEditingBrw.xhtml";
            // videoCntr    = "pgVideo/videoViewPg.xhtml";
   
    
    public videoMgrObj(){
        
    }
    // ---------------------------------------------------------------
    @ManagedProperty("#{mgrVideoProduction}")
    private mgrVideoProduction mgrVideoProduction = null;
    public void setMgrVideoProduction(mgrVideoProduction mgrVideoProduction) {
        this.mgrVideoProduction = mgrVideoProduction;
    }
    @ManagedProperty("#{videoBean}")
    private videoBean videoBean;
    public void setVideoBean(videoBean pick) {
        this.videoBean = pick;
    }
    
    public String getHeader(){
        return mgrVideoProduction.getVideoGrpDesc();
    }
    public videoBean getVideoBean() {
        if (videoBean == null) genList();
        return videoBean;
    }
    public void addNewVideo(){
        videoBean = new videoBean();
        setSelected();
    }
     public void setSelected(){
        if (videoBean != null){
            mgrVideoProduction.setVideoId(videoBean.getVideoId());
            if (mgrVideoProduction.isLogin()){
                mgrVideoProduction.setVideoEditing(true);
                mgrVideoProduction.forwardCntr("pgVideo/videoEditingInfo_1.xhtml");
                //mgrVideoProduction.forwardCntr("pgVideo/videoEditingInfo.xhtml");
                
            } else {
                mgrVideoProduction.setVideoEditing(false);
                mgrVideoProduction.forwardCntr("pgVideo/videoViewPlayVideo.xhtml");
            }

        } else {
            System.err.println("public void selected(jvpLookupBean picked)");
        }
    }
     
    // ---------------------------------------------------------------
   
    
    private videoObj getvideoObj(){
        if (videoObj == null){
            videoObj = new videoObj();
            
        }
        return videoObj;
    }
    
    public void uploadFile(FileUploadEvent event){
        if (uploadImages == null) {
            path = getvideoObj().getPath(getObj())+mgrVideoProduction.getNewPath();
            uploadImages = new uploadImages(path,"img");
            System.out.println("path ="+path);
            new File(path).mkdirs();
        }
        try {
            String str = uploadImages.uploadFile(mgrVideoProduction.getCntr(),event);
            str = str.substring(str.indexOf("jvp"));
            this.getVideoBean().setImageName(str);
            getvideoObj().update(videoBean, getObj());
            mgrVideoProduction.forward();
        } catch (IOException ex) {
            Logger.getLogger(videoMgrObj.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
    public void selectChng2(){
        
        for (videoBean x: list){
            if (x.getVideoId() == picked){
                 videoBean = x;
                 break;
            }
        }
        mgrVideoProduction.forward();
      
    }
    
    public void switchVideo(int type,String html){
        switchVideo(type);
    }
    public void switchVideo(int type){
        videoType = type;
        genList();
        mgrVideoProduction.forwardCntr(mgrVideoProduction.isLogin() ? videoCntrEditing : videoCntr);
    }
    public List<videoBean> getList() {
        int x = mgrVideoProduction.getVideoType();
        if (list == null ||  mgrVideoProduction.isRefreshList() ) { //videoType != x ||
            mgrVideoProduction.setRefreshList(false);
            videoType = x;
            genList();
        }
        return list;
    }
    
    private synchronized void genList(){
        if (list != null) list.clear();
        picked = 0;
        list = (new videoObj()).getVideoBeanList(videoType, getObj());
        if (list != null && list.size() > 0){
            videoBean = list.get(0);
            //if (list.size() == 1){
            //    setSelected();
            //}
        } else {
            if (list == null) list = new ArrayList<videoBean>();
            videoBean = new videoBean();
            
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

    /**
     * @return the lookup
     */
    public List<lookupBean> getLookup() {
        if (lookup == null){
            lookup = new ArrayList<lookupBean>();
            lookup.add(new lookupBean(-11,"Flash (Youtube)"));
            lookup.add(new lookupBean(-12,"Quicktime (Vimeo)"));
        }
        return lookup;
    }
}
