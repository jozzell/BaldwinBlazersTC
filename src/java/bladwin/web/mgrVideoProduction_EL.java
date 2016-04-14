/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bladwin.web;

import cv.bisc.db.dbMgr;
import cv.bisc.db.dbMgrEnumType;
import jvp.obj.video.videoObj;
import mgn.obj.lookupSys.mgnSysLookupObj;
import obj.db.v1.dbMgrInterface;

/**
 *
 * @author lmeans
 */
public class mgrVideoProduction_EL {
    private dbMgr dbMgr,dbBlazers;
    public void refreshSession(){
        try {
            (new videoObj()).chConnect(getObj());
            (new mgnSysLookupObj()).chckConection(this.getDbBlazers());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
       
    }
    public  dbMgrInterface getObj(){
        if (dbMgr == null){
            dbMgr = new dbMgr("jdbc/jvp", dbMgrEnumType.typeMySql.getType());
            
        }
        return dbMgr;
    }

    /**
     * @return the dbBlazers
     */
    public dbMgr getDbBlazers() {
        if (dbBlazers == null){
            dbBlazers = new dbMgr("jdbc/blazers", dbMgrEnumType.typeMySql.getType());
            
        }
        return dbBlazers;
    }
}
