import java.awt.image.BufferedImage;
import java.io.File ;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.slf4j.*;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract ;  
import net.sourceforge.tess4j.TesseractException ;
import net.sourceforge.tess4j.util.LoadLibs;  
public class Hello{  
    public static void main( String[ ] args ) throws IOException  
    {  
       
    	//System.setProperty("jna.library.path", "32".equals(System.getProperty("sun.arch.data.model")) ? "lib/win32-x86" : "lib/win32-x86-64");
    	System.out.println("Hello");
        ITesseract instance = new Tesseract();  // JNA Interface Mapping
        //instance.setDatapath(null);
        File imageFile = new File("20221119_221613_z.jpg");
        
        
        BufferedImage bi = ImageIO.read(imageFile);
        System.out.println("Type: "+bi.getType());
        bi = ImageHelper.convertImageToGrayscale(bi);
        int width=bi.getWidth();
        int height=bi.getHeight();
        width*=3;
        height*=3;
        BufferedImage bi2 = ImageHelper.getScaledInstance(bi, width, height);
        //bi = ImageHelper.rotateImage(bi, 90);
        File outputfile = new File("saved.png");
        ImageIO.write(bi2, "png", outputfile);
        
          
        // ITesseract instance = new Tesseract1(); // JNA Direct Mapping
        File tessDataFolder = LoadLibs.extractTessResources("tessdata");
        //List<String> configs = Arrays.asList("digits");
        instance.setDatapath(tessDataFolder.getPath());
        //instance.setConfigs(null);
        instance.setLanguage("eng+ara");
         
        try {
            String result = instance.doOCR(bi2);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }  
}