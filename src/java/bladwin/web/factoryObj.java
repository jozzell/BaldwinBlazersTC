/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bladwin.web;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


/**
 *
 * @author lmeans
 */
public class factoryObj implements  Serializable{
    public  final Logger logger = (Logger) Logger.getLogger(factoryObj.class);
    // ==========================================================
   
     public  void FacesContextForward(final String url, final FacesContext theFacesContext) {
        ExternalContext ec = theFacesContext.getExternalContext();
        try {
            ec.redirect(url);
        } catch (IOException ex) {
            logger.error(ex.toString());
        }
    }
     public  Object accessBeanFromFacesContext(final String theBeanName, final FacesContext theFacesContext) {
        Object obj = null;
        try {
            obj = theFacesContext.getELContext().getELResolver().getValue(theFacesContext.getELContext(), null, theBeanName);
            
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return obj;
    }
      public  void setAccessBeanFromFacesContext(final String theBeanName, final FacesContext theFacesContext, Object obj) {
       
        try {
            theFacesContext.getELContext().getELResolver().setValue(theFacesContext.getELContext(), null, theBeanName, obj);
            
        } catch (Exception e) {
            logger.error(e.toString());
        }

    }
}
