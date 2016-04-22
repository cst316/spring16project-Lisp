package net.sf.memoranda.ui;

import java.io.*;
import java.nio.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JOptionPane;

import net.sf.memoranda.EventsManager;
import net.sf.memoranda.util.CurrentStorage;
import net.sf.memoranda.util.FileStorage;
import net.sf.memoranda.util.Local;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;

public class ExportSticker {

        private String name; 
        
        public ExportSticker(String x) {
                this.name = remove1(x);
        }

        /**
         * Function to eliminate special chars from a string
         */
        public static String remove1(String input) {
            
            String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
            
            String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
            String output = input;
            for (int i=0; i<original.length(); i++) {
            
                output = output.replace(original.charAt(i), ascii.charAt(i));
            }
            return output;
        }
        
        public boolean export(String src){
                boolean result = true;
                String fs = FileStorage.JN_DOCPATH;
                
                String contents = getSticker();
                try {
                File file = new File(fs + this.name+"."+src);
                
                
                        FileWriter fwrite=new FileWriter(file,true);
            
                        fwrite.write(contents);
                        
                        fwrite.close();
                        JOptionPane.showMessageDialog(null,Local.getString("Document created with success in its Memorandum folder"));
            
            
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,Local.getString("We failed to create your document"));
        }
                
                
                        
                return result;
        }
        
        public String getSticker(){
                Map stickers = EventsManager.getStickers();
        String result = "";
        String nl = System.getProperty("line.separator"); 
                for (Iterator i = stickers.keySet().iterator(); i.hasNext();) {
            String id = (String)i.next();
            result += (String)(((Element)stickers.get(id)).getValue())+nl;
            }
            
                return result;
        }
        
        /*public static String getStickers() {
                String result ="";
                Elements els = _root.getChildElements("sticker");
                for (int i = 0; i < els.size(); i++) {
                        Element se = els.get(i);
                        m.put(se.getAttribute("id").getValue(), se.getValue());
                }
                return m;
        }*/
        
        
        
}