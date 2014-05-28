package it.moise.plc.uml.xmltree;



import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;

import javax.swing.JTree;

import org.apache.commons.io.IOUtils;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * Class to display an XML file in a JTree. Uses the JDOM (Java Document Object
 * Model) to back the XML.
 * 
 * @see http://java.sun.com/webservices/jaxp/dist/1.1/docs/tutorial/index.html
 */
public class XMLTree {
    
    //keep handles on the documents and readers
    private static Document document;
    private static SAXBuilder saxBuilder;
    private static boolean validate = false;
    private static BufferedReader reader;
    private static byte[] xml = new byte[] {};
    
    //tree to be displayed
    private static JTree tree;

    /**
     * Creates a new instance of the JDOMTree class
     */
    public XMLTree() {
        saxBuilder = new SAXBuilder("org.apache.xerces.parsers.SAXParser", validate);
    }
    

    /**
     * Returns the JTree with xml inside.
     * 
     * @return JTree is present, or null.
     */
    public JTree getTree() {
        return tree;
    }

    
    /**
     * Read in an XML file to display in the tree
     * 
     * @param xmlFile Path to an XML file.
     */
    public void parseFile(FileReader xmlFile) throws Exception {

        try {
            
            //read file into a Document object
           // reader = new BufferedReader(new FileReader(xmlFile));
        	reader = new BufferedReader(xmlFile);  
            xml = IOUtils.toByteArray(reader);
            
            try {
                document = saxBuilder.build(new ByteArrayInputStream(xml));
                //TODO later I'll add validation against the schema 
                //validate(document);
            } catch (JDOMException jdome) {
                throw new Exception("\n"+jdome.toString());
            }
            
            //convert the document object into a JTree
            JDOMToTreeModelAdapter model = new JDOMToTreeModelAdapter(document);
            tree = new JTree(model);
            tree.setCellRenderer(new XMLTreeCellRenderer());
            
        } catch (Exception e){
            //if any exception set to null so we will  
        	//refresh the display with the exception
            tree = null;            
            throw e;
        }
    }
}

