/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.che.convert.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DGN
 */
public class TxtConverter extends FileConverter {

    public TxtConverter(String inputFilePath) {
        super(inputFilePath);
    }

    @Override
    public void convert() {
        DataInputStream in = null;
        BufferedReader br = null;
        InputStreamReader is = null;
        Document pdfdoc = new Document();
        try {
            PdfWriter.getInstance(pdfdoc, outStream);
            pdfdoc.open();
            pdfdoc.setMarginMirroring(true);
            pdfdoc.setMargins(36, 72, 108, 180);
            pdfdoc.topMargin();
            Font normal_font = new Font();
            Font bold_font = new Font();
            bold_font.setStyle(Font.BOLD);
            bold_font.setSize(10);
            normal_font.setStyle(Font.NORMAL);
            normal_font.setSize(10);
            pdfdoc.add(new Paragraph("\n"));
            in = new DataInputStream(inStream);
            is = new InputStreamReader(in);
            br = new BufferedReader(is);
            String strLine;
            while ((strLine = br.readLine()) != null) {
                System.out.println("strLine = " + strLine);
                Paragraph para = new Paragraph(strLine + "\n", normal_font);
                para.setAlignment(Element.ALIGN_JUSTIFIED);
                pdfdoc.add(para);
            }
             pdfdoc.close(); 
             in.close();
             br.close();
             is.close();
        } catch (DocumentException ex) {
            Logger.getLogger(TxtConverter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TxtConverter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
