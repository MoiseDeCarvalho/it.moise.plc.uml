/**
 * 
 **/
package it.moise.plc.uml.value;

import it.moise.plc.uml.interfaces.value.InterfaceLowerValue;

import org.w3c.dom.NamedNodeMap;

/**
 * @author De Carvalho Mo♪se
 *
 **/
public class LowerValue implements InterfaceLowerValue {
	private String id = null;
	private String name = null;
	private String type = null;
	private String value = null;
	/**
	 * 
	 **/
	/**
	 * Set il costruttore e gli passo un node
	 * @param NamedNodeMap node
	 */
	public LowerValue(NamedNodeMap node){
		String delimiter ="xmi:";
		String temp[] = null;
		//System.out.println("\n**********Sezione di caricamento degli attributi *****LowerValue*************");
		for (int i=0; i < node.getLength(); i++){
			temp = null;
			delimiter ="xmi:";
			//System.out.println(node.item(i).getNodeName() + " -- " + node.item(i).getNodeValue());
			temp = node.item(i).getNodeName().split(delimiter);
			if (temp[0].equalsIgnoreCase("")) {
				temp[0] = temp[1];
			}
			if (temp.length > 0){
				//temp = node.item(i).getNodeName().split(delimiter);
				//System.out.println("\t ---- " + temp[0]);
				
				switch (temp[0]){
				case "name":
					this.setName(node.item(i).getNodeValue());
					//System.out.println("\t ---- " + this.getName()+"    ecoo il NAME");
					break;
				case "id":
					this.setId(node.item(i).getNodeValue());
					//System.out.println("\t ---- " + this.getId()+"    ecoo il ID");
					break;
				case "type":
					this.setType(node.item(i).getNodeValue());
					//System.out.println("\t ---- " + this.getType()+"    ecoo il TYPE");
					break;
				case "value":
					this.setValue(node.item(i).getNodeValue());
					//System.out.println("\t ---- " + this.getValue()+"    ecoo il VALUE");
					break;
				
					
				}
			}
		}
		//System.out.println("\n**********Fine caricamento degli attributi *****LowerValue*************");
	}
	public LowerValue() {
	}

	/**
	 * Set l'id dell'Value
	 * @param String id
	 * @see it.moise.plc.uml.interfaces.value.InterfaceLowerValue#setId(java.lang.String)
	 **/
	@Override
	public void setId(String id) {
		this.id = id;

	}

	/**
	 * Restituisce l'id dell'Value
	 * @see it.moise.plc.uml.interfaces.value.InterfaceLowerValue#getId()
	 **/
	@Override
	public String getId() {
		// 
		return this.id;
	}

	/**
	 * Set il nome dell'Value
	 * @param String name
	 * @see it.moise.plc.uml.interfaces.value.InterfaceLowerValue#setName(java.lang.String)
	 **/
	@Override
	public void setName(String name) {
		this.name = name;

	}

	/**
	 * Restituisce il nome dell'value
	 * @see it.moise.plc.uml.interfaces.value.InterfaceLowerValue#getName()
	 **/
	@Override
	public String getName() {
		// 
		return this.name;
	}

	/**
	 * Set il tipo dell'Value
	 * @param String type
	 * @see it.moise.plc.uml.interfaces.value.InterfaceLowerValue#setType(java.lang.String)
	 **/
	@Override
	public void setType(String type) {
		this.type = type;

	}

	/**
	 * Restituisce il tipo dell'Value
	 * @see it.moise.plc.uml.interfaces.value.InterfaceLowerValue#getType()
	 **/
	@Override
	public String getType() {
		// 
		return this.type;
	}

	/**
	 * Set il valore di value
	 * @param String value
	 * @see it.moise.plc.uml.interfaces.value.InterfaceLowerValue#setvalue(java.lang.String)
	 **/
	@Override
	public void setValue(String value) {
		this.value = value;

	}

	/**
	 * Restituisce il valore
	 * @see it.moise.plc.uml.interfaces.value.InterfaceLowerValue#getVaue()
	 **/
	@Override
	public String getValue() {
		// 
		return this.value;
	}

}
