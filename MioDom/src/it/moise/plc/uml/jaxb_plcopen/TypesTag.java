package it.moise.plc.uml.jaxb_plcopen;

import org.plcopen.xml.tc6_0201.ObjectFactory;
import org.plcopen.xml.tc6_0201.Project;
/**
 * Classe che permette di creare un oggetto Project.Types completo di tutti i suoi valori
 * @author De Carvalho Moïse
 *
 */
public class TypesTag extends Project.Types {
	//private Project.Types types;
	private Project.Types.DataTypes dataTypes;
	
	private Project.Types.Pous pous;
	
	/**
	 * Metodo costruttore
	 */
	public TypesTag() {
		super();
		//this.types = new ObjectFactory().createProjectTypes();
		this.createTypes();
	}
	/**
	 * Metodo per riempire l'oggetto
	 */
	public void createTypes(){
		this.dataTypes = new ObjectFactory().createProjectTypesDataTypes();
		
		this.pous = new ObjectFactory().createProjectTypesPous();
		
		this.setPous(this.pous);
		this.setDataTypes(this.dataTypes);
		System.out.println ("Oggetto TypesTag creato");
	}
	
	/**
	 * Imposta il valore di Types
	 * @param value
	 */
	public void setTypes(TypesTag value){
	//	this.types = value;
	}
	/**
	 * Restituisce Project.Types
	 * @return Types
	 */
	public Project.Types getTypes(){
		return this;
	}

	/**
	 * Imposta il valore di DataTypes
	 * @param value
	 */
	public void setDataTypes(DataTypes value){
		this.dataTypes = value;
	}
	/**
	 * Restituisce Project.Types.DataTypes
	 * @return DataTypes
	 */
	public Project.Types.DataTypes getDataTypes(){
		return this.dataTypes;
	}
	
	
	
}
