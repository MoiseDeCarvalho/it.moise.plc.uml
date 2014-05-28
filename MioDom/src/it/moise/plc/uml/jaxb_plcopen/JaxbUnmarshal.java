package it.moise.plc.uml.jaxb_plcopen;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class JaxbUnmarshal {
	
	String filePath;
	String context;
	JAXBContext jaxbContext;
	InputStream InpStr;
	
	public JaxbUnmarshal(String filePath, String context){
		this.filePath=filePath;
		this.context=context;
		try {
			jaxbContext = JAXBContext.newInstance (context);
		} catch (JAXBException e) {			
			e.printStackTrace();
		}
	}	
		
	public Object getUnmarshalledObject(){
		
		Unmarshaller unmarshaller=null;
		Object objectJAXB=null;
		try {			
			unmarshaller = jaxbContext.createUnmarshaller ();			
			objectJAXB = unmarshaller.unmarshal( new FileInputStream(filePath));		
		} catch (JAXBException e) {			
			e.printStackTrace();
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		}			
		return objectJAXB;			
	}
	
	


}
