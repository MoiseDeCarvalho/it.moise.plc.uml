package it.moise.plc.uml.jaxb_plcopen;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.plcopen.xml.tc6_0201.*;

/**
 * Questa classe estende la classe Project.ContentHeader, facendo in questo modo posso manipolare 
 * l'oggetto nella classe ConversionToCoDeSys
 * @author De Carvalho Moïse
 *
 */
public class ContentHeaderTag extends Project.ContentHeader {
	//private Project.ContentHeader contentHeader;
	private Project.ContentHeader.CoordinateInfo coordinateInfo = new ObjectFactory().createProjectContentHeaderCoordinateInfo();
	
	private Project.ContentHeader.CoordinateInfo.Fbd fbd = new ObjectFactory().createProjectContentHeaderCoordinateInfoFbd();
	private Project.ContentHeader.CoordinateInfo.Fbd.Scaling fbdScaling = new ObjectFactory().createProjectContentHeaderCoordinateInfoFbdScaling();
	
	private Project.ContentHeader.CoordinateInfo.Ld ld = new ObjectFactory().createProjectContentHeaderCoordinateInfoLd();
	private Project.ContentHeader.CoordinateInfo.Ld.Scaling ldScaling = new ObjectFactory().createProjectContentHeaderCoordinateInfoLdScaling();
	
	private Project.ContentHeader.CoordinateInfo.Sfc sfc = new ObjectFactory().createProjectContentHeaderCoordinateInfoSfc();
	private Project.ContentHeader.CoordinateInfo.Sfc.Scaling sfcScaling = new ObjectFactory().createProjectContentHeaderCoordinateInfoSfcScaling();
	
	private Date today = new Date();
	private String data = "";
	/**
	 * Metodo costruttore
	 * @throws DatatypeConfigurationException
	 */
	public ContentHeaderTag() throws DatatypeConfigurationException {
		super();
		this.createContentHeader();
	}
	
	/**
	 * Questo metodo viene utilizzato per riempire di dati l'oggetto super()
	 * @throws DatatypeConfigurationException 
	 */
	public void createContentHeader() throws DatatypeConfigurationException{
		/*
		 * Project.ContentHeader.CoordinateInfo
		 */
		DatatypeFactory f = DatatypeFactory.newInstance();
		this.setName("mio file name");
		this.setAuthor("De Carvalho Moïse");
		this.setComment("Commento del progetto");
		this.setLanguage("ita");
		
		this.fbdScaling.setX(new BigDecimal("1"));
		this.fbdScaling.setY(new BigDecimal("1"));
		this.fbd.setScaling(this.fbdScaling);
		this.coordinateInfo.setFbd(this.fbd);
		
		this.ldScaling.setX(new BigDecimal("1"));
		this.ldScaling.setY(new BigDecimal("1"));
		this.ld.setScaling(this.ldScaling);
		this.coordinateInfo.setLd(this.ld);
		
		this.sfcScaling.setX(new BigDecimal("1"));
		this.sfcScaling.setY(new BigDecimal("1"));
		this.sfc.setScaling(this.sfcScaling);
		this.coordinateInfo.setSfc(this.sfc);
		
		SimpleDateFormat  calendario = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		this.data= calendario.format(this.today);
		XMLGregorianCalendar c = f.newXMLGregorianCalendar(data);
		
		this.setCoordinateInfo(this.coordinateInfo);
		this.setModificationDateTime(c);
			// Fine Project.ContentHeader.CoordinateInfo
		System.out.println("time: " + data);
		System.out.println ("Oggetto Content Header creato");
	}
}
