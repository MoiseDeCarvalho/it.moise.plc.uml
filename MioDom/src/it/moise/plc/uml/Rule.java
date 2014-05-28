/**
 * 
 */
package it.moise.plc.uml;

import it.moise.plc.uml.interfaces.InterfaceRule;

import org.w3c.dom.NamedNodeMap;

/**
 * Classse che ralizza un oggetto di rule
 * @author De Carvalho Moïse
 *
 */
public class Rule implements InterfaceRule {
	private String id = null;
	private String name = null;
	private String type = null;
	private String[] constrainedElement;
	private Comment comment = new Comment();
	private Specification specification= new Specification();
	/**
	 * Construttore con parametro
	 * @param node Oggetto di tipo NamedNodeMap da analizzare
	 */
	public Rule(NamedNodeMap node){
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
				case "constrainedElement":
					this.setConstrainedElement(node.item(i).getNodeValue());
					break;
					
				}
			}
		}
	}
	/**
	 * Costruttore senza parametri
	 */
	public Rule() {
	}

	/**
	 * Set l'id del Rule
	 * @param  id Id della rule
	 * @see it.moise.plc.uml.interfaces.InterfaceRule#setId(java.lang.String)
	 **/
	@Override
	public void setId(String id) {
		this.id = id;

	}

	/**
	 * Restituisce l'id del Rule
	 * @see it.moise.plc.uml.interfaces.InterfaceRule#getId()
	 **/
	@Override
	public String getId() {
		// 
		return this.id;
	}

	/**
	 * Set il name del Rule
	 * @param name Nome della rule
	 * @see it.moise.plc.uml.interfaces.InterfaceRule#setName(java.lang.String)
	 **/
	@Override
	public void setName(String name) {
		this.name = name;

	}

	/**
	 * Restituisce in name del Rule
	 * @see it.moise.plc.uml.interfaces.InterfaceRule#getName()
	 **/
	@Override
	public String getName() {
		// 
		return this.name;
	}

	/**
	 * Set il type del Rule
	 * @param type Tipo della rule
	 * @see it.moise.plc.uml.interfaces.InterfaceRule#setType(java.lang.String)
	 **/
	@Override
	public void setType(String type) {
		this.type = type;

	}

	/**
	 * Restituisce il type del Rule
	 * @see it.moise.plc.uml.interfaces.InterfaceRule#getType()
	 **/
	@Override
	public String getType() {
		// 
		return this.type;
	}

	/**
	 * Set il constrainedElement di Rule
	 * @param contrainedElement Elementi vincolati dalla rule
	 * @see it.moise.plc.uml.interfaces.InterfaceRule#setConstrainedElement(java.lang.String)
	 **/
	@Override
	public void setConstrainedElement(String contrainedElement) {
		// attenzione prima di fare l'assegnazione bisogna separare gli elementi passati sono stringhe unite
		
				String listElement[] = contrainedElement.split(" ");
				String  value = null;
				this.constrainedElement = new String[listElement.length];
				for (int i=0; i < listElement.length; i++){
					value = listElement[i];
					this.constrainedElement[i] = value;
				}
	}

	/**
	 * Restituisce il constrainedElement di Rule
	 * @see it.moise.plc.uml.interfaces.InterfaceRule#getConstrainedElement()
	 **/
	@Override
	public String[] getConstrainedElement() {
		// 
		return this.constrainedElement;
	}

	/**
	 * Set il comment di Rule
	 * @param comment Oggetto di tipo comment legato alla rule
	 * @see it.moise.plc.uml.interfaces.InterfaceRule#setComment(it.moise.plc.uml.Comment)
	 **/
	@Override
	public void setComment(Comment comment) {
		this.comment = comment;

	}

	/**
	 * Restituisce il comment di Rule
	 * @see it.moise.plc.uml.interfaces.InterfaceRule#getComment()
	 **/
	@Override
	public Comment getComment() {
		// 
		return this.comment;
	}

	/**
	 * Set la specification di Rule
	 * @param specification Oggetto di tipo specification
	 * @see it.moise.plc.uml.interfaces.InterfaceRule#setSpecification(it.moise.plc.uml.Specification)
	 **/
	@Override
	public void setSpecification(Specification specification) {
		this.specification = specification;

	}

	/**
	 * Resituisce  la specification di Rule
	 * @see it.moise.plc.uml.interfaces.InterfaceRule#getSpecification()
	 **/
	@Override
	public Specification getSpecification() {
		// 
		return this.specification;
	}

}
