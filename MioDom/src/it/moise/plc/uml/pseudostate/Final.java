/**
 * 
 */
package it.moise.plc.uml.pseudostate;

import it.moise.plc.uml.interfaces.pseudostate.InterfaceFinal;

/**
 * @author De Carvalho Moïse
 *
 */
public class Final extends PseudoState implements it.moise.plc.uml.interfaces.pseudostate.InterfaceFinal {
	private String id = null;
	private String name = null;
	private String type = null;
	private String kind = null;
	private int level = 0;
	/**
	 * 
	 */
	public Final() {
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
