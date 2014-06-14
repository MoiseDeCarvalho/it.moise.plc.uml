/**
 * 
 */
package it.moise.plc.uml;

import it.moise.plc.uml.interfaces.InterfaceReception;

import java.util.ArrayList;

import org.w3c.dom.NamedNodeMap;

/**
 * Classe che costruisce un oggetto reception
 * @author De Carvalho Moise
 *
 */
public class Reception implements InterfaceReception {
	private String id = null;
	private String name = null;
	private String type = null;
	private String methodId = null;
	private String signalId = null;
	private String visibility = null;
	private boolean isLeaf = false;
	private boolean isStatic = false;
	private boolean isAbstract = false;
	private ArrayList<Parameter> listParameter = new ArrayList<Parameter>();
	/**
	 * Costruttore con parametro
	 * @param node Oggetto di tipo NamedNodeMap da analizzare 
	 */
	public Reception(NamedNodeMap node){
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
					this.setIsLeaf(Boolean.getBoolean(node.item(i).getNodeValue()));
					break;
				case "isStatic":
					this.setIsStatic(Boolean.getBoolean(node.item(i).getNodeValue()));
					break;
				case "method":
					this.setMethod(node.item(i).getNodeValue());
					break;
				case "signal":
					this.setSignalId(node.item(i).getNodeValue());
					break;
				}
			}
		}
	}
	/**
	 * Costruttore senza parametri
	 */
	public Reception() {
	}

	/**
	 * Set l'id
	 * @param id Valore dell'id
	 * @see it.moise.plc.uml.interfaces.InterfaceReception#setId(java.lang.String)
	 */
	@Override
	public void setId(String id) {
		this.id = id;

	}

	/**
	 * Restituisce l'id
	 * @see it.moise.plc.uml.interfaces.InterfaceReception#getId()
	 */
	@Override
	public String getId() {
		// 
		return this.id;
	}

	/**
	 * Set il name
	 * @param name Nome dell'oggetto
	 * @see it.moise.plc.uml.interfaces.InterfaceReception#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;

	}

	/**
	 * Restituisce il name
	 * @see it.moise.plc.uml.interfaces.InterfaceReception#getName()
	 */
	@Override
	public String getName() {
		// 
		return this.name;
	}

	/**
	 * Set il type
	 * @param type Tipo dell'oggetto
	 * @see it.moise.plc.uml.interfaces.InterfaceReception#setType(java.lang.String)
	 */
	@Override
	public void setType(String type) {
		this.type = type;

	}

	/**
	 * Restituisce il type
	 * @see it.moise.plc.uml.interfaces.InterfaceReception#getType()
	 */
	@Override
	public String getType() {
		// 
		return this.type;
	}

	/**
	 * Set visibility
	 * @param visibility Indica la visibilità
	 * @see it.moise.plc.uml.interfaces.InterfaceReception#setVisibility(java.lang.String)
	 */
	@Override
	public void setVisibility(String visibility) {
		this.visibility = visibility;

	}

	/**
	 * Restituisce visibility
	 * @see it.moise.plc.uml.interfaces.InterfaceReception#getVisibility()
	 */
	@Override
	public String getVisibility() {
		// 
		return this.visibility;
	}

	/**
	 * Set isLeaf
	 * @param isLeaf Valore booleano
	 * @see it.moise.plc.uml.interfaces.InterfaceReception#setIsLeaf(boolean)
	 */
	@Override
	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;

	}

	/**
	 * Restituisce isLeaf
	 * @see it.moise.plc.uml.interfaces.InterfaceReception#getIsLeaf()
	 */
	@Override
	public boolean getIsLeaf() {
		// 
		return this.isLeaf;
	}

	/**
	 * Set isStatic
	 * @param isStatic Valore booleano
	 * @see it.moise.plc.uml.interfaces.InterfaceReception#setIsStatic(boolean)
	 */
	@Override
	public void setIsStatic(boolean isStatic) {
		this.isStatic = isStatic;

	}

	/**
	 * Restituisce isStatic
	 * @see it.moise.plc.uml.interfaces.InterfaceReception#getIsStatic()
	 */
	@Override
	public boolean getIsStatic() {
		// 
		return this.isStatic;
	}

	/**
	 * set isAbstract
	 * @param isAbstract Valore booleano
	 * @see it.moise.plc.uml.interfaces.InterfaceReception#setIsAbstract(boolean)
	 */
	@Override
	public void setIsAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;

	}

	/**
	 * Restituisce isAbstract
	 * @see it.moise.plc.uml.interfaces.InterfaceReception#getIsAbstract()
	 */
	@Override
	public boolean getIsAbstract() {
		// 
		return this.isAbstract;
	}

	/**
	 * Set method con idEffect
	 * @param idEffect Id di effect a cui è legato
	 * @see it.moise.plc.uml.interfaces.InterfaceReception#setMethod(java.lang.String)
	 */
	@Override
	public void setMethod(String idEffect) {
		this.methodId = idEffect;

	}

	/**
	 * Restituisce method
	 * @see it.moise.plc.uml.interfaces.InterfaceReception#getMethod()
	 */
	@Override
	public String getMethod() {
		// 
		return this.methodId;
	}

	/**
	 * Set signalId
	 * @param idSignal Id di signal a cui è legato
	 * @see it.moise.plc.uml.interfaces.InterfaceReception#setSignalId(java.lang.String)
	 */
	@Override
	public void setSignalId(String idSignal) {
		this.signalId = idSignal;

	}

	/**
	 * Restituisce il signalId
	 * @see it.moise.plc.uml.interfaces.InterfaceReception#getSignalId()
	 */
	@Override
	public String getSignalId() {
		// 
		return this.signalId;
	}

	
	/**
	 * Set add listParameter
	 * @param parameter Oggetto di tipo parameter
	 * @see it.moise.plc.uml.interfaces.InterfaceReception#setAddListParameter(it.moise.plc.uml.Parameter)
	 */
	@Override
	public void setAddListParameter(Parameter parameter) {
		this.listParameter.add(parameter);

	}
	
	/**
	 * Restituisce la listParameter
	 * @see it.moise.plc.uml.interfaces.InterfaceReception#getListParameter()
	 */
	@Override
	public ArrayList<Parameter> getListParameter() {
		// 
		return this.listParameter;
		}
	
}
