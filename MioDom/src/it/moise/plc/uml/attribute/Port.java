/**
 * 
 **/
package it.moise.plc.uml.attribute;

import it.moise.plc.uml.interfaces.attribute.InterfacePort;

import org.w3c.dom.NamedNodeMap;

/**
 * @author De Carvalho Moïse
 *
 **/
public class Port extends Attribute implements InterfacePort {

	private boolean isBehavior = false;
	private boolean isConjugaded = false;

	/**
	 * Carico l'oggetto Port con i valori dell'oggeto attribute e il nodo in input
	 * @param <T>
	 * 
	 **/
	public <T> Port(Attribute<T> attribute, NamedNodeMap node){
		
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
		//System.out.println("\n**********Sezione di caricamento degli attributi *****Port*************");
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
				case "isBehavior":
					this.setIsBehavior (Boolean.valueOf(node.item(i).getNodeValue()));
					//System.out.println("\t ---- " + this.getIsBehavior()+"    ecoo il ISBEHAVIOR");
					break;
				case "isConjugated":
					this.setIsConjugated(Boolean.valueOf(node.item(i).getNodeValue()));
					//System.out.println("\t ---- " + this.getIsConjugated()+"    ecoo il ISCONJUGATED");
					break;
				}
			}
		}
		//System.out.println("\n**********Fine caricamento degli attributi *****Port*************");
	}
	
	public Port() {
	}

	
	/**
	 * Set la variabile booleana isBehavior
	 * @param boolean isBehavior
	 * @see it.moise.plc.uml.interfaces.attribute.InterfacePort#setIsBehavior(boolean)
	 **/
	@Override
	public void setIsBehavior(boolean isBehavior) {
		this.isBehavior = isBehavior;

	}

	/**
	 * Restituisce la variabile booleana isBehavior
	 * @see it.moise.plc.uml.interfaces.attribute.InterfacePort#getIsBehavior()
	 **/
	@Override
	public boolean getIsBehavior() {
		// 
		return this.isBehavior;
	}

	/**
	 * Set la variabile booleana isConjugaded
	 * @param boolean isConjugaded
	 * @see it.moise.plc.uml.interfaces.attribute.InterfacePort#setIsConjugaded(boolean)
	 **/
	@Override
	public void setIsConjugated(boolean isConjugaded) {
		this.isConjugaded = isConjugaded;

	}

	/**
	 * Restituisce la variabile booleana isCojugaded
	 * @see it.moise.plc.uml.interfaces.attribute.InterfacePort#getIsConjugaded()
	 **/
	@Override
	public boolean getIsConjugated() {
		// 
		return this.isConjugaded;
	}

	

}
