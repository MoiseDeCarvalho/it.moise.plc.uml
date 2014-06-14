/**
 * 
 **/
package it.moise.plc.uml;

import it.moise.plc.uml.interfaces.InterfaceParameter;
import it.moise.plc.uml.value.LowerValue;
import it.moise.plc.uml.value.UpperValue;
import it.moise.plc.uml.value.Value;

import org.w3c.dom.NamedNodeMap;

/**
 * Costruisce un oggetto di tipo parameter
 * @author De Carvalho Moise
 *
 **/
public class Parameter implements InterfaceParameter {
	private String id = null;
	private String name = null;
	private TypeParameter typeParameter = new TypeParameter();
	private String xmiType = null;
	private String idTypeParameter = null;
	private String visibility = null;
	private boolean isOrdered = false;
	private boolean isException = false;
	private boolean isStream = false;
	private String effect = null;
	private Value value = new Value();
	private LowerValue lowerValue = new LowerValue();
	private UpperValue upperValue = new UpperValue();
	
	/**
	 * Costruttore con parametro
	 * @param node Oggetto di tipo NamedNodeMap da analizzare
	 */
	public Parameter(NamedNodeMap node){
		String delimiter ="xmi:";
		String temp[] = null;
		for (int i=0; i < node.getLength(); i++){
			temp = null;
			delimiter ="xmi:";
			temp = node.item(i).getNodeName().split(" ");
			if (temp[0].equalsIgnoreCase("") && temp.length > 1) {
				temp[0] = temp[1];
			}
			if (temp.length > 0){
				
				switch (temp[0]){
				case "xmi:id":
					this.setId(node.item(i).getNodeValue());
					break;
				case "xmi:type":
					this.setXmiType(node.item(i).getNodeValue());
					break;
				case "type":
					this.setIdTypeParameter(node.item(i).getNodeValue());
					break;
				case "name":
					this.setName(node.item(i).getNodeValue());
					break;
				case "visibility":
					this.setVisibility(node.item(i).getNodeValue());
					break;
				case "isOrdered":
					this.setIsOrdered(Boolean.valueOf(node.item(i).getNodeValue()));
					break;
				case "effect":
					this.setEffect(node.item(i).getNodeValue());
					break;
				case "isException":
					this.setIsException(Boolean.valueOf(node.item(i).getNodeValue()));
					break;
				case "isStream":
					this.setIsStream(Boolean.valueOf(node.item(i).getNodeValue()));
					break;
				}
			}
		}
	}
	/**
	 * Metodo costruttore senza parametri
	 **/
	public Parameter() {
	}

	/**
	 * Set l'id dell'Parameter
	 * @param id Contiene l'id
	 * @see it.moise.plc.uml.interfaces.InterfaceParameter#setId(java.lang.String)
	 **/
	@Override
	public void setId(String id) {
		this.id = id;

	}

	/**
	 * Restituisce l'id di Parameter
	 * @see it.moise.plc.uml.interfaces.InterfaceParameter#getId()
	 **/
	@Override
	public String getId() {
		return this.id;
	}

	/**
	 * Set il nome di Parameter
	 * @param name Contiene il nome
	 * @see it.moise.plc.uml.interfaces.InterfaceParameter#setName(java.lang.String)
	 **/
	@Override
	public void setName(String name) {
		this.name = name;

	}

	/**
	 * Restituisce il nome di Parameter
	 * @see it.moise.plc.uml.interfaces.InterfaceParameter#getName()
	 **/
	@Override
	public String getName() {
		return this.name;
	}

	
	/**
	 * Set idTypeParameter
	 * @param idTypeParameter Contiene il tipo di parametro 
	 * @see it.moise.plc.uml.interfaces.InterfaceParameter#setIdTypeParameter(java.lang.String)
	 **/
	@Override
	public void setIdTypeParameter(String idTypeParameter) {
		this.idTypeParameter = idTypeParameter;

	}

	/**
	 * Restituisce idTypeParameter
	 * @see it.moise.plc.uml.interfaces.InterfaceParameter#getTypeParameter()
	 **/
	@Override
	public String getIdTypeParameter() {
		return this.idTypeParameter;
	}
	
	/**
	 * Set il tipo di Parameter
	 * @param  typeParameter Contiene il tipo
	 * @see it.moise.plc.uml.interfaces.InterfaceParameter#setTypeParameter(it.moise.plc.uml.TypeParameter)
	 **/
	@Override
	public void setTypeParameter(TypeParameter typeParameter) {
		this.typeParameter = typeParameter;

	}

	/**
	 * Restituisce il tipo di Parameter
	 * @see it.moise.plc.uml.interfaces.InterfaceParameter#getTypeParameter()
	 **/
	@Override
	public TypeParameter getTypeParameter() {
		return this.typeParameter;
	}

	/**
	 * Set il XMItipo di Parameter
	 * @param xmiType Contiene il tipo XMI 
	 * @see it.moise.plc.uml.interfaces.InterfaceParameter#setXmiType(java.lang.String)
	 **/
	@Override
	public void setXmiType(String xmiType) {
		this.xmiType = xmiType;

	}

	/**
	 * Restituisce il XMItipo di Parameter
	 * @see it.moise.plc.uml.interfaces.InterfaceParameter#getXmiType()
	 **/
	@Override
	public String getXmiType() {
		return this.xmiType;
	}
	/**
	 * Set il visibility di Parameter
	 * @param visibility Indica la visibilità 
	 * @see it.moise.plc.uml.interfaces.InterfaceParameter#setVisibility(java.lang.String)
	 **/
	@Override
	public void setVisibility(String visibility) {
		this.visibility = visibility;

	}

	/**
	 * Restituisce il visibility di Parameter
	 * @see it.moise.plc.uml.interfaces.InterfaceParameter#getVisibility()
	 **/
	@Override
	public String getVisibility() {
		return this.visibility;
	}
	
	/**
	 * Set isOrdered di Parameter
	 * @param isOrdered  Valore booleano
	 * @see it.moise.plc.uml.interfaces.InterfaceParameter#setIsOrdered(boolean)
	 **/
	@Override
	public void setIsOrdered(boolean isOrdered) {
		this.isOrdered = isOrdered;

	}

	/**
	 * Restituisce isOrdered di Parameter
	 * @see it.moise.plc.uml.interfaces.InterfaceParameter#getIsOrdered()
	 **/
	@Override
	public boolean getIsOrdered() {
		return this.isOrdered;
	}
	
	/**
	 * Set effect di Parameter
	 * @param effect Contiene lìid dieffect 
	 * @see it.moise.plc.uml.interfaces.InterfaceParameter#setEffect(java.lang.String)
	 **/
	@Override
	public void setEffect(String effect) {
		this.effect = effect;

	}

	/**
	 * Restituisce effect di Parameter
	 * @see it.moise.plc.uml.interfaces.InterfaceParameter#getEffect()
	 **/
	@Override
	public String getEffect() {
		return this.effect;
	}
	
	/**
	 * Set il valore di  Parameter
	 * @param value Contiene il valore dell'id del parameter
	 * @see it.moise.plc.uml.interfaces.InterfaceParameter#setDefaultValue(it.moise.plc.uml.value.Value)
	 **/
	@Override
	public void setDefaultValue(Value value) {
		this.value = value;

	}

	/**
	 * Restituisce il valore
	 * @see it.moise.plc.uml.interfaces.InterfaceParameter#getDefaultValue()
	 **/
	@Override
	public Value getDefaultValue() {
		return this.value;
	}


	/**
	 * Set isException
	 * @param isException Valore booleano
	 * @see it.moise.plc.uml.interfaces.InterfaceParameter#setIsException(boolean)
	 **/
	@Override
	public void setIsException(boolean isException) {
		this.isException = isException;

	}

	/**
	 * Restituisce il valore di isException
	 * @see it.moise.plc.uml.interfaces.InterfaceParameter#getIsException()
	 **/
	@Override
	public boolean getIsException() {
		return this.isException;
	}

	/**
	 * Set isStream di Parameter
	 * @param isStream Valore booleano 
	 * @see it.moise.plc.uml.interfaces.InterfaceParameter#setIsStream(boolean)
	 **/
	@Override
	public void setIsStream(boolean isStream) {
		this.isStream = isStream;

	}

	/**
	 * Restituisce isStream di Parameter
	 * @see it.moise.plc.uml.interfaces.InterfaceParameter#getIsStream()
	 **/
	@Override
	public boolean getIsStream() {
		return this.isStream;
	}
	
	/**
	 * Set il valore lower di  Parameter
	 * @param lowerValue Oggetto di tipo lowervalue 
	 * @see it.moise.plc.uml.interfaces.InterfaceParameter#setLowerValue(it.moise.plc.uml.value.LowerValue)
	 **/
	@Override
	public void setLowerValue(LowerValue lowerValue) {
		this.lowerValue = lowerValue;

	}

	/**
	 * Restituisce il valore lower
	 * @see it.moise.plc.uml.interfaces.InterfaceParameter#getLowerValue()
	 **/
	@Override
	public LowerValue getLowerValue() {
		return this.lowerValue;
	}

	
	/**
	 * Set il valore upper di  Parameter
	 * @param upperValue Oggetto di tipo upperValue 
	 * @see it.moise.plc.uml.interfaces.InterfaceParameter#setUpperValue(it.moise.plc.uml.value.UpperValue)
	 **/
	@Override
	public void setUpperValue(UpperValue upperValue) {
		this.upperValue = upperValue;

	}

	/**
	 * Restituisce il valore upper
	 * @see it.moise.plc.uml.interfaces.InterfaceParameter#getUpperValue()
	 **/
	@Override
	public UpperValue getUpperValue() {
		return this.upperValue;
	}

}
