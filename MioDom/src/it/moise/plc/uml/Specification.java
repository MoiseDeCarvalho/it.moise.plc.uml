/**
 * 
 **/
package it.moise.plc.uml;

import it.moise.plc.uml.interfaces.InterfaceSpecification;

import org.w3c.dom.NamedNodeMap;

/**
 * Classe che costruisce un oggetto di tipo specfication
 * @author De Carvalho Moïse
 *
 **/
public class Specification implements InterfaceSpecification {
	private String id = null;
	private String name = null;
	private String type = null;
	private String value = null;
	private Comment comment = new Comment();
	
	/**
	 * Costruttore con parametro
	 * @param node Oggetto di tipo NamedNodeMap da analizzare 
	 */
	public Specification(NamedNodeMap node){
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
				case "value":
					this.setValue(node.item(i).getNodeValue());
					break;
					
				}
			}
		}
	}
	/**
	 * Costruttore senza parametro
	 **/
	public Specification() {
	}

	/**
	 * Set l'id del Specification
	 * @param id Id dell'oggetto
	 * @see it.moise.plc.uml.interfaces.InterfaceSpecification#setId(java.lang.String)
	 **/
	@Override
	public void setId(String id) {
		this.id = id;

	}

	/**
	 * Restituisce l'id del Specification
	 * @see it.moise.plc.uml.interfaces.InterfaceSpecification#getId()
	 **/
	@Override
	public String getId() {
		// 
		return this.id;
	}

	/**
	 * Set il nome del Specification
	 * @param name Nome dell'oggetto
	 * @see it.moise.plc.uml.interfaces.InterfaceSpecification#setName(java.lang.String)
	 **/
	@Override
	public void setName(String name) {
		this.name = name;

	}

	/**
	 * Restituisce il nome del Specification
	 * @see it.moise.plc.uml.interfaces.InterfaceSpecification#getName()
	 **/
	@Override
	public String getName() {
		// 
		return this.name;
	}

	/**
	 * Set il tipo del Specification
	 * @param type Tipo dell'oggetto
	 * @see it.moise.plc.uml.interfaces.InterfaceSpecification#setType(java.lang.String)
	 **/
	@Override
	public void setType(String type) {
		this.type = type;

	}

	/**
	 * Restituisce il tipo del Specification
	 * @see it.moise.plc.uml.interfaces.InterfaceSpecification#getType()
	 **/
	@Override
	public String getType() {
		// 
		return this.type;
	}
	
	/**
	 * Set il valore del Specification
	 * @param value Valore dell'oggetto
	 * @see it.moise.plc.uml.interfaces.InterfaceSpecification#setValue(java.lang.String)
	 **/
	@Override
	public void setValue(String value) {
		this.value = value;

	}

	/**
	 * Restituisce il valore della specification
	 * @see it.moise.plc.uml.interfaces.InterfaceSpecification#getValue()
	 **/
	@Override
	public String getValue() {
		// 
		return this.value;
	}

	/**
	 * Set il valore del Commento
	 * @param comment Oggetto di tipo comment
	 * @see it.moise.plc.uml.interfaces.InterfaceSpecification#setComment(it.moise.plc.uml.Comment)
	 **/
	@Override
	public void setComment(Comment comment) {
		this.comment = comment;

	}
	
	/**
	 * Restituisce il valore del commento
	 * @see it.moise.plc.uml.interfaces.InterfaceSpecification#getValue()
	 **/
	@Override
	public Comment getComment() {
		// 
		return this.comment;
	}
}
