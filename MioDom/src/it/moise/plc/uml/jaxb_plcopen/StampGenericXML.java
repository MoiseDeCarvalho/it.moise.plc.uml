package it.moise.plc.uml.jaxb_plcopen;


/*
JAXBContext context;
BufferedWriter writer = null;
writer = new BufferedWriter(new FileWriter(selectedFile));
context = JAXBContext.newInstance(Books.class);
Marshaller m = context.createMarshaller();
m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
m.marshal(new Books(books), writer);
writer.close();
*/
import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class StampGenericXML {	
	static public void staticStampGenericXML(Object objectJAXB, String context) throws ParserConfigurationException, SAXException, TransformerException{
		try {
			
			JAXBContext jaxbLocalContext = JAXBContext.newInstance ("org.plcopen.xml.tc6_0201");
			 
			Marshaller marshaller = jaxbLocalContext.createMarshaller();
			marshaller.setProperty(marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(objectJAXB, System.out);
		} catch (JAXBException e1) {			
			e1.printStackTrace();
		}
		
		
	}
}
