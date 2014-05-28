/**
 * 
 */
package it.moise.plc.uml.pseudostate;

import it.moise.plc.uml.interfaces.pseudostate.InterfaceExitPoint;

import org.w3c.dom.NamedNodeMap;

/**
 * @author De Carvalho Moïse
 *
 */
public class ExitPoint extends PseudoState implements it.moise.plc.uml.interfaces.pseudostate.InterfaceExitPoint {
	private String id = null;
	private String name = null;
	private String type = null;
	private String kind = null;
	private int level = 0;
	/**
	 * Costruttore con parametro nodo
	 * @param NamedNodeMap
	 */
	public ExitPoint (NamedNodeMap node){
		String delimiter ="xmi:";
		String temp[] = null;
		//System.out.println("\n**********Sezione di caricamento degli attributi *****ExitPoint - Pseudostate*************");
		for (int i=0; i < node.getLength(); i++){
			temp = null;
			delimiter ="xmi:";
			//System.out.println(node.item(i).getNodeName() + " -- " + node.item(i).getNodeValue());
			temp = node.item(i).getNodeName().split(delimiter);
			if (temp[0].equalsIgnoreCase("") && temp.length > 1) {
				temp[0] = temp[1];
			}
			if (temp.length > 0){
				//temp = node.item(i).getNodeName().split(delimiter);
				//System.out.println("\t ---- " + temp[0]);
				
				switch (temp[0]){
				case "id":
					this.setId(node.item(i).getNodeValue());
					//System.out.println("\t ---- " + this.getId()+"    ecoo il ID");
					break;
				case "type":
					this.setType(node.item(i).getNodeValue());
					//System.out.println("\t ---- " + this.getType()+"    ecoo il TYPE");
					break;
				case "name":
					this.setName(node.item(i).getNodeValue());
					//System.out.println("\t ---- " + this.getName() +"    ecco NAME");
					break;
				case "kind":
					this.setKind(node.item(i).getNodeValue());
					//System.out.println("\t ---- " + this.getKind() +"    ecco KIND");
					break;
				}
			}
		}
		//System.out.println("\n**********Fine caricamento degli attributi *****ExitPoint - Pseudostate*************");
	}
	/**
	 * 
	 */
	public ExitPoint() {
	}
	/**
	 * Set l'id
	 * @param String id
	 * @see it.moise.plc.uml.interfaces.pseudostate.InterfacePseudoState#setId(java.lang.String)
	 */
	@Override
	public void setId(String id) {
		this.id = id;

	}

	/** 
	 * Restituisce l'id
	 * @see it.moise.plc.uml.interfaces.pseudostate.InterfacePseudoState#getId()
	 */
	@Override
	public String getId() {
		// 
		return this.id;
	}

	/** 
	 * Set il nome
	 * @param String name
	 * @see it.moise.plc.uml.interfaces.pseudostate.InterfacePseudoState#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;

	}

	/** 
	 * Restituisce il name
	 * @see it.moise.plc.uml.interfaces.pseudostate.InterfacePseudoState#getName()
	 */
	@Override
	public String getName() {
		// 
		return this.name;
	}

	/** 
	 * Set il type
	 * @param String type
	 * @see it.moise.plc.uml.interfaces.pseudostate.InterfacePseudoState#setType(java.lang.String)
	 */
	@Override
	public void setType(String type) {
		this.type = type;

	}

	/** 
	 * Restituisce il type
	 * @see it.moise.plc.uml.interfaces.pseudostate.InterfacePseudoState#getType()
	 */
	@Override
	public String getType() {
		// 
		return this.type;
	}
	
	/** 
	 * Set il kind
	 * @param String 
	 * @see it.moise.plc.uml.interfaces.pseudostate.InterfacePseudoState#setKind(java.lang.String)
	 */
	@Override
	public void setKind(String kind) {
		this.kind = kind;

	}

	/** 
	 * Restituisce il kind
	 * @see it.moise.plc.uml.interfaces.pseudostate.InterfacePseudoState#getKind()
	 */
	@Override
	public String getKind() {
		// 
		return this.kind;
	}
	
	/**
	 * Set il livello
	 * @param int
	 * @see Interfaces.pseudostate.InterfacePseudoState#setLevel(java.util.int)
	 */
	@Override
	public void setLevel(int level){
		this.level = level;
	}
	/**
	 * Restituisce il livello di appartenenza
	 * @return level
	 */
	@Override
	public int getLevel(){
		return this.level;
	}
}
