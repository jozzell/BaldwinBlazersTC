/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.eNum.maint;

import app.mgr.webMgr;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import jvp.obj.bean.lookupBean;
import jvp.obj.bean.scheduleBean;
import jvp.obj.eNum.eNumDayOfWeek;
import jvp.obj.eNum.eNumWeekInd;

import mgn.obj.cal.calendarSql;
import obj.db.v1.dbMgrInterface;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;
import org.apache.log4j.Logger;
//import org.apache.log4j.Logger;
import sun.jdbc.rowset.CachedRowSet;

/**
 *
 * @author lmeans
 */
@ManagedBean
@ViewScoped
public class calMaintMgr implements Serializable {
    private Date startDate, endDate;
    private ScheduleModel eventModel;
    public  final Logger logger = (Logger) Logger.getLogger(calMaintMgr.class);
    
    
    @ManagedProperty("#{webMgr}")
    private webMgr webMgr = null;
    public void setMgnMgrObj(webMgr webMgr) {
        this.webMgr = webMgr;
    }
    public ScheduleModel getEventModel() {
        if (eventModel == null) {
            eventModel = genScheduleModel(webMgr.getObj());
        }
        return eventModel;
    }
    
   
    public void save() {

    }

    public void back() {

    }
    public void getPreview(){
        
    }
    public void previewEdit() {

    }

    public void previewAdd() {

    }

    public void previewPublish() {

    }
    // ======================================================
    // BELOW ARE GOING TO USE TO  EXTEND CLASS
    // ======================================================
     public ScheduleModel genScheduleModel(dbMgrInterface db) {
         calendarSql calendarSql = new calendarSql();
        if (this.startDate == null)setDateRange();
        ScheduleModel eModel = new DefaultScheduleModel();
        
        CachedRowSet rs = null;
        try {
            //System.err.println(calendarSql.getCalSelectBydate(getActiveDeptList()));
            //System.err.println(startDate.toString());
            //System.err.println(endDate.toString());
            
            rs = db.getCachedRowSet(calendarSql.getCalSelectBydate("22"),
                    new Object[]{
                        startDate, endDate, 0, 
                    });
            setScheduleModel(true,eModel,rs);
            db.closeCachedRowSet(rs);
           
            rs = db.getCachedRowSet(calendarSql.getCalSelectBydateNext("22"),
                    new Object[]{
                        endDate, 0, startDate, endDate
                    });
            setScheduleModel(false,eModel,rs);
            db.closeCachedRowSet(rs);
        } catch (Exception ex) {
            //eventModel = new ;
            logger.error(ex.toString());
        } 
        return eModel;
    }
    private void setDateRange(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
        
        cal.add(Calendar.DATE,-7);
        
        //cal.add(Calendar.DATE, -7);
        startDate = cal.getTime();
        cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH));
        
        cal.add(Calendar.DATE, +7);
       
        
        endDate = cal.getTime();
        //System.err.println(startDate.toString());
        //System.err.println(endDate.toString());
    }
    private void setScheduleModel(boolean currEvent,ScheduleModel eModel , final CachedRowSet rs){
        
        try {
            
            String pre= (currEvent== true ? "":"*("),
                    post =(currEvent == true ? "":")");
            while (rs.next()) {
                DefaultScheduleEvent ex;
                pre = currEvent == true ? "": pre+" "+new SimpleDateFormat("EEE MMM dd yyyy").format(rs.getDate(13))+" ";
                if (currEvent){
                    ex = new DefaultScheduleEvent( rs.getString(3), rs.getDate(5), rs.getDate(6),rs.getInt(1));
                    ex.setAllDay(false);
                } else {
                    ex = new DefaultScheduleEvent( pre+rs.getString(3)+post, rs.getDate(13), rs.getDate(13),rs.getInt(1));
                    ex.setAllDay(true);
                }
                
                eModel.addEvent(ex);
            }
        } catch (Exception ex) {
            //eventModel = null;
            logger.error(ex.toString());
        }
    }
    public List<lookupBean> pickListDow, pickListDay;
    private scheduleBean scheduleBean;

    public List<lookupBean> getListDow() {
        if (pickListDow == null) {
            pickListDow = new ArrayList<lookupBean>();
            pickListDow.add(new lookupBean(eNumWeekInd.every));
            pickListDow.add(new lookupBean(eNumWeekInd.every1st));
            pickListDow.add(new lookupBean(eNumWeekInd.every2nd));
            pickListDow.add(new lookupBean(eNumWeekInd.every3rd));
            pickListDow.add(new lookupBean(eNumWeekInd.every4th));
            pickListDow.add(new lookupBean(eNumWeekInd.every5th));
        }
        return pickListDow;
    }

    public List<lookupBean> getListDay() {
        if (pickListDay == null) {
            pickListDay = new ArrayList<lookupBean>();
            pickListDay.add(new lookupBean(eNumDayOfWeek.sunday));
            pickListDay.add(new lookupBean(eNumDayOfWeek.monday));
            pickListDay.add(new lookupBean(eNumDayOfWeek.tuesday));
            pickListDay.add(new lookupBean(eNumDayOfWeek.wednesday));
            pickListDay.add(new lookupBean(eNumDayOfWeek.thursday));
            pickListDay.add(new lookupBean(eNumDayOfWeek.friday));
            pickListDay.add(new lookupBean(eNumDayOfWeek.saturday));
        }
        return pickListDay;
    }

    public scheduleBean getScheduleBean() {
        if (scheduleBean == null) {
            scheduleBean = new scheduleBean();
        }
        return scheduleBean;
    }

    /**
     * @param scheduleBean the scheduleBean to set
     */
    public void setScheduleBean(scheduleBean scheduleBean) {
        this.scheduleBean = scheduleBean;
    }
}
