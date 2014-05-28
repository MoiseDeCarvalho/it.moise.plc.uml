/**
 * 
 */
package it.moise.plc.uml.connection_point;

import it.moise.plc.uml.interfaces.connection_point.InterfaceExit;

/**
 * @author De Carvalho Moïse
 *
 */
public class Exit implements InterfaceExit {
	private String id = null;
	private String name = null;
	private String type = null;
	private String kind = null;
	/**
	 * 
	 */
	public Exit() {
	}

	/**
	 * Set l'id dell'Exit
	 * @param String id
	 * @see it.moise.plc.uml.interfaces.connection_point.InterfaceExit#setId(java.lang.String)
	 **/
	@Override
	public void setId(String id) {
		this.id = id;

	}

	/**
	 * Restituisce l'id dell'Exit
	 * @see it.moise.plc.uml.interfaces.connection_point.InterfaceExit#getId()
	 **/
	@Override
	public String getId() {
		// 
		return this.id;
	}

	/**
	 * Set il nome dell'Exit
	 * @param String name
	 * @see it.moise.plc.uml.interfaces.connection_point.InterfaceExit#setName(java.lang.String)
	 **/
	@Override
	public void setName(String name) {
		this.name = name;

	}

	/**
	 * Restituisce il nome dell'Exit
	 * @see it.moise.plc.uml.interfaces.connection_point.InterfaceExit#getName()
	 **/
	@Override
	public String getName() {
		// 
		return this.name;
	}

	/**
	 * Set il tipo dell'Exit
	 * @param String type
	 * @see it.moise.plc.uml.interfaces.connection_point.InterfaceExit#setType(java.lang.String)
	 **/
	@Override
	public void setType(String type) {
		this.type = type;

	}

	/**
	 * Restituisce il tipo dell'Exit
	 * @see it.moise.plc.uml.interfaces.connection_point.InterfaceExit#getType()
	 **/
	@Override
	public String getType() {
		// 
		return this.type;
	}
	
	/**
	 * Set il kind dell'Exit
	 * @param String kind
	 * @see it.moise.plc.uml.interfaces.connection_point.InterfaceExit#seKind(java.lang.String)
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
