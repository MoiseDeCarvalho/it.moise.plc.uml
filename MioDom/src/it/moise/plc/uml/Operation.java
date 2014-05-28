/**
 * 
 **/
package it.moise.plc.uml;

import it.moise.plc.uml.interfaces.InterfaceOperation;

import java.util.ArrayList;

import org.w3c.dom.NamedNodeMap;

/**
 * Oggetto Operation contiene un operazione definita nell'uml
 * @author De Carvalho Moise
 *
 **/
public class Operation implements InterfaceOperation {
	private String id = null;
	private String name = null;
	private String type = null;
	private String visibility = null;
	private boolean isLeaf = false;
	private boolean isStatic = false;
	private String cuncurrency = null;
	private boolean isAbstract = false;
	private String  method = null;
	private String idBodyCondition = null;
	private Rule bodyCondition = new Rule();
	private boolean isQuery = false;
	private String[] idPreCondition;
	private ArrayList<Rule> listPreCondition = new ArrayList<Rule>();
	private String[] idPostCondition;
	private ArrayList<Rule> listPostCondition = new ArrayList<Rule>();
	private ArrayList<Parameter> listParameter = new ArrayList<Parameter>();
	private Comment comment = new Comment();
	/**
	 * Costruttore con parametri
	 * @param node Oggetto di tipo NamedNodeMap da analizzare ed assegnare i valori
	 **/
	public Operation(NamedNodeMap node){
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
				case "visibility":
					this.setVisibility(node.item(i).getNodeValue());
					break;	
				case "isLeaf":
					this.setIsLeaf(Boolean.valueOf(node.item(i).getNodeValue()));
					break;
				case "isStatic":
					this.setIsStatic(Boolean.valueOf(node.item(i).getNodeValue()));
					break;
				case "cuncurrency":
					this.setCuncurrency(node.item(i).getNodeValue());
					break;
				case "isAbstract":
					this.setIsAbstract(Boolean.valueOf(node.item(i).getNodeValue()));
					break;
				case "method":
					this.setMethod(node.item(i).getNodeValue());
					break;
				case "bodyCondition":
					this.setIdBodyCondition(node.item(i).getNodeValue());
					break;
				case "isQuery":
					this.setIsQuery(Boolean.valueOf(node.item(i).getNodeValue()));
					break;
				case "precondition":
					this.setIdPreCondition(node.item(i).getNodeValue());
					break;
				case "postcondition":
					this.setIdPostCondition(node.item(i).getNodeValue());
					break;
				}
			}
		}
	}
	/**
	 * Costruttore senz aparametri
	 */
	public Operation() {
	}

	/**
	 * Set l'id dell'Operation
	 * @param id Id delloperazione
	 * @see it.moise.plc.uml.interfaces.InterfaceOperation#setId(java.lang.String)
	 **/
	@Override
	public void setId(String id) {
		this.id = id;

	}

	/**
	 * Restituisce l'id dell'Operation
	 * @see it.moise.plc.uml.interfaces.InterfaceOperation#getId()
	 **/
	@Override
	public String getId() {
		// 
		return this.id;
	}

	/**
	 * Set il nome dell'Operation
	 * @param name Nome dell'operazione
	 * @see it.moise.plc.uml.interfaces.InterfaceOperation#setName(java.lang.String)
	 **/
	@Override
	public void setName(String name) {
		this.name = name;

	}

	/**
	 * Restituisce il nome dell'Operation
	 * @see it.moise.plc.uml.interfaces.InterfaceOperation#getName()
	 **/
	@Override
	public String getName() {
		// 
		return this.name;
	}

	/**
	 * Set il tipo dell'Operation
	 * @param type Tipo dell'operazione
	 * @see it.moise.plc.uml.interfaces.InterfaceOperation#setType(java.lang.String)
	 **/
	@Override
	public void setType(String type) {
		this.type = type;

	}

	/**
	 * Restituisce il tipo dell'Operation
	 * @see it.moise.plc.uml.interfaces.InterfaceOperation#getType()
	 **/
	@Override
	public String getType() {
		// 
		return this.type;
	}

	
	/**
	 * Set visibility dell'Operation
	 * @param visibility Indica la visibilità 
	 * @see it.moise.plc.uml.interfaces.InterfaceOperation#setVisibility(java.lang.String)
	 **/
	@Override
	public void setVisibility(String visibility) {
		this.visibility = visibility;

	}

	/**
	 * Restituisce visibility dell'Operation
	 * @see it.moise.plc.uml.interfaces.InterfaceOperation#getVisibility()
	 **/
	@Override
	public String getVisibility() {
		// 
		return this.visibility;
	}
	
	
	/**
	 * Set isLeaf dell'Operation
	 * @param isLeaf Valore booleano 
	 * @see it.moise.plc.uml.interfaces.InterfaceOperation#setIsLeaf(boolean)
	 **/
	@Override
	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;

	}

	/**
	 * Restituisce isLeaf dell'Operation
	 * @see it.moise.plc.uml.interfaces.InterfaceOperation#getIsLeaf()
	 **/
	@Override
	public boolean getIsLeaf() {
		// 
		return this.isLeaf;
	}
	
	/**
	 * Set isStatic dell'Operation
	 * @param isStatic Valore booleano 
	 * @see it.moise.plc.uml.interfaces.InterfaceOperation#setIsStatic(boolean)
	 **/
	@Override
	public void setIsStatic(boolean isStatic) {
		this.isStatic = isStatic;

	}

	/**
	 * Restituisce isStatic dell'Operation
	 * @see it.moise.plc.uml.interfaces.InterfaceOperation#getIsStatic()
	 **/
	@Override
	public boolean getIsStatic() {
		// 
		return this.isStatic;
	}
	
	
	/**
	 * Set il cuncurrency di Operation
	 * @param cuncurrency indica se concorrente
	 * @see it.moise.plc.uml.interfaces.InterfaceOperation#setCuncurrency(java.lang.String)
	 **/
	@Override
	public void setCuncurrency(String cuncurrency) {
		this.cuncurrency = cuncurrency;

	}

	/**
	 * Restituisce il cuncurrency di Operation
	 * @see it.moise.plc.uml.interfaces.InterfaceOperation#getCuncurrency()
	 **/
	@Override
	public String getCuncurrency() {
		// 
		return this.cuncurrency;
	}
	
	/**
	 * Set isAbstract dell'Operation
	 * @param isAbstract Valore booleano 
	 * @see it.moise.plc.uml.interfaces.InterfaceOperation#setIsAbstract(boolean)
	 **/
	@Override
	public void setIsAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;

	}

	/**
	 * Restituisce isAbstract dell'Operation
	 * @see it.moise.plc.uml.interfaces.InterfaceOperation#getIsAbstract()
	 **/
	@Override
	public boolean getIsAbstract() {
		// 
		return this.isAbstract;
	}
	
	
	/**
	 * Set il method di Operation
	 * @param method Indica il metodo
	 * @see it.moise.plc.uml.interfaces.InterfaceOperation#setMethod(java.lang.String)
	 **/
	@Override
	public void setMethod(String method) {
		this.method = method;

	}

	/**
	 * Restituisce il method di Operation
	 * @see it.moise.plc.uml.interfaces.InterfaceOperation#getMethod()
	 **/
	@Override
	public String getMethod() {
		// 
		return this.method;
	}

	
	/**
	 * Set idBodyCondition di Operation
	 * @param idBodyCondition indica dove applicare l'operazione 
	 * @see it.moise.plc.uml.interfaces.InterfaceOperation#setIdBodyCondition(java.lang.String)
	 **/
	@Override
	public void setIdBodyCondition(String idBodyCondition) {
		this.idBodyCondition = idBodyCondition;

	}

	/**
	 * Restituisce idBodyCondition di Operation
	 * @see it.moise.plc.uml.interfaces.InterfaceOperation#getIdBodyCondition()
	 **/
	@Override
	public String getIdBodyCondition() {
		// 
		return this.idBodyCondition;
	}
	
	/**
	 * Set bodyCondition rule di Operation
	 * @param bodyCondition oggetto di tipo rule 
	 * @see it.moise.plc.uml.interfaces.InterfaceOperation#setBodyCondition(it.moise.plc.uml.Rule)
	 **/
	@Override
	public void setBodyCondition(Rule bodyCondition) {
		this.bodyCondition = bodyCondition;

	}

	/**
	 * Restituisce bodyCondition di Operation
	 * @see it.moise.plc.uml.interfaces.InterfaceOperation#getBodyCondition()
	 **/
	@Override
	public Rule getBodyCondition() {
		// 
		return this.bodyCondition;
	}
	
	/**
	 * Set isQuery dell'Operation
	 * @param isQuery Valore booleano 
	 * @see it.moise.plc.uml.interfaces.InterfaceOperation#setIsQuery(boolean)
	 **/
	@Override
	public void setIsQuery(boolean isQuery) {
		this.isQuery = isQuery;

	}

	/**
	 * Restituisce isQuery dell'Operation
	 * @see it.moise.plc.uml.interfaces.InterfaceOperation#getIsAbstract()
	 **/
	@Override
	public boolean getIsQuery() {
		// 
		return this.isQuery;
	}
	
	/**
	 * Set l'id delle preCondition
	 * @param idPreCondition Id della precondition 
	 * @see it.moise.plc.uml.interfaces.InterfaceOperation#setIdPreCondition(java.lang.String)
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
	 * @see it.moise.plc.uml.interfaces.InterfaceOperation#getIdPreCondition()
	 */
	@Override
	public String[] getIdPreCondition(){
		return this.idPreCondition;
	}
	
	/** 
	 * Set preCondition
	 * @param preCondition oggetto di tipo rule 
	 * @see it.moise.plc.uml.interfaces.InterfaceOperation#setAddListPreCondition(it.moise.plc.uml.Rule)
	 */
	@Override
	public void setAddListPreCondition(Rule preCondition) {
		this.listPreCondition.add(preCondition);

	}

	
	/** 
	 * Restituisce preCondition
	 * @see it.moise.plc.uml.interfaces.InterfaceOperation#getListPreCondition()
	 */
	@Override
	public ArrayList<Rule> getListPreCondition() {
		// 
		return this.listPreCondition;
	}
	
	

	/**
	 * Set l'id delle postCondition
	 * @param idPostCondition Id della postCondition
	 * @see it.moise.plc.uml.interfaces.InterfaceOperation#setIdPostCondition(java.lang.String)
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
	 * @see it.moise.plc.uml.interfaces.InterfaceOperation#getIdPostCondition()
	 */
	@Override
	public String[] getIdPostCondition(){
		return this.idPostCondition;
	}
	
	/** 
	 * Set postCondition
	 * @param  postCondition Oggetto di tipo Rule
	 * @see it.moise.plc.uml.interfaces.InterfaceOperation#setAddListPostCondition(it.moise.plc.uml.Rule)
	 */
	@Override
	public void setAddListPostCondition(Rule postCondition) {
		this.listPostCondition.add(postCondition);

	}

	/** 
	 * Restituisce postCondition
	 * @see it.moise.plc.uml.interfaces.InterfaceOperation#getListPostCondition()
	 */
	@Override
	public ArrayList<Rule> getListPostCondition() {
		// 
		return this.listPostCondition;
	}
	
	
	/**
	 * Set il parameter di Operation
	 * @param parameter Oggetto di tipo parametro
	 * @see it.moise.plc.uml.interfaces.InterfaceOperation#setAddListParameter(it.moise.plc.uml.Parameter)
	 **/
	@Override
	public void setAddListParameter(Parameter parameter) {
		this.listParameter.add(parameter);

	}

	/**
	 * Restituisce il valore di parameter
	 * @see it.moise.plc.uml.interfaces.InterfaceOperation#getListParameter()
	 **/
	@Override
	public ArrayList<Parameter> getListParameter() {
		// 
		return this.listParameter;
	}

	/**
	 * Set il comment di Operation
	 * @param comment Oggetto di tipo comment
	 * @see it.moise.plc.uml.interfaces.InterfaceOperation#setComment(it.moise.plc.uml.Comment)
	 **/
	@Override
	public void setComment(Comment comment) {
		this.comment = comment;

	}

	/**
	 * Restituisce il valore di comment
	 * @see it.moise.plc.uml.interfaces.InterfaceOperation#getComment()
	 **/
	@Override
	public Comment getComment() {
		// 
		return this.comment;
	}
}
