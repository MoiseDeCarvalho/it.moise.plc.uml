package it.moise.plc.uml;

import it.moise.plc.uml.attribute.Attribute;
import it.moise.plc.uml.attribute.ExtensionEnd;
import it.moise.plc.uml.attribute.Port;
import it.moise.plc.uml.attribute.Property;
import it.moise.plc.uml.event.AnyReceiveEvent;
import it.moise.plc.uml.event.CallEvent;
import it.moise.plc.uml.event.ChangeEvent;
import it.moise.plc.uml.event.SignalEvent;
import it.moise.plc.uml.event.TimeEvent;
import it.moise.plc.uml.pseudostate.Choice;
import it.moise.plc.uml.pseudostate.DeepHistory;
import it.moise.plc.uml.pseudostate.EntryPoint;
import it.moise.plc.uml.pseudostate.ExitPoint;
import it.moise.plc.uml.pseudostate.Fork;
import it.moise.plc.uml.pseudostate.Initial;
import it.moise.plc.uml.pseudostate.Join;
import it.moise.plc.uml.pseudostate.Junction;
import it.moise.plc.uml.pseudostate.ShallowHistory;
import it.moise.plc.uml.pseudostate.Terminate;
import it.moise.plc.uml.value.LowerValue;
import it.moise.plc.uml.value.UpperValue;
import it.moise.plc.uml.value.Value;

import javax.swing.JTextArea;
import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;
/**
 * Classe che esegue il parsing del file XMI in formato .uml, cerca di trovare tutte le informazioni necessarie per poter elaborarle
 * successivamente
 * @author De Carvalho Moise
 *
 */
public class MioDom {
	private  Model modello = new Model();
	private JTextArea txtArea; 
	private String fileName;
	private String msg="";
	private boolean console = false;		// il valore è true se è stato lanciato da console
	// contiene gli stati per livello, il primo indice indica il livello, il secondo indice il numero dello stato
	private StateLevel[][] arrayStateLevel;
	/*
	 *  contiene tutte le transizioni.
	 *  - tra stato e stato
	 *  - tra initialPoint e stato
	 *  - tra pseudoState e pseudoState
	 *  Quando la transizione inizia da uno initialState, inserisco nel campo NAMESTATESOURCE = DEFAULT
	 *  in questo modo quando devo creare lo sfc, posso sapere quale stato parte di default
	 */
	private Vector<Transitions> vectorTransitions = new Vector<Transitions>();
	private Transitions[][] arrayTransitions;
	// contiene in numero degli stati per livello, verrè utilizzato per dimensioanre arrayStateLevel
	private int[] numberStateForLevel;
	
	private ArrayList<StateLevel> grafoStati = new ArrayList<StateLevel>();
	//private Transitions[] listTransitions;
	private int numberLevels = -1;
	//private	ScriviSuFile scrivi = new ScriviSuFile("ContenutoModello.txt");
	private String idRegionStateMachine = "";
	int z=0;
	
	private ArrayList<NamedNodeMap> listNodeMap = new ArrayList<NamedNodeMap>();
	private ArrayList<String> listStringElement = new ArrayList<String>();
	private ArrayList<Node> listNode = new ArrayList<Node>();
	private GestioneAnalisiModello gestioneModelloObj = new GestioneAnalisiModello(modello, this.globalVar, this.txtArea);
	private GlobalVar globalVar = new Factory().createGlobalVar();
	private int numberStateLevels = 0;
	private String stateManagerActionValue = "";
	private String initActionValue = "";
	
	private String[] tipiVariabili = {"BOOL", "BYTE", "BIT", "WORD", "DWORD", "LWORD", "SINT", "USINT", "INT", "UINT", "LINT", "DINT", 
			"REAL", "LREAL", "STRING", "TIME", "TOD", "DATE", "DT"};
	
	// Questa lista mette in corrispondenza il suo indice con il nome della transizione nel'UML per non perdere l'associazione
		private ArrayList<String> listTRXsWithOriginalName = new ArrayList<String>();
		private ArrayList<String> listTrxOutGrafo = new ArrayList<String>();
		// associa gli indici di vectorTransitions con l'indice di TRXs
		private ArrayList<Trx_In> listTrxIn = new ArrayList<Trx_In>();
		
	/*
	 * Variabili per il file XML
	 */
	private String typePou = "";

	/**
	 * Metodo costruttore con parametri
	 * @param filename	Nome del file da aprire per il parsing
	 * @param txtArea JtextArea da utilizzare per la stampa dei messaggi
	 * @throws InterruptedException Eccezione
	 * @throws ClassNotFoundException Eccezoione
	 * @throws IOException Eccezione
	 */
	public MioDom(String filename, JTextArea txtArea) throws InterruptedException, ClassNotFoundException, IOException {
		this.txtArea = txtArea;
		this.fileName = filename;
		this.setGlobalVar(globalVar);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		int i = 0;
		try {
			DocumentBuilder builder = dbf.newDocumentBuilder();
			File xmlFile = new File(this.fileName);
			Document document = builder.parse(xmlFile);
			try {
				this.printNodeInfo(document);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i++;
			
			
		} catch (SAXException sxe) {
			Exception x = sxe;
			if (sxe.getException() != null)
				x = sxe.getException();
			x.printStackTrace();
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		
		
		try {
			this.analisiDom();
		} catch (ClassNotFoundException | InterruptedException e) {
			e.printStackTrace();
		}
		this.elaboraDom();
		
		// apro la finestra per chiedere che tipo di pou si vuole creare
		
		stampaSystemOutTxtArea("\n-> Analisi terminata con successo!!");
		// eseguo l'analisi del modello stampando i contenuti sui file
		
	}
	/**
	 * Metodo costruttore con parametri da utilizzare se il programma è stata lanciato da console
	 * @param filename Nome del file uml da aprire
	 * @param console Se TRUE il programma è stato lanciato da console
	 * @throws InterruptedException Eccezione
	 * @throws ClassNotFoundException Eccezione
	 * @throws IOException Eccezione
	 */
	public MioDom(String filename, boolean console) throws InterruptedException, ClassNotFoundException, IOException {		
		this.fileName = filename;
		this.console = console;
		this.setGlobalVar(globalVar);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		int i = 0;
		try {
			DocumentBuilder builder = dbf.newDocumentBuilder();
			File xmlFile = new File(this.fileName);
			Document document = builder.parse(xmlFile);
			try {
				this.printNodeInfo(document);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i++;
			
			
		} catch (SAXException sxe) {
			Exception x = sxe;
			if (sxe.getException() != null)
				x = sxe.getException();
			x.printStackTrace();
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		
		
		try {
			this.analisiDom();
		} catch (ClassNotFoundException | InterruptedException e) {
			e.printStackTrace();
		}
		this.elaboraDom();
		
	}
	/**
	 * Chiamata a stampa la lista degli stati
	 * 
	 */
	private void elaboraDom(){
		//ScriviSuFile fileTransitions = new ScriviSuFile("listTransitions.txt");
		GestioneAnalisiModello analisiModello = new GestioneAnalisiModello(this.txtArea, this.console);
		analisiModello.setLivelloSperiore(this.modello.getStateMachine().getListState());
		analisiModello.stampaListaStati(this.modello.getStateMachine().getListState());
		analisiModello.stampaListaRegioni(this.modello.getStateMachine().getListRegion());
		
		
		// controllo se tutti gli stati sono raggiungibili o meno
		analisiModello.controlloStatoRaggiungibile(
				this.modello.getStateMachine().getListState(), 
				this.modello.getStateMachine().getListTransition());
		
		//set il numero max di livelli di  stati
		this.numberLevels = analisiModello.setNumberLevels(this.modello.getStateMachine().getListState());
	
		// dimensiono l'array gloable di Transitions arrayTransitions
		//this.arrayTransitions = new Transitions[this.modello.getStateMachine().getListTransition().size()];
		
		// dimensiono l'array globale numberStateForLevel
		//this.numberStateForLevel = new int[this.numberLevels];
		
		// elenco i subState per ogni state
		analisiModello.addSubStateForEachStateFromRegion(
				this.modello.getStateMachine().getListState(), 
				this.modello.getStateMachine().getListRegion());
		
		
		
		// dimensiono l'array arrayStateLevel
		this.arrayStateLevel = new StateLevel[this.numberLevels][this.modello.getStateMachine().getListState().size()];
		
		
		analisiModello.elencaStateLevel(
				this.arrayStateLevel, 
				this.modello.getStateMachine().getListState(), 
				this.numberLevels, 
				this.numberStateForLevel);
		
		
		// costruico l'arrayTransitions
		analisiModello.elencaAssociaStateToTransitions(
				this.vectorTransitions, 
				this.arrayStateLevel, 
				this.modello.getStateMachine().getListTransition(),
				this.modello.getStateMachine().getListInitial());
		
		analisiModello.writeVectorTransitions(this.vectorTransitions);
		// dimensiono l'arrayTransitions
		this.arrayTransitions = new Transitions[this.vectorTransitions.size()][this.numberLevels];
		
		
		
		// riempio di valori l'arrayTransitions
		analisiModello.createArrayTransitions(
				this.vectorTransitions, 
				this.arrayTransitions, 
				this.arrayStateLevel);
		
		analisiModello.writeArrayTransitions(this.arrayTransitions);
		
		

		this.listTRXsWithOriginalName = analisiModello.getListTRXsWithOriginalName();
		
		// faccio il check sul vectorTransitions, per controllare e settare quale sono le transizioni che 
		// partono da un initialPoint
		checkVectorTransitions();
		
		
		
		checkIdSourceDefaultFromVectorTransitions();
		
		// trovo il primo stato che si avvia del diagramma UML e imposto la variabile statoDiAvvio = TRUE
		searchStatoDiAvvio();
		
		
		
		// verifico ed imposto il valore su CHOICE a true, se ci sono delle transizioni che partono o arrivano in elementi CHOICE
		checkTransitionFromToChoiceElement();
		
		// verifico ed imposto il valore su FORK a true, se ci sono delle transizioni che partono o arrivano in elementi FORK
		checkTransitionFromToForkElement();
				
		// verifico ed imposto il valore su JOIN a true, se ci sono delle transizioni che partono o arrivano in elementi JOIN
		checkTransitionFromToJoinElement();
				
		// stampo su file l'elenco degli stateLevel
		analisiModello.writeListaStateLevel(this.arrayStateLevel);
		
		/*
		 *  verifico se ci sono delle variabili impostate, se si, controllo i loro tipi, associo eventualmente il tipo variabile
		 *  con il tipodefinito dall'utente
		 *  verifico se sono locali o globali
		 *  tipo ="-1" indica che si è usato un tipo definito dall'utente
		 *  visibility = TRUE indica che la variabile è locale 
		 */
		checkVariables();
		
		// verifico che le variabili presenti nei vari stati siano presenti anche nella lista delle variabili globali,
				// in caso non sono presenti insierisco un commento nell'action così può controllare
		this.verificoVariabiliPresenza();
				
				
		
		
		
		
		// creo SFCFake_StateManger.txt
		analisiModello.booleanCombinationStates(this.arrayTransitions.length, this.arrayStateLevel, this.modello.getStateMachine().getListState().size());

		this.setGlobalVar(analisiModello.getGlobalVar());
		this.setNumberStateLevels(this.arrayTransitions[0].length);
		this.setStateManagerActionValue(analisiModello.getGlobalVar().getStateManagerActionValue());
		
		
		

		// inserisco le transizioni di default negli stati in listTransitionOut
		insertTransitionDefaultToListTransitionOut();
		
		
		// inserisco il newNameStateSource e il newNameStateTarget a vectorTransitions
		insertNewNameStateSourceTarget();
		
		/*
		 * CHOICE
		 * modifico le transition che partono  da CHOICE
		 * quelle che hanno come source l'id del choice le faccio risultare cha partono dallo stato di appartenza del CHOICE
		 * e modifico il nome della transition
		 */
		modificoTrxChoice();

		// elimino le trx degli stateLevel in trxOut che hanno come target un CHOICE
		eliminaTrxTargetChoice();
		
		/*
		 * FORK
		 * In TrxOut dello stato che è source nella trx che arriva al fork, aggiungo il numero delle trx che partono dal fork
		 * e modifico i valori del source nell transitions che partono dal fork
		 */
		modificoTrxFork();
		/*
		 * JOIN
		 * cancello le transition che partono  da JOIN
		 * Quelle che arrivano al join, vengono modificate e messo come target lo stato di arrivo della trx che esce dal join
		 */
		modificoTrxJoin();
		
		
		/*
		 * DeepHistory
		 */
		modificoDeepHistory();
		/*
		 * ShallowHistory
		 */
		modificoShallowHistory();
		
		// stampo su file il vectorTransitions
		scriviVectorTransitions();
		
		// associo i commenti agli eventuali choice
		associaCommentiAChoice();
		
		// associo i commenti a Transition
		associaCommentiATransition();
		
		// inserisco i CHOICE trovati ai rispettivi stati di arrayStateLevel
		loadChoiceIntoArrayStateLevel();
		
		// Cerco commenti per le transizioni che partono da uno stato ed arrivano solo ad un altro stato.
		// Ciò significa che c'è un IF condition e quindi lo devo aggiungere nell'Action ST
		//loadCommentFromTRXIntoArrayStateLevel();
		
		// creo lista di adiacenza per poter poi fare il   DFS
		creaListaDiAdiacenza();

		// scorro tutti i stati di cui è composto il grafo, prelevo le trxOut e le aggiungo alla lista
		loadListTrxOutGrafo();
		// creo SFC_Fake.txt
		analisiModello.createSFCFakeFile(this.listTrxOutGrafo.size());
		
		

		// creo SFCFake_StateManger.txt
		analisiModello.booleanCombinationStates(
				this.getListTrxOutGrafo().size(), 
				this.arrayStateLevel, 
				this.modello.getStateMachine().getListState().size());
		this.setStateManagerActionValue(analisiModello.getGlobalVar().getStateManagerActionValue());
		
		// creo SFCFake_Init creo init dopo aver a disposizione tutte le trxOut che il grafo mi fornisce
		analisiModello.createSFCFake_Init_ActionFile(
				this.listTrxOutGrafo, 
				this.arrayStateLevel, 
				this.vectorTransitions, 
				this.modello.getStateMachine().getListRegion());
		this.setInitActionValue(analisiModello.getGlobalVar().getInitActionValue());
		
		//Associo gli indici di vectorTransition e quelli di TRXs per il ladder
		this.listTrxIn = analisiModello.getListTrxIn();
		
		// rimpiazzo in carattere SPAZIO se presente nel nome della transizione con UNSERSCORE
		// modifico il nome della transizione tutto UPPERCASE
		for (Transitions itemTransitions : this.vectorTransitions){
			itemTransitions.setName(itemTransitions.getName().replaceAll(" ", "_"));
			itemTransitions.setName(itemTransitions.getName().toUpperCase());
		}
		for (int i=0; i < 5; i++){
			
		}
	}
	// , ArrayList<String> listStringElement, ArrayList<NamedNodeMap>
	// listNodeMap
	/**
	 * Stampa le info sui nodi, in modo ricorsivo
	 * 
	 * @param currentNode    il nodo corrente
	 * @throws InterruptedException
	 **/
	public void printNodeInfo(Node currentNode) throws InterruptedException {
		int k = 0;

		short sNodeType = currentNode.getNodeType();
		// Se è di tipo Element ricavo le informazioni e le stampo

		if (sNodeType == Node.ELEMENT_NODE) {
			k++;
			String sNodeName = currentNode.getNodeName();
			String sNodeValue = searchTextInElement(currentNode);
			this.listNode.add(currentNode);
			//System.out.println("Elemento: " + currentNode.getNodeName() + " " + currentNode.getNodeValue());
			
		//	this.scrivi.scrivi("Z="+ this.z +" Elemento: " + currentNode.getNodeName() + " " + currentNode.getNodeValue());
			this.z++;
			// preleva gli attributi del nodo corrente
			NamedNodeMap nnmAttributes = currentNode.getAttributes();

			// //System.out.println("Elemento: " + sNodeName);
			this.listStringElement.add(sNodeName);
			this.listNodeMap.add(nnmAttributes);
			
		}

		int iChildNumber = currentNode.getChildNodes().getLength();

		// Se non si tratta di una foglia continua l'esplorazione
		if (currentNode.hasChildNodes()) {

			NodeList nlChilds = currentNode.getChildNodes();
			for (int iChild = 0; iChild < iChildNumber; iChild++) { 
				printNodeInfo(nlChilds.item(iChild));
			}
		}
	}

	/**
	 * Cerca il contenuto per un determinato nodo dato com parametro
	 * @param elementNode Elementto di tipo Node
	 */
	private static String searchTextInElement(Node elementNode) {
		String sText = "";
		if (elementNode.hasChildNodes()) {
			// Il child node di tipo testo è il primo
			Node nTextChild = elementNode.getChildNodes().item(0);
			sText = nTextChild.getNodeValue();
		}
		return sText;
	}

	/**
	 * Restituisce un array di soli valori dato un nodo in input
	 * 
	 * @param nnm Oggetto di tipo NamedNodeMap
	 *            
	 * @return sAttrList Lista degli attributi
	 */
	private static String[] getAttributesValue(NamedNodeMap nnm) {
		String[] sAttrList = new String[5];
		if (nnm != null && nnm.getLength() > 0) {
			for (int iAttr = 0; iAttr < nnm.getLength(); iAttr++) {
				sAttrList[iAttr] = nnm.item(iAttr).getNodeValue();
			}
		}
		return sAttrList;
	}

	/**
	 * Stampa gli attributi di un nodo
	 * @param nnm oggetto di tipo NamedNodeMap
	 * @return sAttrList Se pieno sono gli attributi
	 */
	private static String printAttributes(NamedNodeMap nnm) {
		String sAttrList = new String();
		if (nnm != null && nnm.getLength() > 0) {
			for (int iAttr = 0; iAttr < nnm.getLength(); iAttr++) {
				sAttrList += nnm.item(iAttr).getNodeName();
				sAttrList += "=";
				sAttrList += nnm.item(iAttr).getNodeValue();
				sAttrList += "; ";
			}
			return sAttrList;
		} else {
			return "assenti";
		}
	}

	/**
	 * Esegue l'analisi del modello
	 * @throws ClassNotFoundException Eccezione
	 * @throws InterruptedException Eccezione
	 */
	public void analisiDom() throws ClassNotFoundException, InterruptedException {
		int nRegioni = 0;
		int nTransizioni = 0;
		int nStati = 0;
		Iterator<?> listaElementi = this.listStringElement.iterator();
		// questo oggetto tiene traccia dell'ulti
		int k = 0;
		String parent = null;
		String parentState = null;
		String idParentState = null;
		String parentRegion = null;
		String idParentRegion = null;
		String idStateMachine = null;
		String parentTransition = null;
		
		stampaSystemOutTxtArea("\n->Sono stati trovati " + this.listNode.size() + " nodi");
		
		while (listaElementi.hasNext() && (k < this.listStringElement.size())) {

			String tag = (String) listaElementi.next();
	//		this.scrivi.scrivi(tag + "\t\t\t" + this.listNodeMap.get(k).item(0) );
			switch (tag) {
			case "uml:Model":
				parent = "Model";
				this.modello.loadAttribute(this.listNodeMap.get(k), k);
				break;
			case "packageImport":
				parent = "packageImport";
				PackageImport packageImport = new PackageImport(this.listNodeMap.get(k));
				this.modello.setPackageImport(packageImport);
				break;
			case "importedPackage":
				parent = "importedPackage";
				ImportedPackage importedPackage = new ImportedPackage(this.listNodeMap.get(k));
				this.modello.getPackageImport().setImportedPackage(importedPackage);
				;
				break;
			case "packagedElement":
				String typePackageElement = this.listNodeMap.get(k).item(this.listNodeMap.get(k).getLength() - 1).getNodeValue();
				switch (typePackageElement) {
				case "uml:StateMachine":
					parentState = "StateMachine";
					parentRegion = "";
					StateMachine stateMachine = new StateMachine(this.listNodeMap.get(k));
					this.modello.setStateMachine(stateMachine);
					idStateMachine = this.modello.getStateMachine().getId();
					
					// aggiungo una coppia (id, name) di valori alla mappaState
					this.modello.getStateMachine().setAddMapState(stateMachine.getId(), stateMachine.getName());
					break;
				case "uml:AnyReceiveEvent":
					this.modello.setAddListAnyReceiveEvent(new  AnyReceiveEvent(this.listNodeMap.get(k)));
					break;
				case "uml:CallEvent":
					this.modello.setAddListCallEvent(new  CallEvent(this.listNodeMap.get(k)));
					break;
				case "uml:ChangeEvent":
					ChangeEvent changeEvent =new  ChangeEvent(this.listNodeMap.get(k));
					
					// verifico se contiene un valore da impostare
					if (this.listStringElement.get(k+1).equals("changeExpression")){
						k++;
						listaElementi.next();
						Value value=new Value(this.listNodeMap.get(k));
						changeEvent.setChangeExpression(value);
					}
					this.modello.setAddListChangeEvent(changeEvent);
					break;
				case "uml:SignalEvent":
					this.modello.setAddListSignalEvent(new  SignalEvent(this.listNodeMap.get(k)));
					break;
				case "uml:TimeEvent":
					this.modello.setAddListTimeEvent(new  TimeEvent(this.listNodeMap.get(k)));
					break;		
				case "uml:Class":
					this.modello.setClassDiagram(new ClassDiagram(this.listNodeMap.get(k)));
					System.out.println("aggiunto la classe");
					break;
				case "uml:PrimitiveType":
					System.out.println("trovato word");
					this.modello.getStateMachine().setAddVectorPrimitiveType(new PrimitiveType(this.listNodeMap.get(k)));
					if (console)
						System.out.println("Trovato nuovo tipo di dati");
					else
						txtArea.append("\n->Trovato nuovo tipo di dati");
					break;
				}
			

				break;
			case "ownedOperation":
				/*
				 * verifico che ci siano delle operation in statemachine
				 * 
				 */
				Operation operation = new Operation(this.listNodeMap.get(k));
				
				// verifico che ci siano dei parametri per l'operation
				
				
				
				while (this.listStringElement.get(k + 1).equals("ownedParameter")) {					
					k++;
					listaElementi.next();
					// verifico che la listaParameter di reception è vuota
					
					if (operation.getListParameter().size() == 0) {
						operation.setAddListParameter(new Parameter(this.listNodeMap.get(k)));

						// verifico se il parametro ha un lowerValue
						if (this.listStringElement.get(k + 1).equals("lowerValue")) {
							k++;
							listaElementi.next();
							operation.getListParameter().get(0).setLowerValue(new LowerValue(this.listNodeMap.get(k)));
						}
						// verifico se il parametro ha un upperValue
						if (this.listStringElement.get(k + 1).equals("upperValue")) {
							k++;
							listaElementi.next();
							operation.getListParameter().get(0).setUpperValue(new UpperValue(this.listNodeMap.get(k)));
						}
						// verifico se il parametro ha un defaultValue
						if (this.listStringElement.get(k + 1).equals("defaultValue")) {
							k++;
							listaElementi.next();
							operation.getListParameter().get(0).setDefaultValue(new Value(this.listNodeMap.get(k)));
						}
					}
					// in questo caso la lista dei parametri di OPERATION no è vuota
					else {
						operation.setAddListParameter(new Parameter(this.listNodeMap.get(k)));
						int indice = operation.getListParameter().size() - 1;
						// verifico se il parametro ha un lowerValue
						if (this.listStringElement.get(k + 1).equals("lowerValue")) {
							k++;
							listaElementi.next();
							operation.getListParameter().get(indice).setLowerValue(new LowerValue(this.listNodeMap.get(k)));
						}
						// verifico se il parametro ha un upperValue
						if (this.listStringElement.get(k + 1).equals("upperValue")) {
							k++;
							listaElementi.next();
							operation.getListParameter().get(indice).setUpperValue(new UpperValue(this.listNodeMap.get(k)));
						}
						// verifico se il parametro ha un defaultValue
						if (this.listStringElement.get(k + 1).equals("defaultValue")) {
							k++;
							listaElementi.next();
							operation.getListParameter().get(indice).setDefaultValue(new Value(this.listNodeMap.get(k)));
						}
					}	
					
				}
				

				switch (parentState) {
				case "StateMachine":
					this.modello.getStateMachine().setAddOperation(operation);
					break;

				}
				break;
			case "nestedClassifier":
				// devo definire chi è il parente diretto del nodo attuale ovvero il suo contenitore
				String nomeNodoGenitore = this.listNode.get(k).getParentNode().getNodeName();
				if (nomeNodoGenitore.equals("packagedElement")) {
					if (this.listNode.get(k).getParentNode().getAttributes().item(2).getNodeValue().equals("uml:StateMachine")) {
						// il nodo è uno stato Machine
						parent = "StateMachine";
					}
				} else if (nomeNodoGenitore.equals("doActivity")) {
					// se il node genitore è doAcitivity

				}

				switch (parent) {
				case "StateMachine":
					try {
						NamedNodeMap node = this.listNodeMap.get(k);
						String delimiter ="xmi:";
						String type = "";
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
								case "type":
									type = node.item(i).getNodeValue();
									break;
								}
							}
						}
						switch (type){
						case "uml:PrimitiveType":
							this.modello.getStateMachine().setAddVectorPrimitiveType(new PrimitiveType(this.listNodeMap.get(k)));
							if (console)
								System.out.println("Trovato nuovo tipo di dati");
							else
								txtArea.append("\n->Trovato nuovo tipo di dati");
							break;
						case "uml:Extension":
							this.modello.getClassDiagram().setAddVectorExtension(new Extension(this.listNodeMap.get(k)));
							break;
						default:
							Signal signal = new Signal(this.listNodeMap.get(k));
							Attribute attrib;
							// devo verificare se ci sono degli attributi ed i relativi loro valori
							
							// *************** verifico se c'è un nestedClassifier
							// (signal) ********************
							while (this.listStringElement.get(k + 1).equals("ownedAttribute")) {
								k++;
								listaElementi.next();
								attrib = new Attribute(this.listNodeMap.get(k));
								if (this.listStringElement.get(k + 1).equals("defaultValue")) {
									tag = (String) listaElementi.next();
									k++;
									Value value = new Value(this.listNodeMap.get(k));
									attrib.setDefaulValue(value);
								}
		
								signal.setAddListAttribute(attrib);
							}
							// aggiungo il signal alla listSignal di StateMachine
							this.modello.getStateMachine().setAddSignal(signal);
							break;
						}
						
						
						
						
						
						
					} catch (Exception e) {
					}
					break;
				
				}
				break;
			case "region":
				Region region = new Region(this.listNodeMap.get(k));
				idParentRegion = region.getId();
				parentRegion = region.getId();
				// memorizzo l'id della region della stateMachine, se la lista listregion ==0 vuol dire cha la prima regione che inserisco è
				// quella della stateMachine
				Node nodoDue  = this.listNode.get(k).getParentNode();

				for (int v=0; v < nodoDue.getAttributes().getLength(); v++){
					String[] temp = nodoDue.getAttributes().item(v).getNodeName().split("xmi:");
					if (temp[0].equalsIgnoreCase("") && temp.length > 1) {
						temp[0] = temp[1];
					}
					if (temp.length > 0){
						
						switch (temp[0]){
						case "id":
							idParentState=(nodoDue.getAttributes().item(v).getNodeValue());							
							break;
						}
					}
				}
				
				if (this.modello.getStateMachine().getListRegion().size() == 0){
					this.idRegionStateMachine = region.getId();
					// aggiungo a region l'idStateAppartenenza
					region.setIdStateAppartenenza(idStateMachine);
				}else{
					// aggiungo a region l'idStateAppartenenza
					region.setIdStateAppartenenza(idParentState);
				}
				if (listaElementi.hasNext()){
					while (this.listStringElement.get(k+1).equals("ownedComment")){
						k++;
						listaElementi.next();
						Comment comment = new Comment(this.listNodeMap.get(k)); 
						if (this.listStringElement.get(k+1).equals("body")){
							k++;
							listaElementi .next();
							comment.setBody(searchTextInElement(this.listNode.get(k)));
							comment.setIdRegionAppartenenza(region.getId());
							comment.setIdStateAppartenenza(region.getIdStateAppartenenza());
						}
						region.setAddListComment(comment);
					}
				}
				
				
				// aggiungo a region l'idStateAppartenenza
				//region.setIdStateAppartenenza(idParentState);

				// aggiungo un nodo alla lista listIdRegion di STATE se l'id
				// dello stato genitore della region in questione
				// è differente da idStateMachine
				if ((idParentState != null)) {
					nStati = this.modello.getStateMachine().getListState().size();
					// cerco lo stato di appartenenza ed inserisco l'id della
					for (int i = 0; i < nStati; i++) {
						if (this.modello.getStateMachine().getListState().get(i).getId().equals(idParentState)) {
							this.modello.getStateMachine().getListState().get(i).setAddListIdRegion(idParentRegion);
							this.modello.getStateMachine().setAddRegion(region);
						}
					}
				} else {
					// aggiungo un nodo alla lista listRegion dello STATE MACHINE
					this.modello.getStateMachine().setAddRegion(region);
				}
				if (this.modello.getStateMachine().getListRegion().size() == 0){
					this.modello.getStateMachine().setAddRegion(region);
				}
				// aggiungo una coppia di valori (id,name) alla mapRegion
				this.modello.getStateMachine().setAddMapRegion(region.getId(), region.getName());
				// memorizzo l'id dell'ultima region visitata

				break;
			case "subvertex":
				nRegioni = this.modello.getStateMachine().getListRegion().size();
				// Aggiungo in vertex alla regione di competenza
				{
					NamedNodeMap node = this.listNodeMap.get(k);
					String temp[] = null;
					String typeNode = null;
					String idNode = null;
					String kindNode = null;
					for (int i = 0; i < node.getLength(); i++) {
						temp = node.item(i).getNodeName().split("xmi:");
						if (temp[0].equalsIgnoreCase("") && temp.length > 1) {
							temp[0] = temp[1];
						}
						if (temp.length > 0) {
							switch (temp[0]) {
							case "type":
								typeNode = (node.item(i).getNodeValue());
								break;
							case "id":
								idNode = (node.item(i).getNodeValue());
								break;
							case "kind":
								kindNode = (node.item(i).getNodeValue());
								break;
							}
						}
					}
					// ************* switch Pseudostate o state di subvertex
					switch (typeNode) {
					case "uml:Pseudostate":
						// annullo il valore di kindNode, in questo modo so che se è uno pseudostate e kindNode = null
						// allora so che ho uno pseudostate di tipo initial e lo aggiungo alla regione di competenza
						//*** devo ricercare il parent Region di livello piè alto e non quello piè nidificato
						String regioneDiAppartenza = this.listNode.get(k).getParentNode().getAttributes().item(1).getNodeValue();
						if (kindNode == null) {
							{
								stampaSystemOutTxtArea("\n->uml:Pseudostate - id-region : " + idParentRegion + " regione di appartenenza: " + regioneDiAppartenza);
								
								for (int i = 0; i < nRegioni; i++) {
									if (this.modello.getStateMachine().getListRegion().get(i).getId().equals(regioneDiAppartenza)) {
										// verifico se l'id della regione di appartenza corrisponde all'id della region della State Machine
										if (this.idRegionStateMachine.equals(regioneDiAppartenza)){
											this.modello.getStateMachine().getListRegion().get(i).setInitialPseudoState(new Initial(this.listNodeMap.get(k), true));
											this.modello.getStateMachine().setAddInitial(new Initial(this.listNodeMap.get(k), true));
										}else{
											this.modello.getStateMachine().getListRegion().get(i).setInitialPseudoState(new Initial(this.listNodeMap.get(k)));
											this.modello.getStateMachine().setAddInitial(new Initial(this.listNodeMap.get(k)));
										}
										
									}
								}
							}
						} else {
							// in questo caso devo fare un altro switch per verificare il tipo di pseudostate che ho da aggiungere
							
							
							switch (kindNode) {
							case "deepHistory":
								for (int i = 0; i < nRegioni; i++) {
									if (this.modello.getStateMachine().getListRegion().get(i).getId().equals(regioneDiAppartenza)) {
										this.modello.getStateMachine().getListRegion().get(i).setDeepHistory(new DeepHistory(this.listNodeMap.get(k)));
										this.modello.getStateMachine().getVectorDeepHistory().add(new DeepHistory(this.listNodeMap.get(k)));
									}
								}
								break;
							case "shallowHistory":
								for (int i = 0; i < nRegioni; i++) {
									if (this.modello.getStateMachine().getListRegion().get(i).getId().equals(regioneDiAppartenza)) {
										this.modello.getStateMachine().getListRegion().get(i).setShallowHistory(new ShallowHistory(this.listNodeMap.get(k)));
										this.modello.getStateMachine().getVectorShallowHistory().add(new ShallowHistory(this.listNodeMap.get(k)));
									}
								}

								break;
							case "join":
								for (int i = 0; i < nRegioni; i++) {
									if (this.modello.getStateMachine().getListRegion().get(i).getId().equals(regioneDiAppartenza)) {
										this.modello.getStateMachine().getListRegion().get(i).setAddListJoin(new Join(this.listNodeMap.get(k)));
										this.modello.getStateMachine().getVectorJoin().add(new Join(this.listNodeMap.get(k)));
									}
								}
								break;
							case "fork":
								for (int i = 0; i < nRegioni; i++) {
									if (this.modello.getStateMachine().getListRegion().get(i).getId().equals(regioneDiAppartenza)) {
										this.modello.getStateMachine().getListRegion().get(i).setAddListFork(new Fork(this.listNodeMap.get(k)));
										this.modello.getStateMachine().getVectorFork().add(new Fork(this.listNodeMap.get(k)));
									}
								}
								break;
							case "junction":
								for (int i = 0; i < nRegioni; i++) {
									if (this.modello.getStateMachine().getListRegion().get(i).getId().equals(regioneDiAppartenza)) {
										this.modello.getStateMachine().getListRegion().get(i).setAddListJunction(new Junction(this.listNodeMap.get(k)));
										this.modello.getStateMachine().getVectorJunction().add(new Junction(this.listNodeMap.get(k)));
									}
								}
								break;
							case "choice":
								Choice choice;
								for (int i = 0; i < nRegioni; i++) {
									if (this.modello.getStateMachine().getListRegion().get(i).getId().equals(regioneDiAppartenza)) {
										choice = new Choice(this.listNodeMap.get(k));
										choice.setIdRegionAppartenenza(regioneDiAppartenza);
										this.modello.getStateMachine().getListRegion().get(i).setAddListChoice(choice);
										this.modello.getStateMachine().getVectorChoice().add(new Choice(this.listNodeMap.get(k)));
									}
								}
								break;
							case "terminate":
								for (int i = 0; i < nRegioni; i++) {
									if (this.modello.getStateMachine().getListRegion().get(i).getId().equals(regioneDiAppartenza)) {
										this.modello.getStateMachine().getListRegion().get(i).setTerminate(new Terminate(this.listNodeMap.get(k)));
										this.modello.getStateMachine().getVectorTerminate().add(new Terminate(this.listNodeMap.get(k)));
									}
								}
								break;

							}

						}
						kindNode = null;
						break;
					case "uml:State":
						idParentState = idNode;

						// Aggiungo lo stato alla lista degli stati di stateMachine
						this.modello.getStateMachine().getListState().add(new State(this.listNodeMap.get(k)));

						
						// Aggiungo idRegionAppartenenza allo stato appena trovato
						nStati = this.modello.getStateMachine().getListState().size();
						//*** devo ricercare il parent Region di livello piè alto e non quello piè nidificato
						regioneDiAppartenza = this.listNode.get(k).getParentNode().getAttributes().item(1).getNodeValue();
						this.modello.getStateMachine().getListState().get(nStati - 1).setIdRegionAppartenenza(regioneDiAppartenza);

						// Aggiungo l'id dello stato alla lista degli stati della regione di appartenenza
						nRegioni = this.modello.getStateMachine().getListRegion().size();
						// trovo l'id dell'ultimo stato inserito
						String idStato = this.modello.getStateMachine().getListState().get(nStati-1).getId();
						for (int i=0; i < this.modello.getStateMachine().getListRegion().size(); i++){
							if (this.modello.getStateMachine().getListRegion().get(i).getId().equals(regioneDiAppartenza)){
								this.modello.getStateMachine().getListRegion().get(i).setAddListIdState(idStato);
							}
						}
						// Trovo l'id della regione di livello superiore per settate lo stato di livello superiore dello stato in essere ora
						{
							String idRegionUp = this.listNode.get(k).getParentNode().getAttributes().item(1).getNodeValue();
							String testo = "\n id region di livello superiore che contiene lo stato ora : " + idRegionUp + " Stato = " + idStato;
				//			scrivi.scrivi(testo);
							
							// Adesso ricerco id dello stato di livello superiore
							String idStateUp = this.getIdStateLevelUp(idRegionUp);
							if (idStateUp.isEmpty())
								idStateUp = "-1";
							this.modello.getStateMachine().getListState().get(nStati-1).setIdStateLevelUp(idStateUp);
						}
						break;
					case "FinalState":

						break;
					}

					// FINE DELLO Switch Pseudostate o state di subvertex
				}

				break;
			
			/*
			 * 
			 * 
			 * carico doActivity
			 */
			case "doActivity": {
				DoActivity doActivity = new DoActivity(this.listNodeMap.get(k));
				
				while (this.listStringElement.get(k + 1).equals("variable")) {
					
					
					// poichè il tipo delle variabili può anche essere definito dall'utente o utilizzare i tipi primitivi dei defaul di papyrus, verifico 
					// se utilizza href come impostazione del tipo
					if (this.listStringElement.get(k+2).equals("type")){
						doActivity.setAddListVariable(new Variable(this.listNodeMap.get(k+1), this.listNodeMap.get(k+2)));
						k++;
						listaElementi.next();
						k++;
						listaElementi.next();
					}else{
						doActivity.setAddListVariable(new Variable(this.listNodeMap.get(k+1)));
						k++;
						listaElementi.next();
					}
				}
				
				// verifico se c'è un commento
				if (this.listStringElement.get(k + 1).equals("ownedComment")) {
					k++;
					listaElementi.next();
					doActivity.setComment(new Comment(this.listNodeMap.get(k)));
					// prelevo il valore del commento nel tag body e lo aggiungo al commento
					if (this.listStringElement.get(k + 1).equals("body")) {
						k++;
						listaElementi.next();
						doActivity.getComment().setBody(searchTextInElement(this.listNode.get(k)));
					}
				}
				// ******** verifico se ci sono delle Rule per PreCondition e/o
				// PostCondition di doActivity ********************

				int cont = 0;
				ArrayList<Rule> ruleArray = new ArrayList<Rule>();
				while (this.listStringElement.get(k + 1).equals("ownedRule")) {
					k++;
					listaElementi.next();
					ruleArray.add(new Rule(this.listNodeMap.get(k)));
					// verifico se c'è specification
					if (this.listStringElement.get(k + 1).equals("specification")) {
						k++;
						listaElementi.next();
						ruleArray.get(cont).setSpecification(new Specification(this.listNodeMap.get(k)));

						// verifico se c'è un commento
						if (this.listStringElement.get(k + 1).equals("ownedComment")) {
							k++;
							listaElementi.next();
							ruleArray.get(cont).getSpecification().setComment(new Comment(this.listNodeMap.get(k)));
							// prelevo il valore del commento nel tag body e lo
							// aggiungo al commento
							if (this.listStringElement.get(k + 1).equals("body")) {
								k++;
								listaElementi.next();
								ruleArray.get(cont).getSpecification().getComment().setBody(searchTextInElement(this.listNode.get(k)));
							}
						}
					} else if (this.listStringElement.get(k + 1).equals("ownedComment")) {
						k++;
						listaElementi.next();
						ruleArray.get(cont).setComment(new Comment(this.listNodeMap.get(k)));
						// prelevo il valore del commento nel tag body e lo aggiungo al commento
						if (this.listStringElement.get(k + 1).equals("body")) {
							k++;
							listaElementi.next();
							ruleArray.get(cont).getComment().setBody(searchTextInElement(this.listNode.get(k)));
						}

						if (this.listStringElement.get(k + 1).equals("specification")) {
							k++;
							listaElementi.next();
							ruleArray.get(cont).setSpecification(new Specification(this.listNodeMap.get(k)));

							// verifico se c'è un commento
							if (this.listStringElement.get(k + 1).equals("ownedComment")) {
								k++;
								listaElementi.next();
								ruleArray.get(cont).getSpecification().setComment(new Comment(this.listNodeMap.get(k)));
								// prelevo il valore del commento nel tag body e lo aggiungo al commento
								if (this.listStringElement.get(k + 1).equals("body")) {
									k++;
									listaElementi.next();
									ruleArray.get(cont).getSpecification().getComment().setBody(searchTextInElement(this.listNode.get(k)));
								}
							}

						}
					}

					cont++;

				}

				// ************** fine caricamento rule per - doActivity *********************

				// verifico se ci sono gli Id per il preCondition in activity
				if (doActivity.getIdPreCondition() != null && doActivity.getIdPreCondition().length > 0) {
					for (int i = 0; i < doActivity.getIdPreCondition().length; i++) {
						for (int j = 0; j < ruleArray.size(); j++) {
							if (doActivity.getIdPreCondition()[i].equals(ruleArray.get(j).getId())) {
								doActivity.setAddListPreCondition(ruleArray.get(j));
							}
						}
					}
				}
				// verifico se ci sono gli Id per il postCondition in activity
				if (doActivity.getIdPostCondition() != null && doActivity.getIdPostCondition().length > 0) {
					for (int i = 0; i < doActivity.getIdPostCondition().length; i++) {
						for (int j = 0; j < ruleArray.size(); j++) {
							if (doActivity.getIdPostCondition()[i].equals(ruleArray.get(j).getId())) {
								doActivity.setAddListPostCondition(ruleArray.get(j));
							}
						}
					}
				}
				
				
				ruleArray.clear();
				// ********** termine sezione del caricamento delle RULE per doActivity ****************************

				// ********* verifico se c'è un nestedClassifier (signal) ************
				if (this.listStringElement.get(k + 1).equals("nestedClassifier")) {
					k++;
					listaElementi.next();
					doActivity.setSignal(new Signal(this.listNodeMap.get(k)));

					// *************** verifico se c'è un nestedClassifier (signal) ********************
					while (this.listStringElement.get(k + 1).equals("ownedAttribute")) {
						k++;
						listaElementi.next();
						Attribute attrib = new Attribute(this.listNodeMap.get(k));
						if (this.listStringElement.get(k + 1).equals("defaultValue")) {
							tag = (String) listaElementi.next();
							k++;
							Value value = new Value(this.listNodeMap.get(k));
							attrib.setDefaulValue(value);
						}

						// Verifico che tipo di attributo è: Port - Property - ExtensionEnd
						switch (attrib.getType()) {
						case "uml:Port":
							Port port = new Port(attrib, this.listNodeMap.get(k));
							break;
						case "uml:Property":
							Property property = new Property(attrib, this.listNodeMap.get(k));
							break;
						case "uml:ExtensionEnd":
							ExtensionEnd extensionEnd = new ExtensionEnd(attrib, this.listNodeMap.get(k));
							break;

						}

						switch (attrib.getType()) {
						case "uml:Port":
							Port port = new Port(attrib, this.listNodeMap.get(k));
							doActivity.getSignal().setAddListAttribute(port);
							break;
						case "uml:Property":
							Property property = new Property(attrib, this.listNodeMap.get(k));
							doActivity.getSignal().setAddListAttribute(property);
							break;
						case "uml:ExtensionEnd":
							ExtensionEnd extensionEnd = new ExtensionEnd(attrib, this.listNodeMap.get(k));
							doActivity.getSignal().setAddListAttribute(extensionEnd);
							break;
						case "":
							doActivity.getSignal().setAddListAttribute(attrib);
							break;
						}
					}
				}
				// ******** FINE DELLA SECTION nestedClassifier (signal) x activity

				
				// ****************** verifico se c'è un un operation*************************************
				while (this.listStringElement.get(k + 1).equals("ownedOperation")) {
					k++;
					listaElementi.next();
					doActivity.setAddListOperation(new Operation(this.listNodeMap.get(k)));

					// verifico se c'è un commento
					if (this.listStringElement.get(k + 1).equals("ownedComment")) {
						k++;
						listaElementi.next();
						Comment comment = new Comment(this.listNodeMap.get(k));
						// prelevo il valore del commento nel tag body e lo aggiungo al commento
						if (this.listStringElement.get(k + 1).equals("body")) {
							k++;
							listaElementi.next();
							comment.setBody(searchTextInElement(this.listNode.get(k)));
						}
						// verifico che il commento che ho prelevato dal nodo sia il commento dell'operation di activity
						for (int i = 0; i < comment.getAnnotatedElement().length; i++) {
							for (int j=0; j < doActivity.getListOperation().size(); j++){
								if (comment.getAnnotatedElement()[i].equals(doActivity.getListOperation().get(j).getId())) {
									doActivity.getListOperation().get(doActivity.getListOperation().size()-1).setComment(comment);
								}
							}
						}

					}

					// ****** Verifico e aggiungo i rule per i preCondition - postCondition - bodyCondition **********
					ruleArray.clear();
					cont=0;
					while (this.listStringElement.get(k + 1).equals("ownedRule")) {
						k++;
						listaElementi.next();
						ruleArray.add(new Rule(this.listNodeMap.get(k)));
						// verifico se c'è specification
						if (this.listStringElement.get(k + 1).equals("specification")) {
							k++;
							listaElementi.next();
							ruleArray.get(cont).setSpecification(new Specification(this.listNodeMap.get(k)));

							// verifico se c'è un commento
							if (this.listStringElement.get(k + 1).equals("ownedComment")) {
								k++;
								listaElementi.next();
								ruleArray.get(cont).getSpecification().setComment(new Comment(this.listNodeMap.get(k)));
								// prelevo il valore del commento nel tag body e lo aggiungo al commento
								if (this.listStringElement.get(k + 1).equals("body")) {
									k++;
									listaElementi.next();
									ruleArray.get(cont).getSpecification().getComment().setBody(searchTextInElement(this.listNode.get(k)));
								}
							}
						} else if (this.listStringElement.get(k + 1).equals("ownedComment")) {
							k++;
							listaElementi.next();
							ruleArray.get(cont).setComment(new Comment(this.listNodeMap.get(k)));
							// prelevo il valore del commento nel tag body e lo aggiungo al commento
							if (this.listStringElement.get(k + 1).equals("body")) {
								k++;
								listaElementi.next();
								ruleArray.get(cont).getComment().setBody(searchTextInElement(this.listNode.get(k)));
							}

							if (this.listStringElement.get(k + 1).equals("specification")) {
								k++;
								listaElementi.next();
								ruleArray.get(cont).setSpecification(new Specification(this.listNodeMap.get(k)));

								// verifico se c'è un commento
								if (this.listStringElement.get(k + 1).equals("ownedComment")) {
									k++;
									listaElementi.next();
									ruleArray.get(cont).getSpecification().setComment(new Comment(this.listNodeMap.get(k)));
									// prelevo il valore del commento nel tag body e lo aggiungo al commento
									if (this.listStringElement.get(k + 1).equals("body")) {
										k++;
										listaElementi.next();
										ruleArray.get(cont).getSpecification().getComment().setBody(searchTextInElement(this.listNode.get(k)));
									}
								}

							}
						}

						cont++;

					}
					// ****** FINE caricamento dei rule per i preCondition  postCondition - bodyCondition **********

					// verifico se ci sono gli Id per il preCondition di operation in doActivity
					for (int l=0; l < doActivity.getListOperation().size(); l++){
						if (doActivity.getListOperation().get(l).getIdPreCondition() != null && doActivity.getListOperation().get(l).getIdPreCondition().length > 0) {
							for (int i = 0; i < doActivity.getListOperation().get(l).getIdPreCondition().length; i++) {
								for (int j = 0; j < ruleArray.size(); j++) {
									if (doActivity.getListOperation().get(l).getIdPreCondition()[i].equals(ruleArray.get(j).getId())) {
										doActivity.getListOperation().get(l).setAddListPreCondition(ruleArray.get(j));
									}
								}
							}
						}
					}

					// verifico se ci sono gli Id per il postCondition di operation in doActivity
					for (int l=0; l < doActivity.getListOperation().size(); l++){
						if (doActivity.getListOperation().get(l).getIdPostCondition() != null && doActivity.getListOperation().get(l).getIdPostCondition().length > 0) {
							for (int i = 0; i < doActivity.getListOperation().get(l).getIdPostCondition().length; i++) {
								for (int j = 0; j < ruleArray.size(); j++) {
									if (doActivity.getListOperation().get(l).getIdPostCondition()[i].equals(ruleArray.get(j).getId())) {
										doActivity.getListOperation().get(l).setAddListPostCondition(ruleArray.get(j));
									}
								}
							}
						}
					}
					// verifico se ci sono gli Id per il bodyCondition di operation in doActivity
					for (int l=0; l < doActivity.getListOperation().size(); l++){
						if (doActivity.getListOperation().get(l).getIdBodyCondition() != null) {
							for (int j = 0; j < ruleArray.size(); j++) {
								if (doActivity.getListOperation().get(l).getIdBodyCondition().equals(ruleArray.get(j).getId())) {
									doActivity.getListOperation().get(l).setBodyCondition(ruleArray.get(j));
								}
							}
	
						}
					}
					// / ******** ho finito di caricare i rule *****************

					// ******** adesso carico tutti i parametri per operation di
					// doActivity con i loro vari valori presenti *******

					while (this.listStringElement.get(k + 1).equals("ownedParameter")) {
						k++;
						listaElementi.next();
						// verifico che la listaParameter di reception è vuota
						for (int i = 0; i < doActivity.getListOperation().size(); i++){
							if (doActivity.getListOperation().get(i).getListParameter().size() == 0) {
								doActivity.getListOperation().get(i).setAddListParameter(new Parameter(this.listNodeMap.get(k)));
	
								// verifico se il parametro ha un lowerValue
								if (this.listStringElement.get(k + 1).equals("lowerValue")) {
									k++;
									listaElementi.next();
									doActivity.getListOperation().get(i).getListParameter().get(0).setLowerValue(new LowerValue(this.listNodeMap.get(k)));
								}
								// verifico se il parametro ha un upperValue
								if (this.listStringElement.get(k + 1).equals("upperValue")) {
									k++;
									listaElementi.next();
									doActivity.getListOperation().get(i).getListParameter().get(0).setUpperValue(new UpperValue(this.listNodeMap.get(k)));
								}
								// verifico se il parametro ha un defaultValue
								if (this.listStringElement.get(k + 1).equals("defaultValue")) {
									k++;
									listaElementi.next();
									doActivity.getListOperation().get(i).getListParameter().get(0).setDefaultValue(new Value(this.listNodeMap.get(k)));
								}
							}
							// in questo caso la lista dei parametri di OPERATION non è vuota
							else {
								doActivity.getListOperation().get(i).setAddListParameter(new Parameter(this.listNodeMap.get(k)));
								int indice = doActivity.getListOperation().get(i).getListParameter().size() - 1;
								// verifico se il parametro ha un lowerValue
								if (this.listStringElement.get(k + 1).equals("lowerValue")) {
									k++;
									listaElementi.next();
	
									doActivity.getListOperation().get(i).getListParameter().get(indice).setLowerValue(new LowerValue(this.listNodeMap.get(k)));
								}
								// verifico se il parametro ha un upperValue
								if (this.listStringElement.get(k + 1).equals("upperValue")) {
									k++;
									listaElementi.next();
									doActivity.getListOperation().get(i).getListParameter().get(indice).setUpperValue(new UpperValue(this.listNodeMap.get(k)));
								}
								// verifico se il parametro ha un defaultValue
								if (this.listStringElement.get(k + 1).equals("defaultValue")) {
									k++;
									listaElementi.next();
									doActivity.getListOperation().get(i).getListParameter().get(indice).setDefaultValue(new Value(this.listNodeMap.get(k)));
								}
							}
						}
					}

					// ******* FINE caricamento dei parametri per operation di doActivity ********************
				}
				// *********** verifico la presenza di NESTED CLASSIFIER per operation di doActivity ***************
				ArrayList<TypeParameter> typeParameterArray = new ArrayList<TypeParameter>();
				cont = 0;
				while (this.listStringElement.get(k + 1).equals("nestedClassifier")) {
					k++;
					listaElementi.next();
					typeParameterArray.add(new TypeParameter(this.listNodeMap.get(k)));
					// verifico se c'è un attritbuto per il typeprameter
					int i = 0;
					while (this.listStringElement.get(k + 1).equals("ownedAttribute")) {
						k++;
						listaElementi.next();
						typeParameterArray.get(cont).setAddListAttribute(new Attribute(this.listNodeMap.get(k)));
						// verifico che l'attributo abbia un default value
						if (this.listStringElement.get(k + 1).equals("defaultValue")) {
							k++;
							listaElementi.next();
							typeParameterArray.get(cont).getListAttribute().get(i).setDefaulValue(new Value(this.listNodeMap.get(k)));
						}
						i++;
					}
					// fine verifica per l'attributo del typeParameter
					cont++;
				}

				// *********** FINE caricamento del nested classifier ***********************
				// associo i nested classifier eventualmente trovati con i rispettivi parametri

				for (int i = 0; i < typeParameterArray.size(); i++) {
					for (int l=0; l < doActivity.getListOperation().size(); l++){
						for (int j = 0; j < doActivity.getListOperation().get(l).getListParameter().size(); j++) {
							if (doActivity.getListOperation().get(l).getListParameter().get(j).getIdTypeParameter() != null) {
								if (doActivity.getListOperation().get(l).getListParameter().get(j).getIdTypeParameter().equals(typeParameterArray.get(i).getId())) {
									doActivity.getListOperation().get(l).getListParameter().get(j).setTypeParameter(typeParameterArray.get(i));
								}
							}
						}
					}
				}

				// fine dell'associazione dei nested classifier con i rispettivi parametri
				// ***** verifico se c'è un un ownedReception x activity
				while (this.listStringElement.get(k + 1).equals("ownedReception")) {
					k++;
					listaElementi.next();
					doActivity.setAddListReception(new Reception(this.listNodeMap.get(k)));

					/*
					 * // ******** Controllo e aggiungo tutti i Rule che ci
					 * sono: per i preCondition - postCondition - specification
					 * while
					 * (this.listStringElement.get(k+1).equals("ownedRule")){
					 * //System.out.println("\n Ha il nodo UN OWNED_RULE"); k++;
					 * listaElementi.next();
					 * 
					 * }
					 */

					// **** fine sezione di caricamento dei Rule per i preCondition - postCondition - specification

					// **** controllo ed aggiungo tutti i parametri nella lista di reception
					while (this.listStringElement.get(k + 1).equals("ownedParameter")) {
						k++;
						listaElementi.next();
						// verifico che la listaParameter di reception è vuota
						for (int i=0; i < doActivity.getListReception().size(); i++){
							if (doActivity.getListReception().get(i).getListParameter().size() == 0) {
								doActivity.getListReception().get(i).setAddListParameter(new Parameter(this.listNodeMap.get(k)));
	
								// verifico se il parametro ha un lowerValue
								if (this.listStringElement.get(k + 1).equals("lowerValue")) {
									k++;
									listaElementi.next();
									doActivity.getListReception().get(i).getListParameter().get(0).setLowerValue(new LowerValue(this.listNodeMap.get(k)));
								}
								// verifico se il parametro ha un upperValue
								if (this.listStringElement.get(k + 1).equals("upperValue")) {
									k++;
									listaElementi.next();
									doActivity.getListReception().get(i).getListParameter().get(0).setUpperValue(new UpperValue(this.listNodeMap.get(k)));
								}
								// verifico se il parametro ha un defaultValue
								if (this.listStringElement.get(k + 1).equals("defaultValue")) {
									k++;
									listaElementi.next();
									doActivity.getListReception().get(i).getListParameter().get(0).setDefaultValue(new Value(this.listNodeMap.get(k)));
								}
							}
							// in questo caso la lista dei parametri di reception non è vuota
							else {
								doActivity.getListReception().get(i).setAddListParameter(new Parameter(this.listNodeMap.get(k)));
								int indice = doActivity.getListReception().get(i).getListParameter().size() - 1;
								// verifico se il parametro ha un lowerValue
								if (this.listStringElement.get(k + 1).equals("lowerValue")) {
									k++;
									listaElementi.next();
	
									doActivity.getListReception().get(i).getListParameter().get(indice).setLowerValue(new LowerValue(this.listNodeMap.get(k)));
								}
								// verifico se il parametro ha un upperValue
								if (this.listStringElement.get(k + 1).equals("upperValue")) {
									k++;
									listaElementi.next();
									doActivity.getListReception().get(i).getListParameter().get(indice).setUpperValue(new UpperValue(this.listNodeMap.get(k)));
								}
								// verifico se il parametro ha un defaultValue
								if (this.listStringElement.get(k + 1).equals("defaultValue")) {
									k++;
									listaElementi.next();
									doActivity.getListReception().get(i).getListParameter().get(indice).setDefaultValue(new Value(this.listNodeMap.get(k)));
								}
							}
						}
					}
					// FINE caricamento parametri nella lista
				}

				// VERIFICO SE CI SONO VARIABILI E LE CARICO
				int i = 0;
				while (this.listStringElement.get(k + 1).equals("variable")) {
					k++;
					listaElementi.next();
					doActivity.setAddListVariable(new Variable(this.listNodeMap.get(k)));				
					// verifico se c'è un lowerValue
					if (this.listStringElement.get(k + 1).equals("lowerValue")) {
						k++;
						listaElementi.next();
						doActivity.getListVariable().get(i).setLowerValue(new LowerValue(this.listNodeMap.get(k)));
					}
					// verifico se c'è un upperValue
					if (this.listStringElement.get(k + 1).equals("upperValue")) {
						k++;
						listaElementi.next();
						doActivity.getListVariable().get(i).setUpperValue(new UpperValue(this.listNodeMap.get(k)));
					}
					i++;
				}
				// ********** associo i tipi delle variabili alle varibili
				for (int ii = 0; ii < doActivity.getListVariable().size(); ii++) {
					if (doActivity.getListVariable().get(ii).getIdTypeVariable() != null) {
						for (int j = 0; j < typeParameterArray.size(); j++) {
							if (doActivity.getListVariable().get(ii).getIdTypeVariable().equals(typeParameterArray.get(j).getId())) {
								doActivity.getListVariable().get(ii).setTypeVariable(typeParameterArray.get(j));
							}
						}
						
						// provvedo a controllare la lista dei signal presenti in StateMachine ed associare con il tipo 
						//delle variabili di doActivity
						for (int j = 0; j < this.modello.getStateMachine().getListSignal().size(); j++) {
							if (doActivity.getListVariable().get(ii).getIdTypeVariable().equals(this.modello.getStateMachine().getListSignal().get(j).getId())) {
								doActivity.getListVariable().get(ii).setTypeVariableSignal(this.modello.getStateMachine().getListSignal().get(j));
							}
						}
					}
				}

				// ******** FINE dell'associazione delle variabili con i loro tipi

				// aggiungo activity alla transitione corrente
				nStati = this.modello.getStateMachine().getListState().size();
				for (int ii=0; ii < nStati; ii++) {
					if (this.modello.getStateMachine().getListState().get(ii).getId().equals(idParentState)) {
						this.modello.getStateMachine().getListState().get(ii).setDoActivity(doActivity);
					}
				}
			}
				break;

			/*
			 * 
			 * FINE CARICAMENTO DO_ACTIVITY
			 */

			/*
			 * 
			 * 
			 * carico entry
			 */
			case "entry": {
				Activity entryActivity = new Activity(this.listNodeMap.get(k));
				// ******** verifico se ci sono delle Rule per PreCondition e/o PostCondition di entry ********************

				int cont = 0;
				ArrayList<Rule> ruleArray = new ArrayList<Rule>();
				while (this.listStringElement.get(k + 1).equals("ownedRule")) {
					k++;
					listaElementi.next();
					ruleArray.add(new Rule(this.listNodeMap.get(k)));
					// verifico se c'è specification
					if (this.listStringElement.get(k + 1).equals("specification")) {
						k++;
						listaElementi.next();
						ruleArray.get(cont).setSpecification(new Specification(this.listNodeMap.get(k)));

						// verifico se c'è un commento
						if (this.listStringElement.get(k + 1).equals("ownedComment")) {
							k++;
							listaElementi.next();
							ruleArray.get(cont).getSpecification().setComment(new Comment(this.listNodeMap.get(k)));
							// prelevo il valore del commento nel tag body e lo aggiungo al commento
							if (this.listStringElement.get(k + 1).equals("body")) {
								k++;
								listaElementi.next();
								ruleArray.get(cont).getSpecification().getComment().setBody(searchTextInElement(this.listNode.get(k)));
							}
						}
					} else if (this.listStringElement.get(k + 1).equals("ownedComment")) {
						k++;
						listaElementi.next();
						ruleArray.get(cont).setComment(new Comment(this.listNodeMap.get(k)));
						// prelevo il valore del commento nel tag body e lo
						// aggiungo al commento
						if (this.listStringElement.get(k + 1).equals("body")) {
							
							k++;
							listaElementi.next();
							ruleArray.get(cont).getComment().setBody(searchTextInElement(this.listNode.get(k)));
						}

						if (this.listStringElement.get(k + 1).equals("specification")) {
							k++;
							listaElementi.next();
							ruleArray.get(cont).setSpecification(new Specification(this.listNodeMap.get(k)));

							// verifico se c'è un commento
							if (this.listStringElement.get(k + 1).equals("ownedComment")) {
								k++;
								listaElementi.next();
								ruleArray.get(cont).getSpecification().setComment(new Comment(this.listNodeMap.get(k)));
								// prelevo il valore del commento nel tag body e lo aggiungo al commento
								if (this.listStringElement.get(k + 1).equals("body")) {
									
									k++;
									listaElementi.next();
									ruleArray.get(cont).getSpecification().getComment().setBody(searchTextInElement(this.listNode.get(k)));
								}
							}

						}
					}

					cont++;

				}

				// ************** fine caricamento rule per - doActivity *********************

				// verifico se ci sono gli Id per il preCondition in activity
				if (entryActivity.getIdPreCondition() != null && entryActivity.getIdPreCondition().length > 0) {
					for (int i = 0; i < entryActivity.getIdPreCondition().length; i++) {
						for (int j = 0; j < ruleArray.size(); j++) {
							if (entryActivity.getIdPreCondition()[i].equals(ruleArray.get(j).getId())) {
								entryActivity.setAddListPreCondition(ruleArray.get(j));
							}
						}
					}
				}
				// verifico se ci sono gli Id per il postCondition in activity
				if (entryActivity.getIdPostCondition() != null && entryActivity.getIdPostCondition().length > 0) {
					for (int i = 0; i < entryActivity.getIdPostCondition().length; i++) {
						for (int j = 0; j < ruleArray.size(); j++) {
							if (entryActivity.getIdPostCondition()[i].equals(ruleArray.get(j).getId())) {
								entryActivity.setAddListPostCondition(ruleArray.get(j));
							}
						}
					}
				}
				ruleArray.clear();
				// ********** termine sezione del caricamento delle RULE per entryActivity ****************************

				// ********* verifico se c'è un nestedClassifier (signal) ************
				if (this.listStringElement.get(k + 1).equals("nestedClassifier")) {
					k++;
					listaElementi.next();
					entryActivity.setSignal(new Signal(this.listNodeMap.get(k)));
					// *************** verifico se c'è un nestedClassifier (signal) ********************
					while (this.listStringElement.get(k + 1).equals("ownedAttribute")) {
						k++;
						listaElementi.next();
						Attribute attrib = new Attribute(this.listNodeMap.get(k));
						if (this.listStringElement.get(k + 1).equals("defaultValue")) {
							tag = (String) listaElementi.next();
							k++;
							Value value = new Value(this.listNodeMap.get(k));
							attrib.setDefaulValue(value);
						}

						// Verifico che tipo di attributo è: Port - Property - ExtensionEnd
						switch (attrib.getType()) {
						case "uml:Port":
							Port port = new Port(attrib, this.listNodeMap.get(k));
							break;
						case "uml:Property":
							Property property = new Property(attrib, this.listNodeMap.get(k));
							break;
						case "uml:ExtensionEnd":
							ExtensionEnd extensionEnd = new ExtensionEnd(attrib, this.listNodeMap.get(k));
							break;

						}

						switch (attrib.getType()) {
						case "uml:Port":
							Port port = new Port(attrib, this.listNodeMap.get(k));
							entryActivity.getSignal().setAddListAttribute(port);
							break;
						case "uml:Property":
							Property property = new Property(attrib, this.listNodeMap.get(k));
							entryActivity.getSignal().setAddListAttribute(property);
							break;
						case "uml:ExtensionEnd":
							ExtensionEnd extensionEnd = new ExtensionEnd(attrib, this.listNodeMap.get(k));
							entryActivity.getSignal().setAddListAttribute(extensionEnd);
							break;
						case "":
							entryActivity.getSignal().setAddListAttribute(attrib);
							break;
						}
					}
				}
				// ******** FINE DELLA SECTION nestedClassifier (signal) x entryActivity

				
				// ****************** verifico se c'è un un operation********************************
				while (this.listStringElement.get(k + 1).equals("ownedOperation")) {
					k++;
					listaElementi.next();
					entryActivity.setAddListOperation(new Operation(this.listNodeMap.get(k)));

					// verifico se c'è un commento
					if (this.listStringElement.get(k + 1).equals("ownedComment")) {
						k++;
						listaElementi.next();
						Comment comment = new Comment(this.listNodeMap.get(k));
						// prelevo il valore del commento nel tag body e lo aggiungo al commento
						if (this.listStringElement.get(k + 1).equals("body")) {
							
							k++;
							listaElementi.next();
							comment.setBody(searchTextInElement(this.listNode.get(k)));
						}
						// verifico che il commento che ho prelevato dal nodo
						// sia il commento dell'operation di entryActivity
						for (int i = 0; i < comment.getAnnotatedElement().length; i++) {
							for (int j=0; j < entryActivity.getListOperation().size(); j++){
								if (comment.getAnnotatedElement()[i].equals(entryActivity.getListOperation().get(j).getId())) {
									entryActivity.getListOperation().get(entryActivity.getListOperation().size()-1).setComment(comment);
								}
							}
						}

					}

					// ****** Verifico e aggiungo i rule per i preCondition - postCondition - bodyCondition **********
					ruleArray.clear();
					cont=0;
					while (this.listStringElement.get(k + 1).equals("ownedRule")) {
						k++;
						listaElementi.next();
						ruleArray.add(new Rule(this.listNodeMap.get(k)));
						// verifico se c'è specification
						if (this.listStringElement.get(k + 1).equals("specification")) {
							k++;
							listaElementi.next();
							ruleArray.get(cont).setSpecification(new Specification(this.listNodeMap.get(k)));

							// verifico se c'è un commento
							if (this.listStringElement.get(k + 1).equals("ownedComment")) {
								k++;
								listaElementi.next();
								ruleArray.get(cont).getSpecification().setComment(new Comment(this.listNodeMap.get(k)));
								// prelevo il valore del commento nel tag body e lo aggiungo al commento
								if (this.listStringElement.get(k + 1).equals("body")) {
									
									k++;
									listaElementi.next();
									ruleArray.get(cont).getSpecification().getComment().setBody(searchTextInElement(this.listNode.get(k)));
								}
							}
						} else if (this.listStringElement.get(k + 1).equals("ownedComment")) {
							k++;
							listaElementi.next();
							ruleArray.get(cont).setComment(new Comment(this.listNodeMap.get(k)));
							// prelevo il valore del commento nel tag body e lo aggiungo al commento
							if (this.listStringElement.get(k + 1).equals("body")) {
								
								k++;
								listaElementi.next();
								ruleArray.get(cont).getComment().setBody(searchTextInElement(this.listNode.get(k)));
							}

							if (this.listStringElement.get(k + 1).equals("specification")) {
								k++;
								listaElementi.next();
								ruleArray.get(cont).setSpecification(new Specification(this.listNodeMap.get(k)));

								// verifico se c'è un commento
								if (this.listStringElement.get(k + 1).equals("ownedComment")) {
									k++;
									listaElementi.next();
									ruleArray.get(cont).getSpecification().setComment(new Comment(this.listNodeMap.get(k)));
									// prelevo il valore del commento nel tag body e lo aggiungo al commento
									if (this.listStringElement.get(k + 1).equals("body")) {
										
										k++;
										listaElementi.next();
										ruleArray.get(cont).getSpecification().getComment().setBody(searchTextInElement(this.listNode.get(k)));
									}
								}

							}
						}

						cont++;

					}
					// ****** FINE caricamento dei rule per i preCondition - postCondition - bodyCondition **********

					// verifico se ci sono gli Id per il preCondition di operation in entryActivity
					for (int l=0; l < entryActivity.getListOperation().size(); l++){
						if (entryActivity.getListOperation().get(l).getIdPreCondition() != null && entryActivity.getListOperation().get(l).getIdPreCondition().length > 0) {
							for (int i = 0; i < entryActivity.getListOperation().get(l).getIdPreCondition().length; i++) {
								for (int j = 0; j < ruleArray.size(); j++) {
									if (entryActivity.getListOperation().get(l).getIdPreCondition()[i].equals(ruleArray.get(j).getId())) {
										entryActivity.getListOperation().get(l).setAddListPreCondition(ruleArray.get(j));
									}
								}
							}
						}
					}

					// verifico se ci sono gli Id per il postCondition di operation in entryActivity
					for (int l=0; l < entryActivity.getListOperation().size(); l++){
						if (entryActivity.getListOperation().get(l).getIdPostCondition() != null && entryActivity.getListOperation().get(l).getIdPostCondition().length > 0) {
							for (int i = 0; i < entryActivity.getListOperation().get(l).getIdPostCondition().length; i++) {
								for (int j = 0; j < ruleArray.size(); j++) {
									if (entryActivity.getListOperation().get(l).getIdPostCondition()[i].equals(ruleArray.get(j).getId())) {
										entryActivity.getListOperation().get(l).setAddListPostCondition(ruleArray.get(j));
									}
								}
							}
						}
					}

					// verifico se ci sono gli Id per il bodyCondition di operation in entryActivity
					for (int l=0; l < entryActivity.getListOperation().size(); l++){
						if (entryActivity.getListOperation().get(l).getIdBodyCondition() != null) {
							for (int j = 0; j < ruleArray.size(); j++) {
								if (entryActivity.getListOperation().get(l).getIdBodyCondition().equals(ruleArray.get(j).getId())) {
									entryActivity.getListOperation().get(l).setBodyCondition(ruleArray.get(j));
								}
							}
	
						}
					}
					// / ******** ho finito di caricare i rule *****************

					// ******** adesso carico tutti i parametri per operation di
					// entryActivity con i loro vari valori presenti *******

					while (this.listStringElement.get(k + 1).equals("ownedParameter")) {
						k++;
						listaElementi.next();
						// verifico che la listaParameter di reception è vuota
						for (int i = 0; i < entryActivity.getListOperation().size(); i++){
							if (entryActivity.getListOperation().get(i).getListParameter().size() == 0) {
								entryActivity.getListOperation().get(i).setAddListParameter(new Parameter(this.listNodeMap.get(k)));
	
								// verifico se il parametro ha un lowerValue
								if (this.listStringElement.get(k + 1).equals("lowerValue")) {
									k++;
									listaElementi.next();
									entryActivity.getListOperation().get(i).getListParameter().get(0).setLowerValue(new LowerValue(this.listNodeMap.get(k)));
								}
								// verifico se il parametro ha un upperValue
								if (this.listStringElement.get(k + 1).equals("upperValue")) {
									k++;
									listaElementi.next();
									entryActivity.getListOperation().get(i).getListParameter().get(0).setUpperValue(new UpperValue(this.listNodeMap.get(k)));
								}
								// verifico se il parametro ha un defaultValue
								if (this.listStringElement.get(k + 1).equals("defaultValue")) {
									k++;
									listaElementi.next();
									entryActivity.getListOperation().get(i).getListParameter().get(0).setDefaultValue(new Value(this.listNodeMap.get(k)));
								}
							}
							// in questo caso la lista dei parametri di OPERATION non è vuota
							else {
								entryActivity.getListOperation().get(i).setAddListParameter(new Parameter(this.listNodeMap.get(k)));
								int indice = entryActivity.getListOperation().get(i).getListParameter().size() - 1;
								// verifico se il parametro ha un lowerValue
								if (this.listStringElement.get(k + 1).equals("lowerValue")) {
									k++;
									listaElementi.next();
	
									entryActivity.getListOperation().get(i).getListParameter().get(indice).setLowerValue(new LowerValue(this.listNodeMap.get(k)));
								}
								// verifico se il parametro ha un upperValue
								if (this.listStringElement.get(k + 1).equals("upperValue")) {
									k++;
									listaElementi.next();
									entryActivity.getListOperation().get(i).getListParameter().get(indice).setUpperValue(new UpperValue(this.listNodeMap.get(k)));
								}
								// verifico se il parametro ha un defaultValue
								if (this.listStringElement.get(k + 1).equals("defaultValue")) {
									k++;
									listaElementi.next();
									entryActivity.getListOperation().get(i).getListParameter().get(indice).setDefaultValue(new Value(this.listNodeMap.get(k)));
								}
							}
						}
					}

					// ******* FINE caricamento dei parametri per operation di entryActivity ********************
				}
				// *********** verifico la presenza di NESTED CLASSIFIER per operation di entryActivity ***************
				ArrayList<TypeParameter> typeParameterArray = new ArrayList<TypeParameter>();
				cont = 0;
				while (this.listStringElement.get(k + 1).equals("nestedClassifier")) {
					k++;
					listaElementi.next();
					typeParameterArray.add(new TypeParameter(this.listNodeMap.get(k)));
					// verifico se c'è un attritbuto per il typeprameter
					int i = 0;
					while (this.listStringElement.get(k + 1).equals("ownedAttribute")) {
						k++;
						listaElementi.next();
						typeParameterArray.get(cont).setAddListAttribute(new Attribute(this.listNodeMap.get(k)));
						// verifico che l'attributo abbia un default value
						if (this.listStringElement.get(k + 1).equals("defaultValue")) {
							k++;
							listaElementi.next();
							typeParameterArray.get(cont).getListAttribute().get(i).setDefaulValue(new Value(this.listNodeMap.get(k)));
						}
						i++;
					}
					// fine verifica per l'attributo del typeParameter
					cont++;
				}

				// *********** FINE caricamento del nested classifier**********
				// associo i nested classifier eventualmente trovati con i rispettivi parametri

				for (int i = 0; i < typeParameterArray.size(); i++) {
					for (int l=0; l < entryActivity.getListOperation().size(); l++){
						for (int j = 0; j < entryActivity.getListOperation().get(l).getListParameter().size(); j++) {
							if (entryActivity.getListOperation().get(l).getListParameter().get(j).getIdTypeParameter() != null) {
								if (entryActivity.getListOperation().get(l).getListParameter().get(j).getIdTypeParameter().equals(typeParameterArray.get(i).getId())) {
									entryActivity.getListOperation().get(l).getListParameter().get(j).setTypeParameter(typeParameterArray.get(i));
								}
							}
						}
					}
				}

				// fine dell'associazione dei nested classifier con i rispettivi parametri

				// ***** verifico se c'è un un ownedReception x entryActivity
				while (this.listStringElement.get(k + 1).equals("ownedReception")) {
					k++;
					listaElementi.next();
					entryActivity.setAddListReception(new Reception(this.listNodeMap.get(k)));

					/*
					 * // ******** Controllo e aggiungo tutti i Rule che ci
					 * sono: per i preCondition - postCondition - specification
					 * while
					 * (this.listStringElement.get(k+1).equals("ownedRule")){
					 * //System.out.println("\n Ha il nodo UN OWNED_RULE"); k++;
					 * listaElementi.next();
					 * 
					 * }
					 */

					// **** fine sezione di caricamento dei Rule per i preCondition - postCondition - specification

					// **** controllo ed aggiungo tutti i parametri nella lista di reception
					while (this.listStringElement.get(k + 1).equals("ownedParameter")) {
						k++;
						listaElementi.next();
						// verifico che la listaParameter di reception è vuota
						for (int i=0; i < entryActivity.getListReception().size(); i++){
							if (entryActivity.getListReception().get(i).getListParameter().size() == 0) {
								entryActivity.getListReception().get(i).setAddListParameter(new Parameter(this.listNodeMap.get(k)));
	
								// verifico se il parametro ha un lowerValue
								if (this.listStringElement.get(k + 1).equals("lowerValue")) {
									k++;
									listaElementi.next();
									entryActivity.getListReception().get(i).getListParameter().get(0).setLowerValue(new LowerValue(this.listNodeMap.get(k)));
								}
								// verifico se il parametro ha un upperValue
								if (this.listStringElement.get(k + 1).equals("upperValue")) {
									k++;
									listaElementi.next();
									entryActivity.getListReception().get(i).getListParameter().get(0).setUpperValue(new UpperValue(this.listNodeMap.get(k)));
								}
								// verifico se il parametro ha un defaultValue
								if (this.listStringElement.get(k + 1).equals("defaultValue")) {
									k++;
									listaElementi.next();
									entryActivity.getListReception().get(i).getListParameter().get(0).setDefaultValue(new Value(this.listNodeMap.get(k)));
								}
							}
							// in questo caso la lista dei parametri di reception non è vuota
							else {
								entryActivity.getListReception().get(i).setAddListParameter(new Parameter(this.listNodeMap.get(k)));
								int indice = entryActivity.getListReception().get(i).getListParameter().size() - 1;
								// verifico se il parametro ha un lowerValue
								if (this.listStringElement.get(k + 1).equals("lowerValue")) {
									k++;
									listaElementi.next();
	
									entryActivity.getListReception().get(i).getListParameter().get(indice).setLowerValue(new LowerValue(this.listNodeMap.get(k)));
								}
								// verifico se il parametro ha un upperValue
								if (this.listStringElement.get(k + 1).equals("upperValue")) {
									k++;
									listaElementi.next();
									entryActivity.getListReception().get(i).getListParameter().get(indice).setUpperValue(new UpperValue(this.listNodeMap.get(k)));
								}
								// verifico se il parametro ha un defaultValue
								if (this.listStringElement.get(k + 1).equals("defaultValue")) {
									k++;
									listaElementi.next();
									entryActivity.getListReception().get(i).getListParameter().get(indice).setDefaultValue(new Value(this.listNodeMap.get(k)));
								}
							}
						}
					}
					// FINE caricamento parametri nella lista
				}
				
				// VERIFICO SE CI SONO VARIABILI E LE CARICO
				int i = 0;
				while (this.listStringElement.get(k + 1).equals("variable")) {
					k++;
					listaElementi.next();
					entryActivity.setAddListVariable(new Variable(this.listNodeMap.get(k)));
					// verifico se c'è un lowerValue
					if (this.listStringElement.get(k + 1).equals("lowerValue")) {
						k++;
						listaElementi.next();
						entryActivity.getListVariable().get(i).setLowerValue(new LowerValue(this.listNodeMap.get(k)));
					}
					// verifico se c'è un upperValue
					if (this.listStringElement.get(k + 1).equals("upperValue")) {
						k++;
						listaElementi.next();
						entryActivity.getListVariable().get(i).setUpperValue(new UpperValue(this.listNodeMap.get(k)));
					}
					i++;
				}
				// ********** associo i tipi delle variabili alle varibili
				for (int ii = 0; ii < entryActivity.getListVariable().size(); ii++) {
					if (entryActivity.getListVariable().get(ii).getIdTypeVariable() != null) {
						for (int j = 0; j < typeParameterArray.size(); j++) {
							if (entryActivity.getListVariable().get(ii).getIdTypeVariable().equals(typeParameterArray.get(j).getId())) {
								entryActivity.getListVariable().get(ii).setTypeVariable(typeParameterArray.get(j));
							}
						}
						
						// provvedo a controllare la lista dei signal presenti in StateMachine ed associare con il tipo 
						//delle variabili di doActivity
						for (int j = 0; j < this.modello.getStateMachine().getListSignal().size(); j++) {
							if (entryActivity.getListVariable().get(ii).getIdTypeVariable().equals(this.modello.getStateMachine().getListSignal().get(j).getId())) {
								entryActivity.getListVariable().get(ii).setTypeVariableSignal(this.modello.getStateMachine().getListSignal().get(j));
							}
						}
					}
				}

				// ******** FINE dell'associazione delle variabili con i loro tipi

				// aggiungo entryActivity alla transitione corrente
				nStati = this.modello.getStateMachine().getListState().size();
				for (i=0; i < nStati; i++) {
					if (this.modello.getStateMachine().getListState().get(i).getId().equals(idParentState)) {
						this.modello.getStateMachine().getListState().get(i).setEntry(entryActivity);
						NamedNodeMap n= this.listNodeMap.get(k);
					}
				}
			}
				break;

			/*
			 * 
			 * FINE CARICAMENTO ENTRY
			 */

			/*
			 * 
			 * 
			 * carico exit
			 */

			case "exit": {
				Activity exitActivity = new Activity(this.listNodeMap.get(k));
				// ******** verifico se ci sono delle Rule per PreCondition e/o PostCondition di exitActivity ********************

				int cont = 0;
				ArrayList<Rule> ruleArray = new ArrayList<Rule>();
				while (this.listStringElement.get(k + 1).equals("ownedRule")) {
					k++;
					listaElementi.next();
					ruleArray.add(new Rule(this.listNodeMap.get(k)));
					// verifico se c'è specification
					if (this.listStringElement.get(k + 1).equals("specification")) {
						k++;
						listaElementi.next();
						ruleArray.get(cont).setSpecification(new Specification(this.listNodeMap.get(k)));

						// verifico se c'è un commento
						if (this.listStringElement.get(k + 1).equals("ownedComment")) {
							k++;
							listaElementi.next();
							ruleArray.get(cont).getSpecification().setComment(new Comment(this.listNodeMap.get(k)));
							// prelevo il valore del commento nel tag body e lo aggiungo al commento
							if (this.listStringElement.get(k + 1).equals("body")) {
								
								k++;
								listaElementi.next();
								ruleArray.get(cont).getSpecification().getComment().setBody(searchTextInElement(this.listNode.get(k)));
							}
						}
					} else if (this.listStringElement.get(k + 1).equals("ownedComment")) {
						k++;
						listaElementi.next();
						ruleArray.get(cont).setComment(new Comment(this.listNodeMap.get(k)));
						// prelevo il valore del commento nel tag body e lo aggiungo al commento
						if (this.listStringElement.get(k + 1).equals("body")) {
							
							k++;
							listaElementi.next();
							ruleArray.get(cont).getComment().setBody(searchTextInElement(this.listNode.get(k)));
						}

						if (this.listStringElement.get(k + 1).equals("specification")) {
							k++;
							listaElementi.next();
							ruleArray.get(cont).setSpecification(new Specification(this.listNodeMap.get(k)));

							// verifico se c'è un commento
							if (this.listStringElement.get(k + 1).equals("ownedComment")) {
								k++;
								listaElementi.next();
								ruleArray.get(cont).getSpecification().setComment(new Comment(this.listNodeMap.get(k)));
								// prelevo il valore del commento nel tag body e lo aggiungo al commento
								if (this.listStringElement.get(k + 1).equals("body")) {
									
									k++;
									listaElementi.next();
									ruleArray.get(cont).getSpecification().getComment().setBody(searchTextInElement(this.listNode.get(k)));
								}
							}

						}
					}

					cont++;

				}

				// ************** fine caricamento rule per - exitActivity *********************

				// verifico se ci sono gli Id per il preCondition in exitActivity
				if (exitActivity.getIdPreCondition() != null && exitActivity.getIdPreCondition().length > 0) {
					for (int i = 0; i < exitActivity.getIdPreCondition().length; i++) {
						for (int j = 0; j < ruleArray.size(); j++) {
							if (exitActivity.getIdPreCondition()[i].equals(ruleArray.get(j).getId())) {
								exitActivity.setAddListPreCondition(ruleArray.get(j));
							}
						}
					}
				}
				// verifico se ci sono gli Id per il postCondition in exitActivity
				if (exitActivity.getIdPostCondition() != null && exitActivity.getIdPostCondition().length > 0) {
					for (int i = 0; i < exitActivity.getIdPostCondition().length; i++) {
						for (int j = 0; j < ruleArray.size(); j++) {
							if (exitActivity.getIdPostCondition()[i].equals(ruleArray.get(j).getId())) {
								exitActivity.setAddListPostCondition(ruleArray.get(j));
							}
						}
					}
				}
				ruleArray.clear();
				// ********** termine sezione del caricamento delle RULE per exitActivity ****************************

				// ********* verifico se c'è un nestedClassifier (signal)************
				if (this.listStringElement.get(k + 1).equals("nestedClassifier")) {
					k++;
					listaElementi.next();
					exitActivity.setSignal(new Signal(this.listNodeMap.get(k)));

					// *************** verifico se c'è un nestedClassifier (signal) ********************
					while (this.listStringElement.get(k + 1).equals("ownedAttribute")) {
						k++;
						listaElementi.next();
						Attribute attrib = new Attribute(this.listNodeMap.get(k));
						if (this.listStringElement.get(k + 1).equals("defaultValue")) {
							tag = (String) listaElementi.next();
							k++;
							Value value = new Value(this.listNodeMap.get(k));
							attrib.setDefaulValue(value);
						}

						// Verifico che tipo di attributo è: Port - Property - ExtensionEnd
						switch (attrib.getType()) {
						case "uml:Port":
							Port port = new Port(attrib, this.listNodeMap.get(k));
							break;
						case "uml:Property":
							Property property = new Property(attrib, this.listNodeMap.get(k));
							break;
						case "uml:ExtensionEnd":
							ExtensionEnd extensionEnd = new ExtensionEnd(attrib, this.listNodeMap.get(k));
							break;

						}

						switch (attrib.getType()) {
						case "uml:Port":
							Port port = new Port(attrib, this.listNodeMap.get(k));
							exitActivity.getSignal().setAddListAttribute(port);
							break;
						case "uml:Property":
							Property property = new Property(attrib, this.listNodeMap.get(k));
							exitActivity.getSignal().setAddListAttribute(property);
							break;
						case "uml:ExtensionEnd":
							ExtensionEnd extensionEnd = new ExtensionEnd(attrib, this.listNodeMap.get(k));
							exitActivity.getSignal().setAddListAttribute(extensionEnd);
							break;
						case "":
							exitActivity.getSignal().setAddListAttribute(attrib);
							break;

						}
					}
				}
				// ******** FINE DELLA SECTION nestedClassifier (signal) x exitActivity

				
				// ****************** verifico se c'è un un operation**********************
				while (this.listStringElement.get(k + 1).equals("ownedOperation")) {
					k++;
					listaElementi.next();
					exitActivity.setAddListOperation(new Operation(this.listNodeMap.get(k)));

					// verifico se c'è un commento
					if (this.listStringElement.get(k + 1).equals("ownedComment")) {
						k++;
						listaElementi.next();
						Comment comment = new Comment(this.listNodeMap.get(k));
						// prelevo il valore del commento nel tag body e lo aggiungo al commento
						if (this.listStringElement.get(k + 1).equals("body")) {
							
							k++;
							listaElementi.next();
							comment.setBody(searchTextInElement(this.listNode.get(k)));
						}
						// verifico che il commento che ho prelevato dal nodo
						// sia il commento dell'operation di exitActivity
						for (int i = 0; i < comment.getAnnotatedElement().length; i++) {
							for (int j=0; j < exitActivity.getListOperation().size(); j++){
								if (comment.getAnnotatedElement()[i].equals(exitActivity.getListOperation().get(j).getId())) {
									exitActivity.getListOperation().get(exitActivity.getListOperation().size()-1).setComment(comment);
								}
							}
						}

					}

					// ****** Verifico e aggiungo i rule per i preCondition - postCondition - bodyCondition **********
					ruleArray.clear();
					cont=0;
					while (this.listStringElement.get(k + 1).equals("ownedRule")) {
						k++;
						listaElementi.next();
						ruleArray.add(new Rule(this.listNodeMap.get(k)));
						// verifico se c'è specification
						if (this.listStringElement.get(k + 1).equals("specification")) {
							k++;
							listaElementi.next();
							ruleArray.get(cont).setSpecification(new Specification(this.listNodeMap.get(k)));

							// verifico se c'è un commento
							if (this.listStringElement.get(k + 1).equals("ownedComment")) {
								k++;
								listaElementi.next();
								ruleArray.get(cont).getSpecification().setComment(new Comment(this.listNodeMap.get(k)));
								// prelevo il valore del commento nel tag body e lo aggiungo al commento
								if (this.listStringElement.get(k + 1).equals("body")) {
									
									k++;
									listaElementi.next();
									ruleArray.get(cont).getSpecification().getComment().setBody(searchTextInElement(this.listNode.get(k)));
								}
							}
						} else if (this.listStringElement.get(k + 1).equals("ownedComment")) {
							k++;
							listaElementi.next();
							ruleArray.get(cont).setComment(new Comment(this.listNodeMap.get(k)));
							// prelevo il valore del commento nel tag body e lo aggiungo al commento
							if (this.listStringElement.get(k + 1).equals("body")) {
								
								k++;
								listaElementi.next();
								ruleArray.get(cont).getComment().setBody(searchTextInElement(this.listNode.get(k)));
							}

							if (this.listStringElement.get(k + 1).equals("specification")) {
								k++;
								listaElementi.next();
								ruleArray.get(cont).setSpecification(new Specification(this.listNodeMap.get(k)));

								// verifico se c'è un commento
								if (this.listStringElement.get(k + 1).equals("ownedComment")) {
									k++;
									listaElementi.next();
									ruleArray.get(cont).getSpecification().setComment(new Comment(this.listNodeMap.get(k)));
									// prelevo il valore del commento nel tag body e lo aggiungo al commento
									if (this.listStringElement.get(k + 1).equals("body")) {
										
										k++;
										listaElementi.next();
										ruleArray.get(cont).getSpecification().getComment().setBody(searchTextInElement(this.listNode.get(k)));
									}
								}

							}
						}

						cont++;

					}
					// ****** FINE caricamento dei rule per i preCondition - postCondition - bodyCondition **********

					// verifico se ci sono gli Id per il preCondition di operation in exitActivity
					for (int l=0; l < exitActivity.getListOperation().size(); l++){
						if (exitActivity.getListOperation().get(l).getIdPreCondition() != null && exitActivity.getListOperation().get(l).getIdPreCondition().length > 0) {
							for (int i = 0; i < exitActivity.getListOperation().get(l).getIdPreCondition().length; i++) {
								for (int j = 0; j < ruleArray.size(); j++) {
									if (exitActivity.getListOperation().get(l).getIdPreCondition()[i].equals(ruleArray.get(j).getId())) {
										exitActivity.getListOperation().get(l).setAddListPreCondition(ruleArray.get(j));
									}
								}
							}
						}
					}

					// verifico se ci sono gli Id per il postCondition di operation in entryActivity
					for (int l=0; l < exitActivity.getListOperation().size(); l++){
						if (exitActivity.getListOperation().get(l).getIdPostCondition() != null && exitActivity.getListOperation().get(l).getIdPostCondition().length > 0) {
							for (int i = 0; i < exitActivity.getListOperation().get(l).getIdPostCondition().length; i++) {
								for (int j = 0; j < ruleArray.size(); j++) {
									if (exitActivity.getListOperation().get(l).getIdPostCondition()[i].equals(ruleArray.get(j).getId())) {
										exitActivity.getListOperation().get(l).setAddListPostCondition(ruleArray.get(j));
									}
								}
							}
						}
					}

					// verifico se ci sono gli Id per il bodyCondition di operation in exitActivity
					for (int l=0; l < exitActivity.getListOperation().size(); l++){
						if (exitActivity.getListOperation().get(l).getIdBodyCondition() != null) {
							for (int j = 0; j < ruleArray.size(); j++) {
								if (exitActivity.getListOperation().get(l).getIdBodyCondition().equals(ruleArray.get(j).getId())) {
									exitActivity.getListOperation().get(l).setBodyCondition(ruleArray.get(j));
								}
							}
	
						}
					}
					// / ******** ho finito di caricare i rule *****************

					// ******** adesso carico tutti i parametri per operation di
					// exitActivity con i loro vari valori presenti *******

					while (this.listStringElement.get(k + 1).equals("ownedParameter")) {
						k++;
						listaElementi.next();
						// verifico che la listaParameter di reception è vuota
						for (int i = 0; i < exitActivity.getListOperation().size(); i++){
							if (exitActivity.getListOperation().get(i).getListParameter().size() == 0) {
								exitActivity.getListOperation().get(i).setAddListParameter(new Parameter(this.listNodeMap.get(k)));
	
								// verifico se il parametro ha un lowerValue
								if (this.listStringElement.get(k + 1).equals("lowerValue")) {
									k++;
									listaElementi.next();
									exitActivity.getListOperation().get(i).getListParameter().get(0).setLowerValue(new LowerValue(this.listNodeMap.get(k)));
								}
								// verifico se il parametro ha un upperValue
								if (this.listStringElement.get(k + 1).equals("upperValue")) {
									k++;
									listaElementi.next();
									exitActivity.getListOperation().get(i).getListParameter().get(0).setUpperValue(new UpperValue(this.listNodeMap.get(k)));
								}
								// verifico se il parametro ha un defaultValue
								if (this.listStringElement.get(k + 1).equals("defaultValue")) {
									k++;
									listaElementi.next();
									exitActivity.getListOperation().get(i).getListParameter().get(0).setDefaultValue(new Value(this.listNodeMap.get(k)));
								}
							}
							// in questo caso la lista dei parametri di OPERATIONnon è vuota
							else {
								exitActivity.getListOperation().get(i).setAddListParameter(new Parameter(this.listNodeMap.get(k)));
								int indice = exitActivity.getListOperation().get(i).getListParameter().size() - 1;
								// verifico se il parametro ha un lowerValue
								if (this.listStringElement.get(k + 1).equals("lowerValue")) {
									k++;
									listaElementi.next();
	
									exitActivity.getListOperation().get(i).getListParameter().get(indice).setLowerValue(new LowerValue(this.listNodeMap.get(k)));
								}
								// verifico se il parametro ha un upperValue
								if (this.listStringElement.get(k + 1).equals("upperValue")) {
									k++;
									listaElementi.next();
									exitActivity.getListOperation().get(i).getListParameter().get(indice).setUpperValue(new UpperValue(this.listNodeMap.get(k)));
								}
								// verifico se il parametro ha un defaultValue
								if (this.listStringElement.get(k + 1).equals("defaultValue")) {
									k++;
									listaElementi.next();
									exitActivity.getListOperation().get(i).getListParameter().get(indice).setDefaultValue(new Value(this.listNodeMap.get(k)));
								}
							}
						}
					}

					// ******* FINE caricamento dei parametri per operation di exitActivity ********************
				}
				// *********** verifico la presenza di NESTED CLASSIFIER per operation di exitActivity ***************
				ArrayList<TypeParameter> typeParameterArray = new ArrayList<TypeParameter>();
				cont = 0;
				while (this.listStringElement.get(k + 1).equals("nestedClassifier")) {
					k++;
					listaElementi.next();
					typeParameterArray.add(new TypeParameter(this.listNodeMap.get(k)));
					// verifico se c'è un attritbuto per il typeprameter
					int i = 0;
					while (this.listStringElement.get(k + 1).equals("ownedAttribute")) {
						k++;
						listaElementi.next();
						typeParameterArray.get(cont).setAddListAttribute(new Attribute(this.listNodeMap.get(k)));
						// verifico che l'attributo abbia un default value
						if (this.listStringElement.get(k + 1).equals("defaultValue")) {
							k++;
							listaElementi.next();
							typeParameterArray.get(cont).getListAttribute().get(i).setDefaulValue(new Value(this.listNodeMap.get(k)));
						}
						i++;
					}
					// fine verifica per l'attributo del typeParameter
					cont++;
				}

				// *********** FINE caricamento del nested classifier*************
				// associo i nested classifier eventualmente trovati con i rispettivi parametri

				for (int i = 0; i < typeParameterArray.size(); i++) {
					for (int l=0; l < exitActivity.getListOperation().size(); l++){
						for (int j = 0; j < exitActivity.getListOperation().get(l).getListParameter().size(); j++) {
							if (exitActivity.getListOperation().get(l).getListParameter().get(j).getIdTypeParameter() != null) {
								if (exitActivity.getListOperation().get(l).getListParameter().get(j).getIdTypeParameter().equals(typeParameterArray.get(i).getId())) {
									exitActivity.getListOperation().get(l).getListParameter().get(j).setTypeParameter(typeParameterArray.get(i));
								}
							}
						}
					}
				}

				// fine dell'associazione dei nested classifier con i rispettivi parametri

				
				// ***** verifico se c'è un un ownedReception x exitActivity
				while (this.listStringElement.get(k + 1).equals("ownedReception")) {
					k++;
					listaElementi.next();
					exitActivity.setAddListReception(new Reception(this.listNodeMap.get(k)));

					/*
					 * // ******** Controllo e aggiungo tutti i Rule che ci
					 * sono: per i preCondition - postCondition - specification
					 * while
					 * (this.listStringElement.get(k+1).equals("ownedRule")){
					 * //System.out.println("\n Ha il nodo UN OWNED_RULE"); k++;
					 * listaElementi.next();
					 * 
					 * }
					 */

					// **** fine sezione di caricamento dei Rule per i preCondition - postCondition - specification

					// **** controllo ed aggiungo tutti i parametri nella lista di reception
					while (this.listStringElement.get(k + 1).equals("ownedParameter")) {
						k++;
						listaElementi.next();
						// verifico che la listaParameter di reception è vuota
						for (int i=0; i < exitActivity.getListReception().size(); i++){
							if (exitActivity.getListReception().get(i).getListParameter().size() == 0) {
								exitActivity.getListReception().get(i).setAddListParameter(new Parameter(this.listNodeMap.get(k)));
	
								// verifico se il parametro ha un lowerValue
								if (this.listStringElement.get(k + 1).equals("lowerValue")) {
									k++;
									listaElementi.next();
									exitActivity.getListReception().get(i).getListParameter().get(0).setLowerValue(new LowerValue(this.listNodeMap.get(k)));
								}
								// verifico se il parametro ha un upperValue
								if (this.listStringElement.get(k + 1).equals("upperValue")) {
									k++;
									listaElementi.next();
									exitActivity.getListReception().get(i).getListParameter().get(0).setUpperValue(new UpperValue(this.listNodeMap.get(k)));
								}
								// verifico se il parametro ha un defaultValue
								if (this.listStringElement.get(k + 1).equals("defaultValue")) {
									k++;
									listaElementi.next();
									exitActivity.getListReception().get(i).getListParameter().get(0).setDefaultValue(new Value(this.listNodeMap.get(k)));
								}
							}
							// in questo caso la lista dei parametri di reception non è vuota
							else {
								exitActivity.getListReception().get(i).setAddListParameter(new Parameter(this.listNodeMap.get(k)));
								int indice = exitActivity.getListReception().get(i).getListParameter().size() - 1;
								// verifico se il parametro ha un lowerValue
								if (this.listStringElement.get(k + 1).equals("lowerValue")) {
									k++;
									listaElementi.next();
	
									exitActivity.getListReception().get(i).getListParameter().get(indice).setLowerValue(new LowerValue(this.listNodeMap.get(k)));
								}
								// verifico se il parametro ha un upperValue
								if (this.listStringElement.get(k + 1).equals("upperValue")) {
									k++;
									listaElementi.next();
									exitActivity.getListReception().get(i).getListParameter().get(indice).setUpperValue(new UpperValue(this.listNodeMap.get(k)));
								}
								// verifico se il parametro ha un defaultValue
								if (this.listStringElement.get(k + 1).equals("defaultValue")) {
									k++;
									listaElementi.next();
									exitActivity.getListReception().get(i).getListParameter().get(indice).setDefaultValue(new Value(this.listNodeMap.get(k)));
								}
							}
						}
					}
					// FINE caricamento parametri nella lista
				}
				
				// VERIFICO SE CI SONO VARIABILI E LE CARICO
				int i = 0;
				while (this.listStringElement.get(k + 1).equals("variable")) {
					k++;
					listaElementi.next();
					exitActivity.setAddListVariable(new Variable(this.listNodeMap.get(k)));
					// verifico se c'è un lowerValue
					if (this.listStringElement.get(k + 1).equals("lowerValue")) {
						k++;
						listaElementi.next();
						exitActivity.getListVariable().get(i).setLowerValue(new LowerValue(this.listNodeMap.get(k)));
					}
					// verifico se c'è un upperValue
					if (this.listStringElement.get(k + 1).equals("upperValue")) {
						k++;
						listaElementi.next();
						exitActivity.getListVariable().get(i).setUpperValue(new UpperValue(this.listNodeMap.get(k)));
					}
					i++;
				}
				// ********** associo i tipi delle variabili alle varibili
				for (int ii = 0; ii < exitActivity.getListVariable().size(); ii++) {
					if (exitActivity.getListVariable().get(ii).getIdTypeVariable() != null) {
						for (int j = 0; j < typeParameterArray.size(); j++) {
							if (exitActivity.getListVariable().get(ii).getIdTypeVariable().equals(typeParameterArray.get(j).getId())) {
								exitActivity.getListVariable().get(ii).setTypeVariable(typeParameterArray.get(j));
							}
						}
						
						// provvedo a controllare la lista dei signal presenti in StateMachine ed associare con il tipo 
						//delle variabili di doActivity
						for (int j = 0; j < this.modello.getStateMachine().getListSignal().size(); j++) {
							if (exitActivity.getListVariable().get(ii).getIdTypeVariable().equals(this.modello.getStateMachine().getListSignal().get(j).getId())) {
								exitActivity.getListVariable().get(ii).setTypeVariableSignal(this.modello.getStateMachine().getListSignal().get(j));
							}
						}
					}
				}

				// ******** FINE dell'associazione delle variabili con i loro tipi

				// aggiungo activity alla transitione corrente
				nStati = this.modello.getStateMachine().getListState().size();
				for (int i0; i < nStati; i++) {
					if (this.modello.getStateMachine().getListState().get(i).getId().equals(idParentState)) {
						this.modello.getStateMachine().getListState().get(i).setExit(exitActivity);
					}
				}
			}
				break;

			/*
			 * 
			 * FINE CARICAMENTO EXIT
			 */

			case "connectionPoint":
				nStati = this.modello.getStateMachine().getListState().size();
				//*** devo ricercare il parent Region di livello piè alto e non quello piè nidificato
				{
					
					NamedNodeMap node = this.listNodeMap.get(k);
					
					String temp[] = null;
					String kindNode = null;
					for (int i = 0; i < node.getLength(); i++) {
						temp = node.item(i).getNodeName().split(" ");
						if (temp[0].equalsIgnoreCase("") && temp.length > 1) {
							temp[0] = temp[1];
						}
						if (temp.length > 0) {
							switch (temp[0]) {
							case "kind":
								kindNode = (node.item(i).getNodeValue());
								break;
							}
						}
					}
					// ************* switch Pseudostate o state di subvertex
					if (kindNode != null){
						if (kindNode.equals("entryPoint") ) {
							for (int i = 0; i < nStati; i++) {
								if (this.modello.getStateMachine().getListState().get(i).getId().equals(idParentState)) {
									this.modello.getStateMachine().getListState().get(i).setEntryPoint(new EntryPoint(this.listNodeMap.get(k)));
									this.modello.getStateMachine().getVectorEntryPoint().add(new EntryPoint(this.listNodeMap.get(k)));
								}
							}
						} else {
							for (int i = 0; i < nStati; i++) {
								if (this.modello.getStateMachine().getListState().get(i).getId().equals(idParentState)) {
									this.modello.getStateMachine().getListState().get(i).setExitPoint(new ExitPoint(this.listNodeMap.get(k)));
									this.modello.getStateMachine().getVectorExitPoint().add(new ExitPoint(this.listNodeMap.get(k)));
								}
							}
						}
					}
				}
				break;
			case "deferrableTrigger":
				// aggiungo alla lista degli stati di Statemachine, all'ultimo
				// stato inserito, alla lista deferrableTrigger
				this.modello.getStateMachine().getListState().get(this.modello.getStateMachine().getListState().size() - 1)
						.setAddDeferrableTrigger(new DeferrableTrigger(this.listNodeMap.get(k)));
				break;
			case "ownedAttribute":

				Attribute attribute = new Attribute(this.listNodeMap.get(k));
				// Trovo il nome del genitore dell'attributo guardo il valore
				// del campo name
				// Controllo se esiste il nodo Value per l'attributo se si lo
				// prelevo e avando di una posizione nella lista
				if (this.listStringElement.get(k + 1).equals("defaultValue")) {
					//System.out.println("\n Ha il nodo value");
					tag = (String) listaElementi.next();
					k++;
					Value value = new Value(this.listNodeMap.get(k));
					attribute.setDefaulValue(value);
				}

				// Verifico che tipo di attributo è: Port - Property - ExtensionEnd
				switch (attribute.getName()) {
				case "Port":
					Port port = new Port(attribute, this.listNodeMap.get(k));
					break;
				case "Property":
					Property property = new Property(attribute, this.listNodeMap.get(k));
					break;
				case "ExtensionEnd":
					ExtensionEnd extensionEnd = new ExtensionEnd(attribute, this.listNodeMap.get(k));
					break;

				}
				// verifico a chi deo aggiungere l'attributo
				switch (parent) {
				case "StateMachine":
					int i = 0;

					while (i < this.modello.getStateMachine().getListSignal().size()) {
						if (this.modello.getStateMachine().getListSignal().get(i).getName() == (this.listNodeMap.get(k - 2).item(0).getNodeValue())) {
							switch (attribute.getType()) {
							case "uml:Port":
								Port port = new Port(attribute, this.listNodeMap.get(k));
								this.modello.getStateMachine().getListSignal().get(i).setAddListAttribute(port);
								i = this.modello.getStateMachine().getListSignal().size();
								break;
							case "uml:Property":
								Property property = new Property(attribute, this.listNodeMap.get(k));
								this.modello.getStateMachine().getListSignal().get(i).setAddListAttribute(property);
								i = this.modello.getStateMachine().getListSignal().size();
								break;
							case "uml:ExtensionEnd":
								ExtensionEnd extensionEnd = new ExtensionEnd(attribute, this.listNodeMap.get(k));
								this.modello.getStateMachine().getListSignal().get(i).setAddListAttribute(extensionEnd);
								i = this.modello.getStateMachine().getListSignal().size();
								break;

							}
						}
						i++;
					}
					break;
				}

				break;
			case "transition":
				Transition transition = new Transition(this.listNodeMap.get(k));
				parentTransition = transition.getId();
				// verifico se c'è un commento alla transition, se si prelevo il
				// nodo, e vado avanti con una posizione
				if (this.listStringElement.get(k + 1).equals("ownedComment")) {
					k++;
					listaElementi.next();
					Comment comment = new Comment(this.listNodeMap.get(k));
					// prelevo il valore del commento nel tag body e lo aggiungo al commento
					if (this.listStringElement.get(k + 1).equals("body")) {
						
						k++;
						listaElementi.next();
						comment.setBody(searchTextInElement(this.listNode.get(k)));

					}
					// aggiungo il commento alla transition
					transition.setComment(comment);
				}

					// verifico se c'è una regola di guardia guard rule
					while (this.listStringElement.get(k + 1).equals("ownedRule")) {
						k++;
						listaElementi.next();
						Rule guardRule = new Rule(this.listNodeMap.get(k));

						// verifico che la rule sia corretta in riferimento alla guardrule di transition con id di rule
						if (transition.getIdGuardRule().equals(guardRule.getId())){
							transition.setGuardRule(guardRule);
						
							// verifico se c'è specification
							if (this.listStringElement.get(k + 1).equals("specification")) {
								k++;
								listaElementi.next();
								Specification specification = new Specification(this.listNodeMap.get(k));
								transition.getGuardRule().setSpecification(specification);
	
								// verifico se c'è un commento
								if (this.listStringElement.get(k + 1).equals("ownedComment")) {
									k++;
									listaElementi.next();
									Comment commentRule = new Comment(this.listNodeMap.get(k));
									// prelevo il valore del commento nel tag body e lo aggiungo al commento
									if (this.listStringElement.get(k + 1).equals("body")) {
										
										k++;
										listaElementi.next();
										commentRule.setBody(searchTextInElement(this.listNode.get(k)));
										transition.getGuardRule().getSpecification().setComment(commentRule);
									}
								}
	
							} else if (this.listStringElement.get(k + 1).equals("ownedComment")) {
								k++;
								listaElementi.next();
								Comment commentRule = new Comment(this.listNodeMap.get(k));
								// prelevo il valore del commento nel tag body e lo aggiungo al commento
								if (this.listStringElement.get(k + 1).equals("body")) {
									
									k++;
									listaElementi.next();
									commentRule.setBody(searchTextInElement(this.listNode.get(k)));
									guardRule.setComment(commentRule);
									transition.setGuardRule(guardRule);
								}
	
								if (this.listStringElement.get(k + 1).equals("specification")) {
									k++;
									listaElementi.next();
									Specification specification = new Specification(this.listNodeMap.get(k));
									transition.getGuardRule().setSpecification(specification);
	
									// verifico se c'è un commento
									if (this.listStringElement.get(k + 1).equals("ownedComment")) {
										k++;
										listaElementi.next();
										Comment commentRules = new Comment(this.listNodeMap.get(k));
										// prelevo il valore del commento nel tag body e lo aggiungo al commento
										if (this.listStringElement.get(k + 1).equals("body")) {
											
											k++;
											listaElementi.next();
											commentRules.setBody(searchTextInElement(this.listNode.get(k)));
											transition.getGuardRule().getSpecification().setComment(commentRules);
										}
									}
	
								}
							}
						}
							// fine della verifica di specification
							
						
					 
				}
				// trovo la regione di appartenza della transizione
				for (int i = 0; i < this.modello.getStateMachine().getListRegion().size(); i++) {
					if (this.modello.getStateMachine().getListRegion().get(i).getId().equals(parentRegion)) {
						this.modello.getStateMachine().getListRegion().get(i).setAddTransition(transition);
					}
				}
				// aggiungo la transition anche alla lista in stateMachine
				this.modello.getStateMachine().setAddTransition(transition);
				break;
			case "effect":

				NamedNodeMap node = this.listNodeMap.get(k);
				String temp[] = null;
				String typeEffect = null;
				for (int i = 0; i < node.getLength(); i++) {
					temp = node.item(i).getNodeName().split("xmi:");
					if (temp[0].equalsIgnoreCase("") && temp.length > 1) {
						temp[0] = temp[1];
					}
					if (temp.length > 0) {
						switch (temp[0]) {
						case "type":
							typeEffect = (node.item(i).getNodeValue());
							break;
						}
					}
				}

				switch (typeEffect) {
				case "uml:Activity":
					Activity activity = new Activity(this.listNodeMap.get(k));
					// ******** verifico se ci sono delle Rule per PreCondition e/o PostCondition ********************

					int cont = 0;
					ArrayList<Rule> ruleArray = new ArrayList<Rule>();
					while (this.listStringElement.get(k + 1).equals("ownedRule")) {
						k++;
						listaElementi.next();
						ruleArray.add(new Rule(this.listNodeMap.get(k)));
						// verifico se c'è specification
						if (this.listStringElement.get(k + 1).equals("specification")) {
							k++;
							listaElementi.next();
							ruleArray.get(cont).setSpecification(new Specification(this.listNodeMap.get(k)));

							// verifico se c'è un commento
							if (this.listStringElement.get(k + 1).equals("ownedComment")) {
								k++;
								listaElementi.next();
								ruleArray.get(cont).getSpecification().setComment(new Comment(this.listNodeMap.get(k)));
								// prelevo il valore del commento nel tag body e lo aggiungo al commento
								if (this.listStringElement.get(k + 1).equals("body")) {
									
									k++;
									listaElementi.next();
									ruleArray.get(cont).getSpecification().getComment().setBody(searchTextInElement(this.listNode.get(k)));
								}
							}
						} else if (this.listStringElement.get(k + 1).equals("ownedComment")) {
							k++;
							listaElementi.next();
							ruleArray.get(cont).setComment(new Comment(this.listNodeMap.get(k)));
							// prelevo il valore del commento nel tag body e lo aggiungo al commento
							if (this.listStringElement.get(k + 1).equals("body")) {
								
								k++;
								listaElementi.next();
								ruleArray.get(cont).getComment().setBody(searchTextInElement(this.listNode.get(k)));
							}

							if (this.listStringElement.get(k + 1).equals("specification")) {
								k++;
								listaElementi.next();
								ruleArray.get(cont).setSpecification(new Specification(this.listNodeMap.get(k)));

								// verifico se c'è un commento
								if (this.listStringElement.get(k + 1).equals("ownedComment")) {
									k++;
									listaElementi.next();
									ruleArray.get(cont).getSpecification().setComment(new Comment(this.listNodeMap.get(k)));
									// prelevo il valore del commento nel tag body e lo aggiungo al commento
									if (this.listStringElement.get(k + 1).equals("body")) {
										
										k++;
										listaElementi.next();
										ruleArray.get(cont).getSpecification().getComment().setBody(searchTextInElement(this.listNode.get(k)));
									}
								}

							}
						}

						cont++;

					}

					// ************** fine caricamento rule per effect -/ activity *********************

					// verifico se ci sono gli Id per il preCondition in  activity
					if (activity.getIdPreCondition() != null && activity.getIdPreCondition().length > 0) {
						for (int i = 0; i < activity.getIdPreCondition().length; i++) {
							for (int j = 0; j < ruleArray.size(); j++) {
								if (activity.getIdPreCondition()[i].equals(ruleArray.get(j).getId())) {
									activity.setAddListPreCondition(ruleArray.get(j));
								}
							}
						}
					}
					// verifico se ci sono gli Id per il postCondition in  activity
					if (activity.getIdPostCondition() != null && activity.getIdPostCondition().length > 0) {
						for (int i = 0; i < activity.getIdPostCondition().length; i++) {
							for (int j = 0; j < ruleArray.size(); j++) {
								if (activity.getIdPostCondition()[i].equals(ruleArray.get(j).getId())) {
									activity.setAddListPostCondition(ruleArray.get(j));
								}
							}
						}
					}
					ruleArray.clear();
					// ********** termine sezione del caricamento delle RULE per activity ****************************

					// ********* verifico se c'è un nestedClassifier (signal)
					// ************
					if (this.listStringElement.get(k + 1).equals("nestedClassifier")) {
						k++;
						listaElementi.next();
						activity.setSignal(new Signal(this.listNodeMap.get(k)));

						// *************** verifico se c'è un nestedClassifier (signal) ********************
						while (this.listStringElement.get(k + 1).equals("ownedAttribute")) {
							k++;
							listaElementi.next();
							Attribute attrib = new Attribute(this.listNodeMap.get(k));
							if (this.listStringElement.get(k + 1).equals("defaultValue")) {
								//System.out.println("\n Ha il nodo value");
								tag = (String) listaElementi.next();
								k++;
								// listaElementi.next();
								Value value = new Value(this.listNodeMap.get(k));
								attrib.setDefaulValue(value);
							}

							// Verifico che tipo di attributo è: Port - Property - ExtensionEnd
							switch (attrib.getType()) {
							case "uml:Port":
								Port port = new Port(attrib, this.listNodeMap.get(k));
								break;
							case "uml:Property":
								Property property = new Property(attrib, this.listNodeMap.get(k));
								break;
							case "uml:ExtensionEnd":
								ExtensionEnd extensionEnd = new ExtensionEnd(attrib, this.listNodeMap.get(k));
								break;

							}

							switch (attrib.getType()) {
							case "uml:Port":
								Port port = new Port(attrib, this.listNodeMap.get(k));
								activity.getSignal().setAddListAttribute(port);
								break;
							case "uml:Property":
								Property property = new Property(attrib, this.listNodeMap.get(k));
								activity.getSignal().setAddListAttribute(property);
								break;
							case "uml:ExtensionEnd":
								ExtensionEnd extensionEnd = new ExtensionEnd(attrib, this.listNodeMap.get(k));
								activity.getSignal().setAddListAttribute(extensionEnd);
								break;
							case "":
								activity.getSignal().setAddListAttribute(attrib);
								break;
							}
						}
					}
					// ******** FINE DELLA SECTION nestedClassifier (signal) x activity

					
					// ****************** verifico se c'è un un operation*****************
					while (this.listStringElement.get(k + 1).equals("ownedOperation")) {
						k++;
						listaElementi.next();
						activity.setAddListOperation(new Operation(this.listNodeMap.get(k)));

						// verifico se c'è un commento
						if (this.listStringElement.get(k + 1).equals("ownedComment")) {
							k++;
							listaElementi.next();
							Comment comment = new Comment(this.listNodeMap.get(k));
							// prelevo il valore del commento nel tag body e lo aggiungo al commento
							if (this.listStringElement.get(k + 1).equals("body")) {
								
								k++;
								listaElementi.next();
								comment.setBody(searchTextInElement(this.listNode.get(k)));
							}
							// verifico che il commento che ho prelevato dal
							// nodo sia il commento dell'operation di activity
								for (int i = 0; i < comment.getAnnotatedElement().length; i++) {
									for (int l=0; l < activity.getListOperation().size(); l++){
										if (comment.getAnnotatedElement()[i].equals(activity.getListOperation().get(l).getId())) {
											activity.getListOperation().get(l).setComment(comment);
										}
									}
								}

						}

						// ****** Verifico e aggiungo i rule per i preCondition postCondition - bodyCondition **********
						ruleArray.clear();
						cont=0;
						while (this.listStringElement.get(k + 1).equals("ownedRule")) {
							k++;
							listaElementi.next();
							ruleArray.add(new Rule(this.listNodeMap.get(k)));
							// verifico se c'è specification
							if (this.listStringElement.get(k + 1).equals("specification")) {
								k++;
								listaElementi.next();
								ruleArray.get(cont).setSpecification(new Specification(this.listNodeMap.get(k)));

								// verifico se c'è un commento
								if (this.listStringElement.get(k + 1).equals("ownedComment")) {
									k++;
									listaElementi.next();
									ruleArray.get(cont).getSpecification().setComment(new Comment(this.listNodeMap.get(k)));
									// prelevo il valore del commento nel tag body e lo aggiungo al commento
									if (this.listStringElement.get(k + 1).equals("body")) {
										
										k++;
										listaElementi.next();
										ruleArray.get(cont).getSpecification().getComment().setBody(searchTextInElement(this.listNode.get(k)));
									}
								}
							} else if (this.listStringElement.get(k + 1).equals("ownedComment")) {
								k++;
								listaElementi.next();
								ruleArray.get(cont).setComment(new Comment(this.listNodeMap.get(k)));
								// prelevo il valore del commento nel tag body e lo aggiungo al commento
								if (this.listStringElement.get(k + 1).equals("body")) {
									
									k++;
									listaElementi.next();
									ruleArray.get(cont).getComment().setBody(searchTextInElement(this.listNode.get(k)));
								}

								if (this.listStringElement.get(k + 1).equals("specification")) {
									k++;
									listaElementi.next();
									ruleArray.get(cont).setSpecification(new Specification(this.listNodeMap.get(k)));

									// verifico se c'è un commento
									if (this.listStringElement.get(k + 1).equals("ownedComment")) {
										k++;
										listaElementi.next();
										ruleArray.get(cont).getSpecification().setComment(new Comment(this.listNodeMap.get(k)));
										// prelevo il valore del commento nel tag body e lo aggiungo al commento
										if (this.listStringElement.get(k + 1).equals("body")) {
											
											k++;
											listaElementi.next();
											ruleArray.get(cont).getSpecification().getComment().setBody(searchTextInElement(this.listNode.get(k)));
										}
									}

								}
							}

							cont++;

						}
						// ****** FINE caricamento dei rule per i preCondition - postCondition - bodyCondition **********

						// verifico se ci sono gli Id per il preCondition di operation in activity
						for (int l=0; l < activity.getListOperation().size(); l++){
							if (activity.getListOperation().get(l).getIdPreCondition() != null && activity.getListOperation().get(l).getIdPreCondition().length > 0) {
								for (int i = 0; i < activity.getListOperation().get(l).getIdPreCondition().length; i++) {
									for (int j = 0; j < ruleArray.size(); j++) {
										if (activity.getListOperation().get(l).getIdPreCondition()[i].equals(ruleArray.get(j).getId())) {
											activity.getListOperation().get(l).setAddListPreCondition(ruleArray.get(j));
										}
									}
								}
							}
						}

						// verifico se ci sono gli Id per il postCondition di operation in activity
						for (int l=0; l < activity.getListOperation().size(); l++){
							if (activity.getListOperation().get(l).getIdPostCondition() != null && activity.getListOperation().get(l).getIdPostCondition().length > 0) {
								for (int i = 0; i < activity.getListOperation().get(l).getIdPostCondition().length; i++) {
									for (int j = 0; j < ruleArray.size(); j++) {
										if (activity.getListOperation().get(l).getIdPostCondition()[i].equals(ruleArray.get(j).getId())) {
											activity.getListOperation().get(l).setAddListPostCondition(ruleArray.get(j));
										}
									}
								}
							}
						}

						// verifico se ci sono gli Id per il bodyCondition di operation in activity
						for (int l=0; l < activity.getListOperation().size(); l++){
							if (activity.getListOperation().get(l).getIdBodyCondition() != null) {
								for (int j = 0; j < ruleArray.size(); j++) {
									if (activity.getListOperation().get(l).getIdBodyCondition().equals(ruleArray.get(j).getId())) {
										activity.getListOperation().get(l).setBodyCondition(ruleArray.get(j));
									}
								}
		
							}
						}
						// / ******** ho finito di caricare i rule********

						// ******** adesso carico tutti i parametri per
						// operation di activity con i loro vari valori presenti
						// *******

						while (this.listStringElement.get(k + 1).equals("ownedParameter")) {
							k++;
							listaElementi.next();
							// verifico che la listaParameter di reception è vuota
							for (int i = 0; i < activity.getListOperation().size(); i++){
								if (activity.getListOperation().get(i).getListParameter().size() == 0) {
									//System.out.println("\n non ci sono elementi nella lista parametri");
									activity.getListOperation().get(i).setAddListParameter(new Parameter(this.listNodeMap.get(k)));
		
									// verifico se il parametro ha un lowerValue
									if (this.listStringElement.get(k + 1).equals("lowerValue")) {
										k++;
										listaElementi.next();
										activity.getListOperation().get(i).getListParameter().get(0).setLowerValue(new LowerValue(this.listNodeMap.get(k)));
									}
									// verifico se il parametro ha un upperValue
									if (this.listStringElement.get(k + 1).equals("upperValue")) {
										k++;
										listaElementi.next();
										activity.getListOperation().get(i).getListParameter().get(0).setUpperValue(new UpperValue(this.listNodeMap.get(k)));
									}
									// verifico se il parametro ha un defaultValue
									if (this.listStringElement.get(k + 1).equals("defaultValue")) {
										k++;
										listaElementi.next();
										activity.getListOperation().get(i).getListParameter().get(0).setDefaultValue(new Value(this.listNodeMap.get(k)));
									}
								}
								// in questo caso la lista dei parametri di OPERATION non è vuota
								else {
									activity.getListOperation().get(i).setAddListParameter(new Parameter(this.listNodeMap.get(k)));
									int indice = activity.getListOperation().get(i).getListParameter().size() - 1;
									// verifico se il parametro ha un lowerValue
									if (this.listStringElement.get(k + 1).equals("lowerValue")) {
										k++;
										listaElementi.next();
		
										activity.getListOperation().get(i).getListParameter().get(indice).setLowerValue(new LowerValue(this.listNodeMap.get(k)));
									}
									// verifico se il parametro ha un upperValue
									if (this.listStringElement.get(k + 1).equals("upperValue")) {
										k++;
										listaElementi.next();
										activity.getListOperation().get(i).getListParameter().get(indice).setUpperValue(new UpperValue(this.listNodeMap.get(k)));
									}
									// verifico se il parametro ha un defaultValue
									if (this.listStringElement.get(k + 1).equals("defaultValue")) {
										k++;
										listaElementi.next();
										activity.getListOperation().get(i).getListParameter().get(indice).setDefaultValue(new Value(this.listNodeMap.get(k)));
									}
								}
							}
						}

						// ******* FINE caricamento dei parametri per operation di activity ********************
					}
					// *********** verifico la presenza di NESTED CLASSIFIER per operation di activity ***************
					ArrayList<TypeParameter> typeParameterArray = new ArrayList<TypeParameter>();
					cont = 0;
					while (this.listStringElement.get(k + 1).equals("nestedClassifier")) {
						k++;
						listaElementi.next();
						typeParameterArray.add(new TypeParameter(this.listNodeMap.get(k)));
						// verifico se c'è un attritbuto per il typeprameter
						int i = 0;
						while (this.listStringElement.get(k + 1).equals("ownedAttribute")) {
							k++;
							listaElementi.next();
							typeParameterArray.get(cont).setAddListAttribute(new Attribute(this.listNodeMap.get(k)));
							// verifico che l'attributo abbia un default value
							if (this.listStringElement.get(k + 1).equals("defaultValue")) {
								k++;
								listaElementi.next();
								typeParameterArray.get(cont).getListAttribute().get(i).setDefaulValue(new Value(this.listNodeMap.get(k)));
							}
							i++;
						}
						// fine verifica per l'attributo del typeParameter
						cont++;
					}

					// *********** FINE caricamento del nested classifier
					// ***********************
					// associo i nested classifier eventualmente trovati con i rispettivi parametri

					for (int i = 0; i < typeParameterArray.size(); i++) {
						for (int l=0; l < activity.getListOperation().size(); l++){
							for (int j = 0; j < activity.getListOperation().get(l).getListParameter().size(); j++) {
								if (activity.getListOperation().get(l).getListParameter().get(j).getIdTypeParameter() != null) {
									if (activity.getListOperation().get(l).getListParameter().get(j).getIdTypeParameter().equals(typeParameterArray.get(i).getId())) {
										activity.getListOperation().get(l).getListParameter().get(j).setTypeParameter(typeParameterArray.get(i));
									}
								}
							}
						}
					}

					// fine dell'associazione dei nested classifier con i rispettivi parametri

					
					// ***** verifico se c'è un un ownedReception x activity
					while (this.listStringElement.get(k + 1).equals("ownedReception")) {
						k++;
						listaElementi.next();
						activity.setAddListReception(new Reception(this.listNodeMap.get(k)));

						/*
						 * // ******** Controllo e aggiungo tutti i Rule che ci
						 * sono: per i preCondition - postCondition -
						 * specification while
						 * (this.listStringElement.get(k+1).equals
						 * ("ownedRule")){
						 * //System.out.println("\n Ha il nodo UN OWNED_RULE");
						 * k++; listaElementi.next();
						 * 
						 * }
						 */

						// **** fine sezione di caricamento dei Rule per i preCondition - postCondition - specification

						// **** controllo ed aggiungo tutti i parametri nella lista di reception
						while (this.listStringElement.get(k + 1).equals("ownedParameter")) {
							k++;
							listaElementi.next();
							// verifico che la listaParameter di reception è vuota
							for (int i=0; i < activity.getListReception().size(); i++){
								if (activity.getListReception().get(i).getListParameter().size() == 0) {
									activity.getListReception().get(i).setAddListParameter(new Parameter(this.listNodeMap.get(k)));
		
									// verifico se il parametro ha un lowerValue
									if (this.listStringElement.get(k + 1).equals("lowerValue")) {
										k++;
										listaElementi.next();
										activity.getListReception().get(i).getListParameter().get(0).setLowerValue(new LowerValue(this.listNodeMap.get(k)));
									}
									// verifico se il parametro ha un upperValue
									if (this.listStringElement.get(k + 1).equals("upperValue")) {
										k++;
										listaElementi.next();
										activity.getListReception().get(i).getListParameter().get(0).setUpperValue(new UpperValue(this.listNodeMap.get(k)));
									}
									// verifico se il parametro ha un defaultValue
									if (this.listStringElement.get(k + 1).equals("defaultValue")) {
										k++;
										listaElementi.next();
										activity.getListReception().get(i).getListParameter().get(0).setDefaultValue(new Value(this.listNodeMap.get(k)));
									}
								}
								// in questo caso la lista dei parametri di reception non è vuota
								else {
									activity.getListReception().get(i).setAddListParameter(new Parameter(this.listNodeMap.get(k)));
									int indice = activity.getListReception().get(i).getListParameter().size() - 1;
									// verifico se il parametro ha un lowerValue
									if (this.listStringElement.get(k + 1).equals("lowerValue")) {
										k++;
										listaElementi.next();
		
										activity.getListReception().get(i).getListParameter().get(indice).setLowerValue(new LowerValue(this.listNodeMap.get(k)));
									}
									// verifico se il parametro ha un upperValue
									if (this.listStringElement.get(k + 1).equals("upperValue")) {
										k++;
										listaElementi.next();
										activity.getListReception().get(i).getListParameter().get(indice).setUpperValue(new UpperValue(this.listNodeMap.get(k)));
									}
									// verifico se il parametro ha un defaultValue
									if (this.listStringElement.get(k + 1).equals("defaultValue")) {
										k++;
										listaElementi.next();
										activity.getListReception().get(i).getListParameter().get(indice).setDefaultValue(new Value(this.listNodeMap.get(k)));
									}
								}
							}
						}
						// FINE caricamento parametri nella lista
					}
					
					
					// VERIFICO SE CI SONO VARIABILI E LE CARICO
					int i = 0;
					while (this.listStringElement.get(k + 1).equals("variable")) {
						k++;
						listaElementi.next();
						activity.setAddListVariable(new Variable(this.listNodeMap.get(k)));
						
						// poichè il tipo delle variabili può anche essere definito dall'utente o utilizzare i tipi primitivi dei defaul di papyrus, verifico 
						// se utilizza href come impostazione del tipo
						if (this.listStringElement.get(k+1).equals("type")){
							this.modello.getStateMachine().setAddVectorVariable(new Variable(this.listNodeMap.get(k), this.listNodeMap.get(k+1)));
							k++;
							listaElementi.next();
						}else{
							this.modello.getStateMachine().setAddVectorVariable(new Variable(this.listNodeMap.get(k)));
						}
						// verifico se c'è un lowerValue
						if (this.listStringElement.get(k + 1).equals("lowerValue")) {
							k++;
							listaElementi.next();
							activity.getListVariable().get(i).setLowerValue(new LowerValue(this.listNodeMap.get(k)));
						}
						// verifico se c'è un upperValue
						if (this.listStringElement.get(k + 1).equals("upperValue")) {
							k++;
							listaElementi.next();
							activity.getListVariable().get(i).setUpperValue(new UpperValue(this.listNodeMap.get(k)));
						}
						i++;
					}
					// ********** associo i tipi delle variabili alle varibili
					for (int ii = 0; ii < activity.getListVariable().size(); ii++) {
						if (activity.getListVariable().get(ii).getIdTypeVariable() != null) {
							for (int j = 0; j < typeParameterArray.size(); j++) {
								if (activity.getListVariable().get(ii).getIdTypeVariable().equals(typeParameterArray.get(j).getId())) {
									activity.getListVariable().get(ii).setTypeVariable(typeParameterArray.get(j));
								}
							}
							
							// provvedo a controllare la lista dei signal presenti in StateMachine ed associare con il tipo 
							//delle variabili di doActivity
							for (int j = 0; j < this.modello.getStateMachine().getListSignal().size(); j++) {
								if (activity.getListVariable().get(ii).getIdTypeVariable().equals(this.modello.getStateMachine().getListSignal().get(j).getId())) {
									activity.getListVariable().get(ii).setTypeVariableSignal(this.modello.getStateMachine().getListSignal().get(j));
								}
							}
						}
					}

					// ******** FINE dell'associazione delle variabili con i loro tipi

					// aggiungo activity alla transitione corrente
					nRegioni = this.modello.getStateMachine().getListRegion().size();
					int nTransizione = 0;
					for (int jj = 0; jj < nRegioni; jj++) {
						nTransizione = this.modello.getStateMachine().getListRegion().get(jj).getListTransition().size();
						for (int l = 0; l < nTransizione; l++) {
							if (this.modello.getStateMachine().getListRegion().get(jj).getListTransition().get(l).getId().equals(parentTransition)) {
								this.modello.getStateMachine().getListRegion().get(jj).getListTransition().get(l).setEffect(activity);
							}
						}
					}
					break;
				case "uml:FunctionBehavior":
					break;
				case "uml:Interaction":
					break;
				case "uml:ProtocolStateMachine":
					break;
				case "uml:OpaqueBehavior":
					break;
				case "uml:StateMachine":
					break;

				}
				break;
			case "trigger":
				nRegioni = this.modello.getStateMachine().getListRegion().size();
				{
					for (int i = 0; i < nRegioni; i++) {
						nTransizioni = this.modello.getStateMachine().getListRegion().get(i).getListTransition().size();
						for (int j = 0; j < nTransizioni; j++) {
							if (this.modello.getStateMachine().getListRegion().get(i).getListTransition().get(j).getId().equals(parentTransition)) {
								this.modello.getStateMachine().getListRegion().get(i).getListTransition().get(j)
										.setAddTrigger(new Trigger(this.listNodeMap.get(k)));
							}
						}
					}
				}
				break;
			
			}

			if (this.listNodeMap.get(k) != null && this.listNodeMap.get(k).getLength() > 0) {

				for (int index = 0; index < this.listNodeMap.get(k).getLength(); index++) {

				}
			}
			
			k++;
			

		}
		
		// per ogni deferrableTrigger di ogni stato della lista listState di modello verifico se ci sono dei deferrableTrigger
		// se si lo ricerco nelle varie liste degli eventi e lo associo al deferrableTrigger mediante associazione
		nStati = this.modello.getStateMachine().getListState().size();
		for (int i = 0; i < nStati; i++){
				for (int l=0; l < this.modello.getStateMachine().getListState().get(i).getListDeferrableTrigger().size(); l++ ){
					// AnyReceiveEvent
					for (int j=0; j < this.modello.getListAnyReceiveEvent().size(); j++){
						if (this.modello.getStateMachine().getListState().get(i).getListDeferrableTrigger().get(l).getIdEvent().equals( this.modello.getListAnyReceiveEvent().get(j).getId())){
							this.modello.getStateMachine().getListState().get(i).getListDeferrableTrigger().get(l).setEvent(this.modello.getListAnyReceiveEvent().get(j));
							j=this.modello.getListAnyReceiveEvent().size();
						}
					}
					// CallEvent
					for (int j=0; j < this.modello.getListCallEvent().size(); j++){
						if (this.modello.getStateMachine().getListState().get(i).getListDeferrableTrigger().get(l).getIdEvent().equals( this.modello.getListCallEvent().get(j).getId())){
							this.modello.getStateMachine().getListState().get(i).getListDeferrableTrigger().get(l).setEvent(this.modello.getListCallEvent().get(j));
							j=this.modello.getListCallEvent().size();
						}
					}
					
					// ChangeEvent
					for (int j=0; j < this.modello.getListChangeEvent().size(); j++){
						if (this.modello.getStateMachine().getListState().get(i).getListDeferrableTrigger().get(l).getIdEvent().equals( this.modello.getListChangeEvent().get(j).getId())){
							this.modello.getStateMachine().getListState().get(i).getListDeferrableTrigger().get(l).setEvent(this.modello.getListChangeEvent().get(j));
							j=this.modello.getListChangeEvent().size();
						}
					}
					// SignalEvent
					for (int j=0; j < this.modello.getListSignalEvent().size(); j++){
						if (this.modello.getStateMachine().getListState().get(i).getListDeferrableTrigger().get(l).getIdEvent().equals( this.modello.getListSignalEvent().get(j).getId())){
							this.modello.getStateMachine().getListState().get(i).getListDeferrableTrigger().get(l).setEvent(this.modello.getListSignalEvent().get(j));
							j=this.modello.getListSignalEvent().size();
						}
					}
					// TimeEvent
					for (int j=0; j < this.modello.getListTimeEvent().size(); j++){
						if (this.modello.getStateMachine().getListState().get(i).getListDeferrableTrigger().get(l).getIdEvent().equals( this.modello.getListTimeEvent().get(j).getId())){
							this.modello.getStateMachine().getListState().get(i).getListDeferrableTrigger().get(l).setEvent(this.modello.getListTimeEvent().get(j));
							j=this.modello.getListTimeEvent().size();
						}
					}
				}
		}
		
		// per ogni trigger di ogni transizione di ogni region del modello verifico se ci sono dei Trigger
		// se si lo ricerco nelle varie liste degli eventi e lo associo al Trigger mediante associazione
		nRegioni = this.modello.getStateMachine().getListRegion().size();
		int index=0;
		boolean trovato= false;
		for (int i=0; i < nRegioni; i++){
			for (int j=0; j < this.modello.getStateMachine().getListRegion().get(i).getListTransition().size(); j++){
				for (int l=0; l < this.modello.getStateMachine().getListRegion().get(i).getListTransition().get(j).getListTrigger().size(); l++){
					
					// AnyReceiveEvent
					for (int z=0; z < this.modello.getListAnyReceiveEvent().size(); z++){
						if (this.modello.getStateMachine().getListRegion().get(i).getListTransition().get(j).getListTrigger().get(l).getIdEvent().equals(
								this.modello.getListAnyReceiveEvent().get(z).getId())){
							this.modello.getStateMachine().getListRegion().get(i).getListTransition().get(j).getListTrigger().get(l).setEvent(this.modello.getListAnyReceiveEvent().get(z));					
							trovato = true;
							index = z;}
					}
					if (trovato){
						this.modello.getListAnyReceiveEvent().remove(index);
						trovato = false;
					}
					// CallEvent
					for (int z=0; z < this.modello.getListCallEvent().size(); z++){
						if (this.modello.getStateMachine().getListRegion().get(i).getListTransition().get(j).getListTrigger().get(l).getIdEvent().equals(
								this.modello.getListCallEvent().get(z).getId())){
							this.modello.getStateMachine().getListRegion().get(i).getListTransition().get(j).getListTrigger().get(l).setEvent(this.modello.getListCallEvent().get(z));					
							trovato = true;
							index = z;}
					}
					if (trovato){
						this.modello.getListCallEvent().remove(index);
						trovato = false;
					}
					
					// ChangeEvent
					for (int z=0; z < this.modello.getListChangeEvent().size(); z++){
						if (this.modello.getStateMachine().getListRegion().get(i).getListTransition().get(j).getListTrigger().get(l).getIdEvent().equals(
								this.modello.getListChangeEvent().get(z).getId())){
							this.modello.getStateMachine().getListRegion().get(i).getListTransition().get(j).getListTrigger().get(l).setEvent(this.modello.getListChangeEvent().get(z));					
							trovato = true;
							index = z;}
					}
					if (trovato){
						this.modello.getListChangeEvent().remove(index);
						trovato = false;
					}
					
					// SignalEvent
					for (int z=0; z < this.modello.getListSignalEvent().size(); z++){
						if (this.modello.getStateMachine().getListRegion().get(i).getListTransition().get(j).getListTrigger().get(l).getIdEvent().equals(
								this.modello.getListSignalEvent().get(z).getId())){
							this.modello.getStateMachine().getListRegion().get(i).getListTransition().get(j).getListTrigger().get(l).setEvent(this.modello.getListSignalEvent().get(z));					
							trovato = true;
							index = z;}
					}
					if (trovato){
						this.modello.getListSignalEvent().remove(index);
						trovato = false;
					}
					
					
					// TimeEvent
					for (int z=0; z < this.modello.getListTimeEvent().size(); z++){
						if (this.modello.getStateMachine().getListRegion().get(i).getListTransition().get(j).getListTrigger().get(l).getIdEvent().equals(
								this.modello.getListTimeEvent().get(z).getId())){
							this.modello.getStateMachine().getListRegion().get(i).getListTransition().get(j).getListTrigger().get(l).setEvent(this.modello.getListTimeEvent().get(z));					
							trovato = true;
							index = z;}
					}
					if (trovato){
						this.modello.getListTimeEvent().remove(index);
						trovato = false;
					}
				}
			}
		}

	}

	private void StampaContenuto() {
		ScriviSuFile scrivi = new ScriviSuFile("ContenutoModello.txt");
		// stampo la lista delle regioni
		for (int i = 0; i < this.modello.getStateMachine().getListRegion().size(); i++) {
			scrivi.scrivi("\n" + this.modello.getStateMachine().getListRegion().get(i).getId() + " - "
					+ this.modello.getStateMachine().getListRegion().get(i).getName());
		}
		
		
	}
	
	/**
	 * Metodo che mi permette di trovare lo stato di livello superiore rispetto a quello che ho incontrato in fase di parsing
	 * Restituisce l'id dello stato di livello immediatamente sopra
	 * @param idRegion Contiene l'id della regione da ricercare
	 * @return stateLevelUp Restituisce l'id dello stato di livello immediatamente sopra
	 */
	public String getIdStateLevelUp(String idRegion){
		int index = 0;
		String stateLevelUp = "";
		int i =0;
		for ( i=0; i < this.modello.getStateMachine().getListState().size(); i++){
			// ci possono essere piè regioni in uno stato per cui bisogna controllare l'elenco degli id delle regioni
			int j=0;
			for ( j=0; j < this.modello.getStateMachine().getListState().get(i).getListIdRegion().size(); j++){
				
					if (this.modello.getStateMachine().getListState().get(i).getListIdRegion().get(j).equals(idRegion)){
						stateLevelUp = this.modello.getStateMachine().getListState().get(i).getId();
					}
			}
			
		}
		return stateLevelUp;
				
	}
	/**
	 * Verifico quale transizione parte da un initialState, se positivo, viene inserito nel campo nameStateSource il valore di DEFAULT
	 * in questo modo sappiamo che lo stateTarget della transizione parte di default. Questo ci è molto utile per costruire lo SFC.
	 * 
	 * Altra operazione che esegue e quella di caricare le transizioni in uscita ed in entrata per ogni stato:
	 * 	-listTransitionIn
	 *  -listTransitionOut
	 */
	private void checkVectorTransitions(){
		stampaSystemOutTxtArea("\n->Eseguo un check sulle transizioni individuate");
		for(Initial itemInitialPoint : this.modello.getStateMachine().getListInitial()){
			for( Transitions itemTransitions : this.vectorTransitions){
				if(itemInitialPoint.getId().equals(itemTransitions.getIdStateSource())){
					// devo verificare se la transizione che parte dall'initialPoint arriva ad un choice, se si devo mettere come
					// source alle transizioni che escono dal choice l'id dello stato che contiene l'initialPoint
					// se non arriva al choice la transizione allora lo metto come DEFAULT
					if (existTrxStartFromInitialPointToChoice(itemInitialPoint.getId())){
						
					}else{
						itemTransitions.setNameStateSource("DEFAULT");
					}
				}
			}
		}
		/*
		 * Aggiungo in listTransitionOut le transizioni in uscita dallo stato preso in esame
		 */
		for (int i=0; i < this.arrayStateLevel.length; i++){
			for (int j=0; j < this.arrayStateLevel[i].length; j++){
				if (this.arrayStateLevel[i][j] != null){
					for(Transitions itemTransitions: this.vectorTransitions){
						if (this.arrayStateLevel[i][j].getIdStato().equals(itemTransitions.getIdStateSource())){
							this.arrayStateLevel[i][j].setAddListTransitionOut(String.valueOf(itemTransitions.getNumberTransition()));
						}
					}
				}
			}
		}
		/*
		 * Aggiungo in listTransitionIn le transizioni in entrata per ogni stato preso in esame
		 */
		for (int i=0; i < this.arrayStateLevel.length; i++){
			for (int j=0; j < this.arrayStateLevel[i].length; j++){
				if (this.arrayStateLevel[i][j] != null){
					for(Transitions itemTransitions: this.vectorTransitions){
						if (this.arrayStateLevel[i][j].getIdStato().equals(itemTransitions.getIdStateTarget())){
							this.arrayStateLevel[i][j].setAddListTransitionIn(String.valueOf(itemTransitions.getNumberTransition()));
						}
					}
				}
			}
		}
	}
	
	/**
	 * Devo verificare se la transizione che parte dall'initialPoint arriva ad un CHOICE, se si devo mettere come
	 * source alle transizioni che escono dal CHOICE l'id dello stato che contiene l'initialPoint
	 * @param idInitialPoint Contiene l'id di initialPoint da ricercare
	 * @return ris Valore vooleano
	 */
	private boolean existTrxStartFromInitialPointToChoice(String idInitialPoint){
		boolean ris = false;
		boolean choiceFound = false;
		String idStateFound = "-1";
		String idChoice = "";
		for (Choice itemChoice : this.getModello().getStateMachine().getVectorChoice()){
			idChoice = itemChoice.getId();
			for (Transitions itemTransitions : this.vectorTransitions){
				if (itemTransitions.getIdStateSource().equals(idInitialPoint) &&
					itemTransitions.getIdStateTarget().equals(idChoice)){
				// in caso affermativo restituisco TRUE ed imposto il valore di idSource delle transizione che escono dal CHOICE,
				// il valore dell'idState di appartenenza dell'initialPOint
					idStateFound=searchIdStateFromIdInitalPoint(idInitialPoint);
					
					choiceFound = true;
					ris = true;
				}
			}
			if (choiceFound){
				for (Transitions itemTransitions : this.vectorTransitions){
					if (itemTransitions.getIdStateSource().equals(idChoice)){
						itemTransitions.setIdStateSource(idStateFound);
						itemTransitions.setIdChoiceSource(idChoice); // conservo il valore dell'idChoice per fare la condizione
					}
				}
			}
			choiceFound = false;
			idStateFound = "-1";
		}
		return ris;
	}
	/**
	 * Cerca lo stato che sarà ad avviare a partire da un initialPoint
	 * @param idInitialPoint Contiene l'id 
	 * @return ris Valore booleano
	 */
	private String searchIdStateFromIdInitalPoint(String idInitialPoint){
		String ris ="";
		for(Region itemRegion : this.modello.getStateMachine().getListRegion()){
			if (itemRegion.getInitialPseudoState() != null){
				if (itemRegion.getInitialPseudoState().getId().equals(idInitialPoint))
					ris = itemRegion.getIdStateAppartenenza();
			}
		}
		return ris;
	}
	/**
	 * Imposta il tipo di Pou
	 * @param value Valore del tipo di pou
	 */
	public void setTypePou(String value){
		this.typePou = value;
	}
	/**
	 * Restituisce il tipo di pou
	 * @return typePou tipo di Pou
	 */
	public String getTypePou(){
		return this.typePou;
	}
	/**
	 * Imposta un oggetto GlobalVar
	 * @param value Oggetto globalVar
	 */
	public void setGlobalVar(GlobalVar value){
		this.globalVar = value;
	}
	/**
	 * Restituisce un oggetto globalVar
	 * @return globalVar
	 */
	public GlobalVar getGlobalVar(){
		return this.globalVar;
	}
	/**
	 * Imposta il numero degli stati per livello
	 * @param value Contiene il valore
	 */
	public void setNumberStateLevels(int value){
		this.numberStateLevels = value;
	}
	/**
	 * Restituisce il numero di stati per livello
	 * @return numberStateLevels Contiene il valore
	 */
	public int getNumberStateLevels(){
		return this.numberStateLevels;
	}
	/**
	 * Imposta il valore di STATE MANAGER
	 * @param value Contiene il valore
	 */
	public void setStateManagerActionValue(String value){
		this.stateManagerActionValue = value;
	}
	/**
	 * Restituisce il valore di STATE MANAGER
	 * @return stateManagerActionValue Contiene il valore
	 */
	public String getStateManagerActionValue(){
		return this.stateManagerActionValue;
	}
	/**
	 * Contiene il valore di INIT
	 * @param value Contiene il valore
	 */
	public void setInitActionValue(String value){
		this.initActionValue = value;
	}
	/**
	 * Restituisce il valore di INIT
	 * @return initActionValue Contiene il valore
	 */
	public String getInitActionValue(){
		return this.initActionValue;
	}
	/**
	 * Set la variabile per conterra i dati da utilizzare per la costruzione del file xml
	 * @param value Contiene il valore
	 */
	public void setGestioneAnalisiModello(GestioneAnalisiModello value){
		this.gestioneModelloObj = value;
	}
	/**
	 * Restituisce l'oggetto che contiene tutti i dati per costruire il file xml
	 * @return gestioneModelloObj Contiene l'oggetto 
	 */
	public GestioneAnalisiModello getGestioneAnalisiModello(){
		return this.gestioneModelloObj;
	}
	/**
	 * Restituisce l'array di StateLevel utile per costruire l'SFC, dove gli stati sono separati per livello
	 * @return arrayStateLevel Restituisce la lista degli stati
	 */
	public StateLevel[][] getArrayStateLevel(){
		return this.arrayStateLevel;
	}
	/**
	 * Restituisce il vettore che contiene tutte le transizioni del modello, utile pr costruire lo SFC
	 * @return vectorTransitions Vettore delle transizioni
	 */
	public Vector<Transitions> getVectorTransitions(){
		return this.vectorTransitions;
	}
	
	/**
	 * Per ogni transizione di vectorTransitions, devo verificare se è di tipo DEFAULT ovvero parte da un initialPoint,
	 * se si, devo cambiare il suo idSource con l'idSource dello stato che contiene il relativo initialPoint
	 */
	private void checkIdSourceDefaultFromVectorTransitions(){
		stampaSystemOutTxtArea("\n->Cerco le transizioni che partono da un initialPoint e le trasformo in DEFAULT");
		for(int j=0; j < this.vectorTransitions.size(); j++){
			if (this.vectorTransitions.get(j).getNameStateSource().equals("DEFAULT")){
				String id = this.vectorTransitions.get(j).getIdStateSource();
				String newIdSource = "";
				for (int i = 0; i < this.modello.getStateMachine().getListRegion().size(); i++){
					if (this.modello.getStateMachine().getListRegion().get(i).getInitialPseudoState() != null){
						
						if (this.modello.getStateMachine().getListRegion().get(i).getInitialPseudoState().getId().equals(id)){
							
							this.vectorTransitions.get(j).setIdStateSource(this.modello.getStateMachine().getListRegion().get(i).getIdStateAppartenenza());
							stampaSystemOutTxtArea("\n\t->Trovato id, new id " +
									this.modello.getStateMachine().getListRegion().get(i).getInitialPseudoState().getId() + "  "+
									this.modello.getStateMachine().getListRegion().get(i).getIdStateAppartenenza());
							
							
							i = this.modello.getStateMachine().getListRegion().size();
							
						}
					}
				}
			}
		}
	}
	/**
	 * Verifico per ogni transizione di vectorTransitions se ci sono delle partenze o degli arrivi in elementi di tipo CHOICE
	 * del modello UML. 
	 * Se SI imposto a TRUE il valore del campo choice
	 */
	private void checkTransitionFromToChoiceElement(){
		stampaSystemOutTxtArea("\n->Eseguo un check sulle transizioni in cerca di quelle che partono o arrivano in un elemento CHOICE");
		for (Transitions itemTransition : this.vectorTransitions){
			for(Choice itemChoice :  this.modello.getStateMachine().getVectorChoice()){
				if (itemTransition.getNameStateSource() != "DEFAULT"){
					if (	itemTransition.getIdStateSource().equals(itemChoice.getId()) || 
							itemTransition.getIdStateTarget().equals(itemChoice.getId())){
						itemTransition.setChoice(true);
					}
					// la transizione parte dal choice
					if (itemTransition.getIdStateSource().equals(itemChoice.getId())){
						itemTransition.setChoiceStart(true);
						itemTransition.setIdChoiceSource(itemChoice.getId());
					}
					// la transizione arriva al choice
					if (itemTransition.getIdStateTarget().equals(itemChoice.getId())){
						itemTransition.setChoiceEnd(true);
					}
				}
			}
		}
	}
	
	/**
	 * Verifico per ogni transizione di vectorTransitions se ci sono delle partenze o degli arrivi in elementi di tipo FORK
	 * del modello UML. 
	 * Se SI imposto a TRUE il valore del campo fork
	 */
	private void checkTransitionFromToForkElement(){
		stampaSystemOutTxtArea("\n->Eseguo un check sulle transizioni in cerca di quelle che partono o arrivano in un elemento FORK");
		for (Transitions itemTransition : this.vectorTransitions){
			for(Fork itemFork :  this.modello.getStateMachine().getVectorFork()){
				if (itemTransition.getNameStateSource() != "DEFAULT"){
					if (	itemTransition.getIdStateSource().equals(itemFork.getId()) || 
							itemTransition.getIdStateTarget().equals(itemFork.getId())){
						itemTransition.setFork(true);
					}
					// la transizione parte dal fork
					if (itemTransition.getIdStateSource().equals(itemFork.getId())){
						itemTransition.setForkStart(true);
					}
					// la transizione arriva al fork
					if (itemTransition.getIdStateTarget().equals(itemFork.getId())){
						itemTransition.setForkEnd(true);
					}
				}
			}
		}
	}
	
	/**
	 * Verifico per ogni transizione di vectorTransitions se ci sono delle partenze o degli arrivi in elementi di tipo JOIN
	 * del modello UML. 
	 * Se SI imposto a TRUE il valore del campo join
	 */
	private void checkTransitionFromToJoinElement(){
		stampaSystemOutTxtArea("\n->Eseguo un check sulle transizioni in cerca di quelle che partono o arrivano in un elemento JOIN");
		for (Transitions itemTransition : this.vectorTransitions){
			for(Join itemJoin :  this.modello.getStateMachine().getVectorJoin()){
				if (itemTransition.getNameStateSource() != "DEFAULT"){
					if (	itemTransition.getIdStateSource().equals(itemJoin.getId()) || 
							itemTransition.getIdStateTarget().equals(itemJoin.getId())){
						itemTransition.setJoin(true);
					}
					// la transizione parte dal Join
					if (itemTransition.getIdStateSource().equals(itemJoin.getId())){
						itemTransition.setJoinStart(true);
					}
					// la transizione arriva al Join
					if (itemTransition.getIdStateTarget().equals(itemJoin.getId())){
						itemTransition.setJoinEnd(true);
					}
				}
			}
		}
	}
	
	/**
	 * Cerca il primo stato di avvio e imposta il valore della variabile statoDiAvvio= TRUE.
	 * Per cercarlo, listo tutti gli inizialPoint e controllo il valore di initialPointDiAvvio se TRUE, prendo il suo ID
	 * e lo cerco in listTransitions di modello, in questo modo trovo l'ide della transizione, e dopo cerco il destinatario della transizione 
	 */
	private void searchStatoDiAvvio(){
		stampaSystemOutTxtArea("\n->Cerco lo stato di avvio della State Machine");
		String idInitialPointDiAvvio = "";
		String idTransizione = "";
		String idStato = "";
		// cerco l'id dell'initialPoint di avvio
		for(Initial item : this.modello.getStateMachine().getListInitial()){
			if (item.getInitialPointDiAvvio()){
				idInitialPointDiAvvio = item.getId();
				stampaSystemOutTxtArea("\n->Trovato id initialPointDiAvvio = " + idInitialPointDiAvvio);
			}
		}
		
		// cerco la transizione che parte dall'initialPointDiAvvio
		for(Transition item : this.modello.getStateMachine().getListTransition()){
			if (item.getSource().equals(idInitialPointDiAvvio)){
				idTransizione = item.getId();
				idStato = item.getTarget();
				stampaSystemOutTxtArea("\n->Trovato id transizione di avvio  = " + idTransizione);
				stampaSystemOutTxtArea("\n->Trovato id stato di avvio  = " + idStato);
			}
		}
		
		// imposto a TRUE il valore di statoDiAvvio per lo stato cha ha come id = idStato
		for (int i = 0; i < this.arrayStateLevel.length; i++){
			for(int j = 0; j < this.arrayStateLevel[i].length; j++){
				if (this.arrayStateLevel[i][j] != null){
					if (this.arrayStateLevel[i][j].getIdStato().equals(idStato)){
						this.arrayStateLevel[i][j].setStatoDiAvvio(true);
					}
				}
			}
		}	
	}
	/**
	 * Aggiunge un elemento alla listaGrafo 
	 * @param stato
	 */
	private void setAddGrafoStati(StateLevel stato){
		this.grafoStati.add(stato);
	}
	/**
	 * Restituisce la lista GrafoStati
	 * @return grafoStati
	 */
	public ArrayList<StateLevel> getGrafoStati(){
		return this.grafoStati;
	}
	/**
	 * Inserisco i valori di newNameStateSource e di newNameStateTarget a vectorTransitions
	 */
	private void insertNewNameStateSourceTarget(){
		String newNameSource = "";
		String newNameTarget = "";
		for (int i=0; i < this.vectorTransitions.size(); i++){
			newNameSource = searchNewNameState(this.vectorTransitions.get(i).getIdStateSource());
			newNameTarget = searchNewNameState(this.vectorTransitions.get(i).getIdStateTarget());
			this.vectorTransitions.get(i).setNewNameStateSource(newNameSource);
			this.vectorTransitions.get(i).setNewNameStateTarget(newNameTarget);
		}
	}
	/**
	 * Restituisce lo stato dato in input il suo ID
	 * @param idState Id dello stato
	 * @return state Restituisce lo stato
	 */
	private StateLevel searchState(String idState){
		StateLevel state = new StateLevel();
		for(int i = 0; i < this.arrayStateLevel.length; i++){
			for (int j = 0; j < this.arrayStateLevel[i].length; j++){
				if (this.arrayStateLevel[i][j] != null){
					if (this.arrayStateLevel[i][j].getIdStato().equals(idState)){
						state = this.arrayStateLevel[i][j];
					}
				}
			}
		}
		return state;
	}
	/**
	 * Restituisce il newNameState dato in input il suo ID
	 * @param idState Id dello stato
	 * @return newName Restituisce il nuovo nome dello stato
	 */
	private String searchNewNameState(String idState){
		String newName = "";
		for(int i = 0; i < this.arrayStateLevel.length; i++){
			for (int j = 0; j < this.arrayStateLevel[i].length; j++){
				if (this.arrayStateLevel[i][j] != null){
					if (this.arrayStateLevel[i][j].getIdStato().equals(idState)){
					newName = this.arrayStateLevel[i][j].getNewName();
					}
				}
			}
		}
		return newName;
	}
	/**
	 * Crea la lista di adiacenza per gli stati, in questo modo posso fare una visita DFS al grafo che si crea;
	 */
	private void creaListaDiAdiacenza(){
		stampaSystemOutTxtArea("\n->Creazione del grafo");
		StateLevel stato;
		// cerco lo stato iniziale
		for (int i = 0; i < this.arrayStateLevel.length; i++){
			for(int j = 0; j < this.arrayStateLevel[i].length; j++){
				if (this.arrayStateLevel[i][j] != null){
					if (this.arrayStateLevel[i][j].getStatoDiAvvio()){
						stato = new StateLevel();
						stato = this.arrayStateLevel[i][j];
						this.arrayStateLevel[i][j].setAggiuntoAlGrafo(true);
						stato = creaListaAdiacentiPerLoStato(this.arrayStateLevel[i][j]);
						this.arrayStateLevel[i][j] = stato;
						this.setAddGrafoStati(stato);
						stampaSystemOutTxtArea("\n\t->Aggiunto al grafo il nodo iniziale : " + stato.getNewName());
					}
				}
			}
		}
		
		// aggiungo gli altri stati tranne quello iniziale
		for (int i = 0; i < this.arrayStateLevel.length; i++){
			for(int j = 0; j < this.arrayStateLevel[i].length; j++){
				if (this.arrayStateLevel[i][j] != null){
					if (!this.arrayStateLevel[i][j].getStatoDiAvvio() && !this.arrayStateLevel[i][j].getAggiuntoAlGrafo()){
						this.arrayStateLevel[i][j].setAggiuntoAlGrafo(true);
						stato = creaListaAdiacentiPerLoStato(arrayStateLevel[i][j]);
						this.arrayStateLevel[i][j] = stato;
						this.setAddGrafoStati(stato);
					}
				}
			}
		}
		
		//stampo la lista di adiacenza
		
		for (StateLevel itemState : this.grafoStati){
			stampaSystemOutTxtArea("\n->Stato: " + itemState.getNewName() + " - " );
			System.out.println();
			if (itemState.getListaAdiacenti().size() > 0){
				for (String itemAdiacenti : itemState.getListaAdiacenti()){
					stampaSystemOutTxtArea("\n\t->|--->" + itemAdiacenti);
					
				}
			}
		}
	}
	/**
	 * Crea la lista degli stati adiacenti per ogni stato che gli viene passato
	 * @param state Stato da analizzare
	 * @return state Stato analizzato
	 */
	public StateLevel creaListaAdiacentiPerLoStato(StateLevel state){
		stampaSystemOutTxtArea("\n->Creo lista dei nodi adiacenti per lo Stato/Nodo: " + state.getNomeStato() );
		String nameState = "";
		for (String nrTrxOut : state.getListTransitionOut()){
			nameState=this.vectorTransitions.get(Integer.parseInt(nrTrxOut)-1).getNewNameStateTarget();
			state.setAddListaAdiacenti(nameState);
		}
		return state;
	}
	
	/**
	 * Scrive su file
	 */
	private void scriviVectorTransitions(){
	//	ScriviSuFile miofile = new ScriviSuFile("vector_Transitions_All.txt");
		String testo = "";
		for (Transitions itemTrx : this.vectorTransitions){
			
			testo  = "***************\nTrx nr. " + itemTrx.getNumberTransition();
			testo = testo + "\n Nome Source: " + itemTrx.getNameStateSource();
			testo = testo + "\n New Nome Source : " + itemTrx.getNewNameStateSource();
			testo = testo + "\n Id State Source : "+ itemTrx.getIdStateSource();
			testo = testo + "\n Nome Target:" + itemTrx.getNameStateTarget();
			testo = testo + "\n New Nome Target : " + itemTrx.getNewNameStateTarget();
			testo = testo + "\n Id State Target : "+ itemTrx.getIdStateTarget();
	//		miofile.scrivi(testo);
		}
	}
	
	


	
	/**
	 * Leggo tutti gli in initialPoint, e aggiungo le transizioni di DEFAULT ai rispettivi stati
	 */
	private void insertTransitionDefaultToListTransitionOut(){
		stampaSystemOutTxtArea("\n->Lettura di tutti gli initialPoint e aggiungo le transizioni di DEFAULT ai rispettivi stati");
		String idInitial = "";
		String idStato = "";
		String idTransizione ="";
		Initial initial = new Initial();
		Region region = new Region();
		Transition transition = new Transition();
		
		for (int i = 0; i < this.modello.getStateMachine().getListInitial().size(); i++){
			initial = this.modello.getStateMachine().getListInitial().get(i);
			// se non è l'initialPoint di avvio
			if (!initial.getInitialPointDiAvvio()){
				idInitial = initial.getId();
				for (int j=0; j < this.modello.getStateMachine().getListRegion().size(); j++){
					region = this.modello.getStateMachine().getListRegion().get(j);
					if (region.getInitialPseudoState() != null){
						if (idInitial.equals(region.getInitialPseudoState().getId())){
							idStato = region.getIdStateAppartenenza();
						}
					}
				}
				// cerco la transizione che parte da initialPoint
				for (int k=0; k < this.modello.getStateMachine().getListTransition().size(); k++){
					transition = this.modello.getStateMachine().getListTransition().get(k);
					if (transition.getSource().equals(idInitial)){
						idTransizione = transition.getId();
					}
				}
				
				// cerco la transition in VectorTransitions
				for (int ii=0; ii < this.vectorTransitions.size(); ii++){
					if (this.vectorTransitions.get(ii).getIdTransition().equals(idTransizione)){
						// cerco lo stato a cui inserire la transizione
						for (int l=0; l < this.arrayStateLevel.length; l++){
							for(int j=0; j < this.arrayStateLevel[l].length; j++){
								if (this.arrayStateLevel[l][j] != null){
									if (this.arrayStateLevel[l][j].getIdStato().equals(idStato)){
										this.arrayStateLevel[l][j].getListTransitionOut().add(String.valueOf(ii+1));
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * Cerco tutte le transizioni che partono da un Choice in modo che queste transizioni le faccio risultare che partono dallo 
	 * stato dove parte la trx che arriva al CHOICE
	 * ed aggiungo anche le Trx nella lista delle TrxOut dello stateLevel che arriva al CHOICE
	 */
	private void modificoTrxChoice(){
		stampaSystemOutTxtArea("\n->Lettura informationi di tutti gli elementi CHOICE se presenti");
		String idStato ="";
		String newNameStato = "";
		StateLevel state = new StateLevel();
		String idChoiceEnd = "";
		String idSource = "";
		String idChoiceSource = "";
		for (int i=0; i < this.vectorTransitions.size(); i++){
			// controllo se c'è una transizione che arriva al Choice, se si memorizzo la transizione e l'id dello stato di partenza
			if (this.vectorTransitions.get(i).getChoiceEnd()){	
				idChoiceEnd = this.vectorTransitions.get(i).getIdStateTarget();
				for (int l=0; l < this.vectorTransitions.size(); l++){
					idSource = this.vectorTransitions.get(l).getIdStateSource();
					idChoiceSource = this.vectorTransitions.get(l).getIdChoiceSource();
					if (this.vectorTransitions.get(l).getIdChoiceSource().equals(this.vectorTransitions.get(i).getIdStateTarget())){
						
						for (Region itemRegion : this.modello.getStateMachine().getListRegion()){
							for (Transition itemTransition: itemRegion.getListTransition()){
								if (itemTransition.getId().equals(this.vectorTransitions.get(i).getIdTransition())){
									idStato = itemRegion.getIdStateAppartenenza();
									state = searchState(idStato);
									// modifico la l'idSource della transizione con l'idStato che parte dal choice
									this.vectorTransitions.get(l).setIdStateSource(idStato);
									this.vectorTransitions.get(l).setNameStateSource("Choice");
									this.vectorTransitions.get(l).setNewNameStateSource(state.getNewName());
									this.vectorTransitions.get(l).setLevelSource(state.getLevelStato());
									this.vectorTransitions.get(l).setNumberStateSource(state.getNumberState());
									// aggiungo la Trx alla lista trxOut dello stato
									aggiungiTransizioneAStateLevelTrxOut(idStato, String.valueOf(l+1));	
								}
							}
						}
					}
				}
				
			}
		}	
		// elimino le transizioni che arrivano al choice
		for (int i=0; i < this.vectorTransitions.size(); i++){
			// controllo se c'è una transizione che arriva al choice, se si memorizzo la transizione e l'id dello stato di partenza
			if (this.vectorTransitions.get(i).getChoiceEnd()){				
				eliminaTrxOut(idStato, String.valueOf(i+1));
			}
		}
	}
	
	
	
	
	
	
	/**           B U O N O P R I M A D E L L A M O D I FI C  A
	 * Cerco tutte le transizioni che partono da un Choice in modo che queste transizioni le faccio risultare che partono dallo 
	 * stato dove parte la trx che arriva al CHOICE
	 * ed aggiungo anche le Trx nella lista delle TrxOut dello stateLevel che arriva al CHOICE
	
	private void modificoTrxChoice(){
		stampaSystemOutTxtArea("\n->Lettura informationi di tutti gli elementi CHOICE se presenti");
		String idStato ="";
		String newNameStato = "";
		StateLevel state = new StateLevel();
		String idChoiceEnd = "";
		String idSource = "";
		String idChoiceSource = "";
		for (int i=0; i < this.vectorTransitions.size(); i++){
			// controllo se c'è una transizione che arriva al Choice, se si memorizzo la transizione e l'id dello stato di partenza
			if (this.vectorTransitions.get(i).getChoiceEnd()){	
				idChoiceEnd = this.vectorTransitions.get(i).getIdStateTarget();
				for (int l=0; l < this.vectorTransitions.size(); l++){
					idSource = this.vectorTransitions.get(l).getIdStateSource();
					idChoiceSource = this.vectorTransitions.get(l).getIdChoiceSource();
					if (this.vectorTransitions.get(l).getIdChoiceSource().equals(this.vectorTransitions.get(i).getIdStateTarget())){
						
						for (Region itemRegion : this.modello.getStateMachine().getListRegion()){
							for (Transition itemTransition: itemRegion.getListTransition()){
								if (itemTransition.getId().equals(this.vectorTransitions.get(i).getIdTransition())){
									idStato = itemRegion.getIdStateAppartenenza();
									state = searchState(idStato);
									// modifico la l'idSource della transizione con l'idStato che parte dal choice
									this.vectorTransitions.get(l).setIdStateSource(idStato);
									this.vectorTransitions.get(l).setNameStateSource("Choice");
									this.vectorTransitions.get(l).setNewNameStateSource(state.getNewName());
									this.vectorTransitions.get(l).setLevelSource(state.getLevelStato());
									this.vectorTransitions.get(l).setNumberStateSource(state.getNumberState());
									// aggiungo la Trx alla lista trxOut dello stato
									aggiungiTransizioneAStateLevelTrxOut(idStato, String.valueOf(l+1));	
								}
							}
						}
					}
				}
				
			}
		}	
		// elimino le transizioni che arrivano al choice
		for (int i=0; i < this.vectorTransitions.size(); i++){
			// controllo se c'è una transizione che arriva al choice, se si memorizzo la transizione e l'id dello stato di partenza
			if (this.vectorTransitions.get(i).getChoiceEnd()){				
				eliminaTrxOut(idStato, String.valueOf(i+1));
			}
		}
	}
	
	
	
	 */
	
	
	/**
	 * Cerco tutte le transizioni che partono da un FORK in modo che queste transizioni le faccio risultare che partono dallo stato dove 
	 * parte la trx che arriva al FORK
	 * ed aggiungo anche le Trx nella lista delle TrxOut dello stateLevel che arriva al FORK
	 */
	private void modificoTrxFork(){
		stampaSystemOutTxtArea("\n->Modifico tutte le transizioni che partono ed arrivano ad un elemento FORK");
		StateLevel state = new StateLevel();
		String idStato ="";
		String newNameStato = "";
		for (int i=0; i < this.vectorTransitions.size(); i++){
			// controllo se c'è una transizione che arriva la fork, se si memorizzo la transizione e l'id dello stato di partenza
			if (this.vectorTransitions.get(i).getForkEnd()){				
				for (int l=0; l < this.vectorTransitions.size(); l++){
					if (this.vectorTransitions.get(l).getIdStateSource().equals(this.vectorTransitions.get(i).getIdStateTarget())){
						idStato = this.vectorTransitions.get(i).getIdStateSource();
						state = searchState(idStato);
						// modifico la l'idSource della transizione con l'idStato che parte dal fork
						this.vectorTransitions.get(l).setIdStateSource(idStato);
						this.vectorTransitions.get(l).setNameStateSource("Fork");
						this.vectorTransitions.get(l).setNewNameStateSource(state.getNewName());
						this.vectorTransitions.get(l).setLevelSource(state.getLevelStato());
						this.vectorTransitions.get(l).setNumberStateSource(state.getNumberState());
						// aggiungo la Trx alla lista trxOut dello stato
						aggiungiTransizioneAStateLevelTrxOut(idStato, String.valueOf(l+1));	
					}
				}
				
			}
		}	
		// elimino le transizioni che arrivano al fork
		for (int i=0; i < this.vectorTransitions.size(); i++){
			// controllo se c'è una transizione che arriva la fork, se si memorizzo la transizione e l'id dello stato di partenza
			if (this.vectorTransitions.get(i).getForkEnd()){				
				eliminaTrxOut(idStato, String.valueOf(i+1));
			}
		}
	}
	
	
	/**
	 * Cerco tutte le trx che partono da un join, prendo l'idStato di target della transition, e lo imposto come target a tutte le 
	 * trx che hanno come target lo stesso id del Join
	 */
	private void modificoTrxJoin(){
		stampaSystemOutTxtArea("\n->Modifico tutte le transizioni che partono ed arrivano ad un elemento JOIN");
		String idJoin = "";
		String idStato ="";
		String newNameStato = "";
		StateLevel state = new StateLevel();
		for (int i=0; i < this.vectorTransitions.size(); i++){
			// controllo se c'è una transizione che parte da un join, se si memorizzo la transizione e l'id dello stato di arrivo
			if (this.vectorTransitions.get(i).getJoinStart()){				
				for (int l=0; l < this.vectorTransitions.size(); l++){
					if (this.vectorTransitions.get(l).getIdStateTarget().equals(this.vectorTransitions.get(i).getIdStateSource())){
						idStato = this.vectorTransitions.get(i).getIdStateTarget();
						state = searchState(idStato);
						// modifico la l'idTarget della transizione con l'idStato che è il target della trx in out del join
						this.vectorTransitions.get(l).setIdStateTarget(idStato);
						this.vectorTransitions.get(l).setNameStateTarget("Join");
						this.vectorTransitions.get(l).setNewNameStateTarget(state.getNewName());
						this.vectorTransitions.get(l).setLevelTarget(state.getLevelStato());
						this.vectorTransitions.get(l).setNumberStateTarget(state.getNumberState());
						// aggiungo la Trx alla lista trxOut dello stato
						//aggiungiTransizioneAStateLevelTrxOut(idStato, String.valueOf(l+1));	
					}
				}
				
			}
		}	
		
	}
	
	/**
	 * Cerco tutti i deepHistory, e tutte le transizioni che hanno un target nel deepHistory, e cambio il target della Trx con lo stato che
	 * contiene il deepHistory
	 */
	private void modificoDeepHistory(){
		stampaSystemOutTxtArea("\n->Cerco tutti i DeepHistory e setto la relativa transizione");
		String idStato ="";
		String idDeepHistory ="";
		Region region = new Region();
		
		for (DeepHistory itemDeep : this.modello.getStateMachine().getVectorDeepHistory()){
			for(int i=0; i < this.vectorTransitions.size(); i++){
				if (this.vectorTransitions.get(i).getIdStateTarget().equals(itemDeep.getId())){
					// cerco la regione di appartenza del deepHistory
					for (int l=0; l < this.modello.getStateMachine().getListRegion().size(); l++){
						region = this.modello.getStateMachine().getListRegion().get(l);
						if (region.getDeepHistory() != null){
							if (region.getDeepHistory().getId().equals(itemDeep.getId())){
								// ho trovato la region
								idStato = region.getIdStateAppartenenza();
								this.vectorTransitions.get(i).setNewNameStateTarget(searchNewNameState(idStato));
								this.vectorTransitions.get(i).setNameStateTarget("DeepHistory");
							}
						}
					}
				}
			}
		}
	}
	
	
	/**
	 * Cerco tutti i shallowHistory, e tutte le transizioni che hanno un target nel shallowHistory, e cambio il target della Trx con lo stato che
	 * contiene il shallowHistory
	 */
	private void modificoShallowHistory(){
		stampaSystemOutTxtArea("\n->Cerco tutti i ShallowHistory e setto la relativa transizione");
		String idStato ="";
		String idShallowHistory ="";
		Region region = new Region();
		
		for (ShallowHistory itemShallow : this.modello.getStateMachine().getVectorShallowHistory()){
			for(int i=0; i < this.vectorTransitions.size(); i++){
				if (this.vectorTransitions.get(i).getIdStateTarget().equals(itemShallow.getId())){
					// cerco la regione di appartenza del ShallowHistory
					for (int l=0; l < this.modello.getStateMachine().getListRegion().size(); l++){
						region = this.modello.getStateMachine().getListRegion().get(l);
						if (region.getShallowHistory() != null){
							if (region.getShallowHistory().getId().equals(itemShallow.getId())){
								// ho trovato la region
								idStato = region.getIdStateAppartenenza();
								this.vectorTransitions.get(i).setNewNameStateTarget(searchNewNameState(idStato));
								this.vectorTransitions.get(i).setNameStateTarget("ShallowHistory");
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * Questo metodo mi permette di aggiungere una Trx nella lista delle TrxOut di uno StateLevel
	 * @param idStato Id dello stato
	 * @param nrTrx Numerod elle transizioni
	 */
	private void aggiungiTransizioneAStateLevelTrxOut(String idStato, String nrTrx){
		boolean presente = false; 
		for (int i=0; i < this.arrayStateLevel.length; i++){
			for (int j=0; j < this.arrayStateLevel[i].length; j++){
				if (this.arrayStateLevel[i][j] != null){
					if (this.arrayStateLevel[i][j].getIdStato().equals(idStato)){
						// verifico che la transizione non sia già presente
						for (int n=0; n < this.arrayStateLevel[i][j].getListTransitionOut().size(); n++){
							if (this.arrayStateLevel[i][j].getListTransitionOut().get(n).equals(nrTrx))
								presente = true;
						}
						// aggiungo una trxOut
						if (!presente)
							this.arrayStateLevel[i][j].getListTransitionOut().add(nrTrx);
						presente = false;
					}
				}
			}
		}
	}
	/**
	 * Elimino dalla lista TrxOut di ogni stateLevel quelle transizioni che arrivano ad un CHOICE, questo lo posso fare percheè ho già inerito le trx che 
	 * dal choice
	 */
	private void eliminaTrxTargetChoice(){
		String idChoice = "";
		String idStato ="";
		
		
		Region region = new Region();
		for (int i=0; i < this.vectorTransitions.size(); i++){
			// controllo se c'è un choice
			if (this.vectorTransitions.get(i).getChoice()){
				// mi assicuro che la transizione arrivi al choice
				if (this.vectorTransitions.get(i).getChoiceEnd()){
					idChoice = this.vectorTransitions.get(i).getIdStateTarget();
					idStato = this.vectorTransitions.get(i).getIdStateTarget();
					eliminaTrxOut(idStato, String.valueOf(i+1));
				}
			}
		}
	}
	/**
	 * Metodo che mi permette di eliminare una trx in trxOut a partire dall'id dello stato e dal numero di trx da eliminare
	 * @param idStato Id dello stato
	 * @param nrTrx Numero delle transizioni
	 */
	private void eliminaTrxOut(String idStato, String nrTrx){
		for (int i=0; i < this.arrayStateLevel.length; i++){
			for (int j=0; j < this.arrayStateLevel[i].length; j++){
				if (this.arrayStateLevel[i][j] != null){
					for (int k=0; k < this.arrayStateLevel[i][j].getListTransitionOut().size(); k++){
						if (this.arrayStateLevel[i][j].getListTransitionOut().get(k).equals(nrTrx)){
							// elimino la trx dalla lista
							this.arrayStateLevel[i][j].getListTransitionOut().remove(k);
						}
					}
				}
			}
		}
	}
	/**
	 * Metedo che effettua il controllo delle variabili, se sono state impostate.
	 * per prima cosa controllo i tipi, è possibile che ci siano anche dei tipi definiti dall'utente, pertanto questi 
	 * devono essere associati
	 * - tipo="-1" -> tipo definito dall'utente;
	 * - visibility ="true" -> indica che la variabile è locale e non globale
	 * - verifico che i tipi definiti dall'utente siano corretti con quelli che codesys può accettare
	 */
	private void checkVariables(){
		stampaSystemOutTxtArea("\n->Check sulle variabili");
		Variable variabile= new Variable();
		PrimitiveType tipoPrimitivo = new PrimitiveType();
		// effettuo la conferma dei tipi
		for (int i =0; i < this.modello.getStateMachine().getVectorPrimitiveType().size(); i++){
			this.modello.getStateMachine().getVectorPrimitiveType().get(i).setTipoConfermato(
					controlloTipoVariabile(this.modello.getStateMachine().getVectorPrimitiveType().get(i).getName()));
		//	if (!this.modello.getStateMachine().getVectorPrimitiveType().get(i).getTipoConfermato())
		//		this.modello.getStateMachine().getVectorPrimitiveType().get(i).setName("DINT");
			
		}
		// associo i tipi alle variabili che non hanno il tipo impostato
		for (int i=0; i < this.modello.getStateMachine().getVectorVariable().size(); i++){
			variabile = this.modello.getStateMachine().getVectorVariable().get(i);
			if (!variabile.getTipoAssociato()){
				// indica che non è stato associato il tipo quindi lo devo associare con quelli definiti dall'utente
				for(int l=0; l < this.modello.getStateMachine().getVectorPrimitiveType().size(); l++){
					tipoPrimitivo = this.modello.getStateMachine().getVectorPrimitiveType().get(l);
					if (variabile.getIdTypeVariable() != null){
						if (variabile.getIdTypeVariable().equals(tipoPrimitivo.getId())){
							this.modello.getStateMachine().getVectorVariable().get(i).setType(tipoPrimitivo.getName());
							this.modello.getStateMachine().getVectorVariable().get(i).setTipoAssociato(true);
						}
					}
				}
			}
		}
		// verifico se ci sono delle variabile non nessun tipo associato e le imposto a DINT
		for (int i=0; i < this.modello.getStateMachine().getVectorVariable().size(); i++){
			variabile = this.modello.getStateMachine().getVectorVariable().get(i);
			if (!variabile.getTipoAssociato()){
				this.modello.getStateMachine().getVectorVariable().get(i).setType(ConstantString.DERIVED);
				this.modello.getStateMachine().getVectorVariable().get(i).setTipoAssociato(true);
			}
		}
		
	}
	/**
	 * Restituisce il modello
	 * @return modello Contiene il modello
	 */
	public Model getModello(){
		return this.modello;
	}
	/**
	 * Effettua il controllo del tipoVariabile con i tipi che possono essere accettati, restiruisce TRUE se il tipo è corretto
	 * @param tipoVar tipo della variabile
	 * @return ris =TRUE	tipo accettato
	 */
	private boolean controlloTipoVariabile(String tipoVar){
		stampaSystemOutTxtArea("\n->Controllo il tipo della variabile");
		boolean ris = false;
		for (int i=0; i < this.tipiVariabili.length; i++){
			if (tipoVar.equals(this.tipiVariabili[i])){
				ris = true;
			}
		}
		return ris;
	}
	/**
	 * Stampa un messaggio video
	 * @param value valore da stampare
	 */
	private void stampaSystemOutTxtArea(String value){
		if (!this.console){
			this.msg = value;
			System.out.println(msg);
			this.txtArea.append(this.msg);
			this.txtArea.setCaretPosition(this.txtArea.getText().length());
		}
	}
	/**
	 * Verifico che le variabili presenti nei vari stati siano presenti anche nella lista delle variabili globali,
	 * in caso non sono presenti insierisco un commento nell'action così può controllare
	 */
	private void verificoVariabiliPresenza(){
		boolean presente = false;
		String listVarNotPresenti = "";
		String nameVarState = "";
		int nrVarMachine = this.modello.getStateMachine().getVectorVariable().size();
		int nrState = this.modello.getStateMachine().getListState().size();
		int nrVar = 0;
		for (int i = 0; i < arrayStateLevel.length; i++){
			for (int j=0; j < arrayStateLevel[i].length; j++){
				if (arrayStateLevel[i][j] != null){
					nrVar = arrayStateLevel[i][j].getListVariable().size();
					if (nrVar > 0){
						for (int k=0; k < nrVar; k++){
							nameVarState = arrayStateLevel[i][j].getListVariable().get(k).getName();
							presente =  checkVariableMachine(nameVarState);
							if (!presente){
								// la variabile non è stata trovata
								listVarNotPresenti = listVarNotPresenti + "/// Le variabili " + nameVarState + ", ";
							}
							presente = false;
						}
					}
					if (listVarNotPresenti.length() > 0){
						// modifico action perchè ci sono delle variabili non elencati nella stateMachine
						String action = arrayStateLevel[i][j].getAction();
						action = action + "\n\n" + listVarNotPresenti + " Non sono presenti nella lista delle variabili caricate tramite la transizione iniziele del diagramma UML";
						arrayStateLevel[i][j].setAction(action);	
						System.out.println(action);
					}
				}
			}	
		}
	}
	/**
	 * Dato in input come parametro il nome di una variabile presente nell'elenco della lista di uno stato, controlla che esso 
	 * sia presente anche nella lista della stateMachine, se TRUE è presente
	 * @param nameVarState Nome della variabile
	 * @return found valore booleano
	 */
	private boolean checkVariableMachine(String nameVarState){
		boolean found = false;
		int nrVarMachine = this.modello.getStateMachine().getVectorVariable().size();
		for (int i = 0; i < nrVarMachine; i++){
			if (nameVarState.equals(this.modello.getStateMachine().getVectorVariable().get(i).getName()))
				found = true;
		}
		return found;
	}
	
	/**
	 * Cerco tutti i commenti inseriti nelle liste delle Region e gli associo ai Choice se sono presenti
	 */
	private void associaCommentiAChoice(){
		
		for(Region itemRegion : this.modello.getStateMachine().getListRegion()){
			for (Choice itemChoice : itemRegion.getListChoice()){
				for(Region itemRegionExt : this.modello.getStateMachine().getListRegion()){
					for (Comment itemComment : itemRegionExt.getListComment()){
						if (itemComment.getAnnotatedElement() != null){
							for (int i=0; i < itemComment.getAnnotatedElement().length; i++){
								if (itemComment.getAnnotatedElement()[i].equals(itemChoice.getId())){
									itemChoice.setComment(itemComment);
								}
							}
						}
					}
				}
			}
		}
	}
	/**
	 * Associo i commenti alle transizioni
	 */
	private void associaCommentiATransition(){
		for(Region itemRegion : this.modello.getStateMachine().getListRegion()){
			for (Comment itemComment : itemRegion.getListComment()){
				if (itemComment.getAnnotatedElement() != null){
					for (int i=0; i < itemComment.getAnnotatedElement().length; i++){
						searchTRXWithAnnotatedElement(itemComment.getAnnotatedElement()[i], itemComment);
							
					}
						
				}
			}			
		}
	}
	/**
	 * Dato come parametro in input l'id di un elemento annotato di Comment, verifica che sia uguale all'id della transitions
	 * @param id Contiene l'id dell'elemento
	 */
	private void searchTRXWithAnnotatedElement(String id, Comment comment){
		for (Transitions itemTransitions : this.vectorTransitions){
			if (itemTransitions.getIdTransition().equals(id))
				itemTransitions.setComment(comment);
		}
	}
	/**
	 * Inserisco tutti gli oggetti CHOICE trovati in tutte le regioni e le carico nella lista di ciauscun stato di appartenenza in 
	 * arrayStateLevl. questo mi sarà utile successivamente in v
	 */
	private void loadChoiceIntoArrayStateLevel(){
		String testo = "";
		String testoAction = "";
		Vector<String> listStateChoiceOut ;  
		int[] indiciStateLevel = new int[2];
		int tmp = 0;
		indiciStateLevel[0] = -1;
		indiciStateLevel[1] = -1;
		Region  region;
		for (Region itemRegion : this.modello.getStateMachine().getListRegion()){
			for (Choice itemChoice : itemRegion.getListChoice()){
				indiciStateLevel = searchStateLevelWithId(itemChoice.getIdRegionAppartenenza());
				System.out.println(indiciStateLevel[0] + " " + indiciStateLevel[1]);
				//if (indiciStateLevel[1] == 19)
					//System.exit(1);
				//testo = searchTextCommentFromTRXChoice(itemChoice.getId());
				testoAction = this.arrayStateLevel[indiciStateLevel[0]][indiciStateLevel[1]].getAction();
				testoAction = testoAction + testo;
				this.arrayStateLevel[indiciStateLevel[0]][indiciStateLevel[1]].setAction(testoAction);
				
			}
		}
	}
	/**
	 * Cerca il commento per un determinato choice
	 * @param idChoice Id del choice
	 * @return Restituisce la stringa
	 */
	private String searchTextCommentFromTRXChoice(String idChoice){
		String testoAction = "";
		for (Transitions itemTransitions : this.vectorTransitions){
			if (itemTransitions.getIdChoiceSource().equals(idChoice)){
				if (itemTransitions.getComment() != null){
					if (itemTransitions.getComment().getBody() != null){
						if (itemTransitions.getComment().getBody().length() > 0){
							testoAction = testoAction + "\n" + itemTransitions.getComment().getBody() + "\n\t" + itemTransitions.getNewNameStateTarget();
							itemTransitions.setCommentValutato(true);
						}
					}
				}
			}
				
		}
		return testoAction;
	}
	/**
	 * Questo metodo prende in input l'id di uno stato e restituisce i suoi indici di posizione all'interno dell'arrayStateLevel
	 * @param id Id della regione
	 * @return Restituisce gli indici della sua posizione
	 */
	private int[] searchStateLevelWithId(String idRegion){
		int[] indiciStateLevel = new int[2];
		String idState="";
		for (Region itemRegion : this.modello.getStateMachine().getListRegion()){
			if (itemRegion.getId().equals(idRegion))
				idState = itemRegion.getIdStateAppartenenza();
		}
		for (int i=0; i < this.arrayStateLevel.length; i++){
			for (int j=0; j < this.arrayStateLevel[i].length; j++){
				if (this.arrayStateLevel[i][j] != null){
					if (this.arrayStateLevel[i][j].getIdStato().equals(idState)){
						indiciStateLevel[0] = i;
						indiciStateLevel[1] = j;
					}
				}
			}
		}
		return indiciStateLevel;
	}
	/**
	 * Cerco tutte le transizioni che partono da uno stato ed arrivano direttamente ad un altro stato
	 * se esiste un commento cuol dire che c'è un trigger sotto forma di IF e la devo inserire nell'Action
	 * dello StateLevel di partenza della transition
	 */
	private void loadCommentFromTRXIntoArrayStateLevel(){
		boolean statoSource = false;
		boolean statoTarget = false;
		boolean joinTarget = false;
		boolean ifPresent = false;
		int ifPosition = -1;
		int[] indiciStatoSource = new int[2];
		int[] indiciStatoTarget = new int[2];
		String testo = "";
		String bodyComment = "";
		
		for (Transitions itemTransitions : this.vectorTransitions){
			if (itemTransitions.getIdStateSource() != "DEFAULT"){
				indiciStatoSource = searchIndexStateLevelWithId(itemTransitions.getIdStateSource());
				if (indiciStatoSource[0] > -1){
					// vuol dire che il source è uno stato
					statoSource = true;
				}
				indiciStatoTarget = searchIndexStateLevelWithId(itemTransitions.getIdStateTarget());
				if (indiciStatoTarget[0] > -1){
					// vuol dire che il source è uno stato
					statoTarget = true;
				}
				// transizione da STATO a STATO
				if (statoSource && statoTarget){
					// la transizione parte da uno stato ed arriva ad un altro stato
					if (this.arrayStateLevel[indiciStatoSource[0]][indiciStatoSource[1]].getAction() != null){
						testo = this.arrayStateLevel[indiciStatoSource[0]][indiciStatoSource[1]].getAction();
					}
					
					// verifico che ci sia un commento ed un body
					/*if ((itemTransitions.getComment() != null) && (itemTransitions.getComment().getBody() != null)){	
						if (!itemTransitions.getCommentValutato()){
							ifPosition = itemTransitions.getComment().getBody().indexOf("IF");
							if (ifPosition > -1){
								// nel commento della transition l'IF è già costruito quindi non devo costruirla
								testo = testo + "\n" + itemTransitions.getComment().getBody() + 
										"\n\t" + this.arrayStateLevel[indiciStatoTarget[0]][indiciStatoTarget[1]].getNewName();
								this.arrayStateLevel[indiciStatoSource[0]][indiciStatoSource[1]].setAction(testo);
								itemTransitions.setCommentValutato(true);
							}
							else{
								testo = testo + "\n" + "IF " + itemTransitions.getComment().getBody() + " THEN " +
										 this.arrayStateLevel[indiciStatoTarget[0]][indiciStatoTarget[1]].getNewName();
								this.arrayStateLevel[indiciStatoSource[0]][indiciStatoSource[1]].setAction(testo);
								itemTransitions.setCommentValutato(true);
							
							ifPosition  = -1;
						}
					}}*/
					statoSource = false;
					statoTarget = false;
				}
			}
		}
		
	}
	/**
	 * Questo metodo prende in input l'id di uno stato e restituisce i suoi indici di posizione all'interno dell'arrayStateLevel
	 * e compe terzo elemento dell'array di object di ritorno indica se TRUE o FALSE
	 * @param id Id dello stato
	 * @return Indice della sua posizione nell'array
	 */
	private int[] searchIndexStateLevelWithId(String idState){
		int[] indiciStateLevel = new int[2];
		indiciStateLevel[0] = -1;
		indiciStateLevel[1] = -1;
		for (int i=0; i < this.arrayStateLevel.length; i++){
			for (int j=0; j < this.arrayStateLevel[i].length; j++){
				if (this.arrayStateLevel[i][j] != null){
					if (this.arrayStateLevel[i][j].getIdStato().equals(idState)){
						indiciStateLevel[0] = i;
						indiciStateLevel[1] = j;
					}
				}
			}
		}
		return indiciStateLevel;
	}
	/**
	 * Restituisce la lista associate con il numero degli indici e il nome delle transizioni
	 * @return listTRXsWithOriginalName
	 */
	public ArrayList<String> getListTRXsWithOriginalName (){
		ArrayList<String> newListTRXsWithOriginalName = new ArrayList<String>(); 
		for (int i=0; i < this.listTRXsWithOriginalName.size(); i++){
			if (this.listTRXsWithOriginalName.get(i).length() > 0)
				newListTRXsWithOriginalName.add(this.listTRXsWithOriginalName.get(i));
		}
		this.listTRXsWithOriginalName = new ArrayList<String>();
		this.listTRXsWithOriginalName = newListTRXsWithOriginalName;
		return this.listTRXsWithOriginalName ;
	}
	/**
	 * Aggiunge il numero di una transizione in uscita da un nodo del grafo
	 * @param nrTrx Numero di transizione in uscita da un nodo del grafo
	 */
	public void setAddListTrxOutGrafo(String nrTrx){
		this.listTrxOutGrafo.add(nrTrx);
	}
	/**
	 * Restituisce la lista contenete l'elenco di tutte le trxOut del Grafo
	 * @return  listTrxOutGrafo Lista delle transizioni del grafo
	 */
	public ArrayList<String> getListTrxOutGrafo(){
		return this.listTrxOutGrafo;
	}
	/**
	 * Carico tutte le transizioni in out dagli stati del grafo nella lista
	 */
	private void loadListTrxOutGrafo(){
		int valore = 0;
		
		for (StateLevel itemGrafo : this.grafoStati){
			for(int i =0; i < itemGrafo.getListTransitionOut().size(); i++){
				valore = Integer.parseInt(itemGrafo.getListTransitionOut().get(i))-1;
				this.setAddListTrxOutGrafo(String.valueOf(valore));
			}
		}
		
		
	}
	/**
	 * Restituisce la lista delle trx che dove sono associati gli indici di vectorTransitions e l'indice di TRXs per il ladder
	 * @return listTrxIn LIsta
	 */
	public ArrayList<Trx_In> getListTrxIn (){
		return this.listTrxIn;
	}
}
