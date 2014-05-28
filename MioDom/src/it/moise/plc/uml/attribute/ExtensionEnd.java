package it.moise.plc.uml.attribute;

import org.w3c.dom.NamedNodeMap;

public class ExtensionEnd extends Attribute implements it.moise.plc.uml.interfaces.attribute.InterfaceExtensionEnd{
	private boolean isReadOnly = false;
	private boolean isStatic = false;
	private String subsettedProperty = null;
	/**
	 * Carico l'oggetto Port con i valori dell'oggeto attribute e il nodo in input
	 * @param <T>
	 * 
	 **/
	public <T> ExtensionEnd(Attribute<T> attribute, NamedNodeMap node){
		
		this.setId(attribute.getId());
		this.setName(attribute.getName());
		this.setType(attribute.getType());
		this.setIsLeaf(attribute.getIsLeaf());
		this.setIsOrdered(attribute.getIsOrdered());
		this.setIsDerived(attribute.getIsDerivedUnion());
		this.setIsDerivedUnion(attribute.getIsDerivedUnion());
		this.setDefaulValue(attribute.getDefalutValue());
		
		String delimiter ="xmi:";
		String temp[] = null;
		//System.out.println("\n**********Sezione di caricamento degli attributi *****ExtensionEnd*************");
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
				case "isStatic":
					this.setIsStatic (Boolean.valueOf(node.item(i).getNodeValue()));
					//System.out.println("\t ---- " + this.getIsStatic()+"    ecoo il ISSTATIC");
					break;
				case "isReadOnly":
					this.setIsReadOnly(Boolean.valueOf(node.item(i).getNodeValue()));
					//System.out.println("\t ---- " + this.getIsReadOnly()+"    ecoo il ISREADYONLY");
					break;
				
				case "subsettedProperty":
					this.setSubsettedProperty(node.item(i).getNodeValue());
					//System.out.println("\t ---- " + this.getSubsettedProperty()+"    ecoo il ISSUBSETTEDPROPERTY");
					break;
				}
			
			}
		}
		//System.out.println("\n**********Fine caricamento degli attributi *****ExtensionEnd*************");
	}
	
	/**
	 * 
	 **/
	public ExtensionEnd() {
	}
	

	/**
	 * Set la variabile booleana isStatic
	 * @param boolean isBehavior
	 * @see Interfaces.InterfaceInterfaceExtensionEnd#setIsStatic(boolean)
	 **/
	@Override
	public void setIsStatic(boolean isStatic) {
		this.isStatic = isStatic;

	}

	/**
	 * Restituisce la variabile booleana isStatic
	 * @see Interfaces.InterfaceInterfaceExtensionEnd#getIsStatic()
	 **/
	@Override
	public boolean getIsStatic() {
		// 
		return this.isStatic;
	}

	/**
	 * Set la variabile subsettedProperty
	 * @param boolean isConjugaded
	 * @see Interfaces.InterfaceInterfaceExtensionEnd#setSubsettedProperty(String)
	 **/
	@Override
	public void setSubsettedProperty(String subsettedProperty) {
		this.subsettedProperty = subsettedProperty;

	}

	/**
	 * Restituisce la variabilesubsettedProperty
	 * @see Interfaces.InterfaceInterfaceExtensionEnd#getSubsettedProperty()
	 **/
	@Override
	public String getSubsettedProperty() {
		// 
		return this.subsettedProperty;
	}

	
	
	/**
	 * Set la variabile booleana isReadOnly
	 * @param boolean isDerived
	 * @see Interfaces.InterfaceInterfaceExtensionEnd#setIsReadOnly(boolean)
	 **/
	@Override
	public void setIsReadOnly(boolean isReadOnly) {
		this.isReadOnly = isReadOnly;

	}

	/**
	 * Restituisce la variabile booleana isReadOnly
	 * @see Interfaces.InterfaceInterfaceExtensionEnd#getIsReadOnly()
	 **/
	@Override
	public boolean getIsReadOnly() {
		// 
		return this.isReadOnly;
	}
}
