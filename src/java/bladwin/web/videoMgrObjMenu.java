/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bladwin.web;
import java.io.Serializable;
import jvp.obj.eNum.eNumVideoIDs;
/**
 *
 * @author lmeans
 */
public class videoMgrObjMenu implements Serializable{
    videoMgrObjMenuInterface iface;
   
    public videoMgrObjMenu(videoMgrObjMenuInterface i){
        iface = i;
    }
    
    
    // ==============================
   public void setTrackClubIndoor(){
        iface.setVideoType(eNumVideoIDs.TrackClubIndoor);
        //iface.setVideoType(97,"Baldwin Blazers (Indoor)");
    }
   public void setTrackClubOutdoor(){
       iface.setVideoType(eNumVideoIDs.TrackClubOutdoor);
        //iface.setVideoType(1113,"Baldwin Blazers (Outdoor)");
    }
   public void setTrackClubAAU(){
       iface.setVideoType(eNumVideoIDs.TrackClubAAU);
        //iface.setVideoType(1114,"Baldwin Blazers (AAU)");
    }
   
     public void calEOC(){
         iface.setVideoType(eNumVideoIDs.EOC);
         //iface.setVideoType(1107, "cal/church_EOC.xhtml");
        
    }
    public void calFirst(){
        iface.setVideoType(eNumVideoIDs.FirstBaptist);
         //iface.setVideoType(1106, "Royal Priesthool (Anniversary)");
       
    }
    public void calFirst2009(){
        iface.setVideoType(eNumVideoIDs.FirstBaptist2009);
         //iface.setVideoType(1206, "Gospel Concert (2010)");
       
    }
    public void calMount(){
        iface.setVideoType(eNumVideoIDs.MountOlive);
         //iface.setVideoType(1105, "cal/church_mtolive.xhtml");
        
        
    }
    
     
    // ==============================
    public void setHearAndNow(){
        iface.setVideoType(eNumVideoIDs.HearAndNow);
        //iface.setVideoType(107,"Jozzell Video Production");
    }
    public void setShirley(){
        iface.setVideoType(eNumVideoIDs.ShirleyPublicAccess);
        //iface.setVideoType(108,"Jozzell Video Production");
    }
    // ==============================
   
    public void setVideoSports(){
        iface.setVideoType(eNumVideoIDs.VideoSports);
        //iface.setVideoType(102,"Football Highlight Real");
    }
    public void setVideoSportsGame(){
        iface.setVideoType(eNumVideoIDs.VideoSportsGame);
        //iface.setVideoType(106,"Jozzell Video Production");
    }
    // ==============================
    public void setHome(){
        iface.setHome();
        //iface.setVideoType(101,"Events");
    }
    public void setAboutUs(){
        iface.msgAboutUs();
        //iface.setVideoType(101,"Events");
    }
     public void setVideoSweet16(){
         iface.setVideoType(eNumVideoIDs.VideoSweet16);
        //iface.setVideoType(100,"Jozzell Video Production");
    }
    public void setEventsRedCarpet(){
        iface.setVideoType(eNumVideoIDs.EventsRedCarpet);
       // iface.setVideoType(105,"Jozzell Video Production");
    }
    
    // ==============================
    public void setCommunityPicnic(){
        iface.setVideoType(eNumVideoIDs.CommunityPicnic);
       // iface.setVideoType(103,"Jozzell Video Production");
    }
    public void setCommunityAwardCeremony(){
        iface.setVideoType(eNumVideoIDs.CommunityAwardCeremony);
      //  iface.setVideoType(104,"Jozzell Video Production");
    }
    // ==============================
    public void setMsRucker(){
        iface.setVideoType(eNumVideoIDs.MsRucker);
        //iface.setVideoType(201,"Jozzell Video Production");
    }
    public void setMrClean(){
        iface.setVideoType(eNumVideoIDs.MrClean);
        //iface.setVideoType(200,"Jozzell Video Production");
    }
   
    public void setAnaysha(){
        iface.setVideoType(eNumVideoIDs.Anaysha);
        //iface.setVideoType(202,"Jozzell Video Production");
    }
    
     public void set22nd(){
         iface.setVideoType(eNumVideoIDs.VOI22nd);
        //iface.setVideoType(203,"22nd Anniversary (2011)"  );
    }
    public void set23nd(){
        iface.setVideoType(eNumVideoIDs.VOI23nd);
        //iface.setVideoType(204,"23rd Anniversary (2012)");
    }
    public void set24nd(){
        iface.setVideoType(eNumVideoIDs.VOI24nd);
        //iface.setVideoType(205,"24th Anniversary (2013)");
    }
    public void set25nd(){
        iface.setVideoType(eNumVideoIDs.VOI25nd);
        //iface.setVideoType(99,"25th Anniversary (2014) Brooklyn Marriott");
    }
    public void set26nd(){
        iface.setVideoType(eNumVideoIDs.VOI26nd);
        //iface.setVideoType(98,"26th Anniversary (2015)");
    }
    // ==============================
     public void setSpinMain(){
         iface.setVideoType(eNumVideoIDs.SpinMain);
        //iface.setVideoType(206,"Jozzell Video Production");
    }
    public void setSpinBonus(){
        iface.setVideoType(eNumVideoIDs.SpinBonus);
        //iface.setVideoType(208,"Jozzell Video Production");
    }
    public void setLeone(){
        iface.setVideoType(eNumVideoIDs.Leone);
        //iface.setVideoType(207,"Jozzell Video Production");
    }
    // ==============================
    public void setAdds(){
        iface.setVideoType(eNumVideoIDs.Adds);
        //iface.setVideoType(210,"Jozzell Video Production");
    }
    
}
