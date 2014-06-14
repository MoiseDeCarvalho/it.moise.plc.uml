package it.moise.plc.uml.jaxb_plcopen;



import it.moise.plc.uml.ConstantString;
import it.moise.plc.uml.ScriviSuFile;
import it.moise.plc.uml.StateLevel;
import it.moise.plc.uml.Transitions;
import it.moise.plc.uml.Trx_In;
import it.moise.plc.uml.Variable;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;


















import org.plcopen.xml.tc6_0201.AddData;
import org.plcopen.xml.tc6_0201.Body;
import org.plcopen.xml.tc6_0201.Connection;
import org.plcopen.xml.tc6_0201.ConnectionPointIn;
import org.plcopen.xml.tc6_0201.ConnectionPointOut;
import org.plcopen.xml.tc6_0201.DataType;
import org.plcopen.xml.tc6_0201.DataType.Derived;
import org.plcopen.xml.tc6_0201.EdgeModifierType;
import org.plcopen.xml.tc6_0201.FormattedText;
import org.plcopen.xml.tc6_0201.ObjectFactory;
import org.plcopen.xml.tc6_0201.Position;
import org.plcopen.xml.tc6_0201.PouType;
import org.plcopen.xml.tc6_0201.Project;
import org.plcopen.xml.tc6_0201.RangeSigned;
import org.plcopen.xml.tc6_0201.StorageModifierType;
import org.plcopen.xml.tc6_0201.Value_;
import org.plcopen.xml.tc6_0201.VarListPlain;
import org.plcopen.xml.tc6_0201.Body.SFC.Step.ConnectionPointOutAction;

/**
 * Classe che mi permette di creare l'oggetto Project.Types.Pous.Pou e di popolarlo con i dati
 * @author De Carvalho Moïse
 *
 */
public class PouTag extends Project.Types.Pous.Pou {
	/*
	 *  Questa lista viene utilizzata da selectionDivergence e selectionConvergence.
	 *  Ogni volta che viene creata una selection, viene aggiunto un elemento
	 *  Ogni volta che viene chiusa una selection, viene eliminato un elemento
	 *  Alla line la lista deve essere vuota
	 */
	private Vector<it.moise.plc.uml.jaxb_plcopen.SelectionDivConv> listSelection = new Vector<it.moise.plc.uml.jaxb_plcopen.SelectionDivConv>();
	private SelectionDivergenceTag selectionDivergence;
	private SelectionConvergenceTag selectionConvergence;
	private StepTag step;
	private InVariableTag inVariable;
	private TransitionTag transition;
	private JumpStepTag jumpStep;
	
	private String assegnazioneTRXConValore ="";
	/*
	 *  Questa lista viene utilizzata da selectionDivergence e selectionConvergence.
	 *  Ogni volta che viene creata una selection, viene aggiunto un elemento
	 *  Ogni volta che viene chiusa una selection, viene eliminato un elemento
	 *  Alla line la lista deve essere vuota
	 */
	private Vector<it.moise.plc.uml.jaxb_plcopen.SimultaneousDivConv> listSimultaneous = new Vector<it.moise.plc.uml.jaxb_plcopen.SimultaneousDivConv>();
	private Vector<it.moise.plc.uml.Transitions> vectorTransitions = new Vector<it.moise.plc.uml.Transitions>();
	
	// questa variabile se true indica che lo stato transita verso uno stato apparatenente ad un livello diverso da quello attuale
	//private boolean statoTransita = false;
	
	// questa variabile se true indica che lo stato ha una transizione di tipo DEFAULT
	//private boolean statoDefaultTrx = false;
	int numberLivelli  =0;
	int numberStateFirstLevel = 0;
	private ScriviSuFile fileOutput = new ScriviSuFile("System_Out_Println.txt");
	//private Project.Types.Pous.Pou pou;
	private Project.Types.Pous.Pou.Interface pouInterface;
	private Project.Types.Pous.Pou.Interface.InputVars inputInterfaceVars;
	private Project.Types.Pous.Pou.Interface.OutputVars outputInterfaceVars;
	private Project.Types.Pous.Pou.Interface.LocalVars localInterfaceVars;
	private Project.Types.Pous.Pou.Interface.GlobalVars globalInterfaceVars;
	private Project.Types.Pous.Pou.Interface.InOutVars inOutInterfaceVars;
	private Project.Types.Pous.Pou.Interface.TempVars tempInterfaceVars;
	
	private Project.Types.Pous.Pou.Interface.LocalVars constantsLocalVars;
	private VarListPlain.Variable varBool;
	private Project.Types.Pous.Pou.Actions pouActions;
	private Project.Types.Pous.Pou.Actions.Action pouStateMangerAction;
	
	private Body body;
	private String statManagerValue = "";
	private Body.SFC sfc = new ObjectFactory().createBodySFC();
	private ArrayList<StateLevel> grafo;
	private Vector<Variable> vectorVariable = new Vector<Variable>();
	private Vector<String> vectorUserType = new Vector<String>();
	private String[] tipiVariabili = {"BOOL", "BYTE", "BIT", "WORD", "DWORD", "LWORD", "SINT", "USINT", "INT", "UINT", "LINT", "DINT", 
			"REAL", "LREAL", "STRING", "TIME", "TOD", "DATE", "DT"};
	
	// Questa lista mette in corrispondenza il suo indice con il nome della transizione nel'UML per non perdere l'associazione
	private ArrayList<String> listTRXsWithOriginalName = new ArrayList<String>();
	private ArrayList<String> listTrxOutGrafo = new ArrayList<String>();
	//private Vector<Transitions> vectorTransitions = new Vector<Transitions>();
											 
	private ArrayList<Trx_In> listTrxIn = new ArrayList<Trx_In>();
	
	/**
	 * Metodo costruttore
	 */
	public PouTag( 	ArrayList<Trx_In> listTrxIn,
					ArrayList<String> listTRXsWithOriginalName,
					ArrayList<String> listTrxOutGrafo,
					String stateManagerValue,
					int numberStateLevels,
					String stateManagerActionValue,
					String initActionValue,
					StateLevel[][] listaStati,
					Vector<it.moise.plc.uml.Transitions> vectorTransitions,
					ArrayList<StateLevel> grafo,
					Vector<Variable> vectorVariable,
					Vector<String> vectorUserType) {
		super();
		
		this.numberLivelli = listaStati.length;
		this.listTrxIn = listTrxIn;
		this.vectorTransitions = vectorTransitions;
		this.listTrxOutGrafo = listTrxOutGrafo;
		this.listTRXsWithOriginalName = listTRXsWithOriginalName;
		this.vectorTransitions = vectorTransitions;
		this.vectorUserType = vectorUserType;
		this.vectorVariable = vectorVariable;
		this.grafo = new ArrayList<StateLevel>();
		this.grafo = grafo;
		this.pouInterface = new ObjectFactory().createProjectTypesPousPouInterface();
		this.globalInterfaceVars = new ObjectFactory().createProjectTypesPousPouInterfaceGlobalVars();
		
		this.inputInterfaceVars = new ObjectFactory().createProjectTypesPousPouInterfaceInputVars();
		this.outputInterfaceVars = new ObjectFactory().createProjectTypesPousPouInterfaceOutputVars();
		this.localInterfaceVars = new ObjectFactory().createProjectTypesPousPouInterfaceLocalVars();
		
		this.inOutInterfaceVars = new ObjectFactory().createProjectTypesPousPouInterfaceInOutVars();
		this.tempInterfaceVars = new ObjectFactory().createProjectTypesPousPouInterfaceTempVars();
		this.constantsLocalVars = new ObjectFactory().createProjectTypesPousPouInterfaceLocalVars();
		
		this.constantsLocalVars.setConstant(true);
		this.pouActions  = new ObjectFactory().createProjectTypesPousPouActions();
		this.pouStateMangerAction = new ObjectFactory().createProjectTypesPousPouActionsAction();
		
		this.body = new ObjectFactory().createBody();
		this.createPou( numberStateLevels, stateManagerActionValue, initActionValue, listaStati, vectorTransitions);
		
	}
	
	
	/**
	 * Metodo per la popolazione dell'oggetto POU
	 */
	public void createPou(	
							int numberStateLevels, 
							String stateManagerActionValue, 
							String initActionValue,
							StateLevel[][] listaStati,
							Vector<it.moise.plc.uml.Transitions> vectorTransitions){
		
		setSFC(listaStati, vectorTransitions);
		
		
		// aggiungo le variabili booleane all'interface input
		this.setName("SFC_Fake_Importato");
		this.setPouType(PouType.fromValue("functionBlock"));
		// aggiungo le variabili GLOBALI se eventualmente esistono
		this.setGlobalInterfaceVars(this.listTrxOutGrafo.size());
		// aggiungo le variabili TEMP se eventualmente esistono
		this.setTempInterfaceVars();
		// aggiungo le variabili in inputVars
		this.setInputInterfaceVars(listTrxIn);
		// aggiungo le variabili LOCAL
		this.setLocalInterfaceVars(this.listTrxOutGrafo.size(), numberStateLevels);
		// aggiungo le costanti locali, rappresentate dal numero di transizioni booleane
		this.setLocalInterfaceConstants(listTrxIn.size());
		// aggiungo le variabili OUTPUT se esventualmente esistono
		this.setOutputInterfaceVars();
		this.pouInterface.getLocalVarsOrTempVarsOrInputVars().add(this.globalInterfaceVars);
		this.pouInterface.getLocalVarsOrTempVarsOrInputVars().add(this.localInterfaceVars);
		this.pouInterface.getLocalVarsOrTempVarsOrInputVars().add(this.tempInterfaceVars);
		this.pouInterface.getLocalVarsOrTempVarsOrInputVars().add(this.inputInterfaceVars);
		this.pouInterface.getLocalVarsOrTempVarsOrInputVars().add(this.outputInterfaceVars);
		this.pouInterface.getLocalVarsOrTempVarsOrInputVars().add(this.constantsLocalVars);
		
		
		this.setInterface(pouInterface);
		/*
		 *Actions 
		 */
		this.setActions(stateManagerActionValue, initActionValue, listTrxIn.size(), listaStati);
		/*
		 * Body
		 */
		 
		this.getBody().add(this.body);
	}
	
	public void setSFC(StateLevel[][] listaStati, Vector<it.moise.plc.uml.Transitions> vectorTransitions){
		StepTag step;
		
		
		
		
		
		ConnectionPointOutAction connectionPointOutAction;
		/*
		 * Classi estese
		 */
		AddDataTag addData = new AddDataTag();
		InVariableTag inVariable;
		ConnectionPointInTag connectionPointIn;
		JumpStepTag jumpStep;
		/*
		 * Fine classi estese
		 */
		
		TransitionTag transition;
		
		Connection connection;
		
		Body.SFC.ActionBlock.Action action;
		Body.SFC.ActionBlock.Action.Reference reference;
		Body.SFC.ActionBlock actionBlock;
		
		Position position = new ObjectFactory().createPosition();
		position.setX(new BigDecimal("0"));
		position.setY(new BigDecimal("0"));
		/*
		 * 
		 * Diagramma SFC
		 */
		
			/*
			 * Per prima cosa creo lo stato di INIT
			 */
		int localId = 0;
		int localIdInit = localId;
		step = new StepTag(true, localId, "Init", position, 0, addData, localId, "-1");
		this.sfc.getCommentOrErrorOrConnector().add(step);
		actionBlock = new ObjectFactory().createBodySFCActionBlock();
		/*
		 * Sezione Action Inizialization - Action Block dello step Init
		 */
	
		localId ++;
		action = new ObjectFactory().createBodySFCActionBlockAction();
		action.setLocalId(new BigInteger(String.valueOf(localId)));
		action.setQualifier("P");
		action.setDuration("");
		action.setIndicator("");
		action.setRelPosition(position);
		reference = new ObjectFactory().createBodySFCActionBlockActionReference();
		reference.setName("Inizialization");
		action.setReference(reference);
		action.setConnectionPointOut(new ObjectFactory().createConnectionPointOut());
		actionBlock.getAction().add(action);
			/*
			 * Fine Action Inizialization
			 */
		/*
		 * Sezione Action AssegnaCondizioniBooleaneTRX - Action Block dello step Init
		 */
	
		localId ++;
		action = new ObjectFactory().createBodySFCActionBlockAction();
		action.setLocalId(new BigInteger(String.valueOf(localId)));
		action.setQualifier("P");
		action.setDuration("");
		action.setIndicator("");
		action.setRelPosition(position);
		reference = new ObjectFactory().createBodySFCActionBlockActionReference();
		reference.setName("AssegnaCondizioniBooleaneTRX");
		action.setReference(reference);
		action.setConnectionPointOut(new ObjectFactory().createConnectionPointOut());
		actionBlock.getAction().add(action);
		/*
		 * Fine Action AssegnaCondizioniBooleaneTRX
		 */
		localId++;
		actionBlock.setPosition(position);
		actionBlock.setLocalId(new BigInteger(String.valueOf(localId)));
		connectionPointIn = new ConnectionPointInTag(localIdInit);
		
		actionBlock.setConnectionPointIn(connectionPointIn);
		
		this.sfc.getCommentOrErrorOrConnector().add(actionBlock);  
		
		
			/*
		     * fine Step INIT
		     */
		
			/*
			 * InVariable
			 */
		localId++;
		inVariable = new InVariableTag("TRUE", localId, position);
		this.sfc.getCommentOrErrorOrConnector().add(inVariable);
			/*
			 * Fine InVariable
			 */
		
			/*
			 * Transition
			 */
		localId++;
		transition = new TransitionTag(position, localId, 0, localId-1, addData);
		transition.setAddData(addData);
		this.sfc.getCommentOrErrorOrConnector().add(transition);
		
		connectionPointOutAction = new ObjectFactory().createBodySFCStepConnectionPointOutAction();
		connectionPointOutAction.setFormalParameter("x");
		step.setConnectionPointOutAction(connectionPointOutAction);
		
		//this.sfc.getCommentOrErrorOrConnector().add(step);

		
		
			
		
		/*
		 * Inizio Step EXEC
		 */
		localId++;
		int localIdEXEC = localId;
		step = new StepTag(false, localId, "Exec", position, 5, addData, localId-1, "-1");
		
		connectionPointOutAction = new ObjectFactory().createBodySFCStepConnectionPointOutAction();
		connectionPointOutAction.setFormalParameter("x");
		step.setConnectionPointOutAction(connectionPointOutAction);
		
		this.sfc.getCommentOrErrorOrConnector().add(step);

		actionBlock = new ObjectFactory().createBodySFCActionBlock();
			/*
			 * Sezione Action Transitions - Action Block dello step EXEC
			 */
		// Action UpdateTRX
		localId ++;
		action = new ObjectFactory().createBodySFCActionBlockAction();
		action.setLocalId(new BigInteger(String.valueOf(localId)));
		action.setQualifier("N");
		action.setDuration("");
		action.setIndicator("");
		action.setRelPosition(position);
		reference = new ObjectFactory().createBodySFCActionBlockActionReference();
		reference.setName("UpdateTRX");
		action.setReference(reference);
		action.setConnectionPointOut(new ObjectFactory().createConnectionPointOut());
		actionBlock.getAction().add(action);
		
		
		// Action Transitions
		localId ++;
		action = new ObjectFactory().createBodySFCActionBlockAction();
		action.setLocalId(new BigInteger(String.valueOf(localId)));
		action.setQualifier("N");
		action.setDuration("");
		action.setIndicator("");
		action.setRelPosition(position);
		reference = new ObjectFactory().createBodySFCActionBlockActionReference();
		reference.setName("Transitions");
		action.setReference(reference);
		action.setConnectionPointOut(new ObjectFactory().createConnectionPointOut());
		actionBlock.getAction().add(action);
			/*
			 * Fine Action Transitions
			 */
	      
		/*
		 * Sezione Action State Manager - Action Block dello step EXEC
		 */
	
		localId ++;
		action = new ObjectFactory().createBodySFCActionBlockAction();
		action.setLocalId(new BigInteger(String.valueOf(localId)));
		action.setQualifier("N");
		action.setDuration("");
		action.setIndicator("");
		action.setRelPosition(position);
		reference = new ObjectFactory().createBodySFCActionBlockActionReference();
		reference.setName("State_Manager");
		action.setReference(reference);
		action.setConnectionPointOut(new ObjectFactory().createConnectionPointOut());
		actionBlock.getAction().add(action);
		/*
		 * Fine Action State Manager
		 */
      
		localId++;
		actionBlock.setPosition(position);
		actionBlock.setLocalId(new BigInteger(String.valueOf(localId)));
		connectionPointIn = new ConnectionPointInTag(localIdEXEC);
		
		actionBlock.setConnectionPointIn(connectionPointIn);
		
		this.sfc.getCommentOrErrorOrConnector().add(actionBlock);
     		/*
     		 * Fine sezione action Block di EXEC
     		 */
		
		/*
		 * Fine Step EXEC
		 */
		localId++;
		inVariable = new InVariableTag("FALSE", localId, position);
		this.sfc.getCommentOrErrorOrConnector().add(inVariable);
		
		localId++;
		transition = new TransitionTag(position, localId, 6, localId-1, addData);
		this.sfc.getCommentOrErrorOrConnector().add(transition);

		/*
		 * Inizio del ciclo FOR per aggiungere gli altri stati
		 */
		// prima cosa devo sapere quanti stati ci sono al I° livello per calcolare quanti simultaneouDivergence ho
		
		for (int i=0; i < listaStati[0].length; i++){
			if (listaStati[0][i] != null){
				this.setNumberStateFirstLevel(i+1);
			}
		}
	//	fileOutput.scrivi("\n\n\n\n Numero di stati al primo livello " + getNumberStateFirstLevel() + "\n\n\n\n\n");
	
		// questa lista contiene le transizioni che escono dallo stato che sto analizzando e vanno ad un altro livello
		Vector<Integer> vectorTrxOutLevel;
		boolean simultaneous = false;
		
		SimultaneousDivergenceTag simultaneousDivergence;
		SimultaneousConvergenceTag simultaneousConvergence;
		SelectionDivergenceTag  selectionDivergence;
		SelectionConvergenceTag selectionConvergence;
		
		String nameSimultaneous = "";
		/*
		 * Aggiungo lo stato di partenza del grafo
		 */
		//localId++;
		//String nomeStatoo = this.grafo.get(this.searchFistStep()).getNewName() + " " + this.grafo.get(this.searchFistStep()).getNomeStato();
		//step = new StepTag(f alse, localId, nomeStatoo, position, localId-1, addData, localId-1, "-1");
		//this.sfc.getCommentOrErrorOrConnector().add(step);
		// la funzione searchFirstStep, trova ol primo stato di avvio
		localId = DFS (this.grafo, this.grafo.get(this.searchFistStep()) , localId, position, addData);
		
		System.out.println("\n\nUltimo localId " + localId);
		
		
		
		System.out.println("ora listSelection ha " + this.getListSelection().size() + " elementi");
		
		/*
		 * Fine del ciclo FOR per aggiungere gli altri stati
		 */
		this.body.setSFC(sfc);
	}
	public void setActions(String stateManagerActionValue, String initActionValue, int numberTransitions, StateLevel[][] listaStati){
		
		
		for (int i=0; i < listaStati.length; i++){
			if (i==0){
				initActionValue = initActionValue + "\nSTATE_LEVELS[" + (i+1) + "].Current := " + this.grafo.get(this.searchFistStep()).getNumberState() + ";";
			}
			else{
			}
		initActionValue = initActionValue + "\nSTATE_LEVELS[" + (i+1) + "].Current := " + 0 + ";";
		}
		// State Init Action
		this.setAction("Inizialization", initActionValue);
		
		// State Transitions (ladder diagram)
		this.setTransitionsAction(numberTransitions);
		
		// verifico se gli stati hanno delle action , se si le aggiungo
		for (int i=0; i < listaStati.length; i++){
			for (int j=0; j < listaStati[i].length; j++){
				if (listaStati[i][j] != null){
					if (listaStati[i][j].getAction().length() > 0){
						String nameAction = listaStati[i][j].getNomeStato() + "_Action";
						this.setAction(nameAction, listaStati[i][j].getAction()); 
					}
				}
			}
		}
		// aggiungo le actions
		this.setActions(pouActions);
		
		// Verifico che ci siano dei commenti sulle transizioni del grafo, in caso affermativo, 
		// Creo un actio "AssegnaCondizioniBooleaneTRX" dove per ogni transizione che ha un commento  scrivo in ST:
		// NOME_TRANSIZIONE := CONDIZIONE BOOLEANA;
		String testo = "";
		String tmp = "";
		String nameTrx = "";
		String commento = "";
		int indice = 0;

	Collections.sort(this.vectorTransitions);
		for (String itemTrxOut: this.listTrxOutGrafo){
			indice = Integer.parseInt(itemTrxOut);
			if (this.vectorTransitions.get(indice).getComment() != null &&
				this.vectorTransitions.get(indice).getComment().getBody() != null){
				commento = this.vectorTransitions.get(indice).getComment().getBody().replace("-", "_");
				commento = commento.replaceAll("\r\n|\r|\n", " ");
				nameTrx = this.vectorTransitions.get(indice).getName();
				tmp = nameTrx + "\t\t := " + commento + ";\n";
				testo = testo + tmp;
			}else{
				// imposto la condizione a TRUE
				nameTrx = this.vectorTransitions.get(indice).getName();
				tmp = nameTrx + "\t\t := TRUE;\n";
				testo = testo + tmp;
			}
			commento = "";
			nameTrx = "";
			tmp = "";
		}
		
		this.assegnazioneTRXConValore = testo;
		;
		this.setAction("UpdateTRX", this.assegnazioneTRXConValore);
		
		// State Manager Action
		this.setAction("State_Manager", stateManagerActionValue);
		
		testo = "";
		// inserisco il valore delle variabili
		testo = testo + "\n(* Assegno il valore delle variabili dichiarate nella prima transizione di avvio della state machine *)\n";
		for (Variable item : this.vectorVariable){
			testo = testo + item.getName() + "\t\t:= " + item.getValue() + ";\n";
		}
		
		/* inizializzo STATE_LEVELS
		int primoStato = this.searchFistStep();
		String initStateLevel = "";
		

		for (int i=0; i < this.numberLivelli; i++){
			if (i == 0){
				initStateLevel = "\nSTATE_LEVELS[1].Current := " + this.grafo.get(primoStato).getNumberState() + ";";
				String indiceTrx = this.grafo.get(primoStato).getListTransitionOut().get(0);
				if (this.vectorTransitions.get(Integer.parseInt(indiceTrx)-1).getLevelTarget() == 1)
					
				initStateLevel = initStateLevel + "\nSTATE_LEVELS[1].Next := " + this.vectorTransitions.get(Integer.parseInt(this.grafo.get(primoStato).getListTransitionOut().get(0))-1).getNumberStateTarget() + ";";
			}
			else{
				initStateLevel = initStateLevel + "\nSTATE_LEVELS[" + (i+1) + "].Current := 0;";
				if (this.vectorTransitions.get(Integer.parseInt(this.grafo.get(primoStato).getListTransitionOut().get(0))-1).getLevelTarget() == (i+1))
					
					initStateLevel = initStateLevel + "\nSTATE_LEVELS[" + (i+1) +"].Next := " + this.vectorTransitions.get(Integer.parseInt(this.grafo.get(primoStato).getListTransitionOut().get(0))-1).getNumberStateTarget() + ";";
				else
					initStateLevel = initStateLevel + "\nSTATE_LEVELS[" + (i+1) +"].Next := 0;";
					
			}
			
		}*/
		
		testo = testo + "\n\n\n";
		// aggiungo tutti valori costanti FALSE che saranno utilizzati nell SFC
	/*	int ind = 0;
		String testo_Costanti ="";
		for (String itemTrxOut : this.listTrxOutGrafo){
			ind = Integer.parseInt(itemTrxOut);
			testo_Costanti = testo_Costanti + "_" +vectorTransitions.get(ind).getName() + ":= FALSE;\n";
		}
		testo = testo + testo_Costanti;
	*/
		setAction("AssegnaCondizioniBooleaneTRX", testo);
		
		// creo l'action per UpdateTRX
		
	}
	public void setTransitionsAction(int numberTransitions){
		Project.Types.Pous.Pou.Actions.Action transitionAction = new ObjectFactory().createProjectTypesPousPouActionsAction();
		Body transitionActionBody = new ObjectFactory().createBody();
		AddData addDataTransition = new ObjectFactory().createAddData();
		Body.LD ldDiagram = new ObjectFactory().createBodyLD();
		/*
		 * LeftPowerRail
		 */
		Body.SFC.LeftPowerRail leftPowerRail;
		Body.SFC.LeftPowerRail.ConnectionPointOut connectionPointOut;
		Position position;
		/*
		 * Comment
		 */
		Body.SFC.Comment comment;
		FormattedText valoreCommento;
		/*
		 * Vendor Element
		 */
		Body.SFC.VendorElement vendorElement;
		AddData addDataVendorElement;
		AddData.Data addDataDataVendorElement;
		/*
		 * Contact
		 */
		Body.SFC.Contact contact;
		ConnectionPointIn connectionPointIn;
		Connection connection;
		ConnectionPointOut contactConnectionPointOut;
		/*
		 * Coil
		 */
		Body.SFC.Coil coil;
		ConnectionPointIn coilConnectionPointIn;
		Connection coilConnection;
		ConnectionPointOut coilConnectionPointOut;
		/*
		 * Right Power Rail
		 */
		Body.SFC.RightPowerRail rightPowerRail;
		ConnectionPointIn rightConnectionPointIn;
		
		
		int localId = 0;
		int refLocalId = 0;
		position = new ObjectFactory().createPosition();
		position.setX(new BigDecimal("0"));
		position.setY(new BigDecimal("0"));
		
		/*
		 * LeftPowerrail
		 */
		leftPowerRail = new ObjectFactory().createBodySFCLeftPowerRail();
		connectionPointOut = new ObjectFactory().createBodySFCLeftPowerRailConnectionPointOut();
		
		leftPowerRail.setLocalId(new BigInteger(String.valueOf(localId)));
		
		connectionPointOut.setFormalParameter("none");
		leftPowerRail.setPosition(position);
		leftPowerRail.getConnectionPointOut().add(connectionPointOut);
		ldDiagram.getCommentOrErrorOrConnector().add(leftPowerRail);
			/* 
			 * Fine leftPowerRail
			 */
		
		int index =0;
		for (int i=0; i < this.listTrxIn.size(); i++){
			index = Integer.parseInt(this.listTrxIn.get(i).getNrTrx());
			
			
				/*
				 * Comment
				 */
			comment = new ObjectFactory().createBodySFCComment();
			localId++;
			comment.setLocalId(new BigInteger(String.valueOf(localId)));
			comment.setHeight(new BigDecimal("0"));
			comment.setWidth(new BigDecimal("0"));
			
			
			comment.setPosition(position);
			
			valoreCommento = new ObjectFactory().createFormattedText();
			try{
				valoreCommento.setAny(new JAXBElement<String>(new QName("http://www.w3.org/1999/xhtml","xhtml"), String.class, "1"));
			}
			catch(IllegalArgumentException e){
				e.printStackTrace();
			}
			
			comment.setContent(valoreCommento);
			
			ldDiagram.getCommentOrErrorOrConnector().add(comment);
				/*
				 * Fine Comment
				 */
			
				/*
				 * VendorElement
				 */
			
			vendorElement = new ObjectFactory().createBodySFCVendorElement();
			vendorElement.setPosition(position);
			
			valoreCommento = new ObjectFactory().createFormattedText();
			valoreCommento.setAny(new JAXBElement<String>(new QName("xhtml"), String.class, null));
			vendorElement.setAlternativeText(valoreCommento);
			
			addDataVendorElement = new ObjectFactory().createAddData();
			addDataDataVendorElement = new ObjectFactory().createAddDataData();
			addDataDataVendorElement.setName("http://www.3s-software.com/plcopenxml/fbdelementtype");
			addDataDataVendorElement.setHandleUnknown("implementation");
			try{
				valoreCommento.setAny(new JAXBElement<String>(new QName("ElementType"), String.class, "networktitle"));
			}
			catch(IllegalArgumentException e2)
			{
				e2.printStackTrace();
			}
			addDataDataVendorElement.setAny(valoreCommento);
			addDataVendorElement.getData().add(addDataDataVendorElement);
			vendorElement.setAddData(addDataVendorElement);
			//ldDiagram.getCommentOrErrorOrConnector().add(vendorElement);
				/*
				 * Contact
				 */
			contact = new ObjectFactory().createBodySFCContact();
			localId++;
			contact.setLocalId(new BigInteger(String.valueOf(localId)));
			
			contact.setNegated(Boolean.FALSE);
			contact.setStorage(StorageModifierType.NONE);
			contact.setEdge(EdgeModifierType.NONE);
			
			contact.setPosition(position);
				
			connectionPointIn = new ObjectFactory().createConnectionPointIn();
			connection = new ObjectFactory().createConnection();
			connection.setRefLocalId(new BigInteger(String.valueOf("0")));
			connectionPointIn.getConnection().add(connection);
			//connection.setRefLocalId(new BigInteger(String.valueOf(refLocalId)));
			
			
			contact.setConnectionPointIn(connectionPointIn);
			contactConnectionPointOut = new ObjectFactory().createConnectionPointOut();
			contact.setConnectionPointOut(contactConnectionPointOut);
			contact.setVariable(this.listTrxIn.get(i).getName());
			
			ldDiagram.getCommentOrErrorOrConnector().add(contact);
				/*
				 * Fine Contact
				 */
			
				/*
				 * Coil
				 */
			coil = new ObjectFactory().createBodySFCCoil();
			localId++;
			coil.setLocalId(new BigInteger(String.valueOf(localId)));
			coil.setNegated(false);
			coil.setStorage(StorageModifierType.NONE);			
			coil.setPosition(position);
			
			coilConnectionPointIn = new ObjectFactory().createConnectionPointIn();
			coilConnection = new ObjectFactory().createConnection();
			
			refLocalId = contact.getLocalId().intValue();
			coilConnection.setRefLocalId(new BigInteger(String.valueOf(refLocalId)));
			coilConnectionPointIn.getConnection().add(coilConnection);
			coil.setConnectionPointIn(coilConnectionPointIn);
			coilConnectionPointOut = new ObjectFactory().createConnectionPointOut();
			coil.setConnectionPointOut(coilConnectionPointOut);
			coil.setVariable("TRXs["+ index + "].Condition");
			
			ldDiagram.getCommentOrErrorOrConnector().add(coil);
				/*
				 * Fine Coil
				 */
			
		}
		
		/*
		 * rightPowerRail
		 */
	rightPowerRail = new ObjectFactory().createBodySFCRightPowerRail();
	rightPowerRail.setPosition(position);
	localId++;
	rightPowerRail.setLocalId(new BigInteger(String.valueOf(2147483646)));
	rightConnectionPointIn = new ObjectFactory().createConnectionPointIn();
	rightPowerRail.getConnectionPointIn().add(rightConnectionPointIn);
	ldDiagram.getCommentOrErrorOrConnector().add(rightPowerRail);
	
	
		transitionAction.setName("Transitions");
		transitionActionBody.setLD(ldDiagram);
		transitionAction.setBody(transitionActionBody);
		this.pouActions.getAction().add(transitionAction);
	}
	
	public void setAction(String name, String value){
		Project.Types.Pous.Pou.Actions.Action pouStateManagerAction = new ObjectFactory().createProjectTypesPousPouActionsAction();
		Body stateManagerActionBody = new ObjectFactory().createBody();
		AddData addDataStateManagerAction = new ObjectFactory().createAddData();
		FormattedText valoreStateManagerAction = new FormattedText();
		
		try{
			valoreStateManagerAction.setAny(new JAXBElement<String>( new QName("http://www.w3.org/1999/xhtml","xhtml"), String.class, value));
		}
		catch (IllegalArgumentException e){
			e.printStackTrace();
		}
		
		pouStateManagerAction.setName(name);
		stateManagerActionBody.setST(valoreStateManagerAction);
		pouStateManagerAction.setBody(stateManagerActionBody);
		pouStateManagerAction.setAddData(addDataStateManagerAction);
		
		this.pouActions.getAction().add(pouStateManagerAction);
		
		
	}
	
	private void setGlobalInterfaceVars(int nrTrxOutGrafo){
		DataType dataTypeVar; 
		VarListPlain.Variable variabile;
		int indice = 0;
		// prima di tutto creao le variabili globali che sono indicate per le transizioni.
		// una variabile globale TRX_XX è di tipo booleana, una per ogni transizione del grafo.
		// lo stesso nome di variabile è utilizzato nel source nel LADDER e nel SFC
		for (String itemTrxOut : this.listTrxOutGrafo){
			indice = Integer.parseInt(itemTrxOut);
			dataTypeVar = new ObjectFactory().createDataType();
			variabile = new ObjectFactory().createVarListPlainVariable();
			dataTypeVar.setBOOL(false);
			variabile.setName(vectorTransitions.get(indice).getName());
			variabile.setType(dataTypeVar);
			this.globalInterfaceVars.getVariable().add(variabile);
		}
		
		for (Variable item : this.vectorVariable){
			if (item.getScopeVariable().equals("GLOBAL")){
				variabile = new ObjectFactory().createVarListPlainVariable();
				dataTypeVar = loadTypeVar(item.getName(), item.getType());
				variabile.setType(dataTypeVar);
				variabile.setName(item.getName());
				this.globalInterfaceVars.getVariable().add(variabile);
			}
		}
	}
	public void setLocalInterfaceConstants(int numberTransitions){
		DataType dataTypeBool = new ObjectFactory().createDataType();
		VarListPlain.Variable varCostante;
		Value_.SimpleValue simpleValue;
		Value_ valoreCostante; 
		int indice = 0;
		/*
		 * Creo delle costanti tutte FALSE che saranno utilizzate per il diagramma SFC in modo che non si può transitare da uno stato all'altro
		 * il nome sarà: _NOME_TRX
		 */
		for (String itemTrxOut : this.listTrxOutGrafo){
			indice = Integer.parseInt(itemTrxOut);
			dataTypeBool = new ObjectFactory().createDataType();
			varCostante = new ObjectFactory().createVarListPlainVariable();
			dataTypeBool.setBOOL(false);
			varCostante.setName("_" + vectorTransitions.get(indice).getName());
			varCostante.setType(dataTypeBool);
			valoreCostante = new ObjectFactory().createValue();
			simpleValue = new ObjectFactory().createValueSimpleValue();
			simpleValue.setValue("FALSE");
			valoreCostante.setSimpleValue(simpleValue);
			varCostante.setInitialValue(valoreCostante);
			this.constantsLocalVars.getVariable().add(varCostante);
		}
		/********************** Variabili CONSTANT impostate perchè presenti nell'uml******************/
		DataType dataTypeVar = new ObjectFactory().createDataType();
		VarListPlain.Variable variabile;
		for (Variable item : this.vectorVariable){
			if (item.getScopeVariable().equals("CONSTANT")){
				variabile = new ObjectFactory().createVarListPlainVariable();
				dataTypeVar = loadTypeVar(item.getName(), item.getType());
				variabile.setType(dataTypeVar);
				variabile.setName(item.getName());
				this.constantsLocalVars.getVariable().add(variabile);
			}
		}
		
		/***********************************************************************************/
		/*
		   COMMENTO PERCHè DEVO VEDERE SE LE DEVO DICHIARARE QUI O IN INPUT COME è IMPOSTATO PER IL MOMENTO
		dataTypeBool.setBOOL(true);
		simpleValue.setValue("FALSE");
		valoreCostante.setSimpleValue(simpleValue);
		for (int i=0; i < numberTransitions; i++){
			varCostante = new ObjectFactory().createVarListPlainVariable();
			varCostante.setName("TRX" + String.valueOf(i));
			varCostante.setType(dataTypeBool);
			varCostante.setInitialValue(valoreCostante);
			this.constantsLocalVars.getVariable().add(varCostante);
		}
		
		*/
	}
	public void setInputInterfaceVars(ArrayList<Trx_In> inputVarList){
		DataType dataTypeBool = new ObjectFactory().createDataType();
		DataType dataTypeDInt = new ObjectFactory().createDataType();
		VarListPlain.Variable varIndex = new ObjectFactory().createVarListPlainVariable();
		VarListPlain.Variable varLevel_Curr = new ObjectFactory().createVarListPlainVariable();
		VarListPlain.Variable varLevel_Next = new ObjectFactory().createVarListPlainVariable();
		Value_ value;
		Value_.SimpleValue simpleValue;
		
		/********************** Variabili INPUT impostate perchè presenti nell'uml******************/
		DataType dataTypeVar = new ObjectFactory().createDataType();
		VarListPlain.Variable variabile;
		for (Variable item : this.vectorVariable){
			if (item.getScopeVariable().equals("INPUT")){
				variabile = new ObjectFactory().createVarListPlainVariable();
				dataTypeVar = loadTypeVar(item.getName(), item.getType());
				variabile.setType(dataTypeVar);
				variabile.setName(item.getName());
				this.inputInterfaceVars.getVariable().add(variabile);
			}
		}
		
		/***********************************************************************************/
		dataTypeBool.setBOOL(true);
		
		
		dataTypeDInt.setDINT(1);
		varIndex.setName(ConstantString.INDEX);
		varIndex.setType(dataTypeDInt);
		
		varLevel_Curr.setName(ConstantString.LEVEL_CURR);
		varLevel_Curr.setType(dataTypeDInt);
		varLevel_Next.setName(ConstantString.LEVEL_NEXT);
		varLevel_Next.setType(dataTypeDInt);
		
	}

	private void setTempInterfaceVars(){
		/********************** Variabili TEMP impostate perchè presenti nell'uml******************/
		DataType dataTypeVar = new ObjectFactory().createDataType();
		VarListPlain.Variable variabile;
		for (Variable item : this.vectorVariable){
			if (item.getScopeVariable().equals("TEMP")){
				variabile = new ObjectFactory().createVarListPlainVariable();
				dataTypeVar = loadTypeVar(item.getName(), item.getType());
				variabile.setType(dataTypeVar);
				variabile.setName(item.getName());
				this.tempInterfaceVars.getVariable().add(variabile);
			}
		}
		
		/***********************************************************************************/
	}
	private void setOutputInterfaceVars(){
		/********************** Variabili OUTPUT impostate perchè presenti nell'uml******************/
		DataType dataTypeVar = new ObjectFactory().createDataType();
		VarListPlain.Variable variabile;
		for (Variable item : this.vectorVariable){
			if (item.getScopeVariable().equals("OUTPUT")){
				variabile = new ObjectFactory().createVarListPlainVariable();
				dataTypeVar = loadTypeVar(item.getName(), item.getType());
				variabile.setType(dataTypeVar);
				variabile.setName(item.getName());
				this.outputInterfaceVars.getVariable().add(variabile);
			}
		}
		
		/***********************************************************************************/
	}
	private void setLocalInterfaceVars(int numTransition, int numberStateLevels){
		DataType dataTypeDInt = new ObjectFactory().createDataType();
		VarListPlain.Variable varIndex = new ObjectFactory().createVarListPlainVariable();
		VarListPlain.Variable varLevel_Curr = new ObjectFactory().createVarListPlainVariable();
		VarListPlain.Variable varLevel_Next = new ObjectFactory().createVarListPlainVariable();
		// variabili per l'array delle transitions
		VarListPlain.Variable varArray = new ObjectFactory().createVarListPlainVariable();
		DataType.Array array = new ObjectFactory().createDataTypeArray();
		DataType.Derived derived = new ObjectFactory().createDataTypeDerived();
		DataType dataTypeDerived = new ObjectFactory().createDataType();
		DataType dataTypeVarArray = new ObjectFactory().createDataType();
		RangeSigned range = new ObjectFactory().createRangeSigned();
		FormattedText valoreDocumentationArray = new ObjectFactory().createFormattedText();
		// variabili per l'array degli state_levels
		VarListPlain.Variable varStateLevels = new ObjectFactory().createVarListPlainVariable();
		DataType.Array arrayStateLevels = new ObjectFactory().createDataTypeArray();
		DataType dataTypeDerivedState = new ObjectFactory().createDataType();
		DataType dataTypeArrayStateLevels = new ObjectFactory().createDataType();
		RangeSigned rangeArrayStateLevels = new ObjectFactory().createRangeSigned();
		DataType.Derived derivedArrayStateLevels = new ObjectFactory().createDataTypeDerived();
		
		
		/********************** Variabili LOCALI impostate perchè presenti nell'uml******************/
		DataType dataTypeVar = new ObjectFactory().createDataType();
		VarListPlain.Variable variabile;
		for (Variable item : this.vectorVariable){
			if (item.getScopeVariable().equals("LOCAL")){
				variabile = new ObjectFactory().createVarListPlainVariable();
				dataTypeVar = loadTypeVar(item.getName(), item.getType());
				variabile.setType(dataTypeVar);
				variabile.setName(item.getName());
				this.localInterfaceVars.getVariable().add(variabile);
			}
		}
		
		/***********************************************************************************/
		dataTypeDInt.setDINT(1);
		varIndex.setName(ConstantString.INDEX);
		varIndex.setType(dataTypeDInt);
		this.localInterfaceVars.getVariable().add(varIndex);
		varLevel_Curr.setName(ConstantString.LEVEL_CURR);
		varLevel_Curr.setType(dataTypeDInt);
		this.localInterfaceVars.getVariable().add(varLevel_Curr);
		varLevel_Next.setName(ConstantString.LEVEL_NEXT);
		varLevel_Next.setType(dataTypeDInt);
		this.localInterfaceVars.getVariable().add(varLevel_Next);
		
		
		/*
		 * Creo l'array delle Transitions
		 */
		derived.setName(ConstantString.TRANSITIONS);
			
		range.setLower(String.valueOf("1"));
		range.setUpper(String.valueOf(numTransition));
		dataTypeDerived.setDerived(derived);
		array.getDimension().add(range);
		array.setBaseType(dataTypeDerived);
		dataTypeVarArray.setArray(array);
		varArray.setType(dataTypeVarArray);
		varArray.setName(ConstantString.NAME_ARRAY_TRANSITIONS);
		try{
			valoreDocumentationArray.setAny(new JAXBElement<String>( new QName("http://www.w3.org/1999/xhtml","xhtml"), String.class, ConstantString.ARRAY_TRXS_DOCUMENTATION));
		}
		catch (IllegalArgumentException e){
			e.printStackTrace();
		}
		varArray.setDocumentation(valoreDocumentationArray);
		this.localInterfaceVars.getVariable().add(varArray);
		
		
		/*
		 * Creo l'array degli State_levels
		 */
		rangeArrayStateLevels.setLower(String.valueOf("1"));
		rangeArrayStateLevels.setUpper(String.valueOf(numberStateLevels));
		arrayStateLevels.getDimension().add(rangeArrayStateLevels);
		derivedArrayStateLevels.setName(ConstantString.STATE);
		dataTypeDerivedState.setDerived(derivedArrayStateLevels);
		arrayStateLevels.setBaseType(dataTypeDerivedState);
		dataTypeArrayStateLevels.setArray(arrayStateLevels);
		varStateLevels.setName(ConstantString.NAME_ARRAY_STATE_LEVELS);
		varStateLevels.setType(dataTypeArrayStateLevels);
		FormattedText commentoArrayStateLevels = new ObjectFactory().createFormattedText();
		
		try{
			commentoArrayStateLevels.setAny(new JAXBElement<String>( new QName("http://www.w3.org/1999/xhtml","xhtml"), String.class, ConstantString.ARRAY_STATE_LEVELS_DOCUMENTATION));
		}
		catch (IllegalArgumentException e4){
			e4.printStackTrace();
		}
		varStateLevels.setDocumentation(commentoArrayStateLevels);
		this.localInterfaceVars.getVariable().add(varStateLevels);
	}
	
	/**
	 * Set il valore del testo generato in GestioneAnalisiModello.booleanCombinationStates
	 * 
	 * @param value
	 */
	public void setStateManagerValue(String value){
		this.statManagerValue = value;
	}
	/**
	 * Restituisce il valore impostato
	 * @return statManagerValue
	 */
	public String getStateManagerValue(){
		return this.statManagerValue;
	}
	/**
	 * Imposta il valore di numberStateFirstLevel
	 * @param value
	 */
	public void setNumberStateFirstLevel(int value){
		this.numberStateFirstLevel = value;
	}
	/**
	 * restituisce il valore di numberStateFirstLevel
	 * @return
	 */
	public int getNumberStateFirstLevel(){
		return this.numberStateFirstLevel;
	}
	
	
	/**
	 * Controllo se ci sono delle transizioni di tipo DEFAULT nello stato corrente che sto analizzando
	 * @param listaStati		: lista degli stati
	 * @param vectorTransitions	: lista delle transizioni
	 * @param i					: indice di livello
	 * @param j					: numero dello stato
	 * @return statoDefaultTrx	: se TRUE c'è una una transizione di DEFAULT
	 */
	private boolean controlloStatoDefaultTrx(StateLevel[][] listaStati,
											Vector<it.moise.plc.uml.Transitions> vectorTransitions,
											int i,
											int j){
		boolean statoDefaultTrx = false;
		
		for(int k=0; k < listaStati[i][j].getListTransitionOut().size(); k++){
			int index = Integer.parseInt((listaStati[i][j].getListTransitionOut().get(k)));
			if (vectorTransitions.get(index-1).getNameStateSource().equals("DEFAULT")){
				statoDefaultTrx = true;
			}
		}
			
		return statoDefaultTrx;
	}
	/**
	 * Metodo che cerca se tutte le transizioni hanno come target un livello differente dal livello attuale
	 * @param listaStati		: lista stati
	 * @param vectorTransitions	: lista transizioni
	 * @param i					: livello
	 * @param j					: numero stato
	 * @return	allTrxOutLevel	: se TRUE è vera la condizione
	 */
	private boolean allTrxOutOfLevel(StateLevel[][] listaStati,
											Vector<it.moise.plc.uml.Transitions> vectorTransitions,
											int i,
											int j){
		boolean allTrxOutLevel = false;
		boolean modificato = true;
		int levelStato;
		int levelTarget;
		int trx;
		for (int k = 0; k < listaStati[i][j].getListTransitionOut().size(); k++){
			levelStato = listaStati[i][j].getLevelStato();
			levelTarget = vectorTransitions.get(Integer.parseInt(listaStati[i][j].getListTransitionOut().get(k))-1).getLevelTarget();
			trx = Integer.parseInt(listaStati[i][j].getListTransitionOut().get(k))-1;
			if (levelStato != levelTarget){
				allTrxOutLevel = true;
			}
			else{
				allTrxOutLevel = false;
				modificato = false;
			}
		}
		if (modificato == false)
			allTrxOutLevel = false;
		return allTrxOutLevel;
	}
	
	/**
	 * Metodo che cerca tutte le transizioni che partono da un choice e restituisce un vettore contenente il numero delle transizioni
	 * @param idChoice			: id del choice
	 * @param vectorTransitions	: lista transizioni
	 * @return	trxStartFromChoice: lista delle transizioni che partono dal choice
	 */
	private Vector<Integer> searchTrxStartFromChoice(String idChoice, Vector<it.moise.plc.uml.Transitions> vectorTransitions){
		Vector<Integer> trxStartFromChoice = new Vector<Integer>();
		for (int i=0; i < vectorTransitions.size(); i++){
			if (vectorTransitions.get(i).getIdStateSource() != null){
				if (vectorTransitions.get(i).getIdStateSource().equals(idChoice)){
					trxStartFromChoice.add(i);
				}
			}
		}
		return trxStartFromChoice;
	}
	
	private int determinaQuanteTrxEsconoFromState(StateLevel stateLevel, Vector<it.moise.plc.uml.Transitions> vectorTransitions){
		int numberTrx=0;
		for (int i=0; i < stateLevel.getListTransitionOut().size(); i++){
			int trx=Integer.parseInt(stateLevel.getListTransitionOut().get(i))-1;
			
			if (!vectorTransitions.get(trx).getChoice()){
				numberTrx++;
			}else {
				Vector<Integer> listTrx = new Vector<Integer>();
				String idChoice = vectorTransitions.get(trx).getIdStateTarget();
				listTrx = searchTrxStartFromChoice(idChoice, vectorTransitions);
				//if (vectorTransitions.get(trx).getChoiceStart()){
				numberTrx = listTrx.size();
				
			}
		}
		return numberTrx;
	}
	/**
	 * Restituisce l'indice del grafo che indica chi è il primo step di avvio
	 * @return firstStep
	 */
	private int searchFistStep(){
		int firstStep = 0;
		int i = 0;
		for (StateLevel item : this.grafo){
			
			if (item.getStatoDiAvvio()){
				firstStep = i;
			}
			i++;
		}
		return firstStep;
	}
	
	
	/**
	 * Metodo
	 * @param grafo
	 * @param u
	 */
	private int DFS(ArrayList<StateLevel> grafo, StateLevel u, int localId, Position position, AddData addData){
		StateLevel v = new StateLevel();
		int nrAdiacenti = u.getListaAdiacenti().size();
		boolean selection = false;
		boolean attentionAction = false;
		String selectionName = "-1";
		int localIdSelection = -1;
		//String action = v.getAction();
		
		localId = visitaNodo(u, localId, selectionName, position, localIdSelection);	// visita il nodo e mi restituisce il localId maggiorato
		//System.out.println("Il nodo: " + u.getNewName() + " ha nodi adiacenti sono : " + nrAdiacenti);
		
		
		if (nrAdiacenti > 1){
			localId++;
			System.out.println("\n\n il nodo u=" + u.getNewName() + " deve fare una selection - "
					+ "Questo è il localId da dare alla selection " + localId  +" \n");
			selection = true;
			selectionName = "selection"+localId;
			localIdSelection = localId;
			this.setAddListSelection(new it.moise.plc.uml.jaxb_plcopen.SelectionDivConv("selection"+localId, localId));
			if (u.getAction().length() > 0)
				this.selectionDivergence = new SelectionDivergenceTag(localId, position, localId-3, nrAdiacenti, addData);
			else
				this.selectionDivergence = new SelectionDivergenceTag(localId, position, localId-1, nrAdiacenti, addData);
			this.sfc.getCommentOrErrorOrConnector().add(selectionDivergence);
		}
		
		
		
		this.grafo.get(getNrNodoFromNomeNodo(this.grafo, u.getNewName())).setVisitato(true);
		int numeroNodo;
		for (int i = 0; i < nrAdiacenti; i++){
						
			v = this.grafo.get(getNrNodoFromNomeNodo(this.grafo, u.getListaAdiacenti().get(i)));
			if (u.getAction().length() > 0)
				attentionAction = true;
			localId=visitaArco(u,v, localId, position, selection, localIdSelection, attentionAction);
			
			if (!v.getVisitato()){
				localId = DFS(this.grafo, v, localId, position, addData);
				// verifico se è un nodo foglia
				numeroNodo = getNrNodoFromNomeNodo(this.grafo, v.getNewName());
				if (this.grafo.get(numeroNodo).getListaAdiacenti().size() == 0){
					//System.out.println(this.grafo.get(numeroNodo).getNewName() + " nodo FOGLIA");
					for (int l=0; l < this.getListSelection().size(); l++){
						if (this.getListSelection().get(l).getName().equals(selectionName)){
							if (this.grafo.get(numeroNodo).getAction().length() > 0)
								this.getListSelection().get(l).getListRefLocalId().add(localId-2);
							else
								this.getListSelection().get(l).getListRefLocalId().add(localId);
						}
					}
				}
			}else{
				localId++;
				System.out.println("Nodo " + v.getNewName() + " già visitato faccio un JumpStep - localId = " + localId);
				// il nodo è già stato visitato
				// devo fare uno jumpStep
				String nomeStato = v.getNewName() + "_" + v.getNomeStato();
				this.jumpStep = new JumpStepTag(nomeStato, position, localId, addData);
				this.sfc.getCommentOrErrorOrConnector().add(jumpStep);
				if (this.getListSelection().size() > 0){
					this.getListSelection().get(this.getListSelection().size()-1).getListRefLocalId().add(localId);
				}
							
			}
			
		}
		if (selection){
			
			localId++;
			int ultimaSelection = this.getListSelection().size()-1;
			
			System.out.println("\n\n Chiudo la selection con ConvergenceSelection : " + localId);
			this.selectionConvergence = new SelectionConvergenceTag(
											localId, 
											this.getListSelection().get(ultimaSelection).getListRefLocalId(), 
											u.getListaAdiacenti().size());
			this.sfc.getCommentOrErrorOrConnector().add(selectionConvergence);
			System.out.println("listSelection ha nr. " + (ultimaSelection+1));
			System.out.println("chiudo la selection con il nome : " + this.getListSelection().get(ultimaSelection).getName());
			this.getListSelection().remove(ultimaSelection);
			ultimaSelection = this.getListSelection().size()-1;
			System.out.println("Ora listSelection ha nr. " + (ultimaSelection+1));
			
			// devo inserire l'id della convergence nel refLocalId della convergence precedente
			if (this.getListSelection().size() > 0){
				ultimaSelection = this.getListSelection().size()-1;
				this.getListSelection().get(ultimaSelection).getListRefLocalId().add(localId);
			}
		}
		
		return localId;
	}
	public static int getNrNodoFromNomeNodo(ArrayList<StateLevel> listaNodi, String nomeNodo){
		int i=0;
		int nrNodo=-1;
		for(StateLevel item : listaNodi){
			if (item.getNewName().equals(nomeNodo))
				nrNodo = i;
			i++;
		}
		return nrNodo;
	}
	
	public int visitaNodo(StateLevel u, int localId, String selectionName, Position position, int localIdSelection){
		
		String nomeSelection = "";
		int numeroNodo; 
		String action = u.getAction();
		u.setNomeStato(u.getNomeStato().replace(".", "_"));
		String nameAction = u.getNomeStato() + "_Action"; 
		String nomeStato = u.getNewName() + "_" + u.getNomeStato();
		localId++;
		System.out.println("Il nodo u="+u.getNewName() + " è stato visitato - localId = " + localId);
		numeroNodo = getNrNodoFromNomeNodo(this.grafo, u.getNewName());

		if (!selectionName.equals("-1")){
			this.step = new StepTag(false, localId, nomeStato, position, localIdSelection, addData, localId-1, selectionName);
			// lo step appartiene ad una selection
			// verifico che sia un nodo FOGLIA, in caso affermativo aggiungo il suo ID nell'array refLocalId della selection
			if (u.getListaAdiacenti().size() == 0){
				// è un nodo FOGLIA
				for (int i=0; i < this.getListSelection().size(); i++){
					if (this.getListSelection().get(i).getName().equals(selectionName)){
						this.getListSelection().get(i).getListRefLocalId().add(localId);
					}
				}
			}
			this.sfc.getCommentOrErrorOrConnector().add(step);
			if (action.length() > 0){
				// aggiungo l'action
				localId=addActionBlock(action, localId, position, nameAction);
				
			}
		}else{
			this.step = new StepTag(false, localId, nomeStato, position, localId-1, addData, localId-1, selectionName);
			// verifico se ci sono delle action
			this.sfc.getCommentOrErrorOrConnector().add(step);
			if (action.length() > 0){
				// aggiungo l'action
				localId =addActionBlock(action, localId, position, nameAction);
				
			}
		}
		
		
		
		// 		+ "
		//System.out.println("\t devo aggiungere al nodo il nome della selection\n"
		//		+ "\t devo controllare se è un nodo foglia e/o già visitato per fare un jumpStep, in caso affaermativo"
		//		+ "\n in entrambi i casi devo chiudere la selection, quindi aggiungere il refLocalId alla selction di appartenenza");
		//
		return localId;
	}
	public int visitaArco(StateLevel u, StateLevel v, int localId, Position position, boolean selection, int localIdSelection, boolean attentionAction){
		Trx_In trx_IN;
		System.out.println("\t\t\tl'arco (u,v) = ("+u.getNewName() +"," + v.getNewName() + ")  è stato visitato");
		/*
		 * devo cercare la il numero di trx che mi porta dal nodo u a v
		 */
		int nrNodo = getNrNodoFromNomeNodo(this.grafo, v.getNewName());
		int nrTrx = 0;
		int i=0;
		for (it.moise.plc.uml.Transitions itemTrx : this.vectorTransitions){
			if (itemTrx.getNewNameStateSource().equals(u.getNewName()) && 
				itemTrx.getNewNameStateTarget().equals(v.getNewName())){
				nrTrx = i+1;
			}
			i++;	
		} // k
		localId++;
		this.inVariable = new InVariableTag("_" + this.vectorTransitions.get((nrTrx-1)).getName(), localId, position);
		this.vectorTransitions.get(nrTrx-1).setTrxUtilizzatoInSFC(true);
		
		this.sfc.getCommentOrErrorOrConnector().add(inVariable);
		localId++;
		if (selection){
			
				this.transition = new TransitionTag(position, localId, localIdSelection, localId-1, addData);
		}else{
			if (attentionAction)
				this.transition = new TransitionTag(position, localId, localId-4, localId-1, addData);
			else
				this.transition = new TransitionTag(position, localId, localId-2, localId-1, addData);
		}
		
		this.sfc.getCommentOrErrorOrConnector().add(transition);
		System.out.println("Aggiunto var TRX=" + nrTrx + "  localId=" + localId);
		return localId;
	}
	/**
	 * Verifica se il nodo passato come parametro è un nodo foglia o meno
	 * @param u	: StateLevel
	 * @return isNodoFoglia
	 */
	private boolean isNodoFoglia(StateLevel u){
		boolean isNodoFoglia = false;
		if (u.getListaAdiacenti().size() == 0)
			isNodoFoglia = true;
		else
			isNodoFoglia = false;
		return isNodoFoglia;
	}
	/**
	 * Aggiunge un elemento alla listaSelection 
	 * @param selection
	 */
	private void setAddListSelection(it.moise.plc.uml.jaxb_plcopen.SelectionDivConv selection){
		this.listSelection.add(selection);
	}
	/**
	 * Ritorna la la lista delle SelectionDivConv
	 * @return listSelection
	 */
	private Vector<it.moise.plc.uml.jaxb_plcopen.SelectionDivConv> getListSelection(){
		return this.listSelection;
	}
	
	private DataType loadTypeVar(String name, String type){
		Derived derived;
		DataType data = new ObjectFactory().createDataType();
		if (this.controlloTipoVariabile(type)){
			switch (type){
			case "BOOL":
				data.setBOOL(true);
				break;
			case "BYTE":
				data.setBYTE(null);
				break;
			case "BIT":
				data.setANYBIT(1);
				break;
			case "WORD":
				data.setWORD("WORD");
				break;
			case "DWORD":
				data.setDWORD("DWORD");
				break;
			case "LWORD":
				data.setLWORD("LWORD");
				break;
			case "SINT":
				data.setSINT(1);
				break;
			case "USINT":
				data.setUSINT(1);
				break;
			case "INT":
				data.setINT(1);
				break;
			case "UINT":
				data.setUINT(1);
				break;
			case "LINT":
				data.setLINT(1);
				break;
			case "DINT":
				data.setDINT(1);
				break;
			case "REAL":
				data.setREAL(1);
				break;
			case "LREAL":
				data.setLREAL(1);
				break;
			case "STRING":
				data.setANYSTRING("STRING");
				break;
			case "TIME":
				data.setTIME(1);
				break;
			case "TOD":
				data.setTOD(1);
				break;
			case "DATE":
				data.setDATE(1);
				break;
			case "DT":
				data.setDT(1);
				break;
			}
		}else{
			for (String item : this.vectorUserType){
					if (item.equals(type)){
						derived = new ObjectFactory().createDataTypeDerived();
						derived.setName(type);
						data.setDerived(derived);
					}
				}
				
		}
		return data;
	}
	
	public int addActionBlock(String actionValue, int localId, Position position, String nameAction){
		Body.SFC.ActionBlock.Action action;
		Body.SFC.ActionBlock.Action.Reference reference;
		Body.SFC.ActionBlock actionBlock;
		actionBlock = new ObjectFactory().createBodySFCActionBlock();
		int localIdNew = localId;
		/*
		 * Sezione Action Transitions - Action Block dello step EXEC
		 */
	
		localIdNew ++;
		action = new ObjectFactory().createBodySFCActionBlockAction();
		action.setLocalId(new BigInteger(String.valueOf(localIdNew)));
		action.setQualifier("P");
		action.setDuration("");
		action.setIndicator("");
		action.setRelPosition(position);
		reference = new ObjectFactory().createBodySFCActionBlockActionReference();
		reference.setName(nameAction);
		action.setReference(reference);
		action.setConnectionPointOut(new ObjectFactory().createConnectionPointOut());
		actionBlock.getAction().add(action);
		localIdNew++;
		actionBlock.setPosition(position);
		actionBlock.setLocalId(new BigInteger(String.valueOf(localIdNew)));
		ConnectionPointInTag connectionPointIn = new ConnectionPointInTag(localId);
		
		actionBlock.setConnectionPointIn(connectionPointIn);
		
		this.sfc.getCommentOrErrorOrConnector().add(actionBlock);
		return localIdNew;
	}
	/**
	 * Effettua il controllo del tipoVariabile con i tipi che possono essere accettati, restiruisce TRUE se il tipo è corretto
	 * @param tipoVar
	 * @return ris=TRUE	tipo accettato
	 */
	private boolean controlloTipoVariabile(String tipoVar){
		boolean ris = false;
		for (int i=0; i < this.tipiVariabili.length; i++){
			if (tipoVar.equals(this.tipiVariabili[i])){
				ris = true;
			}
		}
		return ris;
	}
}
