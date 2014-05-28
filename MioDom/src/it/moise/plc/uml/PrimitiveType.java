/**
 * 
 */
package it.moise.plc.uml;

import it.moise.plc.uml.interfaces.InterfacePrimitiveType;

import org.w3c.dom.NamedNodeMap;




/**
 * Classe che costruisce un oggetto PrimitiveType
 * @author De Carvalho Moise
 *
 */
public class PrimitiveType implements InterfacePrimitiveType {
	private String id = "";
	private String name = "";
	private boolean tipoConfermato = false;
	
	/**
	 * Costruttore con parametri
	 * @param node Oggetto di tipo NamedNodeMap da analizzare
	 */
	public PrimitiveType(NamedNodeMap node){
		String delimiter ="xmi:";
		String temp = null;
		for (int i=0; i < node.getLength(); i++){
			temp = null;
			delimiter ="xmi:";
			temp = node.item(i).getNodeName();
			
			if (temp.length() > 0){
				
				switch (temp){
				case "name":
					this.setName(node.item(i).getNodeValue().toUpperCase());
					break;
				case "xmi:id":
					this.setId(node.item(i).getNodeValue());
					String a = node.item(i).getNodeValue();
					break;
				}
			}
		}
	}
	/**
	 * Costruttore senza parametri
	 */
	public PrimitiveType() {
		
	}

     /**
      * Imposta il nome dell'estensione
      * @param name Contiene il nome
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
	  * @param value Contiene l'id
	 * @see it.moise.plc.uml.interfaces.InterfaceExtension#setId(java.lang.String)
	 */
	@Override
	public void setId(String value) {
		this.id = value;
	}

	 /**
	  * Restituisce il nome dell'id
	 * @see it.moise.plc.uml.interfaces.InterfaceExtension#getId()
	 */
	@Override
	public String getId() {
		return this.id;
	}
	
	/**
	 * Imposta il valore della variabile tipoConfermato, se TRUE indica che il tipo è accettato da codesys
	 * @param value Valore booleano
	 */
	public void setTipoConfermato(boolean value){
		this.tipoConfermato = value;
	}
	/**
	 * Restituisce il tipo della variabile tipoConfermato, se FALSE non è accettato da codesys e sarà utilizzato un tipo di default
	 * @return tipoConfermato Restituisc eun valore booleano
	 */
	public boolean getTipoConfermato(){
		return this.tipoConfermato;
	}

}
