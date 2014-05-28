package it.moise.plc.uml.jaxb_plcopen;

import org.plcopen.xml.tc6_0201.DataType;
import org.plcopen.xml.tc6_0201.VarListPlain;
/**
 * Classe che mi permette di costruire una variabile fornendo il nome ed il suo tipo
 * @author De Carvalho Moïse
 *
 */
public class VariableTag extends VarListPlain.Variable{
	/**
	 * Metodo costruttore
	 * @param name
	 * @param dataType
	 */
	public VariableTag(String name, DataType dataType) {
		super();
		this.setName(name);
		this.setType(dataType);
	}
	

}
