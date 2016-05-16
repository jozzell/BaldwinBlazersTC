/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bladwin.web.reg;

/**
 *
 * @author Lloyd
 */
    
public enum eNumReg {
    login(0,"../steps/steps_login.xhtml"),
    newParentName(1,"../steps/steps_new_parent.xhtml"),
    newParentAddr(2,"../steps/steps_new_parent.xhtml"),
    // ------------------------------------------------
    regBrw(1,"../steps/steps_reg.xhtml"),
    regPdfView(2,"../steps/steps_reg_pdf.xhtml"),
    regAthlete(2,"../steps/steps_reg_edit_athlete.xhtml"),
    regEditParent_pg1(2,"../steps/steps_reg_edit_parent.xhtml"),
    regEditParent_pg2(3,"../steps/steps_reg_edit_parent.xhtml"),
    // ------------------------------------------------
    reg_pg01(2,"../steps/steps_reg_add.xhtml"),
    reg_pg02(3,"../steps/steps_reg_add.xhtml"),
    reg_pg03(4,"../steps/steps_reg_add.xhtml"),
    // ------------------------------------------------
    regAddReg(2,"../steps/steps_reg.xhtml"),
    regEditReg(2,"../steps/steps_reg.xhtml"),
    regPdf(2,"../steps/steps_reg.xhtml")
    
    ;
    private final String html;
    private final int pg;
    private eNumReg(int pg,String html){
        this.html = html;
        this.pg = pg;
    }

    public String getHtml(){
        return html;
    }
    public int getPg() {
        return pg;
    }
}
