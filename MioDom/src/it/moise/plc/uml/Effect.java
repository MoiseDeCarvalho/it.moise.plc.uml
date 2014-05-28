/**
 * 
 */
package it.moise.plc.uml;

/**
 * Classe che contiene tutti i valori di un Oggetto Effect se impostata
 * @author De Carvalho Moise
 *
 */
public class Effect<T> implements it.moise.plc.uml.interfaces.InterfaceEffect<T> {
	private String id = null;
	private String name = null;
	private String type = null;
	private String visibility = null;
	private boolean isLeaf = false;
	private boolean isActive = false;
	private boolean isAbstract = false;
	
	/**
	 * Metodo costruttore senza parametri
	 */
	public Effect() {
	}

	/**
	 * Set l'id di effect
	 * @param id conttiene l'id di effect
	 * @see it.moise.plc.uml.interfaces.InterfaceEffect#setId(java.lang.String)
	 */
	@Override
	public void setId(String id) {
		this.id = id;

	}

	/** 
	 * Restituisce l'id
	 * @see it.moise.plc.uml.interfaces.InterfaceEffect#getId()
	 */
	@Override
	public String getId() {
		// 
		return this.id;
	}

	/** 
	 * Set il nome
	 * @param name Nome di effect
	 * @see it.moise.plc.uml.interfaces.InterfaceEffect#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;

	}

	/** 
	 * Restituisce il name
	 * @see it.moise.plc.uml.interfaces.InterfaceEffect#getName()
	 */
	@Override
	public String getName() {
		// 
		return this.name;
	}

	/** 
	 * Set il type
	 * @param type Tipo di effect
	 * @see it.moise.plc.uml.interfaces.InterfaceEffect#setType(java.lang.String)
	 */
	@Override
	public void setType(String type) {
		this.type = type;

	}

	/** 
	 * Restituisce il type
	 * @see it.moise.plc.uml.interfaces.InterfaceEffect#getType()
	 */
	@Override
	public String getType() {
		// 
		return this.type;
	}

	/** 
	 * Set isLeaf
	 * @param isLeaf Valore booleano
	 * @see it.moise.plc.uml.interfaces.InterfaceEffect#setIsLeaf(boolean)
	 */
	@Override
	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;

	}

	/** 
	 * Restituisce isLeaf
	 * @see it.moise.plc.uml.interfaces.InterfaceEffect#getIsLeaf()
	 */
	@Override
	public boolean getIsLeaf() {
		// 
		return this.isLeaf;
	}

	/** 
	 * Set isAbstract
	 * @param isAbstract Valore booleano
	 * @see it.moise.plc.uml.interfaces.InterfaceEffect#setIsAbstract(boolean)
	 */
	@Override
	public void setIsAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;

	}

	/** 
	 * Restituisce isAbstract
	 * @see it.moise.plc.uml.interfaces.InterfaceEffect#getIsAbstract()
	 */
	@Override
	public boolean getIsAbstract() {
		// 
		return this.isAbstract;
	}

	/** 
	 * Set isActive
	 * @param isActive Valore booleano
	 * @see it.moise.plc.uml.interfaces.InterfaceEffect#setIsActive(boolean)
	 */
	@Override
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;

	}

	/** 
	 * Restituisce isActive
	 * @see it.moise.plc.uml.interfaces.InterfaceEffect#getIsActive()
	 */
	@Override
	public boolean getIsActive() {
		// 
		return this.isActive;
	}

	/** 
	 * Set visibility
	 * @param visibility Indica la visibilita'
	 * @see it.moise.plc.uml.interfaces.InterfaceEffect#setVisibility(java.lang.String)
	 */
	@Override
	public void setVisibility(String visibility) {
		this.visibility = visibility;

	}

	/** 
	 * Restituisce visibility
	 * @see it.moise.plc.uml.interfaces.InterfaceEffect#getVisibility()
	 */
	@Override
	public String getVisibility() {
		// 
		return this.visibility;
	}

}
