/**
 * 
 */
package it.moise.plc.uml;

import it.moise.plc.uml.interfaces.InterfaceNestedClassifier;

import java.util.ArrayList;

/**
 * Costruisce un oggetto di tipo nestedclassifier
 * @author De Carvalho Moise
 *
 */
public class NestedClassifier implements InterfaceNestedClassifier {
	private String id = null;
	private String name = null;
	private boolean isActive = false;
	private Object type = null;

	/**
	 * Costruttore senza parametri
	 */
	public NestedClassifier() {
	}

	/**
	 * Set l'id del NestedClassifier
	 * @param id Id del nestedClassifier
	 * @see it.moise.plc.uml.interfaces.InterfaceNestedClassifier#setId(java.lang.String)
	 **/
	@Override
	public void setId(String id) {
		this.id = id;

	}

	/**
	 * Restituisce l'id del NestedClassifier
	 * @see it.moise.plc.uml.interfaces.InterfaceNestedClassifier#getId()
	 **/
	@Override
	public String getId() {
		// 
		return this.id;
	}

	/**
	 * Set il name del NestedClassifier
	 * @param name Nome del nestedclassifier
	 * @see it.moise.plc.uml.interfaces.InterfaceNestedClassifier#setName(java.lang.String)
	 **/
	@Override
	public void setName(String name) {
		this.name = name;

	}

	/**
	 * Restituisce il name del NestedClassifier
	 * @see it.moise.plc.uml.interfaces.InterfaceNestedClassifier#getName()
	 **/
	@Override
	public String getName() {
		// 
		return this.name;
	}

	/**
	 * Set il isActive del NestedClassifier
	 * @param isActive Valore booleano
	 * @see it.moise.plc.uml.interfaces.InterfaceNestedClassifier#setIsActive(boolean)
	 **/
	@Override
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;

	}

	/**
	 * Restituisce il valore di isActive del NestedClassifier
	 * @see it.moise.plc.uml.interfaces.InterfaceNestedClassifier#getIsActive()
	 **/
	@Override
	public boolean getIsActive() {
		// 
		return this.isActive;
	}

	/**
	 * Aggiunge un nodo alla lista del NestedClassifier
	 * @param nestedClassifier Oggetto di tipo object
	 * @see it.moise.plc.uml.interfaces.InterfaceNestedClassifier#setTypeNestedClassifier(java.lang.Object)
	 **/
	@Override
	public void setTypeNestedClassifier(Object nestedClassifier) {
		this.type = nestedClassifier;

	}

	/**
	 * Restituisce ilvalore del nestedClassifier
	 * @see it.moise.plc.uml.interfaces.InterfaceNestedClassifier#getTypeNestedClassifier()
	 **/
	@Override
	public Object getTypeNestedClassifier() {
		// 
		return this.type;
	}
	
	
	

}
