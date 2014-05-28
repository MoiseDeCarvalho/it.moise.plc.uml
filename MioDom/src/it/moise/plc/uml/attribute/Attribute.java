package it.moise.plc.uml.attribute;

import it.moise.plc.uml.value.Value;

import org.w3c.dom.NamedNodeMap;

public class Attribute<T> implements it.moise.plc.uml.interfaces.attribute.InterfaceAttribute<T>{
	private String id = null;
	private String name = null;
	private String type = "";
	private boolean isLeaf = false;
	private boolean isOrdered = false;
	private boolean isDerived = false;
	private boolean isDerivedUnion = false;
	private Value defaultValue = new Value();
	/**
	 * 
	 **/
	
	public Attribute(NamedNodeMap node){
		String delimiter ="xmi:";
		String temp[] = null;
		//System.out.println("\n**********Sezione di caricamento degli attributi *****ownedAttribute*************");
		for (int i=0; i < node.getLength(); i++){
			temp = null;
			delimiter ="xmi:";
			//System.out.println(node.item(i).getNodeName() + " -- " + node.item(i).getNodeValue());
			temp = node.item(i).getNodeName().split(delimiter);
			if (temp[0].equalsIgnoreCase("")) {
				temp[0] = temp[1];
			}
			if (temp.length > 0){
				//temp = node.item(i).getNodeName().split(delimiter);
				//System.out.println("\t ---- " + temp[0]);
				
				switch (temp[0]){
				case "name":
					this.setName(node.item(i).getNodeValue());
					//System.out.println("\t ---- " + this.getName()+"    ecoo il NAME");
					break;
				case "id":
					this.setId(node.item(i).getNodeValue());
					//System.out.println("\t ---- " + this.getId()+"    ecoo il ID");
					break;
				case "type":
					this.setType(node.item(i).getNodeValue());
					//System.out.println("\t ---- " + this.getType()+"    ecoo il TYPE");
					break;
				case "isLeaf":
					this.setIsLeaf(Boolean.valueOf(node.item(i).getNodeValue()));
					//System.out.println("\t ---- " + this.getIsLeaf()+"    ecoo il ISLEAF");
					break;	
				case "isOrdered":
					this.setIsOrdered(Boolean.valueOf(node.item(i).getNodeValue()));
					//System.out.println("\t ---- " + this.getIsOrdered()+"    ecoo il ISORDERED");
					break;
				case "isDerived":
					this.setIsDerived(Boolean.valueOf(node.item(i).getNodeValue()));
					//System.out.println("\t ---- " + this.getIsDerived()+"    ecoo il ISDERIVED");
					break;
				case "isDerivedUnion":
					this.setIsDerivedUnion(Boolean.valueOf(node.item(i).getNodeValue()));
					//System.out.println("\t ---- " + this.getIsDerived()+"    ecoo il ISDERIVEDUNION");
					break;
				}
			}
		}
		//System.out.println("\n**********Fine caricamento degli attributi *****ownedAttribute*************");
	}
	public Attribute() {
	}
	
	/**
	 * Set l'id dell'Attribute
	 * @param String id
	 * @see it.moise.plc.uml.interfaces.attribute.InterfaceAttribute#setId(java.lang.String)
	 **/
	@Override
	public void setId(String id) {
		this.id = id;

	}

	/**
	 * restituisce l'id dell'Attribute
	 * @see it.moise.plc.uml.interfaces.attribute.InterfaceAttribute#getId()
	 **/
	@Override
	public String getId() {
		// 
		return this.id;
	}

	/**
	 * Set il nome dell'Attribute
	 * @param String name
	 * @see it.moise.plc.uml.interfaces.attribute.InterfaceAttribute#setName(java.lang.String)
	 **/
	@Override
	public void setName(String name) {
		this.name = name;

	}

	/** 
	 * Restituisce il nome dell'Attribute
	 * @see it.moise.plc.uml.interfaces.attribute.InterfaceAttribute#getName()
	 **/
	@Override
	public String getName() {
		// 
		return this.name;
	}

	/**
	 * Set il type dell'Attribute
	 * @param String type
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
	 * Set la variabile booleana isLeaf
	 * @param boolean isLeaf
	 * @see it.moise.plc.uml.interfaces.attribute.InterfaceAttribute#setIsLeaf(boolean)
	 **/
	@Override
	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;

	}

	/**
	 * Restituisce la variabile booleana isLeaf
	 * @see it.moise.plc.uml.interfaces.attribute.InterfaceAttribute#getIsLeaf()
	 **/
	@Override
	public boolean getIsLeaf() {
		// 
		return this.isLeaf;
	}

	/**
	 * Set  la variabile booleana isOrdered
	 * @param boolean isOrdered
	 * @see it.moise.plc.uml.interfaces.attribute.InterfaceAttribute#setIsOrdered(boolean)
	 **/
	@Override
	public void setIsOrdered(boolean isOrdered) {
		this.isOrdered = isOrdered;

	}

	/**
	 * Restituisce la variabile booleana isOrdered
	 * @see it.moise.plc.uml.interfaces.attribute.InterfaceAttribute#getIsOrdered()
	 **/
	@Override
	public boolean getIsOrdered() {
		// 
		return this.isOrdered;
	}

	/**
	 * Set la variabile booleana isDerived
	 * @param boolean isDerived
	 * @see it.moise.plc.uml.interfaces.attribute.InterfaceAttribute#setIsDerived(boolean)
	 **/
	@Override
	public void setIsDerived(boolean isDerived) {
		this.isDerived = isDerived;

	}

	/**
	 * Restituisce la variabile booleana isDerived 
	 * @see it.moise.plc.uml.interfaces.attribute.InterfaceAttribute#getIsDerived()
	 **/
	@Override
	public boolean getIsDerived() {
		// 
		return this.isDerived;
	}

	/**
	 * Set la variabile booleana isDerivedUnion
	 * @param boolean isDerivedUnion
	 * @see it.moise.plc.uml.interfaces.attribute.InterfaceAttribute#setIsDerivedUnion(boolean)
	 **/
	@Override
	public void setIsDerivedUnion(boolean isDerivedUnion) {
		this.isDerivedUnion = isDerivedUnion;

	}

	/**
	 * Restituisce la variabile booleana isDerivedUnion
	 * @see it.moise.plc.uml.interfaces.attribute.InterfaceAttribute#getIsDerivedUnion()
	 **/
	@Override
	public boolean getIsDerivedUnion() {
		// 
		return this.isDerivedUnion;
	}

	/**
	 * Set la variabile attributo defaultValue
	 * @param Value defaultValue
	 * @see it.moise.plc.uml.interfaces.attribute.InterfaceAttribute#setDefaulValue(it.moise.plc.uml.value.Value)
	 **/
	@Override
	public void setDefaulValue(Value defaultValue) {
		this.defaultValue = defaultValue;

	}

	/**
	 * Restituisce la variabile defaultValue
	 * @see it.moise.plc.uml.interfaces.attribute.InterfaceAttribute#getDefalutValue()
	 **/
	@Override
	public Value getDefalutValue() {
		// 
		return this.defaultValue;
	}

}
