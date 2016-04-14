/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.mgr;

import cv.bisc.db.dbMgr;
import cv.bisc.db.dbMgrEnumType;
import jvp.obj.video.videoObj;
import obj.db.v1.dbMgrInterface;

/**
 *
 * @author lmeans
 */
public class webMgr_EL {
    private dbMgr dbMgr;
    public void refreshSession(){
       (new videoObj()).chConnect(getObj());
       
    }
    public  dbMgrInterface getObj(){
        if (dbMgr == null){
            dbMgr = new dbMgr("jdbc/jvp", dbMgrEnumType.typeMySql.getType());
        }
        return dbMgr;
    }
}
