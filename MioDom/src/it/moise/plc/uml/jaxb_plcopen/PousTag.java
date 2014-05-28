package it.moise.plc.uml.jaxb_plcopen;

import org.plcopen.xml.tc6_0201.ObjectFactory;
import org.plcopen.xml.tc6_0201.Project;

public class PousTag  extends Project.Types.Pous{

	public PousTag() throws Exception{
		new ObjectFactory().createProjectTypesPous();
	}
	/**
	 * Imposta il valore del Pous
	 * @param value
	 */
	public void setPous(Project.Types.Pous value){
		this.setPous(value);
	}
	/**
	 * Restituisce il Pous
	 * @return Pous
	 */
	public Project.Types.Pous getPous(){
		return this.getPous();
	}

}
