/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bladwin.web;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import jvp.obj.video.videoBean;
import jvp.obj.video.videoObj;

/**
 *
 * @author Lloyd
 */
@ManagedBean
@ViewScoped
public class mgrQuickAdd extends mgrVideoProduction_EL implements Serializable {

    List<videoBean> list;

    public List<videoBean> getList() {
        if (list == null) {
            list = (new videoObj()).getVideoBeanListHm(getObj());
            while (list.size() > 5) {
                list.remove(5);
            }
        }
        return list;
    }
}
