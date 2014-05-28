/**
 * 
 **/
package it.moise.plc.uml.connection_point;

import it.moise.plc.uml.interfaces.connection_point.InterfaceEntry;

/**
 * @author De Carvalho Moïse
 *
 **/
public class Entry implements InterfaceEntry {
	private String id = null;
	private String name = null;
	private String type = null;
	private String kind = null;
	/**
	 * 
	 **/
	public Entry() {
	}

	/**
	 * Set l'id dell'Entry
	 * @param String id
	 * @see it.moise.plc.uml.interfaces.connection_point.InterfaceEntry#setId(java.lang.String)
	 **/
	@Override
	public void setId(String id) {
		this.id = id;

	}

	/**
	 * Restituisce l'id dell'Entry
	 * @see it.moise.plc.uml.interfaces.connection_point.InterfaceEntry#getId()
	 **/
	@Override
	public String getId() {
		// 
		return this.id;
	}

	/**
	 * Set il nome dell'Entry
	 * @param String name
	 * @see it.moise.plc.uml.interfaces.connection_point.InterfaceEntry#setName(java.lang.String)
	 **/
	@Override
	public void setName(String name) {
		this.name = name;

	}

	/**
	 * Restituisce il nome dell'Entry
	 * @see it.moise.plc.uml.interfaces.connection_point.InterfaceEntry#getName()
	 **/
	@Override
	public String getName() {
		// 
		return this.name;
	}

	/**
	 * Set il tipo dell'Entry
	 * @param String type
	 * @see it.moise.plc.uml.interfaces.connection_point.InterfaceEntry#setType(java.lang.String)
	 **/
	@Override
	public void setType(String type) {
		this.type = type;

	}

	/**
	 * Restituisce il tipo dell'Entry
	 * @see it.moise.plc.uml.interfaces.connection_point.InterfaceEntry#getType()
	 **/
	@Override
	public String getType() {
		// 
		return this.type;
	}
	
	/**
	 * Set il kind dell'Entry
	 * @param String kind
	 * @see it.moise.plc.uml.interfaces.connection_point.InterfaceEntry#seKind(java.lang.String)
	 **/
	@Override
	public void setKind(String kind) {
		this.kind = kind;

	}

	/**
	 * Restituisce il kind dell'Entry
	 * @see it.moise.plc.uml.interfaces.connection_point.InterfaceEntry#getKind()
	 **/
	@Override
	public String getKind() {
		// 
		return this.kind;
	}

}
