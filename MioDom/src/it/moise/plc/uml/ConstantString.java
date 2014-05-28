package it.moise.plc.uml;
/**
 * Elenco delle costanti di tipo stringhe utilizzate nelle varie parti del programma
 * @author De Carvalho Moise
 *
 */
public class ConstantString {
	public static final String INDEX;
	public static final String LEVEL_CURR;
	public static final String LEVEL_NEXT;
	public static final String TRANSITIONS;
	public static final String NAME_ARRAY_TRANSITIONS;
	public static final String NAME_ARRAY_STATE_LEVELS;
	public static final String ARRAY_TRXS_DOCUMENTATION;
	public static final String ARRAY_STATE_LEVELS_DOCUMENTATION;
	public static final String STATE;
	public static final String SFC_ELEMENT;
	public static final String IMPLEMENTATION;
	public static final String ATTRIBUTES;
	public static final String SFC;
	public static final String BOOL;
	public static final String BYTE;
	public static final String BIT;
	public static final String WORD;
	public static final String DWORD;
	public static final String LWORD;
	public static final String SINT;
	public static final String USINT;
	public static final String INT;
	public static final String UINT;
	public static final String LINT;
	public static final String DINT;
	public static final String REAL;
	public static final String LREAL;
	public static final String STRING;
	public static final String TIME;
	public static final String TOD;
	public static final String DATE;
	public static final String DT;
	public static final String DERIVED;
	
	static{
		INDEX = "INDEX";
		LEVEL_CURR = "LEVEL_CURR";
		LEVEL_NEXT = "LEVEL_NEXT";
		TRANSITIONS = "TRANSITIONS";
		NAME_ARRAY_TRANSITIONS = "TRXs";
		NAME_ARRAY_STATE_LEVELS = "STATE_LEVELS";
		ARRAY_TRXS_DOCUMENTATION = "array di transizioni, tante quante sono le transizioni nel diagramma"
				+ " NOTA: nella struttura dati TRANSITIONS ho un valore di source e di destination PER OGNI LIVELLO GERARCHICO";
		STATE = "STATE";
		ARRAY_STATE_LEVELS_DOCUMENTATION = " array di stati: UN ELEMENTO PER OGNI LIVELLO GERARCHICO, non per ogni stato... ";
		SFC_ELEMENT = "http://www.3s-software.com/plcopenxml/sfc/element";
		IMPLEMENTATION = "implementation";
		ATTRIBUTES = "attributes";
		SFC = "sfc";
		BOOL = "BOOL";
		BYTE = "BYTE";
		BIT = "BIT";
		WORD = "WORD";
		DWORD = "DWORD";
		LWORD = "LWORD";
		SINT = "SINT";
		USINT = "USINT";
		INT = "INT";
		UINT = "UINT";
		LINT = "LINT";
		DINT = "DINT";
		REAL = "REAL";
		LREAL = "LREAL";
		STRING ="STRING";
		TIME = "TIME";
		TOD = "TOD";
		DATE ="DATE";
		DT = "DT";
		DERIVED ="DERIVED";
		
	}

}
