/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.TreeWriterXML;

import Triangle.AbstractSyntaxTrees.Program;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Marco Gamboa
 */
public class XMLWriter {

    // Draw the AST representing a complete program.
    public static void write(Program ast,String fileName) {
        // Prepare the file to write
        try {
            File file = new File(fileName);
            if(!file.exists());
                file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);

            //XML header
            fileWriter.write("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\"?>\n");

            XMLWriterVisitor layout = new XMLWriterVisitor(fileWriter);
            ast.visit(layout, null);

            fileWriter.close();


        } catch (IOException e) {
            System.err.println("Error while creating xml file for print the AST");
            e.printStackTrace();
        }
    }    
}
