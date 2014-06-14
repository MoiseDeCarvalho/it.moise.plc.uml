/**
 * 
 */
package it.moise.plc.uml;

import it.moise.plc.uml.interfaces.InterfaceIteration;

/**
 * Costruisc eun oggetto di tipo Iteration
 * @author De Carvalho Moise
 *
 */
public class Iteration implements InterfaceIteration {
	private String id = null;
	private String name = null;
	private String type = null;
	private String visibility = null;
	private boolean isLeaf = false;
	private boolean isActive = false;
	private boolean isAbstract = false;
	private Rule preCondition = new Rule();
	private Rule postCondition = new Rule();
	private Specification specification = new Specification();
	/**
	 * costruttore senza parametri
	 */
	public Iteration() {
	}

	/**
	 * Set l'id
	 * @param  id Contiene l'id
	 * @see it.moise.plc.uml.interfaces.InterfaceIteration#setId(java.lang.String)
	 */
	@Override
	public void setId(String id) {
		this.id = id;

	}

	/** 
	 * Restituisce l'id
	 * @see it.moise.plc.uml.interfaces.InterfaceIteration#getId()
	 */
	@Override
	public String getId() {
		// 
		return this.id;
	}

	/** 
	 * Set il nome
	 * @param name Contiene il nome
	 * @see it.moise.plc.uml.interfaces.InterfaceIteration#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;

	}

	/** 
	 * Restituisce il name
	 * @see it.moise.plc.uml.interfaces.InterfaceIteration#getName()
	 */
	@Override
	public String getName() {
		// 
		return this.name;
	}

	/** 
	 * Set il type
	 * @param type Contiene il tipo
	 * @see it.moise.plc.uml.interfaces.InterfaceIteration#setType(java.lang.String)
	 */
	@Override
	public void setType(String type) {
		this.type = type;

	}

	/** 
	 * Restituisce il type
	 * @see it.moise.plc.uml.interfaces.InterfaceIteration#getType()
	 */
	@Override
	public String getType() {
		// 
		return this.type;
	}

	/** 
	 * Set isLeaf
	 * @param  isLeaf Valore booelano
	 * @see it.moise.plc.uml.interfaces.InterfaceIteration#setIsLeaf(boolean)
	 */
	@Override
	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;

	}

	/** 
	 * Restituisce isLeaf
	 * @see it.moise.plc.uml.interfaces.InterfaceIteration#getIsLeaf()
	 */
	@Override
	public boolean getIsLeaf() {
		// 
		return this.isLeaf;
	}

	/** 
	 * Set isAbstract
	 * @param  isAbstract Valore booleano
	 * @see it.moise.plc.uml.interfaces.InterfaceIteration#setIsAbstract(boolean)
	 */
	@Override
	public void setIsAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;

	}

	/** 
	 * Restituisce isAbstract
	 * @see it.moise.plc.uml.interfaces.InterfaceIteration#getIsAbstract()
	 */
	@Override
	public boolean getIsAbstract() {
		// 
		return this.isAbstract;
	}

	/** 
	 * Set isActive
	 * @param isActive Valore booleano
	 * @see it.moise.plc.uml.interfaces.InterfaceIteration#setIsActive(boolean)
	 */
	@Override
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;

	}

	/** 
	 * Restituisce isActive
	 * @see it.moise.plc.uml.interfaces.InterfaceIteration#getIsActive()
	 */
	@Override
	public boolean getIsActive() {
		// 
		return this.isActive;
	}

	/** 
	 * Set visibility
	 * @param visibility Indica la visibilità
	 * @see it.moise.plc.uml.interfaces.InterfaceIteration#setVisibility(java.lang.String)
	 */
	@Override
	public void setVisibility(String visibility) {
		this.visibility = visibility;

	}

	/** 
	 * Restituisce visibility
	 * @see it.moise.plc.uml.interfaces.InterfaceIteration#getVisibility()
	 */
	@Override
	public String getVisibility() {
		// 
		return this.visibility;
	}
	
	/** 
	 * Set preCondition
	 * @param  preCondition Oggetto di tipo rule , regola
	 * @see it.moise.plc.uml.interfaces.InterfaceIteration#setPreCondition(it.moise.plc.uml.Rule)
	 */
	@Override
	public void setPreCondition(Rule preCondition) {
		this.preCondition = preCondition;

	}

	/** 
	 * Restituisce preCondition
	 * @see it.moise.plc.uml.interfaces.InterfaceIteration#getPreCondition()
	 */
	@Override
	public Rule getPreCondition() {
		// 
		return this.preCondition;
	}

	/** 
	 * Set postCondition
	 * @param postCondition Oggetto di tipo Rule
	 * @see it.moise.plc.uml.interfaces.InterfaceIteration#setPostCondition(it.moise.plc.uml.Rule)
	 */
	@Override
	public void setPostCondition(Rule postCondition) {
		this.postCondition = postCondition;

	}

	/** 
	 * Restituisce postCondition
	 * @see it.moise.plc.uml.interfaces.InterfaceIteration#getPostCondition()
	 */
	@Override
	public Rule getPostCondition() {
		// 
		return this.postCondition;
	}
	
	/**
	 * Set Specification
	 * @param specification Oggetto di tipo specification
	 * @see it.moise.plc.uml.interfaces.InterfaceIteration#setSpecification(it.moise.plc.uml.Specification)
	 */
	@Override
	public void setSpecification(Specification specification) {
		this.specification = specification;

	}

	/** 
	 * Restituisce specification
	 * @see it.moise.plc.uml.interfaces.InterfaceIteration#getSpecification()
	 */
	@Override
	public Specification getSpecification() {
		// 
		return this.specification;
	}

}
