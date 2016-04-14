/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bladwin.web.cal;

import bladwin.web.mgrVideoProduction;
import bladwin.web.mgrVideoProduction_EL;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import jvp.obj.bean.calendarBean;
import jvp.obj.bean.lookupBean;
import mgn.obj.cal.calendarObj;

/**
 *
 * @author lmeans
 */
@ManagedBean
@ViewScoped
public class calMgr  extends mgrVideoProduction_EL  implements Serializable {
    private calendarBean calendarBean;
    private List<lookupBean> lookupList;
    
    // ---------------------------------------------------------------
    @ManagedProperty("#{mgrVideoProduction}")
    private mgrVideoProduction mgrVideoProduction = null;
    public void setMgrVideoProduction(mgrVideoProduction mgrVideoProduction) {
        this.mgrVideoProduction = mgrVideoProduction;
    }
    
    public List<lookupBean> getLookupList(){
        if (lookupList == null){
            lookupList = new ArrayList<lookupBean>();
            lookupList.add(new lookupBean(0,"Display Message"));
            lookupList.add(new lookupBean(1,"Hide Message"));
        }
        return lookupList;
    } 
    
    public void cancelEdit(){
        mgrVideoProduction.setHome();
    }
    
    public void saveEdit(){
        save();
        mgrVideoProduction.setHome();
    }
    public void saveEditMsg(){
        save();
        mgrVideoProduction.msgView();
    }
    public void saveCancelMsg(){
        mgrVideoProduction.msgView();
    }
    private void save(){
        calendarObj calendarObj = new calendarObj();
        calendarObj.saveCalBean(calendarBean, mgrVideoProduction.getDbBlazers());
    }
    public calendarBean getCalendarBeanHome() {
        return setBean(-1);
    }
    public calendarBean getCalendarBeanMsg() {
        return setBean(1);
    }
    private calendarBean setBean(int type){
        if (mgrVideoProduction.getSelectId() != 0){
           calendarObj calendarObj = new calendarObj();
           calendarBean = calendarObj.getSqlSelectCalendarId(mgrVideoProduction.getSelectId(), mgrVideoProduction.getDbBlazers());   
        }
        if (calendarBean == null){
            calendarBean = new calendarBean();
            calendarBean.setCalType(type);
            Calendar cal = Calendar.getInstance();
            calendarBean.setStartDate(cal.getTime());
            cal.add(Calendar.DATE, 8);
            calendarBean.setEnddate(cal.getTime());
        }
        return getCalendarBean();
    }
    public calendarBean getCalendarBeanAbout() {
        if (calendarBean == null){
            calendarObj calendarObj = new calendarObj();
            calendarBean = calendarObj.getSqlSelectCalendarId(-1, mgrVideoProduction.getDbBlazers());   
        }
        return getCalendarBean();
    }
    public calendarBean getCalendarBean() {
        if (calendarBean == null){
            calendarBean = new calendarBean();
        }
        return calendarBean;
    }
    // -------------------------------------------------------------------
    public void aboutUsSave(){
        calendarObj calendarObj = new calendarObj();
        calendarObj.saveCalBean(calendarBean, mgrVideoProduction.getDbBlazers());
         aobutUsCancel();
    }
    public void aobutUsCancel(){
        mgrVideoProduction.msgAboutUs();
    }
}
