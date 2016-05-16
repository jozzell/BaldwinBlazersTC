/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bladwin.web.reg;

import java.io.Serializable;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import mgn.obj._beans.customerBean;
import mgn.obj._beans.customerRegBean;
import mgn.obj._beans.mgnLookupBean;
import mgn.obj.cust.custObj;
import mgn.obj.lookup.mgnLookupObj;
import obj.db.v1.dbMgrInterface;
import obj.pdf.pdfRptMgr;
import org.apache.log4j.Logger;


/**
 *
 * @author Lloyd
 */

public class regPDF  implements Serializable{
    // ---------------------------------------------------------------
    public  final Logger logger = (Logger) Logger.getLogger(regPDF.class);
    
    private final pdfRptMgr pdfRptMgr = new pdfRptMgr() ;
     
  
    public void genPed(String loc,regMgr regMgr,dbMgrInterface db){
        Document document = null;
        //String loc = "C:/_bisc/_javaApps/jlData/jvp/CellWidths.pdf";
        
        //regMgr.getCustomerRegBean().setPdf(loc);
        try {
            
            document = new Document(PageSize.A4, 30, 30, 30, 30);
            PdfWriter.getInstance(document, new FileOutputStream(loc));
            document.open();
            customerRegBean r = regMgr.getCustomerRegBean();
            customerBean c = regMgr.getCustomerBean();
            customerBean a = new custObj().getcustomerBean(r.getRegCustId(), db);
            mgnLookupBean bean = new mgnLookupObj().getLookupBean(-947, db);
            Paragraph pg;
            pg = new Paragraph(r.getLookupDesc());
            pg.setAlignment(Paragraph.ALIGN_CENTER);
            pg.setFont(FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD));
            document.add(getHeader(r.getLookupDesc()));
            document.add(getHeader("BALDWIN BLAZERS TRACK CLUB"));
            document.add(getHeader("REGISTRATION"));
            pg = new Paragraph("\n\r");
            document.add(pg);
            // -------------------------------------------------------
            document.add(genAthleteInfo(r,a));
             pg = new Paragraph("\n\r");
            document.add(pg);
            document.add(genParentInfo(c));
             pg = new Paragraph("\n\r");
            document.add(pg);
            document.add(genSport(r));
            pg = new Paragraph("\n\r");
            document.add(pg);
            pg = new Paragraph("PARENT’S SIGNATURE");
            document.add(pg);
             pg = new Paragraph("\n\r");
            document.add(pg);
             pg = new Paragraph("\n\r");
            document.add(pg);
            pg = new Paragraph("_____________________________________________________________________");
            document.add(pg);
            pg = new Paragraph("\n\r");
            document.add(pg);
            document.add(getHeader(r.getFeeDesc()+"\n\r"+ bean.getSubjectBody()));
            // ----------------------------------------------------------------------------
            
        
        } catch (DocumentException ex) {
            logger.error("DocumentException",ex);
        } catch (FileNotFoundException ex) {
            logger.error("FileNotFoundException",ex);
        } finally {
            try {
                if (document != null) document.close();
                
               //pdfFileOpen(loc);
                
            } catch (Exception e) {
                logger.error("FileNotFoundException",e);
            }
        }
    }
    private Paragraph getHeader(String text){
        Paragraph pg = new Paragraph(text);
            pg.setAlignment(Paragraph.ALIGN_CENTER);
            pg.setFont(FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD));
            return pg;
    }
    private PdfPTable genSport(customerRegBean a) {
        float[] widths2 = {0.33f, 0.33f, 0.33f};
        PdfPTable table = new PdfPTable(widths2);
        PdfPCell cell1 = new PdfPCell(),
                cell2 = new PdfPCell(),
                cell3 = new PdfPCell();
        cell1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell1.setPhrase(pdfRptMgr.getRptFont("Shirt Size",true));
        cell2.setPhrase(pdfRptMgr.getRptFont("Short Size",true));
        cell3.setPhrase(pdfRptMgr.getRptFont("Warmup Suit Size",true));
        
       table.addCell(cell1);
       table.addCell(cell2);
       table.addCell(cell3);
       cell1.setPhrase(pdfRptMgr.getRptFont(a.getShirtSizeStr()));
        cell2.setPhrase(pdfRptMgr.getRptFont(a.getShortSideStr()));
        cell3.setPhrase(pdfRptMgr.getRptFont(a.getWarmupSuiteSizeStr()));
       table.addCell(cell1);
       table.addCell(cell2);
       table.addCell(cell3);
        return table;
    }
    
    private PdfPTable genAthleteInfo(customerRegBean a,customerBean c){
        float[] widths = {0.30f, 0.70f};
        PdfPTable table = new PdfPTable(widths);
        table.getDefaultCell().setBorder(0);
         PdfPCell   cell1 = new PdfPCell(),
                       cell2 = new PdfPCell();
         cell1.setPhrase(pdfRptMgr.getRptFont("Athlete Name",true));
        cell2.setPhrase(pdfRptMgr.getRptFont(a.getFullname()));
        
        table.addCell(cell1);
        table.addCell(cell2);
        cell1.setPhrase(pdfRptMgr.getRptFont("School",true));
        cell2.setPhrase(pdfRptMgr.getRptFont(a.getSchool()));
        table.addCell(cell1);
        table.addCell(cell2);
        cell1.setPhrase(pdfRptMgr.getRptFont("Date of Birth",true));
        cell2.setPhrase(pdfRptMgr.getRptFont(a.getDobStr()));
        table.addCell(cell1);
        table.addCell(cell2);
        cell1.setPhrase(pdfRptMgr.getRptFont("Cell #",true));
        cell2.setPhrase(pdfRptMgr.getRptFont(c.getCell()));
        table.addCell(cell1);
        table.addCell(cell2);
        cell1.setPhrase(pdfRptMgr.getRptFont("E-Mail",true));
        cell2.setPhrase(pdfRptMgr.getRptFont(c.getEMail()));
        table.addCell(cell1);
        table.addCell(cell2);
        cell1.setPhrase(pdfRptMgr.getRptFont("Any Health Concerns?",true));
        cell2.setPhrase(pdfRptMgr.getRptFont(a.getRegNote()));
        table.addCell(cell1);
        table.addCell(cell2);
        
        
        cell1.setPhrase(pdfRptMgr.getRptFont("Emergency Contact",true));
        cell2.setPhrase(pdfRptMgr.getRptFont(a.getContact()));
        table.addCell(cell1);
        table.addCell(cell2);
       
                
                
                
        return table;
    }
    private PdfPTable genParentInfo(customerBean c){
         float[] widths = {0.20f, 0.80f};
         PdfPTable table = new PdfPTable(widths);
         table.getDefaultCell().setBorder(0);
         PdfPCell   cell1 = new PdfPCell(),
                       cell2 = new PdfPCell();
        cell1.setPhrase(pdfRptMgr.getRptFont("Parent Name",true));
        cell2.setPhrase(pdfRptMgr.getRptFont(c.getLastName()+", "+c.getFirstName()));
        table.addCell(cell1);
        table.addCell(cell2);
        cell1.setPhrase(pdfRptMgr.getRptFont("Address",true));
        cell2.setPhrase(pdfRptMgr.getRptFont(getAddress(c)));
        table.addCell(cell1);
        table.addCell(cell2);
        cell1.setPhrase(pdfRptMgr.getRptFont("Cell #",true));
        cell2.setPhrase(pdfRptMgr.getRptFont(c.getCell()));
        table.addCell(cell1);
        table.addCell(cell2);
        cell1.setPhrase(pdfRptMgr.getRptFont("Home #",true));
        cell2.setPhrase(pdfRptMgr.getRptFont(c.getHmPhone()));
        table.addCell(cell1);
        table.addCell(cell2);
         cell1.setPhrase(pdfRptMgr.getRptFont("E-Mail",true));
        cell2.setPhrase(pdfRptMgr.getRptFont(c.getEMail()));
        table.addCell(cell1);
        table.addCell(cell2);
        return table;
    }
    private String getAddress(customerBean c){
        StringBuilder sb = new StringBuilder();
        String str;
        str = c.getAddr1()+" "+c.getAddr2();
        sb.append(str.trim()).append("\n");
        str = c.getCity()+" "+c.getState()+" "+c.getZip();
        sb.append(str.trim()).append("\n");
        return sb.toString();
    }
}
