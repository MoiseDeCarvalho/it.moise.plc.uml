package it.moise.plc.uml;

import it.moise.plc.uml.event.Event;
import it.moise.plc.uml.interfaces.InterfaceDeferrableTrigger;

import org.w3c.dom.NamedNodeMap;
/**
 * Contiene informazioni in merito ai Deferrable Trigger se eventualmente inseriti
 * @author De Carvalho Moise
 *
 **/
public class DeferrableTrigger implements InterfaceDeferrableTrigger {
	private String id = null;
	private String name = null;
	private String type = null;
	private String idEvent = null;
	private Event event =new Event();

	/**
	 * Costruttore con parametro
	 * @param node Oggetto di tipo NamedNodeMap, sara' analizzato per estrapolare el informazioni e assegnarle
	 * 
	 */
	public DeferrableTrigger(NamedNodeMap node){
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
				case "event":
					this.setIdEvent(node.item(i).getNodeValue());
					break;
					
				}
			}
		}
	}
	/**
	 * Metodo costrutto senza parametri
	 */
	public DeferrableTrigger() {
	
	}

	/**
	 * Set l'id del trigger
	 * @param id stringa che contiene l'id del trigger
	 * @see it.moise.plc.uml.interfaces.InterfaceDeferrableTrigger#setId(java.lang.String)
	 **/
	@Override
	public void setId(String id) {
		this.id = id;

	}

	/**
	 * Restituisce l'id del trigger
	 * @see it.moise.plc.uml.interfaces.InterfaceDeferrableTrigger#getId()
	 **/
	@Override
	public String getId() {
		
		return this.id;
	}

	/**
	 * Set il nome del trigger
	 * @param name Nome del trigger
	 * @see it.moise.plc.uml.interfaces.InterfaceDeferrableTrigger#setName(java.lang.String)
	 **/
	@Override
	public void setName(String name) {
		this.name = name;

	}

	/**
	 * Restituisce il nome del trigger
	 * 
	 * @see it.moise.plc.uml.interfaces.InterfaceDeferrableTrigger#getName()
	 **/
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * Set il tipo del trigger
	 * @param type Indica il tipo del trigger
	 * @see it.moise.plc.uml.interfaces.InterfaceDeferrableTrigger#setType(java.lang.String)
	 **/
	@Override
	public void setType(String type) {
		this.type = type;

	}

	/**
	 * Restituisce il tipo del trigger
	 * @see it.moise.plc.uml.interfaces.InterfaceDeferrableTrigger#getType()
	 **/
	@Override
	public String getType() {	
		return this.type;
	}

	/**
	 * Set idEvent del trigger
	 * @param idEvent Indica l'id dell'event a cui è associato 
	 * @see it.moise.plc.uml.interfaces.InterfaceDeferrableTrigger#setIdEvent(java.lang.String)
	 **/
	@Override
	public void setIdEvent(String idEvent) {
		this.idEvent = idEvent;

	}

	/**
	 * Restituisce idEvent del trigger
	 * @see it.moise.plc.uml.interfaces.InterfaceDeferrableTrigger#getIdEvent()
	 **/
	@Override
	public String getIdEvent() {
		
		return this.idEvent;
	}
	/**
	 * Set l'evento del trigger
	 * @param event Oggetto di tipo event
	 * @see it.moise.plc.uml.interfaces.InterfaceDeferrableTrigger#setEvent(it.moise.plc.uml.event.Event)
	 **/
	@Override
	public void setEvent(Event event) {
		this.event = event;

	}

	/**
	 * Restituisce l'Event del trigger
	 * @see it.moise.plc.uml.interfaces.InterfaceDeferrableTrigger#getEvent()
	 **/
	@Override
	public Event getEvent() {
		
		return this.event;
	}

}
