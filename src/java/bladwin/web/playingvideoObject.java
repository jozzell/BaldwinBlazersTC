/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bladwin.web;


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
import jvp.obj.video.uploadImages;
import jvp.obj.video.videoBean;
import jvp.obj.video.videoObj;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author lmeans
 */
@ManagedBean
@ViewScoped
public class playingvideoObject extends mgrVideoProduction_EL implements Serializable{
    private videoBean videoBean;
    private int videoId = 0;
    private List<lookupBean> lookup;
    private uploadImages uploadImages;
    private String path = "";
    public playingvideoObject(){
        
    }
    // ------------------------------------------------------------------------
    @ManagedProperty("#{mgrVideoProduction}")
    private mgrVideoProduction mgrVideoProduction = null;
    public void setMgrVideoProduction(mgrVideoProduction mgrVideoProduction) {
        this.mgrVideoProduction = mgrVideoProduction;
    }
    
    
    public String getNewImage(){
        if (mgrVideoProduction.getMisc() == null) return getVideoBean().getImageName();
        return mgrVideoProduction.getMisc();
    }
    public videoBean getVideoBean(){
        int i = mgrVideoProduction.getVideoId();
        if (videoBean == null|| i != videoId){
            videoId = i;
            videoObj videoObj = new videoObj();
            
            videoBean = videoObj.getvideoBean(i, this.getObj());
            if (videoBean == null) {
                videoBean = new videoBean();
                String str = mgrVideoProduction.getMisc();
                videoBean.setImageName(str);
                if (str != null) str = str.substring(str.indexOf("jvp"));
                this.getVideoBean().setImageName(str);
            }
            //if (mgrVideoProduction.isVideoEditing())videoBean.setVideoDesc(mgrVideoProduction.getMisc());
            videoBean.setGrpDesc(mgrVideoProduction.getVideoGrpDesc());
        }
        return videoBean;
    }
    public void back(){
        //if (mgrVideoProduction.getVideoType() == 208){
         //   mgrVideoProduction.setSpinneyHill();
        //} else {
            mgrVideoProduction.setVideoType();
            //mgrVideoProduction.setVideoType();
        //}
        //
        
    }
     public void save(){
        System.err.println("welcome");  
        videoObj videoObj = new videoObj();
        videoBean.setVideoType(mgrVideoProduction.getVideoType());
        if (mgrVideoProduction.getMisc() != null){
            videoBean.setImageName(mgrVideoProduction.getMisc());
        }
        String str = videoBean.getVideoValue();
        if (str != null && str.lastIndexOf("/")> 0){
           str = str.substring(str.lastIndexOf("/"));
        } else if (str != null && str.lastIndexOf("=")> 0){
              str = str.substring(str.lastIndexOf("="));
        }
        videoBean.setVideoValue(str);
        videoObj.update(videoBean, getObj());
        //mgrVideoProduction.forward();
        back();
        
     }
    public void next(){
        System.err.println("welcome");
        mgrVideoProduction.forwardCntr("pgVideo/videoEditingInfo_2.xhtml");
        
    }
    public List<lookupBean> getLookup() {
        if (lookup == null){
            lookup = new ArrayList<lookupBean>();
            lookup.add(new lookupBean(-11,"Flash (Youtube)"));
            lookup.add(new lookupBean(-12,"Quicktime (Vimeo)"));
        }
        return lookup;
    }
    public void uploadFile(FileUploadEvent event){
        videoObj videoObj = new videoObj();
        if (uploadImages == null) {
            path =videoObj.getPath(getObj())+mgrVideoProduction.getNewPath();
            uploadImages = new uploadImages(path,"img");
            System.out.println("path ="+path);
            new File(path).mkdirs();
        }
        try {
            String str = uploadImages.uploadFile(mgrVideoProduction.getCntr(),event);
            
            str = str.substring(str.indexOf("jvp"));
            mgrVideoProduction.setMisc(str.toLowerCase());
            this.getVideoBean().setImageName(str.toLowerCase());
            //videoObj.update(videoBean, getObj());
            //mgrVideoProduction.forward();
            mgrVideoProduction.forwardCntr("pgVideo/videoEditingInfo_2.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(videoMgrObj.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
