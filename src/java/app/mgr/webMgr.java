/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.mgr;

import app.eNum.eNumNavWebPages;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 *
 * @author lmeans
 */
@ManagedBean
@SessionScoped
public class webMgr extends webMgr_EL implements Serializable{
    private eNumNavWebPages eNumRoot = eNumNavWebPages.Roland;
    public String getCenterPanel(){
        return eNumRoot.getXhtml();
    }
}
