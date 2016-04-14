package app.eNum;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lmeans
 */
public enum eNumNavWebPages {
    Roland("",0);
    private String xhtml;
    private int nav;
    private eNumNavWebPages(String xhtml,int nav){
        this.xhtml = xhtml;
        this.nav = nav;
    }

    /**
     * @return the xhtml
     */
    public String getXhtml() {
        return xhtml;
    }

    /**
     * @return the nav
     */
    public int getNav() {
        return nav;
    }
}
