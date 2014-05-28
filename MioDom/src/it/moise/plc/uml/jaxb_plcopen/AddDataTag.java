package it.moise.plc.uml.jaxb_plcopen;

import it.moise.plc.uml.ConstantString;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.plcopen.xml.tc6_0201.AddData;
import org.plcopen.xml.tc6_0201.ObjectFactory;
/**
 * Classe che estende AddData, restituisce un oggetto di tale classe completamente completo di tutti gli elementi in modo da non ripetere 
 * sempre gli stessi valori
 * @author De Carvalho Moïse
 *
 */
public class AddDataTag extends AddData{
	
	private AddData.Data addDataData = new ObjectFactory().createAddDataData();
	private JAXBElement attributes;
	/**
	 * Metodo Costruttore che istanzia l'oggetto e chiama il metodo createAddData per popolare di dati l'oggetto
	 */
	public AddDataTag() {
		super();
		this.createAddData();
	}
	/**
	 * Metodo che viene utilizzato per popolare l'oggetto
	 */
	private void createAddData(){
		this.addDataData = new ObjectFactory().createAddDataData();
		this.addDataData.setName(ConstantString.SFC_ELEMENT);
		this.addDataData.setHandleUnknown(ConstantString.IMPLEMENTATION);
		
		try {
			  this.attributes = new JAXBElement<String>(new QName("", ConstantString.ATTRIBUTES), String.class, "");
			  this.addDataData.setAny(this.attributes);
		  }
		catch (IllegalArgumentException e){
			  e.printStackTrace();
		  }
	  
		this.getData().add(this.addDataData);
	}
}
