/**
 * 
 */
package it.moise.plc.uml;

import it.moise.plc.uml.attribute.Attribute;
import it.moise.plc.uml.interfaces.InterfaceTypeParameter;

import java.util.ArrayList;

import org.w3c.dom.NamedNodeMap;

/**
 * @author De Carvalho Moïse
 *
 */
public class TypeParameter extends Signal implements InterfaceTypeParameter {
	private String id = null;
	private String name = null;
	private String type = null;
	private boolean isLeaf = false;
	private boolean isAbstract = false;
	private boolean isActive = false;
	private String visibility = null;
	private ArrayList<Attribute> listAttribute = new ArrayList<Attribute>();
	
	/**
	 * Cosctruttore con parametro
	 * @param NamedNodeMap
	 */
	public TypeParameter(NamedNodeMap node){
		String delimiter ="xmi:";
		String temp[] = null;
		//System.out.println("\n**********Sezione di caricamento degli attributi *****Signal*************");
		for (int i=0; i < node.getLength(); i++){
			temp = null;
			delimiter ="xmi:";
			//System.out.println(node.item(i).getNodeName() + " -- " + node.item(i).getNodeValue());
			temp = node.item(i).getNodeName().split(" ");
			if (temp[0].equalsIgnoreCase("")) {
				temp[0] = temp[1];
			}
			if (temp.length > 0){
				//temp = node.item(i).getNodeName().split(delimiter);
				//System.out.println("\t ---- " + temp[0]);
				
				switch (temp[0]){
				case "isAbstract":
					this.setIsAbstract(Boolean.valueOf(node.item(i).getNodeValue()));
					//System.out.println("\t ---- " + this.getIsAbstract()+"    ecoo il ISABSTRACT");
					break;
				case "isLeaf":
					this.setIsLeaf(Boolean.valueOf(node.item(i).getNodeValue()));
					//System.out.println("\t ---- " + this.getIsLeaf()+"    ecoo il ISLEAF");
					break;
				case "name":
					this.setName(node.item(i).getNodeValue());
					//System.out.println("\t ---- " + this.getName()+"    ecoo il NAME");
					break;
				case "xmi:id":
					this.setId(node.item(i).getNodeValue());
					//System.out.println("\t ---- " + this.getId()+"    ecoo il ID");
					break;
				case "xmi:type":
					this.setType(node.item(i).getNodeValue());
					//System.out.println("\t ---- " + this.getType()+"    ecoo il TYPE");
					break;
				case "isActive":
					this.setIsActive(Boolean.valueOf(node.item(i).getNodeValue()));
					//System.out.println("\t ---- " + this.getIsActive()+"    ecoo il ISACTIVE");
					break;
				
					
				}
			}
		}
		//System.out.println("\n**********Fine caricamento degli attributi *****Signal*************");
	}
	/**
	 * 
	 */
	public TypeParameter() {
	}

	/**
	 * Set l'id
	 * @param String
	 * @see it.moise.plc.uml.interfaces.InterfaceSignal#setId(java.lang.String)
	 */
	@Override
	public void setId(String id) {
		this.id = id;

	}

	/**
	 * Restituisce l'id
	 * @see it.moise.plc.uml.interfaces.InterfaceSignal#getId()
	 */
	@Override
	public String getId() {
		// 
		return this.id;
	}

	/**
	 * Set il name
	 * @param String
	 * @see it.moise.plc.uml.interfaces.InterfaceSignal#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;

	}

	/**
	 * Restituisce in name
	 * @see it.moise.plc.uml.interfaces.InterfaceSignal#getName()
	 */
	@Override
	public String getName() {
		// 
		return this.name;
	}

	/**
	 * Set il type
	 * @param String
	 * @see it.moise.plc.uml.interfaces.InterfaceSignal#setType(java.lang.String)
	 */
	@Override
	public void setType(String type) {
		this.type = type;

	}

	/**
	 * Restituisce il type
	 * @see it.moise.plc.uml.interfaces.InterfaceSignal#getType()
	 */
	@Override
	public String getType() {
		// 
		return this.type;
	}

	/**
	 * Set  isLeaf
	 * @p boolean
	 * @see it.moise.plc.uml.interfaces.InterfaceSignal#setIsLeaf(boolean)
	 */
	@Override
	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;

	}

	/**
	 * Restituisce isLeaf
	 * @see it.moise.plc.uml.interfaces.InterfaceSignal#getIsLeaf()
	 */
	@Override
	public boolean getIsLeaf() {
		// 
		return this.isLeaf;
	}

	/**
	 * Set isAbstract
	 * @param boolean
	 * @see it.moise.plc.uml.interfaces.InterfaceSignal#setIsAbstract(boolean)
	 */
	@Override
	public void setIsAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;

	}

	/**
	 * Restituisce isAbtract
	 * @see it.moise.plc.uml.interfaces.InterfaceSignal#getIsAbstract()
	 */
	@Override
	public boolean getIsAbstract() {
		// 
		return this.isAbstract;
	}

	/**
	 * Aggiunge un nodo alla lista Attributi
	 * @param Attribute
	 * @see it.moise.plc.uml.interfaces.InterfaceSignal#setAddListAttribute(it.moise.plc.uml.attribute.Attribute)
	 */
	@Override
	public  void setAddListAttribute(Attribute attribute) {
		this.listAttribute.add(attribute);

	}

	/**
	 * Restituisce la lista attributi
	 * @see it.moise.plc.uml.interfaces.InterfaceSignal#getListAttribute()
	 */
	@Override
	public ArrayList<Attribute> getListAttribute() {
		// 
		return this.listAttribute;
	}

	

	/**
	 * Set isActive
	 * @param boolean
	 * @see it.moise.plc.uml.interfaces.InterfaceTypeParameter#setIsActive(boolean)
	 */
	@Override
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;

	}

	/**
	 * Restituisce isActive
	 * @see it.moise.plc.uml.interfaces.InterfaceTypeParameter#getIsActive()
	 */
	@Override
	public boolean getIsActive() {
		// 
		return this.isActive;
	}

	/**
	 * Set visibility
	 * @param string
	 * @see it.moise.plc.uml.interfaces.InterfaceTypeParameter#setVisibility(java.lang.String)
	 */
	@Override
	public void setVisibility(String visibility) {
		this.visibility = visibility;

	}

	/**
	 * Restituisce visibility
	 * @see it.moise.plc.uml.interfaces.InterfaceTypeParameter#getVisibility()
	 */
	@Override
	public String getVisibility() {
		// 
		return this.visibility;
	}

}
