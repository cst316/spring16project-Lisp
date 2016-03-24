package net.sf.memoranda.ui;

//======================================================================= GARY
//import javax.swing.JOptionPane;
//
//import net.sf.memoranda.util.Local;
//
//public class ImportSticker {
//
//String name;        
//        
//        public ImportSticker(String x) {
//                name = x;
//        }
//
//        public boolean import_file(){
//                /*
//                 We are working on this =)
//                  
//                  
//                  */
//                
//                JOptionPane.showMessageDialog(null,Local.getString("Still we cannot import your document"));
//                return true;
//        }
//        
//        
//}

import java.awt.Dimension;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.memoranda.EventsManager;
import net.sf.memoranda.date.CurrentDate;
import net.sf.memoranda.ui.StickerDialog;
import net.sf.memoranda.ui.AppFrame;
import net.sf.memoranda.util.CurrentStorage;

import net.sf.memoranda.util.Local;
import nu.xom.Element;

public class ImportSticker {
    
    static String name;
    static String fileContents;
    static String line;
    static String dateCreated;
    File f1;
    
    public ImportSticker(String x) {
        name = x;
    }
    
    public boolean import_file() throws IOException{
     
        File[] files = File.listRoots();
        for(File f : files){
            f.getPath();
            f1 = new File(name);
        }
		
		
        try{
            FileReader fr = new FileReader(name);
            BufferedReader br = new BufferedReader(fr);   
            while ((line = br.readLine()) != null){
                if(line != null){
                    if (fileContents == null){
                        fileContents = line;
                    }
                    else{
                        fileContents = fileContents + "\n" + line;
                    }
                }
            }
            
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            dateCreated = sdf.format(f1.lastModified());
            if (fileContents == null){
                System.out.println("didn't find it");
                JOptionPane.showMessageDialog(null,Local.getString("We cannot find your document"));
            }
            br.close();
            
        }
//        catch(IOException ex){
//            JOptionPane.showMessageDialog(null,Local.getString("there was a problem finding your file"));
//            name = null;
//            return false;
//        }
        // }
        
        JOptionPane.showMessageDialog(null,Local.getString("We imported your file, just click ok (twice)"));
        return true;
        
    }
    
    
}
//----------------------------------------------------------------------- GARY