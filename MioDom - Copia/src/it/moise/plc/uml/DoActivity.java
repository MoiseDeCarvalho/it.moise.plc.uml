/**
 * 
 */
package it.moise.plc.uml;

import it.moise.plc.uml.interfaces.InterfaceDoActivity;

import java.util.ArrayList;

import org.w3c.dom.NamedNodeMap;

/**
 * Contiene tutte le informazioni che possono essere inserite in DoActivity se impostato nell'uml
 * @author De Carvalho Moise
 *
 */
public class DoActivity implements InterfaceDoActivity {
	private String id = null;
	private String name = null;
	private String type = null;
	private String visibility = null;
	private boolean isLeaf = false;
	private boolean isActive = false;
	private boolean isAbstract = false;
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
	private boolean isReentrant = false;
	private boolean isReadOnly = false;
	private Comment comment = new Comment();

	/**
	 * Costruttore con parametro
	 * @param node Oggetto di tipo NamedNodeMap, sara' analizzato per estrapolare el informazioni e assegnarle
	 */
	public DoActivity(NamedNodeMap node){
		String delimiter ="xmi:";
		String temp[] = null;
		for (int i=0; i < node.getLength(); i++){
			temp = null;
			delimiter ="xmi:";
			temp = node.item(i).getNodeName().split(delimiter);
			if (temp[0].equalsIgnoreCase("") && temp.length > 1) {
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
					this.setIsReadOnly(Boolean.getBoolean(node.item(i).getNodeValue()));
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
	 * Metodo costrutto senza parametro
	 */
	public DoActivity() {
		
	}

	/**
	 * Set l'id di DoActivity
	 * @param  id indica l'id
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#setId(java.lang.String)
	 **/
	@Override
	public void setId(String id) {
		this.id = id;

	}

	/**
	 * Restituisce l'id di DoActivity
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#getId()
	 **/
	@Override
	public String getId() {
		
		return this.id;
	}

	/**
	 * Set il name di DoActivity
	 * @param name Contiene il nome
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#setName(java.lang.String)
	 **/
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Restituisce il name di DoActivity
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#getName()
	 **/
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * Set il type di DoActivity
	 * @param  type Indica il tipo
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#setType(java.lang.String)
	 **/
	@Override
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Restituisce il type 
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#getType()
	 **/
	@Override
	public String getType() {
		return this.type;
	}

	/**
	 * Set isLeaf
	 * @param  isLeaf Valore booleano
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#setIsLeaf(boolean)
	 **/
	@Override
	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;

	}

	/**
	 * Restituisce il valore di isLeaf
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#getIsLeaf()
	 **/
	@Override
	public boolean getIsLeaf() {
		
		return this.isLeaf;
	}

	/**
	 * Set il valore di isAbstract
	 * @param  isAbstract Valore booleano
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#setIsAbstract(boolean)
	 **/
	@Override
	public void setIsAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;

	}

	/**
	 * Restituisce il valore di isAbstract 
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#getIsAbstract()
	 **/
	@Override
	public boolean getIsAbstract() {
		
		return this.isAbstract;
	}

	
	/**
	 * Set il valore di isActive
	 * @param isActive Valore booleano 
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#setIsActive(boolean)
	 **/
	@Override
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;

	}

	/**
	 * Restituisce il valore di isActive 
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#getIsActive()
	 **/
	@Override
	public boolean getIsActive() {
		
		return this.isActive;
	}
	
	/**
	 * Set visibility di DoActivity
	 * @param visibility Indica la visibilità  
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#setVisibility(java.lang.String)
	 **/
	@Override
	public void setVisibility(String visibility) {
		this.visibility = visibility;

	}

	/**
	 * Restituisce visibility di DoActivity
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#getVisibility()
	 **/
	@Override
	public String getVisibility() {
		
		return this.visibility;
	}
	
	
	/**
	 * Set il valore di isReentrant
	 * @param isReentrant Valore booleano
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#setIsReentrant(boolean)
	 **/
	@Override
	public void setIsReentrant(boolean isReentrant) {
		this.isReentrant  = isReentrant;

	}

	/**
	 * Restituisce il valore di isReentrant
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#getIsReentrant()
	 **/
	@Override
	public boolean getIsReentrant() {
		
		return this.isReentrant;
	}

	/**
	 * Set il valore di isReadOnly
	 * @param isReadOnly Valore booleano
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#setIsReadOnly(boolean)
	 **/
	@Override
	public void setIsReadOnly(boolean isReadOnly) {
		this.isReadOnly  = isReadOnly;

	}

	/**
	 * Restituisce il valore di isReadOnly
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#getIsReadOnly()
	 **/
	@Override
	public boolean getIsReadOnly() {
		
		return this.isReadOnly;
	}

	/**
	 * Set il valore di isSingleExecution
	 * @param isSingleExecution Valore booleano
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#setIsSingleExecution(boolean)
	 **/
	@Override
	public void setIsSingleExecution(boolean isSingleExecution) {
		this.isSingleExecution = isSingleExecution;

	}

	/**
	 * Restituisce il valore di isSingleExecution
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#getIsSingleExecution()
	 **/
	@Override
	public boolean getIsSingleExecution() {
		
		return this.isSingleExecution;
	}

	/**
	 * Set l'id delle preCondition
	 * @param idPreCondition Valore booleano  
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#setIdPreCondition(java.lang.String)
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
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#getIdPreCondition()
	 */
	@Override
	public String[] getIdPreCondition(){
		return this.idPreCondition;
	}

	/** 
	 * Set preCondition
	 * @param preCondition Contiene un oggetto di tipo rule  
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#setAddListPreCondition(it.moise.plc.uml.Rule)
	 */
	@Override
	public void setAddListPreCondition(Rule preCondition) {
		this.listPreCondition.add(preCondition);

	}

	/** 
	 * Restituisce preCondition
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#getListPreCondition()
	 */
	@Override
	public ArrayList<Rule> getListPreCondition() {
		
		return this.listPreCondition;
	}

	/**
	 * Set l'id delle postCondition
	 * @param idPostCondition Indica l'id della postCondition  
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#setIdPostCondition(java.lang.String)
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
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#getIdPostCondition()
	 */
	@Override
	public String[] getIdPostCondition(){
		return this.idPostCondition;
	}
	
	/** 
	 * Set postCondition
	 * @param postCondition Contiene un oggetto di tipo rule  
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#setAddListPostCondition(it.moise.plc.uml.Rule)
	 */
	@Override
	public void setAddListPostCondition(Rule postCondition) {
		this.listPostCondition.add(postCondition);

	}

	/** 
	 * Restituisce postCondition
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#getListPostCondition()
	 */
	@Override
	public ArrayList<Rule> getListPostCondition() {
		
		return this.listPostCondition;
	}
	
	/**
	 * Aggiunge un operation
	 * @param operation   Contiene un oggetto di tipo Operation
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#setAddListOperation(it.moise.plc.uml.Operation)
	 */
	@Override
	public void setAddListOperation(Operation operation){
		this.listOperation.add(operation);
	}
	
	/**
	 * Restituisce  operation
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#getListOperation()
	 */
	@Override
	public ArrayList<Operation> getListOperation(){
		return this.listOperation;
	}
	
	/**
	 * Aggiunge un reception
	 * @param reception Contiene un oggetto di tipo reception  
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#setAddListReception(it.moise.plc.uml.Reception)
	 */
	@Override
	public void setAddListReception(Reception reception){
		this.listReception.add(reception);
	}
	
	/**
	 * Restituisce  reception
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#getListReception()
	 */
	@Override
	public ArrayList<Reception> getListReception(){
		return this.listReception;
	}
	
	/**
	 * Set specification
	 * @param specification Indica l'id della specification 
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#setSpecification(java.lang.String)
	 */
	@Override
	public void setSpecification(String specification){
		
		this.specification = specification;
	}
	/**
	 * Restituisce specification
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#getSpecification()
	 */
	@Override
	public String getSpecification(){
		return this.specification;
	}
	
	 

	 
	/**
	 * Aggiunge un nodo variable alla lista listVariable
	 * @param variable Contiene un oggetto di tipo Variable
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#setAddListVariable(it.moise.plc.uml.Variable)
	 **/
	@Override
	public void setAddListVariable(Variable variable) {
		this.listVariable.add(variable);
	}

	/**
	 * Restituisce la lista listVariable
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#getListVariable()
	 **/
	@Override
	public ArrayList<Variable> getListVariable() {
		
		return this.listVariable;
	}

	/**
	 * Stampa la lista listVariable
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#printListVariable(java.util.ArrayList)
	 **/
	@Override
	public void printListVariable(ArrayList<Variable> listVariable){
		

	}
	
	/**
	 * Aggiunge un signal
	 * @param signal Contiene un oggetto di tipo signal  
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#setSignal(it.moise.plc.uml.Signal)
	 */
	@Override
	public void setSignal(Signal signal){
		this.signal = signal;
	}
	
	/**
	 * Restituisce  signal
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#getSignal()
	 */
	@Override
	public Signal getSignal(){
		return this.signal;
	}
	/**
	 * Restituisce un operation
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#getSizeListOperation()
	 */
	@Override
	public int getSizeListOperation(){
		return this.listOperation.size();
	}
	/**
	 * Restituisce una reception
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#getSizeListReception()
	 */
	@Override
	public int getSizeListReception(){
		return this.listOperation.size();
	}

	/**
	 * Set comment
	 * @param comment Contiene un oggetto di tipo comment
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#setComment(it.moise.plc.uml.Comment)
	 */
	@Override
	public void setComment(Comment comment){
		
		this.comment = comment;
	}
	/**
	 * Restituisce comment
	 * @see it.moise.plc.uml.interfaces.InterfaceDoActivity#getComment()
	 */
	@Override
	public Comment getComment(){
		return this.comment;
	}
}
