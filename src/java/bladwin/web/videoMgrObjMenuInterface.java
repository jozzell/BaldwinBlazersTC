/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bladwin.web;

import jvp.obj.eNum.eNumVideoIDs;

/**
 *
 * @author lmeans
 */
public interface videoMgrObjMenuInterface {
    //public abstract videoMgrObj getVideoMgrObj();
    //public abstract void setVideoType(int x);
    //public abstract void setVideoType(int x,String Desc);
    public void setHome();
    public void msgAboutUs();
    public abstract void setVideoType(eNumVideoIDs vid);
    public void setVideoSysId(int videoSysId);
    //
}
