package it.moise.plc.uml;

import it.moise.plc.uml.jaxb_plcopen.ContentHeaderTag;
import it.moise.plc.uml.jaxb_plcopen.FileHeaderTag;
import it.moise.plc.uml.jaxb_plcopen.InstancesTag;
import it.moise.plc.uml.jaxb_plcopen.PouTag;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JTextArea;
import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.plcopen.xml.tc6_0201.*;
import org.plcopen.xml.tc6_0201.Project.*;

/**
 * La classe genera un oggetto che permette di effettuare la conversione utilizzando il package PLCOpen XML, e crea un file XML
 * che sara' utilizzato per importare un progetto all'interno di CodeSys
 * @author De Carvalho Moise
 *
 */
public class ConversionToCoDeSys {
	private JTextArea txtArea;
	private String msg ="";
	private String nameFile = "";
	private String context = "org.plcopen.xml.tc6_0201";
	private MioDom mioDom;
	private FileHeaderTag fileHeader = new FileHeaderTag(); 
	private ContentHeaderTag contentHeader;
	private InstancesTag instances;
	private String[] tipiVariabili = {"BOOL", "BYTE", "BIT", "WORD", "DWORD", "LWORD", "SINT", "USINT", "INT", "UINT", "LINT", "DINT", 
			"REAL", "LREAL", "STRING", "TIME", "TOD", "DATE", "DT"};
	private Vector<String> vectorUserType = new Vector<String>();
	private PouTag pou;
	private Project project = new ObjectFactory().createProject();
	private Project.Types.DataTypes.DataType dataTypeStructBaseType = new ObjectFactory().createProjectTypesDataTypesDataType();
	private Project.Types types = new ObjectFactory().createProjectTypes();
	private Project.Types.DataTypes dataTypes = new ObjectFactory().createProjectTypesDataTypes();
	private Project.Types.Pous pous = new ObjectFactory().createProjectTypesPous();
	private DataType.Derived derived;
	private DataType dataDerived;
	/**
	 * Metodo costruttore con parametri
	 * @param mioDom Contiene un oggetto di tipo MioDom che a sua volta contiene tutte le informazioni del modello UML
	 * @param tipoPou Inditca che tipo di Pou creare
	 * @throws Exception Gestisce le eccezioni
	 */
	public ConversionToCoDeSys(MioDom mioDom, String tipoPou) throws Exception {
		this.txtArea = txtArea;
		this.mioDom = mioDom;		
		this.createConversion();
	}
	/**
	 * Metodo costruttore con parametri
	 * @param mioDom Contiene un oggetto di tipo MioDom, ovvero il file in XMI elaborato
	 * @param tipoPou Indica il tipo di POU che bisognera' creare
	 * @param txt	Messaggio di testo
	 * @param txtArea	E' un oggetto di tipo JTextArea che viene utilizzato per visualizzare messaggi
	 * @throws Exception Eccezio sollevata
	 */
	public ConversionToCoDeSys(MioDom mioDom, String tipoPou, String txt, JTextArea txtArea) throws Exception {
		this.txtArea = txtArea;
		
		this.nameFile = nameFile;
		this.mioDom = mioDom;
		
		this.createConversion();
	}
	/**
	 * Metodo costruttore senza parametri
	 */
	private void createConversion(){
		
		try{
		/*
		 * setto i valori del fileHeader e lo aggiungo al progetto
		 */
			
			this.project.setFileHeader(this.fileHeader);
		/*
		 * istanzio l'oggetto ContentHeaderTag e lo aggiungo al progetto
		 */	
			this.contentHeader = new ContentHeaderTag();
			this.project.setContentHeader(this.contentHeader);
			
		/*
		 * istanzio l'oggetto InstancesTag e lo aggiungo al progetto
		 */	
			this.instances = new InstancesTag();
			this.setInstances(this.instances);
			
		/*
		 * istanzio l'oggetto Types e lo aggiungo al progetto
		 */
			for (Variable item : this.mioDom.getModello().getStateMachine().getVectorVariable()){
				// se il tipo non è di quelli predefiniti
				if (!controlloTipoVariabile(item.getType())){
					if (!controlloExistUserType(item.getType())){
						// se il tipo definito dall'utente non esiste nell'array lo creo e lo aggiungo
						dataTypeStructBaseType = new ObjectFactory().createProjectTypesDataTypesDataType();
						derived = new ObjectFactory().createDataTypeDerived();
						dataDerived = new ObjectFactory().createDataType();
						dataTypeStructBaseType.setName(item.getType());
						derived.setName("SFC_Definito_DallUtente");
						dataDerived.setDerived(derived);
						dataTypeStructBaseType.setBaseType(dataDerived);
						dataTypes.getDataType().add(dataTypeStructBaseType);
						this.vectorUserType.add(item.getType());
					}
				}
				
			}
			Project.Types.Pous.Pou pouProgram = new ObjectFactory().createProjectTypesPousPou();
			
			pouProgram = createPouProgram();
			pous.getPou().add(pouProgram);
			
			this.pou = new PouTag(	this.mioDom.getListTrxIn(),
									this.mioDom.getListTRXsWithOriginalName(),
									this.mioDom.getListTrxOutGrafo(),
									this.mioDom.getGestioneAnalisiModello().getStateManagerValue(),
									this.mioDom.getNumberStateLevels(),
									this.mioDom.getStateManagerActionValue(),
									this.mioDom.getInitActionValue(),
									this.mioDom.getArrayStateLevel(),
									this.mioDom.getVectorTransitions(),
									this.mioDom.getGrafoStati(),
									this.mioDom.getModello().getStateMachine().getVectorVariable(),
									this.vectorUserType);
			pous.getPou().add(pou);
			this.project.setTypes(types);
			this.project.getTypes().setDataTypes(dataTypes);
			
			/*
			 * Creo la struttura State
			 */
			
			VarListPlain varListStructVariabili = new ObjectFactory().createVarListPlain();
			dataTypeStructBaseType = new ObjectFactory().createProjectTypesDataTypesDataType();
			DataType dataTypeState = new ObjectFactory().createDataType();
			DataType tipoVariabile = new ObjectFactory().createDataType();
			VarListPlain.Variable variabile = new ObjectFactory().createVarListPlainVariable();
			
			// priva variabile della struttura STATE
			tipoVariabile.setDINT(1);
			variabile.setType(tipoVariabile);
			variabile.setName("Current");
			varListStructVariabili.getVariable().add(variabile);
			
			// seconda variabile della struttura STATE
			tipoVariabile = new ObjectFactory().createDataType();
			variabile = new ObjectFactory().createVarListPlainVariable();
			
			tipoVariabile.setDINT(1);
			variabile.setType(tipoVariabile);
			variabile.setName("Next");
			varListStructVariabili.getVariable().add(variabile);
			
			dataTypeState.setStruct(varListStructVariabili);
			
			dataTypeStructBaseType.setName("STATE");
			dataTypeStructBaseType.setBaseType(dataTypeState);
			dataTypes.getDataType().add(dataTypeStructBaseType);
			
			
			/*
			 * Creo la struttura Transitions
			 */
			// primo array della Struttura Transition
			dataTypeState = new ObjectFactory().createDataType();
			dataTypeStructBaseType = new ObjectFactory().createProjectTypesDataTypesDataType();
			varListStructVariabili = new ObjectFactory().createVarListPlain();
			DataType.Array array = new ObjectFactory().createDataTypeArray();
			RangeSigned range = new ObjectFactory().createRangeSigned();
			tipoVariabile = new ObjectFactory().createDataType();
			variabile = new ObjectFactory().createVarListPlainVariable();
			String testo = "A source and a destination for each hierarchical level";
			FormattedText testoDocumentation = new ObjectFactory().createFormattedText();
			try{
				testoDocumentation.setAny(new JAXBElement<String>( new QName("http://www.w3.org/1999/xhtml","xhtml"), String.class, testo));
			}
			catch (IllegalArgumentException e1){
				e1.printStackTrace();
			}
			int nLivelli = this.mioDom.getArrayStateLevel().length;
			range.setLower("1");
			range.setUpper(String.valueOf(nLivelli));
			array.getDimension().add(range);
			tipoVariabile.setDINT(1);
			array.setBaseType(tipoVariabile);
			tipoVariabile = new ObjectFactory().createDataType();
			tipoVariabile.setArray(array);
			variabile.setType(tipoVariabile);
			variabile.setName("Source");
			variabile.setDocumentation(testoDocumentation);
			varListStructVariabili.getVariable().add(variabile);
			
			
			// secondo array della Struttura Transition
			array = new ObjectFactory().createDataTypeArray();
			range = new ObjectFactory().createRangeSigned();
			tipoVariabile = new ObjectFactory().createDataType();
			variabile = new ObjectFactory().createVarListPlainVariable();
					
			nLivelli = this.mioDom.getArrayStateLevel().length;
			range.setLower("1");
			range.setUpper(String.valueOf(nLivelli));
			array.getDimension().add(range);
			tipoVariabile.setDINT(1);
			array.setBaseType(tipoVariabile);
			tipoVariabile = new ObjectFactory().createDataType();
			tipoVariabile.setArray(array);
			variabile.setType(tipoVariabile);
			variabile.setName("Target");
			varListStructVariabili.getVariable().add(variabile);
			
			
			// terza variabile della Struttura Transition
			
			tipoVariabile = new ObjectFactory().createDataType();
			variabile = new ObjectFactory().createVarListPlainVariable();
			tipoVariabile.setBOOL(false);
			variabile.setType(tipoVariabile);
			variabile.setName("Condition");
			varListStructVariabili.getVariable().add(variabile);
			
			dataTypeState.setStruct(varListStructVariabili);
			dataTypeStructBaseType.setName("TRANSITIONS");
			dataTypeStructBaseType.setBaseType(dataTypeState);
			
			dataTypes.getDataType().add(dataTypeStructBaseType);			
						
			this.project.getTypes().setPous(pous);
			
			
		/* 
		 * istanzio l'oggetto Pou che è contenuto in POUS e lo aggiungo al progetto
		 */
			// passo la lista dei nomi delle variabili in input da inserire nel file xml
			
			it.moise.plc.uml.jaxb_plcopen.StampGenericXML.staticStampGenericXML(this.project, this.context);
			
			//serializzazione dell'oggetto project
			it.moise.plc.uml.jaxb_plcopen.JaxbMarshal marshall = new it.moise.plc.uml.jaxb_plcopen.JaxbMarshal("outputFileCodesys.xml", context);
			marshall.getMarshalledFile(this.project);
			
		
			
		}catch(Exception e){
			e.printStackTrace();
		}
			
		
		
	}
	
	/**
	 * Aggiungo il FileHeader al project
	 * @param value E' un oggetto di tipo FileHeader
 	 * @throws DatatypeConfigurationException 
	 */
	private void setFileHeaderTag(FileHeader value) throws DatatypeConfigurationException{	
		Date today = new Date();
		String data = "";
		DatatypeFactory f = DatatypeFactory.newInstance();
		this.fileHeader.setCompanyName("moise.it");
		this.fileHeader.setProductName("CODESYS");
		this.fileHeader.setProductVersion("CODESYS V3.5 SP3 Patch 7");	
		SimpleDateFormat  calendario = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		data= calendario.format(today);
		XMLGregorianCalendar c = f.newXMLGregorianCalendar(data);
		
		this.fileHeader.setCreationDateTime(c);
		System.out.println("time: " + data);
		System.out.println ("Oggetto File Header creato");
	}
	
	/**
	 * Imposta il valore del contentHeader
	 * @param value Contiene un oggetto di tipo ContentHeaderTag
	 */
	private void setContentHeader(ContentHeaderTag value){
		this.project.setContentHeader(value);
	}
	/**
	 * Restituisce il contentHeader del progetto
	 * @return contentHeader
	 */
	private ContentHeader getContentHeaderTag(){
		return this.project.getContentHeader();
	}
	
	/**
	 * Imposta il valore del instances
	 * @param value Contiene il valore di un oggetto InstanceTag
	 */
	private void setInstances(InstancesTag value){
		this.project.setInstances(value);
	}
	/**
	 * Restituisce il InstancesT del progetto
	 * @return instances
	 */
	private Instances getInstancesTag(){
		return this.project.getInstances();
	}
	
	
	/**
	 * Stampa un messaggio nella JtextArea
	 * @param value Contien il valore del messaggio da stampare
	 */
	private void stampaSystemOutTxtArea(String value){
		this.msg = value;
		System.out.println(msg);
		this.txtArea.append(this.msg);
		this.txtArea.setCaretPosition(this.txtArea.getText().length());

	}
	/**
	 * Effettua il controllo del tipoVariabile con i tipi che possono essere accettati, restiruisce TRUE se il tipo è corretto
	 * @param tipoVar E' un valore di tipo stringa
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
	/**
	 * Verifica il tipo di dati creato dall'utente del diagramma UML è gia' presente nel vettore
	 * @param value
	 * @return ris Restituisce un valore booleano TRUE se il tipo di dati è gia' stato creato
	 */
	private boolean controlloExistUserType(String value){
		boolean ris = false;
		for (String item : this.vectorUserType){
			if (item.equals(value))
				ris = true;
		}
		return ris;
	}
	/**
	 * Costruisce un oggetto di tipo POU e contine l'inizializzazioni delle variabili globali (riferite alle transizioni e la 
	 * chiamata a SFC_Fake_Importato
	 * @return pouProgram Un oggetto di tipo pou
	 */
	private Project.Types.Pous.Pou createPouProgram(){
		Project.Types.Pous.Pou pouProgram = new ObjectFactory().createProjectTypesPousPou();
		Project.Types.Pous.Pou.Interface pouInterface = new ObjectFactory().createProjectTypesPousPouInterface();
		Project.Types.Pous.Pou.Interface.LocalVars localInterfaceVars = new ObjectFactory().createProjectTypesPousPouInterfaceLocalVars();
		Body body = new ObjectFactory().createBody();
		//Body.SFC sfc = new ObjectFactory().createBodySFC();
		DataType dataTypeVar = new ObjectFactory().createDataType();
		DataType.Derived derivedDataType = new ObjectFactory().createDataTypeDerived();
		VarListPlain.Variable variabile = new ObjectFactory().createVarListPlainVariable();
		FormattedText testo = new ObjectFactory().createFormattedText();
		AddData addData = new ObjectFactory().createAddData();
		
		String text = "test.Inizialization();\ntest.AssegnaCondizioniBooleaneTRX();\ntest();";
		
		pouProgram.setName("PLC_PRG");
		pouProgram.setPouType(PouType.fromValue("program"));
		pouProgram.setInterface(pouInterface);
		
		variabile.setName("test");
		derivedDataType.setName("SFC_Fake_Importato");
		dataTypeVar.setDerived(derivedDataType);
		variabile.setType(dataTypeVar);
		localInterfaceVars.getVariable().add(variabile);
		
		pouInterface.getLocalVarsOrTempVarsOrInputVars().add(localInterfaceVars);
		pouProgram.setInterface(pouInterface);
		
		try{
			testo.setAny(new JAXBElement<String>( new QName("http://www.w3.org/1999/xhtml","xhtml"), String.class, text));
		}
		catch (IllegalArgumentException e){
			e.printStackTrace();
		}
		
		body.setST(testo);
		pouProgram.getBody().add(body);
		pouProgram.setAddData(addData);
		
		return pouProgram;
	}
}
