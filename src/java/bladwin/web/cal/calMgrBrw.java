/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bladwin.web.cal;

import bladwin.web.mgrVideoProduction;
import bladwin.web.mgrVideoProduction_EL;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import jvp.obj.bean.calendarBean;
import mgn.obj.cal.calendarObj;
import mgn.obj.model.calendarBeanModel;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Lloyd
 */
@ManagedBean
@ViewScoped
public class calMgrBrw extends mgrVideoProduction_EL  implements Serializable {
    private calendarBeanModel calendarBeanModel;
    private calendarBean calendarBean;
    
    @ManagedProperty("#{mgrVideoProduction}")
    private mgrVideoProduction mgrVideoProduction = null;
    public void setMgrVideoProduction(mgrVideoProduction mgrVideoProduction) {
        this.mgrVideoProduction = mgrVideoProduction;
    }
    public calendarBeanModel grabCalendarBeanModelAll(){
        
        if (calendarBeanModel == null){
            calendarObj calendarObj = new calendarObj();
            calendarBeanModel = new calendarBeanModel(calendarObj.getListByType(-1, true, mgrVideoProduction.getDbBlazers()));
            
        }
        return calendarBeanModel;
    }
    public calendarBeanModel grabCalendarBeanModelAllMsg(){
        
        if (calendarBeanModel == null){
            calendarObj calendarObj = new calendarObj();
            calendarBeanModel = new calendarBeanModel(calendarObj.getListByType(1, true, mgrVideoProduction.getDbBlazers()));
        }
        return calendarBeanModel;
    }
    
    
    public void setCalendarBean(calendarBean bean){
        calendarBean = bean;
    }
    public calendarBean getCalendarBean() {
        if (calendarBean == null){
            calendarBean = new calendarBean();
        }
        return calendarBean;
    }
    public void brwEdit(){
        if (calendarBean == null){
            RequestContext.getCurrentInstance().execute("PF('dialogWidgetEditClient').show()");
            
        } else {
            mgrVideoProduction.msgEdit(calendarBean.getCalId());
        }
    }
    public void brwNew(){
        mgrVideoProduction.msgPost();
    }
    public void msgNew(){
        mgrVideoProduction.msgViewAdd();
    }
    public void msgEdit(){
        if (calendarBean == null){
            RequestContext.getCurrentInstance().execute("PF('dialogWidgetEditClient').show()");
            
        } else {
            mgrVideoProduction.msgViewEdit(calendarBean.getCalId());
            
        }
    }
}
