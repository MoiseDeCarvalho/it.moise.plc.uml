/**
 * 
 */
package it.moise.plc.uml;

import it.moise.plc.uml.attribute.Attribute;

import java.util.ArrayList;

import org.w3c.dom.NamedNodeMap;

/**
 * Classe che realizza un oggetto di tipo signal
 * @author De Carvalho Moise
 *
 */
public class Signal implements it.moise.plc.uml.interfaces.InterfaceSignal {
	private String id = null;
	private String name = null;
	private String type = null;
	private String visibility = null;
	private boolean isLeaf = false;
	private boolean isAbstract = false;
	private ArrayList<Attribute> listAttribute = new ArrayList<Attribute>();
	/**
	 * Costruttore con parametri
	 * @param node Oggetto di tipo NamedNodeMap da analizzare
	 */
	public Signal(NamedNodeMap node){
		String delimiter ="xmi:";
		String temp[] = null;
		for (int i=0; i < node.getLength(); i++){
			temp = null;
			delimiter ="xmi:";
			temp = node.item(i).getNodeName().split(delimiter);
			if (temp[0].equalsIgnoreCase("")) {
				temp[0] = temp[1];
			}
			if (temp.length > 0){
				
				switch (temp[0]){
				case "isAbstract":
					this.setIsAbstract(Boolean.valueOf(node.item(i).getNodeValue()));
					break;
				case "isLeaf":
					this.setIsLeaf(Boolean.valueOf(node.item(i).getNodeValue()));
					break;
				case "name":
					this.setName(node.item(i).getNodeValue());
					break;
				case "id":
					this.setId(node.item(i).getNodeValue());
					break;
				case "type":
					this.setType(node.item(i).getNodeValue());
					break;
				case "visibility":
					this.setVisibility(node.item(i).getNodeValue());
					break;
				
					
				}
			}
		}
	}
	/**
	 * Costruttore senza parametri
	 */
	public Signal() {
	}

	/**
	 * Set l'id dell'Attribute
	 * @param id Id dell'oggetto
	 * @see it.moise.plc.uml.interfaces.attribute.InterfaceAttribute#setId(java.lang.String)
	 **/
	@Override
	public void setId(String id) {
		this.id = id;

	}

	/**
	 * Restituisce l'id dell'Attribute
	 * @see it.moise.plc.uml.interfaces.attribute.InterfaceAttribute#getId()
	 **/
	@Override
	public String getId() {
		// 
		return this.id;
	}

	/**
	 * Set il name dell'Attribute
	 * @param name Indica il nome
	 * @see it.moise.plc.uml.interfaces.attribute.InterfaceAttribute#setName(java.lang.String)
	 **/
	@Override
	public void setName(String name) {
		this.name = name;

	}

	/**
	 * Restituisce il name dell'Attribute
	 * @see it.moise.plc.uml.interfaces.attribute.InterfaceAttribute#getName()
	 **/
	@Override
	public String getName() {
		// 
		return this.name;
	}

	/**
	 * Set il type dell'Attribute
	 * @param  type Tipo dell'oggetto
	 * @see it.moise.plc.uml.interfaces.attribute.InterfaceAttribute#setType(java.lang.String)
	 **/
	@Override
	public void setType(String type) {
		this.type = type;

	}

	/**
	 * Restituisce il type dell'Attribute
	 * @see it.moise.plc.uml.interfaces.attribute.InterfaceAttribute#getType()
	 **/
	@Override
	public String getType() {
		// 
		return this.type;
	}

	
	/**
	 * Set visibility dell'Attribute
	 * @param visibility indica la visibilità 
	 * @see it.moise.plc.uml.interfaces.InterfaceSignal#setVisibility(java.lang.String)
	 **/
	@Override
	public void setVisibility(String visibility) {
		this.visibility = visibility;

	}

	/**
	 * Restituisce visibility dell'Attribute
	 * @see it.moise.plc.uml.interfaces.InterfaceSignal#getVisibility()
	 **/
	@Override
	public String getVisibility() {
		// 
		return this.visibility;
	}
	
	/**
	 * Set isLeaf dell'Attributo
	 * @param isLeaf Valore booleano
	 * @see it.moise.plc.uml.interfaces.attribute.InterfaceAttribute#setIsLeaf(boolean)
	 **/
	@Override
	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;

	}

	/**
	 * Restituisce il valore di isLeaf
	 * @see it.moise.plc.uml.interfaces.attribute.InterfaceAttribute#getIsLeaf()
	 **/
	@Override
	public boolean getIsLeaf() {
		// 
		return this.isLeaf;
	}

	/**
	 * Set il valore della variabile isAbstract dell'Attributo
	 * @param isAbstract Valore booleano 
	 * @see it.moise.plc.uml.interfaces.InterfaceSignal#setIsAbstract(boolean)
	 **/
	@Override
	public void setIsAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;

	}

	/**
	 * Restituisce il valore della variabile isAbstract
	 * @see it.moise.plc.uml.interfaces.InterfaceSignal#getIsAbstract()
	 **/
	@Override
	public boolean getIsAbstract() {
		// 
		return this.isAbstract;
	}

	/**
	 * Add un nodo alla lista degli attributi
	 * @param attribute Oggetto di tipo attribute
	 * @see it.moise.plc.uml.interfaces.InterfaceSignal#setAddListAttribute(it.moise.plc.uml.attribute.Attribute)
	 **/
	@Override
	public void setAddListAttribute(Attribute attribute) {
		this.listAttribute.add(attribute);

	}

	/**
	 * Restituisce la lista degli attributi
	 * @see it.moise.plc.uml.interfaces.InterfaceSignal#getListAttribute()
	 **/
	@Override
	public ArrayList<Attribute> getListAttribute() {
		// 
		return this.listAttribute;
	}

	

	

}
