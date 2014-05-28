/**
 * 
 */
package it.moise.plc.uml;

import it.moise.plc.uml.interfaces.InterfaceActivity;

import java.util.ArrayList;

import org.w3c.dom.NamedNodeMap;

/**
 * @author De Carvalho Moise 
 * E' una variante del tag effect, a seconda del comportamento che viene scelto per la transition in questione
 */
public class Activity extends Effect implements InterfaceActivity {
	private String id = null;
	private String name = null;
	private String type = null;
	private String visibility = null;
	private boolean isLeaf = false;
	private boolean isActive = false;
	private boolean isAbstract = false;
	private boolean isReadyOnly = false;
	private boolean isSingleExecution = false;
	private String[] idPreCondition;
	private String[] idPostCondition;
	private ArrayList<Rule> listPreCondition = new ArrayList<Rule>();
	private ArrayList<Rule> listPostCondition = new ArrayList<Rule>();
	private ArrayList<Variable> listVariable = new ArrayList<Variable>();
	private Signal signal = new Signal();
	private ArrayList<Operation> listOperation = new ArrayList<Operation>();
	private ArrayList<Reception> listReception = new ArrayList<Reception>();
	private String specification = null;
	private Comment comment = new Comment();
	
	/**
	 * Costruttore con parametro
	 * @param NamedNodeMap node
	 */
	public Activity(NamedNodeMap node){
		String delimiter ="xmi:";
		String temp[] = null;

		for (int i=0; i < node.getLength(); i++){
			temp = null;
			delimiter ="xmi:";
			temp = node.item(i).getNodeName().split(delimiter);
			if (temp[0].equalsIgnoreCase("")) {
				temp[0] = temp[1];
			}
			if (temp.length > 0){		
				switch (temp[0]){
				case "name":
					this.setName(node.item(i).getNodeValue());
					break;
				case "id":
					this.setId(node.item(i).getNodeValue());
					break;
				case "type":
					this.setType(node.item(i).getNodeValue());
					break;
				case "isLeaf":
					this.setIsLeaf(Boolean.getBoolean(node.item(i).getNodeValue()));
					break;
				case "isAbstract":
					this.setIsAbstract(Boolean.getBoolean(node.item(i).getNodeValue()));
					break;
				case "isActive":
					this.setIsActive(Boolean.getBoolean(node.item(i).getNodeValue()));
					break;
				case "isReadyOnly":
					this.setIsReadyOnly(Boolean.getBoolean(node.item(i).getNodeValue()));
					break;
				case "isSingleExecution":
					this.setIsSingleExecution(Boolean.getBoolean(node.item(i).getNodeValue()));
					break;
				case "visibility":
					this.setVisibility(node.item(i).getNodeValue());
					break;
				case "postcondition":
					this.setIdPostCondition(node.item(i).getNodeValue());
					break;
				case "precondition":
					this.setIdPreCondition(node.item(i).getNodeValue());
					break;
				case "specification":
					this.setSpecification(node.item(i).getNodeValue());
					break;
				}
			}
		}
	}
	/**
	 * Metodo Costruttore senza parametri
	 */
	public Activity() {
		
	}

	/**
	 * Imposta il valore dell'id dell'activity
	 * @param id L'id dell'activity
	 * @see moise.dom.InterfaceActivity#setId(java.lang.String)
	 */
	@Override
	public void setId(String id) {
		this.id = id;

	}

	/** 
	 * Restituisce l'id dell'activity
	 * @see moise.dom.InterfaceActivity#getId()
	 */
	@Override
	public String getId() {
		
		return this.id;
	}

	/** 
	 * Set il nome dell'activity
	 * @param name Il nome dell'activity
	 * @see moise.dom.InterfaceActivity#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;

	}

	/** 
	 * Restituisce il name dell'activity
	 * @see moise.dom.InterfaceActivity#getName()
	 */
	@Override
	public String getName() {
		
		return this.name;
	}

	/** 
	 * Set il type dell'activity
	 * @param type Il tipo dell'activity
	 * @see moise.dom.InterfaceActivity#setType(java.lang.String)
	 */
	@Override
	public void setType(String type) {
		this.type = type;

	}

	/** 
	 * Restituisce il type dell'activity
	 * @see moise.dom.InterfaceActivity#getType()
	 */
	@Override
	public String getType() {
		
		return this.type;
	}

	/** 
	 * Set isLeaf dell'activity
	 * @param isLeaf Valore booleano dell'activity
	 * @see moise.dom.InterfaceActivity#setIsLeaf(boolean)
	 */
	@Override
	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;

	}

	/** 
	 * Restituisce isLeaf
	 * @see moise.dom.InterfaceActivity#getIsLeaf()
	 */
	@Override
	public boolean getIsLeaf() {
		
		return this.isLeaf;
	}

	/** 
	 * Set isAbstract
	 * @param isAbstract Valore booleano per indicare se è Abstract
	 * @see moise.dom.InterfaceActivity#setIsAbstract(boolean)
	 */
	@Override
	public void setIsAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;

	}

	/** 
	 * Restituisce isAbstract
	 * @see moise.dom.InterfaceActivity#getIsAbstract()
	 */
	@Override
	public boolean getIsAbstract() {
		
		return this.isAbstract;
	}

	/** 
	 * Set isActive
	 * @param isActive Valore Booleano per indicare se è isActive
	 * @see moise.dom.InterfaceActivity#setIsActive(boolean)
	 */
	@Override
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;

	}

	/** 
	 * Restituisce isActive
	 * @see moise.dom.InterfaceActivity#getIsActive()
	 */
	@Override
	public boolean getIsActive() {
		
		return this.isActive;
	}

	/** 
	 * Set visibility
	 * @param visibility Indica il grado della visibilita'
	 * @see moise.dom.InterfaceActivity#setVisibility(java.lang.String)
	 */
	@Override
	public void setVisibility(String visibility) {
		this.visibility = visibility;

	}

	/** 
	 * Restituisce visibility
	 * @see moise.dom.InterfaceActivity#getVisibility()
	 */
	@Override
	public String getVisibility() {
		
		return this.visibility;
	}

	/** 
	 * Set isReadyOnly
	 * @param isReadyOnly Indica se è di sola lettura
	 * @see it.moise.plc.uml.interfaces.InterfaceActivity#setIsReadyOnly(boolean)
	 */
	@Override
	public void setIsReadyOnly(boolean isReadyOnly) {
		this.isReadyOnly = isReadyOnly;

	}

	/** 
	 * Restituisce isReadyOnly
	 * @see it.moise.plc.uml.interfaces.InterfaceActivity#getIsReadyOnly()
	 */
	@Override
	public boolean getIsReadyOnly() {
		
		return this.isReadyOnly;
	}

	/** 
	 * Set isSingleExecution
	 * @param isSingleExecution Indica se è di singola esecuzione
	 * @see it.moise.plc.uml.interfaces.InterfaceActivity#setIsSingleExecution(boolean)
	 */
	@Override
	public void setIsSingleExecution(boolean isSingleExecution) {
		this.isSingleExecution = isSingleExecution;

	}

	/** 
	 * Restituisce isSingleExecution
	 * @see it.moise.plc.uml.interfaces.InterfaceActivity#getIsSingleExecution(boolean)
	 */
	@Override
	public boolean getIsSingleExecution() {
		return this.isSingleExecution;

	}
	
	/**
	 * Set l'id delle preCondition
	 * @param preCondition Indica il nome della precondition
	 * @see it.moise.plc.uml.interfaces.InterfaceActivity#setIdPreCondition(java.lang.string)
	 */
	@Override
	public void setIdPreCondition(String idPreCondition){
		// attenzione prima di fare l'assegnazione bisogna separare gli elementi passati sono stringhe unite
		
		String listElement[] = idPreCondition.split(" ");
		this.idPreCondition = new String[listElement.length];
		for (int i=0; i < listElement.length; i++){
			this.idPreCondition[i] = listElement[i];
		}
	}
	/**
	 * Restituisce l'id delle preCondition
	 * @see it.moise.plc.uml.interfaces.InterfaceActivity#getIdPreCondition()
	 */
	@Override
	public String[] getIdPreCondition(){
		return this.idPreCondition;
	}
	
	/** 
	 * Set preCondition
	 * @param preCondition Indica la rule della precondition
	 * @see it.moise.plc.uml.interfaces.InterfaceActivity#setAddListPreCondition(moise.plc.uml.Rule)
	 */
	@Override
	public void setAddListPreCondition(Rule preCondition) {
		this.listPreCondition.add(preCondition);

	}

	/** 
	 * Restituisce preCondition
	 * @see it.moise.plc.uml.interfaces.InterfaceActivity#getPreCondition()
	 */
	@Override
	public ArrayList<Rule> getListPreCondition() {
		
		return this.listPreCondition;
	}

	/**
	 * Set l'id delle postCondition
	 * @param postCondition Indica l'id della postCondition
	 * @see it.moise.plc.uml.interfaces.InterfaceActivity#setIdPostCondition(java.lang.string)
	 */
	@Override
	public void setIdPostCondition(String idPostCondition){
		// attenzione prima di fare l'assegnazione bisogna separare gli elementi passati sono stringhe unite
		
		String listElement[] = idPostCondition.split(" ");
		this.idPostCondition = new String[listElement.length];
		for (int i=0; i < listElement.length; i++){
			this.idPostCondition[i] = listElement[i];
		}
	}
	/**
	 * Restituisce l'id delle postCondition
	 * @see it.moise.plc.uml.interfaces.InterfaceActivity#getIdPostCondition()
	 */
	@Override
	public String[] getIdPostCondition(){
		return this.idPostCondition;
	}
	
	/** 
	 * Set postCondition
	 * @param postCondition Indica la Rule della postCondition
	 * @see it.moise.plc.uml.interfaces.InterfaceActivity#setAddListPostCondition(moise.plc.uml.Rule)
	 */
	@Override
	public void setAddListPostCondition(Rule postCondition) {
		this.listPostCondition.add(postCondition);

	}

	/** 
	 * Restituisce postCondition
	 * @see it.moise.plc.uml.interfaces.InterfaceActivity#getPostCondition()
	 */
	@Override
	public ArrayList<Rule> getListPostCondition() {
		
		return this.listPostCondition;
	}

	/**
	 * Aggiunge un nodo alla lista delle variabili
	 * @param variable Contiene un oggetto di tipo variable
	 * @see it.moise.plc.uml.interfaces.InterfaceActivity#setAddListVariable(moise.plc.uml.Variable)
	 */
	@Override
	public void setAddListVariable(Variable variable){
		this.listVariable.add(variable);
	}
	
	/**
	 * Restituisce la lista delle variabili
	 * @see it.moise.plc.uml.interfaces.InterfaceActivity#getListVariable()
	 */
	public ArrayList<Variable> getListVariable(){
		return this.listVariable;
	}
	
	/**
	 * Aggiunge un signal
	 * @param signal Contiene un oggetto di tipo Signal
	 * @see it.moise.plc.uml.interfaces.InterfaceActivity#setSignal(moise.plc.uml.Signal)
	 */
	@Override
	public void setSignal(Signal signal){
		this.signal = signal;
	}
	
	/**
	 * Restituisce  signal
	 * @see it.moise.plc.uml.interfaces.InterfaceActivity#getSignal()
	 */
	public Signal getSignal(){
		return this.signal;
	}
	
	/**
	 * Aggiunge un operation
	 * @param operation Contiene un oggetto di tipo Operation
	 * @see it.moise.plc.uml.interfaces.InterfaceActivity#setOperation(moise.plc.uml.Operation)
	 */
	@Override
	public void setAddListOperation(Operation operation){
		this.listOperation.add(operation);
	}
	
	/**
	 * Restituisce  operation
	 * @see it.moise.plc.uml.interfaces.InterfaceActivity#getOperation()
	 */
	@Override
	public ArrayList<Operation> getListOperation(){
		return this.listOperation;
	}
	
	/**
	 * Aggiunge un reception alla lista
	 * @param reception Contiene un oggetto di tipo Reception
	 * @see it.moise.plc.uml.interfaces.InterfaceActivity#setReception(moise.plc.uml.Reception)
	 */
	@Override
	public void setAddListReception(Reception reception){
		this.listReception.add(reception);
	}
	
	/**
	 * Restituisce  la lista listReception
	 * @see it.moise.plc.uml.interfaces.InterfaceActivity#getReception()
	 */
	@Override
	public ArrayList<Reception> getListReception(){
		return this.listReception;
	}
	
	/**
	 * Set specification
	 * @param specification Contiene il valore id della specification a cui è collegata 
	 * @see it.moise.plc.uml.interfaces.InterfaceActivity#setSpecification(java.lang.string)
	 */
	@Override
	public void setSpecification(String specification){
		
		this.specification = specification;
	}
	/**
	 * Restituisce specification
	 * @see it.moise.plc.uml.interfaces.InterfaceActivity#getSpecification()
	 */
	@Override
	public String getSpecification(){
		return this.specification;
	}
	
	/**
	 * Set comment
	 * @param comment  Contiene un oggetto di tipo Comment
	 * @see it.moise.plc.uml.interfaces.InterfaceActivity#setComment(moise.plc.uml.Comment)
	 */
	@Override
	public void setComment(Comment comment){
		
		this.comment = comment;
	}
	/**
	 * Restituisce comment
	 * @see it.moise.plc.uml.interfaces.InterfaceActivity#getComment()
	 */
	@Override
	public Comment getComment(){
		return this.comment;
	}
}
