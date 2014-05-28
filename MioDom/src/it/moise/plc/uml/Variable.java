/**
 * 
 **/
package it.moise.plc.uml;

import it.moise.plc.uml.interfaces.InterfaceVariable;
import it.moise.plc.uml.value.LowerValue;
import it.moise.plc.uml.value.UpperValue;

import org.w3c.dom.NamedNodeMap;

/**
 * @author De Carvalho Moïse
 *
 **/


public class Variable implements InterfaceVariable {
	private String id = null;
	private String name = null;
	private String xmiType = null;
	private String idTypeVariable = null;
	private String typeReference = null;
	private String scopeVariable = "";
	private boolean isOrdered = false;
	private boolean isUnique = false;
	private boolean visibility = false;	// se TRUE è private
	private LowerValue lowerValue = new LowerValue();
	private UpperValue upperValue = new UpperValue();
	private TypeParameter typeVariable = new TypeParameter();
	private Signal typeVariableSignal = new Signal();
	private String type = "-1";	// indica il tipo finale della variabile
	private boolean tipoAssociato = false; 	//se true indica che il tipo è stato associato 
	private String value ="";
	
	/**
	 * Costruttore con parametro
	 * @param NamedNodeMap node
	 */
	public Variable(NamedNodeMap node, NamedNodeMap tipoVar){
		String delimiter ="xmi:";
		String temp[] = null;
		//System.out.println("\n**********Sezione di caricamento degli attributi *****Variable*************");
		for (int i=0; i < node.getLength(); i++){
			temp = null;
			delimiter ="xmi:";
			//System.out.println(node.item(i).getNodeName() + " -- " + node.item(i).getNodeValue());
			temp = node.item(i).getNodeName().split(" ");
			if (temp[0].equalsIgnoreCase("")) {
				temp[0] = temp[1];
			}
			if (temp.length > 0){
				//temp = node.item(i).getNodeName().split(delimiter);
				//System.out.println("\t ---- " + temp[0]);
				
				switch (temp[0]){
				case "name":
					int position = -1;
					
					position=node.item(i).getNodeValue().indexOf("=");
					
					if (position >0){
						this.setValue(node.item(i).getNodeValue().substring(position+1));
						String tmp = node.item(i).getNodeValue().substring(0, position);
						this.setName(tmp);
					}else
						this.setName(node.item(i).getNodeValue());
					//System.out.println("\t ---- " + this.getName()+"    ecoo il NAME");
					break;
				case "xmi:id":
					this.setId(node.item(i).getNodeValue());
					//System.out.println("\t ---- " + this.getId()+"    ecoo il ID");
					break;
				case "xmi:type":
					this.setXmiType(node.item(i).getNodeValue());
					//System.out.println("\t ---- " + this.getXmiType()+"    ecoo il XMITYPE");
					break;
				case "type":
					this.setIdTypeVariable(node.item(i).getNodeValue());
					//System.out.println("\t ---- " + this.getIdTypeVariable()+"    ecoo il ID_TYPE_VARIABLE");
					break;
				case "isOrdered":
					this.setIsOrdered(Boolean.valueOf(node.item(i).getNodeValue()));
					//System.out.println("\t ---- " + this.getIsOrdered()+"    ecoo il IS_ORDERED");
					break;
				case "isUnique":
					this.setIsUnique(Boolean.valueOf(node.item(i).getNodeValue()));
					//System.out.println("\t ---- " + this.getIsUnique()+"    ecoo il IS_UNIQUE");
					break;
				}
			}
			
			
		}
		//System.out.println("\n**********Fine caricamento degli attributi *****Variable*************");
		for (int i=0; i < tipoVar.getLength(); i++){
			temp = null;
			delimiter ="xmi:";
			//System.out.println(node.item(i).getNodeName() + " -- " + node.item(i).getNodeValue());
			temp = tipoVar.item(i).getNodeName().split(" ");
			if (temp[0].equalsIgnoreCase("")) {
				temp[0] = temp[1];
			}
			if (temp.length > 0){
				switch (temp[0]){
				case "href":
					String tipo = tipoVar.item(i).getNodeValue();
					int posizione= tipo.indexOf("#");
					this.type = tipo.substring(posizione+1, tipo.length()).toUpperCase();
					if (this.type.equals("INTEGER"))
						this.type = "DINT";
					if (this.type.equals("BOOLEAN"))
							this.type = "BOOL";
					this.setType(type);
					this.tipoAssociato = true;
					break;
				}
			}
		}
	}
	
	/**
	 * Costruttore con parametro
	 * @param NamedNodeMap node
	 */
	public Variable(NamedNodeMap node){
		String delimiter ="xmi:";
		String temp[] = null;
		//System.out.println("\n**********Sezione di caricamento degli attributi *****Variable*************");
		for (int i=0; i < node.getLength(); i++){
			temp = null;
			delimiter ="xmi:";
			//System.out.println(node.item(i).getNodeName() + " -- " + node.item(i).getNodeValue());
			temp = node.item(i).getNodeName().split(" ");
			if (temp[0].equalsIgnoreCase("")) {
				temp[0] = temp[1];
			}
			if (temp.length > 0){
				//temp = node.item(i).getNodeName().split(delimiter);
				//System.out.println("\t ---- " + temp[0]);
				
				switch (temp[0]){
				case "name":
					int position = -1;
					
					position=node.item(i).getNodeValue().indexOf("=");

					if (position >0){
						String tmp = node.item(i).getNodeValue().substring(0, position);
						this.setName(tmp);
						this.setValue(node.item(i).getNodeValue().substring(position+1));
					}else
						this.setName(node.item(i).getNodeValue());
					//System.out.println("\t ---- " + this.getName()+"    ecoo il NAME");
					break;
				case "xmi:id":
					this.setId(node.item(i).getNodeValue());
					//System.out.println("\t ---- " + this.getId()+"    ecoo il ID");
					break;
				case "xmi:type":
					this.setXmiType(node.item(i).getNodeValue());
					//System.out.println("\t ---- " + this.getXmiType()+"    ecoo il XMITYPE");
					break;
				case "type":
					this.setIdTypeVariable(node.item(i).getNodeValue());
					//System.out.println("\t ---- " + this.getIdTypeVariable()+"    ecoo il ID_TYPE_VARIABLE");
					break;
				case "isOrdered":
					this.setIsOrdered(Boolean.valueOf(node.item(i).getNodeValue()));
					//System.out.println("\t ---- " + this.getIsOrdered()+"    ecoo il IS_ORDERED");
					break;
				case "isUnique":
					this.setIsUnique(Boolean.valueOf(node.item(i).getNodeValue()));
					//System.out.println("\t ---- " + this.getIsUnique()+"    ecoo il IS_UNIQUE");
					break;
				case "visibility":
					this.setVisibility(Boolean.valueOf(node.item(i).getNodeValue()));
					//System.out.println("\t ---- " + this.getIsUnique()+"    ecoo il VISIBILITY");
					break;
				}
			}
		}
		//System.out.println("\n**********Fine caricamento degli attributi *****Variable*************");
	}
	/**
	 * 
	 **/
	public Variable() {
	}

	/**
	 * Set l'id della Variable
	 * @param String id
	 * @see it.moise.plc.uml.interfaces.InterfaceVariable#setId(java.lang.String)
	 **/
	@Override
	public void setId(String id) {
		this.id = id;

	}

	/**
	 * Restituisce l'id della Variable
	 * @see it.moise.plc.uml.interfaces.InterfaceVariable#getId()
	 **/
	@Override
	public String getId() {
		// 
		return this.id;
	}

	/**
	 * Set il nome della Variable
	 * @param String name
	 * @see it.moise.plc.uml.interfaces.InterfaceVariable#setName(java.lang.String)
	 **/
	@Override
	public void setName(String name) {
		this.name = name;

	}

	/**
	 * Restituisce il nome della Variable
	 * @see it.moise.plc.uml.interfaces.InterfaceVariable#getName()
	 **/
	@Override
	public String getName() {
		// 
		return this.name;
	}

	/**
	 * Set il tipo della Variable
	 * @param String 
	 * @see it.moise.plc.uml.interfaces.InterfaceVariable#setType(java.lang.String)
	 **/
	@Override
	public void setXmiType(String xmiType) {
		this.xmiType = xmiType;

	}

	/**
	 * Restituisce il tipo della Variable
	 * @see it.moise.plc.uml.interfaces.InterfaceVariable#getType()
	 **/
	@Override
	public String getXmiType() {
		// 
		return this.xmiType;
	}

	/**
	 * Set il idTypeVariable della Variable
	 * @param String 
	 * @see it.moise.plc.uml.interfaces.InterfaceVariable#setIdTypeVariable(java.lang.String)
	 **/
	@Override
	public void setIdTypeVariable(String idTypeVariable) {
		this.idTypeVariable = idTypeVariable;

	}

	/**
	 * Restituisce il idTypeVariable della Variable
	 * @see it.moise.plc.uml.interfaces.InterfaceVariable#getIdTypeVariable()
	 **/
	@Override
	public String getIdTypeVariable() {
		// 
		return this.idTypeVariable;
	}
	
	/**
	 * Set il typeReference
	 * @param String typeReference
	 * @see it.moise.plc.uml.interfaces.InterfaceVariable#setTypeReference(java.lang.String)
	 **/
	@Override
	public void setTypeReference(String typeReference) {
		this.typeReference = typeReference;

	}

	/**
	 * Restituisce il typeReference
	 * @see it.moise.plc.uml.interfaces.InterfaceVariable#getTypeReference()
	 **/
	@Override
	public String getTypeReference() {
		// 
		return this.typeReference;
	}

	/**
	 * Set la variabile isOredered
	 * @param boolean isOrdered
	 * @see it.moise.plc.uml.interfaces.InterfaceVariable#setIsOrdered(boolean)
	 **/
	@Override
	public void setIsOrdered(boolean isOrdered) {
		this.isOrdered = isOrdered;

	}

	/**
	 * Restituisce il valore della variabile isOrdered
	 * @see it.moise.plc.uml.interfaces.InterfaceVariable#getIsOrdered()
	 **/
	@Override
	public boolean getIsOrdered() {
		// 
		return this.isOrdered;
	}
	
	/**
	 * Set la variabile isUnique
	 * @param boolean isUnique
	 * @see it.moise.plc.uml.interfaces.InterfaceVariable#setIsUnique(boolean)
	 **/
	@Override
	public void setIsUnique(boolean isUnique) {
		this.isUnique = isUnique;

	}

	/**
	 * Restituisce il valore della variabile isUnique
	 * @see it.moise.plc.uml.interfaces.InterfaceVariable#getIsUnique()
	 **/
	@Override
	public boolean getIsUnique() {
		// 
		return this.isUnique;
	}
	
	/**
	 * Set la variabile visibility
	 * @param boolean visibility
	 * @see it.moise.plc.uml.interfaces.InterfaceVariable#setVisibility(boolean)
	 **/
	@Override
	public void setVisibility(boolean visibility) {
		this.visibility = visibility;

	}

	/**
	 * Restituisce il valore della variabile visibility
	 * @see Interfaces.getVisibility#getVisibility()
	 **/
	@Override
	public boolean getVisibility() {
		// 
		return this.visibility;
	}
	
	@Override
	public void setLowerValue(LowerValue lowerValue ){
		this.lowerValue = lowerValue;
	}
	@Override
	public LowerValue getLowerValue(){
		return this.lowerValue;
	}
	@Override
	public void setUpperValue(UpperValue upperValue ){
		this.upperValue = upperValue;
	}
	@Override
	public UpperValue getUpperValue(){
		return this.upperValue;
	}
	@Override
	public void setTypeVariable(TypeParameter typeVariable){
		this.typeVariable = (TypeParameter) typeVariable;
	}
	@Override
	public TypeParameter getTypeVariable()
	{
		return this.typeVariable;
	}
	
	@Override
	public void setTypeVariableSignal(Signal typeVariableSignal){
		this.typeVariableSignal = typeVariableSignal;
	}
	@Override
	public Signal getTypeVariableSignal()
	{
		return this.typeVariableSignal;
	}
	public void setType(String value){
		this.type = value;
	}
	public String getType(){
		return this.type;
	}
	/**
	 * Imposta a true il valore della variabile di controllo del tipo associato
	 * @param value
	 */
	public void setTipoAssociato(boolean value){
		this.tipoAssociato = value;
	}
	/**
	 * Restituisce true se è il tipo è già stato associato
	 * @return
	 */
	public boolean getTipoAssociato(){
		return this.tipoAssociato;
	}
	/**
	 * Imposta in formato stringa lo amibito di scopo  della variabile
	 * @param value
	 */
	public void setScopeVariable(String value){
		this.scopeVariable = value;
	}
	/**
	 * Restituisce in formato stringa l'ambito di scopo della variabile
	 * @return
	 */
	public String getScopeVariable(){
		return this.scopeVariable;
	}
	/**
	 * Imposta in formato stringa il valore  della variabile
	 * @param value
	 */
	public void setValue(String value){
		this.value = value;
	}
	/**
	 * Restituisce in formato stringa il valore della variabile
	 * @return
	 */
	public String getValue(){
		return this.value;
	}
}
