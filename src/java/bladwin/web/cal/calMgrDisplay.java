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
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import jvp.obj.bean.calendarBean;
import mgn.obj.cal.calendarObj;

/**
 *
 * @author lmeans
 */
@ManagedBean
@RequestScoped
public class calMgrDisplay  extends mgrVideoProduction_EL  implements Serializable {
    private List<calendarBean> list,calendarBeanAboutUs;
    // ---------------------------------------------------------------
    @ManagedProperty("#{mgrVideoProduction}")
    private mgrVideoProduction mgrVideoProduction = null;
    public void setMgrVideoProduction(mgrVideoProduction mgrVideoProduction) {
        this.mgrVideoProduction = mgrVideoProduction;
    }

   

    public List<calendarBean> grabMsgList() {
        if (list == null || list.isEmpty()) {
            calendarObj calendarObj = new calendarObj();
            list = calendarObj.getListTodayNonGrpUsr(1, 45, getDbBlazers());
        }
        return list;
    }
    
    public List<calendarBean> grabListHome() {
        if (list == null){
            
            calendarObj calendarObj = new calendarObj();
            list = calendarObj.getListByType(-1, false, mgrVideoProduction.getDbBlazers());
            if(list == null || list.isEmpty()){
                list = new ArrayList<calendarBean>();
                calendarBean b = calendarObj.getSqlSelectCalendarId(-1, mgrVideoProduction.getDbBlazers());
                list.add(b);
            }
           
        }
        return list;
    }
    public List<calendarBean> grabAboutUsBean() {
        if (calendarBeanAboutUs == null){
            calendarBeanAboutUs = new ArrayList<calendarBean>();
            calendarObj calendarObj = new calendarObj();
            
            calendarBean b = calendarObj.getSqlSelectCalendarId(-1, mgrVideoProduction.getDbBlazers());
            calendarBeanAboutUs.add(b);
        }
        return calendarBeanAboutUs;
    }
}
