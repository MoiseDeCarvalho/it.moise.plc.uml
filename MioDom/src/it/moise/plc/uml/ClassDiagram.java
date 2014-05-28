package it.moise.plc.uml;

import it.moise.plc.uml.attribute.Attribute;
import it.moise.plc.uml.interfaces.InterfaceClassDiagram;
import java.util.Vector;
import org.w3c.dom.NamedNodeMap;


/**
 * Rappresentare una classe con proprieta' e metodi, a cui sara' applicata una state machine
 * che descrivera' il suo comportamento. Una classe in questo caso rappresenta una POU
 * @author De Carvalho Moise
 * @param <T>
 *
 */
public class ClassDiagram<T> implements InterfaceClassDiagram{
	private String id  = "";
	private String name = "";
	private Vector<Attribute<T>> vectorAttribute = new Vector<Attribute<T>>();
	private Vector<Operation> vectorOperation = new Vector<Operation>();
	private Vector<Extension> vectorExtension = new Vector<Extension>();
	/**
	 * Metodo Costruttore con parametro
	 * @param node
	 */
	public ClassDiagram(NamedNodeMap node) {
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
				}
			}
		}
	}

	/**
	 * Costruttore senza parametro
	 */
	public ClassDiagram() {
	}

	
	/**
	 * Set l'id dello State
	 * @param String id
	 * @see it.moise.plc.uml.interfaces.InterfaceClassDiagram#setId(java.lang.String)
	 **/
	@Override
	public void setId(String id) {
		this.id = id;

	}

	/**
	 * Restituisce l'id dello State
	 * @see it.moise.plc.uml.interfaces.InterfaceClassDiagram#getId()
	 **/
	@Override
	public String getId() {
		// 
		return this.id;
	}

	/**
	 * Set il name dello State
	 * @param String name
	 * @see it.moise.plc.uml.interfaces.InterfaceClassDiagram#setName(java.lang.String)
	 **/
	@Override
	public void setName(String name) {
		this.name = name;

	}

	/**
	 * Restituisce il name  dello State
	 * @see it.moise.plc.uml.interfaces.InterfaceClassDiagram#getName()
	 **/
	@Override
	public String getName() {
		// 
		return this.name;
	}
	
	/**
	 * Aggiunge un elemento a vectorAttribute
	 * @param Attribute
	 * @see it.moise.plc.uml.interfaces.InterfaceClassDiagram#setAddVectorAttribute(it.moise.plc.uml.attribute.Attribute)
	 */
	@Override
	public <T>void setAddVectorAttribute(Attribute<T> attribute){
		this.vectorAttribute.add((Attribute) attribute);
	}
	/**
	 * Restituisce il vectorAttribute
	 * @see it.moise.plc.uml.interfaces.InterfaceClassDiagram#getVectorAttribute()
	 */
	@Override
	public Vector<Attribute<T>> getVectorAttribute(){
		return (Vector<Attribute<T>>) this.vectorAttribute;
	}
	
	/**
	 * Aggiunge un elemento a vectorOperation
	 * @param Operation
	 * @see it.moise.plc.uml.interfaces.InterfaceClassDiagram#setAddVectorOperation(it.moise.plc.uml.Operation)
	 */
	@Override
	public void setAddVectorOperation(Operation operation){
		this.vectorOperation.add(operation);
	}
	/**
	 * Restituisce il vectorOperation
	 * @see it.moise.plc.uml.interfaces.InterfaceClassDiagram#getVectorOperation()
	 */
	@Override
	public Vector<Operation> getVectorOperation(){
		return this.vectorOperation;
	}
	
	/**
	 * Aggiunge un elemento a vectorExtension
	 * @param Extension
	 * @see it.moise.plc.uml.interfaces.InterfaceClassDiagram#setAddVectorExtension(it.moise.plc.uml.Extension)
	 */
	@Override
	public void setAddVectorExtension(Extension extension){
		this.vectorExtension.add(extension);
	}
	/**
	 * Restituisce il vectorExtension
	 * @see it.moise.plc.uml.interfaces.InterfaceClassDiagram#getVectorExtension()
	 */
	@Override
	public Vector<Extension> getVectorExtension(){
		return this.vectorExtension;
	}
	
	
}
