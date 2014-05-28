package it.moise.plc.uml.jaxb_plcopen;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.plcopen.xml.tc6_0201.*;
import org.plcopen.xml.tc6_0201.Project.FileHeader;
import org.xml.sax.SAXException;
/**
 * Classe che permette di creare un oggetto Project.FileHeader completo di tutti i suoi parametri
 *
 * @author De Carvalho Moïse
 *
 */
public class FileHeaderTag  extends Project.FileHeader{
	private Date today = new Date();
	private String data = "";
	//private FileHeader fileHeader = new ObjectFactory().createProjectFileHeader();
	public FileHeaderTag() {
		
		try {
			createFileHeader();
		} catch (IllegalArgumentException | DatatypeConfigurationException
				| ParserConfigurationException | SAXException
				| TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void createFileHeader() throws IllegalArgumentException,DatatypeConfigurationException, ParserConfigurationException, SAXException, TransformerException{
		DatatypeFactory f = DatatypeFactory.newInstance();
		this.setCompanyName("moise.it");
		this.setProductName("CODESYS");
		this.setProductVersion("CODESYS V3.5 SP3 Patch 7");	
		SimpleDateFormat  calendario = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		this.data= calendario.format(this.today);
		XMLGregorianCalendar c = f.newXMLGregorianCalendar(data);
		
		this.setCreationDateTime(c);
		System.out.println("time: " + data);
		System.out.println ("Oggetto File Header creato");
	}
	
	

}
