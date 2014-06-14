/**
 * 
 **/
package it.moise.plc.uml;

import it.moise.plc.uml.event.AnyReceiveEvent;
import it.moise.plc.uml.event.CallEvent;
import it.moise.plc.uml.event.ChangeEvent;
import it.moise.plc.uml.event.SignalEvent;
import it.moise.plc.uml.event.TimeEvent;
import it.moise.plc.uml.interfaces.InterfaceModel;

import java.util.ArrayList;

import org.w3c.dom.NamedNodeMap;

/**
 * Classe che contiene il modello analizzato
 * @author De Carvalho Moise
 *
 **/
public class Model implements InterfaceModel {
	private String name = null;
	private String id = null;
	private String version = null;
	private String uml = null;
	private String xmi = null;
	private PackageImport packageImport = new PackageImport();
	private StateMachine stateMachine = new StateMachine();
	private ClassDiagram classDiagram = new ClassDiagram();
	private ArrayList<CallEvent> listCallEvent = new ArrayList<CallEvent>();
	private ArrayList<ChangeEvent> listChangeEvent = new ArrayList<ChangeEvent>();
	private ArrayList<TimeEvent> listTimeEvent = new ArrayList<TimeEvent>();
	private ArrayList<SignalEvent> listSignalEvent = new ArrayList<SignalEvent>();
	private ArrayList<AnyReceiveEvent> listAnyReceiveEvent = new ArrayList<AnyReceiveEvent>();

	/**
	 * Costruttore senza parametri
	 **/
	public Model() {
	}
	
	/**
	 * Set il name del Mode
	 * @param name Nome del modello
	 * @see it.moise.plc.uml.interfaces.InterfaceModel#setName(java.lang.String)
	 **/
	@Override
	public void setName(String name) {
		this.name = name;

	}

	
	/**
	 * Restituisce il name del Model
	 * @see it.moise.plc.uml.interfaces.InterfaceModel#getName()
	 **/
	@Override
	public String getName() {
		// 
		return this.name;
	}
	
	/**
	 * Set l'id del Mode
	 * @param id Id del modello
	 * @see it.moise.plc.uml.interfaces.InterfaceModel#setId(java.lang.String)
	 **/
	@Override
	public void setId(String id) {
		this.id = id;

	}

	
	/**
	 * Restituisce l'id del Model
	 * @see it.moise.plc.uml.interfaces.InterfaceModel#getName()
	 **/
	@Override
	public String getId() {
		// 
		return this.id;
	}
	@Override
	/**
	 * Set la version del Model
	 * @param version Versione del modello
	 * @see it.moise.plc.uml.interfaces.InterfaceModel#setVersion(java.lang.String)
	 **/
	public void setVersion(String version) {
		this.version = version;

	}

	@Override
	/**
	 * Restituisce la version del Model
	 * @see it.moise.plc.uml.interfaces.InterfaceModel#getVersion()
	 **/
	public String getVersion() {
		// 
		return this.version;
	}
	@Override
	/**
	 * Set l'uml del Model
	 * @param uml Stringa UML
	 * @see it.moise.plc.uml.interfaces.InterfaceModel#setUml(java.lang.String)
	 **/
	public void setUml(String uml) {
		this.uml = uml;

	}
	@Override
	/**
	 * Restituisce l'uml del Model
	 * @see it.moise.plc.uml.interfaces.InterfaceModel#getUml()
	 **/
	public String getUml() {
		// 
		return this.uml;
	}
	@Override
	/**
	 * Set l'xmi del Model
	 * @param xmi Url XMI
	 * @see it.moise.plc.uml.interfaces.InterfaceModel#setXmi(java.lang.String)
	 **/
	public void setXmi(String xmi) {
		this.xmi = xmi;

	}
	@Override
	/**
	 * Restituisce xmi del Model
	 * @see it.moise.plc.uml.interfaces.InterfaceModel#getXmi()
	 **/
	public String getXmi() {
		// 
		return this.xmi;
	}
	
	@Override
	/**
	 * Set il packageImport del Model
	 * @param packageImport Contiene il valore del package importated
	 * @see it.moise.plc.uml.interfaces.InterfaceModel#setPackageImport(it.moise.plc.uml.PackageImport)
	 **/
	public void setPackageImport(PackageImport packageImport) {
		this.packageImport = packageImport;

	}
	
	@Override
	/**
	 * Restituisce il packageImport del Model
	 * @see it.moise.plc.uml.interfaces.InterfaceModel#getPackageImport()
	 **/
	public PackageImport getPackageImport() {
		// 
		return this.packageImport;
	}
	
	/**
	 * Set lo stateMachine
	 * @param stateMachine Oggetto statemachine
	 * @see it.moise.plc.uml.interfaces.InterfaceModel#setStateMachine(it.moise.plc.uml.StateMachine)
	 **/
	@Override
	public void setStateMachine(StateMachine stateMachine) {
		this.stateMachine = stateMachine;

	}

	/**
	 * Restituisce lo stateMachine
	 * @see it.moise.plc.uml.interfaces.InterfaceModel#getStateMachine()
	 **/
	@Override
	public StateMachine getStateMachine() {
		// 
		return this.stateMachine;
	}

	/**
	 * Aggiunge un nodo alla lista listCallEvent
	 * @param callEvent Oggetto callEvent
	 * @see it.moise.plc.uml.interfaces.InterfaceModel#setAddListCallEvent(it.moise.plc.uml.event.CallEvent)
	 **/
	@Override
	public void setAddListCallEvent(CallEvent callEvent) {
		this.listCallEvent.add(callEvent);

	}

	/**
	 * Restituisce la lista listCallEvent
	 * @see it.moise.plc.uml.interfaces.InterfaceModel#getListCallEvent()
	 **/
	@Override
	public ArrayList<CallEvent> getListCallEvent() {
		// 
		return this.listCallEvent;
	}

	/**
	 * Stampa la lista listCallEvent
	 * @see it.moise.plc.uml.interfaces.InterfaceModel#printListCallEvent(java.util.ArrayList)
	 **/
	@Override
	public void printListCallEvent(ArrayList<CallEvent> callEvent) {
		// 
		
		/**
		 * 
		 * devo stampare la lista
		 * 
		 */
	}

	/**
	 * Aggiunge un nodo alla lista listChangeEvent
	 * @param changeEvent Oggetto changeEvent
	 * @see it.moise.plc.uml.interfaces.InterfaceModel#setAddListChangeEvent(it.moise.plc.uml.event.ChangeEvent)
	 **/
	@Override
	public void setAddListChangeEvent(ChangeEvent changeEvent) {
		this.listChangeEvent.add(changeEvent);

	}

	/**
	 * Restituisce la lista listChangeEvent
	 * @see it.moise.plc.uml.interfaces.InterfaceModel#getListChangeEvent()
	 **/
	@Override
	public ArrayList<ChangeEvent> getListChangeEvent() {
		// 
		return this.listChangeEvent;
	}

	
	/**
	 * Aggiunge un nodo alla lista listSignalEvent
	 * @param signalEvent Oggeto signalEvent
	 * @see it.moise.plc.uml.interfaces.InterfaceModel#setAddListSignalEvent(it.moise.plc.uml.event.SignalEvent)
	 **/
	@Override
	public void setAddListSignalEvent(SignalEvent signalEvent) {
		this.listSignalEvent.add(signalEvent);

	}

	/**
	 * Restituisce la lista listSignalEvent
	 * @see it.moise.plc.uml.interfaces.InterfaceModel#getListSignalEvent()
	 **/
	@Override
	public ArrayList<SignalEvent> getListSignalEvent() {
		// 
		return this.listSignalEvent;
	}

	

	/**
	 * Aggiunge un nodo alla lista listTimeEvent
	 * @param timeEvent Oggetto timeEvent
	 * @see it.moise.plc.uml.interfaces.InterfaceModel#setAddListTimeEvent(it.moise.plc.uml.event.TimeEvent)
	 **/
	@Override
	public void setAddListTimeEvent(TimeEvent timeEvent) {
		this.listTimeEvent.add(timeEvent);

	}

	/**
	 * Restituisce la lista listTimeEvent
	 * @see it.moise.plc.uml.interfaces.InterfaceModel#getListTimeEvent()
	 **/
	@Override
	public ArrayList<TimeEvent> getListTimeEvent() {
		// 
		return this.listTimeEvent;
	}

	

	/**
	 * Aggiunge un nodo alla lista listAnyTimeEvent
	 * @param anyReceiveEvent oggetto anyReceiveEvent
	 * @see it.moise.plc.uml.interfaces.InterfaceModel#setAddListAnyReceiveEvent(it.moise.plc.uml.event.AnyReceiveEvent)
	 **/
	@Override
	public void setAddListAnyReceiveEvent(AnyReceiveEvent anyReceiveEvent) {
		this.listAnyReceiveEvent.add(anyReceiveEvent);

	}

	/**
	 * Restitituisce la lista listAnyReceiveEvent
	 * @see it.moise.plc.uml.interfaces.InterfaceModel#getListAnyReceiveEvent()
	 **/
	@Override
	public ArrayList<AnyReceiveEvent> getListAnyReceiveEvent() {
		// 
		return this.listAnyReceiveEvent;
	}

	

	/**
	 * Load attribute to Model
	 * @param index indice
	 * @param node Oggetto NamedNodeMap
	 */
	public void loadAttribute(NamedNodeMap node, int index){
		String delimiter ="xmi:";
		String temp[] = null;
		for (int i=0; i < node.getLength(); i++){
			temp = null;
			delimiter ="xmi:";
			temp = node.item(i).getNodeName().split(delimiter);
			if (temp[0].equalsIgnoreCase("")) {
				temp[0] = temp[1];
			}
			if (temp.length == 1){
				delimiter = "xmlns:";
				temp = node.item(i).getNodeName().split(delimiter);
				if (temp[0].equalsIgnoreCase("")) {
					temp[0] = temp[1];
				}
			}
			if (temp.length > 0){
				switch (temp[0]){
				case "name":
					this.setName(node.item(i).getNodeValue());
					break;
				case "id":
					this.setId(node.item(i).getNodeValue());
					break;
				case "version":
					this.setVersion(node.item(i).getNodeValue());
					break;
				case "uml":
					this.setUml(node.item(i).getNodeValue());
					break;
				case "xmi":
					this.setXmi(node.item(i).getNodeValue());
					break;
					
				}
			}
		}
		
	}
	/**
	 * Imposta la classDiagram
	 * @param classDiagram
	 */
	@Override
	public void setClassDiagram(ClassDiagram classDiagram){
		this.classDiagram = classDiagram;
	}
	/**
	 * Restituisce la classDiagram
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public ClassDiagram getClassDiagram(){
		return this.classDiagram;
	}
	
}
