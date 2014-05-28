/**
 * 
 **/
package it.moise.plc.uml.connection_point;

import it.moise.plc.uml.interfaces.connection_point.InterfaceConnectionPoint;

/**
 * @author De Carvalho Moïse
 *
 **/
public class ConnectionPoint implements InterfaceConnectionPoint {
	private String id = null;
	private String name = null;
	private String type = null;
	private String kind = null;
	/**
	/**
	 * 
	 **/
	public ConnectionPoint() {
	}

	/**
	 * Set l'id del ConnectionPoint
	 * @param String id
	 * @see it.moise.plc.uml.interfaces.connection_point.InterfaceConnectionPoint#setId(java.lang.String)
	 **/
	@Override
	public void setId(String id) {
		this.id = id;

	}

	/**
	 * Restituisce l'id del ConnectionPoint
	 * @see it.moise.plc.uml.interfaces.connection_point.InterfaceConnectionPoint#getId()
	 **/
	@Override
	public String getId() {
		// 
		return this.id;
	}

	/**
	 * Set il nome del ConnectionPoint
	 * @param String name
	 * @see it.moise.plc.uml.interfaces.connection_point.InterfaceConnectionPoint#setName(java.lang.String)
	 **/
	@Override
	public void setName(String name) {
		this.name = name;

	}

	/**
	 * Restituisce il nome del ConnectionPoint
	 * @see it.moise.plc.uml.interfaces.connection_point.InterfaceConnectionPoint#getName()
	 **/
	@Override
	public String getName() {
		// 
		return this.name;
	}

	/**
	 * Set il tipo del ConnectionPoint
	 * @param String type
	 * @see it.moise.plc.uml.interfaces.connection_point.InterfaceConnectionPoint#setType(java.lang.String)
	 **/
	@Override
	public void setType(String type) {
		this.type = type;

	}

	/**
	 * Restituisce il tipo del ConnectionPoint
	 * @see it.moise.plc.uml.interfaces.connection_point.InterfaceConnectionPoint#getType()
	 **/
	@Override
	public String getType() {
		// 
		return this.type;
	}

	/**
	 * Set il kind del del ConnectionPoint
	 * @param String kind
	 * @see it.moise.plc.uml.interfaces.connection_point.InterfaceConnectionPoint#setKind(java.lang.String)
	 **/
	@Override
	public void setKind(String kind) {
		this.kind = kind;

	}

	/**
	 * Restituisce il kind del del ConnectionPoint
	 * @see it.moise.plc.uml.interfaces.connection_point.InterfaceConnectionPoint#getKind()
	 **/
	@Override
	public String getKind() {
		// 
		return this.kind;
	}

}
