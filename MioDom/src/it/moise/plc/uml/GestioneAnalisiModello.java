/**
 * 
 */
package it.moise.plc.uml;

import it.moise.plc.uml.pseudostate.Initial;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import javax.swing.JTextArea;

/**
 * Questa classe contiene una serie di metodi per analizzare il modello (Model) che viene passato come parametro
 * scrivere sui file di testo gli elenchi delle transizioni, delle regioni, degli stati
 * @author De Carvalho Moise
 * 
 *
 */
public class GestioneAnalisiModello {
	private String nomeFileStati = "listaStati.txt";
	private String nomeFileRegioni = "listaRegioni.txt";
	private String nomeFileTransizioni = "listaTransizioni.txt";
	
	private String stateManagerActionValue = "";
	private String initActionValue = "";
	private String sfcFakeValue = "";
	// Questa lista mette in corrispondenza il suo indice con il nome della transizione nel'UML per non perdere l'associazione
	private ArrayList<String> listTRXsWithOriginalName = new ArrayList<String>(); 	
	
	private StateLevel[][] arrayStateLevelDefinitivo;
	// associa gli indici di vectorTransitions con l'indice di TRXs
	private ArrayList<Trx_In> listTrxIn = new ArrayList<Trx_In>();
	
	private JTextArea txtArea;
	private String msg = "";
	private GlobalVar globalVar = new GlobalVar();
	
	private boolean console=false;
	
	private Model modello = new Model();
	/**
	 * Costruttore di classe con parametr
	 * @param modello Contiene tutto il modello UML analizzato
	 * @param globalVar Oggetto che contiene tutte le variabili globali
	 * @param txtArea JTextArea per visualizzare i messaggi tipo console
	 */
public GestioneAnalisiModello(Model modello, GlobalVar globalVar, JTextArea txtArea){
		this.globalVar = globalVar;
		this.txtArea = txtArea;
		this.modello = modello;
	}
	/**
	 * Costruttore con parametri
	 * @param txtArea JTextArea per visualizzare i messaggi tipo console
	 * @param console Valore booleano, se TRUE indica che il programma è stato lanciato da riga di comando
	 */
	public GestioneAnalisiModello(JTextArea txtArea, boolean console) {
		this.txtArea = txtArea;
		this.console = console;
	}
	
	/** 
	 * Crea il file SFC_Fake_State_Manager che contiene l'insieme delle transizioni,
	 * creando su file la combinazione degli stati mediante l'analisi delle transizioni e degli stati
	 * @param arrayTransitions Array bidimensionale che contiene tutte le transizioni
	 * @param listaStati Array bidimensionale che contiene tutti gli stati rilevati
	 * @param numberState Indica il numero degli stati
	 */
	public void booleanCombinationStates(int ntransitions, StateLevel[][] listaStati, int numberState){
		//ScriviSuFile stateManagerFile = new ScriviSuFile("SFCFake_State_Manager.txt");
		String testoState = "";
		String testo = "";
		String idTarget = "";
		String idState = "";
		String idStateLevelUp = "";
		String legendState = "";
		int nLevel = listaStati.length;
		
		StateLevel stateLevelTemp = new StateLevel();
		String newNameState= String.valueOf(Character.toChars(65));
		boolean raggiungibileDaAltroStato = false;
		stampaSystemOutTxtArea("\n->Creazione dello SFC State_Manager che contiene l'insieme delle transizioni");
		// istanzio l'array, che conterrà tutti gli stati con i nomi che saranno utilizzati nel progetto codesys, e conterrà anche tutte le transizioni
		// in entrata ed in uscita per ogni stato
		this.arrayStateLevelDefinitivo = new StateLevel[listaStati.length][numberState];
		
		testo = "FOR LEVEL_NEXT := 1 TO " + nLevel +" DO\n";
		testo = testo + "\tSTATE_LEVELS[LEVEL_NEXT].Next := STATE_LEVELS[LEVEL_NEXT].Current;\n";
		testo = testo + "END_FOR\n\n\n";
		testo = testo + "FOR INDEX := 1 TO " + ntransitions + " DO\n";
		testo = testo + "\tFOR LEVEL_CURR := 1 TO " + nLevel  +" DO;\n";		
		testo = testo + "\t\tIF (STATE_LEVELS[LEVEL_CURR].Current = TRXs[INDEX].Source[LEVEL_CURR]) THEN\n";
		testo = testo + "\t\t\tIF TRXs[INDEX].Condition THEN";
		testo = testo + "\t\t\t\tFOR LEVEL_NEXT := 1 TO " + nLevel +" DO\n";
		testo = testo + "\t\t\t\t\tSTATE_LEVELS[LEVEL_NEXT].Next := TRXs[INDEX].Target[LEVEL_NEXT];\n";
		testo = testo + "\t\t\t\tEND_FOR\n";
		testo = testo + "\t\t\t\tEXIT;\n";
		testo = testo + "\t\t\tEND_IF\n";
		testo = testo + "\t\tEND_IF\n";
		testo = testo + "\tEND_FOR\n";
		testo = testo + "END_FOR\n\n\n";
		
		testo = testo + "FOR LEVEL_NEXT := 1 TO " + nLevel + " DO\n";
		testo = testo + "\tSTATE_LEVELS[LEVEL_NEXT].Current := STATE_LEVELS[LEVEL_NEXT].Next;\n";
		testo = testo + "END_FOR\n";
	
		//stateManagerFile.scrivi(testo);
		testo = testo + "\n\n\n";
		for (int i = 0; i < listaStati.length; i++){
			for(int j=0; j < listaStati[i].length; j++){
				if (listaStati[i][j] != null){
					// copio il singolo stato nell'array stateLevelDefinitivo
					this.arrayStateLevelDefinitivo[i][j] = listaStati[i][j];
					
					idState = listaStati[i][j].getIdStato();
					// per ogni livello creo una variabile per ogni stato che incontro
					newNameState= String.valueOf(Character.toChars(65 + i));
					// inserisco il nome deifinito dello stato
					this.arrayStateLevelDefinitivo[i][j].setNomeStato(listaStati[i][j].getNomeStato());
					// imposto il nuovo nome dello stato nell'arrayStateLevel
					this.arrayStateLevelDefinitivo[i][j].setNewName(newNameState + listaStati[i][j].getNumberState());
					newNameState = newNameState + + listaStati[i][j].getNumberState() + "_" + listaStati[i][j].getNomeStato().replace(".", "_") + "._x";
					legendState = legendState  + "(*" + newNameState + "\t" + listaStati[i][j].getNomeStato() +" ; *)\n";
					testoState = "\n" + newNameState + " := (STATE_LEVELS[" + (i+1) +"].Current = " + listaStati[i][j].getNumberState() + ")";
					
					idStateLevelUp = listaStati[i][j].getIdStateLevelUp();
					if (idStateLevelUp != "-1"){
						
						stateLevelTemp = this.getStateLevelFromListaStati(listaStati, idStateLevelUp);
						testoState = testoState +" AND (STATE_LEVELS[" + stateLevelTemp.getLevelStato() +"].Current = " + stateLevelTemp.getNumberState() + ")";
						
						idStateLevelUp = stateLevelTemp.getIdStateLevelUp();
						while (idStateLevelUp != "-1"){
							idStateLevelUp = stateLevelTemp.getIdStateLevelUp();
							stateLevelTemp = this.getStateLevelFromListaStati(listaStati, idStateLevelUp);
							testoState = testoState +" AND (STATE_LEVELS[" + stateLevelTemp.getLevelStato() +"].Current = " + stateLevelTemp.getNumberState() +")";
							idStateLevelUp = stateLevelTemp.getIdStateLevelUp();
					}
						
					}
					
					//stateManagerFile.scrivi(testoState);
					testo = testo + testoState +";";
					raggiungibileDaAltroStato = false;
				}
			}
		}
		
		//testo = testo + "\n\n\n AssegnaCondizioniBooleaneTRX();\n"; 
		//stateManagerFile.scrivi("\n\n\n\n" + legendState);
		//scrivo il valore del testo di StateManager nella variabile che posso riutilizzare
		this.globalVar.setStateManagerActionValue(testo + "\n\n\n" + legendState  );
	}
	
		
	/**
	 * Restituisce lo stateLevel dalla listStati passando come parametro il suo id
	 * @param listaStati Elenco di tutti gli stati, array bidimensionale
	 * @param idState Indica l'idState da ricercare
	 * @return stateLevel	Restituisce lo state level come oggetto
	 */
	public StateLevel getStateLevelFromListaStati(StateLevel[][] listaStati, String idState){
		StateLevel stateLevel = new StateLevel();
		for (int i = 0; i < listaStati.length; i++){
			int j=0;
			while (listaStati[i][j] != null){
				if (idState.equals(listaStati[i][j].getIdStato())){
					stateLevel = listaStati[i][j];
				}
				j++;
			}
		}
		return stateLevel;
	}
	/**
	 * Crea il file SFCFake.InitiAction.txt
	 * @param arrayTransitions Array bidimensionale di tutte le transizioni
	 */
	public void createSFCFake_Init_ActionFile(ArrayList<String> trxOut, StateLevel[][] arrayStateLevel, 
			Vector<Transitions> vectorTransitions,ArrayList<Region> listRegion ){
		int nrLivelli = arrayStateLevel.length;
		int indiceTrxOut = 0;
		int indice = 0;
		String nameStateSource = "";
		String nameStateTarget = "";
		StateLevel stateSource = new StateLevel();
		StateLevel stateTarget = new StateLevel();
		StateLevel state = new StateLevel();
		int levelStateSource = 0;
		int levelStateTarget = 0;
		int numberStateSource = 0;
		int numberStateTarget = 0;
		String testoInitActionValue = "";
		String testoTrxOriginal = "";
		String testoTrxSource = "";
		String testoTrxTarget = "";
		String testoCommento = "";
		Trx_In trxIn;
		
		int indiceTrx = 0;
		int index = 1;
		// Per ogni tranzione che è presente nella lista devo creare un  elemento TRXs[] da mettere in INIT
		for (String itemTrxOut : trxOut){
			indiceTrxOut = Integer.parseInt(itemTrxOut);
			
			testoTrxOriginal = "(* " + vectorTransitions.get(indiceTrxOut).getName() + "  *)";
			trxIn = new Trx_In();
			trxIn.setName(vectorTransitions.get(indiceTrxOut).getName());
			trxIn.setName(trxIn.getName().replace(" ", "_"));
			trxIn.setNrTrx(String.valueOf(index));
			
			
			levelStateTarget = vectorTransitions.get(indiceTrxOut).getLevelTarget()-1;
			if (vectorTransitions.get(indiceTrxOut).getNameStateSource().equals("DEFAULT")){
				stateSource = this.getStateLevelFromIdTrx(vectorTransitions.get(indiceTrxOut).getIdTransition(), arrayStateLevel, listRegion);
				numberStateSource = stateSource.getNumberState()-1;
				numberStateTarget =  vectorTransitions.get(indiceTrxOut).getNumberStateTarget()-1;
				stateTarget = arrayStateLevel[levelStateTarget][numberStateTarget];
				levelStateSource = stateSource.getLevelStato()-1;
			}else{
				levelStateSource = vectorTransitions.get(indiceTrxOut).getLevelSource()-1;
			numberStateSource =  vectorTransitions.get(indiceTrxOut).getNumberStateSource()-1;
			numberStateTarget =  vectorTransitions.get(indiceTrxOut).getNumberStateTarget()-1;
			stateSource = arrayStateLevel[levelStateSource][numberStateSource];
			stateTarget = arrayStateLevel[levelStateTarget][numberStateTarget];
			}
			//if (!(stateSource.getNomeStato().length() > 0)){
			//	stateSource = this.getStateLevelFromIdTrx(vectorTransitions.get(indiceTrxOut).getIdTransition(), arrayStateLevel);
		//	}
			// Genero le stringhe per il SOURCE
			for (int livello = 0;  livello < arrayStateLevel.length; livello++){
				testoTrxSource = testoTrxSource + "TRXs[" + index +"].Source[" + (livello+1) +"] :=";
				if (stateSource.getLevelStato() == (livello+1))
					testoTrxSource = testoTrxSource + stateSource.getNumberState() + ";\n";
				else
					testoTrxSource = testoTrxSource + "0;\n";
			}
			// Genero le stringhe per il TARGET
			Vector<String> livelliStati = new Vector<String>();
			livelliStati = getNumberUpState(stateTarget.getLevelStato(), stateTarget.getNumberState(), vectorTransitions.get(indiceTrxOut).getIdStateTarget(), arrayStateLevel);
			for (int livello = 0;  livello < arrayStateLevel.length; livello++){
				testoTrxTarget = testoTrxTarget + "TRXs[" + index +"].Target[" + (livello+1) +"] :=";
				
					testoTrxTarget = testoTrxTarget + livelliStati.get(livello) + ";\n";
				
				
			}
			
			testoCommento = "(* Setup transizione " + testoTrxOriginal + " : Source nome: " + stateSource.getNomeStato() +
					" Level: " + stateSource.getLevelStato() + " n. stato: " + stateSource.getNumberState() + 
					" : Target nome: " + stateTarget.getNomeStato() + " Level: " + stateTarget.getLevelStato() + 
					" n. stato: " + stateTarget.getNumberState() + " *)\n";
					
			
			testoInitActionValue = testoInitActionValue  + testoTrxOriginal + "\n" +
					testoTrxSource + "\n" + testoTrxTarget + "\n" + testoCommento + "\n";
		
			this.listTrxIn.add(trxIn);
			testoTrxSource = "";
			testoTrxOriginal = "";
			testoTrxSource = "";
			testoTrxTarget = "";
			testoCommento = "";
			index++;
		}
		for (int i=0; i < arrayStateLevel.length; i++){
			testoInitActionValue = testoInitActionValue + "\nSTATE_LEVELS[" + (i+1) + "].Current := " + 1 + ";";
		}
		// set il valore di initActionValue per poi usarlo
		this.globalVar.setInitActionValue(testoInitActionValue);
	}
	
	
	/**
	 * Crea il file SFCFake.InitiAction.txt
	 * @param arrayTransitions Array bidimensionale di tutte le transizioni
	 
	public void createSFCFake_Init_ActionFile(Transitions[][] arrayTransitions){
		//ScriviSuFile fileArrayTransitions = new ScriviSuFile("SFCFake_InitiAction.txt");
		String testoCommento = "";
		String testoNrTrx = "";
		String source = "";
		String target = "";
		String testo = "";
		String testoValue = "";
		String testoTitle = "";
		String testoInitActionValue = "";
		String nameTrx = "";
		boolean leafTransitionsSource = false; // se true significa che o in source non c'è uno state a nessun livello
		boolean leafTransitionsTarget = false; // se true significa che o in target non c'è uno state a nessun livello
		int contTransition = 1;
		
		stampaSystemOutTxtArea("\n->Creazione di SFC InitAction");
		// matrice nTransions x nLevels
		for (int i=0; i < arrayTransitions.length; i++){
			testoCommento ="(* SETUP transition : Source";
			for (int j=0; j < arrayTransitions[i].length; j++){
				
				source =source + "\nTRXs["+(contTransition)+"].Source["+(j+1)+"]:="+arrayTransitions[i][j].getNumberStateSource();
				// continuo a scrivere il commento della transizione
				if  (arrayTransitions[i][j].getNumberStateSource() != 0){
					
					testoNrTrx = "(* TRX nr. " + arrayTransitions[i][j].getName() + "*)\n\n";
					nameTrx = arrayTransitions[i][j].getName();
					testoCommento = testoCommento + " Nome: " + arrayTransitions[i][j].getNameStateSource() +
													" Level: " + arrayTransitions[i][j].getLevelSource() + 
													" n. stato: " + arrayTransitions[i][j].getNumberStateSource();
					leafTransitionsSource = true;
				}
			}
			for (int j=0; j < arrayTransitions[i].length; j++){
				target = target + "\nTRXs["+(contTransition)+"].Target["+(j+1)+"]:="+arrayTransitions[i][j].getNumberStateTarget();
				// continuo a scrivere il commento della transizione
				if (arrayTransitions[i][j].getNumberStateTarget() !=0){
					testoCommento = testoCommento + " Dest. name State: " + arrayTransitions[i][j].getNameStateTarget() + 
									  			    " Level: " + arrayTransitions[i][j].getLevelTarget() + 
									  			    " n. Stato: " +arrayTransitions[i][j].getNumberStateTarget() + 
									  			    " AND ";
					leafTransitionsTarget = true;
				}
			}
			if (leafTransitionsSource && leafTransitionsTarget){
				testo = testoCommento + "*)"  + source + "\n" + target + "\n";
				//testo = testo + testoNrTrx + "\n";
				contTransition++;
			}
			if (source.length() > 0)
				this.listTRXsWithOriginalName.add(nameTrx);
			leafTransitionsSource = false;
			leafTransitionsTarget = false;
			//fileArrayTransitions.scrivi(testo);
			testoInitActionValue = testoInitActionValue + testo + testoNrTrx;
			testo = "";
			testoCommento = "";
			source = "";
			target = "";
			nameTrx = "";
		}
		
		
		testoTitle = "\n\n(* INITIAL STATE *)";
		testoInitActionValue = testoInitActionValue + "\n\n" + testoTitle;
		
		//fileArrayTransitions.scrivi(testoTitle);
		for (int i = 0; i < arrayTransitions[0].length; i++){
			testo = "STATE_LEVELS[" + (i+1) +"].Current:=1";
			testoValue = testoValue + "\n" + testo;
			//fileArrayTransitions.scrivi(testo);
		}
		testoInitActionValue = testoInitActionValue + testoValue;
		// set il valore di initActionValue per poi usarlo
		this.globalVar.setInitActionValue(testoInitActionValue);
	}
*/	
	
	
	/**
	 * Crea lo script SFC_Fake e memorizza in SFC_Fake.txt
	 * @param arrayTransitions Array bidimensionale che contiene tutte le transizioni
	 */
	public void createSFCFakeFile(int nrTrxOut){
		//ScriviSuFile sfcFakeFile = new ScriviSuFile("SFC_Fake.txt");
		String sfcFakeText = "";
		String nomeFunctionBlock = "SFC_Fake";
		
		stampaSystemOutTxtArea("\n->Creazione dello SFC in ST");
		
		sfcFakeText= "FUNCTION BLOCK " + "\n" + nomeFunctionBlock;
		sfcFakeText = sfcFakeText + "VAR_INPUT";
		for (int i = 0; i < nrTrxOut; i++){
			sfcFakeText = sfcFakeText + "\n\tIN"+ i + ": BOOL;";
			this.globalVar.setInputVarList("IN" + String.valueOf(i));
		}
		sfcFakeText = sfcFakeText + "\nEND_VAR\n\n";
		
		sfcFakeText = sfcFakeText + "\n\nVAR_OUTPUT";
		sfcFakeText = sfcFakeText + "\nEND_VAR\n\n";
		
		sfcFakeText = sfcFakeText + "\nVAR";
		sfcFakeText = sfcFakeText + "\n\tINDEX: DINT";
		sfcFakeText = sfcFakeText + "\n\tLEVEL_CURR: DINT";
		sfcFakeText = sfcFakeText + "\n\tLEVEL_NEXT: DINT";
		sfcFakeText = sfcFakeText + "\n\t(* array di transizioni, tante quante sono le transizioni nel diagramma*)";
		sfcFakeText = sfcFakeText + "\n\t(* NOTA: nella struttura dati TRANSITIONS ho un valore di source e di destination PER OGNI LIVELLO GERARCHICO *)";
		sfcFakeText = sfcFakeText + "\n\tTRXs : ARRAY[1.." + nrTrxOut + "] OF TRANSITIONS;";
		sfcFakeText = sfcFakeText + "\n\t(* array di stati: UN ELEMENTO PER OGNI LIVELLO GERARCHICO, non per ogni stato... *)";
		sfcFakeText = sfcFakeText + "\n\tSTATE_LEVELS : ARRAY[1.." + nrTrxOut + "] OF STATE;";
		sfcFakeText = sfcFakeText + "\nEND_VAR";

		sfcFakeText = sfcFakeText + "\n\n\nVAR CONSTANT";
		sfcFakeText = sfcFakeText + "\n\tDEFAULT: BOOL := FALSE;";
		for (int i = 0; i < nrTrxOut; i++){
			sfcFakeText = sfcFakeText + "\n\tTRX" + i +": BOOL := FALSE";
		}
		sfcFakeText = sfcFakeText + "\nEND_VAR";
		//sfcFakeFile.scrivi(sfcFakeText);
		
		// aggiungo il valore di sfcFakeText a sfcFakeValue per usarlo dopo
		this.setSfcFakeValue(sfcFakeText);
		
		
	}
	
	
	/**
	 * Crea l'arrayTransitions, riempiendolo di trantions, con source e target, tenendo conto anche diinitialPoint
	 * @param arrayTransitions Array bidimensionale che contiene tutte le transizioni
	 * @param vectorTransitions Elenco di tutte le transizioni modificate e corrette
	 * @param listaStati Array bidimensionale di tutti gli stati
	 */
	public void createArrayTransitions(
			Vector<Transitions> vectorTransitions, 
			Transitions[][] arrayTransitions, 
			StateLevel[][] listaStati){

		int contTransitions = 0;
		int nLevels = arrayTransitions[0].length;
		int nTransitions = vectorTransitions.size();
		int levelSource = -1;
		int levelTarget = -1;
		String idStateTarget="";
		StateLevel stateLevel = new StateLevel();
		StateLevel stateLevelBis = new StateLevel();
		
		stampaSystemOutTxtArea("\n->Creazione del vettore delle transizioni");
		
		// (matrice: nTransitions x nLevels)
		for (int i=0; i < arrayTransitions.length; i++){
			for(int j=0; j <arrayTransitions[i].length; j++){
				arrayTransitions[i][j] = new Transitions();
			}
		}
		
		for (Transitions transitions : vectorTransitions){
			levelSource = transitions.getLevelSource();
			levelTarget = transitions.getLevelTarget();
			
				//System.out.println("\n jhfdksjhfk");
			if (transitions.getLevelSource() > 0){
				stateLevel = this.getStateLevel(listaStati, transitions.getIdStateSource());
				// setto il source per la transitions
				if (transitions.getIdStateSource().equals(stateLevel.getIdStato())){
					arrayTransitions[contTransitions][stateLevel.getLevelStato()-1].setIdStateSource(transitions.getIdStateSource());
					arrayTransitions[contTransitions][stateLevel.getLevelStato()-1].setLevelSource(transitions.getLevelSource());
					arrayTransitions[contTransitions][stateLevel.getLevelStato()-1].setNameStateSource(transitions.getNameStateSource());
					arrayTransitions[contTransitions][stateLevel.getLevelStato()-1].setNumberStateSource(transitions.getNumberStateSource());
					arrayTransitions[contTransitions][stateLevel.getLevelStato()-1].setName(transitions.getName());
					idStateTarget = transitions.getIdStateTarget();
					// mi restituisce lo StateLevel target della transitions che sto esaminando
					stateLevel = this.getStateLevel(listaStati, idStateTarget);
					if (stateLevel.getIdStato() == ""){
						System.out.println("\nIl destinatario della transition non è uno stato ma altro, pseudostate +idState= " + idStateTarget);
					}
					else{
						// imposto i valori per il target
						arrayTransitions[contTransitions][stateLevel.getLevelStato()-1].setIdStateTarget(stateLevel.getIdStato());
						arrayTransitions[contTransitions][stateLevel.getLevelStato()-1].setLevelTarget(stateLevel.getLevelStato());
						arrayTransitions[contTransitions][stateLevel.getLevelStato()-1].setNameStateTarget(stateLevel.getNomeStato());
						arrayTransitions[contTransitions][stateLevel.getLevelStato()-1].setNumberStateTarget(stateLevel.getNumberState());
						
						// verifico se ci sono dei sottostati che abbiano un initailPoint per settare la transitions ad altre livelli
						if (stateLevel.getVectorSubState().size() > 0){
							// ci sono dei subState
							for (String idSubState : stateLevel.getVectorSubState()){
								stateLevelBis = this.getStateLevel(listaStati, idSubState);
								if (stateLevelBis.getInitialPoint()){
									// imposto i valori per il target
									arrayTransitions[contTransitions][stateLevelBis.getLevelStato()-1].setIdStateTarget(stateLevelBis.getIdStato());
									arrayTransitions[contTransitions][stateLevelBis.getLevelStato()-1].setLevelTarget(stateLevelBis.getLevelStato());
									arrayTransitions[contTransitions][stateLevelBis.getLevelStato()-1].setNameStateTarget(stateLevelBis.getNomeStato());
									arrayTransitions[contTransitions][stateLevelBis.getLevelStato()-1].setNumberStateTarget(stateLevelBis.getNumberState());
								}
							}
						}
					}
				}
				else{
					}
			}
			else
			{
				stampaSystemOutTxtArea("\n->Transition che nn parte da stato");
			}
		contTransitions++;	
		}
	
	}
			
	
	
	/**
	 * Associa tutte le transizioni che sono presenti nel modello e crea un array di oggetti Transitions
	 * con numberTransition, idTransition, numberStateSource, idStateSource, numberStateTarget, idStateTarget
	 * valueTransition
	 * @param vectorTransitions Elenco di tutte le transizioni
	 * @param arrayStateLevel Array di tutti gli stati
	 * @param listTransition Lista di tutte le transizioni
	 * @param listInitial Lista di tutti gli initialPoint rilevati
	 */
	public void elencaAssociaStateToTransitions(
			Vector<Transitions> vectorTransitions, 
			StateLevel[][] arrayStateLevel,
			ArrayList<Transition> listTransition,
			ArrayList<Initial> listInitial){
		
		int nTransition = listTransition.size();
		int numberTransition = 0;
		Vector<String> listTargetFromInitial = new Vector<String>();
		
		stampaSystemOutTxtArea("\n->Associazione tra stati e transizioni di appartenza");
		for (int i = 0; i < nTransition; i++){
			vectorTransitions.add(new Transitions(listTransition.get(i), i+1));
		}
		
		// ordino il vectorTransitions per idSource
		Collections.sort(vectorTransitions,  new Transitions.SourceComparator());
		
		// cerco il numero di stato source nella listaStati
		for (Transitions item : vectorTransitions){
			// ciclo i livelli
			for (int i=0; i < arrayStateLevel.length; i++){
				// ciclo l'array per ogni livello
				for (int j=0; j < arrayStateLevel[i].length; j++){
					if (arrayStateLevel[i][j] != null){						
						if (item.getIdStateSource().equals(arrayStateLevel[i][j].getIdStato())){
							item.setNumberStateSource(arrayStateLevel[i][j].getNumberState());
							item.setLevelSource(arrayStateLevel[i][j].getLevelStato());
							item.setNameStateSource(arrayStateLevel[i][j].getNomeStato());
						}
					}
				}
			}
		}
		
		// verifico se c'è qualche transizione che parte da un initial point, se si prelevo l'idTarget
		// della transition e lo inserisco nel vettore listTargetFromInitial, per poi utilizzarlo per
		// settare la variabile booleana di StateLevel
		
		
		for (Transitions item : vectorTransitions){
			for (Initial initial : listInitial){
				if (item.getIdStateSource().equals(initial.getId())){
					listTargetFromInitial.add(item.getIdStateTarget());
				}
			}
		}
		// ordino il vectorTransitions per idtarget
		Collections.sort(vectorTransitions,  new Transitions.TargetComparator());
		// cerco il numero di stato source nella listaStati
		for (Transitions item : vectorTransitions){
			// ciclo i livelli
			for (int i=0; i < arrayStateLevel.length; i++){
				// ciclo l'array per ogni livello
				for(int j=0; j < arrayStateLevel[i].length; j++){
					if (arrayStateLevel[i][j] != null){
						if (item.getIdStateTarget().equals(arrayStateLevel[i][j].getIdStato())){
							item.setNumberStateTarget(arrayStateLevel[i][j].getNumberState());
							item.setLevelTarget(arrayStateLevel[i][j].getLevelStato());
							item.setNameStateTarget(arrayStateLevel[i][j].getNomeStato());
						}
					}
				}
			}
		}
		
		// ordino il vectorTransitions per il numero di livello SOURCE e poi numero le transizioni a partire da quelle che 
		// appartengono al primo livello
		Collections.sort(vectorTransitions, new Transitions.LevelSourceComparator());
		for (int i=0; i < vectorTransitions.size(); i++)
			vectorTransitions.get(i).setNumberTransition(i+1);
		
		
		// imposto il valore true per ogni stateLevel che � raggiungibile tramite un initialPoint
		for (String item : listTargetFromInitial){
			for (int i=0; i < arrayStateLevel.length; i++){
				// ciclo l'array per ogni livello
				for(int j=0; j < arrayStateLevel[i].length; j++){
					if (arrayStateLevel[i][j] != null){
						if (item.equals(arrayStateLevel[i][j].getIdStato())){
							arrayStateLevel[i][j].setInitialPoint(true);
						}
					}
				}
			}
		}
	}
	
	/**
	 * Metodo che controlla se tutti gli stati sono raggiungibili o meno mediante transizioni
	 * @param listaStati Elenco di tutti gli stati
	 * @param listTransition Elenco di tutte le transizioni
	 */
	public void controlloStatoRaggiungibile(ArrayList<State> listaStati, ArrayList<Transition> listTransition){
		stampaSystemOutTxtArea("\n->Check se ciascun stato è raggiungibile");
		for (State item : listaStati){
			item.setRaggiungibile(statoRaggiungibile(item.getId(), listTransition));
		}
		
		// ciclo un altra volta la lista stati stampando a video chi non è raggiungibile
		for (State item : listaStati){
			if (!(item.getRaggiungibile())){
				stampaSystemOutTxtArea("\n\t->Stato: " + item.getName() +" non raggiungibile con transizione - id :" + item.getId());
			}
			else{
				stampaSystemOutTxtArea("\n\t->Stato " +item.getName() +" raggiungibile");
			}
		}
	}
	
	
	/**
	 * Metodo che restituisce true se uno stato � raggiungibile mediante transizione
	 * @param idState Contiene l'id dello stato
	 * @param listTransition Elenco di tutte le transizioni
	 * @return value Ritorna un valore booleano se lo stato è rangiungibile o meno
	 */
	public boolean statoRaggiungibile(String idState, ArrayList<Transition> listTransition){
		boolean value = false;
		for (Transition item : listTransition){
			if (item.getTarget().equals(idState))
				value = true;
		}
		return value;
	}
	
	
	/**
	 * Elenca gli stati e costruisce l'array degli stati
	 * @param arrayStateLevel Elenco degli stati aggiornati
	 * @param listaStati Elenco degli stati rilevati
	 * @param numberLevels Numero dei livelli
	 * @param numberStateForLevel Numero di stati per livello
	 */
	public void elencaStateLevel(StateLevel[][] arrayStateLevel, 
								ArrayList<State> listaStati, 
								int numberLevels,
								int[] numberStateForLevel){
		int nStati = listaStati.size();
		int numberState = 0;
		
		stampaSystemOutTxtArea("\n->Costruzione della matrice degli stati: livello x nr. stati");
		
		// ordino la lista degli stati secondo il livello
		Collections.sort(listaStati, new State.LevelComparator());
		
		
		for (int i=0; i< numberLevels; i++){
			int nStateForLevel = 0;
			for (int j=0; j < nStati;j++){
				if (listaStati.get(j).getLevel().equals(i+1)){
					//setto il numero di stato
					arrayStateLevel[i][nStateForLevel] = new StateLevel(listaStati.get(j), nStateForLevel+1);
					nStateForLevel++;
				}		
			}
			
		}
		
	}
	
	
	/**
	 * Trova il numero di livelli max
	 * @param listaStati Elenco degli stati
	 * @return level Ritorna il numero di livello
	 */
	public int setNumberLevels(ArrayList<State> listaStati){
		int nStati = listaStati.size();
		int level = 0;
		for (int i = 0; i < nStati; i++){
			if (listaStati.get(i).getLevel() > level)
				level = listaStati.get(i).getLevel();
		}
		return level;
	}
	
	/**
	 * Calcola i numeri di livelli e deve fornire gli stati strutturati per livelli
	 * @param listaStati Elenco degli stati
	 */
	public void setLivelloSperiore(ArrayList<State> listaStati){
		int nStati = listaStati.size();
		for (int i = 0; i < nStati; i++){
			int livello = 1;
			String idStateLevelUp = listaStati.get(i).getIdStateLevelUp();
			if (idStateLevelUp.equals("-1"))
				listaStati.get(i).setLevel(livello);
			else {
				while (!idStateLevelUp.equals("-1")){
					for (int j = 0; j < nStati; j++){
						if (idStateLevelUp.equals(listaStati.get(j).getId())){
							livello++;
							idStateLevelUp = listaStati.get(j).getIdStateLevelUp();
							listaStati.get(i).setLevel(livello);
							if (idStateLevelUp.equals("-1"))
								j=nStati;
						}
						
					}
				}
				
			}
			listaStati.get(i).setLevel(livello);
		}
		
	}
	/**
	 * Stampa la lista degli stati
	 * @param listaStati Elenco degli stati
	 */
	public void stampaListaStati(ArrayList<State> listaStati){
		String testo = "";
		//ScriviSuFile stateWrite = new ScriviSuFile("ListaStati.txt");
		for (int i = 0; i < listaStati.size(); i++){
		//	stateWrite.scrivi("\nN. = " + i +" Livello="+listaStati.get(i).getLevel()+" " +listaStati.get(i).getName() + "\t\t\t " + listaStati.get(i).getId());
			testo="\n\t\tRegione di appartenenza := " +listaStati.get(i).getIdRegionAppartenenza();
		//	stateWrite.scrivi (testo);
			for (int j=0; j < listaStati.get(j).getListIdRegion().size(); j++){
				if (listaStati.get(i).getListIdRegion().size() > 0){
					testo = "\n\t\tRegione sottostante:= " + listaStati.get(i).getIdRegion(j);
		//			stateWrite.scrivi (testo);
				}
			}
			testo = "\n\t\tStato di livello superiore " + listaStati.get(i).getIdStateLevelUp();
		//	stateWrite.scrivi (testo);
			testo="_____________________________________________________";
		//	stateWrite.scrivi (testo);
		}
	}
	
	
	/**
	 * Stampa la lista delle regioni e delle transizioni su file differenti
	 * @param listaRegioni Elenco delle regioni
	 */
	public void stampaListaRegioni(ArrayList<Region> listaRegioni){
		//ScriviSuFile regionWrite = new ScriviSuFile("ListaRegioni.txt");
		//ScriviSuFile transitionWrite = new ScriviSuFile("ListaTransizioni.txt");
		for (int i = 0; i < listaRegioni.size(); i++){
		//	regionWrite.scrivi("\nN. = " + i +" " +listaRegioni.get(i).getName() + "\t\t\t " + listaRegioni.get(i).getId());
			// verifico se ci sono degli stati all'interno della regione, se si stampo i loro ID
			if (listaRegioni.get(i).getListIdState().size() > 0){
		//		regionWrite.scrivi("\nElenco degli stati appartenenti alla regione:");
				for (int k=0; k < listaRegioni.get(i).getListIdState().size(); k++){
		//			regionWrite.scrivi("\n\t N = " + k + " " + listaRegioni.get(i).getListIdState().get(k));
				}
			}
			for (int j=0; j < listaRegioni.get(i).getListTransition().size(); j++){
		//		transitionWrite.scrivi("\nN. = " + j +" " +listaRegioni.get(i).getListTransition().get(j).getName() + "\t\t\t " + 
		//					listaRegioni.get(i).getListTransition().get(j).getId());
			}
		}
	}
	
	
	/**
	 * Metodo che aggiunge tutti i sotto stati appartenenti ad uno stato. Poiche ogni stato pu� avere diverse regioni
	 * e ogni regione può avere diversi stati, devo aggiungere quest'ultimi allo stato immediatamente superiore
	 * @param listaStati Elenco degli stati
	 * @param listaRegioni Elenco delle regioni
	 */
	public void addSubStateForEachStateFromRegion(ArrayList<State> listaStati, ArrayList<Region> listaRegioni){
		
		// ordino la listaStati per ID
		Collections.sort(listaStati, new State.IdStateComparator());
		Collections.sort(listaRegioni, new Region.IdRegionComparator());
		
		for (State item : listaStati){
			// verifico che la lista delle regioni dello stato in questione sia piena di almeno una regione
			if (item.getListIdRegion().size() > 0){
				// per ogni regione appartenente alla lista elenco gli stati inclusi nella regione in state.vectorSubState
				for(String idRegion : item.getListIdRegion()){
					for (Region region : listaRegioni){
						if (idRegion.equals(region.getId())){
							// verifico che la lista degli stati sia piena di almeno uno stato
							if (region.getListIdState().size() > 0){
								for (String idState: region.getListIdState()){
									item.setAddVectorSubState(idState);
								}
							}
						}	
					}
				}
			}
		}
	}
	
	/**
	 * Stampa fu sile il contenuto dell'array bidimensionale arrayStateLevel
	 * @param arrayStateLevel Array bidimensionale degli stati
	 */
	public void writeListaStateLevel(StateLevel[][] arrayStateLevel){
	//	ScriviSuFile stateLevelFile = new ScriviSuFile("StateLevel.txt");
		String testo = "";
		for (int i = 0; i < arrayStateLevel.length; i++){
			for(int j=0; j < arrayStateLevel[i].length; j++){
				if (arrayStateLevel[i][j] != null){
					testo = "\n Nr:state: " + arrayStateLevel[i][j].getNumberState();
		//			stateLevelFile.scrivi(testo);
					testo = "\n Nr.LevelState: " + arrayStateLevel[i][j].getLevelStato();
		//			stateLevelFile.scrivi(testo);
					testo = "\n Id:state: " + arrayStateLevel[i][j].getIdStato();
		//			stateLevelFile.scrivi(testo);
					testo = "\n Raggiungibile: " + arrayStateLevel[i][j].getRaggiungibile();
		//			stateLevelFile.scrivi(testo);
					testo = "\n Nome:state: " + arrayStateLevel[i][j].getNomeStato();
		//			stateLevelFile.scrivi(testo);
					testo = "\n Lo stato ha un initial : " + String.valueOf(arrayStateLevel[i][j].getInitialPoint());
		//			stateLevelFile.scrivi(testo);
					testo = "\nSottoStati:";
		//			stateLevelFile.scrivi(testo);
					testo = "\nID stato di livello supeiore: " + arrayStateLevel[i][j].getIdStateLevelUp();
		//			stateLevelFile.scrivi(testo);
					for (String item : arrayStateLevel[i][j].getVectorSubState()){
						testo = "\n\t Id_Sottostato: " + item;
		//				stateLevelFile.scrivi(testo);
					}
		//			stateLevelFile.scrivi("_______________________________________________________");
				}
			}
		}
		
	}
	
	/**
	 * Ritorno uno StateLevel dall'arrayStateLevel dando come parametro il suo id
	 * @param idState Id dello stato da cercare
	 * @param arrayStateLevel Array bidimensionale degli stati
	 * @return stateLevelFound Valore booleano che indica se lo stato è stato trovato
	 */
	public StateLevel getStateLevel(StateLevel[][] arrayStateLevel, String idState){
		StateLevel stateLevelFound = new StateLevel();
		for (int i=0; i< arrayStateLevel.length; i++){
			for(int j=0; j < arrayStateLevel[i].length; j++){
				if (arrayStateLevel[i][j] != null){
					if (arrayStateLevel[i][j].getIdStato().equals(idState)){
						stateLevelFound = arrayStateLevel[i][j];
					}
				}
			}	
		}
		return stateLevelFound;
	}

	/**
	 * Scrive la lista delle transizioni
	 * @param arrayTransitions
	 */
	public void writeArrayTransitions(Transitions[][] arrayTransitions){
	//	ScriviSuFile fileArrayTransitions = new ScriviSuFile("arrayTransitions.txt");
		String testo = "";
		boolean leafTransitionsSource = false; // se true significa che o in source non c'è uno state a nessun livello
		boolean leafTransitionsTarget = false; // se true significa che o in target non c'è uno state a nessun livello
	
		// matrice nTransions x nLevels
		for (int i=0; i < arrayTransitions.length; i++){
			//testo="Transitions Leaf  = " + String.valueOf(arrayTransitions[i][j].getLeafTransitions());
			testo = "Source";
	//		fileArrayTransitions.scrivi(testo);
			for (int j=0; j < arrayTransitions[i].length; j++){
				if (arrayTransitions[i][j].getNumberStateSource() != 0)
					leafTransitionsSource = true;
				arrayTransitions[i][j].setLeafTransitions(leafTransitionsSource);
				testo ="\nTRXs["+(i+1)+"].Source["+(j+1)+"]:="+arrayTransitions[i][j].getNumberStateSource();
	//			fileArrayTransitions.scrivi(testo);
			}
			testo = "Target";
	//		fileArrayTransitions.scrivi(testo);
			for (int j=0; j < arrayTransitions[i].length; j++){
				if (arrayTransitions[i][j].getNumberStateTarget() != 0)
					leafTransitionsTarget = true;
				arrayTransitions[i][j].setLeafTransitions(leafTransitionsTarget);
				testo ="\nTRXs["+(i+1)+"].Target["+(j+1)+"]:="+arrayTransitions[i][j].getNumberStateTarget();
	//			fileArrayTransitions.scrivi(testo);
			}
			if (!leafTransitionsSource){
				testo = "La transizione non ha uno stato in SOURCE";
	//			fileArrayTransitions.scrivi(testo);
			}
			if (!leafTransitionsTarget){
				testo = "La transizione non ha uno stato in TARGET";
	//			fileArrayTransitions.scrivi(testo);
			}
			testo = "_____________________________________________";
	//		fileArrayTransitions.scrivi(testo);
			leafTransitionsSource = false;
			leafTransitionsTarget = false;
		}
	}
	
	/**
	 * Scrive il vettore delle transizioni
	 * @param vectorTransitions Vettore delle transizioni
	 */
	public void writeVectorTransitions(Vector<Transitions> vectorTransitions){
	//	ScriviSuFile fileVectorTransitions = new ScriviSuFile("vectorTransitions.txt");
		String testo = "";
		int i=0;
		for (Transitions item: vectorTransitions){
			testo = "Source";
	//		fileVectorTransitions.scrivi(testo);
			testo ="\nTRXs["+(i+1)+"] State Source="+item.getNumberStateSource();
	//		fileVectorTransitions.scrivi(testo);
			testo ="\n\tLevel State Source="+item.getLevelSource();
	//		fileVectorTransitions.scrivi(testo);
			testo = "Target";
	//		fileVectorTransitions.scrivi(testo);
			testo ="\nTRXs["+(i+1)+"] State Target="+item.getNumberStateTarget();
	//		fileVectorTransitions.scrivi(testo);
			testo ="\n\tLevel State Target="+item.getLevelTarget();
	//		fileVectorTransitions.scrivi(testo);
			testo = "_______________________________________________________";
	//		fileVectorTransitions.scrivi(testo);
			i++;
		}
	}
	/**
	 * Questo metodo mi permette di aggiungere il numero di transizione in entrata ed in uscita per ciascun stato ad ogni livello
	 * in modo da poter utilizzare l'oggetto nel progetto di codesys
	 * @param arrayTransitions Array bidimensionale delle transizioni
	 * @param listaStati Elenco degli stati
	 */
	public void setAddTransitionsToArrayStateLevelDefinitivo(Transitions[][] arrayTransitions, StateLevel[][] listaStati){
	//	ScriviSuFile fileStateLevelTransitions = new ScriviSuFile("StateLevelANDTransitions.txt");
		String testo="";
		System.out.println("dimensione array Transition " + arrayTransitions.length + " " + arrayTransitions[0].length);
		System.out.println("dimensione listaStati " + listaStati.length + " " + listaStati[0].length);
		for (int i=0; i < arrayTransitions.length; i++){
			for(int j=0; j < arrayTransitions[i].length; j++){
				for(int k=0; k < listaStati[j].length; k++){
					System.out.println("i=" + i + " j=" + j + " k=" +k);
					if (listaStati[j][k] != null){
						if(arrayTransitions[i][j].getIdStateTarget()==listaStati[j][k].getIdStato()){
							System.out.println("trovato");
							testo = "\n\nLa transizione nr \t" + i + 
									"\n parte da \t" + arrayTransitions[i][j].getNameStateSource() + 
									"\n arriva allo stato \t" + arrayTransitions[i][j].getNameStateTarget() + 
									"\n il nome dello stato destinatario è \t" +listaStati[j][k].getNomeStato()+listaStati[j][k].getNumberState();
	//						fileStateLevelTransitions.scrivi(testo);
							testo="";
						}
					}
				}
			}
		}
	}
	/**
	 * Imposta il valore di StateManager
	 * @param value Contiene lo STATE MANAGER per codesys
	 */
	public void setStateManagerValue(String value){
		this.stateManagerActionValue = value;
	}
	
	/**
	 * Restituisce il valore di STATE MANAGER
	 * @return stateManagerActionValue Contiene il valore di STATE MANAGER per Codesys
	 */
	public String getStateManagerValue(){
		return this.stateManagerActionValue;
	}
	/**
	 * Imposta il valore si INIT per codesys
	 * @param value Contiene il valore elaborato
	 */
	public void setInitActionValue(String value){
		this.initActionValue = value;
	}
	/**
	 * Restituisce il valore di INIT per codesys
	 * @return initActionValue Contiene il valore di INIT per codesys
	 */
	public String getInitActionValue(){
		return this.initActionValue;
	}
	/**
	 * Imposta il valore di SFC per codesys
	 * @param value Contiene il valore
	 */
	public void setSfcFakeValue(String value){
		this.sfcFakeValue = value;
	}
	/**
	 * Restituisce il valore di SFC per codesys
	 * @return sfcFakeValue Contiene il valore
	 */
	public String getSfcFakeValue(){
		return this.sfcFakeValue;
	}


	/**
	 * Restituisce il valore dello oggetto globalVar
	 * @return globalVar Contiene il valore
	 */
	public GlobalVar getGlobalVar(){
		return this.globalVar;
	}
	/**
	 * Stampa messaggi nella console
	 * @param value Contiene il valore del messaggio da stampare nella txtArea
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
	 * Cerca lo StateLevel a partire dal suo nuovo nome e restituisce lo stateLevel
	 * @param newName Nuovo nome dello statp
	 * @param arrayStateLevel Array[][] di StateLevel
	 * @return state Contiene lo stateLevel che coincide con il nuovo nome
	 */
	public StateLevel searchStateFromNewName(String newName, StateLevel[][] arrayStateLevel){
		StateLevel state = new StateLevel();
		for (int i=0; i < arrayStateLevel.length; i++){
			for (int j=0; j < arrayStateLevel[i].length; j++){
				if (arrayStateLevel[i][j] != null){
					if (arrayStateLevel[i][j].getNewName().equals(newName))
						state = arrayStateLevel[i][j];
				}
			}
		}
		return state;
	}
	/**
	 * Restituisce la lista delle trx che dove sono associati gli indici di vectorTransitions e l'indice di TRXs per il ladder
	 * @return listTrxIn LIsta
	 */
	public ArrayList<Trx_In> getListTrxIn (){
		return this.listTrxIn;
	}
	
	private StateLevel getStateLevelFromIdTrx(String idTransition, StateLevel[][] arrayStateLevel, ArrayList<Region> listRegion){
		StateLevel state = new StateLevel();
		String idState = "";
		
		for (Region itemRegion : listRegion){
			for (Transition itemTransition : itemRegion.getListTransition()){
				if (itemTransition.getId().equals(idTransition)){
					idState = itemRegion.getIdStateAppartenenza();
					for (int i=0; i < arrayStateLevel.length; i++){
						for (int j=0; j < arrayStateLevel[i].length; j++){
							if (arrayStateLevel[i][j] != null){
								if (arrayStateLevel[i][j].getIdStato().equals(idState))
									state = arrayStateLevel[i][j];
							}
						}
					}
				}
			}
		}
		return state;
	}
	/**
	 * Questo metodo prend in input il numero di livello ed il numero di uno stato e cerca lo stato di livello superiore
	 * @param livello Indica il livello
	 * @param idStato Indica il numero dello stato da cui partire per cercare ilsuo genitore
	 * @param arrayStateLevel Array bidimensionale degli stati
	 * @return ris Restituisce il numero dello stato genitore
	 */
	private Vector<String> getNumberUpState(int livello, int  nrStateTarget, String idStato, StateLevel[][] arrayStateLevel){
		Vector<String> livelli = new Vector<String>();
		Vector<String> livelliDefinitivi = new Vector<String>();
		int livelloIniziale = livello-1;
		int ris = -1;
		while (livello > 1){
			
			for (int i=0; i < arrayStateLevel.length; i++){
				for (int j=0; j < arrayStateLevel[i].length; j++){
					if (arrayStateLevel[i][j] != null){
						for (String item : arrayStateLevel[i][j].getVectorSubState()){
							if (item.equals(idStato)){
								ris = arrayStateLevel[i][j].getNumberState();
								livelli.add(String.valueOf(ris));
								livello = arrayStateLevel[i][j].getLevelStato();
								idStato = arrayStateLevel[i][j].getIdStato();
							}
						}
					}
				}
			}
		}
		
		// Metto in ordine i livelli
		// prima di tutto inizializzo il vettore
		if (livelloIniziale == 0){
				livelliDefinitivi.add(String.valueOf(nrStateTarget));
				for (int j=1; j < arrayStateLevel.length; j++)
					livelliDefinitivi.add(String.valueOf(-1));
		}else{
			for (int i=0; i < arrayStateLevel.length; i++){
				if (i < livelloIniziale){
					livelliDefinitivi.add(livelli.get(livelli.size()-1));
					livelli.remove(livelli.size()-1);
				}
				else if (i == livelloIniziale){
					livelliDefinitivi.add(String.valueOf(nrStateTarget));
				}
				else{
					
						livelliDefinitivi.add(String.valueOf(-6));
				}
			}
		}
		
		return livelliDefinitivi;
	}
	
	private Vector<String> getNumberUpStateRicorsivo(int livello, String idStato, StateLevel[][] arrayStateLevel){
		Vector<String> livelli = new Vector<String>();
		Vector<String> livell = new Vector<String>();
		int ris = -1;
		
		for (int i=0; i < arrayStateLevel.length; i++){
			for (int j=0; j < arrayStateLevel[i].length; j++){
				if (arrayStateLevel[i][j] != null){
					for (String item : arrayStateLevel.clone()[i][j].getVectorSubState()){
						if (item.equals(idStato))
							ris = arrayStateLevel[i][j].getNumberState();
					}
				}
			}
		}
		return livelli;
	}
}
