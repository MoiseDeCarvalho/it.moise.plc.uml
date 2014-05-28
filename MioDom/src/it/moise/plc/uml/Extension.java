/**
 * 
 */
package it.moise.plc.uml;

import it.moise.plc.uml.interfaces.InterfaceExtension;

import org.w3c.dom.NamedNodeMap;

/**
 * Classe che costruisce un oggetto Extension
 * @author De Carvalho Moise
 *
 */
public class Extension implements InterfaceExtension {
	private String id = "";
	private String name = "";
	/**
	 * Costruttore con parametri
	 * @param node Oggetto di tipo NamedNodeMap da analizzare per estrarre i valori
	 */
	public Extension(NamedNodeMap node){
		String delimiter ="xmi:";
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
				case "name":
					this.setName(node.item(i).getNodeValue());
					break;
				case "id":
					this.setId(node.item(i).getNodeValue());
					break;
				}
			}
		}
	}
	/**
	 * Costruttore senza parametri
	 */
	public Extension() {
		
	}

     /**
      * Imposta il nome dell'estensione
      * @param name Nome di extension
	 * @see it.moise.plc.uml.interfaces.InterfaceExtension#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;

	}

	 /**
	  * Restiruisce il nome dell'estensione
	 * @see it.moise.plc.uml.interfaces.InterfaceExtension#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}
	 /**
	  * Imposta il nome dell'id
	  * @param id Indica l'id di extension
	 * @see it.moise.plc.uml.interfaces.InterfaceExtension#setId(java.lang.String)
	 */
	@Override
	public void setId(String id) {
		this.id = id;
	}

	 /**
	  * Restituisce il nome dell'id
	 * @see it.moise.plc.uml.interfaces.InterfaceExtension#getId()
	 */
	@Override
	public String getId() {
		return this.id;
	}

}
