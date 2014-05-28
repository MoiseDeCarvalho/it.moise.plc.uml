/**
 * 
 */
package it.moise.plc.uml.event;

import it.moise.plc.uml.interfaces.event.InterfaceChangeEvent;
import it.moise.plc.uml.value.Value;

import org.w3c.dom.NamedNodeMap;

/**
 * @author De Carvalho Moise
 *
 */
public class ChangeEvent extends Event implements InterfaceChangeEvent {
	private String id = "";
	private String name = "";
	private String type = "";
	private String signal = "";
	private String visibility = "";
	private Value changeExpression = new Value();
	
	public ChangeEvent(NamedNodeMap node){
		String delimiter ="xmi:";
		String temp[] = null;
		//System.out.println("\n**********Sezione di caricamento degli attributi *****ChangeEvent*************");
		for (int i=0; i < node.getLength(); i++){
			temp = null;
			delimiter ="xmi:";
			//System.out.println(node.item(i).getNodeName() + " -- " + node.item(i).getNodeValue());
			temp = node.item(i).getNodeName().split(delimiter);
			if (temp[0].equalsIgnoreCase("")) {
				temp[0] = temp[1];
			}
			if (temp.length > 0){
				//temp = node.item(i).getNodeName().split(delimiter);
				//System.out.println("\t ---- " + temp[0]);
				
				switch (temp[0]){
				case "id":
					this.setId(node.item(i).getNodeValue());
					//System.out.println("\t ---- " + this.getId()+"    ecoo il ID");
					break;
				case "name":
					this.setName(node.item(i).getNodeValue());
					//System.out.println("\t ---- " + this.getName()+"    ecoo il NAME");
					break;
				case "type":
					this.setType(node.item(i).getNodeValue());
					//System.out.println("\t ---- " + this.getType()+"    ecoo il TYPE");
					break;
				case "visibility":
					this.setVisibility(node.item(i).getNodeValue());
					//System.out.println("\t ---- " + this.getVisibility()+"    ecoo il VISIBILITY");
					break;
				}
			}
		}
		//System.out.println("\n**********Fine caricamento degli attributi *****ChangeEvent*************");
	}
	/**
	 * 
	 */
	public ChangeEvent() {
	}

	/**
	 * Set l'id dell'evento
	 * @param String id
	 * @see it.moise.plc.uml.interfaces.event.InterfaceChangeEvent#setId(java.lang.String)
	 **/
	@Override
	public void setId(String id) {
		this.id = id;

	}

	/**
	 * Restituisce l'id dell'evento 
	 * @see it.moise.plc.uml.interfaces.event.InterfaceChangeEvent#getId()
	 **/
	@Override
	public String getId() {
		// 
		return this.id;
	}

	/**
	 * Set il nome dell'evento
	 * @param String name
	 * @see it.moise.plc.uml.interfaces.event.InterfaceChangeEvent#setName(java.lang.String)
	 **/
	@Override
	public void setName(String name) {
		this.name = name;

	}

	/**
	 * Restituisce il nome dell evento 
	 * @see it.moise.plc.uml.interfaces.event.InterfaceChangeEvent#getName()
	 **/
	@Override
	public String getName() {
		// 
		return this.name;
	}

	/**
	 * Set il tipo dell'evento
	 * @param String type
	 * @see it.moise.plc.uml.interfaces.event.InterfaceChangeEvent#setType(java.lang.String)
	 **/
	@Override
	public void setType(String type) {
		this.type = type;

	}

	/**
	 * Restituisce il tipo dell'evento
	 * @see it.moise.plc.uml.interfaces.event.InterfaceChangeEvent#getType()
	 **/
	@Override
	public String getType() {
		// 
		return this.type;
	}

	/* (non-Javadoc)
	 * @see Interfaces.InterfaceChangeEvent#setSignal(java.lang.String)
	 */
	@Override
	public void setSignal(String signal) {
		// 

	}

	/* (non-Javadoc)
	 * @see Interfaces.InterfaceChangeEvent#getSignal()
	 */
	@Override
	public String getSignal() {
		// 
		return null;
	}

	/**
	 * Set visibility dell'evento
	 * @param String 
	 * @see it.moise.plc.uml.interfaces.event.InterfaceChangeEvent#setVisibility(java.lang.String)
	 **/
	@Override
	public void setVisibility(String visibility) {
		this.visibility = visibility;

	}

	/**
	 * Restituisce ivisibility dell'evento
	 * @see it.moise.plc.uml.interfaces.event.InterfaceChangeEvent#getType()
	 **/
	@Override
	public String getVisibility() {
		// 
		return this.visibility;
	}
	
	/**
	 * Set changeExpression
	 * @param Value
	 * see Intefaces.event.InterfaceChangeEvent#setChangeExpression(moise.dom.Value)
	 */
	public void setChangeExpression(Value changeExpression){
		this.changeExpression = changeExpression;
	}
	
	/**
	 * Get changeExpression
	 * see Intefaces.event.InterfaceChangeEvent#getChangeExpression()
	 */
	public Value getChangeExpression(){
		return this.changeExpression;
	}
}
