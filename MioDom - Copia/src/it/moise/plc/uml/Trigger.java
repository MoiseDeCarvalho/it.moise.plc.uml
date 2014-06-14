/**
 * 
 **/
package it.moise.plc.uml;

import it.moise.plc.uml.event.Event;
import it.moise.plc.uml.interfaces.InterfaceTrigger;

import org.w3c.dom.NamedNodeMap;

/**
 * @author De Carvalho Moïse
 *
 **/
public class Trigger implements InterfaceTrigger {
	private String id = null;
	private String name = null;
	private String type = null;
	private String idEvent = "";
	private Event event =new Event();
	
	/**
	 * Costruttore con parametro
	 * @param NamedNodeMap
	 */
	public Trigger(NamedNodeMap node){
		String delimiter ="xmi:";
		String temp[] = null;
		//System.out.println("\n**********Sezione di caricamento degli attributi *****Trigger*************");
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
				case "event":
					this.setIdEvent(node.item(i).getNodeValue());
					//System.out.println("\t ---- " + this.getIdEvent()+"    ecoo il EVENT");
					break;
				}
			}
		}
		//System.out.println("\n**********Fine caricamento degli attributi *****Trigger*************");
	}
	/**
	 * 
	 **/
	public Trigger() {
	}

	/**
	 * Set l'id del trigger
	 * @param String id
	 * @see it.moise.plc.uml.interfaces.InterfaceTrigger#setId(java.lang.String)
	 **/
	@Override
	public void setId(String id) {
		this.id = id;

	}

	/**
	 * Restituisce l'id del trigger
	 * @see it.moise.plc.uml.interfaces.InterfaceTrigger#getId()
	 **/
	@Override
	public String getId() {
		// 
		return this.id;
	}

	/**
	 * Set il nome del trigger
	 * @param String name
	 * @see it.moise.plc.uml.interfaces.InterfaceTrigger#setName(java.lang.String)
	 **/
	@Override
	public void setName(String name) {
		this.name = name;

	}

	
	/**
	 * Set il type del trigger
	 * @param String 
	 * @see it.moise.plc.uml.interfaces.InterfaceTrigger#setType(java.lang.String)
	 **/
	@Override
	public void setType(String type) {
		this.type = type;

	}
	/**
	 * Restituisce il type del trigger
	 * 
	 * @see it.moise.plc.uml.interfaces.InterfaceTrigger#getType()
	 **/
	@Override
	public String getType() {
		// 
		return this.type;
	}

	

	/**
	 * Restituisce il nome del trigger
	 * 
	 * @see it.moise.plc.uml.interfaces.InterfaceTrigger#getName()
	 **/
	@Override
	public String getName() {
		// 
		return this.name;
	}
	
	/**
	 * Set idEvent del trigger
	 * @param String 
	 * @see it.moise.plc.uml.interfaces.InterfaceTrigger#setIdEvent(java.lang.String)
	 **/
	@Override
	public void setIdEvent(String idEvent) {
		this.idEvent = idEvent;

	}

	/**
	 * Restituisce idEvent del trigger
	 * @see it.moise.plc.uml.interfaces.InterfaceTrigger#getIdEvent()
	 **/
	@Override
	public String getIdEvent() {
		// 
		return this.idEvent;
	}

	/**
	 * Set l'evento del trigger
	 * @param Event event
	 * @see it.moise.plc.uml.interfaces.InterfaceTrigger#setEvent(it.moise.plc.uml.interfaces.event.InterfaceEvent)
	 **/
	@Override
	public void setEvent(Event event) {
		this.event = event;

	}

	/**
	 * Restituisce l'Event del trigger
	 * @see it.moise.plc.uml.interfaces.InterfaceTrigger#getEvent()
	 **/
	@Override
	public Event getEvent() {
		// 
		return this.event;
	}

}
