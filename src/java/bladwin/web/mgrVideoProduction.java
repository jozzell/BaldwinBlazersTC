/*

Download FastStone Photo Resizer
http://www.faststone.org/FSResizerDownload.htm


 */

package bladwin.web;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import jvp.obj.bean.userBean;
import jvp.obj.eNum.eNumVideoIDs;
import mgn.obj.images.mgnMgrObjBean;
import mgn.obj.usr.userObj;
import mgn.web.mgr.eNum.calObjMgrEnum;
import org.primefaces.context.RequestContext;

/**
 *
 * @author lmeans
 */

@ManagedBean(name = "mgrVideoProduction")   
@SessionScoped
public final class mgrVideoProduction extends mgrVideoProduction_EL implements videoMgrObjMenuInterface,Serializable {
    private mgnMgrObjBean mgnMgrObjBean;
    //private videoMgrObj videoMgrObj;
    private videoMgrObjMenu videoMgrObjMenu;
    private boolean start = true,login=false,videoEditing=false,refreshList=true,superbowl=false;
    private String newPath = null,password;
    private userBean userBean;
    private int athletId,parentId;
    //private customerBean customerBean;
    private final String               
            panelWest="./menu.xhtml";
     private String 
             misc,
            url="videoViewPgEditingBrw-Top.xhtml",
            panelCenter= null;
    private int cntr=0,videoSysId,videoId,selectId;//videoType=208,
    public eNumVideoIDs Current_eNumVideoIDs;
    public mgrVideoProduction(){
         refreshSession();
    }
    
    // =============================================================================
        public void regNewUsers(){
            url = "reg/userLogin00.xhtml";
            setVideoType();
        }
    // =============================================================================
    public void superbowl(){
        if (this.superbowl || this.isLogin()){
            url = "donation/superbowl.xhtml";
        } else {
            url = "donation/superbowlView.xhtml";
        }
        
         setVideoType();
    }
    public String getPassword() {
        return password;
    }

   
    public void setPassword(String password) {
        this.password = password;
    }
    public void checkPassword(){
        if (password != null || password.trim().toLowerCase().endsWith("blazers2016")){
            this.superbowl = true;
            password = null;
        }
        superbowl();
    }
    // -------------------------------------------------------------
    public void userLogin(){
        getUserBean().setUserPass(null);
        forwardCntr("user/login.xhtml");
    }
    public void chkLogin(){
         userObj userObj = new userObj();
         String email = this.getUserBean().getEMail();
         this.userBean = userObj.login(getUserBean().getEMail(), getUserBean().getUserPass(), this.getDbBlazers());
         if(userBean != null) {
             getUserBean().setUserPass(null);
             login = true;
             this.setHome();
         } else {
             this.getUserBean().setEMail(email);
             login = false;
             RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
             
         }
     }
    public void userLogout(){
        this.login = false;
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        try {
            ec.redirect(ec.getRequestContextPath());
        } catch (IOException ex) {
            Logger.getLogger(mgrVideoProduction.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.forward();
       
    }
     public boolean isLogin() {
        return login;
    }
    // ==========================================
    public void msgView(){
        this.selectId=1;
         url = "pgCal/BaldwinBlazerMsg.xhtml";
         setVideoType();
    }
    public void msgViewAdd(){
        this.selectId=0;
          url = "pgCal/BaldwinBlazerMsgEdit.xhtml";
         setVideoType();
    }
    public void msgViewEdit(int i){
        this.selectId=i;
          url = "pgCal/BaldwinBlazerMsgEdit.xhtml";
         setVideoType();
    }
    public void msgViewBrw(){
        this.selectId=0;
          url = "pgCal/BaldwinBlazerMsgEditBrw.xhtml";
         setVideoType();
        
    }
     public void msgPost(){
         this.selectId=0;
          url = "pgCal/PostingNew.xhtml";
         setVideoType();
     }
     public void msgEdit(int id){
          this.selectId=id;
          url = "pgCal/PostingEdit.xhtml";
         setVideoType();
     }
     public void msgBrw(){
         this.selectId=0;
         url = "pgCal/BaldwinBlazerHome_EditBrw.xhtml";
         setVideoType();
     }
    // --------------------------------------------------------------
    
     public void msgAboutUsEdit(){
         url = "pgCal/BaldwinBlazerAboutUsEdit.xhtml";
         setVideoType();
     }
    
    // ==========================================
    public void setVideoId(int i){
        this.videoId = i;
       
    }
    public int getVideoId(){
        return videoId;
    }
    public String getVideoGrpDesc(){
        return this.Current_eNumVideoIDs.getTitle();
    }
    @Override
     public void msgAboutUs(){

         url = "pgCal/BaldwinBlazerHome.xhtml";
        setVideoType();
     }
    @Override
    public void setHome(){
        url = "pgCal/BaldwinBlazerAboutUs.xhtml";
         setVideoType();
    }
    
    public void setSpinneyHill(){
        setVideoType(Current_eNumVideoIDs.SpinMain);
        
    }
    
    public void setVideoType(calObjMgrEnum e,int nav){
        url = e.getUrl();
        mgnMgrObj().setNavPick(nav);
        setVideoType();
    }
     @Override
     public void setVideoType(eNumVideoIDs x) {
         refreshList = true;
         Current_eNumVideoIDs = x;
         //setVideoType();
         switch(x.getBrwDisplayType()){
             case 1:
                 url = "VideoList_Grid.xhtml";
                 //forwardCntr("pgVideo/VideoList_Grid.xhtml");
                 break;
             default:
                 url = "videoViewPgEditingBrw-Top.xhtml";
                 //forwardCntr("pgVideo/videoViewPgEditingBrw.xhtml");
                 break;
         }
         setVideoType();
         
     }
   
    public void setVideoType() {
        System.out.println(url);
        forwardCntr("pgVideo/videoViewPgEditingBrw.xhtml");
        //forwardCntr(url);
    }
    
    public int getVideoType() {
        return Current_eNumVideoIDs.getId();
    }
    @Override
    public void setVideoSysId(int videoSysId) {
        this.videoSysId = videoSysId;
        this.forwardCntr("pgVideo/videoBrwEvents.xhtml");
    }
    public int getVideoSysId() {
        return videoSysId;
    }
    
    // ==========================================
    public int getCntr(){
        return cntr++;
    }
    public int refreshSessionCnt(){
        return cntr;
    }
    
    public String getSpinnellHillImage(){
        return "/jvp/spinneyHill_Cover.jpg";
    }
    public String getJvpInclude(){
        if (!start){
            return "jvp_include.xhtml";
        } else {
            return "jvp_include_start.xhtml";
        }
    }
   
    
    
    public void forward() {
       
        //prefix= "";
        start= false;
        (new factoryObj()).FacesContextForward("/TrackClub", FacesContext.getCurrentInstance());
    }
    
     public void forwardCntr(String center){
         panelCenter = center;
         System.out.println(center);
         forward();
     }
    
    
    
    public videoMgrObjMenu getVideoMgrObjMenu() {
        if (videoMgrObjMenu == null) videoMgrObjMenu = new videoMgrObjMenu(this);
        return videoMgrObjMenu;
    }

    /**
     * @return the panelWest
     */
    public String getPanelWest() {
        return panelWest;
    }

    /**
     * @return the panelCenter
     */
    public String getPanelCenter() {
        if (panelCenter == null){
            setHome();
        }
        return panelCenter;
    }

    /**
     * @return the login
     */
  
   

    /**
     * @return the newPath
     */
   
    public String getNewPath() {
       if (newPath == null)newPath = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime());
        //if (newPath == null)newPath = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
        return "/"+newPath;
    }

    /**
     * @return the misc
     */
    public String getMisc() {
        return misc;
    }

    /**
     * @param misc the misc to set
     */
    public void setMisc(String misc) {
        this.misc = misc;
    }

    /**
     * @return the videoEditing
     */
    public boolean isVideoEditing() {
        return videoEditing;
    }

    /**
     * @param videoEditing the videoEditing to set
     */
    public void setVideoEditing(boolean videoEditing) {
        this.videoEditing = videoEditing;
        misc = null;
    }

    /**
     * @return the userBean
     */
    public userBean getUserBean() {
        if (userBean == null){
            userBean = new userBean();
        }
        return userBean;
    }

    /**
     * @return the selectId
     */
    //public int getSelectId() {
    //    return selectId;
    //}

    /**
     * @param selectId the selectId to set
     */
    public void setSelectId(int selectId) {
        this.selectId = selectId;
    }
    public int getSelectId(){
        return this.selectId;
    }

    /**
     * @return the refreshList
     */
    public boolean isRefreshList() {
        return refreshList;
    }

    /**
     * @param refreshList the refreshList to set
     */
    public void setRefreshList(boolean refreshList) {
        this.refreshList = refreshList;
    }

    /**
     * @param url
     * @return the url
     */
    public void setUrl(String url){
        this.url = url;
        setVideoType();
    }
    public String getUrl() {
        return url;
    }

    
    public  synchronized void batchAdd(boolean add) {
        
    }
    public mgnMgrObjBean mgnMgrObj() {
        if (mgnMgrObjBean == null){
            mgnMgrObjBean = new mgnMgrObjBean();
        }
        return mgnMgrObjBean;
    }

    /**
     * @return the customerBean
     */
    

    /**
     * @return the athletId
     */
    public int getAthletId() {
        return athletId;
    }

    /**
     * @param athletId the athletId to set
     */
    public void setAthletId(int athletId) {
        this.athletId = athletId;
    }

    /**
     * @return the parentId
     */
    public int getParentId() {
        return parentId;
    }

    /**
     * @param parentId the parentId to set
     */
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    /**
     * @return the password
     */
    
   
   
     


   

    
    

   
}

