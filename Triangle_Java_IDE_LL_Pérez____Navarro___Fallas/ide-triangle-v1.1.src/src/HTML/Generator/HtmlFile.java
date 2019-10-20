/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HTML.Generator;
import Triangle.AbstractSyntaxTrees.Program;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sol
 */
public class HtmlFile {
    private String name;  
    private FileWriter writer; 
   

    public HtmlFile(Program ast, String name) {
        this.name = name;
        try {
            this.writer = new FileWriter(name);
            writer.write("IDE-Triangle");
        } catch (IOException ex) {
            Logger.getLogger(HtmlFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public void HtmlWrite(String text){
        try {
           
            writer.write("<html>" );
            writer.write("<head>" + "<title>IDE-Triangle</title>\n" + "</head>" );  //set title of html file
            writer.write("<style>" + "p {\n" +
            "font-size: 1em;\n" + "font-family: \"DejaVu Sans\", monospaced;\n" + "  }\n" + //set font
            ".reserved {font-weight:bold;}" +                                   //style for reserved words
            ".literal {\n" + "    color : #1D4D89;\n" + "  }\n" +               //style for literals
            ".comment {\n" + "    color: #6CDCA4;\n" + "  }\n" +                //style for comments
            "</style>" );
            writer.write("<body>");
            writer.write("<p>");
            writer.write(text);
            writer.write("</p>");
             writer.write("</body>");
             writer.write("</html>");
            writer.close();
            
        } catch (IOException ex) {
            Logger.getLogger(HtmlFile.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    

    
}
