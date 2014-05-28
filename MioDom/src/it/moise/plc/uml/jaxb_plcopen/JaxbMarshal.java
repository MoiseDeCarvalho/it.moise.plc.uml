package it.moise.plc.uml.jaxb_plcopen;


import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class JaxbMarshal {	
	String filePath;
	String context;
	JAXBContext jaxbContext;

	
	public JaxbMarshal(String filePath, String context){
		
		this.filePath=filePath;
		this.context=context;
		try {
			jaxbContext = JAXBContext.newInstance(context);
		} catch (JAXBException e) {			
			e.printStackTrace();
		}
	}		
	public void getMarshalledFile(Object object) throws IOException{	
		
		Marshaller marshaller=null;		
		try {			
			marshaller = jaxbContext.createMarshaller ();			
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, new Boolean(true));
			FileOutputStream file = new FileOutputStream(filePath);
			marshaller.marshal(object, file);
			file.close();
		} catch (JAXBException e) {			
			e.printStackTrace();
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		}						
	}
}
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




